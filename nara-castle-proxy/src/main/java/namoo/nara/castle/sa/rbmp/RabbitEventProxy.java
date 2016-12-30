package namoo.nara.castle.sa.rbmp;

import namoo.nara.share.event.NaraEventProxy;
import namoo.nara.share.event.rbmq.RabbitEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitEventProxy implements NaraEventProxy<RabbitEvent> {
    //
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void create(RabbitEvent rabbitEvent) {
        //
        rabbitTemplate.convertAndSend(rabbitEvent.getTopicExchange(), rabbitEvent.getRoutingKey(), rabbitEvent);
    }
}
