package namoo.nara.castle.es.listener.rabbit.external;

import namoo.nara.share.event.NaraEventListener;
import namoo.nara.town.event.CitizenRegisteredEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class CitizenRegisteredEventRabbitListener extends NaraEventListener<CitizenRegisteredEvent> {
    //
    @Override
    @RabbitListener(queues = "CASTLE.CITIZEN_REGISTERED_EVENT_QUEUE")
    public void listen(CitizenRegisteredEvent event) {
        getHandlers().forEach(handler -> handler.handle(event));
    }

}
