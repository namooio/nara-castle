package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.mongo.document.CastellanViewDoc;
import namoo.nara.castle.da.mongo.springdata.CastellanViewMongoRepository;
import namoo.nara.castle.domain.view.CastellanView;
import namoo.nara.castle.domain.view.store.CastellanViewStore;
import namoo.nara.share.exception.store.NonExistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CastellanViewMongoStore implements CastellanViewStore {
    //
    @Autowired
    private CastellanViewMongoRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void create(CastellanView castellanView) {
        //
        repository.insert(CastellanViewDoc.toDocument(castellanView));
    }

    @Override
    public CastellanView retrieve(String id) {
        //
        CastellanViewDoc castellanViewDoc = repository.findOne(id);
        if (castellanViewDoc == null) throw new NonExistenceException(String.format("No castellanView document[%s] found.", id));
        return castellanViewDoc.toDomain();
    }

    @Override
    public List<CastellanView> retrieveAll() {
        //
        List<CastellanViewDoc> castellanViewDocs = repository.findAll();
        return CastellanViewDoc.toDomains(castellanViewDocs);
    }

    @Override
    public void update(CastellanView castellanView) {
        //
        String id = castellanView.getId();
        if (!repository.exists(id)) throw new NonExistenceException(String.format("No castellanView document[%s] found.", id));
        CastellanViewDoc castellanViewDoc = CastellanViewDoc.toDocument(castellanView);
        repository.save(castellanViewDoc);
    }

    @Override
    public void delete(String id) {
        //
        repository.delete(id);
    }

    @Override
    public boolean exists(String id) {
        //
        return repository.exists(id);
    }
}
