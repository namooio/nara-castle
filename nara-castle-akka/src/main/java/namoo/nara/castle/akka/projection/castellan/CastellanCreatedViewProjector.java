package namoo.nara.castle.akka.projection.castellan;

import namoo.nara.castle.domain.spec.event.castellan.CastellanCreated;
import namoo.nara.castle.domain.view.CastellanView;
import namoo.nara.castle.domain.view.store.CastellanViewStore;
import namoo.nara.share.akka.support.projection.ViewProjector;
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

        CastellanView castellanView = new CastellanView(event.getCastellan());

        this.castellanViewStore.create(castellanView);
    }
}
