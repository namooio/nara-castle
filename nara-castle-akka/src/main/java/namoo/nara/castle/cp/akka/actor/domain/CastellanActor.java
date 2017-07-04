package namoo.nara.castle.cp.akka.actor.domain;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.persistence.AbstractPersistentActor;
import namoo.nara.castle.cp.akka.actor.store.command.castellan.UpdateCastellanCommand;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.IdentityPlate;
import namoo.nara.castle.domain.spec.command.castellan.ModifyCastellanCommand;
import namoo.nara.castle.domain.spec.event.castellan.CastellanModified;
import namoo.nara.castle.domain.spec.query.castellan.FindIdentityPlateQuery;

public class CastellanActor extends AbstractPersistentActor {
    //
    private Castellan castellan;
    private ActorRef castellanStore;

    static public Props props(Castellan castellan, ActorRef castellanStore) {
        //
        return Props.create(CastellanActor.class, () -> new CastellanActor(castellan, castellanStore));
    }

    public CastellanActor(Castellan castellan, ActorRef castellanStore) {
        //
        this.castellan = castellan;
        this.castellanStore = castellanStore;
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
        castellanStore.tell(new UpdateCastellanCommand(castellan), getSelf());
    }

    /*********************** Event ***********************/
}
