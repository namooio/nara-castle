package namoo.nara.castle.cp;

import namoo.nara.castle.domain.logic.CastleEventProcessLogic;
import namoo.nara.castle.domain.logic.CastleServiceLogic;
import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.CastleServiceLycler;
import namoo.nara.castle.domain.store.CastleStoreLycler;

public class CastleTestServiceLycler implements CastleServiceLycler {
    //
    private CastleService castleService;
    private CastleEventProcess castleEventProcess;

    public CastleTestServiceLycler(CastleStoreLycler storeLycler, CastleProxyLycler proxyLycler) {
        //
        this.castleService = new CastleServiceLogic(storeLycler, proxyLycler);
        this.castleEventProcess = new CastleEventProcessLogic(storeLycler, proxyLycler);
    }

    @Override
    public CastleService castleService() {
        //
        return castleService;
    }

    @Override
    public CastleEventProcess castleEventProcess() {
        //
        return castleEventProcess;
    }
}
