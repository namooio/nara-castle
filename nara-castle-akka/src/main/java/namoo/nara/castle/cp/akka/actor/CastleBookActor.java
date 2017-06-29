package namoo.nara.castle.cp.akka.actor;

import akka.actor.Props;
import akka.persistence.AbstractPersistentActor;
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

    static public Props props() {
        //
        return Props.create(CastleBookActor.class, () -> new CastleBookActor());
    }

    public CastleBookActor() {
        //
        this.castleBook = new CastleBook();
    }

    @Override
    public String persistenceId() {
        //
        return castleBook.getId();
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
        logger.debug("Handle command start  {}{}", command.getClass().getSimpleName(), command);

        long nextSequence = castleBook.getSequence() + 1;

        persist(new SequenceIncreased(nextSequence), this::handleSequenceIncreasedEvent);
        getSender().tell(nextSequence, getSelf());

        logger.debug("Handle command finish {}{}", command.getClass().getSimpleName(), command);
    }


    private void handleFindCastleBookQuery(FindCastleBookQuery query) {
        //
        logger.debug("Handle query start  {}{}", query.getClass().getSimpleName(), query);

        getSender().tell(castleBook, getSelf());

        logger.debug("Handle query finish {}{}", query.getClass().getSimpleName(), query);
    }

    private void handleSequenceIncreasedEvent(SequenceIncreased event) {
        //
        logger.debug("Handle event start  {}{}", event.getClass().getSimpleName(), event);

        castleBook.apply(event);

        logger.debug("Handle event start  {}{}", event.getClass().getSimpleName(), event);
    }


}
