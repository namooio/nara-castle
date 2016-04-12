package namoo.nara.castle.sp.springweb;

import namoo.nara.castle.adapter.logic.CastellanAdapterLogic;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
@RestController
@RequestMapping("castellan/{id}")
public class CastellanResource extends CastellanAdapterLogic {

    @Autowired
    public CastellanResource(CastleServiceLycler castleServiceLycler) {
        super(castleServiceLycler);
    }

    @Override
    @RequestMapping(value = "displayname", method = RequestMethod.PUT)
    public void modifyDisplayName(@PathVariable("id") String id, @RequestBody String displayName) {
        super.modifyDisplayName(id, displayName);
    }

    @Override
    @RequestMapping(value = "photo", method = RequestMethod.PUT)
    public void modifyPhoto(@PathVariable("id") String id, @RequestBody String photoId) {
        super.modifyPhoto(id, photoId);
    }

    @Override
    @RequestMapping(value = "primaryemail", method = RequestMethod.PUT)
    public void modifyPrimaryEmail(@PathVariable("id") String id, @RequestBody String email) {
        super.modifyPrimaryEmail(id, email);
    }

    @Override
    @RequestMapping(value = "primaryphone", method = RequestMethod.PUT)
    public void modifyPrimaryPhone(@PathVariable("id") String id, @RequestBody String phoneNumber) {
        super.modifyPrimaryPhone(id, phoneNumber);
    }
}
