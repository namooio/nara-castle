package namoo.nara.castle.da.jpa.jpo;

import namoo.nara.castle.domain.entity.Castellan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 16..
 */
@Entity(name = "NARA_CASTELLAN")
public class CastellanJpo {

    /** Same with gateway login user id, castellan oid */
    @Id
    private String oid;

    @Column
    private String displayName;

    public CastellanJpo() {
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public static CastellanJpo create(Castellan castellan) {
        if (castellan == null) return null;
        CastellanJpo castellanJpo = new CastellanJpo();
        castellanJpo.setOid(castellan.getOid());
        castellanJpo.setDisplayName(castellan.getDisplayName());
        return castellanJpo;
    }

    public Castellan toDomain() {
        Castellan castellan = new Castellan(this.oid);
        castellan.setDisplayName(this.displayName);
        return castellan;
    }
}
