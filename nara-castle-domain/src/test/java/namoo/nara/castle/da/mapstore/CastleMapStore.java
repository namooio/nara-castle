package namoo.nara.castle.da.mapstore;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.castle.domain.store.CastleStore;

import java.util.HashMap;
import java.util.Map;

public class CastleMapStore implements CastleStore {
    //
    private Map<String, Castle> castleMap = new HashMap<>();
    private CastleBook castleBook = new CastleBook();

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
    public void update(Castle castle) {
        //
        this.castleMap.put(castle.getId(), castle);
    }

    @Override
    public void delete(Castle castle) {
        //
        this.castleMap.remove(castle.getId());
    }

    @Override
    public void delete(String id) {
        //
        this.castleMap.remove(id);
    }

    @Override
    public CastleBook retrieveCastleBook() {
        //
        return this.castleBook;
    }

    @Override
    public CastleBook retrieveCastleBookWithNextCastleSequence() {
        //
        this.castleBook.nextSequence();
        return this.castleBook;
    }

    @Override
    public void updateCastleBook(CastleBook castleBook) {
        //
        this.castleBook = castleBook;
    }

}
