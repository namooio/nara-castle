package namoo.nara.castle.client;

import namoo.nara.castle.front.dto.CastleFindDto;
import namoo.nara.castle.front.CastleFrontService;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

import java.util.List;
import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 19..
 */
public class CastleFrontClient implements CastleFrontService {
    //
    private NaraRestClient naraRestClient;

    public CastleFrontClient(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public void suspendCastle(String id, String remarks) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_SUSPEND)
                .addPathParam("id", id)
                .setRequestDto(remarks)
        );
    }

    @Override
    public void reopenCastle(String id, String remarks) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_REOPEN)
                        .addPathParam("id", id)
                        .setRequestDto(remarks)
        );
    }

    @Override
    public void modifyName(String id, String name) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_MODIFY_NAME)
                        .addPathParam("id", id)
                        .setRequestDto(name)
        );
    }

    @Override
    public void modifyLocale(String id, Locale locale) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_MODIFY_LOCALE)
                        .addPathParam("id", id)
                        .setRequestDto(locale)
        );
    }

    @Override
    public CastleFindDto findCastle(String id) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_FIND)
                        .addPathParam("id", id)
                        .setResponseType(CastleFindDto.class)
        );
    }

    @Override
    public List<CastleFindDto> findAllCastles() {
        //
         return naraRestClient.sendAndRecieve(
                 RequestBuilder.create(CastleServiceUrl.URL_CASTLE_FIND_ALL)
                         .setResponseType(List.class)
         );
    }
}
