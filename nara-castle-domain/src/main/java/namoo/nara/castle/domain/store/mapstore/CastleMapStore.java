package namoo.nara.castle.domain.store.mapstore;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.store.CastleStore;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CastleMapStore implements CastleStore {
    //
    private Map<String, Castle> castleMap;
    private long castleSequence;

    public CastleMapStore() {
        //
        this.castleMap = new LinkedHashMap<>();
        this.castleSequence = 0;
    }

    @Override
    public void create(Castle castle) {
        //
        this.castleMap.put(castle.getId(), castle);
    }

    @Override
    public Castle retrieve(String id) {
        //
        return this.castleMap.get(id);
    }

    @Override
    public List<Castle> retrieveAll() {
        //
        return new ArrayList<>(this.castleMap.values());
    }

    @Override
    public void update(Castle castle) {
        //
        this.castleMap.put(castle.getId(), castle);
    }

    @Override
    public void delete(String id) {
        //
        this.castleMap.remove(id);
    }

    @Override
    public long retrieveNextSequence() {
        //
        return castleSequence++;
    }
}
