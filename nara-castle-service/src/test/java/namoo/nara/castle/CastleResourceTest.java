package namoo.nara.castle;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.JoinedMetro;
import namoo.nara.castle.domain.spec.sdo.CastleCdo;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.domain.granule.Email;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CastleResourceTest extends AbstractCastleApplicationTests {

    Logger logger = LoggerFactory.getLogger(getClass());

    private String nationId = "P";
    private String metroId = "P0P";

    @Test
    public void test() {

        getCastleRestAdapter().buildCastle(new CastleCdo(nationId,
                new NameValueList("castellan", new Castellan(new Email("kchuh@nextree.co.kr") , new JoinedMetro(metroId, "5YC1R@P0P")).toJson()))
        );

        getCastleRestAdapter().buildCastle(new CastleCdo(nationId,
                new NameValueList("castellan", new Castellan(new Email("hkkang@nextree.co.kr"), new JoinedMetro(metroId, "5YC1S@P0P")).toJson()))
        );

        getCastleRestAdapter().buildCastle(new CastleCdo(nationId,
                new NameValueList("castellan", new Castellan(new Email("iylee@nextree.co.kr") , new JoinedMetro(metroId, "5YC1T@P0P")).toJson()))
        );

        getCastleRestAdapter().buildCastle(new CastleCdo(nationId,
                new NameValueList("castellan", new Castellan(new Email("tsong@nextree.co.kr") , new JoinedMetro(metroId, "5YC1U@P0P")).toJson()))
        );

        Assert.assertEquals(4, getCastleRestAdapter().findCastlesOf(nationId).size());

        Castle castle = getCastleRestAdapter().findCastleByNationIdAndEmail(nationId, "kchuh@nextree.co.kr");
        String castleId = castle.getId();

        Assert.assertNotNull(castle);

        logger.debug("{}", castle);

        castle = getCastleRestAdapter().findCastleByNationIdAndEmail(nationId, "kchuh@nextree.co.kr");
        Assert.assertNotNull(castle);

        Castellan castellan = castle.getCastellan();
        castellan.addJoinedMetro("Q0P", "1A@Q0P");
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
