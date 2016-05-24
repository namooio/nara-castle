package namoo.nara.castle.domain.entity.history;

public class LoginAccount {
    //
    private String loginId;
    private LoginChannel channel;
    private long createTime;
    private long deleteTime;

    public LoginAccount() {
        //
    }

    protected LoginAccount(String loginId, LoginChannel channel) {
        //
        this.loginId = loginId;
        this.channel = channel;
        this.createTime = System.currentTimeMillis();
    }

    public static LoginAccount newInstance(String loginId, LoginChannel loginChannel) {
        //
        return new LoginAccount(loginId, loginChannel);
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public LoginChannel getChannel() {
        return channel;
    }

    public void setChannel(LoginChannel channel) {
        this.channel = channel;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public enum LoginChannel {
        NaraEmail,
        NaraUsername,
        Facebook,
        Google
    }
}