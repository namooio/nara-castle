package namoo.nara.castle;

import namoo.nara.castle.protocol.sdo.CastellanEmailSdo;
import namoo.nara.castle.protocol.sdo.CastellanSdo;
import namoo.nara.castle.protocol.sdo.CastleSdo;
import namoo.nara.castle.protocol.sdo.JoinedMetroAddSdo;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CastleResourceTest extends AbstractCastleApplicationTests {
    //
    @Test
    public void castleTest() {
        //
        Assert.assertEquals(5, getCastleRestAdapter().findCastles().size());
        CastleSdo castle = getCastleRestAdapter().findCastle(kchuhCastleId);
        Assert.assertEquals(Locale.KOREA, castle.getLocale());

        getCastleRestAdapter().modifyLocale(kchuhCastleId, Locale.US);
        castle = getCastleRestAdapter().findCastle(kchuhCastleId);
        Assert.assertEquals(Locale.US, castle.getLocale());
    }

    @Test
    public void emailTest() {
        //
        CastellanSdo castellan = getCastleRestAdapter().findCastle(kchuhCastleId).getCastellanSdo();
        Assert.assertEquals(0, castellan.getEmails().size());

        getCastleRestAdapter().addEmail(kchuhCastleId, "kchuh@nextree.co.kr");
        castellan = getCastleRestAdapter().findCastle(kchuhCastleId).getCastellanSdo();
        Assert.assertEquals(1, castellan.getEmails().size());

        List<CastellanEmailSdo> emails = castellan.getEmails();
        Iterator<CastellanEmailSdo> iterator = emails.iterator();
        while(iterator.hasNext()) {
            CastellanEmailSdo emailDto = iterator.next();
            Assert.assertEquals(false, emailDto.isVerified());
        }

        getCastleRestAdapter().verifyEmail(kchuhCastleId, "kchuh@nextree.co.kr");
        castellan = getCastleRestAdapter().findCastle(kchuhCastleId).getCastellanSdo();

        emails = castellan.getEmails();
        iterator = emails.iterator();
        while(iterator.hasNext()) {
            CastellanEmailSdo emailDto = iterator.next();
            if ("kchuh@nextree.co.kr".equals(emailDto.getAddress())) {
                Assert.assertEquals(true, emailDto.isVerified());
            }
            else {
                Assert.assertEquals(false, emailDto.isVerified());
            }
        }
    }

    @Test
    public void joinedMetroTest() {
        //
        CastellanSdo castellan = getCastleRestAdapter().findCastle(kchuhCastleId).getCastellanSdo();
        Assert.assertEquals(0, castellan.getJoinedMetros().size());
        getCastleRestAdapter().addJoinedMetro(kchuhCastleId, new JoinedMetroAddSdo("M01", "1@M01"));
        getCastleRestAdapter().addJoinedMetro(kchuhCastleId, new JoinedMetroAddSdo("M02", "1@M02"));

        castellan = getCastleRestAdapter().findCastle(kchuhCastleId).getCastellanSdo();
        Assert.assertEquals(2, castellan.getJoinedMetros().size());

        getCastleRestAdapter().removeJoinedMetro(kchuhCastleId, "M02");
        castellan = getCastleRestAdapter().findCastle(kchuhCastleId).getCastellanSdo();
        Assert.assertEquals(1, castellan.getJoinedMetros().size());
    }
}
