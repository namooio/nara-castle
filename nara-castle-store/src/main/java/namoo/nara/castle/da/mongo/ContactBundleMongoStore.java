package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.mongo.mdo.ContactBundleMdo;
import namoo.nara.castle.da.mongo.mdo.contact.AddressBookMdo;
import namoo.nara.castle.da.mongo.mdo.contact.UserAddressMdo;
import namoo.nara.castle.da.mongo.springdata.ContactBundleMdoRepository;
import namoo.nara.castle.domain.entity.contact.AddressBook;
import namoo.nara.castle.domain.entity.contact.ContactBundle;
import namoo.nara.castle.domain.entity.contact.UserAddress;
import namoo.nara.castle.domain.store.ContactBundleStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Repository
public class ContactBundleMongoStore implements ContactBundleStore {

    @Autowired
    private ContactBundleMdoRepository contactBundleMdoRepository;

    @Override
    public String create(ContactBundle contact) {
        return null;
    }

    @Override
    public void update(ContactBundle contact) {

    }

    @Override
    public void delete(String id) {

    }

    public void create(String id, ContactBundle contactBundle) {
        ContactBundleMdo contactBundleMdo = new ContactBundleMdo();
        contactBundleMdo.setId(id);

        AddressBook addressBook = contactBundle.getAddressBook();
        if (addressBook != null) {
            AddressBookMdo addressBookMdo = new AddressBookMdo();
            List<UserAddress> all = addressBook.findAll();
            for(UserAddress userAddress : all) {
                UserAddressMdo userAddressMdo = new UserAddressMdo();
//                userAddressMdo.setStyle(userAddress.getStyle());
                userAddressMdo.setZipCode(userAddress.getZipCode());
                userAddressMdo.setState(userAddress.getState());
                userAddressMdo.setCity(userAddress.getCity());
                userAddressMdo.setAddressPartOne(userAddress.getAddressPartOne());
                userAddressMdo.setAddressPartTwo(userAddress.getAddressPartTwo());
                addressBookMdo.addAddress(userAddressMdo);
            }
            contactBundleMdo.setAddressBookMdo(addressBookMdo);
        }
        contactBundleMdoRepository.save(contactBundleMdo);
    }

    public ContactBundle retrieve(String id) {
        ContactBundle contactBundle = new ContactBundle();

        ContactBundleMdo contactBundleMdo = contactBundleMdoRepository.findOne(id);
        AddressBookMdo addressBookMdo = contactBundleMdo.getAddressBookMdo();
        List<UserAddressMdo> addressDocumentList = addressBookMdo.getAddressList();
        AddressBook addressBook = new AddressBook();
        contactBundle.setAddressBook(addressBook);
        for(UserAddressMdo userAddressMdo : addressDocumentList) {
            UserAddress userAddress = new UserAddress();
            userAddress.setZipCode(userAddressMdo.getZipCode());
            userAddress.setState(userAddressMdo.getState());
            userAddress.setCity(userAddressMdo.getCity());
            userAddress.setAddressPartOne(userAddressMdo.getAddressPartOne());
            userAddress.setAddressPartTwo(userAddressMdo.getAddressPartTwo());
            addressBook.addAddress(userAddress);
        }
        return contactBundle;
    }
}
