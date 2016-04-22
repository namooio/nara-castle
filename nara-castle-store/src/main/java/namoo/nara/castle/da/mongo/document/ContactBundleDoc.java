package namoo.nara.castle.da.mongo.document;

import namoo.nara.castle.da.mongo.document.contact.AddressBookDoc;
import namoo.nara.castle.da.mongo.document.contact.EmailBookDoc;
import namoo.nara.castle.da.mongo.document.contact.NameBookDoc;
import namoo.nara.castle.da.mongo.document.contact.PhoneBookDoc;
import namoo.nara.castle.domain.entity.contact.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Document(collection = "ContactBundle")
public class ContactBundleDoc {
    //
    @Id
    private String id;

    private NameBookDoc nameBook;
    private PhoneBookDoc phoneBook;
    private EmailBookDoc emailBook;
    private AddressBookDoc addressBook;

    public ContactBundleDoc() {
        //
    }

    public static ContactBundleDoc newInstance(ContactBundle contact) {
        //
        ContactBundleDoc contactDoc = new ContactBundleDoc();
        contactDoc.setId(contact.getId());

        NameBook nameBook = contact.getNameBook();
        PhoneBook phoneBook = contact.getPhoneBook();
        EmailBook emailBook = contact.getEmailBook();
        AddressBook addressBook = contact.getAddressBook();

        if (nameBook != null) contactDoc.setNameBook(NameBookDoc.newInstance(nameBook));
        if (phoneBook != null) contactDoc.setPhoneBook(PhoneBookDoc.newInstance(phoneBook));
        if (emailBook != null) contactDoc.setEmailBook(EmailBookDoc.newInstance(emailBook));
        if (addressBook != null) contactDoc.setAddressBook(AddressBookDoc.newInstance(addressBook));

        return contactDoc;
    }

    public ContactBundle toDomain() {
        //
        ContactBundle contactBundle = ContactBundle.newInstance(id);

        if (nameBook != null) contactBundle.attachNameBook(nameBook.toDomain());
        if (phoneBook != null) contactBundle.attachPhoneBook(phoneBook.toDomain());
        if (emailBook != null) contactBundle.attachEmailBook(emailBook.toDomain());
        if (addressBook != null) contactBundle.attachAddressBook(addressBook.toDomain());

        return contactBundle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NameBookDoc getNameBook() {
        return nameBook;
    }

    public void setNameBook(NameBookDoc nameBook) {
        this.nameBook = nameBook;
    }

    public PhoneBookDoc getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(PhoneBookDoc phoneBook) {
        this.phoneBook = phoneBook;
    }

    public EmailBookDoc getEmailBook() {
        return emailBook;
    }

    public void setEmailBook(EmailBookDoc emailBook) {
        this.emailBook = emailBook;
    }

    public AddressBookDoc getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBookDoc addressBook) {
        this.addressBook = addressBook;
    }
}
