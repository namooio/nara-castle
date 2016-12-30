package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.logic.CastleServiceLogic;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class CastleServiceSpringLycler implements CastleServiceLycler {
    //
    @Autowired
    private CastleStoreLycler storeLycler;

    @Autowired
    private CastleProxyLycler proxyLycler;

    @Bean
    @Override
    public CastleService requestCastleService() {
        //
        return new CastleServiceLogic(storeLycler, proxyLycler);
    }

}
