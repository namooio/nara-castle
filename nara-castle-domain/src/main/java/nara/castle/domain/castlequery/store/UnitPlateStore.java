package nara.castle.domain.castlequery.store;

import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.model.UnitPlate;

import java.util.List;

public interface UnitPlateStore {
    //
    void create(UnitPlate unitPlate);
    void create(List<UnitPlate> unitPlates);
    UnitPlate retrieve(String id);
    List<UnitPlate> retrieve(String key, KeyAttr attr);
    List<UnitPlate> retrieveByCastleId(String castleId);
    boolean exist(String key, KeyAttr attr);
    void delete(UnitPlate unitPlate);
    void delete(List<UnitPlate> unitPlates);
    void deleteByCastleId(String castleId);
}