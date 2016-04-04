package namoo.nara.castle.domain.entity;

public class Castellan {
    //
    private String usid;
    private String displayName;
    private String loginId;
    private String photoId;         // profile photo
    private String primaryEmail;    // nullable
    private String primaryPhone;    // nullable

    private InfoBundleBox infoBundleBox;

    public Castellan() {
    }

    protected Castellan(String usid, String displayName, String loginId) {
        //
        this.usid = usid;
        this.displayName = displayName;
        this.loginId = loginId;
        this.infoBundleBox = new InfoBundleBox();
    }

    public static Castellan newInstance(String usid, String displayName, String loginId) {
        //
        return new Castellan(usid, displayName, loginId);
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

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
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

    public InfoBundleBox getInfoBundleBox() {
        return infoBundleBox;
    }

    public void setInfoBundleBox(InfoBundleBox infoBundleBox) {
        this.infoBundleBox = infoBundleBox;
    }
}