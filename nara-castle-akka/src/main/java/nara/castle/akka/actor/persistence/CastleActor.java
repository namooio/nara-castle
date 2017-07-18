package nara.castle.akka.actor.persistence;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.PatternsCS;
import nara.share.akka.support.actor.NaraActorConst;
import nara.castle.domain.entity.Castellan;
import nara.castle.domain.entity.Castle;
import nara.castle.domain.spec.command.castellan.RegisterCastellanCommand;
import nara.castle.domain.spec.command.castle.BuildCastleCommand;
import nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import nara.castle.domain.spec.command.castle.WithdrawMetroCommand;
import nara.castle.domain.spec.event.castle.CastleBuilt;
import nara.castle.domain.spec.event.castle.CastleModified;
import nara.castle.domain.spec.event.castle.MetroEnrolled;
import nara.castle.domain.spec.event.castle.MetroWithdrawn;
import nara.castle.domain.spec.query.castle.FindCastleQuery;
import nara.share.akka.support.actor.NaraPersistentActor;
import nara.share.akka.support.actor.result.ActorResult;
import nara.share.domain.event.NaraEvent;
import nara.share.domain.protocol.NaraCommand;
import nara.share.domain.protocol.NaraQuery;
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
        matcher()
            .match(CastleBuilt.class, this::handleCastleBuiltEvent)
            .match(CastleModified.class, castleModified -> getState().apply(castleModified))
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
                Castle castle = new Castle(getState().getId(), buildCastleCommand.getEnrollment());
                persist(new CastleBuilt(castle), this::handleAndRespond);
            })
            .match(EnrollMetroCommand.class, enrollMetroCommand -> {
                //
                persist(new MetroEnrolled(getState().getId(), enrollMetroCommand.getEnrollment()), this::handleAndRespond);
            })
            .match(WithdrawMetroCommand.class, withdrawMetroCommand -> {
                //
                persist(new MetroWithdrawn(withdrawMetroCommand.getMetroId(), withdrawMetroCommand.getCivilianId()), this::handleAndRespond);
            })
            .match(ModifyCastleCommand.class, modifyCastleCommand -> {
                //
                persist(new CastleModified(modifyCastleCommand), this::handleAndRespond);
            })
        .onMessage(command);
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        matcher()
            .match(FindCastleQuery.class, findCastleQuery -> responseStateResult())
        .onMessage(query);
    }

    private void handleCastleBuiltEvent(CastleBuilt event) {
        //
        getState().apply(event);
        String castleId = getState().getId();

        ActorRef castellanActor = lookupOrCreateChild(castleId, Castellan.class, CastellanActor.props(castleId));
        NaraCommand command = new RegisterCastellanCommand(event.getCastle());
        PatternsCS.ask(castellanActor, command, NaraActorConst.DEFAULT_TIMEOUT).thenAccept(response -> {
            //
            ActorResult<Castellan> result = (ActorResult) response;
            if (!result.isSuccess()) {
                // TODO
            }
        });
    }

}
