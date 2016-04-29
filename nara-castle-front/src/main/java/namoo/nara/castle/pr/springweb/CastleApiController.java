package namoo.nara.castle.pr.springweb;

import namoo.nara.castle.adapter.CastellanAdapter;
import namoo.nara.castle.adapter.CastleAdapter;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 18..
 */
@RestController
@RequestMapping("api/castles")
public class CastleApiController {
    //
    @Autowired
    private CastleAdapter castleAdapter;


    @RequestMapping(method = RequestMethod.GET)
    public List<CastleFindDto> findAllCastles() {
        //
        return castleAdapter.findAllCastles();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public CastleFindDto find(@PathVariable("id") String castleId) {
        //
        return castleAdapter.findCastle(castleId);
    }

}
