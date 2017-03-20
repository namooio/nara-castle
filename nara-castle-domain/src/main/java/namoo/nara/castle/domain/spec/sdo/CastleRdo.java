package namoo.nara.castle.domain.spec.sdo;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.castle.domain.entity.Castle;

import java.io.Serializable;
import java.util.Locale;

public class CastleRdo implements Serializable {
    //
    private String id;

    private Castellan castellan;
    private Locale locale;
    private String builtTime;

    public CastleRdo() {
        //
    }

    public CastleRdo(Castle castle) {
        this.id = castle.getId();
        this.castellan = castle.getCastellan();
        this.locale = castle.getLocale();
        this.builtTime = castle.getBuiltTime().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Castellan getCastellan() {
        return castellan;
    }

    public void setCastellan(Castellan castellan) {
        this.castellan = castellan;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(String builtTime) {
        this.builtTime = builtTime;
    }
}
