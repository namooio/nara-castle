package namoo.nara.castle;

import namoo.nara.castle.domain.entity.history.LoginAccount;
import namoo.nara.castle.front.dto.history.AccountBookDto;
import namoo.nara.castle.front.dto.history.CastleStateBookDto;
import namoo.nara.castle.front.dto.history.LoginAccountDto;
import namoo.nara.castle.front.dto.history.MetroBookDto;
import namoo.nara.castle.rep.dto.CastleBuildDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastleHistoryFrontResourceTest extends AbstractCastleServiceApplicationTests  {
    //
    private String id;

    @Before
    public void setupInitialData() {
        //
        id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setLocale(Locale.US);
        getCastleRepClient().buildCastle(id, castleBuildDto);
    }

    @Test
    public void testAccountBook() {
        //
        AccountBookDto accountBookDto = new AccountBookDto();
        LoginAccountDto loginAccountDto = new LoginAccountDto();
        loginAccountDto.setChannel(LoginAccount.LoginChannel.Nara.name());
        loginAccountDto.setCreateTime(System.currentTimeMillis());
        loginAccountDto.setLoginUserId("1234");
        accountBookDto.addAccountDto(loginAccountDto);

        getCastleHistoryFrontClient().attachAccountBook(id, accountBookDto);

        accountBookDto = getCastleHistoryFrontClient().findAccountBook(id);
        Assert.assertEquals(1, accountBookDto.getAccounts().size());

        getCastleHistoryFrontClient().detachAccountBook(id);
        accountBookDto = getCastleHistoryFrontClient().findAccountBook(id);
        Assert.assertEquals(0, accountBookDto.getAccounts().size());
    }

    @Test
    public void testCastleStateBook() {
        //
        CastleStateBookDto castleStateBookDto = new CastleStateBookDto();
        getCastleHistoryFrontClient().attachCastleStateBook(id, castleStateBookDto);

        castleStateBookDto = getCastleHistoryFrontClient().findCastleStateBook(id);
        Assert.assertNotNull(castleStateBookDto);

        getCastleHistoryFrontClient().detachAccountBook(id);
        castleStateBookDto = getCastleHistoryFrontClient().findCastleStateBook(id);
        Assert.assertEquals(0, castleStateBookDto.getStates().size());
    }

    @Test
    public void testMetroBook() {
        //
        MetroBookDto metroBookDto = new MetroBookDto();
        getCastleHistoryFrontClient().attachMetroBook(id, metroBookDto);

        metroBookDto = getCastleHistoryFrontClient().findMetroBook(id);
        Assert.assertNotNull(metroBookDto);

        getCastleHistoryFrontClient().detachAccountBook(id);
        metroBookDto = getCastleHistoryFrontClient().findMetroBook(id);
        Assert.assertEquals(0, metroBookDto.getMetros().size());
    }
}
