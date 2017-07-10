package namoo.nara.castle.akka.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import namoo.nara.castle.akka.projection.CastleBuiltViewProjector;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.castle.domain.spec.command.castle.BuildCastleCommand;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.castle.domain.spec.command.castlebook.NextSequenceCommand;
import namoo.nara.castle.domain.spec.event.castle.CastleBuilt;
import namoo.nara.castle.domain.spec.query.castle.FindAllCastlesQuery;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import namoo.nara.castle.domain.view.CastleView;
import namoo.nara.castle.domain.view.store.CastleViewStoreLycler;
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

    private CastleViewStoreLycler storeLycler;

    static public Props props(CastleViewStoreLycler storeLycler) {
        //
        return Props.create(CastleSupervisorActor.class, () -> new CastleSupervisorActor(storeLycler));
    }

    public CastleSupervisorActor(CastleViewStoreLycler storeLycler) {
        //
        super("castle-supervisor");
        this.storeLycler = storeLycler;

        getViewProjectorMap().put(CastleBuilt.class.getName(), new CastleBuiltViewProjector(storeLycler.requestCastleViewStore()));
    }

    @Override
    public void handleEvent(NaraEvent event) {
        //
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        if (command instanceof BuildCastleCommand) {
            handleBuildCastleCommand((BuildCastleCommand) command);
        }
        else if (command instanceof EnrollMetroCommand) {
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

    private void handleBuildCastleCommand(BuildCastleCommand command) {
        //
        String castleBookId = CastleIdBuilder.requestCastleBookId();
        ActorRef castleBookActor = lookupOrCreateChildPersistentActor(castleBookId, CastleBook.class, CastleBookActor.props(castleBookId));

        Long nextCastleSequence = new AwaitableActorExecutor<Long>().execute(castleBookActor, new NextSequenceCommand());
        String castleId = CastleIdBuilder.requestCastleId(nextCastleSequence);

        fowardCommand(castleId, Castle.class, CastleActor.props(castleId, storeLycler), command);
    }

    private void handleEnrollMetroCommand(EnrollMetroCommand command) {
        //
        String castleId = command.getCastleId();
        fowardCommand(castleId, Castle.class, CastleActor.props(castleId, storeLycler), command);
    }

    private void handleModifyCastleCommand(ModifyCastleCommand command) {
        //
        String castleId = command.getCastleId();
        fowardCommand(castleId, Castle.class, CastleActor.props(castleId, storeLycler), command);
    }

    /*********************** Command ***********************/

    /*********************** Query ***********************/

    private void handleFindCastleQuery(FindCastleQuery query) {
        //
        String castleId = query.getCastleId();
        ActorRef castleActor = lookupOrCreateChildPersistentActor(castleId, Castle.class, CastleActor.props(castleId, storeLycler));
        Castle castle = new AwaitableActorExecutor<Castle>().execute(castleActor, query);
        getSender().tell(castle, getSelf());
    }

    private void handleFindAllCastlesQuery(FindAllCastlesQuery query) {
        //
        List<CastleView> castleViews = storeLycler.requestCastleViewStore().retrieveAll();
        getSender().tell(castleViews, getSelf());
    }

    /*********************** Query ***********************/

    /*********************** Event ***********************/


    /*********************** Event ***********************/
}
