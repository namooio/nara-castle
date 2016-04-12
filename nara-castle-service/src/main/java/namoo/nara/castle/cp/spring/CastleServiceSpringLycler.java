package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 1..
 */
@Component
public class CastleServiceSpringLycler implements CastleServiceLycler {
    //
    @Autowired
    private CastleService castleService;

    @Autowired
    private CastellanService castellanService;

    @Override
    public CastleService requestCastleService() {
        //
        return castleService;
    }

    @Override
    public CastellanService requestCastellanService() {
        //
        return castellanService;
    }

    @Override
    public CastleHistoryService requestCastleHisotryService() {
        //
        return null;
    }

    @Override
    public CastellanContactService requestCastellanContactService() {
        //
        return null;
    }
}
