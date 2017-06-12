package namoo.nara.castle.domain.spec.drama;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.spec.sdo.MetroEnrollmentCdo;
import namoo.nara.share.domain.NameValueList;

public interface CastleProvider {
    //
    String addMetroEnrollment(MetroEnrollmentCdo metroEnrollmentCdo);
    String addMetroEnrollment(String castleId, MetroEnrollmentCdo metroEnrollmentCdo);
    void withdrawMetro(String castleId, String metroId, String civilianId);

    Castle findCastle(String castleId);
    Castle findCastleByEmail(String email);
    Castle findCastleByPhone(String phone);
    Castle findCastleByEnrolledMetro(String metroId, String civilianId);

    void modifyCastle(String castleId, NameValueList nameValues);

    Castellan findCastellan(String castleId);
    void modifyCastellan(String castleId, NameValueList nameValues);
}