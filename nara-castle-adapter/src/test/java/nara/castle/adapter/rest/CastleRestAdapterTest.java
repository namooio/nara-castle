package nara.castle.adapter.rest;

import nara.share.domain.granule.Name;
import nara.share.domain.granule.NaraZone;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.springweb.SpringWebRestClient;
import nara.castle.domain.entity.MetroEnrollment;
import nara.castle.domain.spec.command.castle.BuildCastleCommand;
import nara.castle.domain.spec.command.castle.EnrollMetroCommand;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class CastleRestAdapterTest {
    //
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //
        NaraRestClient naraRestClient = new SpringWebRestClient("http://localhost:9000");
        CastleRestAdapter castleRestAdapter = new CastleRestAdapter(naraRestClient);

        MetroEnrollment enrollment = new MetroEnrollment(
                "P0P",
                "C1@P0P",
                new Name(Locale.KOREAN, "기철", "허"),
                "kchuh@nextree.co.kr",
                new NaraZone(Locale.KOREA, "Asia/Seoul")
        );

        castleRestAdapter.buildCastle(new BuildCastleCommand(enrollment)).thenAccept(response -> {
            String castleId = (String) response;
            castleRestAdapter.enrollMetro(castleId, new EnrollMetroCommand(castleId, new MetroEnrollment("Q0P", "C1@Q0P", new Name(Locale.KOREAN, "기철", "허"), "kchuh@nextree.co.kr", new NaraZone(Locale.KOREA, "Asia/Seoul"))));
        }).toCompletableFuture().get();

    }
}
