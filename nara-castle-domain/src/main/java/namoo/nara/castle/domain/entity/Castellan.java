package namoo.nara.castle.domain.entity;

public class Castellan {
    //
    private String usid;
    private String displayName;
    private String photoId;         // profile photo id 
    private String primaryEmail;    // nullable
    private String primaryPhone;    // nullable

    public Castellan() {
    }

    protected Castellan(String usid, String displayName) {
        //
        this.usid = usid;
        this.displayName = displayName;
    }

    public static Castellan newInstance(String usid, String displayName) {
        //
        return new Castellan(usid, displayName);
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
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