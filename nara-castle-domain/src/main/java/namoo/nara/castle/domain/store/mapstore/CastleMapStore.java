package namoo.nara.castle.domain.store.mapstore;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.store.CastleStore;

import java.util.*;

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
    public Castle retrieveByEmail(String email) {
        //
        Collection<Castle> allCastles = castleMap.values();
        for(Castle castle : allCastles) {
            if (castle.getCastellan().hasEmail(email)) {
                return castle;
            }
        }
        return null;
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
