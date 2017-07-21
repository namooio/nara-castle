package nara.castle.actor.akka.projection;

import akka.actor.Props;
import nara.castle.domain.castle.entity.Castellan;
import nara.castle.domain.castle.entity.Contact;
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
        .match(CastleBuilt.class, this::buildCastleBuildReadModel)
        .match(CastellanModified.class, this::buildCastellanModifiedReadModel)
        .match(CastleDemolished.class, this::buildCastleDemolishedReadModel)
        .match(MetroEnrolled.class, this::buildMetroEnrolledReadModel)
        .match(MetroWithdrawn.class, this::buildMetroWithdrawnReadModel)
        .onMessage(event);
    }

    private void buildCastleBuildReadModel(CastleBuilt castleBuilt) {
        //
        Castellan initialState = castleBuilt.getInitialState();
        List<Enrollment> enrollments = initialState.getEnrollments();
        String castellanId = initialState.getId();

        CastellanRM castellanRM = new CastellanRM(initialState);
        List<EnrollmentRM> enrollmentRMS = enrollments.stream().map(enrollment -> new EnrollmentRM(castellanId, enrollment)).collect(Collectors.toList());
        UnitPlateList unitPlateList = UnitPlateRM.extractUnitPlates(castellanId, castellanRM.getContact());

        castellanRMStore.create(castellanRM);
        enrollmentRMStore.create(enrollmentRMS);
        unitPlateRMStore.create(unitPlateList.getUnitPlates());
    }

    private void buildCastellanModifiedReadModel(CastellanModified castellanModified) {
        //
        String castellanId = castellanModified.getCastellanId();
        NameValueList nameValues = castellanModified.getNameValues();

        CastellanRM castellanRM = castellanRMStore.retrieve(castellanId);
        castellanRM.setValues(nameValues);

        castellanRMStore.update(castellanRM);

        if (nameValues.containsName("contact")) {
            Contact contact = castellanRM.getContact();
            UnitPlateList unitPlateList = UnitPlateRM.extractUnitPlates(castellanId, contact);
            unitPlateRMStore.deleteByCastellanId(castellanId);
            unitPlateRMStore.create(unitPlateList.getUnitPlates());
        }
    }

    private void buildCastleDemolishedReadModel(CastleDemolished castleDemolished) {
        //
        String castellanId = castleDemolished.getCastellanId();

        castellanRMStore.delete(castellanId);
        enrollmentRMStore.deleteByCastellanId(castellanId);
        unitPlateRMStore.deleteByCastellanId(castellanId);
    }

    private void buildMetroEnrolledReadModel(MetroEnrolled metroEnrolled) {
        //
        String castellanId = metroEnrolled.getCastellanId();
        Enrollment enrollment = metroEnrolled.getEnrollment();

        EnrollmentRM enrollmentRM = new EnrollmentRM(castellanId, enrollment);
        enrollmentRMStore.create(enrollmentRM);
    }

    private void buildMetroWithdrawnReadModel(MetroWithdrawn metroWithdrawn) {
        //
        Enrollment withdrawnEnrollment = metroWithdrawn.getWithdrawnEnrollment();
        String metroId = withdrawnEnrollment.getMetroId();
        String civilianId = withdrawnEnrollment.getCivilianId();

        EnrollmentRM enrollmentRM = enrollmentRMStore.retrieveByMetroIdAndCivilianId(metroId, civilianId);

        if (enrollmentRM != null) {
            enrollmentRM.setWithdrawn(withdrawnEnrollment.isWithdrawn());
            enrollmentRM.setWithdrawnTime(withdrawnEnrollment.getWithdrawnTime());

            enrollmentRMStore.update(enrollmentRM);
        }
    }

}
