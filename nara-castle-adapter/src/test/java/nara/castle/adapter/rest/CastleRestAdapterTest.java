package nara.castle.adapter.rest;

import nara.castle.domain.castle.command.EnrollmentCommand;
import nara.castle.domain.castle.entity.Enrollment;
import nara.share.domain.granule.Name;
import nara.share.domain.granule.NaraZone;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.springweb.SpringWebRestClient;
import nara.castle.domain.castle.command.BuildCastleCommand;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class CastleRestAdapterTest {
    //
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //
        NaraRestClient naraRestClient = new SpringWebRestClient("http://localhost:9000");
        CastleRestAdapter castleRestAdapter = new CastleRestAdapter(naraRestClient);

        Enrollment enrollment = new Enrollment(
                "P0P",
                "C1@P0P",
                new Name(Locale.KOREAN, "기철", "허"),
                "kchuh@nextree.co.kr",
                new NaraZone(Locale.KOREA, "Asia/Seoul")
        );

        castleRestAdapter.buildCastle(new BuildCastleCommand(enrollment)).thenAccept(response -> {
            String castleId = (String) response;
            castleRestAdapter.enrollMetro(castleId, new EnrollmentCommand(castleId, new Enrollment("Q0P", "C1@Q0P", new Name(Locale.KOREAN, "기철", "허"), "kchuh@nextree.co.kr", new NaraZone(Locale.KOREA, "Asia/Seoul"))));
        }).toCompletableFuture().get();

    }
}
