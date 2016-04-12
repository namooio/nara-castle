package namoo.nara.castle.sp.springweb;

import namoo.nara.castle.adapter.dto.history.AccountBookDto;
import namoo.nara.castle.adapter.dto.history.CastleStateBookDto;
import namoo.nara.castle.adapter.dto.history.MetroBookDto;
import namoo.nara.castle.adapter.logic.CastleHistoryAdapterLogic;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
@RestController
@RequestMapping("castle/{id}/history")
public class CastleHistoryResource extends CastleHistoryAdapterLogic {

    @Autowired
    public CastleHistoryResource(CastleServiceLycler serviceLycler) {
        super(serviceLycler);
    }

    @Override
    @RequestMapping(value = "accountbook", method = RequestMethod.POST)
    public void attachAccountBook(@PathVariable("id") String castleId, @RequestBody AccountBookDto accountBookDto) {
        super.attachAccountBook(castleId, accountBookDto);
    }

    @Override
    @RequestMapping(value = "accountbook", method = RequestMethod.DELETE)
    public void detachAccountBook(@PathVariable("id") String castleId) {
        super.detachAccountBook(castleId);
    }

    @Override
    @RequestMapping(value = "accountbook", method = RequestMethod.GET)
    public AccountBookDto findAccountBook(@PathVariable("id") String castleId) {
        return super.findAccountBook(castleId);
    }

    @Override
    @RequestMapping(value = "statebook", method = RequestMethod.POST)
    public void attachCastleStateBook(@PathVariable("id") String castleId, @RequestBody CastleStateBookDto castleStateBookDto) {
        super.attachCastleStateBook(castleId, castleStateBookDto);
    }

    @Override
    @RequestMapping(value = "statebook", method = RequestMethod.DELETE)
    public void detachCastleStateBook(@PathVariable("id") String castleId) {
        super.detachCastleStateBook(castleId);
    }

    @Override
    @RequestMapping(value = "statebook", method = RequestMethod.GET)
    public CastleStateBookDto findCastleStateBook(@PathVariable("id") String castleId) {
        return super.findCastleStateBook(castleId);
    }

    @Override
    @RequestMapping(value = "metrobook", method = RequestMethod.POST)
    public void attachMetroBook(@PathVariable("id") String castleId, @RequestBody MetroBookDto metroBookDto) {
        super.attachMetroBook(castleId, metroBookDto);
    }

    @Override
    @RequestMapping(value = "metrobook", method = RequestMethod.DELETE)
    public void detatchMetroBook(@PathVariable("id") String castleId) {
        super.detatchMetroBook(castleId);
    }

    @Override
    @RequestMapping(value = "metrobook", method = RequestMethod.GET)
    public MetroBookDto findMetroBook(@PathVariable("id") String castleId) {
        return super.findMetroBook(castleId);
    }
}
