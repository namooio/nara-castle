package namoo.nara.castle.adapter.rest;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.JoinedMetro;
import namoo.nara.castle.domain.spec.CastleService;
import namoo.nara.castle.domain.spec.sdo.CastleCdo;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
    public void modifyLocale(String castleId, Locale locale) {
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_LOCALE_MODIFY)
                .addPathParam("id", castleId)
                .setRequestBody(locale)
        );
    }

    @Override
    public Castle findCastle(String castleId) {
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND)
                .addPathParam("id", castleId)
                .setResponseType(Castle.class)
        );
    }

    @Override
    public Castle findCastleByEmail(String email) {
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND_BY_CONDITION)
                .addQueryParam("email", email)
                .setResponseType(Castle.class)
        );
    }

    @Override
    public List<Castle> findCastles() {
        Castle[] castles = naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND_ALL)
                .setResponseType(Castle[].class)
        );
        if (castles == null) return null;
        return Arrays.asList(castles);
    }

    @Override
    public void addEmail(String castleId, String email) {
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_EMAIL_ADD)
                        .addPathParam("id", castleId)
                        .setRequestBody(email)
        );
    }

    @Override
    public void removeEmail(String castleId, String email) {
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_EMAIL_REMOVE)
                        .addPathParam("id", castleId)
                        .setRequestBody(email)
        );
    }

    @Override
    public void addJoinedMetro(String castleId, JoinedMetro joinedMetroCdo) {
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_JOINED_METRO_ADD)
                        .addPathParam("id", castleId)
                        .setRequestBody(joinedMetroCdo)
        );
    }

    @Override
    public List<JoinedMetro> findJoinedMetros(String castleId) {
        JoinedMetro[] joinedMetros = naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_JOINED_METROS_FIND)
                        .addPathParam("id", castleId)
                        .setResponseType(JoinedMetro[].class)
        );
        if (joinedMetros == null) return null;
        return Arrays.asList(joinedMetros);
    }

    @Override
    public void removeJoinedMetro(String castleId, String metroId) {
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_JOINED_METRO_REMOVE)
                        .addPathParam("id", castleId)
                        .addPathParam("metroId", metroId)
        );
    }

    @Override
    public boolean isJoinedMetro(String castleId, String metroId) {
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_JOINED_METRO_EXISTS)
                        .addPathParam("id", castleId)
                        .addPathParam("metroId", metroId)
                        .setResponseType(boolean.class)
        );
    }
}
