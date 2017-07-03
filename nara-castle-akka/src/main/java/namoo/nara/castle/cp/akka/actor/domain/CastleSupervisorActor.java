package namoo.nara.castle.cp.akka.actor.domain;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.persistence.AbstractPersistentActor;
import namoo.nara.castle.cp.akka.actor.store.command.CreateCastleCommand;
import namoo.nara.castle.cp.akka.actor.store.query.FindCastleByEnrolledMetroQuery;
import namoo.nara.castle.cp.akka.actor.util.ActorNameUtil;
import namoo.nara.castle.cp.akka.actor.util.AwaitableActorExecutor;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.castle.domain.spec.command.castlebook.NextSequenceCommand;
import namoo.nara.castle.domain.spec.event.castle.CastleCreated;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class CastleSupervisorActor extends AbstractPersistentActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private ActorRef castleStoreActor;

    static public Props props(ActorRef castleStoreActor) {
        //
        return Props.create(CastleSupervisorActor.class, () -> new CastleSupervisorActor(castleStoreActor));
    }

    public CastleSupervisorActor(ActorRef castleStoreActor) {
        //
        this.castleStoreActor = castleStoreActor;
    }

    @Override
    public String persistenceId() {
        //
        return "castle-supervisor";
    }

    @Override
    public Receive createReceiveRecover() {
        //
        return receiveBuilder()
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
        String metroId = command.getMetroId();
        String civilianId = command.getCivilianId();

        Castle castle = new AwaitableActorExecutor<Castle>().execute(castleStoreActor, new FindCastleByEnrolledMetroQuery(metroId, civilianId));

        if (castle != null) {
            ActorRef castleActor = lookupCastleActor(castle.getId());
            castleActor.tell(command, getSelf());
        }
        else {
            ActorRef castleBookActor = lookupOrCreateCastleBookActor();

            Long nextCastleSequence = new AwaitableActorExecutor<Long>().execute(castleBookActor, new NextSequenceCommand());
            String castleId = CastleIdBuilder.makeCastleId(nextCastleSequence);

            MetroEnrollment enrollment = new MetroEnrollment(
                    command.getMetroId(),
                    command.getCivilianId(),
                    command.getName(),
                    command.getEmail(),
                    command.getZone());

            castle = new Castle(castleId, enrollment);
            createCastleActor(castle);

            persist(new CastleCreated(castle), this::handleCastleCreatedEvent);
        }
    }

    private void handleModifyCastleCommand(ModifyCastleCommand command) {
        //
        ActorRef castleActor = lookupCastleActor(command.getCastleId());
        castleActor.tell(command, getSelf());
    }

    private void handleFindCastleQuery(FindCastleQuery query) {
        //
        ActorRef castleActor = lookupCastleActor(query.getCastleId());

        Castle castle = new AwaitableActorExecutor<Castle>().execute(castleActor, query);
        getSender().tell(castle, getSelf());
    }

    private void handleCastleCreatedEvent(CastleCreated event) {
        //
        castleStoreActor.tell(new CreateCastleCommand(event.getCastle()), getSelf());
    }

    private ActorRef lookupOrCreateCastleBookActor() {
        //
        String castleBookId = CastleIdBuilder.makeCastleBookId();
        String name = ActorNameUtil.getEntityActorName(castleBookId, CastleBookActor.class);

        Optional<ActorRef> child = getContext().findChild(name);
        if (child.isPresent()) {
            return child.get();
        }
        else {
            return getContext().actorOf(CastleBookActor.props(), name);
        }
    }

    private ActorRef lookupCastleActor(String castleId) {
        //
        String name = ActorNameUtil.getEntityActorName(castleId, Castle.class);
        return getContext().findChild(name).orElse(null);
    }

    private ActorRef createCastleActor(Castle castle) {
        //
        String name = ActorNameUtil.getEntityActorName(castle.getId(), Castle.class);
        return getContext().actorOf(CastleActor.props(castle, castleStoreActor), name);
    }

//    private void fowardCommand(String id, Class entityClass, NaraCommand command) {
//        //
//        ActorRef entity = lookupOrCreateChild(id, entityClass);
//        entity.forward(command, context());
//    }

}
