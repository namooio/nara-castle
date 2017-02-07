package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.logic.CastleServiceLogic;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class CastleServiceSpringLycler {
    //
    @Autowired
    private CastleStoreLycler storeLycler;

    @Autowired
    private CastleProxyLycler proxyLycler;

    @Bean
    public CastleServiceLogic castleServiceLogic() {
        //
        return new CastleServiceLogic(storeLycler, proxyLycler);
    }

}
