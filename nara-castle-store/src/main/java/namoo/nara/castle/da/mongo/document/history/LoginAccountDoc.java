package namoo.nara.castle.da.mongo.document.history;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class LoginAccountDoc {
    //
    private String loginId;
    private String channel;
    private long createTime;
    private long deleteTime;

    public LoginAccountDoc() {
        //
    }

    public static LoginAccountDoc newInstance(LoginAccount loginAccount) {
        //
        LoginAccountDoc loginAccountDoc = new LoginAccountDoc();
        loginAccountDoc.setLoginId(loginAccount.getLoginId());
        loginAccountDoc.setChannel(loginAccount.getChannel().name());
        loginAccountDoc.setCreateTime(loginAccount.getCreateTime());
        loginAccountDoc.setDeleteTime(loginAccount.getDeleteTime());
        return loginAccountDoc;
    }

    public LoginAccount toDomain() {
        //
        LoginAccount loginAccount = new LoginAccount();
        loginAccount.setLoginId(loginId);
        loginAccount.setChannel(LoginAccount.LoginChannel.valueOf(channel));
        loginAccount.setCreateTime(createTime);
        loginAccount.setDeleteTime(deleteTime);
        return loginAccount;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
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
}
