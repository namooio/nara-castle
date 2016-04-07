package namoo.nara.castle.da.mongo.mdo.contact;

import namoo.nara.castle.domain.entity.contact.UserEmail;

public class UserEmailMdo {
    //
    private String email;
    private UserEmail.EmailType emailType; // Redefine enum type for mongo document?
    private boolean verified;
    private long verifiedTime;

    public UserEmailMdo() {
        //
    }

    public static UserEmailMdo newInstance(UserEmail userEmail) {
        //
        UserEmailMdo userEmailMdo = new UserEmailMdo();
        userEmailMdo.setEmail(userEmail.getEmail());
        userEmailMdo.setEmailType(userEmail.getEmailType());
        userEmailMdo.setVerified(userEmail.isVerified());
        userEmailMdo.setVerifiedTime(userEmail.getVerifiedTime());
        return userEmailMdo;
    }

    public UserEmail getDomain() {
        //
        UserEmail userEmail = new UserEmail();
        userEmail.setEmail(email);
        userEmail.setEmailType(emailType);
        userEmail.setVerified(verified);
        userEmail.setVerifiedTime(verifiedTime);
        return userEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEmail.EmailType getEmailType() {
        return emailType;
    }

    public void setEmailType(UserEmail.EmailType emailType) {
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