package nara.castle.da.mongo;

import nara.castle.da.mongo.document.EnrollmentRMDoc;
import nara.castle.domain.castlequery.model.EnrollmentRM;
import nara.castle.domain.castlequery.store.EnrollmentRMStore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;

public class EnrollmentRMMongoStore implements EnrollmentRMStore {
    //
    private Datastore datastore;

    public EnrollmentRMMongoStore(Datastore datastore) {
        //
        this.datastore = datastore;
    }

    @Override
    public void create(EnrollmentRM enrollmentRM) {
        //
        datastore.save(new EnrollmentRMDoc(enrollmentRM));
    }

    @Override
    public void create(List<EnrollmentRM> enrollmentRMS) {
        //
        datastore.save(EnrollmentRMDoc.toDocument(enrollmentRMS));
    }

    @Override
    public EnrollmentRM retrieve(String id) {
        //
        return datastore.createQuery(EnrollmentRMDoc.class).field("id").equal(id).get().getEnrollmentRM();
    }

    @Override
    public List<EnrollmentRM> retrieveByCastellanId(String castellanId) {
        //
        return EnrollmentRMDoc.toModel(
                datastore.createQuery(EnrollmentRMDoc.class).field("enrollmentRM.castellanId").equal(castellanId).asList()
        );
    }

    @Override
    public EnrollmentRM retrieveByMetroIdAndCitizenId(String metroId, String citizenId) {
        //
        Query<EnrollmentRMDoc> query = datastore.createQuery(EnrollmentRMDoc.class);
        query.and(
                query.criteria("enrollmentRM.metroId").equal(metroId),
                query.criteria("enrollmentRM.citizenId").equal(citizenId)
        );

        EnrollmentRMDoc enrollmentRMDoc = query.get();
        if (enrollmentRMDoc == null) return null;
        return enrollmentRMDoc.getEnrollmentRM();
    }

    @Override
    public void update(EnrollmentRM enrollmentRM) {
        //
        datastore.save(new EnrollmentRMDoc(enrollmentRM));
    }

    @Override
    public void delete(String id) {
        //
        datastore.delete(EnrollmentRMDoc.class, id);
    }

    @Override
    public void deleteByCastellanId(String castellanId) {
        //
        datastore.delete(datastore.createQuery(EnrollmentRMDoc.class).field("enrollmentRM.castellanId").equal(castellanId).asList());
    }

    @Override
    public boolean exists(String id) {
        //
        return datastore.createQuery(EnrollmentRMDoc.class).field("id").equal(id).get() != null;
    }
}
