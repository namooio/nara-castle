package nara.castle.adapter.rest;

import nara.castle.domain.castle.command.BuildCastleCommand;
import nara.castle.domain.castle.command.ModifyCastellanCommand;
import nara.castle.domain.castle.entity.Contact;
import nara.castle.domain.castle.entity.Enrollment;
import nara.castle.domain.castlequery.model.CastellanRM;
import nara.castle.domain.castlequery.query.ExistenceCheckQuery;
import nara.castle.domain.castlequery.query.FindCastellanQuery;
import nara.share.domain.IdName;
import nara.share.domain.NameValueList;
import nara.share.domain.granule.*;
import nara.share.restclient.NaraRestClient;
import nara.share.restclient.springweb.SpringWebRestClient;

import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class CastleRestAdapterTest {
    //
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //
        NaraRestClient naraRestClient = new SpringWebRestClient("http://localhost:9000");
        CastleRestAdapter castleRestAdapter = new CastleRestAdapter(naraRestClient);

        String metroId = UUID.randomUUID().toString();
        String metroName = "Nextree";
        for (int i = 0 ; i < 100 ; i++) {

            IdName metro = new IdName(metroId, metroName);
            String citizenId = UUID.randomUUID().toString();

            Enrollment enrollment = new Enrollment(metro, citizenId,
                    new Name(Locale.KOREAN, "기철" + i, "허"),
                    new Email("kchuh" + i + "@nextree.co.kr"),
                    new NaraZone(Locale.KOREA, "Asia/Seoul")
            );

            String castellanId = castleRestAdapter.buildCastle(new BuildCastleCommand(enrollment)).toCompletableFuture().get();

            while(!castleRestAdapter.existsCastellan(new ExistenceCheckQuery(castellanId)).toCompletableFuture().get()) {
                //
                System.out.println("not exists. re-check");
            }
            FindCastellanQuery findCastellanQuery = new FindCastellanQuery(castellanId);
            CastellanRM castellanRM = castleRestAdapter.findCastellan(findCastellanQuery).toCompletableFuture().get();

            Contact contact = castellanRM.getContact();
            contact.getEmails().add(new Email("michael7557"+i+"@gmail.com"));
            contact.getAddresses().add(Address.getUSSample());
            contact.getAddresses().add(Address.getKoreanSample());

            String countryCode = "82";
            String carrierCode = "010";
            String frontNumber = "1235";
            String backNumber = String.format("%04d", i);

            Phone sample = new Phone(countryCode, carrierCode, frontNumber, backNumber);
            sample.setCategory(Phone.Category.Mobile);

            contact.getPhones().add(sample);

            countryCode = "82";
            carrierCode = "02";
            frontNumber = "2225";
            backNumber = String.format("%04d", i);

            sample = new Phone(countryCode, carrierCode, frontNumber, backNumber);
            sample.setCategory(Phone.Category.Office);

            contact.getPhones().add(Phone.getSecondSample());

            contact.getNames().add(Locale.ENGLISH, "michael"+i, "Huh");

            NameValueList nameValues = new NameValueList();
            nameValues.add("contact", contact.toJson());

            ModifyCastellanCommand modifyCastellanCommand = new ModifyCastellanCommand(castellanId, nameValues);
            castleRestAdapter.modifyCastellan(modifyCastellanCommand).toCompletableFuture().get();

        }

    }

}
