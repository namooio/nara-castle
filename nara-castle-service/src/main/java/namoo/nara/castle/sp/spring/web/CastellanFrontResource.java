package namoo.nara.castle.sp.spring.web;

import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.front.dto.CastellanFindDto;
import namoo.nara.castle.front.logic.CastellanFrontServiceLogic;
import namoo.nara.stage.annotation.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@Feature(name = "Castellan Service")
@RestController
@RequestMapping("castle-api/castellans")
public class CastellanFrontResource extends CastellanFrontServiceLogic {
    //
    @Autowired
    public CastellanFrontResource(CastleServiceLycler castleServiceLycler) {
        super(castleServiceLycler);
    }


    @Override
    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public CastellanFindDto findCastellan(@PathVariable("id") String id) {
        return super.findCastellan(id);
    }

    @Override
    @RequestMapping(value="/{id}/display-name", method= RequestMethod.PUT)
    public void modifyDisplayName(@PathVariable("id") String id, @RequestBody String displayName) {
        super.modifyDisplayName(id, displayName);
    }

    @Override
    @RequestMapping(value="/{id}/photo", method= RequestMethod.PUT)
    public void modifyPhoto(@PathVariable("id") String id, @RequestBody String photoId) {
        super.modifyPhoto(id, photoId);
    }

    @Override
    @RequestMapping(value="/{id}/primary-email", method= RequestMethod.PUT)
    public void modifyPrimaryEmail(@PathVariable("id") String id, @RequestBody String email) {
        super.modifyPrimaryEmail(id, email);
    }

    @Override
    @RequestMapping(value="/{id}/primary-phone", method= RequestMethod.PUT)
    public void modifyPrimaryPhone(@PathVariable("id") String id, @RequestBody String phoneNumber) {
        super.modifyPrimaryPhone(id, phoneNumber);
    }

}
