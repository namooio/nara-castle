package namoo.nara.castle;

import namoo.nara.castle.front.CastellanFrontService;
import namoo.nara.castle.front.CastellanContactFrontService;
import namoo.nara.castle.front.CastleFrontService;
import namoo.nara.castle.front.CastleHistoryFrontService;
import namoo.nara.castle.client.CastellanFrontClient;
import namoo.nara.castle.client.CastellanContactFrontClient;
import namoo.nara.castle.client.CastleFrontClient;
import namoo.nara.castle.client.CastleHistoryFrontClient;
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
    public CastleFrontService createCastleClient() {
        return new CastleFrontClient(new SpringWebRestClient(castleApiHost));
    }

    @Bean
    public CastellanFrontService createCastellanClient() {
        return new CastellanFrontClient(new SpringWebRestClient(castleApiHost));
    }

    @Bean
    public CastellanContactFrontService createCastellanContactClient() {
        return new CastellanContactFrontClient(new SpringWebRestClient(castleApiHost));
    }

    @Bean
    public CastleHistoryFrontService createCastleHistoryClient() {
        return new CastleHistoryFrontClient(new SpringWebRestClient(castleApiHost));
    }

}
