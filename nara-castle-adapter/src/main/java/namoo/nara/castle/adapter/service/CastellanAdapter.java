package namoo.nara.castle.adapter.service;

import namoo.nara.castle.adapter.dto.CastellanFindDto;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public interface CastellanAdapter {
    //
    CastellanFindDto findCastellan(String id);
    void modifyDisplayName(String id, String displayName);
    void modifyPhoto(String id, String photoId);
    void modifyPrimaryEmail(String id, String email);
    void modifyPrimaryPhone(String id, String phoneNumber);
}
