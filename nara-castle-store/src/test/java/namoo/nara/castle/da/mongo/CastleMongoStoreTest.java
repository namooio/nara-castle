package namoo.nara.castle.da.mongo;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.share.exception.store.NonExistenceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Locale;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CastleMongoStoreTest.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = "namoo.nara.castle.da.mongo")
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CastleMongoStoreTest {

    @Autowired
    private CastleStore castleStore;

    @Test
    public void storeCrudTest() {
        // create test
        long castleSequence = castleStore.retrieveNextSequence();
        Assert.assertEquals(1, castleSequence);
        castleSequence = castleStore.retrieveNextSequence();
        Assert.assertEquals(2, castleSequence);

        Castle castle = Castle.getSample();
        String id = castle.getId();
        System.out.println("id : " + id);
        castleStore.create(castle);

        // retrieve test
        castle = castleStore.retrieveByEmail("kchuh@nextree.co.kr");
        Assert.assertNotNull(castle);

        castle = castleStore.retrieveByEmail("michael7557@gmail.com");
        Assert.assertNull(castle);

        castle = castleStore.retrieve(id);
        Assert.assertEquals(Locale.KOREA, castle.getLocale());
        System.out.println(castle);

        // update test
        castle.setLocale(Locale.US);
        castleStore.update(castle);
        castle = castleStore.retrieve(id);
        Assert.assertEquals(Locale.US, castle.getLocale());
        System.out.println(castle);

        // delete test
        castleStore.delete(id);
        try {
            castleStore.retrieve(id);
            Assert.assertTrue(false);
        }
        catch (NonExistenceException e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }
}
