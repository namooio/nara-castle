package namoo.nara.castle;

import namoo.nara.castle.protocol.sdo.CastleFindSdo;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class CastleResourceTest extends AbstractCastleApplicationTests {
    //
    @Test
    public void castleTest() {
        //
        Assert.assertEquals(5, getCastleRestAdapter().findCastles().size());
        CastleFindSdo castle = getCastleRestAdapter().findCastle(kchuhCastleId);
        Assert.assertEquals(Locale.KOREA, castle.getLocale());

        getCastleRestAdapter().modifyLocale(kchuhCastleId, Locale.US);
        castle = getCastleRestAdapter().findCastle(kchuhCastleId);
        Assert.assertEquals(Locale.US, castle.getLocale());
    }
}
