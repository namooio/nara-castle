package namoo.nara.castle;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.share.domain.NameValueList;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleResourceTest extends AbstractCastleApplicationTests {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    private String nationId = "P";
    private String metroId = "P0P";

    @Test
    public void test() {
        //
        getCastleRestAdapter().buildCastle(new CastleCdo(nationId, metroId, "5YC1R@P0P", "kchuh@nextree.co.kr"));
        getCastleRestAdapter().buildCastle(new CastleCdo(nationId, metroId, "5YC1S@P0P", "hkkang@nextree.co.kr"));
        getCastleRestAdapter().buildCastle(new CastleCdo(nationId, metroId, "5YC1T@P0P", "iylee@nextree.co.kr"));
        getCastleRestAdapter().buildCastle(new CastleCdo(nationId, metroId, "5YC1U@P0P", "tsong@nextree.co.kr"));

        Assert.assertEquals(4, getCastleRestAdapter().findCastlesOf(nationId).size());

        Castle castle = getCastleRestAdapter().findCastleByEmail("kchuh@nextree.co.kr");
        String castleId = castle.getId();

        Assert.assertNotNull(castle);

        logger.debug("{}", castle);

        castle = getCastleRestAdapter().findCastleByEmail("kchuh@nextree.co.kr");
        Assert.assertNotNull(castle);

        Castellan castellan = castle.getCastellan();
        castellan.addJoinedMetro("P", "Q0P", "1A@Q0P");
        getCastleRestAdapter().modifyCastle(castle.getId(), new NameValueList("castellan", castellan.toJson()));

        castle = getCastleRestAdapter().findCastle(castleId);
        Assert.assertEquals(2, castle.getCastellan().getJoinedMetroCount());

        getCastleRestAdapter().removeCastle(castleId);
        try {
            getCastleRestAdapter().findCastle(castleId);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }

        Assert.assertEquals(3, getCastleRestAdapter().findCastlesOf(nationId).size());

    }


}
