package namoo.nara.castle.client;

import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import namoo.nara.castle.adapter.service.CastleAdapter;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.NaraRestClientLycler;
import namoo.nara.share.restclient.RequestBuilder;

import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 19..
 */
public class CastleClient implements CastleAdapter {
    //
    private NaraRestClient naraRestClient;

    public CastleClient(NaraRestClientLycler naraRestClientLycler) {
        //
        naraRestClient = naraRestClientLycler.requestNaraRestClient();
    }

    @Override
    public void buildCastle(String id, CastleBuildDto castleBuildDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_BUILD)
                        .addPathParam("id", id)
                        .setRequestDto(castleBuildDto)
        );
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
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_NAME_MODIFY)
                        .addPathParam("id", id)
                        .setRequestDto(name)
        );
    }

    @Override
    public void modifyLocale(String id, Locale locale) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_LOCALE_MODIFY)
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
}
