package namoo.nara.castle.da.mongo.springdata;

import namoo.nara.castle.da.mongo.document.CastleViewDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CastleViewMongoRepository extends MongoRepository<CastleViewDoc, String> {
    //
    CastleViewDoc findByEnrollmentsMetroIdAndEnrollmentsCivilianId(String metroId, String civilianId);
}
