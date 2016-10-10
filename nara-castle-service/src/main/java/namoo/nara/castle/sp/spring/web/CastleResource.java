package namoo.nara.castle.sp.spring.web;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.data.CastleCdo;
import namoo.nara.castle.protocol.CastleProtocol;
import namoo.nara.castle.protocol.sdo.CastleBuildSdo;
import namoo.nara.castle.protocol.sdo.CastleFindSdo;
import namoo.nara.castle.sp.util.SdoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("castle-api")
public class CastleResource implements CastleProtocol {
    //
    @Autowired
    private CastleService castleService;

    @Override
    @RequestMapping(value="castle", method= RequestMethod.POST)
    public String buildCastle(
            @RequestBody CastleBuildSdo castleBuildSdo
    ) {
        //
        CastleCdo castleCdo = SdoUtils.toCastleCdo(castleBuildSdo);
        return this.castleService.buildCastle(castleCdo);
    }

    @Override
    @RequestMapping(value="castles/{id}/locale", method= RequestMethod.PUT)
    public void modifyLocale(
            @PathVariable("id") String castleId,
            @RequestBody Locale locale
    ) {
        //
        this.castleService.modifyLocale(castleId, locale);
    }

    @Override
    @RequestMapping(value="castles/{id}", method= RequestMethod.GET)
    public CastleFindSdo findCastle(
            @PathVariable("id") String castleId
    ) {
        //
        Castle castle = this.castleService.findCastle(castleId);
        return SdoUtils.toCastleFindSdo(castle);
    }

    @Override
    @RequestMapping(value="castles", method= RequestMethod.GET)
    public List<CastleFindSdo> findCastles() {
        //
        List<Castle> castles = this.castleService.findCastles();
        return SdoUtils.toCastleFindSdo(castles);
    }
}
