package namoo.nara.castle.akka.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.Patterns;
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
import namoo.nara.share.akka.support.actor.result.ActorResult;
import namoo.nara.share.domain.protocol.NaraCommand;
import namoo.nara.share.domain.protocol.NaraQuery;
import namoo.nara.share.exception.NaraException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.Await;

import java.util.List;

public class CastleSupervisorActor extends NaraActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleViewStore castleViewStore;
    private CastellanViewStore castellanViewStore;

    static public Props props(CastleViewStoreLycler storeLycler) {
        //
        return Props.create(CastleSupervisorActor.class, () -> new CastleSupervisorActor(storeLycler));
    }

    public CastleSupervisorActor(CastleViewStoreLycler viewStoreLycler) {
        //
        this.castleViewStore = viewStoreLycler.requestCastleViewStore();
        this.castellanViewStore = viewStoreLycler.requestCastellanViewStore();
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        matcher()
            .match(BuildCastleCommand.class, this::handleBuildCastleCommand)
            .match(ModifyCastleCommand.class, modifyCastleCommand -> {
                //
                String castleId = modifyCastleCommand.getCastleId();
                foward(castleId, Castle.class, CastleActor.props(castleId), modifyCastleCommand);
            })
            .match(EnrollMetroCommand.class, enrollMetroCommand -> {
                //
                String castleId = enrollMetroCommand.getCastleId();
                foward(castleId, Castle.class, CastleActor.props(castleId), enrollMetroCommand);
            })
        .onMessage(command);
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
        matcher()
            .match(FindCastleQuery.class, findCastleQuery -> {
                //
                String castleId = findCastleQuery.getCastleId();
                foward(castleId, Castle.class, CastleActor.props(castleId), query);
            })
            .match(FindAllCastlesQuery.class, findAllCastlesQuery -> {
                // Fixme ReadModel 조회는 분리?
                List<CastleView> castleViews = castleViewStore.retrieveAll();
                responseResult(castleViews);
            })
            .match(FindAllCastellansQuery.class, findAllCastellansQuery -> {
                // Fixme ReadModel 조회는 분리?
                List<CastellanView> castellanViews = castellanViewStore.retrieveAll();
                responseResult(castellanViews);
            })
        .onMessage(query);
    }

    private void handleBuildCastleCommand(BuildCastleCommand command) {
        //
        MetroEnrollment enrollment = command.getEnrollment();

        if (castleViewStore.existsByEnrolledMetro(enrollment.getMetroId(), enrollment.getCivilianId())) {
            throw new NaraException(String.format("Castle for enrollment[%s] is already exists", command));
        }

        String castleBookId = CastleIdBuilder.requestCastleBookId();
        ActorRef castleBookActor = lookupOrCreateChild(castleBookId, CastleBook.class, CastleBookActor.props(castleBookId));

        CastleBook castleBook;
        try {
            ActorResult<CastleBook> result = (ActorResult) Await.result(Patterns.ask(castleBookActor, new NextSequenceCommand(), getDefaultTimeOut()), getDefaultTimeOut().duration());
            castleBook = result.get();
            String castleId = CastleIdBuilder.requestCastleId(castleBook.getSequence());
            foward(castleId, Castle.class, CastleActor.props(castleId), command);
        } catch (Exception e) {
            throw new NaraException(e);
        }
    }

}
