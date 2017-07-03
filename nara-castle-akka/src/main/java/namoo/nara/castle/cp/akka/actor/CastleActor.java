package namoo.nara.castle.cp.akka.actor;

import akka.actor.Props;
import akka.persistence.AbstractPersistentActor;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.castle.domain.spec.event.castle.CastleModified;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleActor extends AbstractPersistentActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private Castle castle;

    static public Props props(Castle castle) {
        //
        return Props.create(CastleActor.class, () -> new CastleActor(castle));
    }

    public CastleActor(Castle castle) {
        //
        this.castle = castle;
    }

    @Override
    public String persistenceId() {
        //
        return castle.getId();
    }

    @Override
    public Receive createReceiveRecover() {
        //
        return receiveBuilder()
//                .match(SequenceIncreased.class, this::handleSequenceIncreasedEvent)
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
                .match(ModifyCastleCommand.class, this::handleModifyCastleCommand)

                // query
                .match(FindCastleQuery.class, this::handleFindCastleQuery)

                .build();
    }

    private void handleModifyCastleCommand(ModifyCastleCommand command) {
        //
        logger.debug("Handle command start  {}[{}]", command.getClass().getSimpleName(), command);

        castle.setValues(command.getNameValues());
        persist(new CastleModified(command), this::handleCastleModified);

        logger.debug("Handle command finish {}[{}]", command.getClass().getSimpleName(), command);
    }

    private void handleFindCastleQuery(FindCastleQuery query) {
        //
        logger.debug("Handle query start  {}[{}]", query.getClass().getSimpleName(), query);

        getSender().tell(castle, getSelf());

        logger.debug("Handle query finish {}[{}]", query.getClass().getSimpleName(), query);
    }

    private void handleCastleModified(CastleModified event) {
        //
        logger.debug("Handle event start  {}[{}]", event.getClass().getSimpleName(), event);

        logger.debug("Handle event start  {}[{}]", event.getClass().getSimpleName(), event);
    }
}
