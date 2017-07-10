package namoo.nara.castle.akka.projection;

import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.event.castle.CastleBuilt;
import namoo.nara.castle.domain.view.CastleView;
import namoo.nara.castle.domain.view.store.CastleViewStore;
import namoo.nara.share.akka.support.projection.ViewProjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleBuiltViewProjector implements ViewProjector<CastleBuilt> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleViewStore castleViewStore;

    public CastleBuiltViewProjector(CastleViewStore castleViewStore) {
        //
        this.castleViewStore = castleViewStore;
    }

    @Override
    public void makeProjection(CastleBuilt event) {
        //
        logger.debug("make projection for built castleView {}", event);

        CastleView castleView = new CastleView();
        MetroEnrollment enrollment = event.getEnrollment();
        castleView.setId(event.getCastleId());
        castleView.setStartNationId(enrollment.getNationId());
        castleView.setName(enrollment.getName().getDisplayName());
        castleView.setPrimaryEmail(enrollment.getEmail());
        castleView.setZone(enrollment.getZone());
        castleView.setBuiltTime(event.getBuiltTime());
        castleView.getEnrollments().add(enrollment);

        this.castleViewStore.create(castleView);
    }
}
