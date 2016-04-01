package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 1..
 */
@Component
public class CastleServiceSpringLycler implements CastleServiceLycler {

    @Autowired
    private CastleService castleService;

    @Override
    public CastleService requestCastleService() {
        return castleService;
    }
}
