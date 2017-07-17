package namoo.nara.castle.sp.play;

import namoo.nara.castle.domain.view.CastleView;
import namoo.nara.castle.sp.play.shared.AbstractCastleApplicationTester;
import namoo.nara.castle.sp.play.shared.TestJsonClient;
import org.junit.Test;

import java.util.List;

public class CastleResourceTest extends AbstractCastleApplicationTester {
    //
    @Test
    public void testFindCastles() throws Exception {
        //
        final String url = buildUrl("/castles");

        List<CastleView> castles = TestJsonClient.getList(url, this.testServer.port(), CastleView.class);

        for (CastleView castle : castles) {
            logger.info(castle.toString());
        }
    }

    @Test
    public void testFindCastle() throws Exception {
        //
        final String url = buildUrl("/castles/01");

        CastleView castle = TestJsonClient.getOne(url, this.testServer.port(), CastleView.class);
        logger.info(castle.toString());
    }

}
