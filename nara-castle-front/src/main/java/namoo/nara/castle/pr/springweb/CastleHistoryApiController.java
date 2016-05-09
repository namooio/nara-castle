package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.front.CastleHistoryFrontService;
import namoo.nara.castle.front.dto.history.AccountBookDto;
import namoo.nara.castle.front.dto.history.CastleStateBookDto;
import namoo.nara.castle.front.dto.history.MetroBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@RestController
@RequestMapping("api/castles/{id}/histories")
public class CastleHistoryApiController {
    //
    @Autowired
    private CastleHistoryFrontService castleHistoryFrontService;


    @RequestMapping(value="/account-book", method= RequestMethod.GET)
    public AccountBookDto findAccountBook(@PathVariable("id") String castleId) {
        //
        return castleHistoryFrontService.findAccountBook(castleId);
    }

    @RequestMapping(value="/state-book", method= RequestMethod.GET)
    public CastleStateBookDto findStateBook(@PathVariable("id") String castleId) {
        //
        return castleHistoryFrontService.findCastleStateBook(castleId);
    }

    @RequestMapping(value="/metro-book", method= RequestMethod.GET)
    public MetroBookDto findMetroBook(@PathVariable("id") String castleId) {
        //
        return castleHistoryFrontService.findMetroBook(castleId);
    }

}
