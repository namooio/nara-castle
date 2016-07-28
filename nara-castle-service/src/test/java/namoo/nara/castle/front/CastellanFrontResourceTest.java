package namoo.nara.castle.front;

import namoo.nara.castle.AbstractCastleApplicationTests;
import namoo.nara.castle.front.dto.CastellanFindDto;
import namoo.nara.castle.rep.dto.CastleBuildDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public class CastellanFrontResourceTest extends AbstractCastleApplicationTests {
    //
    private String castleId;

    @Before
    public void setupInitialData() {
        //
        castleId = UUID.randomUUID().toString();

        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setEmail("michael7557@gmail.com");
        castleBuildDto.setLocale(Locale.US);

        getCastleRepClient().buildCastle(castleId, castleBuildDto);
    }

    @Test
    public void testModifyDisplayName() {
        //
        getCastellanFrontClient().modifyDisplayName(castleId, "Juny's Castle");

        CastellanFindDto castellan = getCastellanFrontClient().findCastellan(castleId);
        Assert.assertEquals("Juny's Castle", castellan.getDisplayName());
    }

    @Test
    public void testModifyPhoto() {
        //
        getCastellanFrontClient().modifyPhoto(castleId, "XXX");

        CastellanFindDto castellan = getCastellanFrontClient().findCastellan(castleId);
        Assert.assertEquals("XXX", castellan.getPhotoId());
    }

    @Test
    public void testModifyPrimaryEmail() {
        //
        getCastellanFrontClient().modifyPrimaryEmail(castleId, "kchuh@nextree.co.kr");

        CastellanFindDto castellan = getCastellanFrontClient().findCastellan(castleId);
        Assert.assertEquals("kchuh@nextree.co.kr", castellan.getPrimaryEmail());
    }

    @Test
    public void testModifyPrimaryPhone() {
        //
        getCastellanFrontClient().modifyPrimaryPhone(castleId, "010-6325-7557");

        CastellanFindDto castellan = getCastellanFrontClient().findCastellan(castleId);
        Assert.assertEquals("010-6325-7557", castellan.getPrimaryPhone());
    }

}
