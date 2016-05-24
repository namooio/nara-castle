package namoo.nara.castle.client;

import namoo.nara.castle.rep.CastleRepService;
import namoo.nara.castle.rep.dto.CastleBuildDto;
import namoo.nara.castle.rep.dto.CastleFindDto;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 9..
 */
public class CastleRepClient implements CastleRepService {
    //
    private NaraRestClient naraRestClient;

    public CastleRepClient(NaraRestClient naraRestClient) {
        //
        this.naraRestClient = naraRestClient;
    }

    @Override
    public void buildCastle(String castleId, CastleBuildDto castleBuildDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_REP_CASTLE_BUILD)
                        .addPathParam("id", castleId)
                        .setRequestDto(castleBuildDto)
        );
    }

    @Override
    public CastleFindDto findCastle(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_REP_CASTLE_FIND)
                        .addPathParam("id", castleId)
        );
    }

    @Override
    public void addMetro(String castleId, String metroId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_REP_CASTLE_METRO_ADD)
                        .addPathParam("id", castleId)
                        .addPathParam("metroId", metroId)
        );
    }
}
