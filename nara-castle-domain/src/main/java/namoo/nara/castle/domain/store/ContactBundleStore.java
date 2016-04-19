package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.contact.ContactBundle;

public interface ContactBundleStore {
    //
    String create(ContactBundle contact);
    ContactBundle retrieve(String id);
    void updateAddressBook(ContactBundle contact);
    void updateEmailBook(ContactBundle contact);
    void updateNameBook(ContactBundle contact);
    void updatePhoneBook(ContactBundle contact);
    void delete(String id);
}