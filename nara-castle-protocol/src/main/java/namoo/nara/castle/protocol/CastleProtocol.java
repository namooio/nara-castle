package namoo.nara.castle.protocol;

import namoo.nara.castle.protocol.sdo.CastleBuildSdo;
import namoo.nara.castle.protocol.sdo.CastleFindSdo;

import java.util.List;
import java.util.Locale;

public interface CastleProtocol {
    //
    String buildCastle(CastleBuildSdo castleBuildSdo);
    void modifyLocale(String castleId, Locale locale);
    CastleFindSdo findCastle(String castleId);
    List<CastleFindSdo> findCastles();
}
