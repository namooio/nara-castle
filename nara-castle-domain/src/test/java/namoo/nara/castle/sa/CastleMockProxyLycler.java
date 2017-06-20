package namoo.nara.castle.sa;

import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.share.event.proxy.GlobalEventProxy;
import org.mockito.Mockito;

public class CastleMockProxyLycler implements CastleProxyLycler {
    //
    public CastleMockProxyLycler() {
        //
    }

    @Override
    public GlobalEventProxy getGlobalEventProxy() {
        //
        return Mockito.mock(GlobalEventProxy.class);
    }
}
