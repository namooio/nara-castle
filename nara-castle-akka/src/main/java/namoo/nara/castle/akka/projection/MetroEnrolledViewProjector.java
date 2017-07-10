package namoo.nara.castle.akka.projection;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.event.castle.MetroEnrolled;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.share.akka.support.projection.ViewProjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetroEnrolledViewProjector implements ViewProjector<MetroEnrolled> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleStore castleStore;

    public MetroEnrolledViewProjector(CastleStore castleStore) {
        //
        this.castleStore = castleStore;
    }

    @Override
    public void makeProjection(MetroEnrolled event) {
        //
        try {
            logger.debug("make projection for metro enrolled {}", event);
            Castle castle = this.castleStore.retrieve(event.getCastleId());
            castle.apply(event);
            this.castleStore.update(castle);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }
}
