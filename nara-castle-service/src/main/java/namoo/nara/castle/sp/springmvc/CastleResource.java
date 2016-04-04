package namoo.nara.castle.sp.springmvc;

import namoo.nara.castle.domain.entity.CastleState;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.remote.CastleRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 19..
 */
@RestController
@RequestMapping("castle")
public class CastleResource implements CastleRemote {

    @Autowired
    private CastleService castleService;

    @Override
    @RequestMapping(value = "{castleId}/open", method = RequestMethod.PUT)
    public void open(String castleId) {
        castleService.changeCastleStatus(CastleState.Opened, castleId);
    }

    @Override
    @RequestMapping(value = "{castleId}/close", method = RequestMethod.PUT)
    public void close(String castleId) {
        castleService.changeCastleStatus(CastleState.Closed, castleId);
    }

}
