package namoo.nara.castle.akka.projection;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.event.castle.CastleBuilt;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.share.akka.support.projection.ViewProjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleBuiltViewProjector implements ViewProjector<CastleBuilt> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleStore castleStore;

    public CastleBuiltViewProjector(CastleStore castleStore) {
        //
        this.castleStore = castleStore;
    }

    @Override
    public void makeProjection(CastleBuilt event) {
        //
        try {
            logger.debug("make projection for built castle {}", event);
            Castle castle = new Castle(event.getCastleId());
            castle.apply(event);
            this.castleStore.create(castle);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }
}
