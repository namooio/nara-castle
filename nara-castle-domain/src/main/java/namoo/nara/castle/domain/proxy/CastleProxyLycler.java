package namoo.nara.castle.domain.proxy;

import namoo.nara.share.event.global.GlobalEventService;
import namoo.nara.share.event.local.LocalEventService;

public interface CastleProxyLycler {
    //
    LocalEventService requestLocalEventService();
    GlobalEventService requestGlobalEventService();
}
