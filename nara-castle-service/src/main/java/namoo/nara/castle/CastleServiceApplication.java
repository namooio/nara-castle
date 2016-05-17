package namoo.nara.castle;

import namoo.nara.castle.cp.spring.CastleProxySpringLycler;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CastleServiceApplication {
	//
	public static void main(String[] args) {
		SpringApplication.run(CastleServiceApplication.class, args);
	}

	@Bean
	public CastleProxyLycler createCastleProxyLycler() {
		//
		return new CastleProxySpringLycler();
	}
}
