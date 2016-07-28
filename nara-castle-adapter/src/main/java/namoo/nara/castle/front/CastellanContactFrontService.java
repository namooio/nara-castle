package namoo.nara.castle.front;

import namoo.nara.castle.front.dto.contact.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public interface CastellanContactFrontService {
    //
    void attachNameBook(String castleId, NameBookDto nameBookDto);
    NameBookDto findNameBook(String castleId);
    void detachNameBook(String castleId);

    void attachEmailBook(String castleId, EmailBookDto emailBookDto);
    EmailBookDto findEmailBook(String castleId);
    void detachEmailBook(String castleId);

    void attachPhoneBook(String castleId, PhoneBookDto phoneBookDto);
    PhoneBookDto findPhoneBook(String castleId);
    void detachPhoneBook(String castleId);

    void attachAddressBook(String castleId, AddressBookDto addressBookDto);
    void addUserAddress(String castleId, UserAddressDto addressDto);
    AddressBookDto findAddressBook(String castleId);
    void modifyUserAddress(String castleId, UserAddressDto addressDto);
    void detachAddressBook(String castleId);
    void removeUserAddress(String castleId, String addressTitle);



}
