package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.KeyAttr;
import namoo.nara.castle.domain.entity.UnitPlate;

import java.util.List;

public interface UnitPlateStore {
    //
    void create(UnitPlate unitPlate);

    // FIXME Transaction issue
    void create(List<UnitPlate> unitPlates);
    UnitPlate retrieve(String id);
    List<UnitPlate> retrieve(String key, KeyAttr attr);
    List<UnitPlate> retrieveByCastleId(String castleId);
    boolean exist(String key, KeyAttr attr);
    void delete(UnitPlate unitPlate);

    // FIXME Transaction issue
    void delete(List<UnitPlate> unitPlates);
    void deleteByCastleId(String castleId);
}