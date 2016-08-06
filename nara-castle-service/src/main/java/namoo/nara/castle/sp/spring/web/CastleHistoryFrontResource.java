package namoo.nara.castle.sp.spring.web;

import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.front.dto.history.AccountBookDto;
import namoo.nara.castle.front.dto.history.CastleStateBookDto;
import namoo.nara.castle.front.dto.history.MetroBookDto;
import namoo.nara.castle.front.logic.CastleHistoryFrontServiceLogic;
import namoo.nara.stage.annotation.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@Feature(name = "Castle History Service")
@RestController
@RequestMapping("castle-api/castles/{id}/histories")
public class CastleHistoryFrontResource extends CastleHistoryFrontServiceLogic {
    //
    @Autowired
    public CastleHistoryFrontResource(CastleServiceLycler serviceLycler) {
        super(serviceLycler);
    }


    @Override
    @RequestMapping(value="/account-book", method= RequestMethod.GET)
    public AccountBookDto findAccountBook(@PathVariable("id") String castleId) {
        return super.findAccountBook(castleId);
    }

    @Override
    @RequestMapping(value="/state-book", method= RequestMethod.GET)
    public CastleStateBookDto findCastleStateBook(@PathVariable("id") String castleId) {
        return super.findCastleStateBook(castleId);
    }

    @Override
    @RequestMapping(value="/metro-book", method= RequestMethod.GET)
    public MetroBookDto findMetroBook(@PathVariable("id") String castleId) {
        return super.findMetroBook(castleId);
    }

}
