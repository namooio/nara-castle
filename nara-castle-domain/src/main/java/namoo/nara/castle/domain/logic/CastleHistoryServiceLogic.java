package namoo.nara.castle.domain.logic;

import namoo.nara.castle.domain.entity.history.*;
import namoo.nara.castle.domain.service.CastleHistoryService;
import namoo.nara.castle.domain.store.CastleStoreLycler;
import namoo.nara.castle.domain.store.HistoryBundleStore;

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

        historyStore.updateAccountBook(history);
    }

    @Override
    public void detachAccountBook(String castleId) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        history.detatchAccountBook();

        historyStore.updateAccountBook(history);
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

        historyStore.updateCastleStateBook(history);
    }

    @Override
    public void detachCastleStateBook(String castleId) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        history.detatchCastleStateBook();

        historyStore.updateCastleStateBook(history);
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

        historyStore.updateMetroBook(history);
    }

    @Override
    public void detatchMetroBook(String castleId) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        history.detatchMetroBook();

        historyStore.updateMetroBook(history);
    }

    @Override
    public MetroBook findMetroBook(String castleId) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        return history.getMetroBook();
    }

    @Override
    public void addMetro(String castleId, String metroId) {
        //
        HistoryBundle history = historyStore.retrieve(castleId);
        ParticipantMetro participantMetro = new ParticipantMetro(metroId, System.currentTimeMillis());
        history.getMetroBook().addMetro(participantMetro);
        historyStore.updateMetroBook(history);
    }
}