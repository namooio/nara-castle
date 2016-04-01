package namoo.nara.castle.remote.client;

import namoo.nara.castle.remote.CastellanRemote;
import namoo.nara.castle.remote.dto.CastellanCreateDto;
import namoo.nara.castle.remote.dto.CastellanReadDto;
import namoo.nara.share.restclient.AbstractClient;
import namoo.nara.share.restclient.NaraConnector;
import namoo.nara.share.restclient.RequestBuilder;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public class CastellanClient extends AbstractClient implements CastellanRemote {

    public CastellanClient(NaraConnector naraConnector) {
        super(naraConnector);
    }

    @Override
    public void create(CastellanCreateDto castellanCreateDto) {
        sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_CREATE)
                        .setRequestDto(castellanCreateDto)
        );
    }

    @Override
    public CastellanReadDto findCastellan(String castellanId) {
        return sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_GET)
                        .setResponseType(CastellanReadDto.class)
                        .addPathParam("castellanId", castellanId)
        );
    }

    @Override
    public void remove(String castellanOid) {
        sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_REMOVE)
                        .addPathParam("castellanId", castellanOid)
        );
    }

    @Override
    public CastellanReadDto findByVerifiedEmail(String email) {
        return sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_GET_BY_EMAIL)
                        .setResponseType(CastellanReadDto.class)
                        .addPathParam("email", email)
        );
    }

    @Override
    public void verifyEmail(String email, String castellanId) {
        sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_EMAIL_VERIFY)
                        .addPathParam("castellanId", castellanId)
                        .addPathParam("email", email)
        );
    }

    @Override
    public void setEmailAsPrimary(String email, String castellanId) {
        sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_EMAIL_PRIMARY)
                        .addPathParam("castellanId", castellanId)
                        .addPathParam("email", email)
        );
    }

    @Override
    public void removeEmail(String email, String castellanId) {
        sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_EMAIL_REMOVE)
                        .addPathParam("castellanId", castellanId)
                        .addPathParam("email", email)
        );
    }

    @Override
    public void addEmail(String email, String castellanId) {
        sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_EMAIL_ADD)
                        .addPathParam("castellanId", castellanId)
                        .addPathParam("email", email)
        );
    }

    @Override
    public String findCastellanDisplayName(String castellanId) {
        return  sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_DISPLAYNAME_GET)
                        .addPathParam("castellanId", castellanId)
                        .setResponseType(String.class)
        );
    }
}
