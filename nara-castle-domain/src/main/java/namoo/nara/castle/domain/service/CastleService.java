package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.Castle;

import java.util.Locale;

public interface CastleService {
    //
    void buildCastle(String id, String name, String email, Locale locale);
    void buildCastle(String id, String name, String email, Locale locale, String metroId);
    void suspendCastle(String id, String remarks);
    void reopenCastle(String id, String remarks);
    void modifyName(String id, String name);
    void modifyLocale(String id, Locale locale);
    Castle findCastle(String id);


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