package namoo.nara.castle.adapter.logic;

import namoo.nara.castle.adapter.CastleAdapter;
import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import namoo.nara.castle.adapter.dto.util.DomainConversionUtil;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.CastleServiceLycler;

import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class CastleAdapterLogic implements CastleAdapter {
    //
    private CastleService castleService;

    public CastleAdapterLogic(CastleServiceLycler castleServiceLycler) {
        //
        castleService = castleServiceLycler.requestCastleService();
    }

    @Override
    public void buildCastle(String id, CastleBuildDto castleBuildDto) {
        //
        String name = castleBuildDto.getName();
        String email = castleBuildDto.getEmail();
        Locale locale = castleBuildDto.getLocale();
        String metroId = castleBuildDto.getMetroId();

        if (metroId != null && !metroId.isEmpty()) {
            castleService.buildCastle(id, name, email, locale, metroId);
        }
        else {
            castleService.buildCastle(id, name, email, locale);
        }

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
}
