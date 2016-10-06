package namoo.nara.castle.client;

import namoo.nara.castle.adapter.CastleAdapter;
import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CastleClient implements CastleAdapter {
    //
    private NaraRestClient naraRestClient;

    public CastleClient(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public String buildCastle(CastleBuildDto castleBuildDto) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_BUILD)
                .setRequestDto(castleBuildDto)
                .setResponseType(String.class)
        );
    }

    @Override
    public void modifyLocale(String castleId, Locale locale) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_LOCALE_MODIFY)
                .addPathParam("id", castleId)
                .setRequestDto(locale)
        );
    }

    @Override
    public CastleFindDto findCastle(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_FIND)
                .addPathParam("id", castleId)
                .setResponseType(CastleFindDto.class)
        );
    }

    @Override
    public List<CastleFindDto> findCastles() {
        //
        CastleFindDto[] castleFindDtos = naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_FIND_ALL)
                .setResponseType(CastleFindDto[].class)
        );
        if (castleFindDtos == null) return null;
        return Arrays.asList(castleFindDtos);
    }
}
