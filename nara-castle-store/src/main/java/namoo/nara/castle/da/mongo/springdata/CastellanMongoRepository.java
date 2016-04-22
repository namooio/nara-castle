package namoo.nara.castle.da.mongo.springdata;

import namoo.nara.castle.da.mongo.document.CastellanDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 6..
 */
public interface CastellanMongoRepository extends MongoRepository<CastellanDoc, String> {
}
