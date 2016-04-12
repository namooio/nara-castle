package namoo.nara.castle.adapter.service;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public interface CastellanAdapter {
    //
    void modifyDisplayName(String id, String displayName);
    void modifyPhoto(String id, String photoId);
    void modifyPrimaryEmail(String id, String email);
    void modifyPrimaryPhone(String id, String phoneNumber);
}
