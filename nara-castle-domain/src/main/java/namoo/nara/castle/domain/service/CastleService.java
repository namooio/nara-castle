package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.contact.UserName;
import namoo.nara.castle.domain.entity.CastleState;

public interface CastleService {
    //
    void registerCastellan(String castellanId);
    void registerCastellan(String castellanId, String email);
    void removeCastellan(String castellanId);
    Castellan findCastellan(String castellanId);
    Castellan findCastellanByVerifiedEmail(String email);
    void addCastellanEmail(String email, String castellanId);
    void verifyCastellanEmail(String email, String castellanId);
    void setAsPrimaryEmail(String email, String castellanId);
    void addName(UserName castellanName, String castellanId);
    void removeCastellanEmail(String email, String castellanId);
    void changeCastleStatus(CastleState status, String castellanId);
    String findCastellanDisplayName(String castellanId);
}