package namoo.nara.castle.domain.entity.contact;

import namoo.nara.share.domain.util.Identifiable;

public class ContactBundle implements Identifiable {
    //
    private String castleId;
    private NameBook nameBook;
    private EmailBook emailBook;
    private PhoneBook phoneBook;
    private AddressBook addressBook;

    public ContactBundle() {
        //
    }

    protected ContactBundle(String castleId) {
        //
        this.castleId = castleId;
    }

    public static ContactBundle newInstance(String castleId) {
        //
        return new ContactBundle(castleId);
    }

    @Override
    public String getId() {
        return castleId;
    }

    public NameBook getNameBook() {
        //
        if (nameBook == null) {
            this.nameBook = new NameBook();
        }
        return nameBook;
    }

    public void setNameBook(NameBook nameBook) {
        this.nameBook = nameBook;
    }

    public PhoneBook getPhoneBook() {
        //
        if(phoneBook == null) {
            this.phoneBook = new PhoneBook();
        }

        return phoneBook;
    }

    public void setPhoneBook(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    public EmailBook getEmailBook() {
        //
        if(emailBook == null) {
            this.emailBook = new EmailBook();
        }

        return emailBook;
    }

    public void setEmailBook(EmailBook emailBook) {
        this.emailBook = emailBook;
    }

    public AddressBook getAddressBook() {
        //
        if(addressBook == null) {
            this.addressBook = new AddressBook();
        }

        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }
}