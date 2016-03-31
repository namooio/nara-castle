package namoo.nara.castle;

import namoo.nara.castle.remote.dto.CastellanCreateDto;
import namoo.nara.castle.remote.dto.CastellanReadDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 19..
 */
public class CastellEmailTest extends AbstractServiceApplicationTests {

    @Test
    public void testEmail() {
        String castellanId = UUID.randomUUID().toString();
        String email = "wckang@nextree.co.kr";
        getCastellanClient().create(new CastellanCreateDto(castellanId, "wckang@nextree.co.kr"));

        CastellanReadDto castellanReadDto = getCastellanClient().findCastellan(castellanId);
        Assert.assertNotNull(castellanReadDto);

        castellanReadDto = getCastellanClient().findByVerifiedEmail(email);
        Assert.assertNull(castellanReadDto);

        getCastellanClient().verifyEmail(email, castellanId);
        castellanReadDto = getCastellanClient().findByVerifiedEmail(email);
        Assert.assertNotNull(castellanReadDto);

        try {
            getCastellanClient().removeEmail(email, castellanId);
        }
        catch (Exception e) {
            Assert.assertTrue(true);
        }

        String email2 = "jkkang@nextree.co.kr";
        getCastellanClient().addEmail(email2, castellanId);
        getCastellanClient().verifyEmail(email2, castellanId);
        castellanReadDto = getCastellanClient().findByVerifiedEmail(email2);
        Assert.assertNotNull(castellanReadDto);
        getCastellanClient().removeEmail(email2, castellanId);
        castellanReadDto = getCastellanClient().findByVerifiedEmail(email2);
        Assert.assertNull(castellanReadDto);
    }
}
