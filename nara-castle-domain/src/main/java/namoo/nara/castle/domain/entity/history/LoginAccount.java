package namoo.nara.castle.domain.entity.history;

public class LoginAccount {
    //
    private String loginUserId;
    private LoginChannel channel;
    private long createTime;
    private long deleteTime;

    public LoginAccount() {
        //
    }

    public LoginAccount(String loginUserId, LoginChannel channel, long createTime) {
        //
        this.loginUserId = loginUserId;
        this.channel = channel;
        this.createTime = createTime;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
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

    enum LoginChannel {
        Nara,
        Facebook,
        Google
    }
}