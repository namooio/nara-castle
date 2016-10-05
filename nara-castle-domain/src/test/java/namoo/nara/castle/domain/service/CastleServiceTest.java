package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.logic.CastleServiceLogic;
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
        this.castleService = new CastleServiceLogic(castleStoreLycler);
        kchuhCastleId = this.castleService.buildCastle(new CastleCdo(Locale.KOREA, "kchuh", "kchuh@nextree.co.kr"));
        this.castleService.buildCastle(new CastleCdo(Locale.KOREA, "jyjung", "jyjung@nextree.co.kr"));
        this.castleService.buildCastle(new CastleCdo(Locale.KOREA, "hkkang", "hkkang@nextree.co.kr"));
        this.castleService.buildCastle(new CastleCdo(Locale.KOREA, "tsong", "tsong@nextree.co.kr"));
        this.castleService.buildCastle(new CastleCdo(Locale.KOREA, "iylee", "iylee@nextree.co.kr"));
    }

    @Test
    public void castleTest() {
        //
        Assert.assertEquals(5, castleService.findCastles().size());
        Castle castle = castleService.findCastle(kchuhCastleId);
        Assert.assertEquals("kchuh", castle.getOwner().getDisplayName());
        Assert.assertEquals("kchuh@nextree.co.kr", castle.getOwner().getPrimaryEmail());

    }

}
