package namoo.nara.castle;

import namoo.nara.castle.client.*;
import namoo.nara.castle.front.CastellanContactFrontService;
import namoo.nara.castle.front.CastellanFrontService;
import namoo.nara.castle.front.CastleFrontService;
import namoo.nara.castle.front.CastleHistoryFrontService;
import namoo.nara.castle.rep.CastleRepService;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.jaxrs.JaxRSClient;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CastleTestApplication.class)
@WebIntegrationTest("server.port:0")
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractCastleApplicationTests {
	//
	private String host = "http://127.0.0.1";

	@Value("${local.server.port}")
	private int port;

	private CastleRepClient castleRepClient;
	private CastleFrontClient castleFrontClient;
	private CastleHistoryFrontClient castleHistoryFrontClient;
	private CastellanFrontClient castellanFrontClient;
	private CastellanContactFrontClient castellanContactFrontClient;

	private NaraRestClient naraRestClient;

	private NaraRestClient getNaraRestClient() {
		//
		if (naraRestClient == null) {
			naraRestClient = new JaxRSClient(host + ":" + port + "/");
		}
		return naraRestClient;
	}

	public CastleRepService getCastleRepClient() {
		//
		if (castleRepClient == null) {
			castleRepClient = new CastleRepClient(getNaraRestClient());
		}
		return castleRepClient;
	}

	public CastleFrontService getCastleFrontClient() {
		//
		if (castleFrontClient == null) {
			castleFrontClient = new CastleFrontClient(getNaraRestClient());
		}
		return castleFrontClient;
	}

	public CastleHistoryFrontService getCastleHistoryFrontClient() {
		//
		if (castleHistoryFrontClient == null) {
			castleHistoryFrontClient = new CastleHistoryFrontClient(getNaraRestClient());
		}
		return castleHistoryFrontClient;
	}

	public CastellanFrontService getCastellanFrontClient() {
		//
		if (castellanFrontClient == null) {
			castellanFrontClient = new CastellanFrontClient(getNaraRestClient());
		}
		return castellanFrontClient;
	}

	public CastellanContactFrontService getCastellanContactFrontClient() {
		//
		if (castellanContactFrontClient == null) {
			castellanContactFrontClient = new CastellanContactFrontClient(getNaraRestClient());
		}
		return castellanContactFrontClient;
	}

}
