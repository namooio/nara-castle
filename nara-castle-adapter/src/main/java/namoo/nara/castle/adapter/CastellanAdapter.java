package namoo.nara.castle.adapter;

import namoo.nara.castle.adapter.dto.*;

public interface CastellanAdapter {
    //
    void createCastellan(String castleId, CastellanCreationDto castellanCreationDto);
    CastellanFindDto findCastellan(String castleId);
    CastellanFindDto findCastellan(String loginId, String loginIdType);
    void modifyCastellan(String castleId, CastellanModificationDto castellanModificationDto);
    void modifyCastellanPhoto(String castleId, String photoId);
    void removeCastellan(String castleId);

    void addAccount(String castleId, LoginAccountDto accountDto);
    void removeAccount(String castleId, LoginAccountDto accountDto);
    void modifyPasswordCredential(String castleId, String password);

    void addEmail(String castleId, String email);
    void verifyEmail(String castleId, String email);
    void setPrimaryEmail(String castleId, String email);
    void removeEmail(String castleId, String email);

    void addJoinedMetro(String castleId, JoinedMetroDto joinedMetroDto);
    void removeJoinedMetro(String castleId, JoinedMetroDto joinedMetroDto);
}
