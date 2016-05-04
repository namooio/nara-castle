package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.adapter.CastellanContactAdapter;
import namoo.nara.castle.adapter.dto.contact.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@RestController
@RequestMapping("api/castellans/{id}/contacts")
public class CastellanContactApiController {
    //
    @Autowired
    private CastellanContactAdapter castellanContactAdapter;


    @RequestMapping(value="/name-book", method= RequestMethod.GET)
    public NameBookDto findNameBook(@PathVariable("id") String castleId) {
        //
        return castellanContactAdapter.findNameBook(castleId);
    }

    @RequestMapping(value="/name-book", method=RequestMethod.POST)
    public void attachNameBook(@PathVariable("id") String castleId, @RequestBody NameBookDto nameBookDto) {
        castellanContactAdapter.attachNameBook(castleId, nameBookDto);
    }


    @RequestMapping(value="/phone-book", method= RequestMethod.GET)
    public PhoneBookDto findPhoneBook(@PathVariable("id") String castleId) {
        //
        return castellanContactAdapter.findPhoneBook(castleId);
    }

    @RequestMapping(value="/phone-book", method=RequestMethod.POST)
    public void attachPhoneBook(@PathVariable("id") String castleId, @RequestBody PhoneBookDto phoneBookDto) {
        castellanContactAdapter.attachPhoneBook(castleId, phoneBookDto);
    }


    @RequestMapping(value="/email-book", method= RequestMethod.GET)
    public EmailBookDto findEmailBook(@PathVariable("id") String castleId) {
        //
        return castellanContactAdapter.findEmailBook(castleId);
    }

    @RequestMapping(value="/email-book", method=RequestMethod.POST)
    public void attachEmailBook(@PathVariable("id") String castleId, @RequestBody EmailBookDto emailBookDto) {
        castellanContactAdapter.attachEmailBook(castleId, emailBookDto);
    }


    @RequestMapping(value="/address-book", method= RequestMethod.GET)
    public AddressBookDto findAddressBook(@PathVariable("id") String castleId) {
        //
        return castellanContactAdapter.findAddressBook(castleId);
    }

    @RequestMapping(value="/address-book", method=RequestMethod.POST)
    public void attachAddressBook(@PathVariable("id") String castleId, @RequestBody AddressBookDto addressBookDto) {
        //
        castellanContactAdapter.attachAddressBook(castleId, addressBookDto);
    }

}
