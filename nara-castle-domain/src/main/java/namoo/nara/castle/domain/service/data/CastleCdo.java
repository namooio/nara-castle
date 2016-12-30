package namoo.nara.castle.domain.service.data;

import java.util.Locale;

public class CastleCdo {
    //
    private String castellanEmail; // required
    private String originMetroId;  // required
    private Locale locale; // optional

    public CastleCdo() {
        //
    }

    public CastleCdo(String castellanEmail) {
        this.castellanEmail = castellanEmail;
    }

    public CastleCdo(String castellanEmail, String originMetroId, Locale locale) {
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
