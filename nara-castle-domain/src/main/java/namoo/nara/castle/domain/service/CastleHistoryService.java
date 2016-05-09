package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.history.AccountBook;
import namoo.nara.castle.domain.entity.history.CastleStateBook;
import namoo.nara.castle.domain.entity.history.MetroBook;

public interface CastleHistoryService {
    //
    void attachAccountBook(String castleId, AccountBook accountBook);
    void detachAccountBook(String castleId);
    AccountBook findAccountBook(String castleId);

    void attachCastleStateBook(String castleId, CastleStateBook castleStateBook);
    void detachCastleStateBook(String castleId);
    CastleStateBook findCastleStateBook(String castleId);

    void attachMetroBook(String castleId, MetroBook metroBook);
    void detatchMetroBook(String castleId);
    MetroBook findMetroBook(String castleId);
    void addMetro(String castleId, String metroId);
}