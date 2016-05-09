package namoo.nara.castle.front;

import namoo.nara.castle.front.dto.CastellanFindDto;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public interface CastellanFrontService {
    //
    CastellanFindDto findCastellan(String id);
    void modifyDisplayName(String id, String displayName);
    void modifyPhoto(String id, String photoId);
    void modifyPrimaryEmail(String id, String email);
    void modifyPrimaryPhone(String id, String phoneNumber);
}
