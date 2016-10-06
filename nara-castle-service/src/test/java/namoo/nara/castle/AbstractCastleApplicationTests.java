package namoo.nara.castle;

import namoo.nara.castle.front.CastellanContactFrontService;
import namoo.nara.castle.front.CastellanFrontService;
import namoo.nara.castle.front.CastleFrontService;
import namoo.nara.castle.front.CastleHistoryFrontService;
import namoo.nara.castle.rep.CastleRepService;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.jaxrs.JaxRSClient;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CastleTestApplication.class)
@WebIntegrationTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractCastleApplicationTests {
	//
	private NaraRestClient naraRestClient;

	private CastleFrontClient castleFrontClient;
	private CastleHistoryFrontClient castleHistoryFrontClient;
	private CastellanFrontClient castellanFrontClient;
	private CastellanContactFrontClient castellanContactFrontClient;

	@Autowired
	@Qualifier(value = "CastleRepRemoteClient")
	private CastleRepService castleRepRemoteClient;


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

	private NaraRestClient getNaraRestClient() {
		//
		if (naraRestClient == null) {
			naraRestClient = new JaxRSClient("http://127.0.0.1:19030");
		}
		return naraRestClient;
	}


	public CastleRepService getCastleRepClient() {
		//
		return castleRepRemoteClient;
	}

}
