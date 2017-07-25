package nara.castle.adapter.rest;

import nara.castle.domain.castle.command.BuildCastleCommand;
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

        for (int i = 100 ; i < 102 ; i++) {
            String seq = "0" + i;

            String metroId = "P" + seq;
            String civilianId = "C1" + metroId;

            String metroId2 = "Q" + seq;
            String civilianId2 = "C1" + metroId2;

            Enrollment enrollment = new Enrollment(metroId, civilianId,
                    new Name(Locale.KOREAN, "기철", "허"),
                    new Email("kchuh@nextree.co.kr"),
                    new NaraZone(Locale.KOREA, "Asia/Seoul")
            );

            String castellanId = castleRestAdapter.buildCastle(new BuildCastleCommand(enrollment)).toCompletableFuture().get();

//            while(!castleRestAdapter.existsCastellan(castellanId).toCompletableFuture().get()) {
//                //
//                Thread.sleep(1000);
//            }
//
//            CastellanRM castellanRM = castleRestAdapter.findCastellan(castellanId).toCompletableFuture().get();
//            Contact contact = castellanRM.getContact();
//            contact.getEmails().add(new Email("michael7557@naver.com"));
//
//            NameValueList nameValues = new NameValueList();
//            nameValues.add("displayName", "허허허");
//            nameValues.add("primaryEmail", "michael7557@gmail.com");
//            nameValues.add("contact", contact.toJson());
//
//            ModifyCastellanCommand modifyCastellanCommand = new ModifyCastellanCommand(castellanId, nameValues);
//            castleRestAdapter.modifyCastellan(castellanId, modifyCastellanCommand).toCompletableFuture().get();
//
//            enrollment = new Enrollment(metroId2, civilianId2,
//                    new Name(Locale.KOREAN, "유유", "호"),
//                    new Email("kchuh@me.com"),
//                    new NaraZone(Locale.KOREA, "Asia/Seoul")
//            );
//            AddEnrollmentCommand addEnrollmentCommand = new AddEnrollmentCommand(castellanId, enrollment);
//            castleRestAdapter.addEnrollment(castellanId, addEnrollmentCommand).toCompletableFuture().get();
//
//            Boolean enrolled = castleRestAdapter.checkEnrolled(castellanId, metroId).toCompletableFuture().get();
//            System.out.println(enrolled);
//
//            WithdrawMetroCommand withdrawMetroCommand = new WithdrawMetroCommand(castellanId, metroId, civilianId);
//            castleRestAdapter.withdrawMetro(castellanId, withdrawMetroCommand).toCompletableFuture().get();
//
//            enrolled = castleRestAdapter.checkEnrolled(castellanId, metroId).toCompletableFuture().get();
//            System.out.println(enrolled);
        }



    }
}
