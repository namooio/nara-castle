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

    private String originMetroId = "P0P";
    private String originCivilianId = "5YC1R@P0P";

    @Test
    public void test() {

        long castleSequence = castleStore.retrieveNextSequence(nationId);
        Assert.assertEquals(1, castleSequence);

        castleSequence = castleStore.retrieveNextSequence(nationId);
        Assert.assertEquals(2, castleSequence);

        castleSequence = castleStore.retrieveNextSequence(nationId);
        Assert.assertEquals(3, castleSequence);

        String castleId = CastleContext.getCastleIdBuilder().makeCastleId(nationId, castleSequence);

        Castle castle = new Castle(castleId, nationId, originMetroId, originCivilianId);
        castle.getCastellan().getEmails().add(new Email("kchuh@nextree.co.kr"));
        castleStore.create(castle);

        castle = castleStore.retrieveByEmail(nationId,"michael7557@gmail.com");
        Assert.assertNull(castle);

        castle = castleStore.retrieve(castleId);
        Assert.assertNotNull(castle);

        castle = castleStore.retrieveByEmail(nationId, "kchuh@nextree.co.kr");
        Assert.assertNotNull(castle);

        logger.debug("{}", castle);

        Castellan castellan = castle.getCastellan();
        castellan.getEmails().add(new Email("michael7557@gmail.com"));
        NameValueList nameValues = new NameValueList();
        nameValues.add("castellan", castellan.toJson());
        castle.setValues(nameValues);
        castleStore.update(castle);

        castle = castleStore.retrieveByEmail(nationId,"michael7557@gmail.com");
        Assert.assertNotNull(castle);

        castleStore.delete(castle);
        try {
            castleStore.retrieve(castleId);
            Assert.assertTrue(false);
        }
        catch (NonExistenceException e) {
            Assert.assertTrue(true);
        }
    }
}
