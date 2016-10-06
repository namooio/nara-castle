package namoo.nara.castle;

import namoo.nara.castle.adapter.dto.CastleFindDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class CastleResourceTest extends AbstractCastleApplicationTests {
    //
    @Test
    public void castleTest() {
        //
        Assert.assertEquals(5, getCastleClient().findCastles().size());
        CastleFindDto castle = getCastleClient().findCastle(kchuhCastleId);
        Assert.assertEquals(Locale.KOREA, castle.getLocale());

        getCastleClient().modifyLocale(kchuhCastleId, Locale.US);
        castle = getCastleClient().findCastle(kchuhCastleId);
        Assert.assertEquals(Locale.US, castle.getLocale());
    }
}
