package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.LoginIdType;
import namoo.nara.castle.domain.service.data.CastellanUdo;

public interface CastellanService {
    //
    void createCastellan(String castleId, String name);
    Castellan findCastellan(String castleId);
    Castellan findCastellan(String loginId, LoginIdType loginIdType);
    void modifyCastellan(String castleId, CastellanUdo castellanUdo);
    void modifyCastellanPhoto(String castleId, String photoId);
    void removeCastellan(String castleId);

    void addAccount(String castleId, String loginId, LoginIdType loginIdType);
    void removeAccount(String castleId, String loginId, LoginIdType loginIdType);
    String findPassword(String castleId);
    void modifyPassword(String castleId, String password);

    void addEmail(String castleId, String email);
    void verifyEmail(String castleId, String email);
    void setPrimaryEmail(String castleId, String email);
    void removeEmail(String castleId, String email);

    void addJoinedMetro(String castleId, String metroId, String citizenId);
    void removeJoinedMetro(String castleId, String metroId, String citizenId);


}
