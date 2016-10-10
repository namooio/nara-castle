package namoo.nara.castle.protocol.sdo;

import java.io.Serializable;

public class LoginAccountSdo implements Serializable {
    //
    private String loginId;
    private String loginIdType;

    public LoginAccountSdo() {

    }

    public LoginAccountSdo(String loginId, String loginIdType) {
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
