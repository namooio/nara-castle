package namoo.nara.castle.da.jpa.jpa.jpo;

import namoo.nara.castle.domain.entity.Castellan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 16..
 */
@Entity(name = "NARA_CASTELLAN")
public class CastellanJpo {

    @Id
    private String id;

    @Column
    private String displayName;

    public CastellanJpo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        castellanJpo.setId(castellan.getOid());
        castellanJpo.setDisplayName(castellan.getDisplayName());
        return castellanJpo;
    }

    public Castellan toDomain() {
        Castellan castellan = new Castellan(this.id);
        castellan.setDisplayName(this.displayName);
        return castellan;
    }
}
