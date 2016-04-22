package namoo.nara.castle.domain.service;

import namoo.nara.castle.domain.entity.Castellan;

public interface CastellanService {
    //
    Castellan findCastellan(String id);
    void modifyDisplayName(String id, String displayName);
    void modifyPhoto(String id, String photoId);
    void modifyPrimaryEmail(String id, String email);
    void modifyPrimaryPhone(String id, String phoneNumber);
}