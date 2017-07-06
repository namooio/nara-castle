package namoo.nara.castle.da.mapstore;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.KeyAttr;
import namoo.nara.castle.domain.entity.UnitPlate;
import namoo.nara.castle.domain.store.CastellanStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CastellanMapStore implements CastellanStore {
    //
    private Map<String, Castellan> castellanMap = new HashMap<>();

    @Override
    public void create(Castellan castellan) {
        //
        this.castellanMap.put(castellan.getId(), castellan);
    }

    @Override
    public Castellan retrieve(String id) {
        //
        return this.castellanMap.get(id);
    }

//    @Override
//    public List<UnitPlate> retrieveUnitPlate(String key, KeyAttr attr) {
//        //
//        return null;
//    }

    @Override
    public void update(Castellan castellan) {
        //
        this.castellanMap.put(castellan.getId(), castellan);
    }

    @Override
    public void delete(Castellan castellan) {
        //
        this.castellanMap.remove(castellan.getId());
    }

    @Override
    public boolean exists(String id) {
        //
        return this.castellanMap.get(id) != null;
    }
}
