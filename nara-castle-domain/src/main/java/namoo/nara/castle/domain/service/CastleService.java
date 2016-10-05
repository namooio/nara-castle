package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.service.data.CastleCdo;

import java.util.List;
import java.util.Locale;

public interface CastleService {
    //
    String buildCastle(CastleCdo castleCdo);
    void modifyLocale(String castleId, Locale locale);
    Castle findCastle(String castleId);
    List<Castle> findCastles();
}