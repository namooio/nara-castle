package nara.castle.da.mongo;

import nara.castle.da.mongo.document.UnitPlateRMDoc;
import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.model.UnitPlateRM;
import nara.castle.domain.castlequery.store.UnitPlateRMStore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;

public class UnitPlateRMMongoStore implements UnitPlateRMStore {
    //
    private Datastore datastore;

    public UnitPlateRMMongoStore(Datastore datastore) {
        //
        this.datastore = datastore;
    }

    @Override
    public void create(UnitPlateRM unitPlate) {
        //
        datastore.save(UnitPlateRMDoc.toDocument(unitPlate));
    }

    @Override
    public void create(List<UnitPlateRM> unitPlates) {
        //
        datastore.save(UnitPlateRMDoc.toDocument(unitPlates));
    }

    @Override
    public UnitPlateRM retrieve(String id) {
        //
        return datastore.createQuery(UnitPlateRMDoc.class).field("id").equal(id).get().toModel();
    }

    @Override
    public List<UnitPlateRM> retrieve(KeyAttr keyAttr, String keyValue) {
        //
        Query<UnitPlateRMDoc> query = datastore.createQuery(UnitPlateRMDoc.class);
        query.and(
                query.criteria("keyAttr").equal(keyAttr),
                query.criteria("keyValue").equal(keyValue)
        );
        return UnitPlateRMDoc.toModel(query.asList());
    }

    @Override
    public List<UnitPlateRM> retrieveByCastellanId(String castellanId) {
        //
        return UnitPlateRMDoc.toModel(
                datastore.createQuery(UnitPlateRMDoc.class).field("castellanId").equal(castellanId).asList()
        );
    }

    @Override
    public void delete(String id) {
        //
        datastore.delete(
                datastore.createQuery(UnitPlateRMDoc.class).field("id").equal(id).get()
        );
    }

    @Override
    public void deleteByCastellanId(String castellanId) {
        //
        datastore.delete(
                datastore.createQuery(UnitPlateRMDoc.class).field("castellanId").equal(castellanId).asList()
        );
    }

    @Override
    public boolean exists(KeyAttr keyAttr, String keyValue) {
        //
        return retrieve(keyAttr, keyValue) != null;
    }
}
