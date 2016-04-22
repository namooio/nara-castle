package namoo.nara.castle.adapter.dto;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 22..
 */
public class CastellanFindDto {
    //
    private String displayName;
    private String photoId;         // profile photo id
    private String primaryEmail;    // nullable
    private String primaryPhone;    // nullable, +82-10-9202-9989

    public CastellanFindDto() {
        //
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }
}
