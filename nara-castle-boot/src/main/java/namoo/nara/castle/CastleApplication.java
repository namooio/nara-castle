package namoo.nara.castle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"namoo.nara.castle",
				"namoo.nara.stage"
		}
)
public class CastleApplication {
	//
	public static void main(String[] args) {
		SpringApplication.run(CastleApplication.class, args);
	}

}
