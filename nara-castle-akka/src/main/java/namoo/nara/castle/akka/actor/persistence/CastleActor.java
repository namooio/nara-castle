package namoo.nara.castle.akka.actor.persistence;

import akka.actor.ActorRef;
import akka.actor.Props;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.command.castellan.RegisterCastellanCommand;
import namoo.nara.castle.domain.spec.command.castle.BuildCastleCommand;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.castle.domain.spec.command.castle.WithdrawMetroCommand;
import namoo.nara.castle.domain.spec.event.castle.CastleBuilt;
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

    public CastleActor(String castleId) {
        //
        super(new Castle(castleId));
    }

    @Override
    public void handleEvent(NaraEvent event) {
        //
        match()
            .with(CastleBuilt.class, this::handleCastleBuiltEvent)
            .with(CastleModified.class, castleModified -> getState().apply(castleModified))
            .with(MetroEnrolled.class, metroEnrolled -> getState().apply(metroEnrolled))
            .with(MetroWithdrawn.class, metroWithdrawn -> getState().apply(metroWithdrawn))
        .onMessage(event);
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        match()
            .with(BuildCastleCommand.class, buildCastleCommand -> {
                //
                Castle castle = new Castle(getState().getId(), buildCastleCommand.getEnrollment());
                persist(new CastleBuilt(castle), this::handleEventAndRespond);
            })
            .with(EnrollMetroCommand.class, enrollMetroCommand -> {
                //
                persist(new MetroEnrolled(getState().getId(), enrollMetroCommand.getEnrollment()), this::handleEventAndRespond);
            })
            .with(WithdrawMetroCommand.class, withdrawMetroCommand -> {
                //
                persist(new MetroWithdrawn(withdrawMetroCommand.getMetroId(), withdrawMetroCommand.getCivilianId()), this::handleEventAndRespond);
            })
            .with(ModifyCastleCommand.class, modifyCastleCommand -> {
                //
                persist(new CastleModified(modifyCastleCommand), this::handleEventAndRespond);
            })
        .onMessage(command);
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        match()
            .with(FindCastleQuery.class, findCastleQuery -> responseStateResult())
        .onMessage(query);
    }

    private void handleCastleBuiltEvent(CastleBuilt event) {
        //
        getState().apply(event);
        String castleId = getState().getId();

        ActorRef castellanActor = lookupOrCreateChild(castleId, Castellan.class, CastellanActor.props(castleId));
        NaraCommand command = new RegisterCastellanCommand(event.getCastle());
        castellanActor.tell(command, getSelf());
    }

}
