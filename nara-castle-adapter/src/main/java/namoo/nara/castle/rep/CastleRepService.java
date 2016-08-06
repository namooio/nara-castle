package namoo.nara.castle.rep;

import namoo.nara.castle.rep.dto.CastleBuildDto;
import namoo.nara.castle.rep.dto.CastleFindDto;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 9..
 */
public interface CastleRepService {
    //
    void buildCastle(String castleId, CastleBuildDto castleBuildDto);
    CastleFindDto findCastle(String castleId);

    void addMetro(String castleId, String metroId);

    void addEmail(String castleId, String email);
    void removeEmail(String castleId, String email);
    void verifyEmail(String castleId, String email);
}
