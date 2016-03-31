package namoo.nara.castle;

import namoo.nara.castle.remote.client.CastellanClient;
import namoo.nara.shared.client.NaraConnector;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CastleFrameApplication.class)
@WebIntegrationTest("server.port:0")
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class CastleFrameApplicationTests {

	private String address = "http://127.0.0.1";

	@Value("${local.server.port}")
	private int port;

	private NaraConnector naraConnector;

	private CastellanClient castellanClient;

	private NaraConnector getNaraTestConnector() {
		if (naraConnector == null) {
			naraConnector = new NaraConnector(address + ":" + port + "/");
		}
		return naraConnector;
	}

	public CastellanClient getCastellanClient() {
		if (castellanClient == null) castellanClient = new CastellanClient(getNaraTestConnector());
		return castellanClient;
	}

	@Before
	public void setupInitialData() {
//		createCastle();
	}

}
