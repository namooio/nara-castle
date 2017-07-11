package namoo.nara.castle.adapter.rest;

import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.spec.command.castle.BuildCastleCommand;
import namoo.nara.share.domain.granule.Name;
import namoo.nara.share.domain.granule.NaraZone;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.springweb.SpringWebRestClient;

import java.time.ZoneId;
import java.util.Locale;

public class CastleRestAdapterTest {
    //
    public static void main(String[] args) {
        //
        NaraRestClient naraRestClient = new SpringWebRestClient("http://localhost:9030");
        CastleRestAdapter castleRestAdapter = new CastleRestAdapter(naraRestClient);

        MetroEnrollment enrollment = new MetroEnrollment(
                "P0P",
                "C1@POP",
                new Name(Locale.KOREAN, "기철", "허"),
                "kchuh@nextree.co.kr",
                new NaraZone(Locale.KOREA, ZoneId.of("Asia/Seoul"))
        );
        castleRestAdapter.buildCastle(new BuildCastleCommand(enrollment));
    }
}
