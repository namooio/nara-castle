package namoo.nara.castle.da.jpa.jpo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 17..
 */
@Embeddable
public class CastellanEmailId implements Serializable {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String castellanOid;

    public CastellanEmailId() {
    }

    public CastellanEmailId(String email, String castellanOid) {
        this.email = email;
        this.castellanOid = castellanOid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCastellanOid() {
        return castellanOid;
    }

    public void setCastellanOid(String castellanOid) {
        this.castellanOid = castellanOid;
    }
}
