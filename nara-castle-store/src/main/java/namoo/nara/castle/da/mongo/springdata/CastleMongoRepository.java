package namoo.nara.castle.da.mongo.springdata;

import namoo.nara.castle.da.mongo.document.CastleDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
public interface CastleMongoRepository extends MongoRepository<CastleDoc, String> {

}
