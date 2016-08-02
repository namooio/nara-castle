package namoo.nara.castle.sp.spring.web;

import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.front.dto.contact.AddressBookDto;
import namoo.nara.castle.front.dto.contact.EmailBookDto;
import namoo.nara.castle.front.dto.contact.NameBookDto;
import namoo.nara.castle.front.dto.contact.PhoneBookDto;
import namoo.nara.castle.front.dto.contact.UserAddressDto;
import namoo.nara.castle.front.logic.CastellanContactFrontServiceLogic;
import namoo.nara.stage.annotation.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@Feature(name = "Castellan Contact Service")
@RestController
@RequestMapping("castle-api/castellans/{id}/contacts")
public class CastellanContactFrontResource extends CastellanContactFrontServiceLogic {
    //
    @Autowired
    public CastellanContactFrontResource(CastleServiceLycler serviceLycler) {
        super(serviceLycler);
    }


    @Override
    @RequestMapping(value="/name-book", method=RequestMethod.POST)
    public void attachNameBook(@PathVariable("id") String castleId, @RequestBody NameBookDto nameBookDto) {
        super.attachNameBook(castleId, nameBookDto);
    }

    @Override
    @RequestMapping(value="/name-book", method= RequestMethod.GET)
    public NameBookDto findNameBook(@PathVariable("id") String castleId) {
        return super.findNameBook(castleId);
    }

    @Override
    @RequestMapping(value="/name-book", method= RequestMethod.DELETE)
    public void detachNameBook(String castleId) {
        super.detachNameBook(castleId);
    }


    @Override
    @RequestMapping(value="/phone-book", method=RequestMethod.POST)
    public void attachPhoneBook(@PathVariable("id") String castleId, @RequestBody PhoneBookDto phoneBookDto) {
        super.attachPhoneBook(castleId, phoneBookDto);
    }

    @Override
    @RequestMapping(value="/phone-book", method= RequestMethod.GET)
    public PhoneBookDto findPhoneBook(@PathVariable("id") String castleId) {
        return super.findPhoneBook(castleId);
    }

    @Override
    @RequestMapping(value="/phone-book", method= RequestMethod.DELETE)
    public void detachPhoneBook(@PathVariable("id") String castleId) {
        super.detachPhoneBook(castleId);
    }


    @Override
    @RequestMapping(value="/email-book", method=RequestMethod.POST)
    public void attachEmailBook(@PathVariable("id") String castleId, @RequestBody EmailBookDto emailBookDto) {
        super.attachEmailBook(castleId, emailBookDto);
    }

    @Override
    @RequestMapping(value="/email-book", method= RequestMethod.GET)
    public EmailBookDto findEmailBook(@PathVariable("id") String castleId) {
        return super.findEmailBook(castleId);
    }

    @Override
    @RequestMapping(value="/email-book", method= RequestMethod.DELETE)
    public void detachEmailBook(@PathVariable("id") String castleId) {
        super.detachEmailBook(castleId);
    }


    @Override
    @RequestMapping(value="/address-book", method=RequestMethod.POST)
    public void attachAddressBook(@PathVariable("id") String castleId, @RequestBody AddressBookDto addressBookDto) {
        super.attachAddressBook(castleId, addressBookDto);
    }

    @Override
    @RequestMapping(value="/addresses", method=RequestMethod.POST)
    public void addUserAddress(@PathVariable("id") String castleId, @RequestBody UserAddressDto addressDto) {
        super.addUserAddress(castleId, addressDto);
    }

    @Override
    @RequestMapping(value="/address-book", method= RequestMethod.GET)
    public AddressBookDto findAddressBook(@PathVariable("id") String castleId) {
        return super.findAddressBook(castleId);
    }

    @Override
    @RequestMapping(value="/addresses", method= RequestMethod.PUT)
    public void modifyUserAddress(@PathVariable("id") String castleId, @RequestBody UserAddressDto addressDto) {
        super.modifyUserAddress(castleId, addressDto);
    }

    @Override
    @RequestMapping(value="/address-book", method= RequestMethod.DELETE)
    public void detachAddressBook(@PathVariable("id") String castleId) {
        super.detachAddressBook(castleId);
    }

    @Override
    @RequestMapping(value= "/addresses/{title}", method= RequestMethod.DELETE)
    public void removeUserAddress(@PathVariable("id") String castleId, @PathVariable("title") String addressTitle) {
        super.removeUserAddress(castleId, addressTitle);
    }

}
