package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.history.AccountBook;
import namoo.nara.castle.domain.entity.history.CastleStateBook;

public interface CastleHistoryService {
    //
    void attachAccountBook(String castellanId, AccountBook accountBook);
    void detachAccountBook(String castellanId);
    AccountBook findAccountBook(String castellanId);


    void attachCastleStateBook(String castellanId, CastleStateBook castleStateBook);
    void detachCastleStateBook(String castellanId);
    CastleStateBook findCastleStateBook(String castellanId);
}