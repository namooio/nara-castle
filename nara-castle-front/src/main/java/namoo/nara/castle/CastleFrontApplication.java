package namoo.nara.castle;

import namoo.nara.castle.adapter.service.CastellanAdapter;
import namoo.nara.castle.adapter.service.CastleAdapter;
import namoo.nara.castle.client.CastellanClient;
import namoo.nara.castle.client.CastleClient;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.springweb.SpringWebRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by hkkang on 2016-04-01.
 */
@SpringBootApplication
public class CastleFrontApplication {
    //
    public static void main(String[] args) {
        SpringApplication.run(CastleFrontApplication.class, args);
    }


    @Value("${nara.castle.api.host}")
    private String castleApiHost;


    @Bean
    public CastleAdapter createCastleClient() {
        return new CastleClient(new SpringWebRestClient(castleApiHost));
    }

    @Bean
    public CastellanAdapter createCasellanClient() {
        return new CastellanClient(new SpringWebRestClient(castleApiHost));
    }

}
