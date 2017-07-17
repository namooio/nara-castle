package namoo.nara.castle.akka.actor.persistence;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.PatternsCS;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
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
            .with(MetroEnrolled.class, this::handleMetroEnrolledEvent)
            .with(MetroWithdrawn.class, this::handleMetroWithdrawnEvent)
            .with(CastleModified.class, this::handleCastleModifiedEvent)
        .exec(event);
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        match()
            .with(BuildCastleCommand.class, this::handleBuildCastleCommand)
            .with(EnrollMetroCommand.class, this::handleEnrollMetroCommand)
            .with(WithdrawMetroCommand.class, this::handleWithdrawMetroCommand)
            .with(ModifyCastleCommand.class, this::handleModifyCastleCommand)
        .exec(command);
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        match()
            .with(FindCastleQuery.class, this:: handleFindCastleQuery)
        .exec(query);
    }

    /*********************** Command ***********************/

    private void handleBuildCastleCommand(BuildCastleCommand command) {
        //
        String castleId = getState().getId();
        MetroEnrollment enrollment = command.getEnrollment();
        Castle castle = new Castle(castleId, enrollment);

        persistAndHandle(new CastleBuilt(castle));
    }

    private void handleEnrollMetroCommand(EnrollMetroCommand command) {
        //
        persistAndHandle(new MetroEnrolled(getState().getId(), command.getEnrollment()));
    }

    private void handleWithdrawMetroCommand(WithdrawMetroCommand command) {
        //
        String metroId = command.getMetroId();
        String civilianId = command.getCivilianId();
        persistAndHandle(new MetroWithdrawn(metroId, civilianId));
    }

    private void handleModifyCastleCommand(ModifyCastleCommand command) {
        //
        persistAndHandle(new CastleModified(command));
    }

    /*********************** Command ***********************/

    /*********************** Query ***********************/

    private void handleFindCastleQuery(FindCastleQuery query) {
        //
        getSender().tell(getState(), getSelf());
    }

    /*********************** Query ***********************/

    /*********************** Event ***********************/

    private void handleCastleBuiltEvent(CastleBuilt event) {
        //
        getState().apply(event);
        String castleId = getState().getId();

        ActorRef castellanActor = lookupOrCreateChild(castleId, Castellan.class, CastellanActor.props(castleId));
        ActorRef sender = getSender();

        NaraCommand command = new RegisterCastellanCommand(event.getCastle());
        PatternsCS.ask(castellanActor, command, 1000).thenRun(() -> {
            sender.tell(castleId, getSelf());
        });
    }

    private void handleMetroEnrolledEvent(MetroEnrolled event) {
        //
        getState().apply(event);
        getSender().tell("todo", getSelf());
    }

    private void handleMetroWithdrawnEvent(MetroWithdrawn event) {
        //
        getState().apply(event);
        getSender().tell("todo", getSelf());
    }

    private void handleCastleModifiedEvent(CastleModified event) {
        //
        getState().apply(event);
        getSender().tell("todo", getSelf());
    }

    /*********************** Event ***********************/
}
