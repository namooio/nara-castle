package namoo.nara.castle.rep;

import namoo.nara.castle.AbstractCastleApplicationTests;
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
public class CastleRepResourceTest extends AbstractCastleApplicationTests {
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

        getCastleRepClient().buildCastle(castleId, castleBuildDto);
    }

    @Test
    public void addMetroTest() {
        //
        getCastleRepClient().addMetro(castleId, "98");
        getCastleRepClient().addMetro(castleId, "99");

        MetroBookDto metroBook = getCastleHistoryFrontClient().findMetroBook(castleId);
        Assert.assertEquals(2, metroBook.getMetros().size());
    }
}
