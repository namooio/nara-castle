package namoo.nara.castle.adapter.rest;

import namoo.nara.castle.protocol.CastleProtocol;
import namoo.nara.castle.protocol.sdo.CastleBuildSdo;
import namoo.nara.castle.protocol.sdo.CastleFindSdo;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CastleRestAdapter implements CastleProtocol {
    //
    private NaraRestClient naraRestClient;

    public CastleRestAdapter(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public String buildCastle(CastleBuildSdo castleBuildSdo) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_BUILD)
                .setRequestBody(castleBuildSdo)
                .setResponseType(String.class)
        );
    }

    @Override
    public void modifyLocale(String castleId, Locale locale) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_LOCALE_MODIFY)
                .addPathParam("id", castleId)
                .setRequestBody(locale)
        );
    }

    @Override
    public CastleFindSdo findCastle(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND)
                .addPathParam("id", castleId)
                .setResponseType(CastleFindSdo.class)
        );
    }

    @Override
    public List<CastleFindSdo> findCastles() {
        //
        CastleFindSdo[] castleFindSdos = naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND_ALL)
                .setResponseType(CastleFindSdo[].class)
        );
        if (castleFindSdos == null) return null;
        return Arrays.asList(castleFindSdos);
    }
}
