package namoo.nara.castle.da.mongo.springdata;

import namoo.nara.castle.da.mongo.document.CastleDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CastleMongoRepository extends MongoRepository<CastleDoc, String> {

}
