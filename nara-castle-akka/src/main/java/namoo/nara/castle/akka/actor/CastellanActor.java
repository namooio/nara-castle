package namoo.nara.castle.akka.actor;

import akka.actor.Props;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.IdentityPlate;
import namoo.nara.castle.domain.spec.command.castellan.ModifyCastellanCommand;
import namoo.nara.castle.domain.spec.command.castellan.RegisterCastellanCommand;
import namoo.nara.castle.domain.spec.event.castellan.CastellanCreated;
import namoo.nara.castle.domain.spec.event.castellan.CastellanModified;
import namoo.nara.castle.domain.spec.query.castellan.FindIdentityPlateQuery;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.share.akka.support.actor.NaraPersistentActor;
import namoo.nara.share.domain.event.NaraEvent;
import namoo.nara.share.domain.protocol.NaraCommand;
import namoo.nara.share.domain.protocol.NaraQuery;

public class CastellanActor extends NaraPersistentActor<Castellan> {
    //
    static public Props props(String castellanId, CastleStoreLycler storeLycler) {
        //
        return Props.create(CastellanActor.class, () -> new CastellanActor(castellanId, storeLycler));
    }

    public CastellanActor(String castellanId, CastleStoreLycler storeLycler) {
        //
        super(new Castellan(castellanId));

//        getViewProjectorMap().put(CastleBuilt.class.getName(), new CastleBuiltViewProjector(castleStore));
//        getViewProjectorMap().put(MetroEnrolled.class.getName(), new MetroEnrolledViewProjector(castleStore));
    }

    @Override
    public void handleEvent(NaraEvent event) {
        //
        if (event instanceof CastellanCreated) {
            handleCastellanCreatedEvent((CastellanCreated) event);
        }
        if (event instanceof CastellanModified) {
            handleCastellanModifiedEvent((CastellanModified) event);
        }
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        if (command instanceof RegisterCastellanCommand) {
            handleRegisterCastellanCommand((RegisterCastellanCommand) command);
        }
        else if (command instanceof ModifyCastellanCommand) {
            handleModifyCastellanCommand((ModifyCastellanCommand) command);
        }
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        if (query instanceof FindIdentityPlateQuery) {
            handleFindIdentityPlateQuery((FindIdentityPlateQuery) query);
        }
    }

    /*********************** Command ***********************/

    private void handleRegisterCastellanCommand(RegisterCastellanCommand command) {
        //
        persist(new CastellanCreated(command.getEnrollment()), this::handleCastellanCreatedEvent);
    }

    private void handleModifyCastellanCommand(ModifyCastellanCommand command) {
        //
        persist(new CastellanModified(command), this::handleCastellanModifiedEvent);
    }

    /*********************** Command ***********************/

    /*********************** Query ***********************/

    private void handleFindIdentityPlateQuery(FindIdentityPlateQuery query) {
        //
        IdentityPlate identityPlate = new IdentityPlate(getState());
        getSender().tell(identityPlate, getSelf());
    }

    /*********************** Query ***********************/

    /*********************** Event ***********************/

    private void handleCastellanCreatedEvent(CastellanCreated event) {
        //
        getState().apply(event);
        getSender().tell(getState().getId(), getSelf());
    }

    private void handleCastellanModifiedEvent(CastellanModified event) {
        //
        getState().apply(event);
    }

    /*********************** Event ***********************/
}
