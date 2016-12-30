package namoo.nara.castle.protocol.sdo;

import java.io.Serializable;
import java.util.Locale;

public class CastleBuildSdo implements Serializable {
    //
    private String castellanEmail;
    private String originMetroId;
    private Locale locale;

    public CastleBuildSdo() {
        //
    }

    public CastleBuildSdo(String castellanEmail, String originMetroId, Locale locale) {
        //
        this.castellanEmail = castellanEmail;
        this.originMetroId = originMetroId;
        this.locale = locale;
    }

    public String getCastellanEmail() {
        return castellanEmail;
    }

    public void setCastellanEmail(String castellanEmail) {
        this.castellanEmail = castellanEmail;
    }

    public String getOriginMetroId() {
        return originMetroId;
    }

    public void setOriginMetroId(String originMetroId) {
        this.originMetroId = originMetroId;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
