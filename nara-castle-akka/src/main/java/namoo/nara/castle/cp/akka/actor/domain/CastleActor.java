package namoo.nara.castle.cp.akka.actor.domain;

import akka.actor.ActorRef;
import akka.actor.Props;
import namoo.nara.castle.cp.akka.actor.share.NaraPersistentActor;
import namoo.nara.castle.cp.akka.actor.util.ActorNameUtil;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castellan.RegisterCastellanCommand;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.castle.domain.spec.command.castle.WithdrawMetroCommand;
import namoo.nara.castle.domain.spec.event.castellan.CastellanCreated;
import namoo.nara.castle.domain.spec.event.castle.CastleModified;
import namoo.nara.castle.domain.spec.event.castle.MetroEnrolled;
import namoo.nara.castle.domain.spec.event.castle.MetroWithdrawn;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleActor extends NaraPersistentActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private Castle castle;

    static public Props props(Castle castle) {
        //
        return Props.create(CastleActor.class, () -> new CastleActor(castle));
    }

    public CastleActor(Castle castle) {
        //
        super(castle.getId());
        this.castle = castle;
    }

    @Override
    public Receive createReceiveRecover() {
        //
        return receiveBuilder()
                .match(MetroEnrolled.class, this::handleMetroEnrolledEvent)
                .match(MetroWithdrawn.class, this::handleMetroWithdrawn)
                .match(CastleModified.class, this::handleCastleModifiedEvent)
                .match(CastellanCreated.class, this::handleCastellanCreatedEvent)
//                .match(SnapshotOffer.class, ss -> {
//                    logger.debug("offered state = {}", ss);
//                    Object snapshot = ss.snapshot();
//                })
                .build();
    }

    @Override
    public Receive createReceive() {
        //
        return receiveBuilder()
                // command
                .match(EnrollMetroCommand.class, this::handleEnrollMetroCommand)
                .match(WithdrawMetroCommand.class, this::handleWithdrawMetroCommand)
                .match(ModifyCastleCommand.class, this::handleModifyCastleCommand)
                .match(RegisterCastellanCommand.class, this::handleRegisterCastellanCommand)

                // query
                .match(FindCastleQuery.class, this::handleFindCastleQuery)

                .build();
    }

    /*********************** Command ***********************/

    private void handleEnrollMetroCommand(EnrollMetroCommand command) {
        //
        MetroEnrollment enrollment = new MetroEnrollment(
                command.getMetroId(),
                command.getCivilianId(),
                command.getName(),
                command.getEmail(),
                command.getZone());

        persist(new MetroEnrolled(enrollment), this::handleMetroEnrolledEvent);
    }

    private void handleWithdrawMetroCommand(WithdrawMetroCommand command) {
        //
        String metroId = command.getMetroId();
        String civilianId = command.getCivilianId();
        persist(new MetroWithdrawn(metroId, civilianId), this::handleMetroWithdrawn);
    }

    private void handleModifyCastleCommand(ModifyCastleCommand command) {
        //
        persist(new CastleModified(command), this::handleCastleModifiedEvent);
    }

    private void handleRegisterCastellanCommand(RegisterCastellanCommand command) {
        //
        Castellan castellan = new Castellan(command.getCastle());
        createCastellanActor(castellan);

        persist(new CastellanCreated(castellan), this::handleCastellanCreatedEvent);
    }

    /*********************** Command ***********************/

    /*********************** Query ***********************/

    private void handleFindCastleQuery(FindCastleQuery query) {
        //
        getSender().tell(castle, getSelf());
    }

    /*********************** Query ***********************/

    /*********************** Event ***********************/

    private void handleMetroEnrolledEvent(MetroEnrolled event) {
        //
        castle.apply(event);
    }

    private void handleMetroWithdrawn(MetroWithdrawn event) {
        //
        castle.apply(event);
    }

    private void handleCastleModifiedEvent(CastleModified event) {
        //
        castle.apply(event);
    }

    private void handleCastellanCreatedEvent(CastellanCreated event) {
        //
    }

    /*********************** Event ***********************/

    private ActorRef lookupCastellanActor(String castleId) {
        //
        String name = ActorNameUtil.getEntityActorName(castleId, Castellan.class);
        return getContext().findChild(name).orElse(null);
    }

    private ActorRef createCastellanActor(Castellan castellan) {
        //
        String name = ActorNameUtil.getEntityActorName(castellan.getId(), Castellan.class);
        return getContext().actorOf(CastellanActor.props(castellan), name);
    }
}
