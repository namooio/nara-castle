package namoo.nara.castle.adapter.dto;

import java.io.Serializable;
import java.util.Locale;

public class CastleBuildDto implements Serializable {
    //
    private Locale locale;

    public CastleBuildDto() {
        //
    }

    public CastleBuildDto(Locale locale) {
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
