package namoo.nara.castle;

import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.history.AccountBookDto;
import namoo.nara.castle.adapter.dto.history.CastleStateBookDto;
import namoo.nara.castle.adapter.dto.history.LoginAccountDto;
import namoo.nara.castle.adapter.dto.history.MetroBookDto;
import namoo.nara.castle.domain.entity.history.LoginAccount;
import namoo.nara.castle.domain.entity.history.MetroBook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastleHistoryServiceTest extends AbstractCastleServiceApplicationTests  {
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
    public void testAccountBook() {
        //
        AccountBookDto accountBookDto = new AccountBookDto();
        LoginAccountDto loginAccountDto = new LoginAccountDto();
        loginAccountDto.setChannel(LoginAccount.LoginChannel.Nara.name());
        loginAccountDto.setCreateTime(System.currentTimeMillis());
        loginAccountDto.setLoginUserId("1234");
        accountBookDto.addAccountDto(loginAccountDto);

        getCastleHistoryClient().attachAccountBook(id, accountBookDto);

        accountBookDto = getCastleHistoryClient().findAccountBook(id);
        Assert.assertEquals(1, accountBookDto.getAccounts().size());

        getCastleHistoryClient().detachAccountBook(id);
        accountBookDto = getCastleHistoryClient().findAccountBook(id);
        Assert.assertEquals(0, accountBookDto.getAccounts().size());
    }

    @Test
    public void testCastleStateBook() {
        //
        CastleStateBookDto castleStateBookDto = new CastleStateBookDto();
        getCastleHistoryClient().attachCastleStateBook(id, castleStateBookDto);

        castleStateBookDto = getCastleHistoryClient().findCastleStateBook(id);
        Assert.assertNotNull(castleStateBookDto);

        getCastleHistoryClient().detachAccountBook(id);
        castleStateBookDto = getCastleHistoryClient().findCastleStateBook(id);
        Assert.assertEquals(0, castleStateBookDto.getStates().size());
    }

    @Test
    public void testMetroBook() {
        //
        MetroBookDto metroBookDto = new MetroBookDto();
        getCastleHistoryClient().attachMetroBook(id, metroBookDto);

        metroBookDto = getCastleHistoryClient().findMetroBook(id);
        Assert.assertNotNull(metroBookDto);

        getCastleHistoryClient().detachAccountBook(id);
        metroBookDto = getCastleHistoryClient().findMetroBook(id);
        Assert.assertEquals(0, metroBookDto.getMetros().size());
    }
}
