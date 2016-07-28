package namoo.nara.castle.client;

import namoo.nara.castle.front.dto.history.AccountBookDto;
import namoo.nara.castle.front.dto.history.CastleStateBookDto;
import namoo.nara.castle.front.dto.history.MetroBookDto;
import namoo.nara.castle.front.CastleHistoryFrontService;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastleHistoryFrontClient implements CastleHistoryFrontService {
    //
    private NaraRestClient naraRestClient;

    public CastleHistoryFrontClient(NaraRestClient naraRestClient) {
        this.naraRestClient = naraRestClient;
    }

    @Override
    public void attachAccountBook(String castleId, AccountBookDto accountBookDto) {
        //
        naraRestClient.sendAndRecieve(
            RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_ATTACH_ACCOUNT_BOOK)
                .addPathParam("id", castleId)
                .setRequestDto(accountBookDto)
        );
    }

    @Override
    public void detachAccountBook(String castleId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_DETACH_ACCOUNT_BOOK)
                        .addPathParam("id", castleId)
        );
    }

    @Override
    public AccountBookDto findAccountBook(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_FIND_ACCOUNT_BOOK)
                        .addPathParam("id", castleId)
                        .setResponseType(AccountBookDto.class)
        );
    }

    @Override
    public void attachCastleStateBook(String castleId, CastleStateBookDto castleStateBookDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_ATTACH_STATE_BOOK)
                        .addPathParam("id", castleId)
                        .setRequestDto(castleStateBookDto)
        );
    }

    @Override
    public void detachCastleStateBook(String castleId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_DETACH_STATE_BOOK)
                        .addPathParam("id", castleId)
        );
    }

    @Override
    public CastleStateBookDto findCastleStateBook(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_FIND_STATE_BOOK)
                        .addPathParam("id", castleId)
                        .setResponseType(CastleStateBookDto.class)
        );
    }

    @Override
    public void attachMetroBook(String castleId, MetroBookDto metroBookDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_ATTACH_METRO_BOOK)
                        .addPathParam("id", castleId)
                        .setRequestDto(metroBookDto)
        );
    }

    @Override
    public void detatchMetroBook(String castleId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_DETACH_METRO_BOOKH)
                        .addPathParam("id", castleId)
        );
    }

    @Override
    public MetroBookDto findMetroBook(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_FIND_METRO_BOOK)
                        .addPathParam("id", castleId)
                        .setResponseType(MetroBookDto.class)
        );
    }
}
