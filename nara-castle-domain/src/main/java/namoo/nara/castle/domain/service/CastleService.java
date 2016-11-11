package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.JoinedMetro;
import namoo.nara.castle.domain.service.data.CastleCdo;

import java.util.List;
import java.util.Locale;

public interface CastleService {
    //
    String buildCastle(CastleCdo castleCdo);
    void modifyLocale(String castleId, Locale locale);
    Castle findCastle(String castleId);
    Castle findCastleByEmail(String email);
    List<Castle> findCastles();

    void addEmail(String castleId, String email);
    void removeEmail(String castleId, String email);

    void addJoinedMetro(String castleId, String metroId, String citizenId);
    List<JoinedMetro> findJoinedMetros(String castleId);
    void removeJoinedMetro(String castleId, String metroId);
}