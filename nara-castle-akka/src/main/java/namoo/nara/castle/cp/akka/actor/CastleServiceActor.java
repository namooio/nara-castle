package namoo.nara.castle.cp.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleServiceActor extends AbstractActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleService castleService;

    static public Props props(CastleService castleService) {
        //
        return Props.create(CastleServiceActor.class, () -> new CastleServiceActor(castleService));
    }

    public CastleServiceActor(CastleService castleService) {
        //
        this.castleService = castleService;
    }

    @Override
    public Receive createReceive() {
        //
        return receiveBuilder()
                // command
                .match(EnrollMetroCommand.class, this::handleEnrollMetroCommand)

                // query
//                .match(NaraQuery.class, this::handleQuery)
                .build();
    }

    private void handleEnrollMetroCommand(EnrollMetroCommand command) {
        //
        logger.debug("Handle command start  {}{}", command.getClass().getSimpleName(), command);

//        long nextSequence = castleBook.getSequence() + 1;
//
//        persist(new SequenceIncreased(nextSequence), this::handleSequenceIncreasedEvent);
//        getSender().tell(nextSequence, getSelf());

        logger.debug("Handle command finish {}{}", command.getClass().getSimpleName(), command);
    }

}
