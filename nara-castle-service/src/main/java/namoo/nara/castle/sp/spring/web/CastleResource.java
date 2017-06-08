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
    @PostMapping(value="castles")
    public String buildCastle(
            @RequestBody CastleCdo castleCdo
    ) {
        return this.castleService.buildCastle(castleCdo);
    }

    @Override
    @GetMapping(value="castles/{id}")
    public Castle findCastle(
            @PathVariable("id") String castleId
    ) {
        return this.castleService.findCastle(castleId);
    }

    @Override
    @GetMapping(value="castles/emails/{email:.+}")
    public Castle findCastleByEmail(
            @PathVariable("email") String email
    ) {
        return this.castleService.findCastleByEmail(email);
    }

    @Override
    @GetMapping(value="nations/{nationId}/metros/{metroId}/civilians/{civilianId}")
    public Castle findCastleByJoinedMetro(
            @PathVariable("nationId") String nationId,
            @PathVariable("metroId") String metroId,
            @PathVariable("civilianId") String civilianId
    ) {
        return this.castleService.findCastleByJoinedMetro(nationId, metroId, civilianId);
    }

    @Override
    @GetMapping(value="nations/{nationId}/castles")
    public List<Castle> findCastlesOf(
            @PathVariable("nationId") String nationId
    ) {
        return this.castleService.findCastlesOf(nationId);
    }

    @Override
    @PutMapping(value="castles/{id}")
    public void modifyCastle(
            @PathVariable("id") String castleId,
            @RequestBody NameValueList nameValues
    ) {
        this.castleService.modifyCastle(castleId, nameValues);
    }

    @Override
    @DeleteMapping(value="castles/{id}")
    public void removeCastle(
            @PathVariable("id") String castleId
    ) {
        this.castleService.removeCastle(castleId);
    }
}
