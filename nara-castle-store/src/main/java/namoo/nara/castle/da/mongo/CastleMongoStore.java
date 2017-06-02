package namoo.nara.castle.da.mongo;

import namoo.nara.castle.da.mongo.document.CastleDoc;
import namoo.nara.castle.da.mongo.document.CastleSequenceDoc;
import namoo.nara.castle.da.mongo.springdata.CastleMongoRepository;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.store.CastleStore;
import namoo.nara.share.exception.store.AlreadyExistsException;
import namoo.nara.share.exception.store.NonExistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

        String id = castle.getId();
        if (castleMongoRepository.exists(id)) throw new AlreadyExistsException(String.format("Castle document[ID:%s] already exist.", id));
        CastleDoc castleDoc = CastleDoc.toDocument(castle);
        castleMongoRepository.save(castleDoc);
    }

    @Override
    public Castle retrieve(String id) {

        CastleDoc castleDoc = castleMongoRepository.findOne(id);
        if (castleDoc == null) throw new NonExistenceException(String.format("No castle document[ID:%s] to retrieve.", id));
        return castleDoc.toDomain();
    }

    @Override
    public Castle retrieveByEmail(String nationId, String email) {

        CastleDoc castleDoc = castleMongoRepository.findByNationIdAndCastellanEmailsEmailsEmail(nationId, email);
        if (castleDoc == null) return null;
        return castleDoc.toDomain();
    }

    @Override
    public List<Castle> retrieveByNationId(String nationId) {

        return CastleDoc.toDomains(castleMongoRepository.findByNationId(nationId));
    }

    @Override
    public void update(Castle castle) {

        String id = castle.getId();
        if (!castleMongoRepository.exists(id)) throw new NonExistenceException(String.format("No castle document[ID:%s] to update.", id));
        CastleDoc castleDoc = CastleDoc.toDocument(castle);
        castleMongoRepository.save(castleDoc);
    }

    @Override
    public void delete(String id) {

        castleMongoRepository.delete(id);
    }

    @Override
    public void delete(Castle castle) {

        castleMongoRepository.delete(castle.getId());
    }

    @Override
    public long retrieveNextSequence(String nationId) {

        Query query = new Query(Criteria.where("nationId").is(nationId));
        Update update = new Update();
        update.inc("castleSequence", 1);

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        options.upsert(true);

        CastleSequenceDoc sequenceDoc = mongoTemplate.findAndModify(query, update, options, CastleSequenceDoc.class);
        return sequenceDoc.getCastleSequence();
    }
}
