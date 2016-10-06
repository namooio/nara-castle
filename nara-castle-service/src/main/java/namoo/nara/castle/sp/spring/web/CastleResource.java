package namoo.nara.castle.sp.spring.web;

import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import namoo.nara.castle.adapter.logic.CastleAdapterLogic;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("castle-api")
public class CastleResource extends CastleAdapterLogic {
    //
    @Autowired
    public CastleResource(CastleServiceLycler serviceLycler) {
        super(serviceLycler);
    }

    @Override
    @RequestMapping(value="castle", method= RequestMethod.POST)
    public String buildCastle(
            @RequestBody CastleBuildDto castleBuildDto
    ) {
        return super.buildCastle(castleBuildDto);
    }

    @Override
    @RequestMapping(value="castles/{id}/locale", method= RequestMethod.PUT)
    public void modifyLocale(
            @PathVariable("id") String castleId,
            @RequestBody Locale locale
    ) {
        super.modifyLocale(castleId, locale);
    }

    @Override
    @RequestMapping(value="castles/{id}", method= RequestMethod.GET)
    public CastleFindDto findCastle(
            @PathVariable("id") String castleId
    ) {
        return super.findCastle(castleId);
    }

    @Override
    @RequestMapping(value="castles", method= RequestMethod.GET)
    public List<CastleFindDto> findCastles() {
        return super.findCastles();
    }
}
