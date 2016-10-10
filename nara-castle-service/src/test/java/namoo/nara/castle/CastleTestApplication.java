package namoo.nara.castle;

import namoo.nara.castle.adapter.rest.CastellanRestAdapter;
import namoo.nara.castle.adapter.rest.CastleRestAdapter;
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

    @Bean
    public CastleRestAdapter createCastleRestAdapter() {
        //
        return new CastleRestAdapter(createNaraRestClient());
    }

    @Bean
    public CastellanRestAdapter createCastellanRestAdapter() {
        //
        return new CastellanRestAdapter(createNaraRestClient());
    }
}