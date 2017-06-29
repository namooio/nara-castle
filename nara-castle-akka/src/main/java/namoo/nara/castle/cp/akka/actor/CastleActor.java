package namoo.nara.castle.cp.akka.actor;

import akka.actor.Props;
import akka.persistence.AbstractPersistentActor;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.command.castlebook.NextSequenceCommand;
import namoo.nara.castle.domain.spec.query.castlebook.FindCastleBookQuery;
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
//                .match(NextSequenceCommand.class, this::handleNextSequenceCommand)

                // query
//                .match(FindCastleBookQuery.class, this::handleFindCastleBookQuery)

                .build();
    }
}
