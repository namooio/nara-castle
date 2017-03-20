package namoo.nara.castle.domain.proxy.mockproxy;

import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.share.event.NaraEventProxy;
import namoo.nara.share.event.mock.NaraEventMockProxy;

public class CastleMockProxyLycler implements CastleProxyLycler {
    //
    private NaraEventProxy eventProxy = new NaraEventMockProxy();

    @Override
    public NaraEventProxy eventProxy() {
        return eventProxy;
    }
}
