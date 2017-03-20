package namoo.nara.castle.domain.spec;

import namoo.nara.castle.domain.entity.JoinedMetro;
import namoo.nara.castle.domain.spec.sdo.CastleCdo;
import namoo.nara.castle.domain.spec.sdo.CastleRdo;

import java.util.List;
import java.util.Locale;

public interface CastleService {
    //
    String buildCastle(CastleCdo castleCdo);
    void modifyLocale(String castleId, Locale locale);
    CastleRdo findCastle(String castleId);
    CastleRdo findCastleByEmail(String email);
    List<CastleRdo> findCastles();

    void addEmail(String castleId, String email);
    void removeEmail(String castleId, String email);

    void addJoinedMetro(String castleId, JoinedMetro joinedMetro);
    List<JoinedMetro> findJoinedMetros(String castleId);
    void removeJoinedMetro(String castleId, String metroId);
    boolean isJoinedMetro(String castleId, String metroId);
}
