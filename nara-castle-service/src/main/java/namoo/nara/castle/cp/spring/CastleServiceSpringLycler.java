package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.service.CastellanService;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
