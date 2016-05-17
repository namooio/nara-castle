package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.proxy.GatewayProxy;
import namoo.nara.castle.sa.client.GatewayClientProxy;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 17..
 */
public class CastleProxySpringLycler implements CastleProxyLycler {
    //
    @Value("${nara.gateway.api.host}")
    private String naraGatewayApiHost;

    @Override
    public GatewayProxy requestGatewayProxy() {
        //
        return new GatewayClientProxy(naraGatewayApiHost);
    }
}
