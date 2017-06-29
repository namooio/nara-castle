package namoo.nara.castle.domain.spec;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.IdentityPlate;
import namoo.nara.castle.domain.spec.command.castle.EnrollMetroCommand;
import namoo.nara.share.domain.NameValueList;

public interface CastleService {
    //
    // command
    String enrollMetro(EnrollMetroCommand enrollMetroCommand);
    void withdrawMetro(String castleId, String metroId, String civilianId);
    void modifyCastle(String castleId, NameValueList nameValues);

    // query
    Castle findCastle(String castleId);
    Castle findCastleByEmail(String email);
    Castle findCastleByPhone(String phone);
    Castle findCastleByEnrolledMetro(String metroId, String civilianId);
    IdentityPlate findIdentityPlate(String castleId);

    // castellan query
    Castellan findCastellan(String castleId);
    boolean existsCastellan(String castleId);
    void modifyCastellan(String castleId, NameValueList nameValues);

}