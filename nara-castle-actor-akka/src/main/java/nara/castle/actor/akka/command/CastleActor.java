package nara.castle.actor.akka.command;

import akka.actor.Props;
import nara.castle.domain.castle.command.AddEnrollmentCommand;
import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.command.ModifyCastellanCommand;
import nara.castle.domain.castle.command.WithdrawMetroCommand;
import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castle.event.CastellanModified;
import nara.castle.domain.castle.event.CastleBuilt;
import nara.castle.domain.castle.event.MetroEnrolled;
import nara.castle.domain.castle.event.MetroWithdrawn;
import nara.share.actor.akka.NaraPersistentActor;
import nara.share.domain.event.NaraEvent;
import nara.share.domain.protocol.NaraCommand;
import nara.share.domain.protocol.NaraQuery;
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
    public void handleEvent(NaraEvent event) {
        //
        matcher()
            .match(CastleBuilt.class, castleBuilt -> getState().apply(castleBuilt))
            .match(CastellanModified.class, castellanModified -> getState().apply(castellanModified))
            .match(MetroEnrolled.class, metroEnrolled -> getState().apply(metroEnrolled))
            .match(MetroWithdrawn.class, metroWithdrawn -> getState().apply(metroWithdrawn))
        .onMessage(event);
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
            .match(ModifyCastellanCommand.class, modifyCastellanCommand -> {
                //
                persist(new CastellanModified(getState().getId(), modifyCastellanCommand.getNameValues()), this::handleAndRespond);
            })
            .match(WithdrawMetroCommand.class, withdrawMetroCommand -> {
                //
                persist(new MetroWithdrawn(getState().getId(), withdrawMetroCommand.getMetroId(), withdrawMetroCommand.getCivilianId()), this::handleAndRespond);
            })
        .onMessage(command);
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        matcher()
//            .match(FindCastleQuery.class, findCastleQuery -> responseStateResult())
        .onMessage(query);
    }

}
