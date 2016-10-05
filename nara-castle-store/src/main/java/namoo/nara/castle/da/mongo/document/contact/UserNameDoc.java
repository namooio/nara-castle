package namoo.nara.castle.da.mongo.document.contact;

public class UserNameDoc {
    //
    private String familyName;
    private String firstName;
    private String displayName;
    private String middleName;  // updatable
    private String langCode;    // updatable

    public UserNameDoc() {
        //
    }

    public static UserNameDoc newInstance(UserName userName) {
        //
        UserNameDoc userNameDoc = new UserNameDoc();
        userNameDoc.setFamilyName(userName.getFamilyName());
        userNameDoc.setFirstName(userName.getFirstName());
        userNameDoc.setDisplayName(userName.getDisplayName());
        userNameDoc.setMiddleName(userName.getMiddleName());
        userNameDoc.setLangCode(userName.getLangCode());
        return userNameDoc;
    }

    public UserName toDomain() {
        //
        UserName userName = new UserName();
        userName.setFamilyName(familyName);
        userName.setFirstName(firstName);
        userName.setDisplayName(displayName);
        userName.setMiddleName(middleName);
        userName.setLangCode(langCode);
        return userName;
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