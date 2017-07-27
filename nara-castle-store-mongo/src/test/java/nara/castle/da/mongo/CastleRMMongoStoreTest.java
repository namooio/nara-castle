package nara.castle.da.mongo;

import nara.castle.da.AbstractCastleRMStoreTest;
import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castle.entity.Enrollment;
import nara.castle.domain.castlequery.model.CastellanRM;
import nara.share.domain.IdName;
import nara.share.domain.granule.Email;
import nara.share.domain.granule.Name;
import nara.share.domain.granule.NaraZone;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

public class CastleRMMongoStoreTest extends AbstractCastleRMStoreTest {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {
        //
        CastellanRM castellan = CastellanRM.getSample();
        getCastellanRMStore().create(castellan);

        castellan = getCastellanRMStore().retrieve(castellan.getId());
        logger.debug("{}", castellan);

        List<CastellanRM> castellans = getCastellanRMStore().retrieveAll();
        Assert.assertEquals(1, castellans.size());

        castellan.setDisplayName("홍길동");
        getCastellanRMStore().update(castellan);

        castellan = getCastellanRMStore().retrieve(castellan.getId());
        Assert.assertEquals("홍길동", castellan.getDisplayName());

        Assert.assertTrue(getCastellanRMStore().exists(castellan.getId()));

        getCastellanRMStore().delete(castellan.getId());

        Assert.assertFalse(getCastellanRMStore().exists(castellan.getId()));
    }

    @Test
    public void test2() {
        //
        IdName metro = new IdName("1, ", "Nextree");
        getCastellanRMStore().create(new CastellanRM(new Castellan(new Enrollment(metro, "1", new Name(Locale.KOREAN, "기1", "허"), new Email("kchuh1@nextree.co.kr"), NaraZone.getSample()))));
        getCastellanRMStore().create(new CastellanRM(new Castellan(new Enrollment(metro, "2", new Name(Locale.KOREAN, "기2", "허"), new Email("kchuh2@nextree.co.kr"), NaraZone.getSample()))));
        getCastellanRMStore().create(new CastellanRM(new Castellan(new Enrollment(metro, "3", new Name(Locale.KOREAN, "기3", "허"), new Email("kchuh3@nextree.co.kr"), NaraZone.getSample()))));
        getCastellanRMStore().create(new CastellanRM(new Castellan(new Enrollment(metro, "4", new Name(Locale.KOREAN, "기4", "허"), new Email("kchuh4@nextree.co.kr"), NaraZone.getSample()))));
        getCastellanRMStore().create(new CastellanRM(new Castellan(new Enrollment(metro, "5", new Name(Locale.KOREAN, "기5", "허"), new Email("kchuh5@nextree.co.kr"), NaraZone.getSample()))));
        getCastellanRMStore().create(new CastellanRM(new Castellan(new Enrollment(metro, "6", new Name(Locale.KOREAN, "기6", "허"), new Email("kchuh6@nextree.co.kr"), NaraZone.getSample()))));

        getCastellanRMStore().create(new CastellanRM(new Castellan(new Enrollment(metro, "7", new Name(Locale.KOREAN, "기7", "허"), new Email("kchuh7@nextree.co.kr"), NaraZone.getSample()))));
        getCastellanRMStore().create(new CastellanRM(new Castellan(new Enrollment(metro, "8", new Name(Locale.KOREAN, "기8", "허"), new Email("kchuh8@nextree.co.kr"), NaraZone.getSample()))));
        getCastellanRMStore().create(new CastellanRM(new Castellan(new Enrollment(metro, "9", new Name(Locale.KOREAN, "기9", "허"), new Email("kchuh9@nextree.co.kr"), NaraZone.getSample()))));
        getCastellanRMStore().create(new CastellanRM(new Castellan(new Enrollment(metro, "10", new Name(Locale.KOREAN, "기10", "허"), new Email("kchuh10@nextree.co.kr"), NaraZone.getSample()))));
        getCastellanRMStore().create(new CastellanRM(new Castellan(new Enrollment(metro, "11", new Name(Locale.KOREAN, "기11", "허"), new Email("kchuh11@nextree.co.kr"), NaraZone.getSample()))));

        List<CastellanRM> castellanRMS = getCastellanRMStore().retrieveAll();
        castellanRMS.forEach(castellanRM -> System.out.println(castellanRM));

        castellanRMS = getCastellanRMStore().retrieve(null, 3);
        castellanRMS.forEach(castellanRM -> System.out.println(castellanRM));

        CastellanRM lastCastellan = castellanRMS.get(2);
        String lastCastellanId = lastCastellan.getId();

        castellanRMS = getCastellanRMStore().retrieve(lastCastellanId, 3);
        castellanRMS.forEach(castellanRM -> System.out.println(castellanRM));

        castellanRMS = getCastellanRMStore().retrieve(0, 3);
        castellanRMS.forEach(castellanRM -> System.out.println(castellanRM));

        System.out.println(getCastellanRMStore().count());

    }
}
