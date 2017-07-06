package namoo.nara.castle.cp.akka.actor.share;

import akka.NotUsed;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.persistence.AbstractPersistentActor;
import akka.persistence.query.EventEnvelope;
import akka.persistence.query.PersistenceQuery;
import akka.persistence.query.journal.leveldb.javadsl.LeveldbReadJournal;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import akka.util.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static akka.pattern.PatternsCS.ask;

public abstract class NaraPersistentActor extends AbstractPersistentActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private String persistentId;

    private ActorMaterializer actorMaterializer;
    private LeveldbReadJournal readJournal;

    private Timeout timeout;
    private ResumableProjection resumableProjection;

    public NaraPersistentActor(String persistentId) {
        //
        this.persistentId = persistentId;
        this.resumableProjection = new ResumableProjection();
        this.timeout = Timeout.apply(3, TimeUnit.SECONDS);
    }

    @Override
    public String persistenceId() {
        //
        return persistentId;
    }

    private void startStoreBuilder() throws ExecutionException, InterruptedException {
        //
        ActorSystem actorSystem = getContext().getSystem();

        this.actorMaterializer = ActorMaterializer.create(actorSystem);
        this.readJournal = PersistenceQuery.get(actorSystem).getReadJournalFor(LeveldbReadJournal.class, LeveldbReadJournal.Identifier());

        final Props writerProps = Props.create(TheOneWhoWritesToQueryJournal.class, "bid");
        final ActorRef writer = actorSystem.actorOf(writerProps, "bid-projection-writer");

        Long latestOffset = this.resumableProjection.latestOffset().toCompletableFuture().get();

        Source<EventEnvelope, NotUsed> source = readJournal.eventsByPersistenceId(this.persistenceId(), latestOffset, Long.MAX_VALUE);

        source.mapAsync(8, envelope -> {
            //
            logger.debug("{}", envelope);
            final CompletionStage<Object> f = ask(writer, envelope.event(), timeout);
            return f.thenApplyAsync(in -> envelope.offset(), actorSystem.dispatcher());
        })
        .mapAsync(1, offset -> resumableProjection.saveProgress(offset))
        .runWith(Sink.ignore(), actorMaterializer);
    }

    @Override
    public void preStart() throws Exception {
        //
        this.startStoreBuilder();
    }

    //#projection-into-different-store-simple-classes
    class ExampleStore {
        CompletionStage<Void> save(Object any) {
            // ...
            //#projection-into-different-store-simple-classes
            return null;
            //#projection-into-different-store-simple-classes
        }
    }
    //#projection-into-different-store-simple-classes

    //#projection-into-different-store-actor-run

    class ComplexState {

        boolean readyToSave() {
            return false;
        }
    }

    static class Record {
        static Record of(Object any) {
            return new Record();
        }
    }

    //#projection-into-different-store-actor
    final class TheOneWhoWritesToQueryJournal extends AbstractActor {

        private final ExampleStore store;

        private ComplexState state = new ComplexState();

        public TheOneWhoWritesToQueryJournal() {
            store = new ExampleStore();
        }

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .matchAny(message -> {
                        state = updateState(state, message);

                        // example saving logic that requires state to become ready:
                        if (state.readyToSave())
                            store.save(Record.of(state));

                    })
                    .build();
        }


        ComplexState updateState(ComplexState state, Object msg) {
            // some complicated aggregation logic here ...
            return state;
        }
    }
    //#projection-into-different-store-actor
}
