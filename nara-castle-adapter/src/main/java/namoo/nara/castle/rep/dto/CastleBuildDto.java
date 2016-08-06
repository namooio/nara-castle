package namoo.nara.castle.rep.dto;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class CastleBuildDto implements Serializable {
    //
    private String name;
    private String email;
    private Locale locale;

    public CastleBuildDto() {
        //
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
