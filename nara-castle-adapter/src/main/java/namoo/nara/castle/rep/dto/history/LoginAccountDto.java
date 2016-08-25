package namoo.nara.castle.rep.dto.history;

import java.io.Serializable;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class LoginAccountDto implements Serializable {
    //
    private static final long serialVersionUID = 7370266398778251099L;

    private String loginUserId;
    private String channel;
    private long createTime;
    private long deleteTime;

    public LoginAccountDto() {
        //
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
