package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.logic.CastleServiceLogic;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CastleServiceSpringLycler implements CastleServiceLycler {
    //
    @Autowired
    private CastleStoreLycler storeLycler;

    @Bean
    @Override
    public CastleService requestCastleService() {
        //
        return new CastleServiceLogic(storeLycler);
    }

}
