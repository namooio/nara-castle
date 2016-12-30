package namoo.nara;

import namoo.nara.castle.es.handler.external.CitizenRegisteredEventHandler;
import namoo.nara.castle.es.listener.rabbit.external.CitizenRegisteredEventRabbitListener;
import namoo.nara.castle.sa.rbmp.RabbitEventProxy;
import namoo.nara.share.event.NaraEventProxy;
import namoo.nara.town.event.CitizenRegisteredEvent;
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

	/************************************************************************************/
	@Bean(name = "citizenRegisteredEventQueue")
	public Queue citizenRegisteredEventQueue() {
		return new Queue("CASTLE.CITIZEN_REGISTERED_EVENT_QUEUE");
	}

	@Bean(name = "citizenRegisteredEventTopicExchange")
	public TopicExchange citizenRegisteredEventTopicExchange() {
		return new TopicExchange(CitizenRegisteredEvent.TOPIC_EXCHANGE);
	}

	@Bean
	public Binding citizenRegisteredEventBinding(
			@Qualifier("citizenRegisteredEventQueue") Queue queue,
			@Qualifier("citizenRegisteredEventTopicExchange") TopicExchange exchange
	) {
		return BindingBuilder.bind(queue).to(exchange).with("*");
	}

	@Bean
	public CitizenRegisteredEventRabbitListener citizenRegisteredEventRabbitListener() {
		CitizenRegisteredEventRabbitListener listener = new CitizenRegisteredEventRabbitListener();
		listener.addHandler(new CitizenRegisteredEventHandler());
		return listener;
	}
	/************************************************************************************/
}
