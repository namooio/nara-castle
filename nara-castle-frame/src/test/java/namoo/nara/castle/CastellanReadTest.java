package namoo.nara.castle;

import namoo.nara.castle.remote.dto.CastellanReadDto;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 17..
 */
public class CastellanReadTest extends CastleFrameApplicationTests {

    @Test
    public void getByEmailTest() {
        String email = "wckang@nextree.co.kr";
        CastellanReadDto castellanReadDto = getCastellanClient().findByVerifiedEmail(email);
        Assert.assertNull(castellanReadDto);
    }
}
