package namoo.nara.castle.front;

import namoo.nara.castle.front.dto.CastleFindDto;

import java.util.List;
import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 3. 15..
 */
public interface CastleFrontService {
    //
    void suspendCastle(String id, String remarks);
    void reopenCastle(String id, String remarks);
    void modifyName(String id, String name);
    void modifyLocale(String id, Locale locale);
    CastleFindDto findCastle(String id);
    List<CastleFindDto> findAllCastles();
}
