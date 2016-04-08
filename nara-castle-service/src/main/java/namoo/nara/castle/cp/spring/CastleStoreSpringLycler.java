package namoo.nara.castle.cp.spring;

import namoo.nara.castle.domain.store.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
@Component
public class CastleStoreSpringLycler implements CastleStoreLycler {

    @Autowired
    private CastleStore castleStore;

    @Autowired
    private CastellanStore castellanStore;

    @Autowired
    private ContactBundleStore contactBundleStore;

    @Autowired
    private HistoryBundleStore historyBundleStore;

    @Override
    public CastleStore requestCastleStore() {
        return castleStore;
    }

    @Override
    public CastellanStore requestCastellanStore() {
        return castellanStore;
    }

    @Override
    public ContactBundleStore requestContactBundleStore() {
        return contactBundleStore;
    }

    @Override
    public HistoryBundleStore requestHistoryBundleStore() {
        return historyBundleStore;
    }
}
