package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.logic.CastleEventProcessLogic;
import namoo.nara.castle.domain.logic.CastleServiceLogic;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.spec.CastleEventProcess;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.CastleServiceLycler;
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
    public CastleService castleService() {
        //
        return new CastleServiceLogic(storeLycler, proxyLycler);
    }

    @Override
    public CastleEventProcess castleEventProcess() {
        //
        return new CastleEventProcessLogic(storeLycler, proxyLycler);
    }
}
