package namoo.nara.castle.cp.akka.actor.domain;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.persistence.AbstractPersistentActor;
import namoo.nara.castle.cp.akka.actor.store.command.castlebook.UpdateCastleBookCommand;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.castle.domain.spec.command.castlebook.NextSequenceCommand;
import namoo.nara.castle.domain.spec.event.castlebook.SequenceIncreased;
import namoo.nara.castle.domain.spec.query.castlebook.FindCastleBookQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleBookActor extends AbstractPersistentActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleBook castleBook;
    private ActorRef castleStoreActor;

    static public Props props(ActorRef castleStoreActor) {
        //
        return Props.create(CastleBookActor.class, () -> new CastleBookActor(castleStoreActor));
    }

    public CastleBookActor(ActorRef castleStoreActor) {
        //
        this.castleBook = new CastleBook();
        this.castleStoreActor = castleStoreActor;
    }

    @Override
    public String persistenceId() {
        //
        return CastleIdBuilder.makeCastleBookId();
    }

    @Override
    public Receive createReceiveRecover() {
        //
        return receiveBuilder()
                .match(SequenceIncreased.class, this::handleSequenceIncreasedEvent)
//                .match(SnapshotOffer.class, ss -> {
//                    logger.debug("offered state = {}", ss);
//                    Object snapshot = ss.snapshot();
//                })

                .build();
    }

    @Override
    public Receive createReceive() {
        //
        return receiveBuilder()
                // command
                .match(NextSequenceCommand.class, this::handleNextSequenceCommand)

                // query
                .match(FindCastleBookQuery.class, this::handleFindCastleBookQuery)

                .build();
    }

    private void handleNextSequenceCommand(NextSequenceCommand command) {
        //
        long nextSequence = castleBook.nextSequence();

        persist(new SequenceIncreased(castleBook), this::handleSequenceIncreasedEvent);
        getSender().tell(nextSequence, getSelf());
    }


    private void handleFindCastleBookQuery(FindCastleBookQuery query) {
        //
        getSender().tell(castleBook, getSelf());
    }

    private void handleSequenceIncreasedEvent(SequenceIncreased event) {
        //
        castleBook.apply(event);
        castleStoreActor.tell(new UpdateCastleBookCommand(event.getCastleBook()), getSelf());
    }


}
