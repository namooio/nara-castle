package namoo.nara.castle.front.logic;

import namoo.nara.castle.front.dto.contact.*;
import namoo.nara.castle.front.dto.util.DomainConversionUtil;
import namoo.nara.castle.front.CastellanContactFrontService;
import namoo.nara.castle.domain.entity.contact.*;
import namoo.nara.castle.domain.service.CastellanContactService;
import namoo.nara.castle.domain.service.CastleServiceLycler;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastellanContactFrontServiceLogic implements CastellanContactFrontService {
    //
    private CastellanContactService castellanContactService;

    public CastellanContactFrontServiceLogic(CastleServiceLycler serviceLycler) {
        //
        castellanContactService = serviceLycler.requestCastellanContactService();
    }

    @Override
    public void attachNameBook(String castleId, NameBookDto nameBookDto) {
        //
        NameBook nameBook = DomainConversionUtil.toNameBook(nameBookDto);
        castellanContactService.attachNameBook(castleId, nameBook);
    }

    @Override
    public NameBookDto findNameBook(String castleId) {
        //
        NameBook nameBook = castellanContactService.findNameBook(castleId);
        if (nameBook == null) return null;
        return DomainConversionUtil.toNameBookDto(nameBook);
    }

    @Override
    public void detachNameBook(String castleId) {
        //
        castellanContactService.detachNameBook(castleId);
    }


    @Override
    public void attachEmailBook(String castleId, EmailBookDto emailBookDto) {
        //
        EmailBook emailBook = DomainConversionUtil.toEmailBook(emailBookDto);
        castellanContactService.attachEmailBook(castleId, emailBook);
    }

    @Override
    public EmailBookDto findEmailBook(String castleId) {
        //
        EmailBook emailBook = castellanContactService.findEmailBook(castleId);
        if (emailBook == null) return null;
        return DomainConversionUtil.toEmailBookDto(emailBook);
    }

    @Override
    public void detachEmailBook(String castleId) {
        //
        castellanContactService.detachEmailBook(castleId);
    }


    @Override
    public void attachPhoneBook(String castleId, PhoneBookDto phoneBookDto) {
        //
        PhoneBook phoneBook = DomainConversionUtil.toPhoneBook(phoneBookDto);
        castellanContactService.attachPhoneBook(castleId, phoneBook);
    }

    @Override
    public PhoneBookDto findPhoneBook(String castleId) {
        //
        PhoneBook phoneBook = castellanContactService.findPhoneBook(castleId);
        if (phoneBook == null) return null;
        return DomainConversionUtil.toPhoneBookDto(phoneBook);
    }

    @Override
    public void detachPhoneBook(String castleId) {
        //
        castellanContactService.detachPhoneBook(castleId);
    }


    @Override
    public void attachAddressBook(String castleId, AddressBookDto addressBookDto) {
        //
        AddressBook addressBook = DomainConversionUtil.toAddressBook(addressBookDto);
        castellanContactService.attachAddressBook(castleId, addressBook);
    }

    @Override
    public void addUserAddress(String castleId, UserAddressDto addressDto) {
        //
        UserAddress userAddress = DomainConversionUtil.toUserAddress(addressDto);
        castellanContactService.addUserAddress(castleId, userAddress);
    }

    @Override
    public AddressBookDto findAddressBook(String castleId) {
        //
        AddressBook addressBook = castellanContactService.findAddressBook(castleId);
        if (addressBook == null) return null;
        return DomainConversionUtil.toAddressBookDto(addressBook);
    }

    @Override
    public void modifyUserAddress(String castleId, UserAddressDto addressDto) {
        //
        UserAddress userAddress = DomainConversionUtil.toUserAddress(addressDto);
        castellanContactService.modifyUserAddress(castleId, userAddress);
    }

    @Override
    public void detachAddressBook(String castleId) {
        //
        castellanContactService.detachAddressBook(castleId);
    }

    @Override
    public void removeUserAddress(String castleId, String addressTitle) {
        //
        castellanContactService.removeUserAddress(castleId, addressTitle);
    }

}
