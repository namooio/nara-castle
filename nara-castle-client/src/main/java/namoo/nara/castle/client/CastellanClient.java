package namoo.nara.castle.client;

import namoo.nara.castle.adapter.CastellanAdapter;
import namoo.nara.castle.adapter.dto.*;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

public class CastellanClient implements CastellanAdapter {
    //
    private NaraRestClient naraRestClient;

    public CastellanClient(NaraRestClient naraRestClient) {
        this.naraRestClient = naraRestClient;
    }

    @Override
    public void createCastellan(String castleId, CastellanCreationDto castellanCreationDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CREATE)
                .addPathParam("id", castleId)
                .setRequestDto(castellanCreationDto)
        );
    }

    @Override
    public CastellanFindDto findCastellan(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_FIND)
                .addPathParam("id", castleId)
                .setResponseType(CastellanFindDto.class)
        );
    }

    @Override
    public CastellanFindDto findCastellan(String loginId, String loginIdType) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_FIND_BY_ACCOUNT)
                .addQueryParam("loginId", loginId)
                .addQueryParam("loginIdType", loginIdType)
                .setResponseType(CastellanFindDto.class)
        );
    }

    @Override
    public void modifyCastellan(String castleId, CastellanModificationDto castellanModificationDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_MODIFY)
                .addPathParam("id", castleId)
                .setRequestDto(castellanModificationDto)
        );
    }

    @Override
    public void modifyCastellanPhoto(String castleId, String photoId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_PHOTO_MODIFY)
                .addPathParam("id", castleId)
                .setRequestDto(photoId)
        );
    }

    @Override
    public void removeCastellan(String castleId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_REMOVE)
                .addPathParam("id", castleId)
        );
    }

    @Override
    public void addAccount(String castleId, LoginAccountDto accountDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_ACCOUNT_ADD)
                .addPathParam("id", castleId)
                .setRequestDto(accountDto)
        );
    }

    @Override
    public void removeAccount(String castleId, LoginAccountDto accountDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_ACCOUNT_REMOVE)
                .addPathParam("id", castleId)
                .setRequestDto(accountDto)
        );
    }

    @Override
    public void modifyPasswordCredential(String castleId, String password) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_PASSWORD_CREDENTIAL_MODIFY)
                .addPathParam("id", castleId)
                .setRequestDto(password)
        );
    }

    @Override
    public void addEmail(String castleId, String email) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_EMAIL_ADD)
                .addPathParam("id", castleId)
                .setRequestDto(email)
        );
    }

    @Override
    public void verifyEmail(String castleId, String email) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_EMAIL_VERIFY)
                .addPathParam("id", castleId)
                .setRequestDto(email)
        );
    }

    @Override
    public void setPrimaryEmail(String castleId, String email) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_PRIMARY_EMAIL_SET)
                .addPathParam("id", castleId)
                .setRequestDto(email)
        );
    }

    @Override
    public void removeEmail(String castleId, String email) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_EMAIL_REMOVE)
                .addPathParam("id", castleId)
                .setRequestDto(email)
        );
    }

    @Override
    public void addJoinedMetro(String castleId, JoinedMetroDto joinedMetroDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_JOINED_METRO_ADD)
                .addPathParam("id", castleId)
                .setRequestDto(joinedMetroDto)
        );
    }

    @Override
    public void removeJoinedMetro(String castleId, JoinedMetroDto joinedMetroDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_JOINED_METRO_REMOVE)
                .addPathParam("id", castleId)
                .setRequestDto(joinedMetroDto)
        );
    }
}
