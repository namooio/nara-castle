package namoo.nara.castle;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public class CastleFrontResourceTest { //extends AbstractCastleApplicationTests {
    //
    // FIXME: 수정할 것.
    /*
    @Test
    public void testBuildCastle() {
        //
        String id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setLocale(Locale.US);
        getCastleRepClient().buildCastle(id, castleBuildDto);

        CastleFindDto castleFindDto = getCastleFrontClient().findCastle(id);
        Assert.assertEquals("Michael's Castle", castleFindDto.getName());
        Assert.assertEquals(Locale.US, castleFindDto.getLocale());
        Assert.assertEquals("Ready", castleFindDto.getState());
    }

    @Test
    public void testSuspendAndOpenCastle() {
        //
        String id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setLocale(Locale.US);
        getCastleRepClient().buildCastle(id, castleBuildDto);

        getCastleFrontClient().suspendCastle(id, "Suspend castle...");
        CastleFindDto castleFindDto = getCastleFrontClient().findCastle(id);
        Assert.assertEquals("Suspended", castleFindDto.getState());

        getCastleFrontClient().reopenCastle(id, "Reopen castle...");
        castleFindDto = getCastleFrontClient().findCastle(id);
        Assert.assertEquals("Open", castleFindDto.getState());
    }

    @Test
    public void testModifyName() {
        //
        String id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setLocale(Locale.US);
        getCastleRepClient().buildCastle(id, castleBuildDto);

        getCastleFrontClient().modifyName(id, "Juny's Castle");
        CastleFindDto castleFindDto = getCastleFrontClient().findCastle(id);
        Assert.assertEquals("Juny's Castle", castleFindDto.getName());
    }

    @Test
    public void testModifyLocale() {
        //
        String id = UUID.randomUUID().toString();
        CastleBuildDto castleBuildDto = new CastleBuildDto();
        castleBuildDto.setName("Michael's Castle");
        castleBuildDto.setLocale(Locale.US);
        getCastleRepClient().buildCastle(id, castleBuildDto);

        getCastleFrontClient().modifyLocale(id, Locale.KOREA);
        CastleFindDto castleFindDto = getCastleFrontClient().findCastle(id);
        Assert.assertEquals(Locale.KOREA, castleFindDto.getLocale());
    }
    */

}
