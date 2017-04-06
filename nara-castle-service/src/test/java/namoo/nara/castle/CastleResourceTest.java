package namoo.nara.castle;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.JoinedMetro;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class CastleResourceTest extends AbstractCastleApplicationTests {
    //
    @Test
    public void castleTest() {
        //
        Assert.assertEquals(5, getCastleRestAdapter().findCastles().size());
        Castle castle = getCastleRestAdapter().findCastle(kchuhCastleId);
        Assert.assertEquals(Locale.KOREA, castle.getLocale());

        getCastleRestAdapter().modifyLocale(kchuhCastleId, Locale.US);
        castle = getCastleRestAdapter().findCastle(kchuhCastleId);
        Assert.assertEquals(Locale.US, castle.getLocale());

        castle = getCastleRestAdapter().findCastleByEmail("kchuh@nextree.co.kr");
        Assert.assertNotNull(castle);

        castle = getCastleRestAdapter().findCastleByEmail("michael7557@gmail.com");
        Assert.assertNull(castle);
    }

    @Test
    public void emailTest() {
        //
        Castellan castellan = getCastleRestAdapter().findCastle(kchuhCastleId).getCastellan();
        Assert.assertEquals(1, castellan.getEmails().size());

        getCastleRestAdapter().addEmail(kchuhCastleId, "michael7557@gmail.com");
        castellan = getCastleRestAdapter().findCastle(kchuhCastleId).getCastellan();
        Assert.assertEquals(2, castellan.getEmails().size());
    }

    @Test
    public void joinedMetroTest() {
        //
        Castellan castellan = getCastleRestAdapter().findCastle(kchuhCastleId).getCastellan();
        Assert.assertEquals(1, castellan.getJoinedMetros().size());
        getCastleRestAdapter().addJoinedMetro(kchuhCastleId, new JoinedMetro("M01", "1@M01"));
        getCastleRestAdapter().addJoinedMetro(kchuhCastleId, new JoinedMetro("M02", "1@M02"));

        castellan = getCastleRestAdapter().findCastle(kchuhCastleId).getCastellan();
        Assert.assertEquals(3, castellan.getJoinedMetros().size());

        getCastleRestAdapter().removeJoinedMetro(kchuhCastleId, "M02");
        castellan = getCastleRestAdapter().findCastle(kchuhCastleId).getCastellan();
        Assert.assertEquals(2, castellan.getJoinedMetros().size());
    }
}
