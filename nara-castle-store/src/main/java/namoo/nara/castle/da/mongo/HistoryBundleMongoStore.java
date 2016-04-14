package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.mongo.mdo.HistoryBundleMdo;
import namoo.nara.castle.da.mongo.springdata.HistoryBundleMongoRepository;
import namoo.nara.castle.domain.entity.history.HistoryBundle;
import namoo.nara.castle.domain.store.HistoryBundleStore;
import namoo.nara.share.exception.store.AlreadyExistsException;
import namoo.nara.share.exception.store.NonExistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Repository
public class HistoryBundleMongoStore implements HistoryBundleStore {
    //
    @Autowired
    private HistoryBundleMongoRepository historyBundleMongoRepository;

    @Override
    public String create(HistoryBundle history) {
        //
        String id = history.getId();
        if (historyBundleMongoRepository.exists(id)) throw new AlreadyExistsException(String.format("History bundle document[ID:%s] already exist.", id));
        HistoryBundleMdo historyBundleMdo = HistoryBundleMdo.newInstance(history);
        historyBundleMongoRepository.save(historyBundleMdo);
        return id;
    }

    @Override
    public HistoryBundle retrieve(String id) {
        //
        HistoryBundleMdo historyBundleMdo = historyBundleMongoRepository.findOne(id);
        if (historyBundleMdo == null) throw new NonExistenceException(String.format("No history bundle document[ID:%s] to retrieve.", id));
        return historyBundleMdo.toDomain();
    }

    private void update(HistoryBundle history) {
        //
        String id = history.getId();
        if (!historyBundleMongoRepository.exists(id)) throw new NonExistenceException(String.format("No history bundle document[ID:%s] to update.", id));
        HistoryBundleMdo historyBundleMdo = HistoryBundleMdo.newInstance(history);
        historyBundleMongoRepository.save(historyBundleMdo);
    }

    @Override
    public void updateAccountBook(HistoryBundle history) {
        //
        update(history);
    }

    @Override
    public void updateMetroBook(HistoryBundle history) {
        //
        update(history);
    }

    @Override
    public void updateCastleStateBook(HistoryBundle history) {
        //
        update(history);
    }

    @Override
    public void delete(String id) {
        //
        if (!historyBundleMongoRepository.exists(id)) throw new NonExistenceException(String.format("No history bundle document[ID:%s] to delete.", id));
        historyBundleMongoRepository.delete(id);
    }
}
