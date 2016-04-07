package namoo.nara.castle.da.mongo;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ContactBundleMongoStoreTest.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = "namoo.nara.castle.da.mongo")
@Ignore
public class ContactBundleMongoStoreTest {

    @Autowired
    private ContactBundleMongoStore contactBundleMongoStore;

//    @Test
//    public void test() {
//        ContactBundle contactBundle = new ContactBundle();
//        AddressBook addressBook = new AddressBook();
//        contactBundle.setAddressBook(addressBook);
//        addressBook.addAddress(UserAddress.newKoreanAddress("123-456", "경기도 용인시 죽전동 건영캐스빌 802동 1404호"));
//        addressBook.addAddress(UserAddress.newUsAddress("42452", "CA", "San Diego", "University'st 901"));
//        contactBundleMongoStore.create("abc", contactBundle);
//
//        ContactBundle contactBundle1 = contactBundleMongoStore.retrieve("abc");
//        AddressBook addressBook1 = contactBundle1.getAddressBook();
//        List<UserAddress> userAddresses = addressBook1.findAll();
//        for(UserAddress userAddress : userAddresses) {
//            System.out.println("===============================");
//            System.out.println(userAddress.getZipCode());
//            System.out.println(userAddress.getState());
//            System.out.println(userAddress.getAddressPartOne());
//            System.out.println(userAddress.getAddressPartTwo());
//            System.out.println("===============================");
//        }
//    }
}
