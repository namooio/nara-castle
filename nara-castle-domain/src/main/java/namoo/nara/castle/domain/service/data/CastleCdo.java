package namoo.nara.castle.domain.service.data;

import java.util.Locale;

public class CastleCdo {
    //
    private Locale locale; // optional

    public CastleCdo() {
        //
    }

    public CastleCdo(Locale locale) {
        //
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
