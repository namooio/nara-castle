package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CastleStoreSpringLycler implements CastleStoreLycler {
    //
    @Autowired
    private CastleStore castleStore;

    @Override
    public CastleStore requestCastleStore() {
        //
        return castleStore;
    }

}
