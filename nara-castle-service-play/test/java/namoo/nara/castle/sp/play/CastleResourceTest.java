package namoo.nara.castle.sp.play;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castle.BuildCastleCommand;
import namoo.nara.castle.domain.view.CastellanView;
import namoo.nara.castle.domain.view.CastleView;
import namoo.nara.castle.sp.play.shared.AbstractCastleApplicationTester;
import namoo.nara.castle.sp.play.shared.ResourceTestClient;
import namoo.nara.share.domain.granule.Name;
import namoo.nara.share.domain.granule.NaraZone;
import org.junit.Rule;
import org.junit.Test;

import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

public class CastleResourceTest extends AbstractCastleApplicationTester {
    //
//    final private ResourceTestClient client = new ResourceTestClient(this.testServer.port());

    @Test
    public void testBuildCastle() throws Exception {
        //
        final String url = buildUrl("/castles");

        final String metroId = "POP";
        final String civilianId = "02@POP";
        final Name name = new Name(Locale.KOREA, "gildong", "hong");
        final String email = "gildong@testing.co.kr";
        final NaraZone naraZone = new NaraZone(Locale.KOREA, ZoneId.of("Asia/Seoul").getId());

        MetroEnrollment enrollment = new MetroEnrollment(metroId, civilianId, name, email, naraZone);
        BuildCastleCommand build = new BuildCastleCommand(enrollment);


        String castleId = super.client.post(url, build);
        logger.info(castleId);
//        assertThat(result).isEqualTo("1");


        CastleView castle = super.client.get(buildUrl("/castles/" + castleId), CastleView.class);

        logger.info(castle.toString());
        assertThat(castle).isNotNull();
    }

    @Test
    public void testFindCastle() throws Exception {
        //
        final String url = buildUrl("/castles/01");

        CastleView castle = super.client.get(url, CastleView.class);
        logger.info(castle.toString());

        assertThat(castle).isNotNull();
        assertThat(castle.getId()).isEqualTo("01");
        assertThat(castle.getPrimaryEmail()).isEqualTo("tester@testing.co.kr");
    }

    @Test
    public void testFindAllCastles() throws Exception {
        //
        final String url = buildUrl("/castles");


        List<CastleView> castles = super.client.getList(url, CastleView.class);


        for (CastleView castle : castles) {
            logger.info(castle.toString());
        }
        assertThat(castles.size()).isGreaterThan(0);
    }

    @Test
    public void testFindAllCastellans() throws Exception {
        //
        final String url = buildUrl("/castellans");

        List<CastellanView> castellans = super.client.getList(url, CastellanView.class);

        for (CastellanView castellan : castellans) {
            logger.info(castellan.toString());
        }
        assertThat(castellans.size()).isGreaterThan(0);
    }
}
