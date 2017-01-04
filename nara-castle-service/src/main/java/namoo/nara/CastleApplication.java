package namoo.nara;

import namoo.nara.castle.cp.spring.CastleInitializer;
import namoo.nara.castle.es.handler.internal.CastleBuiltEventHandler;
import namoo.nara.castle.es.listener.internal.CastleBuiltEventRabbitListener;
import namoo.nara.castle.event.CastleBuiltEvent;
import namoo.nara.castle.sa.rbmp.RabbitEventProxy;
import namoo.nara.share.event.NaraEventProxy;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CastleApplication {
	//
	public static void main(String[] args) {
		SpringApplication.run(CastleApplication.class, args);
	}

	@Bean
	public NaraEventProxy eventProxy() {
		//
		return new RabbitEventProxy();
	}

	@Bean
	public CastleInitializer castleTestInitializer() {
		//
		return new CastleInitializer();
	}

	/************************************************************************************/
	@Bean(name = "castleBuiltEventQueue")
	public Queue castleBuiltEventQueue() {
		return new Queue("CASTLE.CASTLE_BUILT_EVENT_QUEUE");
	}

	@Bean(name = "castleBuiltEventTopicExchange")
	public TopicExchange castleBuiltEventTopicExchange() {
		return new TopicExchange(CastleBuiltEvent.TOPIC_EXCHANGE);
	}

	@Bean
	public Binding castleBuiltEventBinding(
			@Qualifier("castleBuiltEventQueue") Queue queue,
			@Qualifier("castleBuiltEventTopicExchange") TopicExchange exchange
	) {
		return BindingBuilder.bind(queue).to(exchange).with("#");
	}

	@Bean
	public CastleBuiltEventRabbitListener castleBuiltEventRabbitListener() {
		CastleBuiltEventRabbitListener listener = new CastleBuiltEventRabbitListener();
		listener.addHandler(new CastleBuiltEventHandler());
		return listener;
	}
	/************************************************************************************/

}
