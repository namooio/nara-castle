package namoo.nara.castle;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castle.BuildCastleCommand;
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
        String castleId = getCastleRestAdapter().buildCastle(new BuildCastleCommand(new MetroEnrollment("P0P", "C1@POP", new Name(Locale.KOREAN, "기철", "허"), "kchuh@nextree.co.kr", new NaraZone(Locale.KOREA, ZoneId.of("Asia/Seoul")))));
        getCastleRestAdapter().enrollMetro(castleId, new EnrollMetroCommand(castleId, new MetroEnrollment("Q0P", "C1@Q0P", new Name(Locale.KOREAN, "기철", "허"), "kchuh@nextree.co.kr", new NaraZone(Locale.KOREA, ZoneId.of("Asia/Seoul")))));

        Thread.sleep(3000);

        List<Castle> castles = getCastleRestAdapter().findCastles();
        logger.debug("{}", castles);
    }

}
