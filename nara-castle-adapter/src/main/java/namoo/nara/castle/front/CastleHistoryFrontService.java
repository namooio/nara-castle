package namoo.nara.castle.front;

import namoo.nara.castle.front.dto.history.AccountBookDto;
import namoo.nara.castle.front.dto.history.CastleStateBookDto;
import namoo.nara.castle.front.dto.history.MetroBookDto;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public interface CastleHistoryFrontService {
    //
    void attachAccountBook(String castleId, AccountBookDto accountBookDto);
    void detachAccountBook(String castleId);
    AccountBookDto findAccountBook(String castleId);

    void attachCastleStateBook(String castleId, CastleStateBookDto castleStateBookDto);
    void detachCastleStateBook(String castleId);
    CastleStateBookDto findCastleStateBook(String castleId);

    void attachMetroBook(String castleId, MetroBookDto metroBookDto);
    void detatchMetroBook(String castleId);
    MetroBookDto findMetroBook(String castleId);
}
