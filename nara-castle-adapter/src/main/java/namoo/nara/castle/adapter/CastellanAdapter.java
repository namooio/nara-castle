package namoo.nara.castle.adapter;

import namoo.nara.castle.adapter.dto.CastellanCreationDto;
import namoo.nara.castle.adapter.dto.CastellanFindDto;
import namoo.nara.castle.adapter.dto.JoinedMetroDto;
import namoo.nara.castle.adapter.dto.LoginAccountDto;

public interface CastellanAdapter {
    //
    void createCastellan(String castleId, CastellanCreationDto castellanCreationDto);
    CastellanFindDto findCastellan(String castleId);
    CastellanFindDto findCastellan(String loginId, String loginIdType);
    void removeCastellan(String castleId);

    void addAccount(String castleId, LoginAccountDto accountDto);
    void removeAccount(String castleId, LoginAccountDto accountDto);
    void modifyPassword(String castleId, String password);
    String findPassword(String castleId);

    void addEmail(String castleId, String email);
    void verifyEmail(String castleId, String email);
    void setPrimaryEmail(String castleId, String email);
    void removeEmail(String castleId, String email);

    void addJoinedMetro(String castleId, JoinedMetroDto joinedMetroDto);
    void removeJoinedMetro(String castleId, JoinedMetroDto joinedMetroDto);
}
