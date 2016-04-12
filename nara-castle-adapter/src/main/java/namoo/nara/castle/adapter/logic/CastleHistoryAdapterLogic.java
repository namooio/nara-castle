package namoo.nara.castle.adapter.logic;

import namoo.nara.castle.adapter.dto.history.AccountBookDto;
import namoo.nara.castle.adapter.dto.history.CastleStateBookDto;
import namoo.nara.castle.adapter.dto.history.MetroBookDto;
import namoo.nara.castle.adapter.service.CastleHistoryAdapter;
import namoo.nara.castle.domain.entity.history.AccountBook;
import namoo.nara.castle.domain.entity.history.CastleStateBook;
import namoo.nara.castle.domain.entity.history.MetroBook;
import namoo.nara.castle.domain.service.CastleHistoryService;
import namoo.nara.castle.domain.service.CastleServiceLycler;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class CastleHistoryAdapterLogic implements CastleHistoryAdapter {
    //
    private CastleHistoryService castleHistoryService;

    public CastleHistoryAdapterLogic(CastleServiceLycler serviceLycler) {
        //
        castleHistoryService = serviceLycler.requestCastleHisotryService();
    }

    @Override
    public void attachAccountBook(String castleId, AccountBookDto accountBookDto) {
        //
        AccountBook accountBook = accountBookDto.toDomain();
        castleHistoryService.attachAccountBook(castleId, accountBook);
    }

    @Override
    public void detachAccountBook(String castleId) {
        //
        castleHistoryService.detachAccountBook(castleId);
    }

    @Override
    public AccountBookDto findAccountBook(String castleId) {
        //
        AccountBook accountBook = castleHistoryService.findAccountBook(castleId);
        if (accountBook == null) return null;
        return AccountBookDto.newInstance(accountBook);
    }

    @Override
    public void attachCastleStateBook(String castleId, CastleStateBookDto castleStateBookDto) {
        //
        CastleStateBook castleStateBook = castleStateBookDto.toDomain();
        castleHistoryService.attachCastleStateBook(castleId, castleStateBook);
    }

    @Override
    public void detachCastleStateBook(String castleId) {
        //
        castleHistoryService.detachCastleStateBook(castleId);
    }

    @Override
    public CastleStateBookDto findCastleStateBook(String castleId) {
        //
        CastleStateBook castleStateBook = castleHistoryService.findCastleStateBook(castleId);
        if (castleStateBook == null) return null;
        return CastleStateBookDto.newInstance(castleStateBook);
    }

    @Override
    public void attachMetroBook(String castleId, MetroBookDto metroBookDto) {
        //
        MetroBook metroBook = metroBookDto.toDomain();
        castleHistoryService.attachMetroBook(castleId, metroBook);
    }

    @Override
    public void detatchMetroBook(String castleId) {
        //
        castleHistoryService.detatchMetroBook(castleId);
    }

    @Override
    public MetroBookDto findMetroBook(String castleId) {
        //
        MetroBook metroBook = castleHistoryService.findMetroBook(castleId);
        if (metroBook == null) return null;
        return MetroBookDto.newInstance(metroBook);
    }
}
