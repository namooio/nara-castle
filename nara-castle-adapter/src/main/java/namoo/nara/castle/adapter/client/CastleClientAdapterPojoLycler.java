package namoo.nara.castle.adapter.client;

import namoo.nara.castle.adapter.service.CastleAdapter;
import namoo.nara.castle.adapter.service.CastleAdapterLycler;
import namoo.nara.share.restclient.NaraConnector;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class CastleClientAdapterPojoLycler implements CastleAdapterLycler {
    //
    private NaraConnector naraConnector;

    public CastleClientAdapterPojoLycler(String host) {
        //
        this.naraConnector = new NaraConnector(host);
    }

    @Override
    public CastleAdapter requestCastleAdapter() {
        //
        return new CastleClientAdapter(naraConnector);
    }
}
