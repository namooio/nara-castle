package namoo.nara.castle.sp.springweb;

import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.rep.dto.CastleBuildDto;
import namoo.nara.castle.rep.dto.CastleFindDto;
import namoo.nara.castle.rep.logic.CastleRepServiceLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 9..
 */
@RestController
@RequestMapping("rep/castles")
public class CastleRepResource extends CastleRepServiceLogic {
    //
    @Autowired
    public CastleRepResource(CastleServiceLycler castleServiceLycler) {
        //
        super(castleServiceLycler);
    }


    @Override
    @RequestMapping(value="{id}", method= RequestMethod.POST)
    public void buildCastle(@PathVariable("id") String castleId, @RequestBody CastleBuildDto castleBuildDto) {
        //
        super.buildCastle(castleId, castleBuildDto);
    }

    @Override
    @RequestMapping(value="{id}", method= RequestMethod.GET)
    public CastleFindDto findCastle(@PathVariable("id") String castleId) {
        //
        return super.findCastle(castleId);
    }

    @Override
    @RequestMapping(value="{id}/metro/{metroId}", method= RequestMethod.POST)
    public void addMetro(@PathVariable ("id") String castleId, @PathVariable ("metroId") String metroId) {
        //
        super.addMetro(castleId, metroId);
    }
}
