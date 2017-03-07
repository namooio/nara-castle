package namoo.nara.castle.es.config.memory;

import namoo.nara.castle.es.handler.internal.CastleBuiltEventForJoinedMetroHandler;
import namoo.nara.share.event.memory.InMemoryEventQueue;
import namoo.nara.share.event.memory.InMemoryEventQueueListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class CastleMemoryEventSpringConfig {

    @Autowired private CastleBuiltEventForJoinedMetroHandler castleBuiltEventForJoinedMetroHandler;

    @Bean
    public InMemoryEventQueue castleBuiltEventQueue() {
        return new InMemoryEventQueue(10);
    }


    @Bean
    public InMemoryEventQueueListener castleBuiltEventListener() {
        InMemoryEventQueueListener listener = new InMemoryEventQueueListener(castleBuiltEventQueue());
        listener.addHandler(castleBuiltEventForJoinedMetroHandler);
        new Thread(listener).start();
        return listener;
    }

}
