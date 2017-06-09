package namoo.nara.castle;

import namoo.nara.share.event.NaraEventProxy;
import namoo.nara.share.event.rbmq.RabbitEventProxy;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CastleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CastleApplication.class, args);
	}

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Bean
	public NaraEventProxy eventProxy() {
		return new RabbitEventProxy(rabbitTemplate);
	}

}
