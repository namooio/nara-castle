package nara.castle.actor.akka.projection;

import akka.actor.Props;
import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castle.entity.Enrollment;
import nara.castle.domain.castle.event.*;
import nara.castle.domain.castlequery.model.CastellanRM;
import nara.castle.domain.castlequery.model.EnrollmentRM;
import nara.castle.domain.castlequery.model.UnitPlateList;
import nara.castle.domain.castlequery.model.UnitPlateRM;
import nara.castle.domain.castlequery.store.CastellanRMStore;
import nara.castle.domain.castlequery.store.CastleRMStoreLycler;
import nara.castle.domain.castlequery.store.EnrollmentRMStore;
import nara.castle.domain.castlequery.store.UnitPlateRMStore;
import nara.share.actor.akka.NaraProjectionActor;
import nara.share.actor.akka.projection.journal.ReadJournalSource;
import nara.share.actor.akka.projection.resume.ResumableProjection;
import nara.share.domain.NameValueList;
import nara.share.domain.event.NaraEvent;

import java.util.List;
import java.util.stream.Collectors;

public class CastleProjectionActor extends NaraProjectionActor {
    //
    private CastellanRMStore castellanRMStore;
    private EnrollmentRMStore enrollmentRMStore;
    private UnitPlateRMStore unitPlateRMStore;

    static public Props props(
            CastleRMStoreLycler storeLycler,
            ReadJournalSource readJournalSource,
            ResumableProjection resumableProjection
    ) {
        //
        return Props.create(CastleProjectionActor.class, () -> new CastleProjectionActor(storeLycler, readJournalSource, resumableProjection));
    }

    public CastleProjectionActor(CastleRMStoreLycler rmStoreLycler, ReadJournalSource readJournalSource, ResumableProjection resumableProjection) {
        //
        super("castle", readJournalSource, resumableProjection);

        castellanRMStore = rmStoreLycler.requestCastellanRMStore();
        enrollmentRMStore = rmStoreLycler.requestEnrollmentRMStore();
        unitPlateRMStore = rmStoreLycler.requestUnitPlateRMStore();
    }

    @Override
    protected void buildReadModel(NaraEvent event) {
        //
        matcher()
        .match(CastleBuiltEvent.class, this::buildCastleBuildReadModel)
        .match(CastellanModifiedEvent.class, this::buildCastellanModifiedReadModel)
        .match(CastleDemolishedEvent.class, this::buildCastleDemolishedReadModel)
        .match(MetroEnrolledEvent.class, this::buildMetroEnrolledReadModel)
        .match(MetroWithdrawnEvent.class, this::buildMetroWithdrawnReadModel)
        .onMessage(event);
    }

    private void buildCastleBuildReadModel(CastleBuiltEvent castleBuiltEvent) {
        //
        Castellan initialState = castleBuiltEvent.getInitialState();
        List<Enrollment> enrollments = initialState.getEnrollments();
        String castellanId = initialState.getId();

        CastellanRM castellanRM = new CastellanRM(initialState);
        List<EnrollmentRM> enrollmentRMS = enrollments.stream().map(enrollment -> new EnrollmentRM(castellanId, enrollment)).collect(Collectors.toList());
        UnitPlateList unitPlateList = UnitPlateRM.extractUnitPlates(castellanRM);

        castellanRMStore.create(castellanRM);
        enrollmentRMStore.create(enrollmentRMS);
        unitPlateRMStore.create(unitPlateList.getUnitPlates());
    }

    private void buildCastellanModifiedReadModel(CastellanModifiedEvent castellanModifiedEvent) {
        //
        String castellanId = castellanModifiedEvent.getCastellanId();
        NameValueList nameValues = castellanModifiedEvent.getNameValues();

        CastellanRM castellanRM = castellanRMStore.retrieve(castellanId);
        castellanRM.setValues(nameValues);

        castellanRMStore.update(castellanRM);

        if (nameValues.containsName("contact")) {
            UnitPlateList unitPlateList = UnitPlateRM.extractUnitPlates(castellanRM);
            unitPlateRMStore.deleteByCastellanId(castellanId);
            unitPlateRMStore.create(unitPlateList.getUnitPlates());
        }
    }

    private void buildCastleDemolishedReadModel(CastleDemolishedEvent castleDemolishedEvent) {
        //
        String castellanId = castleDemolishedEvent.getCastellanId();

        castellanRMStore.delete(castellanId);
        enrollmentRMStore.deleteByCastellanId(castellanId);
        unitPlateRMStore.deleteByCastellanId(castellanId);
    }

    private void buildMetroEnrolledReadModel(MetroEnrolledEvent metroEnrolledEvent) {
        //
        String castellanId = metroEnrolledEvent.getCastellanId();
        Enrollment enrollment = metroEnrolledEvent.getEnrollment();

        EnrollmentRM enrollmentRM = new EnrollmentRM(castellanId, enrollment);
        enrollmentRMStore.create(enrollmentRM);
    }

    private void buildMetroWithdrawnReadModel(MetroWithdrawnEvent metroWithdrawnEvent) {
        //
        Enrollment withdrawnEnrollment = metroWithdrawnEvent.getWithdrawnEnrollment();
        String metroId = withdrawnEnrollment.getMetroId();
        String citizenId = withdrawnEnrollment.getCitizenId();

        EnrollmentRM enrollmentRM = enrollmentRMStore.retrieveByMetroIdAndCitizenId(metroId, citizenId);

        if (enrollmentRM != null) {
            enrollmentRM.setWithdrawn(withdrawnEnrollment.isWithdrawn());
            enrollmentRM.setWithdrawnTime(withdrawnEnrollment.getWithdrawnTime());

            enrollmentRMStore.update(enrollmentRM);
        }
    }

}
