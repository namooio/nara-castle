package namoo.nara.castle.spec;

import namoo.nara.castle.spec.sdo.CastleCdo;
import namoo.nara.castle.spec.sdo.CastleSdo;
import namoo.nara.castle.spec.sdo.JoinedMetroCdo;
import namoo.nara.castle.spec.sdo.JoinedMetroSdo;

import java.util.List;
import java.util.Locale;

public interface CastleService {
    //
    String buildCastle(CastleCdo castleCdo);
    void modifyLocale(String castleId, Locale locale);
    CastleSdo findCastle(String castleId);
    CastleSdo findCastleByEmail(String email);
    List<CastleSdo> findCastles();

    void addEmail(String castleId, String email);
    void removeEmail(String castleId, String email);

    void addJoinedMetro(String castleId, JoinedMetroCdo joinedMetroCdo);
    List<JoinedMetroSdo> findJoinedMetros(String castleId);
    void removeJoinedMetro(String castleId, String metroId);
    boolean isJoinedMetro(String castleId, String metroId);
}
