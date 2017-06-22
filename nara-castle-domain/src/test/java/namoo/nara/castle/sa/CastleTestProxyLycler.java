package namoo.nara.castle.sa;

import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.share.event.worker.ContextEventService;
import namoo.nara.share.event.worker.EventService;

public class CastleTestProxyLycler implements CastleProxyLycler {
    //
    private ContextEventService contextEventService;

    public CastleTestProxyLycler() {
        //
        contextEventService = new ContextEventService();
        contextEventService.start();
    }

    @Override
    public EventService requestEventService() {
        //
        return contextEventService;
    }
}
