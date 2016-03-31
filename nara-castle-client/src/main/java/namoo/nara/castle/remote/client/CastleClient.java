package namoo.nara.castle.remote.client;

import namoo.nara.castle.remote.CastleRemote;
import namoo.nara.shared.client.AbstractClient;
import namoo.nara.shared.client.NaraConnector;
import namoo.nara.shared.client.RequestBuilder;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 19..
 */
public class CastleClient extends AbstractClient implements CastleRemote {

    public CastleClient(NaraConnector naraConnector) {
        super(naraConnector);
    }

    @Override
    public void open(String castleId) {
        sendAndRecieve(RequestBuilder.create(CastleServiceUrl.URL_CASTLE_OPEN).addPathParam("castleId", castleId));
    }

    @Override
    public void close(String castleId) {
        sendAndRecieve(RequestBuilder.create(CastleServiceUrl.URL_CASTLE_CLOSE).addPathParam("castleId", castleId));
    }
}
