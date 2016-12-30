package namoo.nara.castle;

import namoo.nara.castle.adapter.rest.CastleRestAdapter;
import namoo.nara.castle.protocol.CastleProtocol;
import namoo.nara.castle.protocol.sdo.CastleBuildSdo;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Locale;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CastleTestApplication.class)
@WebIntegrationTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractCastleApplicationTests {
	//
	@Autowired
	private CastleRestAdapter castleRestAdapter;

	public CastleProtocol getCastleRestAdapter() {
		return castleRestAdapter;
	}

	protected String kchuhCastleId;

	@Before
	public void setUp() {
		//
		kchuhCastleId = castleRestAdapter.buildCastle(new CastleBuildSdo("kchuh@nextree.co.kr", "1", Locale.KOREA));

		castleRestAdapter.buildCastle(new CastleBuildSdo("tsong@nextree.co.kr", "1", Locale.KOREA));
		castleRestAdapter.buildCastle(new CastleBuildSdo("hkkang@nextree.co.kr", "1", Locale.KOREA));
		castleRestAdapter.buildCastle(new CastleBuildSdo("jyjung@nextree.co.kr", "1", Locale.KOREA));
		castleRestAdapter.buildCastle(new CastleBuildSdo("iylee@nextree.co.kr", "1", Locale.KOREA));
	}

}
