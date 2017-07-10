package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.view.store.CastellanViewStore;
import namoo.nara.castle.domain.view.store.CastleViewStore;
import namoo.nara.castle.domain.view.store.CastleViewStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CastleViewStoreSpringLycler implements CastleViewStoreLycler {
    //
    @Autowired
    private CastleViewStore castleViewStore;

    @Autowired
    private CastellanViewStore castellanViewStore;

    @Override
    public CastleViewStore requestCastleViewStore() {
        //
        return castleViewStore;
    }

    @Override
    public CastellanViewStore requestCastellanViewStore() {
        //
        return castellanViewStore;
    }
}
