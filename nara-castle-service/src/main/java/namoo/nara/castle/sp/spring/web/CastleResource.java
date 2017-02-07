package namoo.nara.castle.sp.spring.web;

import namoo.nara.castle.spec.CastleService;
import namoo.nara.castle.spec.sdo.CastleCdo;
import namoo.nara.castle.spec.sdo.CastleSdo;
import namoo.nara.castle.spec.sdo.JoinedMetroCdo;
import namoo.nara.castle.spec.sdo.JoinedMetroSdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("castle-api")
public class CastleResource implements CastleService {
    //
    @Autowired
    @Qualifier("castleServiceLogic")
    private CastleService castleService;

    @Override
    @RequestMapping(value="castle", method = RequestMethod.POST)
    public String buildCastle(
            @RequestBody CastleCdo castleCdo
    ) {
        return this.castleService.buildCastle(castleCdo);
    }

    @Override
    @RequestMapping(value="castles/{id}/locale", method= RequestMethod.PUT)
    public void modifyLocale(
            @PathVariable("id") String castleId,
            @RequestBody Locale locale
    ) {
        this.castleService.modifyLocale(castleId, locale);
    }

    @Override
    @RequestMapping(value="castles/{id}", method = RequestMethod.GET)
    public CastleSdo findCastle(
            @PathVariable("id") String castleId
    ) {
        return this.castleService.findCastle(castleId);
    }

    @Override
    @RequestMapping(value="castle", method = RequestMethod.GET)
    public CastleSdo findCastleByEmail(
            @RequestParam("email") String email
    ) {
        return this.castleService.findCastleByEmail(email);
    }

    @Override
    @RequestMapping(value="castles", method = RequestMethod.GET)
    public List<CastleSdo> findCastles() {
        return this.castleService.findCastles();
    }

    @Override
    @RequestMapping(value="castellans/{id}/email", method = RequestMethod.POST)
    public void addEmail(
            @PathVariable("id") String castleId,
            @RequestBody String email
    ) {
        this.castleService.addEmail(castleId, email);
    }

    @Override
    @RequestMapping(value="castellans/{id}/email", method= RequestMethod.DELETE)
    public void removeEmail(
            @PathVariable("id") String castleId,
            @RequestBody String email
    ) {
        this.castleService.removeEmail(castleId, email);
    }

    @Override
    @RequestMapping(value="castellans/{id}/joined-metro", method = RequestMethod.POST)
    public void addJoinedMetro(
            @PathVariable("id") String castleId,
            @RequestBody JoinedMetroCdo joinedMetroCdo
    ) {
        this.castleService.addJoinedMetro(castleId, joinedMetroCdo);
    }

    @Override
    @RequestMapping(value="castellans/{id}/joined-metros", method = RequestMethod.GET)
    public List<JoinedMetroSdo> findJoinedMetros(
            @PathVariable("id") String castleId
    ) {
        return castleService.findJoinedMetros(castleId);
    }

    @Override
    @RequestMapping(value="castellans/{id}/joined-metros/{metroId}", method = RequestMethod.DELETE)
    public void removeJoinedMetro(
            @PathVariable("id") String castleId,
            @PathVariable("metroId") String metroId
    ) {
        this.castleService.removeJoinedMetro(castleId, metroId);
    }

    @Override
    @RequestMapping(value = "castellans/{id}/joined-metros/{metroId}/exists", method = RequestMethod.GET)
    public boolean isJoinedMetro(
            @PathVariable("id") String castleId,
            @PathVariable("metroId") String metroId
    ) {
        return castleService.isJoinedMetro(castleId, metroId);
    }
}
