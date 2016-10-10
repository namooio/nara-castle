package namoo.nara.castle;

import namoo.nara.castle.protocol.sdo.CastellanEmailSdo;
import namoo.nara.castle.protocol.sdo.CastellanFindSdo;
import namoo.nara.castle.protocol.sdo.JoinedMetroSdo;
import namoo.nara.castle.protocol.sdo.LoginAccountSdo;
import namoo.nara.castle.adapter.CastleConstant;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class CastellanResourceTest extends AbstractCastleApplicationTests {
    //
    @Test
    public void basicInfoTest() {
        //
        getCastellanRestAdapter().removeCastellan(kchuhCastleId);
        try {
            getCastellanRestAdapter().findCastellan(kchuhCastleId);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void accountTest() {
        //
        CastellanFindSdo castellan = getCastellanRestAdapter().findCastellan(kchuhCastleId);
        Assert.assertEquals(0, castellan.getAccounts().size());

        getCastellanRestAdapter().modifyPassword(kchuhCastleId, "4321");
        castellan = getCastellanRestAdapter().findCastellan(kchuhCastleId);
        Assert.assertEquals("4321", castellan.getCredential().getPassword());

        getCastellanRestAdapter().addAccount(kchuhCastleId, new LoginAccountSdo("kchuh", CastleConstant.LOGIN_ID_TYPE_USERNAME));

        castellan = getCastellanRestAdapter().findCastellan(kchuhCastleId);
        Assert.assertEquals(1, castellan.getAccounts().size());

        castellan = getCastellanRestAdapter().findCastellan("kchuh", CastleConstant.LOGIN_ID_TYPE_USERNAME);
        Assert.assertEquals(1, castellan.getAccounts().size());

        castellan = getCastellanRestAdapter().findCastellan("kchuh@nextree.co.kr", CastleConstant.LOGIN_ID_TYPE_EMAIL);
        Assert.assertNull(castellan);

        getCastellanRestAdapter().verifyEmail(kchuhCastleId, "kchuh@nextree.co.kr");
        castellan = getCastellanRestAdapter().findCastellan("kchuh@nextree.co.kr", CastleConstant.LOGIN_ID_TYPE_EMAIL);
        Assert.assertNotNull(castellan);

        castellan = getCastellanRestAdapter().findCastellan("kchuh", CastleConstant.LOGIN_ID_TYPE_EMAIL);
        Assert.assertNull(castellan);

        getCastellanRestAdapter().removeAccount(kchuhCastleId, new LoginAccountSdo("kchuh", CastleConstant.LOGIN_ID_TYPE_USERNAME));
        castellan = getCastellanRestAdapter().findCastellan(kchuhCastleId);
        Assert.assertEquals(1, castellan.getAccounts().size());
    }

    @Test
    public void emailTest() {
        //
        CastellanFindSdo castellan = getCastellanRestAdapter().findCastellan(kchuhCastleId);
        Assert.assertEquals(1, castellan.getEmails().size());

        try {
            getCastellanRestAdapter().addEmail(kchuhCastleId, "kchuh@nextree.co.kr");
        } catch (Exception e) {
            Assert.assertTrue(true);
        }

        getCastellanRestAdapter().addEmail(kchuhCastleId, "michael7557@gmail.com");
        castellan = getCastellanRestAdapter().findCastellan(kchuhCastleId);
        Assert.assertEquals(2, castellan.getEmails().size());

        List<CastellanEmailSdo> emails = castellan.getEmails();
        Iterator<CastellanEmailSdo> iterator = emails.iterator();
        while(iterator.hasNext()) {
            CastellanEmailSdo emailDto = iterator.next();
            Assert.assertEquals(false, emailDto.isVerified());
        }

        getCastellanRestAdapter().verifyEmail(kchuhCastleId, "kchuh@nextree.co.kr");
        castellan = getCastellanRestAdapter().findCastellan(kchuhCastleId);

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
        Assert.assertEquals(1, castellan.getAccounts().size());


        getCastellanRestAdapter().setPrimaryEmail(kchuhCastleId, "kchuh@nextree.co.kr");
        castellan = getCastellanRestAdapter().findCastellan(kchuhCastleId);
        emails = castellan.getEmails();
        iterator = emails.iterator();
        while(iterator.hasNext()) {
            CastellanEmailSdo emailDto = iterator.next();
            if ("kchuh@nextree.co.kr".equals(emailDto.getAddress())) {
                Assert.assertEquals(true, emailDto.isPrimary());
            }
            else {
                Assert.assertEquals(false, emailDto.isPrimary());
            }
        }

        getCastellanRestAdapter().setPrimaryEmail(kchuhCastleId, "michael7557@gmail.com");
        castellan = getCastellanRestAdapter().findCastellan(kchuhCastleId);
        emails = castellan.getEmails();
        iterator = emails.iterator();
        while(iterator.hasNext()) {
            CastellanEmailSdo emailDto = iterator.next();
            if ("michael7557@gmail.com".equals(emailDto.getAddress())) {
                Assert.assertEquals(true, emailDto.isPrimary());
            }
            else {
                Assert.assertEquals(false, emailDto.isPrimary());
            }
        }
    }

    @Test
    public void joinedMetroTest() {
        //
        CastellanFindSdo castellan = getCastellanRestAdapter().findCastellan(kchuhCastleId);
        Assert.assertEquals(0, castellan.getJoinedMetros().size());
        getCastellanRestAdapter().addJoinedMetro(kchuhCastleId, new JoinedMetroSdo("M01", "1@M01"));
        getCastellanRestAdapter().addJoinedMetro(kchuhCastleId, new JoinedMetroSdo("M02", "1@M02"));

        castellan = getCastellanRestAdapter().findCastellan(kchuhCastleId);
        Assert.assertEquals(2, castellan.getJoinedMetros().size());

        getCastellanRestAdapter().removeJoinedMetro(kchuhCastleId, new JoinedMetroSdo("M02", "1@M02"));
        castellan = getCastellanRestAdapter().findCastellan(kchuhCastleId);
        Assert.assertEquals(1, castellan.getJoinedMetros().size());
    }
}
