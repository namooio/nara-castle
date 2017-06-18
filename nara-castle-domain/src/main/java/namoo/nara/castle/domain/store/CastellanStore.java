package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.KeyAttr;
import namoo.nara.castle.domain.entity.UnitPlate;

import java.util.List;

public interface CastellanStore {
    //
    void create(Castellan castellan);
    Castellan retrieve(String id);
    List<UnitPlate> retrieveUnitPlate(String key, KeyAttr attr);
    void update(Castellan castellan);
    void delete(Castellan castellan);

    boolean exists(String castellanId);
}