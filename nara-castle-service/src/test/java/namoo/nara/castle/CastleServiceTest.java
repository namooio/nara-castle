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

    @Test
    public void testBuildCastle() {
        //
        String id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setMetroId("99");
        castleBuildDto.setLocale(Locale.US);
        getCastellanClientAdapter().buildCastle(id, castleBuildDto);

        CastleFindDto castleFindDto = getCastellanClientAdapter().findCastle(id);
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
        getCastellanClientAdapter().buildCastle(id, castleBuildDto);

        getCastellanClientAdapter().suspendCastle(id, "Suspend castle...");
        CastleFindDto castleFindDto = getCastellanClientAdapter().findCastle(id);
        Assert.assertEquals("Suspended", castleFindDto.getState());

        getCastellanClientAdapter().reopenCastle(id, "Reopen castle...");
        castleFindDto = getCastellanClientAdapter().findCastle(id);
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
        getCastellanClientAdapter().buildCastle(id, castleBuildDto);

        getCastellanClientAdapter().modifyName(id, "Juny's Castle");
        CastleFindDto castleFindDto = getCastellanClientAdapter().findCastle(id);
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
        getCastellanClientAdapter().buildCastle(id, castleBuildDto);

        getCastellanClientAdapter().modifyLocale(id, Locale.KOREA);
        CastleFindDto castleFindDto = getCastellanClientAdapter().findCastle(id);
        Assert.assertEquals(Locale.KOREA, castleFindDto.getLocale());
    }

}
