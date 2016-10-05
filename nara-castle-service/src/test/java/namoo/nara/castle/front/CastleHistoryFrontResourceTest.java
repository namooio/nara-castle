package namoo.nara.castle.front;

import namoo.nara.castle.AbstractCastleApplicationTests;
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
public class CastleHistoryFrontResourceTest extends AbstractCastleApplicationTests {
    //
    private String castleId;

    @Before
    public void setupInitialData() {
        //
        castleId = UUID.randomUUID().toString();

        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setLocale(Locale.US);

        getCastleRepClient().buildCastle(castleId, castleBuildDto);
    }

//    @Test
    // FIXME: 테스트 코드 돌아가도록 수정할 것
    public void testAccountBook() {
        //
        AccountBookDto accountBookDto = new AccountBookDto();
        LoginAccountDto loginAccountDto = new LoginAccountDto();
        loginAccountDto.setChannel(LoginAccount.LoginChannel.NaraEmail.name());
        loginAccountDto.setCreateTime(System.currentTimeMillis());
        loginAccountDto.setLoginUserId("1234");

        accountBookDto.addAccountDto(loginAccountDto);


        getCastleHistoryFrontClient().attachAccountBook(castleId, accountBookDto);

        accountBookDto = getCastleHistoryFrontClient().findAccountBook(castleId);
        Assert.assertEquals(1, accountBookDto.getAccounts().size());

        getCastleHistoryFrontClient().detachAccountBook(castleId);

        accountBookDto = getCastleHistoryFrontClient().findAccountBook(castleId);
        Assert.assertEquals(0, accountBookDto.getAccounts().size());
    }

    @Test
    public void testCastleStateBook() {
        //
        CastleStateBookDto castleStateBookDto = new CastleStateBookDto();
        getCastleHistoryFrontClient().attachCastleStateBook(castleId, castleStateBookDto);

        castleStateBookDto = getCastleHistoryFrontClient().findCastleStateBook(castleId);
        Assert.assertNotNull(castleStateBookDto);


        getCastleHistoryFrontClient().detachAccountBook(castleId);

        castleStateBookDto = getCastleHistoryFrontClient().findCastleStateBook(castleId);
        Assert.assertEquals(0, castleStateBookDto.getStates().size());
    }

    @Test
    public void testMetroBook() {
        //
        MetroBookDto metroBookDto = new MetroBookDto();

        getCastleHistoryFrontClient().attachMetroBook(castleId, metroBookDto);

        metroBookDto = getCastleHistoryFrontClient().findMetroBook(castleId);
        Assert.assertNotNull(metroBookDto);


        getCastleHistoryFrontClient().detachAccountBook(castleId);

        metroBookDto = getCastleHistoryFrontClient().findMetroBook(castleId);
        Assert.assertEquals(0, metroBookDto.getMetros().size());
    }
}
