package namoo.nara.castle;

import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public class CastleServiceTest extends AbstractCastleServiceApplicationTests {
    //
    @Test
    public void testBuildCastle() {
        //
        String id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setMetroId("99");
        castleBuildDto.setLocale(Locale.US);
        getCastleClient().buildCastle(id, castleBuildDto);

        CastleFindDto castleFindDto = getCastleClient().findCastle(id);
        Assert.assertEquals("Michael's Castle", castleFindDto.getName());
        Assert.assertEquals(Locale.US, castleFindDto.getLocale());
        Assert.assertEquals("Ready", castleFindDto.getState());
    }

    @Test
    public void testSuspendAndOpenCastle() {
        //
        String id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setMetroId("99");
        castleBuildDto.setLocale(Locale.US);
        getCastleClient().buildCastle(id, castleBuildDto);

        getCastleClient().suspendCastle(id, "Suspend castle...");
        CastleFindDto castleFindDto = getCastleClient().findCastle(id);
        Assert.assertEquals("Suspended", castleFindDto.getState());

        getCastleClient().reopenCastle(id, "Reopen castle...");
        castleFindDto = getCastleClient().findCastle(id);
        Assert.assertEquals("Open", castleFindDto.getState());
    }

    @Test
    public void testModifyName() {
        //
        String id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setMetroId("99");
        castleBuildDto.setLocale(Locale.US);
        getCastleClient().buildCastle(id, castleBuildDto);

        getCastleClient().modifyName(id, "Juny's Castle");
        CastleFindDto castleFindDto = getCastleClient().findCastle(id);
        Assert.assertEquals("Juny's Castle", castleFindDto.getName());
    }

    @Test
    public void testModifyLocale() {
        //
        String id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setMetroId("99");
        castleBuildDto.setLocale(Locale.US);
        getCastleClient().buildCastle(id, castleBuildDto);

        getCastleClient().modifyLocale(id, Locale.KOREA);
        CastleFindDto castleFindDto = getCastleClient().findCastle(id);
        Assert.assertEquals(Locale.KOREA, castleFindDto.getLocale());
    }

}
