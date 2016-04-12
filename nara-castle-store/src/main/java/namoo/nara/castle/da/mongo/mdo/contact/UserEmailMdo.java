package namoo.nara.castle.da.mongo.mdo.contact;

import namoo.nara.castle.domain.entity.contact.UserEmail;

public class UserEmailMdo {
    //
    private String email;
    private String emailType;
    private boolean verified;
    private long verifiedTime;

    public UserEmailMdo() {
        //
    }

    public static UserEmailMdo newInstance(UserEmail userEmail) {
        //
        UserEmailMdo userEmailMdo = new UserEmailMdo();
        userEmailMdo.setEmail(userEmail.getEmail());
        userEmailMdo.setEmailType(userEmail.getEmailType().name());
        userEmailMdo.setVerified(userEmail.isVerified());
        userEmailMdo.setVerifiedTime(userEmail.getVerifiedTime());
        return userEmailMdo;
    }

    public UserEmail toDomain() {
        //
        UserEmail userEmail = new UserEmail();
        userEmail.setEmail(email);
        userEmail.setEmailType(UserEmail.EmailType.valueOf(emailType));
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