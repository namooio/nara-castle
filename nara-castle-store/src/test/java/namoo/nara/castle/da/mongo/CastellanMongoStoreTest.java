package namoo.nara.castle.da.mongo;

import namoo.nara.castle.domain.entity.Castellan;
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

import java.util.UUID;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CastellanMongoStoreTest.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = "namoo.nara.castle.da.mongo")
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CastellanMongoStoreTest {

    @Autowired
    private CastellanMongoStore castellanMongoStore;

    @Test
    public void storeCrudTest() {
        //
        String id = UUID.randomUUID().toString();

        // create test
        Castellan castellan = Castellan.newInstance(id, "허기철");
        castellanMongoStore.create(castellan);

        // retrieve test
        castellan = castellanMongoStore.retrieve(id);
        Assert.assertEquals("허기철", castellan.getDisplayName());

        // update test
        castellan.setDisplayName("Michael");
        castellanMongoStore.update(castellan);
        castellan = castellanMongoStore.retrieve(id);
        Assert.assertEquals("Michael", castellan.getDisplayName());

        // delete test
        castellanMongoStore.delete(id);
        try {
            castellanMongoStore.retrieve(id);
        }
        catch (NonExistenceException e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }
}
