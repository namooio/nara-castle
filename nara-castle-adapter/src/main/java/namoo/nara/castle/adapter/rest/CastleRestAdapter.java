package namoo.nara.castle.adapter.rest;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.sdo.CastleCdo;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

import java.util.Arrays;
import java.util.List;

public class CastleRestAdapter implements CastleService {

    private NaraRestClient naraRestClient;

    public CastleRestAdapter(NaraRestClient naraRestClient) {
        this.naraRestClient = naraRestClient;
    }

    @Override
    public String buildCastle(CastleCdo castleCdo) {
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_BUILD)
                .setRequestBody(castleCdo)
                .setResponseType(String.class)
        );
    }

    @Override
    public Castle findCastle(String castleId) {
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND)
                .addPathParam("castleId", castleId)
                .setResponseType(Castle.class)
        );
    }

    @Override
    public Castle findCastleByNationIdAndEmail(String nationId, String email) {
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND_BY_NATION_AND_EMAIL)
                .addPathParam("nationId", nationId)
                .addPathParam("email", email)
                .setResponseType(Castle.class)
        );
    }

    @Override
    public List<Castle> findCastlesOf(String nationId) {
        return Arrays.asList(naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND_ALL)
                .addPathParam("nationId", nationId)
                .setResponseType(Castle[].class)
        ));
    }

    @Override
    public void modifyCastle(String castleId, NameValueList nameValues) {
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_BUILD)
                        .addPathParam("castleId", castleId)
                        .setRequestBody(nameValues)
                        .setResponseType(String.class)
        );
    }

    @Override
    public void removeCastle(String castleId) {
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_BUILD)
                        .addPathParam("castleId", castleId)
        );
    }
}
