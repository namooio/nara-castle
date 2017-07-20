package nara.castle.da.mongo;

import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.model.UnitPlateRM;
import nara.castle.domain.castlequery.store.UnitPlateRMStore;
import org.mongodb.morphia.Datastore;

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

    }

    @Override
    public void create(List<UnitPlateRM> unitPlates) {

    }

    @Override
    public UnitPlateRM retrieve(String id) {
        return null;
    }

    @Override
    public List<UnitPlateRM> retrieve(String key, KeyAttr attr) {
        return null;
    }

    @Override
    public List<UnitPlateRM> retrieveByCastellanId(String castellanId) {
        return null;
    }

    @Override
    public void delete(UnitPlateRM unitPlate) {

    }

    @Override
    public void deleteByCastellanId(String castellanId) {

    }

    @Override
    public boolean exist(KeyAttr keyAttr, String keyValue) {
        return false;
    }
}
