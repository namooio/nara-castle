package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.proxy.GatewayProxy;

import static org.mockito.Mockito.mock;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 17..
 */
public class CastleTestProxySpringLycler implements CastleProxyLycler {

    @Override
    public GatewayProxy requestGatewayProxy() {
        //
        GatewayProxy gatewayProxy = mock(GatewayProxy.class);
        return gatewayProxy;
    }
}
