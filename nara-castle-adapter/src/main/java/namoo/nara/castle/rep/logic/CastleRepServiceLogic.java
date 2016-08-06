package namoo.nara.castle.rep.logic;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.service.*;
import namoo.nara.castle.rep.CastleRepService;
import namoo.nara.castle.rep.dto.CastleBuildDto;
import namoo.nara.castle.rep.dto.CastleFindDto;

import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 9..
 */
public class CastleRepServiceLogic implements CastleRepService {
    //
    private CastleService castleService;
    private CastellanContactService castellanContactService;
    private CastleHistoryService castleHistoryService;

    public CastleRepServiceLogic(CastleServiceLycler castleServiceLycler) {
        //
        castleService = castleServiceLycler.requestCastleService();
        castellanContactService = castleServiceLycler.requestCastellanContactService();
        castleHistoryService = castleServiceLycler.requestCastleHisotryService();
    }

    @Override
    public void buildCastle(String castleId, CastleBuildDto castleBuildDto) {
        //
        String name = castleBuildDto.getName();
        String email = castleBuildDto.getEmail();
        Locale locale = castleBuildDto.getLocale();
        CastleCdo castleCdo = new CastleCdo(castleId, name, email, locale);
        castleService.buildCastle(castleCdo);
    }

    @Override
    public CastleFindDto findCastle(String castleId) {
        //
        Castle castle = castleService.findCastle(castleId);

        CastleFindDto dto = new CastleFindDto();
        dto.setId(castle.getId());
        dto.setName(castle.getName());
        dto.setLocale(castle.getLocale());
        dto.setState(castle.getState().name());
        dto.setBuildTime(castle.getBuildTime());

        return dto;
    }

    @Override
    public void addMetro(String castleId, String metroId) {
        //
        castleHistoryService.addMetro(castleId, metroId);
    }

    @Override
    public void addEmail(String castleId, String email) {
        //
        castellanContactService.addEmail(castleId, email);
    }

    @Override
    public void removeEmail(String castleId, String email) {
        //
        castellanContactService.removeEmail(castleId, email);
    }

    @Override
    public void verifyEmail(String castleId, String email) {
        //
        castellanContactService.verifyEmail(castleId, email);
    }
}
