package namoo.nara.castle.cp.akka.actor.domain;

import akka.actor.ActorRef;
import akka.actor.Props;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castellan.RegisterCastellanCommand;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.castle.domain.spec.command.castlebook.NextSequenceCommand;
import namoo.nara.castle.domain.spec.event.castle.CastleCreated;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import namoo.nara.share.akka.support.actor.NaraPersistentActor;
import namoo.nara.share.akka.support.util.ActorNameUtil;
import namoo.nara.share.akka.support.util.AwaitableActorExecutor;
import namoo.nara.share.domain.event.NaraEvent;
import namoo.nara.share.domain.protocol.NaraCommand;
import namoo.nara.share.domain.protocol.NaraQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class CastleSupervisorActor extends NaraPersistentActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private ActorRef castleStoreActor;

    static public Props props(ActorRef castleStoreActor) {
        //
        return Props.create(CastleSupervisorActor.class, () -> new CastleSupervisorActor(castleStoreActor));
    }

    public CastleSupervisorActor(ActorRef castleStoreActor) {
        //
        super("castle-supervisor");
        this.castleStoreActor = castleStoreActor;
    }

    @Override
    public void onEventOccured(NaraEvent event) {
        //
        if (event instanceof CastleCreated) {
            handleCastleCreatedEvent((CastleCreated) event);
        }
    }

    @Override
    public void onReceiveCommand(NaraCommand command) {
        //
        if (command instanceof EnrollMetroCommand) {
            handleEnrollMetroCommand((EnrollMetroCommand) command);
        }
        else if (command instanceof ModifyCastleCommand) {
            handleModifyCastleCommand((ModifyCastleCommand) command);
        }
    }

    @Override
    public void onReceiveQuery(NaraQuery query) {
        //
        if (query instanceof FindCastleQuery) {
            handleFindCastleQuery((FindCastleQuery) query);
        }
    }

    /*********************** Command ***********************/

    private void handleEnrollMetroCommand(EnrollMetroCommand command) {
        //
        String metroId = command.getMetroId();
        String civilianId = command.getCivilianId();

        // TODO
//        Castle castle = new AwaitableActorExecutor<Castle>().execute(castleStoreActor, new FindCastleByEnrolledMetroQuery(metroId, civilianId));
        Castle castle = null;

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

        getSender().tell(castle, getSelf());
    }

    private void handleModifyCastleCommand(ModifyCastleCommand command) {
        //
        ActorRef castleActor = lookupCastleActor(command.getCastleId());
        castleActor.tell(command, getSelf());

    }
    /*********************** Command ***********************/

    /*********************** Query ***********************/

    private void handleFindCastleQuery(FindCastleQuery query) {
        //
        ActorRef castleActor = lookupCastleActor(query.getCastleId());

        Castle castle = new AwaitableActorExecutor<Castle>().execute(castleActor, query);
        getSender().tell(castle, getSelf());
    }

    /*********************** Query ***********************/

    /*********************** Event ***********************/

    private void handleCastleCreatedEvent(CastleCreated event) {
        //
        Castle castle = event.getCastle();
        ActorRef castleActor = lookupCastleActor(castle.getId());
        castleActor.tell(new RegisterCastellanCommand(castle), getSelf());
    }

    /*********************** Event ***********************/

    private ActorRef lookupOrCreateCastleBookActor() {
        //
        String castleBookId = CastleIdBuilder.makeCastleBookId();
        String name = ActorNameUtil.requestPersistentActorName(castleBookId, CastleBookActor.class);

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
        String name = ActorNameUtil.requestPersistentActorName(castleId, Castle.class);
        return getContext().findChild(name).orElse(null);
    }

    private ActorRef createCastleActor(Castle castle) {
        //
        String name = ActorNameUtil.requestPersistentActorName(castle.getId(), Castle.class);
        return getContext().actorOf(CastleActor.props(castle), name);
    }


//    private void fowardCommand(String id, Class entityClass, NaraCommand command) {
//        //
//        ActorRef entity = lookupOrCreateChild(id, entityClass);
//        entity.forward(command, context());
//    }

}
