package namoo.nara.castle.domain.spec;

import namoo.nara.castle.da.mapstore.CastleMapStoreLycler;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.event.CastleEventConfig;
import namoo.nara.castle.domain.logic.CastleEventProcessLogic;
import namoo.nara.castle.domain.logic.CastleServiceLogic;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.spec.sdo.MetroEnrollmentCdo;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.sa.CastleTestProxyLycler;
import namoo.nara.share.event.local.LocalEventQueue;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleServiceLogicTest {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private CastleService castleService;

    @Before
    public void setUp() throws Exception {
        //
        LocalEventQueue eventQueue = new LocalEventQueue();

        CastleStoreLycler storeLycler = new CastleMapStoreLycler();
        CastleProxyLycler proxyLycler = new CastleTestProxyLycler(eventQueue);

        castleService = new CastleServiceLogic(storeLycler, proxyLycler);
        CastleEventProcessLogic castleEventProcessLogic = new CastleEventProcessLogic(storeLycler, proxyLycler);

        CastleEventConfig castleEventConfig = new CastleEventConfig(eventQueue, castleEventProcessLogic);
        castleEventConfig.startRouter();
    }

    @Test
    public void test() throws InterruptedException {
        //
        MetroEnrollmentCdo metroEnrollmentCdo = MetroEnrollmentCdo.getSample();
        String castleId = castleService.addMetroEnrollment(metroEnrollmentCdo);

        // Wait event processing until castellan is created.
        while(!castleService.existsCastellan(castleId)) {
            Thread.sleep(100);
        }

        Castellan castellan = castleService.findCastellan(castleId);
        logger.debug("{}", castellan);
    }
}
