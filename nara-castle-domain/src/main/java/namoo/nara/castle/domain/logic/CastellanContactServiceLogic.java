package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.entity.contact.*;
import namoo.nara.castle.domain.service.CastellanContactService;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.domain.store.ContactBundleStore;

public class CastellanContactServiceLogic implements CastellanContactService {
    //
    private ContactBundleStore contactStore;

    public CastellanContactServiceLogic(CastleStoreLycler storeLycler) {
        //
        this.contactStore = storeLycler.requestContactBundleStore();
    }

    @Override
    public void attachNameBook(String castleId, NameBook nameBook) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        contact.attachNameBook(nameBook);

        contactStore.updateNameBook(contact);
    }

    @Override
    public void detachNameBook(String castleId) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        contact.detatchNameBook();

        contactStore.updateNameBook(contact);
    }

    @Override
    public NameBook findNameBook(String castleId) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        return contact.getNameBook();
    }

    @Override
    public void attachEmailBook(String castleId, EmailBook emailBook) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        contact.attachEmailBook(emailBook);

        contactStore.updateEmailBook(contact);
    }

    @Override
    public void detachEmailBook(String castleId) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        contact.detatchEmailBook();

        contactStore.updateEmailBook(contact);
    }

    @Override
    public EmailBook findEmailBook(String castleId) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        return contact.getEmailBook();
    }

    @Override
    public void addEmail(String castleId, String email) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        EmailBook emailBook = contact.getEmailBook();
        UserEmail userEmail = new UserEmail(email);
        emailBook.addEmail(userEmail);

        contactStore.updateEmailBook(contact);
    }

    @Override
    public void removeEmail(String castleId, String email) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        EmailBook emailBook = contact.getEmailBook();
        emailBook.removeEmail(email);

        contactStore.updateEmailBook(contact);
    }

    @Override
    public void verifyEmail(String castleId, String email) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        EmailBook emailBook = contact.getEmailBook();

        emailBook.verifyEmail(email);
        contactStore.updateEmailBook(contact);
    }

    @Override
    public void attachPhoneBook(String castleId, PhoneBook phoneBook) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        contact.attachPhoneBook(phoneBook);

        contactStore.updatePhoneBook(contact);
    }

    @Override
    public void detachPhoneBook(String castleId) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        contact.detatchPhoneBook();

        contactStore.updatePhoneBook(contact);
    }

    @Override
    public PhoneBook findPhoneBook(String castleId) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        return contact.getPhoneBook();
    }

    @Override
    public void attachAddressBook(String castleId, AddressBook addressBook) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        contact.attachAddressBook(addressBook);

        contactStore.updateAddressBook(contact);
    }

    @Override
    public void detachAddressBook(String castleId) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        contact.detatchAddressBook();

        contactStore.updateAddressBook(contact);
    }

    @Override
    public void addUserAddress(String castleId, UserAddress address) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        AddressBook addressBook = contact.getAddressBook();

        addressBook.add(address);
        contactStore.updateAddressBook(contact);
    }

    @Override
    public void removeUserAddress(String castleId, String addressTitle) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        AddressBook addressBook = contact.getAddressBook();

        if (!addressBook.existByTitle(addressTitle)) {
            return;
        }

        addressBook.removeByTitle(addressTitle);
        contactStore.updateAddressBook(contact);
    }

    @Override
    public void modifyUserAddress(String castleId, UserAddress address) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        AddressBook addressBook = contact.getAddressBook();

        if (!addressBook.existByTitle(address.getTitle())) {
            return;
        }

        addressBook.update(address);
        contactStore.updateAddressBook(contact);
    }

    @Override
    public AddressBook findAddressBook(String castleId) {
        //
        ContactBundle contact = contactStore.retrieve(castleId);
        return contact.getAddressBook();
    }
}