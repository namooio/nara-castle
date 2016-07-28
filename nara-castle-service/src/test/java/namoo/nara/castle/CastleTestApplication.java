package namoo.nara.castle;

import namoo.nara.castle.rep.CastleRepService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 17..
 */
@SpringBootApplication
public class CastleTestApplication {
    //
    @Bean(name = "CastleRepRemoteClient")
    public HttpInvokerProxyFactoryBean createCastleRepRemoteClient() {
        //
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        httpInvoker.setServiceUrl("http://127.0.0.1:19030/castle-rep/remote/CastleRepService");
        httpInvoker.setServiceInterface(CastleRepService.class);
        return httpInvoker;
    }
}