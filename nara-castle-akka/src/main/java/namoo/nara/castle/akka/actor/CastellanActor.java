package namoo.nara.castle.akka.actor;

import akka.actor.Props;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.IdentityPlate;
import namoo.nara.castle.domain.spec.command.castellan.ModifyCastellanCommand;
import namoo.nara.castle.domain.spec.event.castellan.CastellanModified;
import namoo.nara.castle.domain.spec.query.castellan.FindIdentityPlateQuery;
import namoo.nara.share.akka.support.actor.NaraPersistentActor;
import namoo.nara.share.domain.event.NaraEvent;
import namoo.nara.share.domain.protocol.NaraCommand;
import namoo.nara.share.domain.protocol.NaraQuery;

public class CastellanActor extends NaraPersistentActor<Castellan> {
    //
    static public Props props(Castle castle) {
        //
        return Props.create(CastellanActor.class, () -> new CastellanActor(castle));
    }

    public CastellanActor(Castle castle) {
        //
        super(new Castellan(castle));
    }

    @Override
    public void handleEvent(NaraEvent event) {
        //
        if (event instanceof CastellanModified) {
            handleCastellanModifiedEvent((CastellanModified) event);
        }
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        if (command instanceof ModifyCastellanCommand) {
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

    private void handleCastellanModifiedEvent(CastellanModified event) {
        //
        getState().apply(event);
    }

    /*********************** Event ***********************/
}
