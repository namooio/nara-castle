package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.mongo.springdata.HistoryBundleMdoRepository;
import namoo.nara.castle.domain.entity.history.HistoryBundle;
import namoo.nara.castle.domain.store.HistoryBundleStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Repository
public class HistoryBundleMongoStore implements HistoryBundleStore {

    @Autowired
    private HistoryBundleMdoRepository historyBundleMdoRepository;

    @Override
    public String create(HistoryBundle history) {
        return null;
    }

    @Override
    public HistoryBundle retrieve(String id) {
        return null;
    }

    @Override
    public void update(HistoryBundle history) {

    }

    @Override
    public void delete(String id) {

    }
}
