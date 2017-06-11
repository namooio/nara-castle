package namoo.nara.castle.domain.spec;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.sdo.MetroEnrollmentCdo;
import namoo.nara.share.domain.NameValueList;

import java.util.List;

public interface CastleService {
    //
    String buildCastle(MetroEnrollmentCdo metroEnrollmentCdo);
    Castle findCastle(String castleId);
    Castle findCastleByEmail(String email);
    Castle findCastleByPhone(String phone);
    List<Castle> findCastleByLocaleName(String firstName, String familyName);
    List<Castle> findCastelByEngName(String firstName, String familyName);
    Castle findCastleByEnrolledMetro(String metroId, String civilianId);
    List<Castle> findCastlesOf(String metroId, int offset, int limit);

    void modifyCastle(NameValueList nameValues);
    void addMetroEnrollment(String castleId, MetroEnrollmentCdo metroEnrollmentCdo);
    void withdrawMetro(String castleId, String metroId, String civilianId);

    void removeCastle(String castleId);
}