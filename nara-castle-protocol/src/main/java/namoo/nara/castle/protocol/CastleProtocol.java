package namoo.nara.castle.protocol;

import namoo.nara.castle.protocol.sdo.CastleBuildSdo;
import namoo.nara.castle.protocol.sdo.CastleSdo;
import namoo.nara.castle.protocol.sdo.JoinedMetroAddSdo;
import namoo.nara.castle.protocol.sdo.JoinedMetroSdo;

import java.util.List;
import java.util.Locale;

public interface CastleProtocol {
    //
    String buildCastle(CastleBuildSdo castleBuildSdo);
    void modifyLocale(String castleId, Locale locale);
    CastleSdo findCastle(String castleId);
    List<CastleSdo> findCastles();

    void addEmail(String castleId, String email);
    void verifyEmail(String castleId, String email);
    void removeEmail(String castleId, String email);

    void addJoinedMetro(String castleId, JoinedMetroAddSdo joinedMetroAddSdo);
    List<JoinedMetroSdo> findJoinedMetros(String castleId);
    void removeJoinedMetro(String castleId, String metroId);
}
