package namoo.nara.castle.akka.projection;

import namoo.nara.castle.domain.spec.event.castle.CastleCreated;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.share.akka.support.projection.ViewProjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleViewProjector implements ViewProjector<CastleCreated> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleStore castleStore;

    public CastleViewProjector(CastleStore castleStore) {
        //
        this.castleStore = castleStore;
    }

    @Override
    public void makeProjection(CastleCreated event) {
        //
        try {
            logger.debug("make projection for castle {}", event);
            this.castleStore.create(event.getCastle());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }
}
