package namoo.nara.castle.domain.service;

import java.util.Locale;

public interface CastleService {
    //
    void buildCastle(String id, String name, String metroId, Locale locale);
    void suspendCastle(String id, String remarks);
    void reopenCastle(String id, String remarks);
    void modifyName(String id, String name);
    void modifyLocale(String id, Locale locale);


    // AS-IS
    /*
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
    */
}