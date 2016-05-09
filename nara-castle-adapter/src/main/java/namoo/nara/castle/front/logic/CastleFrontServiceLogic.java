package namoo.nara.castle.front.logic;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.front.CastleFrontService;
import namoo.nara.castle.front.dto.CastleFindDto;
import namoo.nara.castle.front.dto.util.DomainConversionUtil;

import java.util.List;
import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class CastleFrontServiceLogic implements CastleFrontService {
    //
    private CastleService castleService;

    public CastleFrontServiceLogic(CastleServiceLycler castleServiceLycler) {
        //
        castleService = castleServiceLycler.requestCastleService();
    }

    @Override
    public void suspendCastle(String id, String remarks) {
        //
        castleService.suspendCastle(id, remarks);
    }

    @Override
    public void reopenCastle(String id, String remarks) {
        //
        castleService.reopenCastle(id, remarks);
    }

    @Override
    public void modifyName(String id, String name) {
        //
        castleService.modifyName(id, name);
    }

    @Override
    public void modifyLocale(String id, Locale locale) {
        //
        castleService.modifyLocale(id, locale);
    }

    @Override
    public CastleFindDto findCastle(String id) {
        //
        Castle castle = castleService.findCastle(id);
        return DomainConversionUtil.toCastleFindDto(castle);
    }

    @Override
    public List<CastleFindDto> findAllCastles() {
        List<Castle> allCastles = castleService.findAllCastles();
        return DomainConversionUtil.toCastleFindDtoList(allCastles);
    }
}
