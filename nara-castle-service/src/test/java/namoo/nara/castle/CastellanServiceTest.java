package namoo.nara.castle;

import namoo.nara.castle.adapter.dto.CastellanFindDto;
import namoo.nara.castle.adapter.dto.CastleBuildDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public class CastellanServiceTest extends AbstractCastleServiceApplicationTests {
    //
    private String id;

    @Before
    public void setupInitialData() {
        //
        id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setMetroId("99");
        castleBuildDto.setLocale(Locale.US);
        getCastleClient().buildCastle(id, castleBuildDto);
    }

    @Test
    public void testModifyDisplayName() {
        //
        getCastellanClient().modifyDisplayName(id, "Juny's Castle");
        CastellanFindDto castellan = getCastellanClient().findCastellan(id);
        Assert.assertEquals("Juny's Castle", castellan.getDisplayName());
    }

    @Test
    public void testModifyPhoto() {
        //
        getCastellanClient().modifyPhoto(id, "XXX");
        CastellanFindDto castellan = getCastellanClient().findCastellan(id);
        Assert.assertEquals("XXX", castellan.getPhotoId());
    }

    @Test
    public void testModifyPrimaryEmail() {
        //
        getCastellanClient().modifyPrimaryEmail(id, "kchuh@nextree.co.kr");
        CastellanFindDto castellan = getCastellanClient().findCastellan(id);
        Assert.assertEquals("kchuh@nextree.co.kr", castellan.getPrimaryEmail());
    }

    @Test
    public void testModifyPrimaryPhone() {
        //
        getCastellanClient().modifyPrimaryPhone(id, "010-6325-7557");
        CastellanFindDto castellan = getCastellanClient().findCastellan(id);
        Assert.assertEquals("010-6325-7557", castellan.getPrimaryPhone());
    }


}
