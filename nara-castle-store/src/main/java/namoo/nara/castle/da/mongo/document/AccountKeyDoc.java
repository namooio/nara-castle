package namoo.nara.castle.da.mongo.document;

public class AccountKeyDoc {

    private String loginId;
    private String loginIdType;

    private AccountKeyDoc(String loginId, String loginIdType) {
        //
        this.loginId = loginId;
        this.loginIdType = loginIdType;
    }

    public static AccountKeyDoc newInstance(String loginId, String loginIdType) {
        //
        return new AccountKeyDoc(loginId, loginIdType);
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
