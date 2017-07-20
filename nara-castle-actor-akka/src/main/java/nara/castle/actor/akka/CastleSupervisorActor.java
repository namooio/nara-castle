package nara.castle.actor.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.Patterns;
import nara.castle.actor.akka.command.CastleActor;
import nara.castle.actor.akka.command.CastleBookActor;
import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.command.EnrollMetroCommand;
import nara.castle.domain.castle.command.ModifyCastleCommand;
import nara.castle.domain.castle.entity.Castle;
import nara.castle.domain.castlebook.command.NextSequenceCommand;
import nara.castle.domain.castlebook.entity.CastleBook;
import nara.castle.domain.castlebook.entity.CastleIdBuilder;
import nara.castle.domain.castlequery.model.CastellanView;
import nara.castle.domain.castlequery.model.CastleView;
import nara.castle.domain.castlequery.query.FindAllCastellansQuery;
import nara.castle.domain.castlequery.query.FindAllCastlesQuery;
import nara.castle.domain.castlequery.query.FindCastleQuery;
import nara.castle.domain.castlequery.store.CastellanViewStore;
import nara.castle.domain.castlequery.store.CastleViewStore;
import nara.castle.domain.castlequery.store.CastleViewStoreLycler;
import nara.share.actor.akka.NaraActor;
import nara.share.actor.akka.NaraActorConst;
import nara.share.actor.akka.result.ActorResult;
import nara.share.domain.protocol.NaraCommand;
import nara.share.domain.protocol.NaraQuery;
import nara.share.exception.NaraException;
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
        String castleBookId = CastleIdBuilder.requestCastleBookId();
        ActorRef castleBookActor = lookupOrCreateChild(castleBookId, CastleBook.class, CastleBookActor.props(castleBookId));

        CastleBook castleBook;
        try {
            ActorResult<CastleBook> result = (ActorResult) Await.result(Patterns.ask(castleBookActor, new NextSequenceCommand(), NaraActorConst.DEFAULT_TIMEOUT), NaraActorConst.DEFAULT_TIMEOUT.duration());
            castleBook = result.get();
            String castleId = CastleIdBuilder.requestCastleId(castleBook.getSequence());

            foward(castleId, Castle.class, CastleActor.props(castleId), command);
        } catch (Exception e) {
            // FIXME fail respond!!
            throw new NaraException(e);
        }
    }

}
