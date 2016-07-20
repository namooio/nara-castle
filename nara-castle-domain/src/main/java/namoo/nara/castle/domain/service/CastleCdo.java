package namoo.nara.castle.domain.service;

import java.util.Locale;

/**
 * Created by kchuh@nextree.co.kr on 2016. 5. 17..
 */
public class CastleCdo {
    //
    private String id;
    private String name;
    private String email;
    private Locale locale;

    public CastleCdo() {
        //
    }

    public CastleCdo(String id, String name, String email, Locale locale) {
        //
        this.id = id;
        this.name = name;
        this.email = email;
        this.locale = locale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
