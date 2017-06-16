package namoo.nara.castle.da.mapstore;

import namoo.nara.castle.domain.entity.KeyAttr;
import namoo.nara.castle.domain.entity.UnitPlate;
import namoo.nara.castle.domain.store.UnitPlateStore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UnitPlateMapStore implements UnitPlateStore {
    //
    private Map<String, UnitPlate> unitPlateMap = new HashMap<>();

    @Override
    public void create(UnitPlate unitPlate) {
        //
        this.unitPlateMap.put(unitPlate.getId(), unitPlate);
    }

    @Override
    public void create(List<UnitPlate> unitPlates) {
        //
        unitPlates.forEach(unitPlate -> create(unitPlate));
    }

    @Override
    public UnitPlate retrieve(String id) {
        //
        return unitPlateMap.get(id);
    }

    @Override
    public List<UnitPlate> retrieve(String key, KeyAttr attr) {
        //
        return unitPlateMap.values()
                .stream()
                .filter(unitPlate -> unitPlate.getKey().equals(key) && unitPlate.getAttr().equals(attr))
                .collect(Collectors.toList());
    }

    @Override
    public List<UnitPlate> retrieveByCastleId(String castleId) {
        //
        return unitPlateMap.values()
                .stream()
                .filter(unitPlate -> unitPlate.getCastleId().equals(castleId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean exist(String key, KeyAttr attr) {
        //
        return this.retrieve(key, attr) != null;
    }

    @Override
    public void delete(UnitPlate unitPlate) {
        //
        this.unitPlateMap.remove(unitPlate.getId());
    }

    @Override
    public void delete(List<UnitPlate> unitPlates) {
        //
        unitPlates.forEach(unitPlate -> this.delete(unitPlate));
    }

    @Override
    public void deleteByCastleId(String castleId) {
        //
        List<UnitPlate> unitPlates = retrieveByCastleId(castleId);
        this.delete(unitPlates);
    }
}
