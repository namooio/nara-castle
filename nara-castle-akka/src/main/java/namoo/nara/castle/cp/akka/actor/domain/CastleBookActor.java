package namoo.nara.castle.cp.akka.actor.domain;

import akka.actor.Props;
import namoo.nara.castle.cp.akka.actor.share.NaraPersistentActor;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.castle.domain.spec.command.castlebook.NextSequenceCommand;
import namoo.nara.castle.domain.spec.event.castlebook.SequenceIncreased;
import namoo.nara.castle.domain.spec.query.castlebook.FindCastleBookQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleBookActor extends NaraPersistentActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleBook castleBook;

    static public Props props() {
        //
        return Props.create(CastleBookActor.class, () -> new CastleBookActor());
    }

    public CastleBookActor() {
        //
        super(CastleIdBuilder.makeCastleBookId());
        this.castleBook = new CastleBook();
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

    /*********************** Command ***********************/

    private void handleNextSequenceCommand(NextSequenceCommand command) {
        //
        long nextSequence = castleBook.nextSequence();

        persist(new SequenceIncreased(castleBook), this::handleSequenceIncreasedEvent);
        getSender().tell(nextSequence, getSelf());
    }

    /*********************** Command ***********************/

    /*********************** Query ***********************/

    private void handleFindCastleBookQuery(FindCastleBookQuery query) {
        //
        getSender().tell(castleBook, getSelf());
    }

    /*********************** Query ***********************/


    /*********************** Event ***********************/

    private void handleSequenceIncreasedEvent(SequenceIncreased event) {
        //
        castleBook.apply(event);
    }

    /*********************** Event ***********************/

}
