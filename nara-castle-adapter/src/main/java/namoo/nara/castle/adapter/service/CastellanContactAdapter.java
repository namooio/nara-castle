package namoo.nara.castle.adapter.service;

import namoo.nara.castle.adapter.dto.contact.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public interface CastellanContactAdapter {
    //
    void attachNameBook(String castleId, NameBookDto nameBookDto);
    void detachNameBook(String castleId);
    NameBookDto findNameBook(String castleId);

    void attachEmailBook(String castleId, EmailBookDto emailBookDto);
    void detachEmailBook(String castleId);
    EmailBookDto findEmailBook(String castleId);

    void attachPhoneBook(String castleId, PhoneBookDto phoneBookDto);
    void detachPhoneBook(String castleId);
    PhoneBookDto findPhoneBook(String castleId);

    void attachAddressBook(String castleId, AddressBookDto addressBookDto);
    void detachAddressBook(String castleId);
    void addUserAddress(String castleId, UserAddressDto addressDto);
    void removeUserAddress(String castleId, String addressTitle);
    void modifyUserAddress(String castleId, UserAddressDto addressDto);
    AddressBookDto findAddressBook(String castleId);
}
