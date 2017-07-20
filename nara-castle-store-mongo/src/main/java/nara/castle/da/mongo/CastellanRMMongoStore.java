package nara.castle.da.mongo;

import nara.castle.da.mongo.document.CastellanRMDoc;
import nara.castle.domain.castlequery.model.CastellanRM;
import nara.castle.domain.castlequery.store.CastellanRMStore;
import org.mongodb.morphia.Datastore;

import java.util.List;

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
        datastore.save(CastellanRMDoc.toDocument(castellanRM));
    }

    @Override
    public CastellanRM retrieve(String id) {
        //
        return datastore.createQuery(CastellanRMDoc.class).field("id").equal(id).get().toModel();
    }

    @Override
    public List<CastellanRM> retrieveAll() {
        //
        return CastellanRMDoc.toModel(datastore.createQuery(CastellanRMDoc.class).asList());
    }

    @Override
    public void update(CastellanRM castellanRM) {
        //
        datastore.save(CastellanRMDoc.toDocument(castellanRM));
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
