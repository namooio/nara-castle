package namoo.nara.castle.da.mongo.springdata;

import namoo.nara.castle.da.mongo.document.AccountKeyDoc;
import namoo.nara.castle.da.mongo.document.CastellanDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CastellanMongoRepository extends MongoRepository<CastellanDoc, String> {

    CastellanDoc findByAccountsKey(AccountKeyDoc key);
}
