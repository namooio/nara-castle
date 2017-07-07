package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.CastleStoreTestApplication;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.MetroEnrollment;
import namoo.nara.castle.domain.store.CastleStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CastleStoreTestApplication.class)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CastleMongoStoreTest {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CastleStore castleStore;

    @Test
    public void test() {
        //
        Castle castle = Castle.getSample();
        castleStore.create(castle);

        castle = castleStore.retrieve(castle.getId());
        logger.debug("{}", castle);

        Optional<MetroEnrollment> enrollment = castle.getEnrollments().stream().findFirst();
        if (enrollment.isPresent()) {
            castle = castleStore.retrieveByEnrolledMetro(enrollment.get().getMetroId(), enrollment.get().getCivilianId());
            logger.debug("{}", castle);
        }

    }
}
