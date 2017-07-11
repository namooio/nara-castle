package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.mongo.document.CastleViewDoc;
import namoo.nara.castle.da.mongo.springdata.CastleViewMongoRepository;
import namoo.nara.castle.domain.view.CastleView;
import namoo.nara.castle.domain.view.store.CastleViewStore;
import namoo.nara.share.exception.store.NonExistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CastleViewMongoStore implements CastleViewStore {
    //
    @Autowired
    private CastleViewMongoRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void create(CastleView castleView) {
        //
        repository.insert(CastleViewDoc.toDocument(castleView));
    }

    @Override
    public CastleView retrieve(String id) {
        //
        CastleViewDoc castleViewDoc = repository.findOne(id);
        if (castleViewDoc == null) throw new NonExistenceException(String.format("No castleView document[%s] found.", id));
        return castleViewDoc.toDomain();
    }

    @Override
    public CastleView retrieveByEnrolledMetro(String metroId, String civilianId) {
        //
        CastleViewDoc castleViewDoc = repository.findByEnrollmentsMetroIdAndEnrollmentsCivilianId(metroId, civilianId);
        if (castleViewDoc == null) return null;
        return castleViewDoc.toDomain();
    }

    @Override
    public List<CastleView> retrieveAll() {
        //
        List<CastleViewDoc> castleViewDocs = repository.findAll();
        return CastleViewDoc.toDomains(castleViewDocs);
    }

    @Override
    public void update(CastleView castleView) {
        //
        String id = castleView.getId();
        if (!repository.exists(id)) throw new NonExistenceException(String.format("No castleView document[%s] found.", id));
        CastleViewDoc castleViewDoc = CastleViewDoc.toDocument(castleView);
        repository.save(castleViewDoc);
    }

    @Override
    public void delete(String id) {
        //
        repository.delete(id);
    }

    @Override
    public boolean existsByEnrolledMetro(String metroId, String civilianId) {
        //
        return retrieveByEnrolledMetro(metroId, civilianId) != null;
    }
}
