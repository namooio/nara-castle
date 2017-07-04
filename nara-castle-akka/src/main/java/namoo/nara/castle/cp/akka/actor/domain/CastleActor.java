package namoo.nara.castle.cp.akka.actor.domain;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.persistence.AbstractPersistentActor;
import namoo.nara.castle.cp.akka.actor.store.command.castellan.CreateCastellanCommand;
import namoo.nara.castle.cp.akka.actor.store.command.castle.UpdateCastleCommand;
import namoo.nara.castle.cp.akka.actor.util.ActorNameUtil;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castellan.RegisterCastellanCommand;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.castle.domain.spec.event.castellan.CastellanCreated;
import namoo.nara.castle.domain.spec.event.castle.CastleModified;
import namoo.nara.castle.domain.spec.event.castle.MetroEnrolled;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleActor extends AbstractPersistentActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private Castle castle;

    private ActorRef castleStoreActor;
    private ActorRef castellanStoreActor;

    static public Props props(Castle castle, ActorRef castleStoreActor) {
        //
        return Props.create(CastleActor.class, () -> new CastleActor(castle, castleStoreActor));
    }

    public CastleActor(Castle castle, ActorRef castleStoreActor) {
        //
        this.castle = castle;
        this.castleStoreActor = castleStoreActor;
    }

    @Override
    public String persistenceId() {
        //
        return castle.getId();
    }

    @Override
    public Receive createReceiveRecover() {
        //
        return receiveBuilder()
                .match(MetroEnrolled.class, this::handleMetroEnrolledEvent)
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
        castleStoreActor.tell(new UpdateCastleCommand(castle), getSelf());
    }

    private void handleCastleModifiedEvent(CastleModified event) {
        //
        castle.apply(event);
        castleStoreActor.tell(new UpdateCastleCommand(castle), getSelf());
    }

    private void handleCastellanCreatedEvent(CastellanCreated event) {
        //
        castellanStoreActor.tell(new CreateCastellanCommand(event.getCastellan()), getSelf());
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
        return getContext().actorOf(CastellanActor.props(castellan, castleStoreActor), name);
    }
}
