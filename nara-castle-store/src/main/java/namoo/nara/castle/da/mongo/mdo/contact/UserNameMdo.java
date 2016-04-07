package namoo.nara.castle.da.mongo.mdo.contact;

public class UserNameMdo {
    //
    private String familyName;
    private String firstName;
    private String displayName;
    private String middleName;  // updatable
    private String langCode;    // updatable

    public UserNameMdo() {
    }

    public UserNameMdo(String familyName, String firstName, String displayName, String langCode) {
        //
        this.familyName = familyName;
        this.firstName = firstName;
        this.displayName = displayName;
        this.langCode = langCode;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }
}