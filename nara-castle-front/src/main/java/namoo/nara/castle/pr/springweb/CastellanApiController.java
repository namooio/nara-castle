package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.front.CastellanFrontService;
import namoo.nara.castle.front.dto.CastellanFindDto;
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
@RequestMapping("api/castellans")
public class CastellanApiController {
    //
    @Autowired
    private CastellanFrontService castellanFrontService;

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public CastellanFindDto find(@PathVariable("id") String id) {
        //
        return castellanFrontService.findCastellan(id);
    }

    @RequestMapping(value="/{id}/primary-email", method= RequestMethod.PUT)
    public void modifyPrimaryEmail(@PathVariable("id") String id, @RequestBody String email) {
        //
        castellanFrontService.modifyPrimaryEmail(id, email);
    }

    @RequestMapping(value="/{id}/primary-phone", method= RequestMethod.PUT)
    public void modifyPrimaryPhoneNumber(@PathVariable("id") String id, @RequestBody String phoneNumber) {
        //
        castellanFrontService.modifyPrimaryPhone(id, phoneNumber);
    }

}
