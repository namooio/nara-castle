package namoo.nara.castle.adapter.logic;

import namoo.nara.castle.adapter.dto.contact.*;
import namoo.nara.castle.adapter.service.CastellanContactAdapter;
import namoo.nara.castle.domain.entity.contact.*;
import namoo.nara.castle.domain.service.CastellanContactService;
import namoo.nara.castle.domain.service.CastleServiceLycler;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastellanContactAdapterLogic implements CastellanContactAdapter {
    //
    private CastellanContactService castellanContactService;

    public CastellanContactAdapterLogic(CastleServiceLycler serviceLycler) {
        //
        castellanContactService = serviceLycler.requestCastellanContactService();
    }

    @Override
    public void attachNameBook(String castleId, NameBookDto nameBookDto) {
        //
        NameBook nameBook = nameBookDto.toDomain();
        castellanContactService.attachNameBook(castleId, nameBook);
    }

    @Override
    public void detachNameBook(String castleId) {
        //
        castellanContactService.detachNameBook(castleId);
    }

    @Override
    public NameBookDto findNameBook(String castleId) {
        //
        NameBook nameBook = castellanContactService.findNameBook(castleId);
        return NameBookDto.newInstance(nameBook);
    }

    @Override
    public void attachEmailBook(String castleId, EmailBookDto emailBookDto) {
        //
        EmailBook emailBook = emailBookDto.toDomain();
        castellanContactService.attachEmailBook(castleId, emailBook);
    }

    @Override
    public void detachEmailBook(String castleId) {
        //
        castellanContactService.detachEmailBook(castleId);
    }

    @Override
    public EmailBookDto findEmailBook(String castleId) {
        //
        EmailBook emailBook = castellanContactService.findEmailBook(castleId);
        return EmailBookDto.newInstance(emailBook);
    }

    @Override
    public void attachPhoneBook(String castleId, PhoneBookDto phoneBookDto) {
        //
        PhoneBook phoneBook = phoneBookDto.toDomain();
        castellanContactService.attachPhoneBook(castleId, phoneBook);
    }

    @Override
    public void detachPhoneBook(String castleId) {
        //
        castellanContactService.detachPhoneBook(castleId);
    }

    @Override
    public PhoneBookDto findPhoneBook(String castleId) {
        //
        PhoneBook phoneBook = castellanContactService.findPhoneBook(castleId);
        return PhoneBookDto.newInstance(phoneBook);
    }

    @Override
    public void attachAddressBook(String castleId, AddressBookDto addressBookDto) {
        //
        AddressBook addressBook = addressBookDto.toDomain();
        castellanContactService.attachAddressBook(castleId, addressBook);
    }

    @Override
    public void detachAddressBook(String castleId) {
        //
        castellanContactService.detachAddressBook(castleId);
    }

    @Override
    public void addUserAddress(String castleId, UserAddressDto addressDto) {
        //
        UserAddress userAddress = addressDto.toDomain();
        castellanContactService.addUserAddress(castleId, userAddress);
    }

    @Override
    public void removeUserAddress(String castleId, String addressTitle) {
        //
        castellanContactService.removeUserAddress(castleId, addressTitle);
    }

    @Override
    public void modifyUserAddress(String castleId, UserAddressDto addressDto) {
        //
        UserAddress userAddress = addressDto.toDomain();
        castellanContactService.modifyUserAddress(castleId, userAddress);
    }

    @Override
    public AddressBookDto findAddressBook(String castleId) {
        //
        AddressBook addressBook = castellanContactService.findAddressBook(castleId);
        return AddressBookDto.newInstance(addressBook);
    }
}
