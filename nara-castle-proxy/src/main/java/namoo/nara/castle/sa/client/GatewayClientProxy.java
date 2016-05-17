package namoo.nara.castle.sa.client;

import namoo.nara.castle.domain.proxy.GatewayProxy;
import namoo.nara.gateway.client.GatewayRepClient;
import namoo.nara.gateway.rep.dto.AccountCreationDto;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.jaxrs.JaxRSClient;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 17..
 */
public class GatewayClientProxy implements GatewayProxy {
    //
    private GatewayRepClient gatewayRepClient;

    public GatewayClientProxy(String gatewayApiHost) {
        //
        NaraRestClient naraRestClient = new JaxRSClient(gatewayApiHost);
        gatewayRepClient = new GatewayRepClient(naraRestClient);
    }

    @Override
    public void createNaraAccount(String castleId, String email, String password) {
        //
        AccountCreationDto accountCreationDto = new AccountCreationDto(castleId, email, password);
        gatewayRepClient.createNaraAccount(accountCreationDto);
    }
}
