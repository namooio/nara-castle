package nara.castle.akka.projection.castle;

import nara.castle.domain.spec.event.castle.CastleBuilt;
import nara.castle.domain.view.CastleView;
import nara.castle.domain.view.store.CastleViewStore;
import nara.share.akka.support.projection.ViewBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleViewBuilder implements ViewBuilder<CastleBuilt> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleViewStore castleViewStore;

    public CastleViewBuilder(CastleViewStore castleViewStore) {
        //
        this.castleViewStore = castleViewStore;
    }

    @Override
    public void build(CastleBuilt event) {
        //
        logger.debug("Build castle view {}", event);

        CastleView castleView = new CastleView(event.getCastle());
        this.castleViewStore.create(castleView);
    }
}
