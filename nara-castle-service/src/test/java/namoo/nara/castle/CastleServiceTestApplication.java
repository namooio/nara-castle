package namoo.nara.castle;

import namoo.nara.castle.cp.spring.CastleTestProxySpringLycler;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 17..
 */
@SpringBootApplication
public class CastleServiceTestApplication {
    //
    @Bean
    public CastleProxyLycler createCastleProxyLycler() {
        //
        return new CastleTestProxySpringLycler();
    }
}
