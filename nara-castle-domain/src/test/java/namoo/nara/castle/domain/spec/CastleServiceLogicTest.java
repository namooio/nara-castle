package namoo.nara.castle.domain.spec;

import namoo.nara.castle.da.mapstore.CastleMapStoreLycler;
import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.logic.CastleServiceLogic;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.spec.sdo.MetroEnrollmentCdo;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.sa.CastleMockProxyLycler;
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
        CastleStoreLycler storeLycler = new CastleMapStoreLycler();
        CastleProxyLycler proxyLycler = new CastleMockProxyLycler();

        // Context initialize
        CastleContext.newInstance(storeLycler, proxyLycler);
        CastleContext.getInstance().startEventService();

        castleService = new CastleServiceLogic();
    }

    @Test
    public void enrollMetroTest() throws InterruptedException {
        //
        MetroEnrollmentCdo metroEnrollmentCdo = MetroEnrollmentCdo.getSample();
        String castleId = castleService.enrollMetro(metroEnrollmentCdo);

        // Wait event processing until castellan is created.
        while(!castleService.existsCastellan(castleId)) {
            Thread.sleep(100);
        }

        Castellan castellan = castleService.findCastellan(castleId);
        logger.debug("{}", castellan);
    }
}
