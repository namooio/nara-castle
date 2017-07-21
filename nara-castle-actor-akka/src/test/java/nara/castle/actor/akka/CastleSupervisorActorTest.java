package nara.castle.actor.akka;

import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castle.entity.Enrollment;
import nara.share.actor.akka.result.ActorResult;
import nara.share.domain.granule.Email;
import nara.share.domain.granule.Name;
import nara.share.domain.granule.NaraZone;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class CastleSupervisorActorTest extends AbstractCastleActorTest {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test() throws InterruptedException {
        //
        String metroId = "P0P";
        String civilianId = "C1@P0P";
        Enrollment enrollment = new Enrollment(metroId, civilianId,
                new Name(Locale.KOREAN, "기철", "허"),
                new Email("kchuh@nextree.co.kr"),
                new NaraZone(Locale.KOREA, "Asia/Seoul")
        );

        BuildCastleCommand buildCastleCommand = new BuildCastleCommand(enrollment);

        getCastleSupervisorActor().tell(buildCastleCommand, getTestProbeActor());
        ActorResult<Castellan> castellan = getTestProbe().expectMsgClass(ActorResult.class);
        logger.debug("{}", castellan.get());
    }
}
