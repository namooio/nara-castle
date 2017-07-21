package nara.castle.da.mongo;

import nara.castle.da.AbstractCastleRMStoreTest;
import nara.castle.domain.castlequery.model.CastellanRM;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
}
