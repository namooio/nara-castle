package namoo.nara.castle.da.mongo.mdo;

import namoo.nara.castle.da.mongo.mdo.contact.AddressBookMdo;
import namoo.nara.castle.da.mongo.mdo.contact.EmailBookMdo;
import namoo.nara.castle.da.mongo.mdo.contact.NameBookMdo;
import namoo.nara.castle.da.mongo.mdo.contact.PhoneBookMdo;
import namoo.nara.castle.domain.entity.contact.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Document(collection = "ContactBundle")
public class ContactBundleMdo {
    //
    @Id
    private String id;

    private NameBookMdo nameBookMdo;
    private PhoneBookMdo phoneBookMdo;
    private EmailBookMdo emailBookMdo;
    private AddressBookMdo addressBookMdo;

    public ContactBundleMdo() {
        //
    }

    public static ContactBundleMdo newInstance(ContactBundle contact) {
        //
        ContactBundleMdo contactMdo = new ContactBundleMdo();
        contactMdo.setId(contact.getId());

        NameBook nameBook = contact.getNameBook();
        PhoneBook phoneBook = contact.getPhoneBook();
        EmailBook emailBook = contact.getEmailBook();
        AddressBook addressBook = contact.getAddressBook();

        if (nameBook != null) contactMdo.setNameBookMdo(NameBookMdo.newInstance(nameBook));
        if (phoneBook != null) contactMdo.setPhoneBookMdo(PhoneBookMdo.newInstance(phoneBook));
        if (emailBook != null) contactMdo.setEmailBookMdo(EmailBookMdo.newInstance(emailBook));
        if (addressBook != null) contactMdo.setAddressBookMdo(AddressBookMdo.newInstance(addressBook));

        return contactMdo;
    }

    public ContactBundle toDomain() {
        //
        ContactBundle contactBundle = ContactBundle.newInstance(id);

        if (nameBookMdo != null) contactBundle.attachNameBook(nameBookMdo.toDomain());
        if (phoneBookMdo != null) contactBundle.attachPhoneBook(phoneBookMdo.toDomain());
        if (emailBookMdo != null) contactBundle.attachEmailBook(emailBookMdo.toDomain());
        if (addressBookMdo != null) contactBundle.attachAddressBook(addressBookMdo.toDomain());

        return contactBundle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NameBookMdo getNameBookMdo() {
        return nameBookMdo;
    }

    public void setNameBookMdo(NameBookMdo nameBookMdo) {
        this.nameBookMdo = nameBookMdo;
    }

    public PhoneBookMdo getPhoneBookMdo() {
        return phoneBookMdo;
    }

    public void setPhoneBookMdo(PhoneBookMdo phoneBookMdo) {
        this.phoneBookMdo = phoneBookMdo;
    }

    public EmailBookMdo getEmailBookMdo() {
        return emailBookMdo;
    }

    public void setEmailBookMdo(EmailBookMdo emailBookMdo) {
        this.emailBookMdo = emailBookMdo;
    }

    public AddressBookMdo getAddressBookMdo() {
        return addressBookMdo;
    }

    public void setAddressBookMdo(AddressBookMdo addressBookMdo) {
        this.addressBookMdo = addressBookMdo;
    }
}
