package namoo.nara.castle;

import namoo.nara.castle.adapter.CastellanAdapter;
import namoo.nara.castle.adapter.CastellanContactAdapter;
import namoo.nara.castle.adapter.CastleAdapter;
import namoo.nara.castle.adapter.CastleHistoryAdapter;
import namoo.nara.castle.client.CastellanClient;
import namoo.nara.castle.client.CastellanContactClient;
import namoo.nara.castle.client.CastleClient;
import namoo.nara.castle.client.CastleHistoryClient;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.jaxrs.JaxRSClient;
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
	private CastleHistoryClient castleHistoryClient;
	private CastellanClient castellanClient;
	private CastellanContactClient castellanContactClient;

	private NaraRestClient naraRestClient;

	private NaraRestClient getNaraRestClient() {
		//
		if (naraRestClient == null) {
			naraRestClient = new JaxRSClient(host + ":" + port + "/");
		}
		return naraRestClient;
	}

	public CastleAdapter getCastleClient() {
		//
		if (castleClient == null) {
			castleClient = new CastleClient(getNaraRestClient());
		}
		return castleClient;
	}

	public CastleHistoryAdapter getCastleHistoryClient() {
		//
		if (castleHistoryClient == null) {
			castleHistoryClient = new CastleHistoryClient(getNaraRestClient());
		}
		return castleHistoryClient;
	}

	public CastellanAdapter getCastellanClient() {
		//
		if (castellanClient == null) {
			castellanClient = new CastellanClient(getNaraRestClient());
		}
		return castellanClient;
	}

	public CastellanContactAdapter getCastellanContactClient() {
		//
		if (castellanContactClient == null) {
			castellanContactClient = new CastellanContactClient(getNaraRestClient());
		}
		return castellanContactClient;
	}

}
