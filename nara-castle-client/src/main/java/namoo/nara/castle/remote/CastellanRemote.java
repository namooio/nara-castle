package namoo.nara.castle.remote;

import namoo.nara.castle.remote.dto.CastellanCreateDto;
import namoo.nara.castle.remote.dto.CastellanReadDto;

/**
 * Created by kchuh@nextree.co.kr on 2016. 3. 15..
 */
public interface CastellanRemote {

    void create(CastellanCreateDto castellanCreateDto);

    void remove(String castellanId);

    CastellanReadDto findCastellan(String castellanId);

    CastellanReadDto findByVerifiedEmail(String email);

    void verifyEmail(String email, String castellanId);

    void setEmailAsPrimary(String email, String castellanId);

    void removeEmail(String email, String castellanId);

    void addEmail(String email, String castellanId);

    String findCastellanDisplayName(String castellanId);
}
