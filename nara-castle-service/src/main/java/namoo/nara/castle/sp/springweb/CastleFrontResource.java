package namoo.nara.castle.sp.springweb;

import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.front.dto.CastleFindDto;
import namoo.nara.castle.front.logic.CastleFrontServiceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@RestController
@RequestMapping("castle-api/castles")
public class CastleFrontResource extends CastleFrontServiceLogic{
    //
    @Autowired
    public CastleFrontResource(CastleServiceLycler castleServiceLycler) {
        super(castleServiceLycler);
    }


    @Override
    @RequestMapping(method=RequestMethod.GET)
    public List<CastleFindDto> findAllCastles() {
        return super.findAllCastles();
    }

    @Override
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public CastleFindDto findCastle(@PathVariable("id") String id) {
        return super.findCastle(id);
    }

    @Override
    @RequestMapping(value="/{id}/reopen", method=RequestMethod.PUT)
    public void reopenCastle(@PathVariable("id") String id, String remarks) {
        super.reopenCastle(id, "by ststem");
    }

    @Override
    @RequestMapping(value="/{id}/suspend", method=RequestMethod.PUT)
    public void suspendCastle(@PathVariable("id") String id, String remarks) {
        super.suspendCastle(id, "by system.");
    }

    @Override
    @RequestMapping(value="/{id}/name", method=RequestMethod.PUT)
    public void modifyName(@PathVariable("id") String id, @RequestBody String name) {
        super.modifyName(id, name);
    }

    @RequestMapping(value="/{id}/locale", method=RequestMethod.PUT)
    public void modifyLocale(@PathVariable("id") String id, @RequestBody String locale) {
        //
        super.modifyLocale(id, new Locale(locale));
    }

}
