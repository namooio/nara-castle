package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.domain.store.UnitPlateStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CastleStoreSpringLycler implements CastleStoreLycler {
    //
    @Autowired
    private CastleStore castleStore;

    private CastellanStore castellanStore;

    private UnitPlateStore unitPlateStore;

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
