package namoo.nara.castle;

import namoo.nara.castle.adapter.rest.CastleRestAdapter;
import namoo.nara.share.event.NaraEventProxy;
import namoo.nara.share.event.memory.InMemoryEventQueueProxy;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.springweb.SpringWebRestClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CastleTestApplication {

    @Bean
    public NaraRestClient naraRestClient() {
        return new SpringWebRestClient("http://127.0.0.1:19030");
    }

    @Bean
    public CastleRestAdapter castleRestAdapter() {
        return new CastleRestAdapter(naraRestClient());
    }

    @Bean
    public NaraEventProxy eventProxy() {
        InMemoryEventQueueProxy eventProxy = new InMemoryEventQueueProxy();
        return eventProxy;
    }

}