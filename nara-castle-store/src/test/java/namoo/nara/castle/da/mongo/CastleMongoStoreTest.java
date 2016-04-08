package namoo.nara.castle.da.mongo;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.OpenState;
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
import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
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
        //
        String id = UUID.randomUUID().toString();

        // create test
        Castle castle = Castle.newInstance(id, "허기철", Locale.US);
        castle.setBuildTime(System.currentTimeMillis());
        castleStore.create(castle);

        // retrieve test
        castle = castleStore.retrieve(id);
        Assert.assertEquals("허기철", castle.getName());
        Assert.assertEquals(Locale.US, castle.getLocale());
        Assert.assertEquals(OpenState.Ready, castle.getState());

        // update test
        castle.setLocale(Locale.KOREA);
        castleStore.update(castle);
        castle = castleStore.retrieve(id);
        Assert.assertEquals("허기철", castle.getName());
        Assert.assertEquals(Locale.KOREA, castle.getLocale());
        Assert.assertEquals(OpenState.Ready, castle.getState());

        // delete test
        castleStore.delete(id);
        try {
            castleStore.retrieve(id);
        }
        catch (NonExistenceException e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }
}
