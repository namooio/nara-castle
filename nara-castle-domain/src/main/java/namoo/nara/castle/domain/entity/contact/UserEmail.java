package namoo.nara.castle.domain.entity.contact;

public class UserEmail {
    //
    private String email;
    private EmailType emailType;
    private boolean verified;
    private long verifiedTime;

    public UserEmail() {
    }

    public UserEmail(String email) {
        //
        this.email = email;
        this.verified = false;
    }

    public UserEmail(String email, EmailType emailType) {
        //
        this.email = email;
        this.emailType = emailType;
        this.verified = false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmailType getEmailType() {
        return emailType;
    }

    public void setEmailType(EmailType emailType) {
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

    public enum EmailType {
        Business,
        Private
    }
}