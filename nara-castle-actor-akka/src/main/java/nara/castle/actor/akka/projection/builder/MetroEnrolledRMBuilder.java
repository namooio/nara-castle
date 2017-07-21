package nara.castle.actor.akka.projection.builder;

import nara.castle.domain.castle.entity.Enrollment;
import nara.castle.domain.castle.event.MetroEnrolled;
import nara.castle.domain.castlequery.model.EnrollmentRM;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.castle.domain.castlequery.store.EnrollmentRMStore;
import nara.share.actor.akka.projection.ReadModelBuilder;

public class MetroEnrolledRMBuilder implements ReadModelBuilder<MetroEnrolled> {
    //
    private EnrollmentRMStore enrollmentRMStore;

    public MetroEnrolledRMBuilder(CastleRMStoreLycler rmStoreLycler) {
        //
        enrollmentRMStore = rmStoreLycler.requestEnrollmentRMStore();
    }

    @Override
    public void build(MetroEnrolled event) {
        //
        String castellanId = event.getCastellanId();
        Enrollment enrollment = event.getEnrollment();

        EnrollmentRM enrollmentRM = new EnrollmentRM(castellanId, enrollment);
        enrollmentRMStore.create(enrollmentRM);
    }
}
