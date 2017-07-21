package nara.castle.actor.akka;

import akka.actor.ActorRef;
import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castle.entity.Enrollment;
import nara.share.actor.akka.NaraActorConst;
import nara.share.actor.akka.message.ActorResponse;
import nara.share.actor.akka.util.ActorNameUtil;
import nara.share.domain.granule.Email;
import nara.share.domain.granule.Name;
import nara.share.domain.granule.NaraZone;
import nara.share.exception.NaraException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.Await;

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

        ActorRef castleSupervisorActor = getCastleSupervisorActor();
        logger.debug("{}", castleSupervisorActor.path());

        getCastleSupervisorActor().tell(buildCastleCommand, getTestProbeActor());
        ActorResponse<Castellan> castellan = getTestProbe().expectMsgClass(ActorResponse.class);
        logger.debug("{}", castellan.get());

        String castellanId = castellan.get().getId();

        String castleActorName = ActorNameUtil.requestPersistentActorName(castellanId, Castellan.class);
        try {
            ActorRef actorRef = Await.result(getActorSystem().actorSelection(getCastleSupervisorActor().path().child(castleActorName)).resolveOne(NaraActorConst.DEFAULT_TIMEOUT), NaraActorConst.DEFAULT_TIMEOUT.duration());
            logger.debug("{}", actorRef.path());
        } catch (Exception e) {
            throw new NaraException(String.format("Fail to request actor by name[%s].", castleActorName), e);
        }
    }
}
