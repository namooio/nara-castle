package namoo.nara.castle.adapter.dto.contact;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class UserEmailDto {
    //
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
