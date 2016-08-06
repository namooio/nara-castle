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
                RequestBuilder.create(CastleServiceUrl.URL_REP_BUILD_CASTLE)
                        .addPathParam("id", castleId)
                        .setRequestDto(castleBuildDto)
        );
    }

    @Override
    public CastleFindDto findCastle(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_REP_FIND_CASTLE)
                        .addPathParam("id", castleId)
        );
    }

    @Override
    public void addMetro(String castleId, String metroId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_REP_ADD_METRO)
                        .addPathParam("id", castleId)
                        .addPathParam("metroId", metroId)
        );
    }

    @Override
    public void addEmail(String castleId, String email) {

    }

    @Override
    public void removeEmail(String castleId, String email) {

    }

    @Override
    public void verifyEmail(String castleId, String email) {

    }
}
