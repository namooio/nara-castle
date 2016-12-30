package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.logic.CastleServiceLogic;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.proxy.mockproxy.CastleMockProxyLycler;
import namoo.nara.castle.domain.service.data.CastleCdo;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.domain.store.mapstore.CastleMapStoreLycler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class CastleServiceTest {
    //
    private CastleService castleService;
    private String kchuhCastleId;

    @Before
    public void setUp() {
        //
        CastleStoreLycler castleStoreLycler = new CastleMapStoreLycler();
        CastleProxyLycler castleProxyLycler = new CastleMockProxyLycler();
        this.castleService = new CastleServiceLogic(castleStoreLycler, castleProxyLycler);
        kchuhCastleId = this.castleService.buildCastle(new CastleCdo("kchuh@nextree.co.kr", "1", Locale.KOREA));
        this.castleService.buildCastle(new CastleCdo("kchuh@nextree.co.kr", "1", Locale.KOREA));
        this.castleService.buildCastle(new CastleCdo("tsong@nextree.co.kr", "1", Locale.KOREA));
        this.castleService.buildCastle(new CastleCdo("hkkang@nextree.co.kr", "1", Locale.KOREA));
        this.castleService.buildCastle(new CastleCdo("jyjung@nextree.co.kr", "1", Locale.KOREA));
    }

    @Test
    public void castleTest() {
        //
        Assert.assertEquals(5, castleService.findCastles().size());
        Castle castle = castleService.findCastle(kchuhCastleId);
        Assert.assertEquals(Locale.KOREA, castle.getLocale());

        castleService.modifyLocale(kchuhCastleId, Locale.US);
        castle = castleService.findCastle(kchuhCastleId);
        Assert.assertEquals(Locale.US, castle.getLocale());
    }

    @Test
    public void emailTest() {
        //
        Castle castle = this.castleService.findCastle(kchuhCastleId);
        Castellan castellan = castle.getCastellan();
        Assert.assertEquals(1, castellan.getEmailsCount());

        this.castleService.addEmail(kchuhCastleId, "michael7557@gmail.com");

        castle = this.castleService.findCastle(kchuhCastleId);
        castellan = castle.getCastellan();
        Assert.assertEquals(2, castellan.getEmailsCount());
    }

    @Test
    public void joinedMetroTest() {
        //
        Castle castle = this.castleService.findCastle(kchuhCastleId);
        Castellan castellan = castle.getCastellan();
        Assert.assertEquals(0, castellan.getJoinedMetrosCount());
        this.castleService.addJoinedMetro(kchuhCastleId, "M01", "1@M01");
        this.castleService.addJoinedMetro(kchuhCastleId, "M02", "1@M02");

        castle = this.castleService.findCastle(kchuhCastleId);
        castellan = castle.getCastellan();
        Assert.assertEquals(2, castellan.getJoinedMetrosCount());

        this.castleService.removeJoinedMetro(kchuhCastleId, "M02");
        castle = this.castleService.findCastle(kchuhCastleId);
        castellan = castle.getCastellan();
        Assert.assertEquals(1, castellan.getJoinedMetrosCount());
    }
}
