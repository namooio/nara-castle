package namoo.nara.castle.rep.dto.contact;

import java.io.Serializable;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class UserEmailDto implements Serializable {
    //
    private static final long serialVersionUID = -5293263228794896597L;

    private String email;
    private String emailType;
    private boolean verified;
    private long verifiedTime;

    public UserEmailDto() {
        //
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public long getVerifiedTime() {
        return verifiedTime;
    }

    public void setVerifiedTime(long verifiedTime) {
        this.verifiedTime = verifiedTime;
    }
}
