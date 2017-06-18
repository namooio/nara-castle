package namoo.nara.castle.domain.spec;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.IdentityPlate;
import namoo.nara.castle.domain.spec.sdo.MetroEnrollmentCdo;
import namoo.nara.share.domain.NameValueList;

public interface CastleService {
    //
    String enrollMetro(MetroEnrollmentCdo metroEnrollmentCdo);
    void withdrawMetro(String castleId, String metroId, String civilianId);

    Castle findCastle(String castleId);
    Castle findCastleByEmail(String email);
    Castle findCastleByPhone(String phone);
    Castle findCastleByEnrolledMetro(String metroId, String civilianId);

    void modifyCastle(String castleId, NameValueList nameValues);

    Castellan findCastellan(String castleId);
    boolean existsCastellan(String castleId);
    void modifyCastellan(String castleId, NameValueList nameValues);
    IdentityPlate findIdentityPlate(String castleId);
}