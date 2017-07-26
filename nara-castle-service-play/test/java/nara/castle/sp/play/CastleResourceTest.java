package nara.castle.sp.play;

import nara.castle.sp.play.shared.AbstractCastleIntegrationTester;
import nara.share.domain.granule.Name;
import nara.share.domain.granule.NaraZone;
import org.junit.Test;

import java.time.ZoneId;
import java.util.Locale;

public class CastleResourceTest extends AbstractCastleIntegrationTester {
    //
    @Test
    public void testBuildCastle() throws Exception {
        //
        final String url = buildUrl("/castles");

        final String metroId = "POP";
        final String citizenId = "02@POP";
        final Name name = new Name(Locale.KOREA, "gildong", "hong");
        final String email = "gildong@testing.co.kr";
        final NaraZone naraZone = new NaraZone(Locale.KOREA, ZoneId.of("Asia/Seoul").getId());

//        Enrollment enrollment = new Enrollment(metroId, citizenId, name, email, naraZone);
//        BuildCastleCommand build = new BuildCastleCommand(enrollment);


//        String castleId = super.getClient().post(url, build);
//        logger.info(castleId);
//        assertThat(result).isEqualTo("1");


//        CastleView castle = super.getClient().get(buildUrl("/castles/" + castleId), CastleView.class);
//
//        logger.info(castle.toString());
//        assertThat(castle).isNotNull();
    }

    @Test
    public void testFindCastle() throws Exception {
        //
        final String url = buildUrl("/castles/01");


//        CastleView castle = super.getClient().get(url, CastleView.class);
//        logger.info(castle.toString());
//
//        assertThat(castle).isNotNull();
//        assertThat(castle.getId()).isEqualTo("01");
//        assertThat(castle.getPrimaryEmail()).isEqualTo("tester@testing.co.kr");
    }

    @Test
    public void testFindAllCastles() throws Exception {
        //
        final String url = buildUrl("/castles");


//        List<CastleView> castles = super.getClient().getList(url, CastleView.class);
//
//        for (CastleView castle : castles) {
//            logger.info(castle.toString());
//        }
//        assertThat(castles.size()).isGreaterThan(0);
    }

    @Test
    public void testFindAllCastellans() throws Exception {
        //
        final String url = buildUrl("/castellans");


//        List<CastellanView> castellans = super.getClient().getList(url, CastellanView.class);
//
//        for (CastellanView castellan : castellans) {
//            logger.info(castellan.toString());
//        }
//        assertThat(castellans.size()).isGreaterThan(0);
    }

}
