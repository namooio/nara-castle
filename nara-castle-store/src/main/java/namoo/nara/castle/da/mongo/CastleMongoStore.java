package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.mongo.mdo.CastleMdo;
import namoo.nara.castle.da.mongo.springdata.CastleMongoRepository;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.share.exception.store.AlreadyExistsException;
import namoo.nara.share.exception.store.NonExistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Repository
public class CastleMongoStore implements CastleStore {
    //
    @Autowired
    private CastleMongoRepository castleMongoRepository;

    @Override
    public void create(Castle castle) {
        //
        String id = castle.getId();
        if (castleMongoRepository.exists(id)) throw new AlreadyExistsException(String.format("Castle document[ID:%s] already exist.", id));
        CastleMdo castleMdo = CastleMdo.newInstance(castle);
        castleMongoRepository.save(castleMdo);
    }

    @Override
    public Castle retrieve(String id) {
        //
        CastleMdo castleMdo = castleMongoRepository.findOne(id);
        if (castleMdo == null) throw new NonExistenceException(String.format("No castle document[ID:%s] to retrieve.", id));
        return castleMdo.toDomain();
    }

    @Override
    public void update(Castle castle) {
        //
        String id = castle.getId();
        if (!castleMongoRepository.exists(id)) throw new NonExistenceException(String.format("No castle document[ID:%s] to update.", id));
        CastleMdo castleMdo = CastleMdo.newInstance(castle);
        castleMongoRepository.save(castleMdo);
    }

    @Override
    public void delete(String id) {
        //
        if (!castleMongoRepository.exists(id)) throw new NonExistenceException(String.format("No castle document[ID:%s] to delete.", id));
        castleMongoRepository.delete(id);
    }
}
