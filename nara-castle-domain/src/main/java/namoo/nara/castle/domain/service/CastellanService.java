package namoo.nara.castle.domain.service;

public interface CastellanService {
    //
    void modifyDisplayName(String id, String displayName);
    void modifyPhoto(String id, String photoId);
    void modifyPrimaryEmail(String id, String email);
    void modifyPrimaryPhone(String id, String phoneNumber);
}