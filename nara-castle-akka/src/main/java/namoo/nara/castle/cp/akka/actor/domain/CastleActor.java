package namoo.nara.castle.cp.akka.actor.domain;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.persistence.AbstractPersistentActor;
import namoo.nara.castle.cp.akka.actor.store.command.castle.UpdateCastleCommand;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
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
                .match(CastleModified.class, this::handleCastleModifiedEvent)
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

                // query
                .match(FindCastleQuery.class, this::handleFindCastleQuery)

                .build();
    }

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

    private void handleFindCastleQuery(FindCastleQuery query) {
        //
        getSender().tell(castle, getSelf());
    }

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

}
