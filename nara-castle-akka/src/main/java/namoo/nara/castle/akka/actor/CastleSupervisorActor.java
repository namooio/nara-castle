package namoo.nara.castle.akka.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import namoo.nara.castle.akka.projection.CastleCreatedViewProjector;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.castle.domain.spec.command.castellan.RegisterCastellanCommand;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.castle.domain.spec.command.castlebook.NextSequenceCommand;
import namoo.nara.castle.domain.spec.event.castle.CastleCreated;
import namoo.nara.castle.domain.spec.query.castle.FindAllCastlesQuery;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.share.akka.support.actor.NaraPersistentActor;
import namoo.nara.share.akka.support.util.AwaitableActorExecutor;
import namoo.nara.share.domain.event.NaraEvent;
import namoo.nara.share.domain.protocol.NaraCommand;
import namoo.nara.share.domain.protocol.NaraQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CastleSupervisorActor extends NaraPersistentActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleStore castleStore;

    static public Props props(CastleStore castleStore) {
        //
        return Props.create(CastleSupervisorActor.class, () -> new CastleSupervisorActor(castleStore));
    }

    public CastleSupervisorActor(CastleStore castleStore) {
        //
        super("castle-supervisor");
        this.castleStore = castleStore;

        getViewProjectorMap().put(CastleCreated.class.getName(), new CastleCreatedViewProjector(castleStore));
    }

    @Override
    public void handleEvent(NaraEvent event) {
        //
        if (event instanceof CastleCreated) {
            handleCastleCreatedEvent((CastleCreated) event);
        }
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        if (command instanceof EnrollMetroCommand) {
            handleEnrollMetroCommand((EnrollMetroCommand) command);
        }
        else if (command instanceof ModifyCastleCommand) {
            handleModifyCastleCommand((ModifyCastleCommand) command);
        }
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        if (query instanceof FindCastleQuery) {
            handleFindCastleQuery((FindCastleQuery) query);
        }
        else if (query instanceof FindAllCastlesQuery) {
            handleFindAllCastlesQuery((FindAllCastlesQuery) query);
        }
    }

    /*********************** Command ***********************/

    private void handleEnrollMetroCommand(EnrollMetroCommand command) {
        //
        String castleBookId = CastleIdBuilder.requestCastleBookId();
        ActorRef castleBookActor = lookupOrCreateChildPersistentActor(castleBookId, CastleBook.class, CastleBookActor.props());

        Long nextCastleSequence = new AwaitableActorExecutor<Long>().execute(castleBookActor, new NextSequenceCommand());
        String castleId = CastleIdBuilder.requestCastleId(nextCastleSequence);

        Castle castle = new Castle(castleId);
        ActorRef castleActor = lookupOrCreateChildPersistentActor(castleId, Castle.class, CastleActor.props(castle, castleStore));
        castleActor.tell(command, getSelf());

        persist(new CastleCreated(castle), this::handleCastleCreatedEvent);
    }

    private void handleModifyCastleCommand(ModifyCastleCommand command) {
        //
        String castleId = command.getCastleId();
        ActorRef castleActor = lookupOrCreateChildPersistentActor(castleId, Castle.class, CastleActor.props(castleId, castleStore));
        castleActor.tell(command, getSelf());

    }
    /*********************** Command ***********************/

    /*********************** Query ***********************/

    private void handleFindCastleQuery(FindCastleQuery query) {
        //
        String castleId = query.getCastleId();
        ActorRef castleActor = lookupOrCreateChildPersistentActor(castleId, Castle.class, CastleActor.props(castleId, castleStore));
        Castle castle = new AwaitableActorExecutor<Castle>().execute(castleActor, query);
        getSender().tell(castle, getSelf());
    }

    private void handleFindAllCastlesQuery(FindAllCastlesQuery query) {
        //
        List<Castle> castles = castleStore.retrieveAll();
        getSender().tell(castles, getSelf());
    }

    /*********************** Query ***********************/

    /*********************** Event ***********************/

    private void handleCastleCreatedEvent(CastleCreated event) {
        //
        String castleId = event.getCastle().getId();

        ActorRef castleActor = lookupOrCreateChildPersistentActor(castleId, Castle.class, CastleActor.props(castleId, castleStore));
        castleActor.tell(new RegisterCastellanCommand(castleId), getSelf());

        getSender().tell(castleId, getSelf());
    }

    /*********************** Event ***********************/

}
