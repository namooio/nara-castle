package namoo.nara.castle.da.mapstore;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.store.CastleStore;

import java.util.HashMap;
import java.util.Map;

public class CastleMapStore implements CastleStore {
    //
    private Map<String, Castle> castleMap = new HashMap<>();

    @Override
    public void create(Castle castle) {
        //
        this.castleMap.put(castle.getId(), castle);
    }

    @Override
    public Castle retrieve(String castleId) {
        //
        return this.castleMap.get(castleId);
    }

    @Override
    public Castle retrieveByEnrolledMetro(String metroId, String civilianId) {
        //
        return this.castleMap.values()
                .stream()
                .filter(castle -> castle.isEnrolledMetro(metroId, civilianId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Castle castle) {
        //
        this.castleMap.put(castle.getId(), castle);
    }

    @Override
    public void delete(Castle castle) {
        //
        this.castleMap.remove(castle.getId());
    }

}
