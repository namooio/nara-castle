package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.share.event.NaraEventProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CastleProxySpringLycler implements CastleProxyLycler {
    //
    @Autowired
    private NaraEventProxy eventProxy;

    @Override
    public NaraEventProxy eventProxy() {
        //
        return eventProxy;
    }
}
