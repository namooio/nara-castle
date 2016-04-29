package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.adapter.CastellanContactAdapter;
import namoo.nara.castle.adapter.dto.contact.AddressBookDto;
import namoo.nara.castle.adapter.dto.contact.EmailBookDto;
import namoo.nara.castle.adapter.dto.contact.NameBookDto;
import namoo.nara.castle.adapter.dto.contact.PhoneBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value="/phone-book", method= RequestMethod.GET)
    public PhoneBookDto findPhoneBook(@PathVariable("id") String castleId) {
        //
        return castellanContactAdapter.findPhoneBook(castleId);
    }

    @RequestMapping(value="/email-book", method= RequestMethod.GET)
    public EmailBookDto findEmailBook(@PathVariable("id") String castleId) {
        //
        return castellanContactAdapter.findEmailBook(castleId);
    }

    @RequestMapping(value="/address-book", method= RequestMethod.GET)
    public AddressBookDto findAddressBook(@PathVariable("id") String castleId) {
        //
        return castellanContactAdapter.findAddressBook(castleId);
    }

}
