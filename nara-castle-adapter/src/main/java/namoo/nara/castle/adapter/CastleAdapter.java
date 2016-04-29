package namoo.nara.castle.adapter;

import namoo.nara.castle.adapter.dto.CastleBuildDto;
import namoo.nara.castle.adapter.dto.CastleFindDto;

import java.util.List;
import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 3. 15..
 */
public interface CastleAdapter {
    //
    void buildCastle(String id, CastleBuildDto castleBuildDto);
    void suspendCastle(String id, String remarks);
    void reopenCastle(String id, String remarks);
    void modifyName(String id, String name);
    void modifyLocale(String id, Locale locale);
    CastleFindDto findCastle(String id);
    List<CastleFindDto> findAllCastles();
}
