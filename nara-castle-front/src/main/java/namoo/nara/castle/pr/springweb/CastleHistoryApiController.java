package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.adapter.CastleHistoryAdapter;
import namoo.nara.castle.adapter.dto.history.AccountBookDto;
import namoo.nara.castle.adapter.dto.history.CastleStateBookDto;
import namoo.nara.castle.adapter.dto.history.MetroBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@RestController
@RequestMapping("api/castle")
public class CastleHistoryApiController {
    //
    @Autowired
    private CastleHistoryAdapter castleHistoryAdapter;


    @RequestMapping(value="/{id}/account-book", method= RequestMethod.GET)
    public AccountBookDto findAccountBook(@PathVariable("id") String castleId) {
        //
        return castleHistoryAdapter.findAccountBook(castleId);
    }

    @RequestMapping(value="/{id}/state-book", method= RequestMethod.GET)
    public CastleStateBookDto findStateBook(@PathVariable("id") String castleId) {
        //
        return castleHistoryAdapter.findCastleStateBook(castleId);
    }

    @RequestMapping(value="/{id}/metro-book", method= RequestMethod.GET)
    public MetroBookDto findMetroBook(@PathVariable("id") String castleId) {
        //
        return castleHistoryAdapter.findMetroBook(castleId);
    }

}
