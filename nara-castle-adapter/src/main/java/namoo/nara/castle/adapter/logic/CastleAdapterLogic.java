package namoo.nara.castle.adapter.logic;

import namoo.nara.castle.adapter.CastleAdapter;
import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;
import namoo.nara.castle.domain.service.CastleService;
import namoo.nara.castle.domain.service.CastleServiceLycler;

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
        return null;
    }

    @Override
    public void modifyLocale(String castleId, Locale locale) {

    }

    @Override
    public CastleFindDto findCastle(String castleId) {
        return null;
    }

    @Override
    public List<CastleFindDto> findCastles() {
        return null;
    }
}
