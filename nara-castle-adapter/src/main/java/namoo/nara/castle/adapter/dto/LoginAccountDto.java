package namoo.nara.castle.adapter.dto;

import java.io.Serializable;

public class LoginAccountDto implements Serializable {
    //
    private String loginId;
    private String loginIdType;

    public LoginAccountDto() {

    }

    public LoginAccountDto(String loginId, String loginIdType) {
        //
        this.loginId = loginId;
        this.loginIdType = loginIdType;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginIdType() {
        return loginIdType;
    }

    public void setLoginIdType(String loginIdType) {
        this.loginIdType = loginIdType;
    }
}
