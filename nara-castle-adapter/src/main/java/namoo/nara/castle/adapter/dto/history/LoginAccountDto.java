package namoo.nara.castle.adapter.dto.history;

import namoo.nara.castle.domain.entity.history.LoginAccount;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class LoginAccountDto {
    //
    private String loginUserId;
    private String channel;
    private long createTime;
    private long deleteTime;

    public LoginAccountDto() {
        //
    }

    public static LoginAccountDto newInstance(LoginAccount loginAccount) {
        //
        LoginAccountDto loginAccountDto = new LoginAccountDto();
        loginAccountDto.setLoginUserId(loginAccount.getLoginUserId());
        loginAccountDto.setChannel(loginAccount.getChannel().name());
        loginAccountDto.setCreateTime(loginAccount.getCreateTime());
        loginAccountDto.setDeleteTime(loginAccount.getDeleteTime());
        return loginAccountDto;
    }

    public LoginAccount toDomain() {
        //
        LoginAccount loginAccount = new LoginAccount();
        loginAccount.setLoginUserId(loginUserId);
        loginAccount.setChannel(LoginAccount.LoginChannel.valueOf(channel));
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
