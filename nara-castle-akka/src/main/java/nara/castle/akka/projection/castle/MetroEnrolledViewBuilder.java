package nara.castle.akka.projection.castle;

import nara.castle.domain.castle.event.MetroEnrolled;
import nara.share.akka.support.projection.ViewBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetroEnrolledViewBuilder implements ViewBuilder<MetroEnrolled> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleViewStore castleViewStore;

    public MetroEnrolledViewBuilder(CastleViewStore castleViewStore) {
        //
        this.castleViewStore = castleViewStore;
    }

    @Override
    public void build(MetroEnrolled event) {
        //
        logger.debug("Build view for enrolled metro {}", event);

        CastleView castleView = castleViewStore.retrieve(event.getCastleId());
        castleView.getEnrollments().add(event.getEnrollment());

        this.castleViewStore.update(castleView);
    }
}
