package nara.castle.actor.akka;

import akka.actor.Props;
import nara.castle.actor.akka.command.CastleActor;
import nara.castle.domain.castle.command.AddEnrollmentCommand;
import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.command.ModifyCastellanCommand;
import nara.castle.domain.castle.command.WithdrawMetroCommand;
import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castlequery.store.CastleViewStoreLycler;
import nara.share.actor.akka.NaraActor;
import nara.share.domain.protocol.NaraCommand;
import nara.share.domain.protocol.NaraQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleSupervisorActor extends NaraActor {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

//    private CastleViewStore castleViewStore;
//    private CastellanViewStore castellanViewStore;

    static public Props props(CastleViewStoreLycler storeLycler) {
        //
        return Props.create(CastleSupervisorActor.class, () -> new CastleSupervisorActor(storeLycler));
    }

    public CastleSupervisorActor(CastleViewStoreLycler viewStoreLycler) {
        //
//        this.castleViewStore = viewStoreLycler.requestCastleViewStore();
//        this.castellanViewStore = viewStoreLycler.requestCastellanViewStore();
    }

    @Override
    public void handleCommand(NaraCommand command) {
        //
        matcher()
            .match(BuildCastleCommand.class, buildCastleCommand -> {
                //
                Castellan castellan = new Castellan(buildCastleCommand.getEnrollment());
                foward(castellan.getId(), Castellan.class, CastleActor.props(castellan), buildCastleCommand);
            })
            .match(ModifyCastellanCommand.class, modifyCastellanCommand -> {
                //
                String castellanId = modifyCastellanCommand.getCastellanId();
                foward(castellanId, Castellan.class, CastleActor.props(castellanId), modifyCastellanCommand);
            })
            .match(AddEnrollmentCommand.class, addEnrollmentCommand -> {
                //
                String castellanId = addEnrollmentCommand.getCastellanId();
                foward(castellanId, Castellan.class, CastleActor.props(castellanId), addEnrollmentCommand);
            })
            .match(WithdrawMetroCommand.class, withdrawMetroCommand -> {
                //
                String castleId = withdrawMetroCommand.getCastellanId();
                foward(castleId, Castellan.class, CastleActor.props(castleId), withdrawMetroCommand);
            })
        .onMessage(command);
    }

    @Override
    public void handleQuery(NaraQuery query) {
        //
//        matcher()
//            .match(FindCastleQuery.class, findCastleQuery -> {
//                //
//                String castleId = findCastleQuery.getCastleId();
//                foward(castleId, Castle.class, CastleActor.props(castleId), query);
//            })
//            .match(FindAllCastlesQuery.class, findAllCastlesQuery -> {
//                // Fixme ReadModel 조회는 분리?
//                List<CastleView> castleViews = castleViewStore.retrieveAll();
//                responseResult(castleViews);
//            })
//            .match(FindAllCastellansQuery.class, findAllCastellansQuery -> {
//                // Fixme ReadModel 조회는 분리?
//                List<CastellanView> castellanViews = castellanViewStore.retrieveAll();
//                responseResult(castellanViews);
//            })
//        .onMessage(query);
    }

    private void handleBuildCastleCommand(BuildCastleCommand command) {
        //
    }

}
