package namoo.nara.castle.protocol.sdo;

import java.io.Serializable;
import java.util.Locale;

public class CastleSdo implements Serializable {
    //
    private String id;

    private CastellanSdo castellanSdo;
    private Locale locale;
    private long builtTime; // UTC 0

    public CastleSdo() {
        //
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CastellanSdo getCastellanSdo() {
        return castellanSdo;
    }

    public void setCastellanSdo(CastellanSdo castellanSdo) {
        this.castellanSdo = castellanSdo;
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
