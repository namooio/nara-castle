package namoo.nara.castle.sp.springweb;

import namoo.nara.castle.adapter.dto.contact.*;
import namoo.nara.castle.adapter.logic.CastellanContactAdapterLogic;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
@RestController
@RequestMapping("castellan/{id}/contact")
public class CastellanContactResource extends CastellanContactAdapterLogic {
    //
    @Autowired
    public CastellanContactResource(CastleServiceLycler serviceLycler) {
        super(serviceLycler);
    }

    @Override
    @RequestMapping(value = "namebook", method = RequestMethod.POST)
    public void attachNameBook(@PathVariable("id") String castleId, @RequestBody NameBookDto nameBookDto) {
        super.attachNameBook(castleId, nameBookDto);
    }

    @Override
    @RequestMapping(value = "namebook", method = RequestMethod.DELETE)
    public void detachNameBook(@PathVariable("id") String castleId) {
        super.detachNameBook(castleId);
    }

    @Override
    @RequestMapping(value = "namebook", method = RequestMethod.GET)
    public NameBookDto findNameBook(@PathVariable("id") String castleId) {
        return super.findNameBook(castleId);
    }

    @Override
    @RequestMapping(value = "emailbook", method = RequestMethod.POST)
    public void attachEmailBook(@PathVariable("id") String castleId, @RequestBody EmailBookDto emailBookDto) {
        super.attachEmailBook(castleId, emailBookDto);
    }

    @Override
    @RequestMapping(value = "emailbook", method = RequestMethod.DELETE)
    public void detachEmailBook(@PathVariable("id") String castleId) {
        super.detachEmailBook(castleId);
    }

    @Override
    @RequestMapping(value = "emailbook", method = RequestMethod.GET)
    public EmailBookDto findEmailBook(@PathVariable("id") String castleId) {
        return super.findEmailBook(castleId);
    }

    @Override
    @RequestMapping(value = "phonebook", method = RequestMethod.POST)
    public void attachPhoneBook(@PathVariable("id") String castleId, @RequestBody PhoneBookDto phoneBookDto) {
        super.attachPhoneBook(castleId, phoneBookDto);
    }

    @Override
    @RequestMapping(value = "phonebook", method = RequestMethod.DELETE)
    public void detachPhoneBook(@PathVariable("id") String castleId) {
        super.detachPhoneBook(castleId);
    }

    @Override
    @RequestMapping(value = "phonebook", method = RequestMethod.GET)
    public PhoneBookDto findPhoneBook(@PathVariable("id") String castleId) {
        return super.findPhoneBook(castleId);
    }

    @Override
    @RequestMapping(value = "addressbook", method = RequestMethod.POST)
    public void attachAddressBook(@PathVariable("id") String castleId, @RequestBody AddressBookDto addressBookDto) {
        super.attachAddressBook(castleId, addressBookDto);
    }

    @Override
    @RequestMapping(value = "addressbook", method = RequestMethod.DELETE)
    public void detachAddressBook(@PathVariable("id") String castleId) {
        super.detachAddressBook(castleId);
    }

    @Override
    @RequestMapping(value = "address", method = RequestMethod.POST)
    public void addUserAddress(@PathVariable("id") String castleId, @RequestBody UserAddressDto addressDto) {
        super.addUserAddress(castleId, addressDto);
    }

    @Override
    @RequestMapping(value = "address", method = RequestMethod.DELETE)
    public void removeUserAddress(@PathVariable("id") String castleId, @RequestBody String addressTitle) {
        super.removeUserAddress(castleId, addressTitle);
    }

    @Override
    @RequestMapping(value = "address", method = RequestMethod.PUT)
    public void modifyUserAddress(@PathVariable("id") String castleId, @RequestBody UserAddressDto addressDto) {
        super.modifyUserAddress(castleId, addressDto);
    }

    @Override
    @RequestMapping(value = "addressbook", method = RequestMethod.GET)
    public AddressBookDto findAddressBook(@PathVariable("id") String castleId) {
        return super.findAddressBook(castleId);
    }
}
