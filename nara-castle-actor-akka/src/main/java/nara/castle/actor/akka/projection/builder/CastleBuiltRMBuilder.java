package nara.castle.actor.akka.projection.builder;

import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castle.entity.Enrollment;
import nara.castle.domain.castle.event.CastleBuilt;
import nara.castle.domain.castlequery.model.CastellanRM;
import nara.castle.domain.castlequery.model.EnrollmentRM;
import nara.castle.domain.castlequery.store.CastellanRMStore;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.castle.domain.castlequery.store.EnrollmentRMStore;
import nara.share.actor.akka.projection.ReadModelBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class CastleBuiltRMBuilder implements ReadModelBuilder<CastleBuilt> {
    //
    private CastellanRMStore castellanRMStore;
    private EnrollmentRMStore enrollmentRMStore;

    public CastleBuiltRMBuilder(CastleRMStoreLycler rmStoreLycler) {
        //
        castellanRMStore = rmStoreLycler.requestCastellanRMStore();
        enrollmentRMStore = rmStoreLycler.requestEnrollmentRMStore();
    }

    @Override
    public void build(CastleBuilt event) {
        //
        Castellan initialState = event.getInitialState();
        List<Enrollment> enrollments = initialState.getEnrollments();
        String castellanId = initialState.getId();

        CastellanRM castellanRM = new CastellanRM(initialState);
        List<EnrollmentRM> enrollmentRMS = enrollments.stream().map(enrollment -> new EnrollmentRM(castellanId, enrollment)).collect(Collectors.toList());

        castellanRMStore.create(castellanRM);
        enrollmentRMStore.create(enrollmentRMS);
    }
}
