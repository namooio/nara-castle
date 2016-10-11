package namoo.nara.castle.adapter.rest;

import namoo.nara.castle.protocol.CastellanProtocol;
import namoo.nara.castle.protocol.sdo.CastellanCreationSdo;
import namoo.nara.castle.protocol.sdo.CastellanFindSdo;
import namoo.nara.castle.protocol.sdo.JoinedMetroSdo;
import namoo.nara.castle.protocol.sdo.LoginAccountSdo;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

import java.util.Arrays;
import java.util.List;

public class CastellanRestAdapter implements CastellanProtocol {
    //
    private NaraRestClient naraRestClient;

    public CastellanRestAdapter(NaraRestClient naraRestClient) {
        this.naraRestClient = naraRestClient;
    }

    @Override
    public void createCastellan(String castleId, CastellanCreationSdo castellanCreationSdo) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_CREATE)
                .addPathParam("id", castleId)
                .setRequestBody(castellanCreationSdo)
        );
    }

    @Override
    public CastellanFindSdo findCastellan(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_FIND)
                .addPathParam("id", castleId)
                .setResponseType(CastellanFindSdo.class)
        );
    }

    @Override
    public CastellanFindSdo findCastellan(String loginId, String loginIdType) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_FIND_BY_ACCOUNT)
                .addQueryParam("loginId", loginId)
                .addQueryParam("loginIdType", loginIdType)
                .setResponseType(CastellanFindSdo.class)
        );
    }

    @Override
    public void removeCastellan(String castleId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_REMOVE)
                .addPathParam("id", castleId)
        );
    }

    @Override
    public void addAccount(String castleId, LoginAccountSdo accountSdo) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_ACCOUNT_ADD)
                .addPathParam("id", castleId)
                .setRequestBody(accountSdo)
        );
    }

    @Override
    public List<LoginAccountSdo> findAccounts(String castleId) {
        //
        LoginAccountSdo[] accounts = naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_ACCOUNTS_FIND)
                .addPathParam("id", castleId)
                .setResponseType(LoginAccountSdo[].class)
        );
        if (accounts == null) return null;
        return Arrays.asList(accounts);
    }

    @Override
    public void removeAccount(String castleId, LoginAccountSdo accountDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_ACCOUNT_REMOVE)
                .addPathParam("id", castleId)
                .setRequestBody(accountDto)
        );
    }

    @Override
    public String findPassword(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_PASSWORD_FIND)
                .addPathParam("id", castleId)
                .setResponseType(String.class)
        );
    }

    @Override
    public void modifyPassword(String castleId, String password) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_PASSWORD_MODIFY)
                .addPathParam("id", castleId)
                .setRequestBody(password)
        );
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
    public void verifyEmail(String castleId, String email) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_EMAIL_VERIFY)
                .addPathParam("id", castleId)
                .setRequestBody(email)
        );
    }

    @Override
    public void setPrimaryEmail(String castleId, String email) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_PRIMARY_EMAIL_SET)
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
    public void addJoinedMetro(String castleId, JoinedMetroSdo joinedMetroSdo) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_JOINED_METRO_ADD)
                .addPathParam("id", castleId)
                .setRequestBody(joinedMetroSdo)
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
    public void removeJoinedMetro(String castleId, JoinedMetroSdo joinedMetroSdo) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleRestUrl.URL_CASTELLAN_JOINED_METRO_REMOVE)
                .addPathParam("id", castleId)
                .setRequestBody(joinedMetroSdo)
        );
    }
}
