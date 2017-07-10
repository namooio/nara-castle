package namoo.nara.castle.da.mongo.springdata;

import namoo.nara.castle.da.mongo.document.CastellanViewDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CastellanViewMongoRepository extends MongoRepository<CastellanViewDoc, String> {
    //
}
