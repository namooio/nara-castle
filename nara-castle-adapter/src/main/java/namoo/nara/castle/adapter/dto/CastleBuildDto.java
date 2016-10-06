package namoo.nara.castle.adapter.dto;

import java.io.Serializable;
import java.util.Locale;

public class CastleBuildDto implements Serializable {
    //
    private Locale locale;
    private String castellanName;
    private String castellanEmail;

    public CastleBuildDto() {
        //
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
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

    public void setCastellanEmail(String castellanEmail) {
        this.castellanEmail = castellanEmail;
    }
}
