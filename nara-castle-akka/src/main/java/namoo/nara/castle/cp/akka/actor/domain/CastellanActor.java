package namoo.nara.castle.cp.akka.actor.domain;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.persistence.AbstractPersistentActor;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.spec.command.castellan.RegisterCastellanCommand;
import namoo.nara.castle.domain.spec.event.castellan.CastellanCreated;

public class CastellanActor extends AbstractPersistentActor {
    //
    private Castellan castellan;
    private ActorRef castleStoreActor;

    static public Props props(Castellan castellan, ActorRef castleStoreActor) {
        //
        return Props.create(CastellanActor.class, () -> new CastellanActor(castellan, castleStoreActor));
    }

    public CastellanActor(Castellan castellan, ActorRef castleStoreActor) {
        //
        this.castellan = castellan;
        this.castleStoreActor = castleStoreActor;
    }

    @Override
    public String persistenceId() {
        //
        return castellan.getId();
    }

    @Override
    public Receive createReceiveRecover() {
        //
        return receiveBuilder()
//                .match(CastleModified.class, this::handleCastleModifiedEvent)
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
//                .match(RegisterCastellanCommand.class, this::handleRegisterCastellanCommand)
//                .match(ModifyCastleCommand.class, this::handleModifyCastleCommand)

                // query
//                .match(FindCastleQuery.class, this::handleFindCastleQuery)

                .build();
    }

}
