package namoo.nara.castle.adapter.logic;

import namoo.nara.castle.adapter.service.CastleAdapter;
import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
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
        String metroId = castleBuildDto.getMetroId();
        Locale locale = castleBuildDto.getLocale();
        castleService.buildCastle(id, name, metroId, locale);
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
        return CastleFindDto.newInstance(castle);
    }
}
