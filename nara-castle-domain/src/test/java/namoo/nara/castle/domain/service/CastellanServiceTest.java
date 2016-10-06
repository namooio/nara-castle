package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.LoginIdType;
import namoo.nara.castle.domain.logic.CastellanServiceLogic;
import namoo.nara.castle.domain.service.data.CastellanUdo;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.domain.store.mapstore.CastleMapStoreLycler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CastellanServiceTest {
    //
    private CastellanService castellanService;
    private String kchuhCastleId = "0";

    @Before
    public void setUp() {
        //
        CastleStoreLycler castleStoreLycler = new CastleMapStoreLycler();
        this.castellanService = new CastellanServiceLogic(castleStoreLycler);
        this.castellanService.createCastellan(kchuhCastleId, "Ki Chul, Huh");
    }

    @Test
    public void basicInfoTest() {
        //
        CastellanUdo castellanUdo = new CastellanUdo();
        castellanUdo.setName("kchuh");
        this.castellanService.modifyCastellan(kchuhCastleId, castellanUdo);
        Castellan castellan = this.castellanService.findCastellan(kchuhCastleId);
        Assert.assertEquals("kchuh", castellan.getName());

        this.castellanService.removeCastellan(kchuhCastleId);
        castellan = this.castellanService.findCastellan(kchuhCastleId);
        Assert.assertNull(castellan);
    }

    @Test
    public void accountTest() {
        //
        Castellan castellan = this.castellanService.findCastellan(kchuhCastleId);
        Assert.assertEquals(0, castellan.getAccounts().size());

        this.castellanService.modifyPasswordCredential(kchuhCastleId, "4321");
        castellan = this.castellanService.findCastellan(kchuhCastleId);
        Assert.assertEquals("4321", castellan.getCredential().getPassword());

        this.castellanService.addAccount(kchuhCastleId, "kchuh", LoginIdType.Username);
        this.castellanService.addAccount(kchuhCastleId, "kchuh@nextree.co.kr", LoginIdType.Email);

        castellan = this.castellanService.findCastellan(kchuhCastleId);
        Assert.assertEquals(2, castellan.getAccounts().size());

        castellan = this.castellanService.findCastellan("kchuh", LoginIdType.Username);
        Assert.assertEquals(2, castellan.getAccounts().size());

        castellan = this.castellanService.findCastellan("kchuh@nextree.co.kr", LoginIdType.Email);
        Assert.assertEquals(2, castellan.getAccounts().size());

        castellan = this.castellanService.findCastellan("kchuh", LoginIdType.Email);
        Assert.assertNull(castellan);

        this.castellanService.removeAccount(kchuhCastleId, "kchuh", LoginIdType.Username);
        castellan = this.castellanService.findCastellan(kchuhCastleId);
        Assert.assertEquals(1, castellan.getAccounts().size());
    }

    @Test
    public void emailTest() {
        //
        Castellan castellan = this.castellanService.findCastellan(kchuhCastleId);
        Assert.assertEquals(0, castellan.getEmailsCount());

        this.castellanService.addEmail(kchuhCastleId, "kchuh@nextree.co.kr");
        this.castellanService.addEmail(kchuhCastleId, "michael7557@gmail.com");
        castellan = this.castellanService.findCastellan(kchuhCastleId);
        Assert.assertEquals(2, castellan.getEmailsCount());
        Assert.assertEquals(false, castellan.findEmail("kchuh@nextree.co.kr").isVerified());

        this.castellanService.verifyEmail(kchuhCastleId, "kchuh@nextree.co.kr");
        castellan = this.castellanService.findCastellan(kchuhCastleId);
        Assert.assertEquals(true, castellan.findEmail("kchuh@nextree.co.kr").isVerified());
        Assert.assertEquals(1, castellan.getAccounts().size());

        Assert.assertEquals(false, castellan.hasPrimaryEmail());
        this.castellanService.setPrimaryEmail(kchuhCastleId, "kchuh@nextree.co.kr");

        castellan = this.castellanService.findCastellan(kchuhCastleId);
        Assert.assertEquals("kchuh@nextree.co.kr", castellan.findPrimaryEmail().getAddress());
    }

    @Test
    public void joinedMetroTest() {
        //
        Castellan castellan = this.castellanService.findCastellan(kchuhCastleId);
        Assert.assertEquals(0, castellan.getJoinedMetrosCount());
        this.castellanService.addJoinedMetro(kchuhCastleId, "M01", "1@M01");
        this.castellanService.addJoinedMetro(kchuhCastleId, "M02", "1@M02");

        castellan = this.castellanService.findCastellan(kchuhCastleId);
        Assert.assertEquals(2, castellan.getJoinedMetrosCount());

        this.castellanService.removeJoinedMetro(kchuhCastleId, "M02", "1@M02");
        castellan = this.castellanService.findCastellan(kchuhCastleId);
        Assert.assertEquals(1, castellan.getJoinedMetrosCount());
    }
}
