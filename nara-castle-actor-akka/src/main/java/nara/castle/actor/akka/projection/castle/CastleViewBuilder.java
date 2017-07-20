//package nara.castle.actor.akka.projection.castle;
//
//import nara.castle.domain.castle.event.CastleBuilt;
//import nara.castle.domain.castlequery.model.CastleView;
//import nara.castle.domain.castlequery.store.CastleViewStore;
//import nara.share.actor.akka.projection.ViewBuilder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class CastleViewBuilder implements ViewBuilder<CastleBuilt> {
//    //
//    Logger logger = LoggerFactory.getLogger(getClass());
//
//    private CastleViewStore castleViewStore;
//
//    public CastleViewBuilder(CastleViewStore castleViewStore) {
//        //
//        this.castleViewStore = castleViewStore;
//    }
//
//    @Override
//    public void build(CastleBuilt event) {
//        //
//        logger.debug("Build castle view {}", event);
//
//        CastleView castleView = new CastleView(event.getCastle());
//        this.castleViewStore.create(castleView);
//    }
//}
