package namoo.nara.castle.domain.store.mapstore;

import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;

public class CastleMapStoreLycler implements CastleStoreLycler {
    //
    private CastleStore castleStore = new CastleMapStore();

    @Override
    public CastleStore requestCastleStore() {
        //
        return castleStore;
    }
}
