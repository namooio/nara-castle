package namoo.nara.castle.adapter.rest;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.drama.CastleProvider;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

import java.util.Arrays;
import java.util.List;

public class CastleRestAdapter implements CastleProvider {
    //
    private NaraRestClient naraRestClient;

    public CastleRestAdapter(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public String buildCastle(CastleCdo castleCdo) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_BUILD)
                .setRequestBody(castleCdo)
                .setResponseType(String.class)
        );
    }

    @Override
    public Castle findCastle(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND)
                .addPathParam("castleId", castleId)
                .setResponseType(Castle.class)
        );
    }

    @Override
    public Castle findCastleByEmail(String email) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND_BY_EMAIL)
                .addPathParam("email", email)
                .setResponseType(Castle.class)
        );
    }

    @Override
    public Castle findCastleByJoinedMetro(String nationId, String metroId, String civilianId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND_BY_JOINED_METRO)
                        .addQueryParam("nationId", nationId)
                        .addQueryParam("metroId", metroId)
                        .addQueryParam("civilianId", civilianId)
                        .setResponseType(Castle.class)
        );
    }

    @Override
    public List<Castle> findCastlesOf(String nationId) {
        //
        return Arrays.asList(naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLES_FIND_BY_NATION)
                .addQueryParam("nationId", nationId)
                .setResponseType(Castle[].class)
        ));
    }

    @Override
    public void modifyCastle(String castleId, NameValueList nameValues) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_MODIFY)
                        .addPathParam("castleId", castleId)
                        .setRequestBody(nameValues)
                        .setResponseType(String.class)
        );
    }

    @Override
    public void removeCastle(String castleId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_REMOVE)
                        .addPathParam("castleId", castleId)
        );
    }
}
