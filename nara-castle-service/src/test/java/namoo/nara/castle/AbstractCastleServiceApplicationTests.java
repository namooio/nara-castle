package namoo.nara.castle;

import namoo.nara.castle.adapter.service.CastleAdapter;
import namoo.nara.castle.client.CastleClient;
import namoo.nara.share.restclient.springweb.SpringWebRestClient;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CastleServiceApplication.class)
@WebIntegrationTest("server.port:0")
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractCastleServiceApplicationTests {
	//
	private String host = "http://127.0.0.1";

	@Value("${local.server.port}")
	private int port;

	private CastleClient castleClient;

	public CastleAdapter getCastleClient() {
		//
		if (castleClient == null) {
			castleClient = new CastleClient(new SpringWebRestClient(host + ":" + port + "/"));
		}
		return castleClient;
	}

	@Before
	public void setupInitialData() {
//		createCastle();
	}

}
