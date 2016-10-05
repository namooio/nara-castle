package namoo.nara.castle.domain.service.data;

import java.util.Locale;

public class CastleCdo {
    //
    private Locale locale;

    private String castellanName;
    private String castellanEmail;

    public CastleCdo(Locale locale, String castellanName, String castellanEmail) {
        //
        this.locale = locale;
        this.castellanName = castellanName;
        this.castellanEmail = castellanEmail;
    }

    public String getCastellanName() {
        return castellanName;
    }

    public void setCastellanName(String castellanName) {
        this.castellanName = castellanName;
    }

    public String getCastellanEmail() {
        return castellanEmail;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setCastellanEmail(String castellanEmail) {
        this.castellanEmail = castellanEmail;
    }

}
