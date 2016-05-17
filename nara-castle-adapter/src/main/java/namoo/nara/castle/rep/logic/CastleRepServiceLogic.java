package namoo.nara.castle.rep.logic;

import namoo.nara.castle.domain.service.CastleCdo;
import namoo.nara.castle.domain.service.CastleHistoryService;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.rep.CastleRepService;
import namoo.nara.castle.rep.dto.CastleBuildDto;

import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 9..
 */
public class CastleRepServiceLogic implements CastleRepService {
    //
    private CastleService castleService;
    private CastleHistoryService castleHistoryService;

    public CastleRepServiceLogic(CastleServiceLycler castleServiceLycler) {
        //
        castleService = castleServiceLycler.requestCastleService();
        castleHistoryService = castleServiceLycler.requestCastleHisotryService();
    }

    @Override
    public void buildCastle(String castleId, CastleBuildDto castleBuildDto) {
        //
        String name = castleBuildDto.getName();
        String email = castleBuildDto.getEmail();
        Locale locale = castleBuildDto.getLocale();
        String password = castleBuildDto.getPassword();

        CastleCdo castleCdo = new CastleCdo(castleId, name, email, locale, password);
        castleService.buildCastle(castleCdo);
    }

    @Override
    public void addMetro(String castleId, String metroId) {
        //
        castleHistoryService.addMetro(castleId, metroId);
    }
}
