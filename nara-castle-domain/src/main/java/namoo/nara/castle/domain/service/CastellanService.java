package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.LoginIdType;

public interface CastellanService {
    //
    void createCastellan(String castleId, String name);
    Castellan findCastellan(String castleId);
    Castellan findCastellan(String loginId, LoginIdType loginIdType);
    void modifyCastellanName(String castleId, String name);
    void modifyCastellanPhoto(String castleId, String photoId);
    void removeCastellan(String castleId);

    void addAccount(String castleId, String loginId, LoginIdType loginIdType);
    void removeAccount(String castleId, String loginId, LoginIdType loginIdType);
    void modifyPasswordCredential(String castleId, String password);

    void addEmail(String id, String email);
    void verifyEmail(String id, String email);
    void removeEmail(String id, String email);

    void addJoinedMetro(String castleId, String metroId, String citizenId);
    void removeJoinedMetro(String castleId, String metroId, String citizenId);

}
