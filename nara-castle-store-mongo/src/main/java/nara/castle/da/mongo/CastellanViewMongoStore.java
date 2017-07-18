package nara.castle.da.mongo;

import nara.castle.da.mongo.document.CastellanViewDoc;
import nara.castle.domain.view.CastellanView;
import nara.castle.domain.view.store.CastellanViewStore;
import nara.share.exception.store.NonExistenceException;
import org.mongodb.morphia.Datastore;

import java.util.List;

public class CastellanViewMongoStore implements CastellanViewStore {
    //
    private Datastore datastore;

    public CastellanViewMongoStore(Datastore datastore) {
        //
        this.datastore = datastore;
    }

    @Override
    public void create(CastellanView castellanView) {
        //
        datastore.save(CastellanViewDoc.toDocument(castellanView));
    }

    @Override
    public CastellanView retrieve(String id) {
        //
        CastellanViewDoc castellanViewDoc = datastore.createQuery(CastellanViewDoc.class).field("id").equal(id).get();
        if (castellanViewDoc == null) throw new NonExistenceException(String.format("No castellanView document[%s] found.", id));
        return castellanViewDoc.toDomain();
    }

    @Override
    public List<CastellanView> retrieveAll() {
        //
        List<CastellanViewDoc> castellanViewDocs = datastore.createQuery(CastellanViewDoc.class).asList();
        return CastellanViewDoc.toDomains(castellanViewDocs);
    }

    @Override
    public void update(CastellanView castellanView) {
        //
        if (exists(castellanView.getId())) throw new NonExistenceException(String.format("No castellanView document[%s] found.", castellanView.getId()));
        datastore.save(CastellanViewDoc.toDocument(castellanView));
    }

    @Override
    public void delete(String id) {
        //
        datastore.delete(CastellanViewDoc.class, id);
    }

    @Override
    public boolean exists(String id) {
        //
        return datastore.createQuery(CastellanViewDoc.class).field("id").equal(id).get() != null;
    }
}
