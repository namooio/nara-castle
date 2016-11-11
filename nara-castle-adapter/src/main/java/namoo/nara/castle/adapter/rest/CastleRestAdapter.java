package namoo.nara.castle.adapter.rest;

import namoo.nara.castle.protocol.CastleProtocol;
import namoo.nara.castle.protocol.sdo.CastleBuildSdo;
import namoo.nara.castle.protocol.sdo.CastleSdo;
import namoo.nara.castle.protocol.sdo.JoinedMetroAddSdo;
import namoo.nara.castle.protocol.sdo.JoinedMetroSdo;
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
    public CastleSdo findCastle(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND)
                .addPathParam("id", castleId)
                .setResponseType(CastleSdo.class)
        );
    }

    @Override
    public CastleSdo findCastleByEmail(String email) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND_BY_CONDITION)
                .addQueryParam("email", email)
                .setResponseType(CastleSdo.class)
        );
    }

    @Override
    public List<CastleSdo> findCastles() {
        //
        CastleSdo[] castleSdos = naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTLE_FIND_ALL)
                .setResponseType(CastleSdo[].class)
        );
        if (castleSdos == null) return null;
        return Arrays.asList(castleSdos);
    }

    @Override
    public void addEmail(String castleId, String email) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_EMAIL_ADD)
                        .addPathParam("id", castleId)
                        .setRequestBody(email)
        );
    }

    @Override
    public void removeEmail(String castleId, String email) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_EMAIL_REMOVE)
                        .addPathParam("id", castleId)
                        .setRequestBody(email)
        );
    }

    @Override
    public void addJoinedMetro(String castleId, JoinedMetroAddSdo joinedMetroAddSdo) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_JOINED_METRO_ADD)
                        .addPathParam("id", castleId)
                        .setRequestBody(joinedMetroAddSdo)
        );
    }

    @Override
    public List<JoinedMetroSdo> findJoinedMetros(String castleId) {
        //
        JoinedMetroSdo[] joinedMetros = naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_JOINED_METROS_FIND)
                        .addPathParam("id", castleId)
                        .setResponseType(JoinedMetroSdo[].class)
        );
        if (joinedMetros == null) return null;
        return Arrays.asList(joinedMetros);
    }

    @Override
    public void removeJoinedMetro(String castleId, String metroId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_JOINED_METRO_REMOVE)
                        .addPathParam("id", castleId)
                        .addPathParam("metroId", metroId)
        );
    }
}
