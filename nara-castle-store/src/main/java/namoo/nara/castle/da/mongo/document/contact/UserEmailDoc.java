package namoo.nara.castle.da.mongo.document.contact;

public class UserEmailDoc {
    //
    private String email;
    private String emailType;
    private boolean verified;
    private long verifiedTime;

    public UserEmailDoc() {
        //
    }

    public static UserEmailDoc newInstance(UserEmail userEmail) {
        //
        UserEmailDoc userEmailDoc = new UserEmailDoc();
        userEmailDoc.setEmail(userEmail.getEmail());
        UserEmail.EmailType emailType = userEmail.getEmailType();
        if (emailType != null) {
            userEmailDoc.setEmailType(userEmail.getEmailType().name());
        }
        userEmailDoc.setVerified(userEmail.isVerified());
        userEmailDoc.setVerifiedTime(userEmail.getVerifiedTime());
        return userEmailDoc;
    }

    public UserEmail toDomain() {
        //
        UserEmail userEmail = new UserEmail();
        userEmail.setEmail(email);
        if (emailType != null) {
            userEmail.setEmailType(UserEmail.EmailType.valueOf(emailType));
        }
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