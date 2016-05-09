package namoo.nara.castle.rep;

import namoo.nara.castle.rep.dto.CastleBuildDto;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 9..
 */
public interface CastleRepService {
    //
    void buildCastle(String castleId, CastleBuildDto castleBuildDto);
    void addMetro(String castleId, String metroId);
}
