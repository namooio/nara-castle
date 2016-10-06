package namoo.nara.castle.adapter.dto;

import java.io.Serializable;
import java.util.Locale;

public class CastleFindDto implements Serializable {
    //
    private String id;
    private Locale locale;
    private long builtTime; // UTC 0

    public CastleFindDto() {
        //
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public long getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(long builtTime) {
        this.builtTime = builtTime;
    }
}
