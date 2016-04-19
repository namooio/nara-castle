package namoo.nara.castle.adapter.dto.contact;

import namoo.nara.castle.domain.entity.contact.UserName;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class UserNameDto {
    //
    private String familyName;
    private String firstName;
    private String displayName;
    private String middleName;  // updatable
    private String langCode;    // updatable

    public UserNameDto() {
        //
    }

    public UserName toDomain() {
        //
        UserName userName = new UserName();
        userName.setFamilyName(familyName);
        userName.setFirstName(firstName);
        userName.setDisplayName(displayName);
        userName.setMiddleName(middleName);
        userName.setLangCode(displayName);
        return userName;
    }

    public static UserNameDto newInstance(UserName userName) {
        //
        UserNameDto userNameDto = new UserNameDto();
        userNameDto.setFamilyName(userName.getFamilyName());
        userNameDto.setFirstName(userName.getFirstName());
        userNameDto.setDisplayName(userName.getDisplayName());
        userNameDto.setMiddleName(userName.getMiddleName());
        userNameDto.setLangCode(userName.getLangCode());
        return userNameDto;
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
