package namoo.nara.castle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

//    @Bean
//    public Castellan createCasellanClient() {
//        return new CastellanClient(new NaraConnector(castleApiHost));
//    }

}
