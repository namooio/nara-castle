package namoo.nara.castle.protocol.sdo;

import java.io.Serializable;
import java.util.Locale;

public class CastleBuildSdo implements Serializable {
    //
    private Locale locale;

    public CastleBuildSdo() {
        //
    }

    public CastleBuildSdo(Locale locale) {
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
