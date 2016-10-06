package namoo.nara.castle.adapter.logic;

import namoo.nara.castle.adapter.CastleAdapter;
import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import namoo.nara.castle.adapter.dto.util.DtoUtils;
import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.CastleServiceLycler;
import namoo.nara.castle.domain.service.data.CastleCdo;

import java.util.List;
import java.util.Locale;

public class CastleAdapterLogic implements CastleAdapter{
    //
    private CastleService castleService;

    public CastleAdapterLogic(CastleServiceLycler serviceLycler) {
        //
        this.castleService = serviceLycler.requestCastleService();
    }

    @Override
    public String buildCastle(CastleBuildDto castleBuildDto) {
        //
        CastleCdo castleCdo = DtoUtils.toCastleCdo(castleBuildDto);
        return this.castleService.buildCastle(castleCdo);
    }

    @Override
    public void modifyLocale(String castleId, Locale locale) {
        //
        this.castleService.modifyLocale(castleId, locale);
    }

    @Override
    public CastleFindDto findCastle(String castleId) {
        //
        Castle castle = this.castleService.findCastle(castleId);
        return DtoUtils.toCastleFindDto(castle);
    }

    @Override
    public List<CastleFindDto> findCastles() {
        //
        List<Castle> castles = this.castleService.findCastles();
        return DtoUtils.toCastleFindDto(castles);
    }
}
