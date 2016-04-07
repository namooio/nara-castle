package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.mongo.mdo.ContactBundleMdo;
import namoo.nara.castle.da.mongo.springdata.ContactBundleMdoRepository;
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

    @Autowired
    private ContactBundleMdoRepository contactBundleMdoRepository;

    @Override
    public String create(ContactBundle contact) {
        //
        String id = contact.getId();
        if (contactBundleMdoRepository.exists(id)) throw new AlreadyExistsException(String.format("Contact bundle document[ID:%s] already exist.", id));
        ContactBundleMdo contactBundleMdo = ContactBundleMdo.newInstance(contact);
        contactBundleMdoRepository.save(contactBundleMdo);
        return id;
    }

    @Override
    public ContactBundle retrieve(String id) {
        //
        ContactBundleMdo contactBundleMdo = contactBundleMdoRepository.findOne(id);
        if (contactBundleMdo == null) throw new NonExistenceException(String.format("No contact bundle document[ID:%s] to retrieve.", id));
        return contactBundleMdo.getDomain();
    }

    @Override
    public void update(ContactBundle contact) {
        //
        String id = contact.getId();
        if (!contactBundleMdoRepository.exists(id)) throw new NonExistenceException(String.format("No contact bundle document[ID:%s] to update.", id));
        ContactBundleMdo contactBundleMdo = ContactBundleMdo.newInstance(contact);
        contactBundleMdoRepository.save(contactBundleMdo);
    }

    @Override
    public void delete(String id) {
        //
        if (!contactBundleMdoRepository.exists(id)) throw new NonExistenceException(String.format("No contact bundle document[ID:%s] to delete.", id));
        contactBundleMdoRepository.delete(id);
    }

}
