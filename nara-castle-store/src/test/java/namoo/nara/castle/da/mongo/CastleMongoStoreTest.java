package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.CastleStoreTestApplication;
import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.domain.granule.Email;
import namoo.nara.share.exception.store.NonExistenceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CastleStoreTestApplication.class)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CastleMongoStoreTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CastleStore castleStore;

    private String nationId = "P";

    private String metroId = "P0P";
    private String civilianId = "5YC1R@P0P";

    @Test
    public void test() {

        long castleSequence = castleStore.retrieveNextSequence(nationId);
        String castleId = CastleContext.getCastleIdBuilder().makeCastleId(nationId, castleSequence);

        Castle castle = new Castle(nationId, castleId);
        Castellan castellan = new Castellan(new Email("kchuh@nextree.co.kr"));
        castle.setCastellan(castellan);

        castleStore.create(castle);

        Assert.assertNull(castleStore.retrieveByEmail(nationId, "michael7557@gmail.com"));

        castle = castleStore.retrieve(castleId);
        logger.debug("{}", castle);

        castellan = castle.getCastellan();
        castellan.getEmails().add(new Email("michael7557@gmail.com"));
        castellan.addJoinedMetro(metroId, civilianId);

        castle.setValues(new NameValueList("castellan", castellan.toJson()));
        castleStore.update(castle);

        Assert.assertNotNull(castleStore.retrieveByEmail(nationId,"kchuh@nextree.co.kr"));
        Assert.assertNotNull(castleStore.retrieveByEmail(nationId,"michael7557@gmail.com"));

        castleStore.delete(castle);
        try {
            castleStore.retrieve(castleId);
        }
        catch (NonExistenceException e) {
            Assert.assertTrue(true);
        }
    }
}
