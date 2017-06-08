package namoo.nara.castle.da.mongo.springdata;

import namoo.nara.castle.da.mongo.document.CastleDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CastleMongoRepository extends MongoRepository<CastleDoc, String> {

    List<CastleDoc> findByNationId(String nationId);
    CastleDoc findByCastellanEmailsEmailsEmail(String email);
    CastleDoc findByCastellanJoinedMetrosNationIdAndCastellanJoinedMetrosMetroIdAndCastellanJoinedMetrosCivilianId(String nationId, String metroId, String civilianId);
}
