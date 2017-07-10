package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.mongo.document.CastleDoc;
import namoo.nara.castle.da.mongo.springdata.CastleMongoRepository;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.share.exception.store.NonExistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CastleMongoStore implements CastleStore {

    @Autowired
    private CastleMongoRepository castleMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void create(Castle castle) {
        //
        castleMongoRepository.insert(CastleDoc.toDocument(castle));
    }

    @Override
    public Castle retrieve(String id) {
        //
        CastleDoc castleDoc = castleMongoRepository.findOne(id);
        if (castleDoc == null) throw new NonExistenceException(String.format("No castle document[%s] found.", id));
        return castleDoc.toDomain();
    }

    @Override
    public Castle retrieveByEnrolledMetro(String metroId, String civilianId) {
        //
        CastleDoc castleDoc = castleMongoRepository.findByEnrollmentsMetroIdAndEnrollmentsCivilianId(metroId, civilianId);
        if (castleDoc == null) return null;
        return castleDoc.toDomain();
    }

    @Override
    public List<Castle> retrieveAll() {
        //
        List<CastleDoc> castles = castleMongoRepository.findAll();
        return CastleDoc.toDomains(castles);
    }

    @Override
    public void update(Castle castle) {
        //
        String id = castle.getId();
        if (!castleMongoRepository.exists(id)) throw new NonExistenceException(String.format("No castle document[%s] found.", id));
        CastleDoc castleDoc = CastleDoc.toDocument(castle);
        castleMongoRepository.save(castleDoc);
    }

    @Override
    public void delete(Castle castle) {
        //
        castleMongoRepository.delete(castle.getId());
    }
}
