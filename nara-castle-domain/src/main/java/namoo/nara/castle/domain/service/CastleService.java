package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.Castle;

import java.util.List;
import java.util.Locale;

public interface CastleService {
    //
    void buildCastle(CastleCdo castleCdo);
    void suspendCastle(String id, String remarks);
    void reopenCastle(String id, String remarks);
    void modifyName(String id, String name);
    void modifyLocale(String id, Locale locale);
    Castle findCastle(String id);
    List<Castle> findAllCastles();

}