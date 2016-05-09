package namoo.nara.castle;

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
public class CastellanFrontResourceTest extends AbstractCastleServiceApplicationTests {
    //
    private String id;

    @Before
    public void setupInitialData() {
        //
        id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setEmail("michael7557@gmail.com");
        castleBuildDto.setLocale(Locale.US);
        getCastleRepClient().buildCastle(id, castleBuildDto);
    }

    @Test
    public void testModifyDisplayName() {
        //
        getCastellanFrontClient().modifyDisplayName(id, "Juny's Castle");
        CastellanFindDto castellan = getCastellanFrontClient().findCastellan(id);
        Assert.assertEquals("Juny's Castle", castellan.getDisplayName());
    }

    @Test
    public void testModifyPhoto() {
        //
        getCastellanFrontClient().modifyPhoto(id, "XXX");
        CastellanFindDto castellan = getCastellanFrontClient().findCastellan(id);
        Assert.assertEquals("XXX", castellan.getPhotoId());
    }

    @Test
    public void testModifyPrimaryEmail() {
        //
        getCastellanFrontClient().modifyPrimaryEmail(id, "kchuh@nextree.co.kr");
        CastellanFindDto castellan = getCastellanFrontClient().findCastellan(id);
        Assert.assertEquals("kchuh@nextree.co.kr", castellan.getPrimaryEmail());
    }

    @Test
    public void testModifyPrimaryPhone() {
        //
        getCastellanFrontClient().modifyPrimaryPhone(id, "010-6325-7557");
        CastellanFindDto castellan = getCastellanFrontClient().findCastellan(id);
        Assert.assertEquals("010-6325-7557", castellan.getPrimaryPhone());
    }


}
