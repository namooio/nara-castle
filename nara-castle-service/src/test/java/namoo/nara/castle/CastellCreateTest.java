package namoo.nara.castle;

import namoo.nara.castle.remote.dto.CastellanCreateDto;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public class CastellCreateTest extends AbstractServiceApplicationTests {

    @Test
    public void createTest() {
        getCastellanClient().create(new CastellanCreateDto(UUID.randomUUID().toString(), "wckang@nextree.co.kr"));
        getCastellanClient().create(new CastellanCreateDto(UUID.randomUUID().toString(), "jkkang@nextree.co.kr"));
        getCastellanClient().create(new CastellanCreateDto(UUID.randomUUID().toString(), "hckang@nextree.co.kr"));
        getCastellanClient().create(new CastellanCreateDto(UUID.randomUUID().toString(), "hkkang@nextree.co.kr"));
        getCastellanClient().create(new CastellanCreateDto(UUID.randomUUID().toString(), "hjkown@nextree.co.kr"));
    }

}
