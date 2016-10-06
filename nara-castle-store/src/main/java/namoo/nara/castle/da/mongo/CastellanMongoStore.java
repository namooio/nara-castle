package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.mongo.document.AccountKeyDoc;
import namoo.nara.castle.da.mongo.document.CastellanDoc;
import namoo.nara.castle.da.mongo.springdata.CastellanMongoRepository;
import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.LoginIdType;
import namoo.nara.castle.domain.store.CastellanStore;
import namoo.nara.share.exception.store.AlreadyExistsException;
import namoo.nara.share.exception.store.NonExistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        CastellanDoc castellanDoc = CastellanDoc.toDocument(castellan);
        castellanMongoRepository.save(castellanDoc);
    }

    @Override
    public Castellan retrieve(String id) {
        //
        CastellanDoc castellanDoc = castellanMongoRepository.findOne(id);
        if (castellanDoc == null) throw new NonExistenceException(String.format("No castellan document[ID:%s] to retrieve.", id));
        return castellanDoc.toDomain();
    }

    @Override
    public Castellan retrieveByLoginIdAndLoginIdType(String loginId, LoginIdType loginIdType) {
        //
        CastellanDoc castellanDoc = castellanMongoRepository.findByAccountsKey(AccountKeyDoc.newInstance(loginId, loginIdType.name()));
        if (castellanDoc == null) throw new NonExistenceException(String.format("No castellan document[LoginID:%s (type:%s)] to retrieve.", loginId, loginIdType.name()));
        return castellanDoc.toDomain();
    }

    @Override
    public List<Castellan> retrieveAll() {
        //
        List<CastellanDoc> allDocs = castellanMongoRepository.findAll();
        return CastellanDoc.toDomains(allDocs);
    }

    @Override
    public void update(Castellan castellan) {
        //
        String id = castellan.getId();
        if (!castellanMongoRepository.exists(id)) throw new NonExistenceException(String.format("No castellan document[ID:%s] to update.", id));
        CastellanDoc castellanDoc = CastellanDoc.toDocument(castellan);
        castellanMongoRepository.save(castellanDoc);
    }

    @Override
    public void delete(String id) {
        //
        if (!castellanMongoRepository.exists(id)) throw new NonExistenceException(String.format("No castellan document[ID:%s] to delete.", id));
        castellanMongoRepository.delete(id);
    }
}
