package nara.castle.da.mongo;

import nara.castle.da.AbstractCastleRMStoreTest;
import nara.castle.domain.castlequery.model.CastellanRM;
import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.model.UnitPlateRM;
import nara.share.domain.granule.Email;
import nara.share.domain.granule.Name;
import nara.share.domain.granule.Phone;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UnitPlateRMMongoStoreTest extends AbstractCastleRMStoreTest {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {
        //
        CastellanRM castellan = CastellanRM.getSample();

        String castellanId = castellan.getId();
        UnitPlateRM unitPlateRMName1 = new UnitPlateRM(castellanId, new Name(Locale.KOREAN, "기철", "허"));
        UnitPlateRM unitPlateRMName2 = new UnitPlateRM(castellanId, new Name(Locale.ENGLISH, "michael", "Huh"));
        UnitPlateRM unitPlateRMPhone = new UnitPlateRM(castellanId, new Phone("82", "10", "6325", "7557"));
        UnitPlateRM unitPlateRMEmail1 = new UnitPlateRM(castellanId, new Email("kchuh@nextree.co.kr"));
        UnitPlateRM unitPlateRMEmail2 = new UnitPlateRM(castellanId, new Email("michael7557@gmail.com"));

        List<UnitPlateRM> unitPlates = new ArrayList<>();
        unitPlates.add(unitPlateRMName1);
        unitPlates.add(unitPlateRMName2);
        unitPlates.add(unitPlateRMPhone);
        unitPlates.add(unitPlateRMEmail1);
        unitPlates.add(unitPlateRMEmail2);

        getUnitPlateRMStore().create(unitPlates);

        unitPlates = getUnitPlateRMStore().retrieveByCastellanId(castellanId);
        Assert.assertEquals(5, unitPlates.size());

        logger.debug("{}", unitPlates);

        unitPlates = getUnitPlateRMStore().retrieve(KeyAttr.Email, "co", null, 10);
        Assert.assertEquals(2, unitPlates.size());


        unitPlates = getUnitPlateRMStore().retrieve(null, "6325", null, 10);
        Assert.assertEquals(1, unitPlates.size());


        logger.debug("{}", unitPlates);

        Assert.assertTrue(getUnitPlateRMStore().exists(KeyAttr.EngName, "michael Huh"));

        getUnitPlateRMStore().retrieve(null, null, null, 100);
    }
}
