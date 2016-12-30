package namoo.nara;

import namoo.nara.castle.sa.rbmp.RabbitEventProxy;
import namoo.nara.share.event.NaraEventProxy;
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

}
