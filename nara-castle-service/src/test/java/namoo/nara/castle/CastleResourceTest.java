package namoo.nara.castle;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.share.domain.granule.Name;
import namoo.nara.share.domain.granule.NaraZone;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

public class CastleResourceTest extends AbstractCastleApplicationTests {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test() throws InterruptedException {
        //
        String castleId;
        castleId = getCastleRestAdapter().enrollMetro(new EnrollMetroCommand(new MetroEnrollment("P0P", "C1@POP", new Name(Locale.KOREAN, "기철", "허"), "kchuh@nextree.co.kr", new NaraZone(Locale.KOREA, ZoneId.of("Asia/Seoul")))));
        castleId = getCastleRestAdapter().enrollMetro(new EnrollMetroCommand(new MetroEnrollment("P0P", "C2@POP", new Name(Locale.KOREAN, "태국", "송"), "tsong@nextree.co.kr", new NaraZone(Locale.KOREA, ZoneId.of("Asia/Seoul")))));
        castleId = getCastleRestAdapter().enrollMetro(new EnrollMetroCommand(new MetroEnrollment("P0P", "C3@POP", new Name(Locale.KOREAN, "형구", "강"), "hkkang@nextree.co.kr", new NaraZone(Locale.KOREA, ZoneId.of("Asia/Seoul")))));
        castleId = getCastleRestAdapter().enrollMetro(new EnrollMetroCommand(new MetroEnrollment("P0P", "C4@POP", new Name(Locale.KOREAN, "지수", "서"), "jsseo@nextree.co.kr", new NaraZone(Locale.KOREA, ZoneId.of("Asia/Seoul")))));
        castleId = getCastleRestAdapter().enrollMetro(new EnrollMetroCommand(new MetroEnrollment("P0P", "C5@POP", new Name(Locale.KOREAN, "인영", "이"), "iylee@nextree.co.kr", new NaraZone(Locale.KOREA, ZoneId.of("Asia/Seoul")))));
        castleId = getCastleRestAdapter().enrollMetro(new EnrollMetroCommand(new MetroEnrollment("P0P", "R1@POP", new Name(Locale.KOREAN, "지용", "정"), "jyjung@test.com", new NaraZone(Locale.KOREA, ZoneId.of("Asia/Seoul")))));
//        logger.debug("{}", castleId);

        Thread.sleep(3000);

        List<Castle> castles = getCastleRestAdapter().findCastles();
        logger.debug("{}", castles);
    }

}
