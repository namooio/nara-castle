package namoo.nara.castle.da.mongo;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.LoginIdType;
import namoo.nara.castle.domain.service.data.CastellanCdo;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.share.exception.store.NonExistenceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CastellanMongoStoreTest.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = "namoo.nara.castle.da.mongo")
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CastellanMongoStoreTest {

    @Autowired
    private CastellanStore castellanStore;

    @Test
    public void storeCrudTest() {
        //
        String id = UUID.randomUUID().toString();
        String password = "Non-encryption";
        String email = "jyjung@nextree.co.kr";

        // create test
        Castellan castellan = Castellan.newInstance(id, new CastellanCdo(email, password));
        castellanStore.create(castellan);

        // retrieve test
        castellan = castellanStore.retrieve(id);
        Assert.assertEquals(password, castellan.getCredential().getPassword());
        Assert.assertEquals(1, castellan.getEmailsCount());
        System.out.println(castellan);

        // retreive all test
        Assert.assertEquals(1, castellanStore.retrieveAll().size());

        // update test
        String metroId = "metro1";
        String citizenId = "metro1-citizen";
        castellan.verifyEmail(email);
        castellan.addJoinedMetro(metroId, citizenId);
        castellan.addAccount("jyjung", LoginIdType.Username);
        castellanStore.update(castellan);

        // retrieve by loginId & Type
        castellan = castellanStore.retrieveByLoginIdAndLoginIdType(email, LoginIdType.Email);
        Assert.assertEquals(2, castellan.getAccounts().size());
        Assert.assertEquals(1, castellan.getJoinedMetrosCount());
        Assert.assertEquals(metroId, castellan.getJoinedMetros().get(0).getMetroId());
        Assert.assertEquals(citizenId, castellan.getJoinedMetros().get(0).getCitizenId());
        System.out.println(castellan);

        // retrieve by wrong login Type
        castellan = castellanStore.retrieveByLoginIdAndLoginIdType("jyjung", LoginIdType.Email);
        Assert.assertNull(castellan);

        // Index (Account-Duplication) test
        Castellan newCastellan = Castellan.newInstance("newId", new CastellanCdo("jyjung@nextree.co.kr", "1234"));
        newCastellan.verifyEmail("jyjung@nextree.co.kr");
        try {
            castellanStore.create(newCastellan);
            castellanStore.retrieveAll().stream().forEach(System.out::println);
            Assert.assertTrue(false);
        } catch (DuplicateKeyException e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(true);
        }

        // delete test
        castellanStore.delete(id);
        try {
            castellanStore.retrieve(id);
            Assert.assertTrue(false);
        }
        catch (NonExistenceException e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }
}
