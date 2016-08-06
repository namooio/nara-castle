package namoo.nara.castle.front;

import namoo.nara.castle.AbstractCastleApplicationTests;
import namoo.nara.castle.front.dto.CastleFindDto;
import namoo.nara.castle.rep.dto.CastleBuildDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public class CastleFrontResourceTest extends AbstractCastleApplicationTests {
    //
    private String castleId;

    @Before
    public void setupInitialData() {
        //
        this.castleId = UUID.randomUUID().toString();

        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setEmail("michael7557@gmail.com");
        castleBuildDto.setLocale(Locale.US);

        getCastleRepClient().buildCastle(this.castleId, castleBuildDto);
    }

    @Test
    public void testBuildCastle() {
        //
        String castleId = UUID.randomUUID().toString();

        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("hkoo's Castle");
        castleBuildDto.setLocale(Locale.US);

        getCastleRepClient().buildCastle(castleId, castleBuildDto);


        CastleFindDto castleFindDto = getCastleFrontClient().findCastle(castleId);
        Assert.assertEquals("hkoo's Castle", castleFindDto.getName());
        Assert.assertEquals(Locale.US, castleFindDto.getLocale());
        Assert.assertEquals("Ready", castleFindDto.getState());
    }

    @Test
    public void testSuspendAndOpenCastle() {
        //
        String castleId = this.castleId;

        getCastleFrontClient().suspendCastle(castleId, "Suspend castle...");

        CastleFindDto castleFindDto = getCastleFrontClient().findCastle(castleId);
        Assert.assertEquals("Suspended", castleFindDto.getState());


        getCastleFrontClient().reopenCastle(castleId, "Reopen castle...");

        castleFindDto = getCastleFrontClient().findCastle(castleId);
        Assert.assertEquals("Open", castleFindDto.getState());
    }

    @Test
    public void testModifyName() {
        //
        String castleId = this.castleId;

        getCastleFrontClient().modifyName(castleId, "Juny's Castle");

        CastleFindDto castleFindDto = getCastleFrontClient().findCastle(castleId);
        Assert.assertEquals("Juny's Castle", castleFindDto.getName());
    }

//    @Test
    // FIXME: 테스트 수행 되도록 수정할 것
    public void testModifyLocale() {
        //
        String castleId = this.castleId;

        getCastleFrontClient().modifyLocale(castleId, Locale.KOREA);

        CastleFindDto castleFindDto = getCastleFrontClient().findCastle(castleId);
        Assert.assertEquals(Locale.KOREA, castleFindDto.getLocale());
    }

}
