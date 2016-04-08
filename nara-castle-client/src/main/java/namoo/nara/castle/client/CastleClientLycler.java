package namoo.nara.castle.client;

import namoo.nara.castle.adapter.service.CastleAdapter;
import namoo.nara.castle.adapter.service.CastleAdapterLycler;
import namoo.nara.share.restclient.NaraRestClientLycler;
import namoo.nara.share.restclient.springweb.SpringWebRestClientLycler;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class CastleClientLycler implements CastleAdapterLycler {
    //
    private CastleClient castleClient;

    public CastleClientLycler(String host) {
        //
//        NaraRestClientLycler naraRestClientLycler = new JaxRSClientLycler(host);
        NaraRestClientLycler naraRestClientLycler = new SpringWebRestClientLycler(host);

        this.castleClient = new CastleClient(naraRestClientLycler);
    }

    @Override
    public CastleAdapter requestCastleAdapter() {
        //
        return castleClient;
    }
}
