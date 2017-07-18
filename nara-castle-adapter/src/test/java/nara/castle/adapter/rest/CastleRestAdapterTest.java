package nara.castle.adapter.rest;

import nara.castle.adapter.rest.CastleRestAdapter;
import nara.castle.domain.entity.MetroEnrollment;
import nara.castle.domain.spec.command.castle.BuildCastleCommand;
import nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.share.domain.granule.Name;
import namoo.nara.share.domain.granule.NaraZone;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.springweb.SpringWebRestClient;

import java.util.Locale;

public class CastleRestAdapterTest {
    //
    public static void main(String[] args) {
        //
        NaraRestClient naraRestClient = new SpringWebRestClient("http://localhost:9000");
        CastleRestAdapter castleRestAdapter = new CastleRestAdapter(naraRestClient);

        MetroEnrollment enrollment = new MetroEnrollment(
                "P0P",
                "C1@POP",
                new Name(Locale.KOREAN, "기철", "허"),
                "kchuh@nextree.co.kr",
                new NaraZone(Locale.KOREA, "Asia/Seoul")
        );
        String castleId = castleRestAdapter.buildCastle(new BuildCastleCommand(enrollment));

        castleRestAdapter.enrollMetro(castleId, new EnrollMetroCommand(castleId, new MetroEnrollment("Q0P", "C1@Q0P", new Name(Locale.KOREAN, "기철", "허"), "kchuh@nextree.co.kr", new NaraZone(Locale.KOREA, "Asia/Seoul"))));
    }
}
