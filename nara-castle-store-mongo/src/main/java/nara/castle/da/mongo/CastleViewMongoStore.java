//package nara.castle.da.mongo;
//
//import nara.castle.da.mongo.document.CastleViewDoc;
//import nara.share.exception.store.NonExistenceException;
//import org.mongodb.morphia.Datastore;
//import org.mongodb.morphia.query.Query;
//
//import java.util.List;
//
//public class CastleViewMongoStore implements CastleViewStore {
//    //
//    private Datastore datastore;
//
//    public CastleViewMongoStore(Datastore datastore) {
//        //
//        this.datastore = datastore;
//    }
//
//    @Override
//    public void create(CastleView castleView) {
//        //
//        datastore.save(CastleViewDoc.toDocument(castleView));
//    }
//
//    @Override
//    public CastleView retrieve(String id) {
//        //
//        CastleViewDoc castleViewDoc = datastore.createQuery(CastleViewDoc.class).field("id").equal(id).get();
//        if (castleViewDoc == null) throw new NonExistenceException(String.format("No castleView document[%s] found.", id));
//        return castleViewDoc.toDomain();
//    }
//
//    @Override
//    public CastleView retrieveByEnrolledMetro(String metroId, String civilianId) {
//        //
//        Query<CastleViewDoc> query = datastore.createQuery(CastleViewDoc.class);
//        query.and(query.criteria("enrollments.metroId").equal(metroId), query.criteria("enrollments.civilianId").equal(civilianId));
//
//        CastleViewDoc castleViewDoc = query.get();
//        if (castleViewDoc == null) return null;
//        return castleViewDoc.toDomain();
//    }
//
//    @Override
//    public List<CastleView> retrieveAll() {
//        //
//        List<CastleViewDoc> castleViewDocs = datastore.createQuery(CastleViewDoc.class).asList();
//        return CastleViewDoc.toDomains(castleViewDocs);
//    }
//
//    @Override
//    public void update(CastleView castleView) {
//        //
//        if (!exists(castleView.getId())) throw new NonExistenceException(String.format("No castleView document[%s] found.", castleView.getId()));
//        datastore.save(CastleViewDoc.toDocument(castleView));
//    }
//
//    @Override
//    public void delete(String id) {
//        //
//        datastore.delete(CastleViewDoc.class, id);
//    }
//
//    @Override
//    public boolean exists(String id) {
//        //
//        return datastore.createQuery(CastleViewDoc.class).field("id").equal(id).get() != null;
//    }
//
//    @Override
//    public boolean existsByEnrolledMetro(String metroId, String civilianId) {
//        //
//        return retrieveByEnrolledMetro(metroId, civilianId) != null;
//    }
//}
