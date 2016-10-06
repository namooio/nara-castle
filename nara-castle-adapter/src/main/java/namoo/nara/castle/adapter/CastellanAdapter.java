package namoo.nara.castle.adapter;

import namoo.nara.castle.adapter.dto.CastellanCreationDto;
import namoo.nara.castle.adapter.dto.CastellanFindDto;
import namoo.nara.castle.domain.entity.LoginIdType;

public interface CastellanAdapter {
    //
    void createCastellan(String castleId, CastellanCreationDto castellanCreationDto);
    CastellanFindDto findCastellan(String castleId);
    CastellanFindDto findCastellan(String loginId, String loginIdType);
    void modifyCastellanName(String castleId, String name);
    void modifyCastellanPhoto(String castleId, String photoId);
    void removeCastellan(String castleId);

    void addAccount(String castleId, String loginId, LoginIdType loginIdType);
    void removeAccount(String castleId, String loginId, LoginIdType loginIdType);
    void modifyPasswordCredential(String castleId, String password);

    void addEmail(String castleId, String email);
    void verifyEmail(String castleId, String email);
    void setPrimaryEmail(String castleId, String email);
    void removeEmail(String castleId, String email);

    void addJoinedMetro(String castleId, String metroId, String citizenId);
    void removeJoinedMetro(String castleId, String metroId, String citizenId);
}
