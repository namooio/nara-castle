package nara.castle.adapter.rest;

import nara.share.domain.granule.Name;
import nara.share.domain.granule.NaraZone;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.springweb.SpringWebRestClient;
import nara.castle.domain.castle.entity.MetroEnrollment;
import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.command.EnrollMetroCommand;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class CastleRestAdapterTest {
    //
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //
        NaraRestClient naraRestClient = new SpringWebRestClient("http://localhost:9000");
        CastleRestAdapter castleRestAdapter = new CastleRestAdapter(naraRestClient);

        for (int i = 0 ; i < 100 ; i++) {
            String seq = "0" + i;

            String metroId = "P" + seq;
            String civilianId = "C1" + metroId;

            String metroId2 = "Q" + seq;
            String civilianId2 = "C1" + metroId2;

            MetroEnrollment enrollment = new MetroEnrollment(
                    metroId,
                    civilianId,
                    new Name(Locale.KOREAN, "기철", "허"),
                    "kchuh@nextree.co.kr",
                    new NaraZone(Locale.KOREA, "Asia/Seoul")
            );

            castleRestAdapter.buildCastle(new BuildCastleCommand(enrollment)).thenAccept(response -> {
                String castleId = (String) response;
                castleRestAdapter.enrollMetro(castleId, new EnrollMetroCommand(castleId, new MetroEnrollment(metroId2, civilianId2, new Name(Locale.KOREAN, "기철", "허"), "kchuh@nextree.co.kr", new NaraZone(Locale.KOREA, "Asia/Seoul"))));
            }).toCompletableFuture().get();
        }


    }
}
