package namoo.nara.castle.domain.service;

import java.util.Locale;

public interface CastleService {
    //
    void buildCastle(String id, String name, String metroId, Locale locale);
    void suspendCastle(String id, String remarks);
    void reopenCastle(String id, String remarks);
    void modifyName(String id, String name);
    void modifyLocale(String id, Locale locale);
}