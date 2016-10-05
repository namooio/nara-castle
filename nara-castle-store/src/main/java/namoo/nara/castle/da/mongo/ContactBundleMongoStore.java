package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.mongo.document.ContactBundleDoc;
import namoo.nara.castle.da.mongo.springdata.ContactBundleMongoRepository;
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
        ContactBundleDoc contactBundleDoc = ContactBundleDoc.newInstance(contact);
        contactBundleMongoRepository.save(contactBundleDoc);
        return id;
    }

    @Override
    public ContactBundle retrieve(String id) {
        //
        ContactBundleDoc contactBundleDoc = contactBundleMongoRepository.findOne(id);
        if (contactBundleDoc == null) throw new NonExistenceException(String.format("No contact bundle document[ID:%s] to retrieve.", id));
        return contactBundleDoc.toDomain();
    }

    private void update(ContactBundle contact) {
        //
        String id = contact.getId();
        if (!contactBundleMongoRepository.exists(id)) throw new NonExistenceException(String.format("No contact bundle document[ID:%s] to update.", id));
        ContactBundleDoc contactBundleDoc = ContactBundleDoc.newInstance(contact);
        contactBundleMongoRepository.save(contactBundleDoc);
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
