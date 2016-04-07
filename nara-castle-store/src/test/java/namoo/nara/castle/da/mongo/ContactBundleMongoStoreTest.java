package namoo.nara.castle.da.mongo;

import namoo.nara.castle.domain.entity.contact.*;
import namoo.nara.castle.domain.store.ContactBundleStore;
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
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ContactBundleMongoStoreTest.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = "namoo.nara.castle.da.mongo")
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ContactBundleMongoStoreTest {

    @Autowired
    private ContactBundleStore contactBundleStore;

    @Test
    public void storeCrudTest() {
        //
        // create test
        String id = UUID.randomUUID().toString();
        ContactBundle contact = ContactBundle.newInstance(id);

        AddressBook addressBook = new AddressBook();
        addressBook.addAddress(UserAddress.newKoreanAddress("111-222", "경기도 용인시 수지구 죽전1동 건영캐스빌 802동 1404호"));
        addressBook.addAddress(UserAddress.newUsAddress("111-222", "CA", "Sandiego", "University ave 901"));

        EmailBook emailBook = new EmailBook();
        emailBook.addEmail(new UserEmail("kchuh@nextree.co.kr", UserEmail.EmailType.Business));
        emailBook.addEmail(new UserEmail("michael7557@gmail.com", UserEmail.EmailType.Private));
        emailBook.addEmail(new UserEmail("kchuh@me.com", UserEmail.EmailType.Private));
        emailBook.addEmail(new UserEmail("kchuh@icloud.com", UserEmail.EmailType.Private));

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addPhone(new UserPhone("010-6325-7557"));

        NameBook nameBook = new NameBook();
        nameBook.addName(new UserName("Huh", "Ki Chul", "kchuh", "us"));
        nameBook.addName(new UserName("허", "기철", "허기철", "kr"));

        contact.setAddressBook(addressBook);
        contact.setEmailBook(emailBook);
        contact.setPhoneBook(phoneBook);
        contact.setNameBook(nameBook);

        contactBundleStore.create(contact);

        // retrieve test
        contact = contactBundleStore.retrieve(id);
        addressBook = contact.getAddressBook();
        Assert.assertEquals(2, addressBook.findAll().size());

        emailBook = contact.getEmailBook();
        Assert.assertEquals(4, emailBook.findAll().size());

        phoneBook = contact.getPhoneBook();
        Assert.assertEquals(1, phoneBook.findAll().size());

        nameBook = contact.getNameBook();
        Assert.assertEquals(2, nameBook.findAll().size());

        // update test
        phoneBook.addPhone(new UserPhone("031-897-2957"));
        contactBundleStore.update(contact);
        contact = contactBundleStore.retrieve(id);
        phoneBook = contact.getPhoneBook();
        Assert.assertEquals(2, phoneBook.findAll().size());

        // delete test
        contactBundleStore.delete(id);
        try {
            contactBundleStore.retrieve(id);
        }
        catch (NonExistenceException e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

}
