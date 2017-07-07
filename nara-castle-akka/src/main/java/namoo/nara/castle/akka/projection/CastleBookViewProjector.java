package namoo.nara.castle.akka.projection;

import namoo.nara.castle.domain.spec.event.castlebook.SequenceIncreased;
import namoo.nara.share.akka.support.projection.ViewProjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleBookViewProjector implements ViewProjector<SequenceIncreased> {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void makeProjection(SequenceIncreased event) {
        //
        logger.debug("make projection for sequence increased {}", event);
    }
}
