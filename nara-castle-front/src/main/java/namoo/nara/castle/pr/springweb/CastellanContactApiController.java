package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.front.CastellanContactFrontService;
import namoo.nara.castle.front.dto.contact.*;
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
    private CastellanContactFrontService castellanContactFrontService;


    @RequestMapping(value="/name-book", method= RequestMethod.GET)
    public NameBookDto findNameBook(@PathVariable("id") String castleId) {
        //
        return castellanContactFrontService.findNameBook(castleId);
    }

    @RequestMapping(value="/name-book", method=RequestMethod.POST)
    public void attachNameBook(@PathVariable("id") String castleId, @RequestBody NameBookDto nameBookDto) {
        castellanContactFrontService.attachNameBook(castleId, nameBookDto);
    }


    @RequestMapping(value="/phone-book", method= RequestMethod.GET)
    public PhoneBookDto findPhoneBook(@PathVariable("id") String castleId) {
        //
        return castellanContactFrontService.findPhoneBook(castleId);
    }

    @RequestMapping(value="/phone-book", method=RequestMethod.POST)
    public void attachPhoneBook(@PathVariable("id") String castleId, @RequestBody PhoneBookDto phoneBookDto) {
        castellanContactFrontService.attachPhoneBook(castleId, phoneBookDto);
    }


    @RequestMapping(value="/email-book", method= RequestMethod.GET)
    public EmailBookDto findEmailBook(@PathVariable("id") String castleId) {
        //
        return castellanContactFrontService.findEmailBook(castleId);
    }

    @RequestMapping(value="/email-book", method=RequestMethod.POST)
    public void attachEmailBook(@PathVariable("id") String castleId, @RequestBody EmailBookDto emailBookDto) {
        castellanContactFrontService.attachEmailBook(castleId, emailBookDto);
    }


    @RequestMapping(value="/address-book", method= RequestMethod.GET)
    public AddressBookDto findAddressBook(@PathVariable("id") String castleId) {
        //
        return castellanContactFrontService.findAddressBook(castleId);
    }

    @RequestMapping(value="/address-book", method=RequestMethod.POST)
    public void attachAddressBook(@PathVariable("id") String castleId, @RequestBody AddressBookDto addressBookDto) {
        //
        castellanContactFrontService.attachAddressBook(castleId, addressBookDto);
    }

}
