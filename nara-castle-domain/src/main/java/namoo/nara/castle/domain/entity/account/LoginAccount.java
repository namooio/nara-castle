package namoo.nara.castle.domain.entity.account;

import namoo.nara.share.domain.ValueObject;

import java.time.ZonedDateTime;

public class LoginAccount implements ValueObject {
    //
    private String loginId;
    private LoginIdType loginIdType;

    private ZonedDateTime createdTime;

    public LoginAccount() {

    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public LoginIdType getLoginIdType() {
        return loginIdType;
    }

    public void setLoginIdType(LoginIdType loginIdType) {
        this.loginIdType = loginIdType;
    }

    public ZonedDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(ZonedDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "LoginAccount{" +
                "loginId='" + loginId + '\'' +
                ", loginIdType=" + loginIdType +
                ", createdTime=" + createdTime +
                '}';
    }
}

