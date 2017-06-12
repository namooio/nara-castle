package namoo.nara.castle.sp.spring.web;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.share.domain.NameValueList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("castle-api")
public class CastleProvider implements CastleProvider {
    //
    @Autowired
    private CastleProvider castleProvider;

    @Override
    @PostMapping(value="castles")
    public String buildCastle(
            @RequestBody CastleCdo castleCdo
    ) {
        //
        return this.castleProvider.buildCastle(castleCdo);
    }

    @Override
    @GetMapping(value="castles/{id}")
    public Castle findCastle(
            @PathVariable("id") String castleId
    ) {
        //
        return this.castleProvider.findCastle(castleId);
    }

    @Override
    @GetMapping(value="castles/emails/{email:.+}")
    public Castle findCastleByEmail(
            @PathVariable("email") String email
    ) {
        //
        return this.castleProvider.findCastleByEmail(email);
    }

    @Override
    @GetMapping(value="castle")
    public Castle findCastleByJoinedMetro(
            @RequestParam("nationId") String nationId,
            @RequestParam("metroId") String metroId,
            @RequestParam("civilianId") String civilianId
    ) {
        //
        return this.castleProvider.findCastleByJoinedMetro(nationId, metroId, civilianId);
    }

    @Override
    @GetMapping(value="castles")
    public List<Castle> findCastlesOf(
            @RequestParam("nationId") String nationId
    ) {
        //
        return this.castleProvider.findCastlesOf(nationId);
    }

    @Override
    @PutMapping(value="castles/{id}")
    public void modifyCastle(
            @PathVariable("id") String castleId,
            @RequestBody NameValueList nameValues
    ) {
        //
        this.castleProvider.modifyCastle(castleId, nameValues);
    }

    @Override
    @DeleteMapping(value="castles/{id}")
    public void removeCastle(
            @PathVariable("id") String castleId
    ) {
        //
        this.castleProvider.removeCastle(castleId);
    }
}
