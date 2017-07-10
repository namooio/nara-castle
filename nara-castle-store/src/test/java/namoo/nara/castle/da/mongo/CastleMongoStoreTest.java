package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.CastleStoreTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CastleStoreTestApplication.class)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CastleMongoStoreTest {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CastleViewMongoStore castleViewMongoStore;

    @Test
    public void test() {
        //

    }
}
