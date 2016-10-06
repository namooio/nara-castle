package namoo.nara.castle.adapter;

import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;

import java.util.List;
import java.util.Locale;

public interface CastleAdapter {
    //
    String buildCastle(CastleBuildDto castleBuildDto);
    void modifyLocale(String castleId, Locale locale);
    CastleFindDto findCastle(String castleId);
    List<CastleFindDto> findCastles();
}
