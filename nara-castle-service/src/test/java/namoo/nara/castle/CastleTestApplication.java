package namoo.nara.castle;

import namoo.nara.castle.es.config.memory.CastleMemoryEventSpringConfig;
import namoo.nara.share.event.NaraEventProxy;
import namoo.nara.share.event.memory.InMemoryEventQueueProxy;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {
        CastleMemoryEventSpringConfig.class
})
public class CastleTestApplication {

    @Bean
    public NaraEventProxy eventProxy() {
        InMemoryEventQueueProxy eventProxy = new InMemoryEventQueueProxy();
        return eventProxy;
    }

}