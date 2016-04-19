package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.mongo.mdo.CastellanMdo;
import namoo.nara.castle.da.mongo.springdata.CastellanMongoRepository;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.share.exception.store.AlreadyExistsException;
import namoo.nara.share.exception.store.NonExistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
@Repository
public class CastellanMongoStore implements CastellanStore {
    //
    @Autowired
    private CastellanMongoRepository castellanMongoRepository;

    @Override
    public void create(Castellan castellan) {
        //
        String id = castellan.getId();
        if (castellanMongoRepository.exists(id)) throw new AlreadyExistsException(String.format("Castellan document[ID:%s] already exist.", id));
        CastellanMdo castellanMdo = CastellanMdo.newInstance(castellan);
        castellanMongoRepository.save(castellanMdo);
    }

    @Override
    public Castellan retrieve(String id) {
        //
        CastellanMdo castellanMdo = castellanMongoRepository.findOne(id);
        if (castellanMdo == null) throw new NonExistenceException(String.format("No castellan document[ID:%s] to retrieve.", id));
        return castellanMdo.toDomain();
    }

    @Override
    public void update(Castellan castellan) {
        //
        String id = castellan.getId();
        if (!castellanMongoRepository.exists(id)) throw new NonExistenceException(String.format("No castellan document[ID:%s] to update.", id));
        CastellanMdo castellanMdo = CastellanMdo.newInstance(castellan);
        castellanMongoRepository.save(castellanMdo);
    }

    @Override
    public void delete(String id) {
        //
        if (!castellanMongoRepository.exists(id)) throw new NonExistenceException(String.format("No castellan document[ID:%s] to delete.", id));
        castellanMongoRepository.delete(id);
    }
}
