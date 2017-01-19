package namoo.nara.castle;

import namoo.nara.castle.adapter.rest.CastleRestAdapter;
import namoo.nara.castle.es.handler.internal.CastleBuiltEventHandler;
import namoo.nara.share.event.NaraEventProxy;
import namoo.nara.share.event.memory.InMemoryEventQueue;
import namoo.nara.share.event.memory.InMemoryEventQueueListener;
import namoo.nara.share.event.memory.InMemoryEventQueueProxy;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.springweb.SpringWebRestClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CastleTestApplication {
    //
    @Bean
    public NaraRestClient naraRestClient() {
        return new SpringWebRestClient("http://127.0.0.1:19030");
    }

    @Bean
    public CastleRestAdapter castleAdapter() {
        //
        return new CastleRestAdapter(naraRestClient());
    }

    @Bean
    public InMemoryEventQueue castleBuiltEventQueue() {
        return new InMemoryEventQueue(10);
    }

    @Bean
    public CastleBuiltEventHandler castleBuiltEventHandler() {
        return new CastleBuiltEventHandler();
    }

    @Bean
    public InMemoryEventQueueListener castleBuiltEventListener() {
        InMemoryEventQueueListener listener = new InMemoryEventQueueListener(castleBuiltEventQueue());
        listener.addHandler(castleBuiltEventHandler());
        new Thread(listener).start();
        return listener;
    }

    @Bean
    public NaraEventProxy eventProxy() {
        //
        InMemoryEventQueueProxy eventProxy = new InMemoryEventQueueProxy();
        return eventProxy;
    }

}