package nara.castle.akka.projection.castellan;

import nara.castle.domain.spec.event.castellan.CastellanCreated;
import nara.castle.domain.view.CastellanView;
import nara.castle.domain.view.store.CastellanViewStore;
import nara.share.akka.support.projection.ViewBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastellanViewBuilder implements ViewBuilder<CastellanCreated> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastellanViewStore castellanViewStore;

    public CastellanViewBuilder(CastellanViewStore castellanViewStore) {
        //
        this.castellanViewStore = castellanViewStore;
    }

    @Override
    public void build(CastellanCreated event) {
        //
        logger.debug("Build castellan view {}", event);

        CastellanView castellanView = new CastellanView(event.getCastellan());
        this.castellanViewStore.create(castellanView);
    }
}
