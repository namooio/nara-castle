package nara.castle.da.mongo;

import nara.castle.da.mongo.document.UnitPlateRMDoc;
import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.model.UnitPlateRM;
import nara.castle.domain.castlequery.store.UnitPlateRMStore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;

import java.util.ArrayList;
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
        datastore.save(new UnitPlateRMDoc(unitPlate));
    }

    @Override
    public void create(List<UnitPlateRM> unitPlates) {
        //
        datastore.save(UnitPlateRMDoc.toDocument(unitPlates));
    }

    @Override
    public UnitPlateRM retrieve(String id) {
        //
        return datastore.createQuery(UnitPlateRMDoc.class).field("id").equal(id).get().getRm();
    }

    @Override
    public List<UnitPlateRM> retrieve(KeyAttr keyAttr, String keyValue, String lastCastellanId, int limit) {
        //
        Query<UnitPlateRMDoc> query = datastore.createQuery(UnitPlateRMDoc.class);

        List<Criteria> andCriterias = new ArrayList<>();

        if (lastCastellanId != null) andCriterias.add(query.criteria("rm.castellanId").lessThan(lastCastellanId));
        if (keyAttr != null) andCriterias.add(query.criteria("rm.keyAttr").equal(keyAttr));
        if (keyValue != null) andCriterias.add(query.criteria("rm.keyValue").contains(keyValue));

        query.and(andCriterias.toArray(new Criteria[andCriterias.size()]));
        return UnitPlateRMDoc.toModel(query.order("-rm.castellanId").asList(new FindOptions().limit(limit)));
    }

    @Override
    public List<UnitPlateRM> retrieveByCastellanId(String castellanId) {
        //
        return UnitPlateRMDoc.toModel(
                datastore.createQuery(UnitPlateRMDoc.class).field("rm.castellanId").equal(castellanId).asList()
        );
    }

    @Override
    public void delete(String id) {
        //
        datastore.delete(
                datastore.createQuery(UnitPlateRMDoc.class).field("id").equal(id)
        );
    }

    @Override
    public void deleteByCastellanId(String castellanId) {
        //
        datastore.delete(
                datastore.createQuery(UnitPlateRMDoc.class).field("rm.castellanId").equal(castellanId)
        );
    }

    @Override
    public boolean exists(KeyAttr keyAttr, String keyValue) {
        //
        Query<UnitPlateRMDoc> query = datastore.createQuery(UnitPlateRMDoc.class);
        query.and(
            query.criteria("rm.keyAttr").equal(keyAttr),
            query.criteria("rm.keyValue").equal(keyValue)
        );
        return query.count() > 0;
    }
}
