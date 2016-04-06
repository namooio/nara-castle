package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.contact.ContactBundle;

public interface ContactBundleStore {
    //
    String create(ContactBundle contact);
    ContactBundle retrieve(String id);
    void update(ContactBundle contact);
    void delete(String id);
}