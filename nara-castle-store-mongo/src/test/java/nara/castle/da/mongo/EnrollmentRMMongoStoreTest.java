package nara.castle.da.mongo;

import nara.castle.da.AbstractCastleRMStoreTest;
import nara.castle.domain.castlequery.model.EnrollmentRM;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EnrollmentRMMongoStoreTest extends AbstractCastleRMStoreTest {
    //
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {
        //
        EnrollmentRM enrollment = EnrollmentRM.getSample();
        getEnrollmentRMStore().create(enrollment);

        enrollment = getEnrollmentRMStore().retrieve(enrollment.getId());

        logger.debug("{}", enrollment);

        List<EnrollmentRM> enrollments = getEnrollmentRMStore().retrieveByCastellanId(enrollment.getCastellanId());
        Assert.assertEquals(1, enrollments.size());

        enrollment.setWithdrawn(true);
        enrollment.setWithdrawnTime(System.currentTimeMillis());

        getEnrollmentRMStore().update(enrollment);

        enrollment = getEnrollmentRMStore().retrieve(enrollment.getId());
        Assert.assertTrue(enrollment.isWithdrawn());

    }
}
