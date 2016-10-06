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
        kchuhCastleId = this.castleService.buildCastle(new CastleCdo(Locale.KOREA));
        this.castleService.buildCastle(new CastleCdo(Locale.KOREA));
        this.castleService.buildCastle(new CastleCdo(Locale.KOREA));
        this.castleService.buildCastle(new CastleCdo(Locale.KOREA));
        this.castleService.buildCastle(new CastleCdo(Locale.KOREA));
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

}
