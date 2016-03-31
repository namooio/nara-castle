package namoo.nara.castle.cp.lifecycler;

import namoo.nara.castle.domain.lifecycler.CastleComponentLifecycler;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.store.CastellanEmailStore;
import namoo.nara.castle.domain.store.CastellanNameStore;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
@Component
public class CastleComponentSpringLifecycler implements CastleComponentLifecycler {

    @Autowired
    private CastleStore castleStore;

    @Autowired
    private CastellanStore castellanStore;

    @Autowired
    private CastellanEmailStore castellanEmailStore;

    @Autowired
    private CastellanNameStore castellanNameStore;

    @Autowired
    private CastleService castleService;

    @Override
    public CastellanEmailStore requestCastellanEmailStore() {
        return castellanEmailStore;
    }

    @Override
    public CastellanNameStore requestCastellanNameStore() {
        return castellanNameStore;
    }

    @Override
    public CastellanStore requestCastellanStore() {
        return castellanStore;
    }

    @Override
    public CastleStore requestCastleStore() {
        return castleStore;
    }

    @Override
    public CastleService requestCastleService() {
        return castleService;
    }
}
