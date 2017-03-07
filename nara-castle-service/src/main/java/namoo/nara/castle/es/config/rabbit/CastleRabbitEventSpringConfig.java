package namoo.nara.castle.es.config.rabbit;

import namoo.nara.castle.es.handler.internal.CastleBuiltEventForJoinedMetroHandler;
import namoo.nara.castle.event.CastleBuiltEvent;
import namoo.nara.share.event.rbmq.RabbitEventListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class CastleRabbitEventSpringConfig {

    private static final String defaultListenerMethod = "listen";

    private static final String castleBuiltEventQueue = "CASTLE.CASTLE_BUILT_EVENT_QUEUE";

    @Autowired private CastleBuiltEventForJoinedMetroHandler castleBuiltEventForJoinedMetroHandler;

    @Autowired private ConnectionFactory connectionFactory;

    private SimpleMessageListenerContainer createMessageListenerContainer(Queue queue, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(queue);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public Queue castleBuiltEventQueue() {
        return new Queue(castleBuiltEventQueue);
    }

    @Bean
    public TopicExchange castleBuiltEventTopicExchange() {
        return new TopicExchange(CastleBuiltEvent.TOPIC_EXCHANGE);
    }

    @Bean
    public Binding castleBuiltEventBinding() {
        return BindingBuilder.bind(castleBuiltEventQueue()).to(castleBuiltEventTopicExchange()).with("#");
    }

    @Bean
    public RabbitEventListener castleBuiltEventRabbitListener() {
        RabbitEventListener listener = new RabbitEventListener();
        listener.addHandler(castleBuiltEventForJoinedMetroHandler);
        return listener;
    }

    @Bean
    public MessageListenerAdapter castleBuiltEventListenerAdapter() {
        return new MessageListenerAdapter(castleBuiltEventRabbitListener(), defaultListenerMethod);
    }

    @Bean
    public SimpleMessageListenerContainer castleBuiltEventListenerContainer() {
        return createMessageListenerContainer(castleBuiltEventQueue(), castleBuiltEventListenerAdapter());
    }

    /************************************************************************************/
}
