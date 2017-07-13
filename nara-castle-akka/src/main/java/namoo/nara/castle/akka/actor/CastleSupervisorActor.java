package namoo.nara.castle.akka.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import namoo.nara.castle.akka.actor.persistence.CastleActor;
import namoo.nara.castle.akka.actor.persistence.CastleBookActor;
import namoo.nara.castle.domain.context.CastleIdBuilder;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castle.BuildCastleCommand;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.castle.domain.spec.command.castlebook.NextSequenceCommand;
import namoo.nara.castle.domain.spec.query.castellan.FindAllCastellansQuery;
import namoo.nara.castle.domain.spec.query.castle.FindAllCastlesQuery;
import namoo.nara.castle.domain.spec.query.castle.FindCastleQuery;
import namoo.nara.castle.domain.view.CastellanView;
import namoo.nara.castle.domain.view.CastleView;
import namoo.nara.castle.domain.view.store.CastellanViewStore;
import namoo.nara.castle.domain.view.store.CastleViewStore;
import namoo.nara.castle.domain.view.store.CastleViewStoreLycler;
import namoo.nara.share.akka.support.actor.NaraActor;
import namoo.nara.share.domain.protocol.NaraCommand;
import namoo.nara.share.domain.protocol.NaraQuery;
import namoo.nara.share.exception.NaraException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.List;

public class CastleSupervisorActor extends NaraActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleViewStoreLycler viewStoreLycler;
    private CastleViewStore castleViewStore;
    private CastellanViewStore castellanViewStore;

    static public Props props(CastleViewStoreLycler storeLycler) {
        //
        return Props.create(CastleSupervisorActor.class, () -> new CastleSupervisorActor(storeLycler));
    }

    public CastleSupervisorActor(CastleViewStoreLycler viewStoreLycler) {
        //
        this.viewStoreLycler = viewStoreLycler;
        this.castleViewStore = viewStoreLycler.requestCastleViewStore();
        this.castellanViewStore = viewStoreLycler.requestCastellanViewStore();
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
        else if (query instanceof FindAllCastellansQuery) {
            handleFindAllCastellansQuery((FindAllCastellansQuery) query);
        }
    }

    /*********************** Command ***********************/

    private void handleBuildCastleCommand(BuildCastleCommand command) {
        //
        MetroEnrollment enrollment = command.getEnrollment();

        if (castleViewStore.existsByEnrolledMetro(enrollment.getMetroId(), enrollment.getCivilianId())) {
            throw new NaraException(String.format("Castle for enrollment[%s] is already exists", command));
        }

        String castleBookId = CastleIdBuilder.requestCastleBookId();
        ActorRef castleBookActor = lookupOrCreateChildPersistentActor(castleBookId, CastleBook.class, CastleBookActor.props(castleBookId));

        Timeout timeout = new Timeout(Duration.create(1, "seconds"));
        Long nextCastleSequence;
        try {
            // FIXME Async
            nextCastleSequence = (Long) Await.result(Patterns.ask(castleBookActor, new NextSequenceCommand(), timeout), timeout.duration());
            String castleId = CastleIdBuilder.requestCastleId(nextCastleSequence);
            fowardCommand(castleId, Castle.class, CastleActor.props(castleId, viewStoreLycler), command);
        } catch (Exception e) {
            throw new NaraException(e);
        }

//        PatternsCS.ask(castleBookActor, new NextSequenceCommand(), 1000).thenAccept(response -> {
//            Long nextCastleSequence = (Long) response;
//            String castleId = CastleIdBuilder.requestCastleId(nextCastleSequence);
//            fowardCommand(castleId, Castle.class, CastleActor.props(castleId, viewStoreLycler), command);
//        });
    }

    private void handleEnrollMetroCommand(EnrollMetroCommand command) {
        //
        String castleId = command.getCastleId();
        fowardCommand(castleId, Castle.class, CastleActor.props(castleId, viewStoreLycler), command);
    }

    private void handleModifyCastleCommand(ModifyCastleCommand command) {
        //
        String castleId = command.getCastleId();
        fowardCommand(castleId, Castle.class, CastleActor.props(castleId, viewStoreLycler), command);
    }

    /*********************** Command ***********************/

    /*********************** Query ***********************/

    private void handleFindCastleQuery(FindCastleQuery query) {
        //
        String castleId = query.getCastleId();
        fowardQuery(castleId, Castle.class, CastleActor.props(castleId, viewStoreLycler), query);
    }

    // Fixme ReadModel 조회는 분리?
    private void handleFindAllCastlesQuery(FindAllCastlesQuery query) {
        //
        List<CastleView> castleViews = castleViewStore.retrieveAll();
        getSender().tell(castleViews, getSelf());
    }

    // Fixme ReadModel 조회는 분리?
    private void handleFindAllCastellansQuery(FindAllCastellansQuery query) {
        //
        List<CastellanView> castellanViews = castellanViewStore.retrieveAll();
        getSender().tell(castellanViews, getSelf());
    }

    /*********************** Query ***********************/

}
