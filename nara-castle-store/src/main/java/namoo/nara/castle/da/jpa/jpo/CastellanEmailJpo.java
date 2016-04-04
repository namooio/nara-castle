package namoo.nara.castle.da.jpa.jpo;

import namoo.nara.castle.domain.entity.contact.UserEmail;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 16..
 */
@Entity(name = "NARA_CASTELLAN_EMAIL")
public class CastellanEmailJpo {

    @EmbeddedId
    private CastellanEmailPk castellanEmailPk;

    @Column(nullable = false)
    private long createTime;

    @Column(nullable = false)
    private boolean primaryEmail;

    @Column
    private boolean verified;

    @Column
    private long verifiedTime;

    public CastellanEmailJpo() {
    }

    public CastellanEmailPk getCastellanEmailPk() {
        return castellanEmailPk;
    }

    public void setCastellanEmailPk(CastellanEmailPk castellanEmailPk) {
        this.castellanEmailPk = castellanEmailPk;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(boolean primaryEmail) {
        this.primaryEmail = primaryEmail;
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

    public static CastellanEmailJpo create(UserEmail castellanEmail, String castellanOid) {
        if (castellanEmail == null) return null;
        CastellanEmailJpo castellanEmailJpo = new CastellanEmailJpo();
        castellanEmailJpo.setCastellanEmailPk(new CastellanEmailPk(castellanEmail.getEmail(), castellanOid));
        castellanEmailJpo.setCreateTime(castellanEmail.getCreateTime());
        castellanEmailJpo.setPrimaryEmail(castellanEmail.isPrimary());
        castellanEmailJpo.setVerified(castellanEmail.isVerified());
        castellanEmailJpo.setVerifiedTime(castellanEmail.getVerifiedTime());
        return castellanEmailJpo;
    }

    public UserEmail toDomain() {
        UserEmail castellanEmail = new UserEmail();
        castellanEmail.setCreateTime(this.createTime);
        castellanEmail.setEmail(this.castellanEmailPk.getEmail());
        castellanEmail.setPrimary(this.primaryEmail);
        castellanEmail.setVerified(this.verified);
        castellanEmail.setVerifiedTime(this.verifiedTime);
        return castellanEmail;
    }
}
