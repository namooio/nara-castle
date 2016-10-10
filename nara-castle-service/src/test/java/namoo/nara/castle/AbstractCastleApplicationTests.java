package namoo.nara.castle;

import namoo.nara.castle.adapter.rest.CastellanRestAdapter;
import namoo.nara.castle.adapter.rest.CastleRestAdapter;
import namoo.nara.castle.protocol.CastellanProtocol;
import namoo.nara.castle.protocol.CastleProtocol;
import namoo.nara.castle.protocol.sdo.CastellanCreationSdo;
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

	@Autowired
	private CastellanRestAdapter castellanRestAdapter;

	public CastleProtocol getCastleRestAdapter() {
		return castleRestAdapter;
	}

	public CastellanProtocol getCastellanRestAdapter() {
		return castellanRestAdapter;
	}

	protected String kchuhCastleId;

	@Before
	public void setUp() {
		//
		kchuhCastleId = castleRestAdapter.buildCastle(new CastleBuildSdo(Locale.KOREA));
		castellanRestAdapter.createCastellan(kchuhCastleId, new CastellanCreationSdo("kchuh@nextree.co.kr", "1234"));

		String castleId;
		castleId = castleRestAdapter.buildCastle(new CastleBuildSdo(Locale.KOREA));
		castellanRestAdapter.createCastellan(castleId, new CastellanCreationSdo("tsong@nextree.co.kr", "1234"));

		castleId = castleRestAdapter.buildCastle(new CastleBuildSdo(Locale.KOREA));
		castellanRestAdapter.createCastellan(castleId, new CastellanCreationSdo("hkkang@nextree.co.kr", "1234"));

		castleId = castleRestAdapter.buildCastle(new CastleBuildSdo(Locale.KOREA));
		castellanRestAdapter.createCastellan(castleId, new CastellanCreationSdo("jyjung@nextree.co.kr", "1234"));

		castleId = castleRestAdapter.buildCastle(new CastleBuildSdo(Locale.KOREA));
		castellanRestAdapter.createCastellan(castleId, new CastellanCreationSdo("iylee@nextree.co.kr", "1234"));
	}

}
