package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.util.Identifiable;

public class Castellan implements Identifiable {
    //
    private String usid;            // == castle id
    private String displayName;
    private String photoId;         // profile photo id 
    private String primaryEmail;    // nullable
    private String primaryPhone;    // nullable, +82-10-9202-9989

    public Castellan() {
    }

    protected Castellan(String castleId, String displayName, String primaryEmail) {
        //
        this.usid = castleId;
        this.displayName = displayName;
        this.primaryEmail = primaryEmail;
    }

    public static Castellan newInstance(String usid, String displayName, String primaryEmail) {
        //
        return new Castellan(usid, displayName, primaryEmail);
    }

    @Override
    public String getId() {
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