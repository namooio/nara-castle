package namoo.nara.castle;

import namoo.nara.castle.adapter.CastleAdapter;
import namoo.nara.castle.client.CastellanClient;
import namoo.nara.castle.client.CastleClient;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.springweb.SpringWebRestClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CastleTestApplication {
    //
//    @Bean(name = "CastleRepRemoteClient")
//    public HttpInvokerProxyFactoryBean createCastleRepRemoteClient() {
//        //
//        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
//        httpInvoker.setServiceUrl("http://127.0.0.1:19030/castle-rep/remote/CastleRepService");
//        httpInvoker.setServiceInterface(CastleRepService.class);
//        return httpInvoker;
//    }

    @Bean
    public NaraRestClient createNaraRestClient() {
        return new SpringWebRestClient("http://127.0.0.1:19030");
    }

    @Bean(name = "CastleClient")
    public CastleAdapter createCastleClient() {
        //
        return new CastleClient(createNaraRestClient());
    }

    @Bean(name = "CastellanClient")
    public CastellanClient createCastellanClient() {
        //
        return new CastellanClient(createNaraRestClient());
    }
}