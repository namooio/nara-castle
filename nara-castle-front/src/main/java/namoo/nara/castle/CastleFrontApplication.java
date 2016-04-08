package namoo.nara.castle;

import namoo.nara.castle.remote.CastellanRemote;
import namoo.nara.castle.remote.client.CastellanClient;
import namoo.nara.share.restclient.NaraConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.nio.charset.Charset;

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
    public CastellanRemote createCasellanRemote() {
        return new CastellanClient(new NaraConnector(castleApiHost));
    }

}
