package nara.castle.actor.akka.command;

import akka.actor.Props;
import nara.castle.domain.castle.command.*;
import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castle.entity.Enrollment;
import nara.castle.domain.castle.event.*;
import nara.share.actor.akka.NaraPersistentActor;
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
            persist(new CastleBuiltEvent(getState()), this::handleAndRespond);
        })
        .match(AddEnrollmentCommand.class, addEnrollmentCommand -> {
            //
            persist(new MetroEnrolledEvent(getState().getId(), addEnrollmentCommand.getEnrollment()), this::handleAndRespond);
        })
        .match(DemolishCastleCommand.class, demolishCastleCommand -> {
            //
            persist(new CastleDemolishedEvent(getState().getId()), this::handleAndRespond);
        })
        .match(ModifyCastellanCommand.class, modifyCastellanCommand -> {
            //
            persist(new CastellanModifiedEvent(getState().getId(), modifyCastellanCommand.getNameValues()), this::handleAndRespond);
        })
        .match(WithdrawMetroCommand.class, withdrawMetroCommand -> {
            //
            Enrollment withdrawalEnrollment = getState().findEnrollment(withdrawMetroCommand.getMetroId(), withdrawMetroCommand.getCitizenId());
            withdrawalEnrollment.withdraw();
            persist(new MetroWithdrawnEvent(withdrawalEnrollment), this::handleAndRespond);
        })
        .onMessage(command);
    }

    // logic goes to parent!
//    @Override
//    public void handleEvent(NaraEvent event) {
//        //
//        matcher()
//        .match(CastleBuiltEvent.class, castleBuiltEvent -> getState().apply(castleBuiltEvent))
//        .match(CastellanModifiedEvent.class, castellanModifiedEvent -> getState().apply(castellanModifiedEvent))
//        .match(CastleDemolishedEvent.class, castleDemolishedEvent -> getState().apply(castleDemolishedEvent))
//        .match(MetroEnrolledEvent.class, metroEnrolledEvent -> getState().apply(metroEnrolledEvent))
//        .match(MetroWithdrawnEvent.class, metroWithdrawnEvent -> getState().apply(metroWithdrawnEvent))
//        .onMessage(event);
//    }
}
