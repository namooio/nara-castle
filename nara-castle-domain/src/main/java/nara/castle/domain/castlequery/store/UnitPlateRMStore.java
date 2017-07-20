package nara.castle.domain.castlequery.store;

import nara.castle.domain.castlequery.model.KeyAttr;
import nara.castle.domain.castlequery.model.UnitPlateRM;

import java.util.List;

public interface UnitPlateRMStore {
    //
    void create(UnitPlateRM unitPlate);
    void create(List<UnitPlateRM> unitPlates);
    UnitPlateRM retrieve(String id);
    List<UnitPlateRM> retrieve(String key, KeyAttr attr);
    List<UnitPlateRM> retrieveByCastellanId(String castellanId);
    void delete(UnitPlateRM unitPlate);
    void deleteByCastellanId(String castellanId);
    boolean exist(KeyAttr keyAttr, String keyValue);
}