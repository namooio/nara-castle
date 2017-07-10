package namoo.nara.castle.akka.projection;

import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.event.castellan.CastellanCreated;
import namoo.nara.castle.domain.view.CastellanView;
import namoo.nara.castle.domain.view.store.CastellanViewStore;
import namoo.nara.share.akka.support.projection.ViewProjector;
import namoo.nara.share.domain.granule.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastellanCreatedViewProjector implements ViewProjector<CastellanCreated> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastellanViewStore castellanViewStore;

    public CastellanCreatedViewProjector(CastellanViewStore castellanViewStore) {
        //
        this.castellanViewStore = castellanViewStore;
    }

    @Override
    public void makeProjection(CastellanCreated event) {
        //
        logger.debug("make projection for built castellanView {}", event);

        CastellanView castellanView = new CastellanView();
        MetroEnrollment enrollment = event.getEnrollment();
        castellanView.setId(event.getCastellanId());
        castellanView.getNames().add(enrollment.getName());
        castellanView.getEmails().add(new Email(enrollment.getEmail()));
        this.castellanViewStore.create(castellanView);
    }
}
