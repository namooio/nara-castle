package namoo.nara.castle;

import namoo.nara.castle.adapter.rest.CastleRestAdapter;
import namoo.nara.castle.domain.spec.drama.CastleProvider;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CastleTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractCastleApplicationTests {

	@Autowired
	private CastleRestAdapter castleRestAdapter;

	public CastleProvider getCastleRestAdapter() {
		return castleRestAdapter;
	}

	@Before
	public void setUp() {

	}

}
