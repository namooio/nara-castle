package nara.castle.actor.akka.command;

import akka.actor.Props;
import nara.castle.domain.castle.command.*;
import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castle.entity.Enrollment;
import nara.castle.domain.castle.event.*;
import nara.share.actor.akka.NaraPersistentActor;
import nara.share.domain.event.NaraEvent;
import nara.share.domain.protocol.NaraCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleActor extends NaraPersistentActor<Castellan> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    static public Props props(String castleId) {
        //
        return Props.create(CastleActor.class, () -> new CastleActor(castleId));
    }

    static public Props props(Castellan castellan) {
        //
        return Props.create(CastleActor.class, () -> new CastleActor(castellan));
    }

    public CastleActor(String castellanId) {
        //
        super(new Castellan(castellanId));
    }

    public CastleActor(Castellan castellan) {
        //
        super(castellan);
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        matcher()
        .match(BuildCastleCommand.class, buildCastleCommand -> {
            //
            persist(new CastleBuilt(getState()), this::handleAndRespond);
        })
        .match(AddEnrollmentCommand.class, addEnrollmentCommand -> {
            //
            persist(new MetroEnrolled(getState().getId(), addEnrollmentCommand.getEnrollment()), this::handleAndRespond);
        })
        .match(DemolishCastleCommand.class, demolishCastleCommand -> {
            //
            persist(new CastleDemolished(getState().getId()), this::handleAndRespond);
        })
        .match(ModifyCastellanCommand.class, modifyCastellanCommand -> {
            //
            persist(new CastellanModified(getState().getId(), modifyCastellanCommand.getNameValues()), this::handleAndRespond);
        })
        .match(WithdrawMetroCommand.class, withdrawMetroCommand -> {
            //
            Enrollment withdrawalEnrollment = getState().findEnrollment(withdrawMetroCommand.getMetroId(), withdrawMetroCommand.getCivilianId());
            withdrawalEnrollment.withdraw();
            persist(new MetroWithdrawn(withdrawalEnrollment), this::handleAndRespond);
        })
        .onMessage(command);
    }

    @Override
    public void handleEvent(NaraEvent event) {
        //
        matcher()
        .match(CastleBuilt.class, castleBuilt -> getState().apply(castleBuilt))
        .match(CastellanModified.class, castellanModified -> getState().apply(castellanModified))
        .match(CastleDemolished.class, castleDemolished -> getState().apply(castleDemolished))
        .match(MetroEnrolled.class, metroEnrolled -> getState().apply(metroEnrolled))
        .match(MetroWithdrawn.class, metroWithdrawn -> getState().apply(metroWithdrawn))
        .onMessage(event);
    }


}
