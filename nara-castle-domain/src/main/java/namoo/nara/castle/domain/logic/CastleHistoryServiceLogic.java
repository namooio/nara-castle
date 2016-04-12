package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.OpenState;
import namoo.nara.castle.domain.entity.history.*;
import namoo.nara.castle.domain.service.CastleHistoryService;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.domain.store.HistoryBundleStore;

import java.util.Locale;

public class CastleHistoryServiceLogic implements CastleHistoryService {
    //
    private HistoryBundleStore historyStore;

    public CastleHistoryServiceLogic(CastleStoreLycler storeLycler) {
        //
        this.historyStore = storeLycler.requestHistoryBundleStore();
    }

    @Override
    public void attachAccountBook(String castleId, AccountBook accountBook) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        history.attachAccountBook(accountBook);

        historyStore.update(history);
    }

    @Override
    public void detachAccountBook(String castleId) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        history.detatchAccountBook();

        historyStore.update(history);
    }

    @Override
    public AccountBook findAccountBook(String castleId) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        return history.getAccountBook();
    }

    @Override
    public void attachCastleStateBook(String castleId, CastleStateBook castleStateBook) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        history.attachCastleStateBook(castleStateBook);

        historyStore.update(history);
    }

    @Override
    public void detachCastleStateBook(String castleId) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        history.detatchCastleStateBook();

        historyStore.update(history);
    }

    @Override
    public CastleStateBook findCastleStateBook(String castleId) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        return history.getCastleStateBook();
    }

    @Override
    public void attachMetroBook(String castleId, MetroBook metroBook) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        history.attachMetroBook(metroBook);

        historyStore.update(history);
    }

    @Override
    public void detatchMetroBook(String castleId) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        history.detatchMetroBook();

        historyStore.update(history);
    }

    @Override
    public MetroBook findMetroBook(String castleId) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        return history.getMetroBook();
    }
}