package namoo.nara.castle.client;

import namoo.nara.castle.adapter.service.CastleAdapter;
import namoo.nara.castle.adapter.service.CastleAdapterLycler;
import namoo.nara.share.restclient.NaraRestClientLycler;
import namoo.nara.share.restclient.springweb.SpringWebRestClientLycler;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class CastleClientAdapterLycler implements CastleAdapterLycler {
    //
    private CastleClientAdapter castleClientAdapter;

    public CastleClientAdapterLycler(String host) {
        //
//        NaraRestClientLycler naraRestClientLycler = new JaxRSClientLycler(host);
        NaraRestClientLycler naraRestClientLycler = new SpringWebRestClientLycler(host);

        this.castleClientAdapter = new CastleClientAdapter(naraRestClientLycler);
    }

    @Override
    public CastleAdapter requestCastleAdapter() {
        //
        return castleClientAdapter;
    }
}
