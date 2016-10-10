package namoo.nara.castle.protocol;

import namoo.nara.castle.protocol.sdo.CastellanCreationSdo;
import namoo.nara.castle.protocol.sdo.CastellanFindSdo;
import namoo.nara.castle.protocol.sdo.JoinedMetroSdo;
import namoo.nara.castle.protocol.sdo.LoginAccountSdo;

import java.util.List;

public interface CastellanProtocol {
    //
    void createCastellan(String castleId, CastellanCreationSdo castellanCreationSdo);
    CastellanFindSdo findCastellan(String castleId);
    CastellanFindSdo findCastellan(String loginId, String loginIdType);
    void removeCastellan(String castleId);

    void addAccount(String castleId, LoginAccountSdo accountDto);
    List<LoginAccountSdo> findAccounts(String castleId);
    void removeAccount(String castleId, LoginAccountSdo accountDto);
    void modifyPassword(String castleId, String password);
    String findPassword(String castleId);

    void addEmail(String castleId, String email);
    void verifyEmail(String castleId, String email);
    void setPrimaryEmail(String castleId, String email);
    void removeEmail(String castleId, String email);

    void addJoinedMetro(String castleId, JoinedMetroSdo joinedMetroSdo);
    List<JoinedMetroSdo> findJoinedMetros(String castleId);
    void removeJoinedMetro(String castleId, JoinedMetroSdo joinedMetroSdo);
}
