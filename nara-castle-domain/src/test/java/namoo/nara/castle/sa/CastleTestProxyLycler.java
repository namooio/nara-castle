package namoo.nara.castle.sa;

import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.share.event.global.GlobalEventService;
import namoo.nara.share.event.local.LocalEventQueue;
import namoo.nara.share.event.local.LocalEventService;
import org.mockito.Mockito;

public class CastleTestProxyLycler implements CastleProxyLycler {
    //
    private LocalEventService localEventService;

    public CastleTestProxyLycler(LocalEventQueue eventQueue) {
        //
        this.localEventService = new LocalEventService(eventQueue);
    }

    @Override
    public LocalEventService requestLocalEventService() {
        //
        return localEventService;
    }

    @Override
    public GlobalEventService requestGlobalEventService() {
        //
        return Mockito.mock(GlobalEventService.class);
    }
}
