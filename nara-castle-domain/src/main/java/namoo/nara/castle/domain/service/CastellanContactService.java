package namoo.nara.castle.domain.service;


import namoo.nara.castle.domain.entity.contact.*;

public interface CastellanContactService {
    //
    void attachNameBook(String castleId, NameBook nameBook);
    void detachNameBook(String castleId);
    NameBook findNameBook(String castleId);

    void attachEmailBook(String castleId, EmailBook emailBook);
    void detachEmailBook(String castleId);
    EmailBook findEmailBook(String castleId);

    void attachPhoneBook(String castleId, PhoneBook phoneBook);
    void detachPhoneBook(String castleId);
    PhoneBook findPhoneBook(String castleId);

    void attachAddressBook(String castleId, AddressBook addressBook);
    void detachAddressBook(String castleId);
    void addUserAddress(String castleId, UserAddress address);
    void removeUserAddress(String castleId, String addressTitle);
    void modifyUserAddress(String castleId, UserAddress address);
    AddressBook findAddressBook(String castleId);
}