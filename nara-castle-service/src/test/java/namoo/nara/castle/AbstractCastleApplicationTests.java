package namoo.nara.castle;

import namoo.nara.castle.adapter.rest.CastleRestAdapter;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.sdo.CastleCdo;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CastleTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractCastleApplicationTests {

	@Autowired
	private CastleRestAdapter castleRestAdapter;

	public CastleService getCastleRestAdapter() {
		return castleRestAdapter;
	}

	protected String kchuhCastleId;

	@Before
	public void setUp() {
		//
		kchuhCastleId = castleRestAdapter.buildCastle(new CastleCdo("kchuh@nextree.co.kr", "1", "1@1", Locale.KOREA));

		castleRestAdapter.buildCastle(new CastleCdo("tsong@nextree.co.kr", "1", "2@1", Locale.KOREA));
		castleRestAdapter.buildCastle(new CastleCdo("hkkang@nextree.co.kr", "1", "3@1", Locale.KOREA));
		castleRestAdapter.buildCastle(new CastleCdo("jyjung@nextree.co.kr", "1", "4@1", Locale.KOREA));
		castleRestAdapter.buildCastle(new CastleCdo("iylee@nextree.co.kr", "1", "5@1", Locale.KOREA));
	}

}
