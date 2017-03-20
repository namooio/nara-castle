package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.JoinedMetro;
import namoo.nara.castle.domain.logic.CastleServiceLogic;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.proxy.mockproxy.CastleMockProxyLycler;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.sdo.CastleCdo;
import namoo.nara.castle.domain.spec.sdo.CastleRdo;
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
        kchuhCastleId = this.castleService.buildCastle(new CastleCdo("kchuh@nextree.co.kr", "1", "1@1", Locale.KOREA));
        this.castleService.buildCastle(new CastleCdo("kchuh@nextree.co.kr", "1", "2@1", Locale.KOREA));
        this.castleService.buildCastle(new CastleCdo("tsong@nextree.co.kr", "1", "3@1", Locale.KOREA));
        this.castleService.buildCastle(new CastleCdo("hkkang@nextree.co.kr", "1", "4@1", Locale.KOREA));
        this.castleService.buildCastle(new CastleCdo("jyjung@nextree.co.kr", "1", "5@1", Locale.KOREA));
    }

    @Test
    public void castleTest() {
        //
        Assert.assertEquals(5, castleService.findCastles().size());
        CastleRdo castle = castleService.findCastle(kchuhCastleId);
        Assert.assertEquals(Locale.KOREA, castle.getLocale());

        castleService.modifyLocale(kchuhCastleId, Locale.US);
        castle = castleService.findCastle(kchuhCastleId);
        Assert.assertEquals(Locale.US, castle.getLocale());
    }

    @Test
    public void emailTest() {
        //
        CastleRdo castle = this.castleService.findCastle(kchuhCastleId);
        Castellan castellan = castle.getCastellan();
        Assert.assertEquals(1, castellan.getEmails().size());

        this.castleService.addEmail(kchuhCastleId, "michael7557@gmail.com");

        castle = this.castleService.findCastle(kchuhCastleId);
        castellan = castle.getCastellan();
        Assert.assertEquals(2, castellan.getEmails().size());
    }

    @Test
    public void joinedMetroTest() {
        //
        CastleRdo castle = this.castleService.findCastle(kchuhCastleId);
        Castellan castellan = castle.getCastellan();
        Assert.assertEquals(1, castellan.getJoinedMetros().size());
        this.castleService.addJoinedMetro(kchuhCastleId, new JoinedMetro("M01", "1@M01"));
        this.castleService.addJoinedMetro(kchuhCastleId, new JoinedMetro("M02", "1@M02"));

        castle = this.castleService.findCastle(kchuhCastleId);
        castellan = castle.getCastellan();
        Assert.assertEquals(3, castellan.getJoinedMetros().size());

        this.castleService.removeJoinedMetro(kchuhCastleId, "M02");
        castle = this.castleService.findCastle(kchuhCastleId);
        castellan = castle.getCastellan();
        Assert.assertEquals(2, castellan.getJoinedMetros().size());
    }
}
