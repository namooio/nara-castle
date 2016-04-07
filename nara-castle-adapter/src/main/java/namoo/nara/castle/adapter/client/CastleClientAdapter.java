package namoo.nara.castle.adapter.client;

import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import namoo.nara.castle.adapter.service.CastleAdapter;
import namoo.nara.share.restclient.AbstractClient;
import namoo.nara.share.restclient.NaraConnector;
import namoo.nara.share.restclient.RequestBuilder;

import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 19..
 */
public class CastleClientAdapter extends AbstractClient implements CastleAdapter {

    public CastleClientAdapter(NaraConnector naraConnector) {
        super(naraConnector);
    }

    @Override
    public void buildCastle(String id, CastleBuildDto castleBuildDto) {
        //
        sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_BUILD)
                        .addPathParam("id", id)
                        .setRequestDto(castleBuildDto)
        );
    }

    @Override
    public void suspendCastle(String id, String remarks) {
        //
        sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_SUSPEND)
                .addPathParam("id", id)
                .setRequestDto(remarks)
        );
    }

    @Override
    public void reopenCastle(String id, String remarks) {
        //
        sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_REOPEN)
                        .addPathParam("id", id)
                        .setRequestDto(remarks)
        );
    }

    @Override
    public void modifyName(String id, String name) {
        //
        sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_NAME_MODIFY)
                        .addPathParam("id", id)
                        .setRequestDto(name)
        );
    }

    @Override
    public void modifyLocale(String id, Locale locale) {
        //
        sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_LOCALE_MODIFY)
                        .addPathParam("id", id)
                        .setRequestDto(locale)
        );
    }

    @Override
    public CastleFindDto findCastle(String id) {
        //
        return sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_FIND)
                        .addPathParam("id", id)
                        .setResponseType(CastleFindDto.class)
        );
    }
}
