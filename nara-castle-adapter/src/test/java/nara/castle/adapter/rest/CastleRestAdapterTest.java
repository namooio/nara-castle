package nara.castle.adapter.rest;

import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.command.WithdrawMetroCommand;
import nara.castle.domain.castle.entity.Enrollment;
import nara.share.domain.granule.Email;
import nara.share.domain.granule.Name;
import nara.share.domain.granule.NaraZone;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.springweb.SpringWebRestClient;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class CastleRestAdapterTest {
    //
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //
        NaraRestClient naraRestClient = new SpringWebRestClient("http://localhost:9000");
        CastleRestAdapter castleRestAdapter = new CastleRestAdapter(naraRestClient);

//        for (int i = 101 ; i < 102 ; i++) {
//            String seq = "0" + i;
//
//            String metroId = "P" + seq;
//            String civilianId = "C1" + metroId;
//
//            String metroId2 = "Q" + seq;
//            String civilianId2 = "C1" + metroId2;
//
//            Enrollment enrollment = new Enrollment(metroId, civilianId,
//                    new Name(Locale.KOREAN, "기철", "허"),
//                    new Email("kchuh@nextree.co.kr"),
//                    new NaraZone(Locale.KOREA, "Asia/Seoul")
//            );
//
//            castleRestAdapter.buildCastle(new BuildCastleCommand(enrollment)).thenAccept(response -> {}).toCompletableFuture().get();
//        }

//        WithdrawMetroCommand withdrawMetroCommand = new WithdrawMetroCommand("a4d19748-1e7d-46a0-886a-bd348df30eed", "P00", "C1P00");
//        castleRestAdapter.withdrawMetro("a4d19748-1e7d-46a0-886a-bd348df30eed", withdrawMetroCommand).toCompletableFuture().get();

    }
}
