package namoo.nara.castle.domain.spec;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.sdo.CastleCdo;
import namoo.nara.share.domain.NameValueList;

import java.util.List;

public interface CastleService {

    String buildCastle(CastleCdo castleCdo);
    Castle findCastle(String castleId);
    Castle findCastleByEmail(String email);
    List<Castle> findCastles();
    void modifyCastle(String castleId, NameValueList nameValues);
    void removeCastle(String castleId);

}
