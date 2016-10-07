package namoo.nara.castle;

import namoo.nara.castle.adapter.dto.CastellanEmailDto;
import namoo.nara.castle.adapter.dto.CastellanFindDto;
import namoo.nara.castle.adapter.dto.JoinedMetroDto;
import namoo.nara.castle.adapter.dto.LoginAccountDto;
import namoo.nara.castle.adapter.dto.util.CastleConst;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

public class CastellanResourceTest extends AbstractCastleApplicationTests {
    //
    @Test
    public void basicInfoTest() {
        //
        getCastellanClient().removeCastellan(kchuhCastleId);
        try {
            getCastellanClient().findCastellan(kchuhCastleId);
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void accountTest() {
        //
        CastellanFindDto castellan = getCastellanClient().findCastellan(kchuhCastleId);
        Assert.assertEquals(0, castellan.getAccounts().size());

        getCastellanClient().modifyPassword(kchuhCastleId, "4321");
        castellan = getCastellanClient().findCastellan(kchuhCastleId);
        Assert.assertEquals("4321", castellan.getCredential().getPassword());

        getCastellanClient().addAccount(kchuhCastleId, new LoginAccountDto("kchuh", CastleConst.LOGIN_ID_TYPE_USERNAME));

        castellan = getCastellanClient().findCastellan(kchuhCastleId);
        Assert.assertEquals(1, castellan.getAccounts().size());

        castellan = getCastellanClient().findCastellan("kchuh", CastleConst.LOGIN_ID_TYPE_USERNAME);
        Assert.assertEquals(1, castellan.getAccounts().size());

        castellan = getCastellanClient().findCastellan("kchuh@nextree.co.kr", CastleConst.LOGIN_ID_TYPE_EMAIL);
        Assert.assertNull(castellan);

        getCastellanClient().verifyEmail(kchuhCastleId, "kchuh@nextree.co.kr");
        castellan = getCastellanClient().findCastellan("kchuh@nextree.co.kr", CastleConst.LOGIN_ID_TYPE_EMAIL);
        Assert.assertNotNull(castellan);

        castellan = getCastellanClient().findCastellan("kchuh", CastleConst.LOGIN_ID_TYPE_EMAIL);
        Assert.assertNull(castellan);

        getCastellanClient().removeAccount(kchuhCastleId, new LoginAccountDto("kchuh", CastleConst.LOGIN_ID_TYPE_USERNAME));
        castellan = getCastellanClient().findCastellan(kchuhCastleId);
        Assert.assertEquals(1, castellan.getAccounts().size());
    }

    @Test
    public void emailTest() {
        //
        CastellanFindDto castellan = getCastellanClient().findCastellan(kchuhCastleId);
        Assert.assertEquals(1, castellan.getEmails().size());

        try {
            getCastellanClient().addEmail(kchuhCastleId, "kchuh@nextree.co.kr");
        } catch (Exception e) {
            Assert.assertTrue(true);
        }

        getCastellanClient().addEmail(kchuhCastleId, "michael7557@gmail.com");
        castellan = getCastellanClient().findCastellan(kchuhCastleId);
        Assert.assertEquals(2, castellan.getEmails().size());

        Set<CastellanEmailDto> emails = castellan.getEmails();
        Iterator<CastellanEmailDto> iterator = emails.iterator();
        while(iterator.hasNext()) {
            CastellanEmailDto emailDto = iterator.next();
            Assert.assertEquals(false, emailDto.isVerified());
        }

        getCastellanClient().verifyEmail(kchuhCastleId, "kchuh@nextree.co.kr");
        castellan = getCastellanClient().findCastellan(kchuhCastleId);

        emails = castellan.getEmails();
        iterator = emails.iterator();
        while(iterator.hasNext()) {
            CastellanEmailDto emailDto = iterator.next();
            if ("kchuh@nextree.co.kr".equals(emailDto.getAddress())) {
                Assert.assertEquals(true, emailDto.isVerified());
            }
            else {
                Assert.assertEquals(false, emailDto.isVerified());
            }
        }
        Assert.assertEquals(1, castellan.getAccounts().size());


        getCastellanClient().setPrimaryEmail(kchuhCastleId, "kchuh@nextree.co.kr");
        castellan = getCastellanClient().findCastellan(kchuhCastleId);
        emails = castellan.getEmails();
        iterator = emails.iterator();
        while(iterator.hasNext()) {
            CastellanEmailDto emailDto = iterator.next();
            if ("kchuh@nextree.co.kr".equals(emailDto.getAddress())) {
                Assert.assertEquals(true, emailDto.isPrimary());
            }
            else {
                Assert.assertEquals(false, emailDto.isPrimary());
            }
        }

        getCastellanClient().setPrimaryEmail(kchuhCastleId, "michael7557@gmail.com");
        castellan = getCastellanClient().findCastellan(kchuhCastleId);
        emails = castellan.getEmails();
        iterator = emails.iterator();
        while(iterator.hasNext()) {
            CastellanEmailDto emailDto = iterator.next();
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
        CastellanFindDto castellan = getCastellanClient().findCastellan(kchuhCastleId);
        Assert.assertEquals(0, castellan.getJoinedMetros().size());
        getCastellanClient().addJoinedMetro(kchuhCastleId, new JoinedMetroDto("M01", "1@M01"));
        getCastellanClient().addJoinedMetro(kchuhCastleId, new JoinedMetroDto("M02", "1@M02"));

        castellan = getCastellanClient().findCastellan(kchuhCastleId);
        Assert.assertEquals(2, castellan.getJoinedMetros().size());

        getCastellanClient().removeJoinedMetro(kchuhCastleId, new JoinedMetroDto("M02", "1@M02"));
        castellan = getCastellanClient().findCastellan(kchuhCastleId);
        Assert.assertEquals(1, castellan.getJoinedMetros().size());
    }
}
