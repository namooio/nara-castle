package namoo.nara.castle.da.mongo;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.LoginIdType;
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
        String name = "정지용";
        String photoId = "photoGood";
        String loginId = "jiyong84444";
        LoginIdType loginIdType = LoginIdType.Username;
        String password = "Non-encryption";
        String email = "jyjung@nextree.co.kr";

        // create test
        Castellan castellan = Castellan.newInstance(id, name);
        castellan.setPhotoId(photoId);
        castellan.addAccount(loginId, loginIdType);
        castellan.setPasswordCredential(password);
        castellan.addEmail(email);
        castellanStore.create(castellan);

        // retrieve test
        castellan = castellanStore.retrieve(id);
        Assert.assertEquals(name, castellan.getName());
        Assert.assertEquals(photoId, castellan.getPhotoId());
        Assert.assertEquals(password, castellan.getCredential().getPassword());
        Assert.assertEquals(1, castellan.getEmailsCount());
        System.out.println(castellan);

        // retreive all test
        Assert.assertEquals(1, castellanStore.retrieveAll().size());

        // update test
        String changedName = "jiyong";
        String metroId = "metro1";
        String citizenId = "metro1-citizen";
        castellan.setName(changedName);
        castellan.verifyEmail(email);
        castellan.addJoinedMetro(metroId, citizenId);
        castellanStore.update(castellan);

        // retrieve by loginId & Type
        castellan = castellanStore.retrieveByLoginIdAndLoginIdType(loginId, loginIdType);
        Assert.assertEquals(changedName, castellan.getName());
        Assert.assertEquals(2, castellan.getAccounts().size());
        Assert.assertEquals(1, castellan.getJoinedMetrosCount());

        castellan = castellanStore.retrieveByLoginIdAndLoginIdType(email, LoginIdType.Email);
        Assert.assertEquals(changedName, castellan.getName());
        Assert.assertEquals(2, castellan.getAccounts().size());
        Assert.assertEquals(metroId, castellan.getJoinedMetros().get(0).getMetroId());
        Assert.assertEquals(citizenId, castellan.getJoinedMetros().get(0).getCitizenId());
        System.out.println(castellan);

        // retrieve by wrong login Type
        try {
            castellanStore.retrieveByLoginIdAndLoginIdType(loginId, LoginIdType.Email);
            Assert.assertTrue(false);
        } catch (NonExistenceException e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(true);
        }

        // Index (Account-Duplication) test
        Castellan newCastellan = Castellan.newInstance("newId", "newName");
        newCastellan.addAccount(loginId, loginIdType);
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
