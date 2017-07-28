package nara.castle.da.mongo;

import nara.castle.da.mongo.document.CastellanRMDoc;
import nara.castle.domain.castlequery.model.CastellanRM;
import nara.castle.domain.castlequery.store.CastellanRMStore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;

import java.util.List;
import java.util.Set;

public class CastellanRMMongoStore implements CastellanRMStore {
    //
    private Datastore datastore;

    public CastellanRMMongoStore(Datastore datastore) {
        //
        this.datastore = datastore;
    }

    @Override
    public void create(CastellanRM castellanRM) {
        //
        datastore.save(new CastellanRMDoc(castellanRM));
    }

    @Override
    public CastellanRM retrieve(String id) {
        //
        return datastore.createQuery(CastellanRMDoc.class).field("id").equal(id).get().getRm();
    }

    @Override
    public List<CastellanRM> retrieve(int offset, int limit) {
        //
        Query<CastellanRMDoc> query = datastore.createQuery(CastellanRMDoc.class);
        query = query.order("-id");
        return CastellanRMDoc.toModel(query.asList(new FindOptions().skip(offset).limit(limit)));
    }

    @Override
    public long count() {
        //
        return datastore.createQuery(CastellanRMDoc.class).count();
    }

    @Override
    public List<CastellanRM> retrieve(String lastCastellanId, int limit) {
        //
        Query<CastellanRMDoc> query = datastore.createQuery(CastellanRMDoc.class);
        query = query.order("-id");
        if (lastCastellanId != null) {
            query = query.field("id").lessThan(lastCastellanId);
        }
        return CastellanRMDoc.toModel(query.asList(new FindOptions().limit(limit)));
    }

    @Override
    public List<CastellanRM> retrieveAll() {
        //
        return CastellanRMDoc.toModel(datastore.createQuery(CastellanRMDoc.class).order("-id").asList());
    }

    @Override
    public List<CastellanRM> retrieveByCastellanIds(Set<String> castellanIds) {
        //
        return CastellanRMDoc.toModel(datastore.createQuery(CastellanRMDoc.class).field("id").in(castellanIds).order("-id").asList());
    }

    @Override
    public void update(CastellanRM castellanRM) {
        //
        datastore.save(new CastellanRMDoc(castellanRM));
    }

    @Override
    public void delete(String id) {
        //
        datastore.delete(CastellanRMDoc.class, id);
    }

    @Override
    public boolean exists(String id) {
        //
        return datastore.createQuery(CastellanRMDoc.class).field("id").equal(id).get() != null;
    }

}
