package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.ValueObject;

public class Castellan implements ValueObject {
    //
    private String displayName;
    private String photoId;         // profile photo id 
    private String primaryEmail;    // nullable

    public Castellan() {

    }

    public Castellan(String displayName, String primaryEmail) {
        //
        this.displayName = displayName;
        this.primaryEmail = primaryEmail;
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

    @Override
    public String toString() {
        return "Castellan{" +
                "displayName='" + displayName + '\'' +
                ", photoId='" + photoId + '\'' +
                ", primaryEmail='" + primaryEmail + '\'' +
                '}';
    }

}