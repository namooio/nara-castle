package namoo.nara.castle.sp.spring.web;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.sdo.CastleCdo;
import namoo.nara.share.domain.NameValueList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("castle-api")
public class CastleResource implements CastleService {

    @Autowired
    @Qualifier("castleLogic")
    private CastleService castleService;

    @Override
    @RequestMapping(value="castles", method = RequestMethod.POST)
    public String buildCastle(
            @RequestBody CastleCdo castleCdo
    ) {
        return this.castleService.buildCastle(castleCdo);
    }

    @Override
    @RequestMapping(value="castles/{id}", method = RequestMethod.GET)
    public Castle findCastle(
            @PathVariable("id") String castleId
    ) {
        return this.castleService.findCastle(castleId);
    }

    @Override
    @RequestMapping(value="castles/emails/{email:.+}", method = RequestMethod.GET)
    public Castle findCastleByEmail(
            @PathVariable("email") String email
    ) {
        return this.castleService.findCastleByEmail(email);
    }

    @Override
    @RequestMapping(value="nations/{nationId}/castles", method = RequestMethod.GET)
    public List<Castle> findCastlesOf(
            @PathVariable("nationId") String nationId
    ) {
        return this.castleService.findCastlesOf(nationId);
    }

    @Override
    @RequestMapping(value="castles/{id}", method = RequestMethod.PUT)
    public void modifyCastle(
            @PathVariable("id") String castleId,
            @RequestBody NameValueList nameValues
    ) {
        this.castleService.modifyCastle(castleId, nameValues);
    }

    @Override
    @RequestMapping(value="castles/{id}", method = RequestMethod.DELETE)
    public void removeCastle(
            @PathVariable("id") String castleId
    ) {
        this.castleService.removeCastle(castleId);
    }
}
