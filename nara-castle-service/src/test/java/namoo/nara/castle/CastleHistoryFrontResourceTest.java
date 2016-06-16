package namoo.nara.castle;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastleHistoryFrontResourceTest { //extends AbstractCastleServiceApplicationTests  {
    //
    private String id;
    // FIXME: 수정할 것.
    /*
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
        loginAccountDto.setChannel(LoginAccount.LoginChannel.NaraEmail.name());
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
    */
}
