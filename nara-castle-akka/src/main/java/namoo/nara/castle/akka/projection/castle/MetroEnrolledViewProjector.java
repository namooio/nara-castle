package namoo.nara.castle.akka.projection.castle;

import namoo.nara.castle.domain.spec.event.castle.MetroEnrolled;
import namoo.nara.castle.domain.view.CastleView;
import namoo.nara.castle.domain.view.store.CastleViewStore;
import namoo.nara.share.akka.support.projection.ViewProjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetroEnrolledViewProjector implements ViewProjector<MetroEnrolled> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleViewStore castleViewStore;

    public MetroEnrolledViewProjector(CastleViewStore castleViewStore) {
        //
        this.castleViewStore = castleViewStore;
    }

    @Override
    public void makeProjection(MetroEnrolled event) {
        //
        logger.debug("make projection for metro enrolled {}", event);

        CastleView castleView = castleViewStore.retrieve(event.getCastleId());
        castleView.getEnrollments().add(event.getEnrollment());

        this.castleViewStore.update(castleView);
    }
}
