package namoo.nara.castle.da.mapstore;

import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.domain.store.UnitPlateStore;

public class CastleMapStoreLycler implements CastleStoreLycler {
    //
    private CastleStore castleStore;
    private CastellanStore castellanStore;
    private UnitPlateStore unitPlateStore;

    public CastleMapStoreLycler() {
        //
        castleStore = new CastleMapStore();
        castellanStore = new CastellanMapStore();
        unitPlateStore = new UnitPlateMapStore();
    }

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

    @Override
    public UnitPlateStore requestUnitPlateStore() {
        //
        return unitPlateStore;
    }
}
