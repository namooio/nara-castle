package namoo.nara.castle.client;

import namoo.nara.castle.front.dto.CastellanFindDto;
import namoo.nara.castle.front.CastellanFrontService;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastellanFrontClient implements CastellanFrontService {
    //
    private NaraRestClient naraRestClient;

    public CastellanFrontClient(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public CastellanFindDto findCastellan(String id) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_FIND)
                .addPathParam("id", id)
                .setResponseType(CastellanFindDto.class)
        );
    }

    @Override
    public void modifyDisplayName(String id, String displayName) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_DISPLAYNAME_MODIFY)
                .addPathParam("id", id)
                .setRequestDto(displayName)
        );
    }

    @Override
    public void modifyPhoto(String id, String photoId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_PHOTO_MODIFY)
                        .addPathParam("id", id)
                        .setRequestDto(photoId)
        );
    }

    @Override
    public void modifyPrimaryEmail(String id, String email) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_PRIMARYEMAIL_MODIFY)
                        .addPathParam("id", id)
                        .setRequestDto(email)
        );
    }

    @Override
    public void modifyPrimaryPhone(String id, String phoneNumber) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTELLAN_PRIMARYPHONE_MODIFY)
                        .addPathParam("id", id)
                        .setRequestDto(phoneNumber)
        );
    }
}
