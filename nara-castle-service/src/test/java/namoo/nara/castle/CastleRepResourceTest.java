package namoo.nara.castle;

import namoo.nara.castle.front.dto.history.MetroBookDto;
import namoo.nara.castle.rep.dto.CastleBuildDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 9..
 */
public class CastleRepResourceTest extends AbstractCastleServiceApplicationTests {
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
    public void addMetroTest() {
        //
        getCastleRepClient().addMetro(id, "98");
        getCastleRepClient().addMetro(id, "99");
        MetroBookDto metroBook = getCastleHistoryFrontClient().findMetroBook(id);
        Assert.assertEquals(2, metroBook.getMetros().size());
    }
}
