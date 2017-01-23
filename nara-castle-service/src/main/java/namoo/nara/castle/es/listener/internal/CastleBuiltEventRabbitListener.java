package namoo.nara.castle.es.listener.internal;

import namoo.nara.castle.event.CastleBuiltEvent;
import namoo.nara.share.event.NaraEventListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class CastleBuiltEventRabbitListener extends NaraEventListener<CastleBuiltEvent> {
    //
    @Override
    @RabbitListener(queues = "CASTLE.CASTLE_BUILT_EVENT_QUEUE")
    public void listen(CastleBuiltEvent event) {
        super.listen(event);
    }

}
