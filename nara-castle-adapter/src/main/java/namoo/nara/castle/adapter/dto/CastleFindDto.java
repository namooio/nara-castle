package namoo.nara.castle.adapter.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Locale;

public class CastleFindDto implements Serializable {
    //
    private Locale locale;
    private ZonedDateTime builtTime;

    public CastleFindDto() {
        //
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public ZonedDateTime getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(ZonedDateTime builtTime) {
        this.builtTime = builtTime;
    }
}
