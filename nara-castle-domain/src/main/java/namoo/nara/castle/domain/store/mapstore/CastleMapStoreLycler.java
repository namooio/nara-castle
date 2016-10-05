package namoo.nara.castle.domain.store.mapstore;

import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;

public class CastleMapStoreLycler implements CastleStoreLycler {
    //
    private CastleStore castleStore = new CastleMapStore();
    private CastellanStore castellanStore = new CastellanMapStore();

    @Override
    public CastleStore requestCastleStore() {
        //
        return castleStore;
    }

    @Override
    public CastellanStore requestCastellanStore() {
        //
        return castellanStore;
    }
}
