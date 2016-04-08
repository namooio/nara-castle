package namoo.nara.castle.domain.service;


import namoo.nara.castle.domain.entity.contact.*;

public interface CastellanContactService {
    //
    void attachNameBook(String castellanId, NameBook namebook);
    void detachNameBook(String castellanId);
    NameBook findNameBook(String castellanId);

    void attachEmailBook(String castellanId, EmailBook emailBook);
    void detachEmailBook(String castellanId);
    EmailBook findEmailBook(String castellanId);

    void attachPhoneBook(String castellanId, PhoneBook phoneBook);
    void detachPhoneBook(String castellanId);
    PhoneBook findPhoneBook(String castellanId);

    void attachAddressBook(String castellanId, AddressBook addressBook);
    void detachAddressBook(String castellanId);
    void addUserAddress(String castellanId, UserAddress address);
    void removeUserAddress(String castellanId, String addresssTitle);
    void modifyUserAddress(String castellanId, UserAddress address);
    AddressBook findAddressBook(String castellanId);
}