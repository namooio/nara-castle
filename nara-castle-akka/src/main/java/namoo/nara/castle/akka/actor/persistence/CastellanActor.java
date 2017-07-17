package namoo.nara.castle.akka.actor.persistence;

import akka.actor.Props;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.IdentityPlate;
import namoo.nara.castle.domain.spec.command.castellan.ModifyCastellanCommand;
import namoo.nara.castle.domain.spec.command.castellan.RegisterCastellanCommand;
import namoo.nara.castle.domain.spec.event.castellan.CastellanCreated;
import namoo.nara.castle.domain.spec.event.castellan.CastellanModified;
import namoo.nara.castle.domain.spec.query.castellan.FindIdentityPlateQuery;
import namoo.nara.share.akka.support.actor.NaraPersistentActor;
import namoo.nara.share.domain.event.NaraEvent;
import namoo.nara.share.domain.protocol.NaraCommand;
import namoo.nara.share.domain.protocol.NaraQuery;

public class CastellanActor extends NaraPersistentActor<Castellan> {
    //
    static public Props props(String castellanId) {
        //
        return Props.create(CastellanActor.class, () -> new CastellanActor(castellanId));
    }

    public CastellanActor(String castellanId) {
        //
        super(new Castellan(castellanId));
    }

    @Override
    public void handleEvent(NaraEvent event) {
        //
        match()
            .with(CastellanCreated.class, this::handleCastellanCreatedEvent)
            .with(CastellanModified.class, this::handleCastellanModifiedEvent)
            .exec(event);
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        match()
            .with(RegisterCastellanCommand.class, this::handleRegisterCastellanCommand)
            .with(ModifyCastellanCommand.class, this::handleModifyCastellanCommand)
            .exec(command);
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        match()
            .with(FindIdentityPlateQuery.class, this::handleFindIdentityPlateQuery)
            .exec(query);
    }

    /*********************** Command ***********************/

    private void handleRegisterCastellanCommand(RegisterCastellanCommand command) {
        //
        Castellan castellan = new Castellan(command.getCastle());
        persistAndHandle(new CastellanCreated(castellan));
    }

    private void handleModifyCastellanCommand(ModifyCastellanCommand command) {
        //
        persistAndHandle(new CastellanModified(command));
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
        getSender().tell("todo", getSelf());
    }

    private void handleCastellanModifiedEvent(CastellanModified event) {
        //
        getState().apply(event);
        getSender().tell("todo", getSelf());
    }

    /*********************** Event ***********************/
}
