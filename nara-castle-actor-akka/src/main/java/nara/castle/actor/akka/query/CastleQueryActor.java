package nara.castle.actor.akka.query;

import akka.actor.Props;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.share.actor.akka.NaraActor;
import nara.share.domain.protocol.NaraQuery;

public class CastleQueryActor extends NaraActor {
    //
    private CastleRMStoreLycler rmStoreLycler;

    static public Props props(CastleRMStoreLycler rmStoreLycler) {
        //
        return Props.create(CastleQueryActor.class, () -> new CastleQueryActor(rmStoreLycler));
    }

    public CastleQueryActor(CastleRMStoreLycler rmStoreLycler) {
        //
        this.rmStoreLycler = rmStoreLycler;
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
}
