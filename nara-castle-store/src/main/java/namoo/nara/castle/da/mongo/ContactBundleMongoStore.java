package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.mongo.mdo.ContactBundleMdo;
import namoo.nara.castle.da.mongo.springdata.ContactBundleMongoRepository;
import namoo.nara.castle.domain.entity.contact.ContactBundle;
import namoo.nara.castle.domain.store.ContactBundleStore;
import namoo.nara.share.exception.store.AlreadyExistsException;
import namoo.nara.share.exception.store.NonExistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Repository
public class ContactBundleMongoStore implements ContactBundleStore {
    //
    @Autowired
    private ContactBundleMongoRepository contactBundleMongoRepository;

    @Override
    public String create(ContactBundle contact) {
        //
        String id = contact.getId();
        if (contactBundleMongoRepository.exists(id)) throw new AlreadyExistsException(String.format("Contact bundle document[ID:%s] already exist.", id));
        ContactBundleMdo contactBundleMdo = ContactBundleMdo.newInstance(contact);
        contactBundleMongoRepository.save(contactBundleMdo);
        return id;
    }

    @Override
    public ContactBundle retrieve(String id) {
        //
        ContactBundleMdo contactBundleMdo = contactBundleMongoRepository.findOne(id);
        if (contactBundleMdo == null) throw new NonExistenceException(String.format("No contact bundle document[ID:%s] to retrieve.", id));
        return contactBundleMdo.toDomain();
    }

    private void update(ContactBundle contact) {
        //
        String id = contact.getId();
        if (!contactBundleMongoRepository.exists(id)) throw new NonExistenceException(String.format("No contact bundle document[ID:%s] to update.", id));
        ContactBundleMdo contactBundleMdo = ContactBundleMdo.newInstance(contact);
        contactBundleMongoRepository.save(contactBundleMdo);
    }

    @Override
    public void updateAddressBook(ContactBundle contact) {
        //
        update(contact);
    }

    @Override
    public void updateEmailBook(ContactBundle contact) {
        //
        update(contact);
    }

    @Override
    public void updateNameBook(ContactBundle contact) {
        //
        update(contact);
    }

    @Override
    public void updatePhoneBook(ContactBundle contact) {
        //
        update(contact);
    }

    @Override
    public void delete(String id) {
        //
        if (!contactBundleMongoRepository.exists(id)) throw new NonExistenceException(String.format("No contact bundle document[ID:%s] to delete.", id));
        contactBundleMongoRepository.delete(id);
    }

}
