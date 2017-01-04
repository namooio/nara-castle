package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.context.CastleContext;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CastleTestInitializer implements ApplicationContextAware {
    //
    @Autowired
    private CastleServiceLycler serviceLycler;

    @Autowired
    private CastleProxyLycler proxyLycler;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //
        CastleContext.setServiceLycler(serviceLycler);
        CastleContext.setProxyLycler(proxyLycler);
    }
}
