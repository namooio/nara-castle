package namoo.nara.castle.domain.store;

import namoo.nara.castle.domain.entity.history.HistoryBundle;

public interface HistoryBundleStore {
    //
    String create(HistoryBundle history);
    HistoryBundle retrieve(String id);
    void update(HistoryBundle history);
    void delete(String id);
}