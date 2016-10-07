package namoo.nara.castle;

import namoo.nara.castle.adapter.CastellanAdapter;
import namoo.nara.castle.adapter.CastleAdapter;
import namoo.nara.castle.adapter.dto.CastellanCreationDto;
import namoo.nara.castle.adapter.dto.CastleBuildDto;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	@Qualifier("CastleClient")
	private CastleAdapter castleClient;

	@Autowired
	@Qualifier("CastellanClient")
	private CastellanAdapter castellanClient;

	public CastleAdapter getCastleClient() {
		return castleClient;
	}

	public CastellanAdapter getCastellanClient() {
		return castellanClient;
	}

	protected String kchuhCastleId;

	@Before
	public void setUp() {
		//
		kchuhCastleId = castleClient.buildCastle(new CastleBuildDto(Locale.KOREA));
		castellanClient.createCastellan(kchuhCastleId, new CastellanCreationDto("kchuh@nextree.co.kr", "1234"));

		String castleId;
		castleId = castleClient.buildCastle(new CastleBuildDto(Locale.KOREA));
		castellanClient.createCastellan(castleId, new CastellanCreationDto("tsong@nextree.co.kr", "1234"));

		castleId = castleClient.buildCastle(new CastleBuildDto(Locale.KOREA));
		castellanClient.createCastellan(castleId, new CastellanCreationDto("hkkang@nextree.co.kr", "1234"));

		castleId = castleClient.buildCastle(new CastleBuildDto(Locale.KOREA));
		castellanClient.createCastellan(castleId, new CastellanCreationDto("jyjung@nextree.co.kr", "1234"));

		castleId = castleClient.buildCastle(new CastleBuildDto(Locale.KOREA));
		castellanClient.createCastellan(castleId, new CastellanCreationDto("iylee@nextree.co.kr", "1234"));
	}

}
