package namoo.nara.castle.cp.akka.actor.domain;

import akka.actor.Props;
import namoo.nara.castle.cp.akka.actor.share.NaraPersistentActor;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.IdentityPlate;
import namoo.nara.castle.domain.spec.command.castellan.ModifyCastellanCommand;
import namoo.nara.castle.domain.spec.event.castellan.CastellanModified;
import namoo.nara.castle.domain.spec.query.castellan.FindIdentityPlateQuery;

public class CastellanActor extends NaraPersistentActor {
    //
    private Castellan castellan;

    static public Props props(Castellan castellan) {
        //
        return Props.create(CastellanActor.class, () -> new CastellanActor(castellan));
    }

    public CastellanActor(Castellan castellan) {
        //
        super(castellan.getId());
        this.castellan = castellan;
    }

    @Override
    public Receive createReceiveRecover() {
        //
        return receiveBuilder()
                .match(CastellanModified.class, this::handleCastellanModifiedEvent)
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
                .match(ModifyCastellanCommand.class, this::handleModifyCastellanCommand)

                // query
                .match(FindIdentityPlateQuery.class, this::handleFindIdentityPlateQuery)

                .build();
    }

    /*********************** Command ***********************/

    private void handleModifyCastellanCommand(ModifyCastellanCommand command) {
        //
        persist(new CastellanModified(command), this::handleCastellanModifiedEvent);
    }

    /*********************** Command ***********************/

    /*********************** Query ***********************/

    private void handleFindIdentityPlateQuery(FindIdentityPlateQuery query) {
        //
        IdentityPlate identityPlate = new IdentityPlate(castellan);
        getSender().tell(identityPlate, getSelf());
    }

    /*********************** Query ***********************/

    /*********************** Event ***********************/

    private void handleCastellanModifiedEvent(CastellanModified event) {
        //
        castellan.apply(event);
    }

    /*********************** Event ***********************/
}
