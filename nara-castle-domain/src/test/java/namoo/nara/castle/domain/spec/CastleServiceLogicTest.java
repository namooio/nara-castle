package namoo.nara.castle.domain.spec;

import namoo.nara.castle.da.mapstore.CastleMapStoreLycler;
import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.logic.CastleServiceLogic;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.sa.CastleTestProxyLycler;
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
        CastleProxyLycler proxyLycler = new CastleTestProxyLycler();

        castleService = new CastleServiceLogic(storeLycler, proxyLycler);

        // Context initialize
        CastleContext.newInstance(storeLycler, proxyLycler);
    }

    @Test
    public void enrollMetroTest() throws InterruptedException {
        //
        EnrollMetroCommand metroEnrollmentCdo = EnrollMetroCommand.getSample();
        String castleId = castleService.enrollMetro(metroEnrollmentCdo);

        // Wait event processing until castellan is created.
        while(!castleService.existsCastellan(castleId)) {
            Thread.sleep(100);
        }

        Castellan castellan = castleService.findCastellan(castleId);
        logger.debug("{}", castellan);
    }
}
