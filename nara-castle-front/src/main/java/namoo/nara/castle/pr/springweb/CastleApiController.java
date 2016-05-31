package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.front.CastleFrontService;
import namoo.nara.castle.front.dto.CastleFindDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@RestController
@RequestMapping("api/castles")
public class CastleApiController {
    //
    @Autowired
    private CastleFrontService castleFrontService;


    @RequestMapping(method=RequestMethod.GET)
    public List<CastleFindDto> findAllCastles() {
        //
        return castleFrontService.findAllCastles();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public CastleFindDto find(@PathVariable("id") String castleId) {
        //
        return castleFrontService.findCastle(castleId);
    }

    @RequestMapping(value="/{id}/reopen", method=RequestMethod.PUT)
    public void reopen(@PathVariable("id") String castleId) {
        castleFrontService.reopenCastle(castleId, "by system.");
    }

    @RequestMapping(value="/{id}/suspend", method=RequestMethod.PUT)
    public void suspend(@PathVariable("id") String castleId) {
        castleFrontService.suspendCastle(castleId, "by system.");
    }

    @RequestMapping(value="/{id}/name", method=RequestMethod.PUT)
    public void modifyName(@PathVariable("id") String castleId, @RequestBody String name) {
        castleFrontService.modifyName(castleId, name);
    }

    @RequestMapping(value="/{id}/locale", method=RequestMethod.PUT)
    public void modifyLocale(@PathVariable("id") String castleId, @RequestBody String locale) {
        castleFrontService.modifyLocale(castleId, new Locale(locale));
    }

}
