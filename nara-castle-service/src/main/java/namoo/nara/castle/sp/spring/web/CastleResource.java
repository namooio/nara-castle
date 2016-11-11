package namoo.nara.castle.sp.spring.web;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.JoinedMetro;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.data.CastleCdo;
import namoo.nara.castle.protocol.CastleProtocol;
import namoo.nara.castle.protocol.sdo.CastleBuildSdo;
import namoo.nara.castle.protocol.sdo.CastleSdo;
import namoo.nara.castle.protocol.sdo.JoinedMetroAddSdo;
import namoo.nara.castle.protocol.sdo.JoinedMetroSdo;
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
    public CastleSdo findCastle(
            @PathVariable("id") String castleId
    ) {
        //
        Castle castle = this.castleService.findCastle(castleId);
        return SdoUtils.toCastleSdo(castle);
    }

    @Override
    @RequestMapping(value="castle", method = RequestMethod.GET)
    public CastleSdo findCastleByEmail(
            @RequestParam("email") String email
    ) {
        //
        Castle castle = this.castleService.findCastleByEmail(email);
        return SdoUtils.toCastleSdo(castle);
    }

    @Override
    @RequestMapping(value="castles", method= RequestMethod.GET)
    public List<CastleSdo> findCastles() {
        //
        List<Castle> castles = this.castleService.findCastles();
        return SdoUtils.toCastleSdo(castles);
    }

    @Override
    @RequestMapping(value="castellans/{id}/email", method= RequestMethod.POST)
    public void addEmail(
            @PathVariable("id") String castleId,
            @RequestBody String email
    ) {
        //
        this.castleService.addEmail(castleId, email);
    }

    @Override
    @RequestMapping(value="castellans/{id}/email", method= RequestMethod.DELETE)
    public void removeEmail(
            @PathVariable("id") String castleId,
            @RequestBody String email
    ) {
        //
        this.castleService.removeEmail(castleId, email);
    }

    @Override
    @RequestMapping(value="castellans/{id}/joined-metro", method= RequestMethod.POST)
    public void addJoinedMetro(
            @PathVariable("id") String castleId,
            @RequestBody JoinedMetroAddSdo joinedMetroAddSdo
    ) {
        //
        String metroId = joinedMetroAddSdo.getMetroId();
        String citizenId = joinedMetroAddSdo.getCitizenId();
        this.castleService.addJoinedMetro(castleId, metroId, citizenId);
    }

    @Override
    @RequestMapping(value="castellans/{id}/joined-metros", method= RequestMethod.GET)
    public List<JoinedMetroSdo> findJoinedMetros(
            @PathVariable("id") String castleId
    ) {
        //
        List<JoinedMetro> joinedMetros = castleService.findJoinedMetros(castleId);
        return SdoUtils.toJoinedMetroSdo(joinedMetros);
    }

    @Override
    @RequestMapping(value="castellans/{id}/joined-metros/{metroId}", method= RequestMethod.DELETE)
    public void removeJoinedMetro(
            @PathVariable("id") String castleId,
            @PathVariable("metroId") String metroId
    ) {
        //
        this.castleService.removeJoinedMetro(castleId, metroId);
    }
}
