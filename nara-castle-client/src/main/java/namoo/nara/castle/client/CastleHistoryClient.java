package namoo.nara.castle.client;

import namoo.nara.castle.adapter.dto.history.AccountBookDto;
import namoo.nara.castle.adapter.dto.history.CastleStateBookDto;
import namoo.nara.castle.adapter.dto.history.MetroBookDto;
import namoo.nara.castle.adapter.CastleHistoryAdapter;
import namoo.nara.share.restclient.NaraRestClient;
import namoo.nara.share.restclient.RequestBuilder;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastleHistoryClient implements CastleHistoryAdapter {
    //
    private NaraRestClient naraRestClient;

    public CastleHistoryClient(NaraRestClient naraRestClient) {
        this.naraRestClient = naraRestClient;
    }

    @Override
    public void attachAccountBook(String castleId, AccountBookDto accountBookDto) {
        //
        naraRestClient.sendAndRecieve(
            RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_ACCOUNTBOOK_ATTACH)
                .addPathParam("id", castleId)
                .setRequestDto(accountBookDto)
        );
    }

    @Override
    public void detachAccountBook(String castleId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_ACCOUNTBOOK_DETACH)
                        .addPathParam("id", castleId)
        );
    }

    @Override
    public AccountBookDto findAccountBook(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_ACCOUNTBOOK_FIND)
                        .addPathParam("id", castleId)
                        .setResponseType(AccountBookDto.class)
        );
    }

    @Override
    public void attachCastleStateBook(String castleId, CastleStateBookDto castleStateBookDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_STATEBOOK_ATTACH)
                        .addPathParam("id", castleId)
                        .setRequestDto(castleStateBookDto)
        );
    }

    @Override
    public void detachCastleStateBook(String castleId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_STATEBOOK_DETACH)
                        .addPathParam("id", castleId)
        );
    }

    @Override
    public CastleStateBookDto findCastleStateBook(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_STATEBOOK_FIND)
                        .addPathParam("id", castleId)
                        .setResponseType(CastleStateBookDto.class)
        );
    }

    @Override
    public void attachMetroBook(String castleId, MetroBookDto metroBookDto) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_METROBOOK_ATTACH)
                        .addPathParam("id", castleId)
                        .setRequestDto(metroBookDto)
        );
    }

    @Override
    public void detatchMetroBook(String castleId) {
        //
        naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_METROBOOK_DETACH)
                        .addPathParam("id", castleId)
        );
    }

    @Override
    public MetroBookDto findMetroBook(String castleId) {
        //
        return naraRestClient.sendAndRecieve(
                RequestBuilder.create(CastleServiceUrl.URL_CASTLE_HISTORY_METROBOOK_FIND)
                        .addPathParam("id", castleId)
                        .setResponseType(MetroBookDto.class)
        );
    }
}
