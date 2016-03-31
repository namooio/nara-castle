package namoo.nara.castle.domain.entity;

/**
 * Castellan email. Create after email verification...
 * All castellan emails are verified.
 *
 * Created by kchuh@nextree.co.kr on 2016. 1. 29..
 */
public class CastellanEmail {

    /**
     * Verified email must be unique...
     */
    private String email;

    private long createTime;

    private boolean primary;

    private boolean verified;

    private long verifiedTime;

    public CastellanEmail() {
        this.createTime = System.currentTimeMillis();
    }

    public CastellanEmail(String email) {
        this();
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primaryEmail) {
        this.primary = primaryEmail;
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
