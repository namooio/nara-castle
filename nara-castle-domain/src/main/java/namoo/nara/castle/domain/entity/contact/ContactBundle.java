package namoo.nara.castle.domain.entity.contact;

public class ContactBundle {
    //
    private NameBook nameBook;
    private PhoneBook phoneBook;
    private EmailBook emailBook;
    private AddressBook addressBook;

    public ContactBundle() {
        //
    }

    public NameBook getNameBook() {
        return nameBook;
    }

    public void setNameBook(NameBook nameBook) {
        this.nameBook = nameBook;
    }

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    public EmailBook getEmailBook() {
        return emailBook;
    }

    public void setEmailBook(EmailBook emailBook) {
        this.emailBook = emailBook;
    }

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }
}
