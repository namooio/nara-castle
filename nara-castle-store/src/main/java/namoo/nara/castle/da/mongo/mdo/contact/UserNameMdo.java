package namoo.nara.castle.da.mongo.mdo.contact;

import namoo.nara.castle.domain.entity.contact.UserName;

public class UserNameMdo {
    //
    private String familyName;
    private String firstName;
    private String displayName;
    private String middleName;  // updatable
    private String langCode;    // updatable

    public UserNameMdo() {
        //
    }

    public static UserNameMdo newInstance(UserName userName) {
        //
        UserNameMdo userNameMdo = new UserNameMdo();
        userNameMdo.setFamilyName(userName.getFamilyName());
        userNameMdo.setFirstName(userName.getFirstName());
        userNameMdo.setDisplayName(userName.getDisplayName());
        userNameMdo.setMiddleName(userName.getMiddleName());
        userNameMdo.setLangCode(userName.getLangCode());
        return userNameMdo;
    }

    public UserName getDomain() {
        //
        UserName userName = new UserName();
        userName.setFamilyName(familyName);
        userName.setFirstName(firstName);
        userName.setDisplayName(displayName);
        userName.setMiddleName(middleName);
        userName.setLangCode(displayName);
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