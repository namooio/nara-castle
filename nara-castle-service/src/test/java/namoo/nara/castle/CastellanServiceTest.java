package namoo.nara.castle;

import namoo.nara.castle.adapter.dto.CastleBuildDto;
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
    }

    @Test
    public void testModifyPhoto() {
        //
        getCastellanClient().modifyPhoto(id, "XXX");
    }

    @Test
    public void testModifyPrimaryEmail() {
        //
        getCastellanClient().modifyPrimaryEmail(id, "kchuh@nextree.co.kr");
    }

    @Test
    public void testModifyPrimaryPhone() {
        //
        getCastellanClient().modifyPrimaryPhone(id, "010-6325-7557");
    }


}
