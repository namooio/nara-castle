package namoo.nara.castle.da.jpa.jpa.jpo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 17..
 */
@Embeddable
public class CastellanEmailPk implements Serializable {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String castellanId;

    public CastellanEmailPk() {
    }

    public CastellanEmailPk(String email, String castellanId) {
        this.email = email;
        this.castellanId = castellanId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCastellanId() {
        return castellanId;
    }

    public void setCastellanId(String castellanId) {
        this.castellanId = castellanId;
    }
}
