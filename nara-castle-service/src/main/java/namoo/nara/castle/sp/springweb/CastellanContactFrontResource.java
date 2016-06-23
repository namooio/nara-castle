package namoo.nara.castle.sp.springweb;

import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.front.dto.contact.AddressBookDto;
import namoo.nara.castle.front.dto.contact.EmailBookDto;
import namoo.nara.castle.front.dto.contact.NameBookDto;
import namoo.nara.castle.front.dto.contact.PhoneBookDto;
import namoo.nara.castle.front.logic.CastellanContactFrontServiceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@RestController
@RequestMapping("api/castellans/{id}/contacts")
public class CastellanContactFrontResource extends CastellanContactFrontServiceLogic {
    //
    @Autowired
    public CastellanContactFrontResource(CastleServiceLycler serviceLycler) {
        super(serviceLycler);
    }


    @Override
    @RequestMapping(value="/name-book", method= RequestMethod.GET)
    public NameBookDto findNameBook(@PathVariable("id") String castleId) {
        return super.findNameBook(castleId);
    }

    @Override
    @RequestMapping(value="/name-book", method=RequestMethod.POST)
    public void attachNameBook(@PathVariable("id") String castleId, @RequestBody NameBookDto nameBookDto) {
        super.attachNameBook(castleId, nameBookDto);
    }

    @Override
    @RequestMapping(value="/phone-book", method= RequestMethod.GET)
    public PhoneBookDto findPhoneBook(@PathVariable("id") String castleId) {
        return super.findPhoneBook(castleId);
    }

    @Override
    @RequestMapping(value="/phone-book", method=RequestMethod.POST)
    public void attachPhoneBook(@PathVariable("id") String castleId, @RequestBody PhoneBookDto phoneBookDto) {
        super.attachPhoneBook(castleId, phoneBookDto);
    }

    @Override
    @RequestMapping(value="/email-book", method= RequestMethod.GET)
    public EmailBookDto findEmailBook(@PathVariable("id") String castleId) {
        return super.findEmailBook(castleId);
    }

    @Override
    @RequestMapping(value="/email-book", method=RequestMethod.POST)
    public void attachEmailBook(@PathVariable("id") String castleId, @RequestBody EmailBookDto emailBookDto) {
        super.attachEmailBook(castleId, emailBookDto);
    }

    @Override
    @RequestMapping(value="/address-book", method= RequestMethod.GET)
    public AddressBookDto findAddressBook(@PathVariable("id") String castleId) {
        return super.findAddressBook(castleId);
    }

    @Override
    @RequestMapping(value="/address-book", method=RequestMethod.POST)
    public void attachAddressBook(@PathVariable("id") String castleId, @RequestBody AddressBookDto addressBookDto) {
        super.attachAddressBook(castleId, addressBookDto);
    }

}
