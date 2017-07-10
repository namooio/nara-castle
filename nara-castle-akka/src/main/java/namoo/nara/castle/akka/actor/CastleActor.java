package namoo.nara.castle.akka.actor;

import akka.actor.Props;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.command.castellan.RegisterCastellanCommand;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.castle.domain.spec.command.castle.WithdrawMetroCommand;
import namoo.nara.castle.domain.spec.event.castellan.CastellanCreated;
import namoo.nara.castle.domain.spec.event.castle.CastleModified;
import namoo.nara.castle.domain.spec.event.castle.MetroEnrolled;
import namoo.nara.castle.domain.spec.event.castle.MetroWithdrawn;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import namoo.nara.share.akka.support.actor.NaraPersistentActor;
import namoo.nara.share.domain.event.NaraEvent;
import namoo.nara.share.domain.protocol.NaraCommand;
import namoo.nara.share.domain.protocol.NaraQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleActor extends NaraPersistentActor<Castle> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    static public Props props(String castleId) {
        //
        return Props.create(CastleActor.class, () -> new CastleActor(castleId));
    }

    static public Props props(Castle castle) {
        //
        return Props.create(CastleActor.class, () -> new CastleActor(castle));
    }

    public CastleActor(String castleId) {
        //
        super(new Castle(castleId));
    }

    public CastleActor(Castle castle) {
        //
        super(castle);
    }

    @Override
    public void handleEvent(NaraEvent event) {
        //
        if (event instanceof MetroEnrolled) {
            handleMetroEnrolledEvent((MetroEnrolled) event);
        }
        else if (event instanceof MetroWithdrawn) {
            handleMetroWithdrawnEvent((MetroWithdrawn) event);
        }
        else if (event instanceof CastleModified) {
            handleCastleModifiedEvent((CastleModified) event);
        }
        else if (event instanceof CastellanCreated) {
            handleCastellanCreatedEvent((CastellanCreated) event);
        }
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        if (command instanceof EnrollMetroCommand) {
            handleEnrollMetroCommand((EnrollMetroCommand) command);
        }
        else if (command instanceof WithdrawMetroCommand) {
            handleWithdrawMetroCommand((WithdrawMetroCommand) command);
        }
        else if (command instanceof ModifyCastleCommand) {
            handleModifyCastleCommand((ModifyCastleCommand) command);
        }
        else if (command instanceof RegisterCastellanCommand) {
            handleRegisterCastellanCommand((RegisterCastellanCommand) command);
        }
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        if (query instanceof FindCastleQuery) {
            handleFindCastleQuery((FindCastleQuery) query);
        }
    }

    /*********************** Command ***********************/

    private void handleEnrollMetroCommand(EnrollMetroCommand command) {
        //
        persist(new MetroEnrolled(getState().getId(), command.getEnrollment()), this::handleMetroEnrolledEvent);
    }

    private void handleWithdrawMetroCommand(WithdrawMetroCommand command) {
        //
        String metroId = command.getMetroId();
        String civilianId = command.getCivilianId();
        persist(new MetroWithdrawn(metroId, civilianId), this::handleMetroWithdrawnEvent);
    }

    private void handleModifyCastleCommand(ModifyCastleCommand command) {
        //
        persist(new CastleModified(command), this::handleCastleModifiedEvent);
    }

    private void handleRegisterCastellanCommand(RegisterCastellanCommand command) {
        //
        Castle castle = getState();
        String castellanId = castle.getId();

        lookupOrCreateChildPersistentActor(castellanId, Castellan.class, CastellanActor.props(castle));
        persist(new CastellanCreated(castellanId), this::handleCastellanCreatedEvent);
    }

    /*********************** Command ***********************/

    /*********************** Query ***********************/

    private void handleFindCastleQuery(FindCastleQuery query) {
        //
        getSender().tell(getState(), getSelf());
    }

    /*********************** Query ***********************/

    /*********************** Event ***********************/

    private void handleMetroEnrolledEvent(MetroEnrolled event) {
        //
        getState().apply(event);
    }

    private void handleMetroWithdrawnEvent(MetroWithdrawn event) {
        //
        getState().apply(event);
    }

    private void handleCastleModifiedEvent(CastleModified event) {
        //
        getState().apply(event);
    }

    private void handleCastellanCreatedEvent(CastellanCreated event) {
        //
    }

    /*********************** Event ***********************/
}
