package nara.castle.adapter.rest;

import namoo.nara.share.domain.granule.Name;
import namoo.nara.share.domain.granule.NaraZone;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.springweb.SpringWebRestClient;
import nara.castle.domain.entity.MetroEnrollment;
import nara.castle.domain.spec.command.castle.BuildCastleCommand;
import nara.castle.domain.spec.command.castle.EnrollMetroCommand;

import java.util.Locale;

public class CastleRestAdapterTest {
    //
    public static void main(String[] args) throws InterruptedException {
        //
        NaraRestClient naraRestClient = new SpringWebRestClient("http://localhost:9000");
        CastleRestAdapter castleRestAdapter = new CastleRestAdapter(naraRestClient);

        MetroEnrollment enrollment = new MetroEnrollment(
                "P0Q",
                "C1@POQ",
                new Name(Locale.KOREAN, "기철", "허"),
                "kchuh@nextree.co.kr",
                new NaraZone(Locale.KOREA, "Asia/Seoul")
        );

        castleRestAdapter.buildCastle(new BuildCastleCommand(enrollment)).thenAccept(response -> {
            String castleId = (String) response;
            castleRestAdapter.enrollMetro(castleId, new EnrollMetroCommand(castleId, new MetroEnrollment("Q0Q", "C1@Q0Q", new Name(Locale.KOREAN, "기철", "허"), "kchuh@nextree.co.kr", new NaraZone(Locale.KOREA, "Asia/Seoul"))));
        });

        Thread.sleep(10000);

    }
}
