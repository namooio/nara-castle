package namoo.nara.castle.sp.springweb;

import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import namoo.nara.castle.adapter.logic.CastleAdapterLogic;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 19..
 */
@RestController
@RequestMapping("castles")
public class CastleResource extends CastleAdapterLogic {
    //
    @Autowired
    public CastleResource(CastleServiceLycler castleServiceLycler) {
        super(castleServiceLycler);
    }

    @Override
    @RequestMapping(value="/{id}", method = RequestMethod.POST)
    public void buildCastle(@PathVariable("id") String id, @RequestBody CastleBuildDto castleBuildDto) {
        super.buildCastle(id, castleBuildDto);
    }

    @Override
    @RequestMapping(value="/{id}/suspend", method = RequestMethod.PUT)
    public void suspendCastle(@PathVariable("id") String id, @RequestBody String remarks) {
        super.suspendCastle(id, remarks);
    }

    @Override
    @RequestMapping(value="/{id}/reopen", method = RequestMethod.PUT)
    public void reopenCastle(@PathVariable("id") String id, @RequestBody String remarks) {
        super.reopenCastle(id, remarks);
    }

    @Override
    @RequestMapping(value="/{id}/name", method = RequestMethod.PUT)
    public void modifyName(@PathVariable("id") String id, @RequestBody String name) {
        super.modifyName(id, name);
    }

    @Override
    @RequestMapping(value="/{id}/locale", method = RequestMethod.PUT)
    public void modifyLocale(@PathVariable("id") String id, @RequestBody Locale locale) {
        super.modifyLocale(id, locale);
    }

    @Override
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public CastleFindDto findCastle(@PathVariable("id") String id) {
        return super.findCastle(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<CastleFindDto> findAllCastles() {
        return super.findAllCastles();
    }
}
