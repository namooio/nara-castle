package namoo.nara.castle.da.mongo.mdo.history;

import namoo.nara.castle.domain.entity.history.LoginAccount;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class LoginAccountMdo {
    //
    private String loginUserId;
    private LoginAccount.LoginChannel channel;
    private long createTime;
    private long deleteTime;

    public LoginAccountMdo() {
        //
    }

    public static LoginAccountMdo newInstance(LoginAccount loginAccount) {
        //
        LoginAccountMdo loginAccountMdo = new LoginAccountMdo();
        loginAccountMdo.setLoginUserId(loginAccount.getLoginUserId());
        loginAccountMdo.setChannel(loginAccount.getChannel());
        loginAccountMdo.setCreateTime(loginAccount.getCreateTime());
        loginAccountMdo.setDeleteTime(loginAccount.getDeleteTime());
        return loginAccountMdo;
    }

    public LoginAccount getDomain() {
        //
        LoginAccount loginAccount = new LoginAccount();
        loginAccount.setLoginUserId(loginUserId);
        loginAccount.setChannel(channel);
        loginAccount.setCreateTime(createTime);
        loginAccount.setDeleteTime(deleteTime);
        return loginAccount;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    public LoginAccount.LoginChannel getChannel() {
        return channel;
    }

    public void setChannel(LoginAccount.LoginChannel channel) {
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
