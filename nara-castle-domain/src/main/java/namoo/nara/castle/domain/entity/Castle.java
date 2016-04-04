package namoo.nara.castle.domain.entity;

import namoo.nara.castle.domain.entity.history.ParticipantMetro;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Information repository for a individual </br>
 * Castle.usid is specified in Bureau service.
 */
public class Castle {
    //
    private String usid;
    private String name;                // Castellan's display name
    private Locale locale;
    private CastleState status;
    private long buildTime;

    private Castellan owner;
    private List<ParticipantMetro> participantMetroList = new ArrayList<>();

    public Castle() {
        //
    }

    protected Castle(String usid, String name, Locale locale) {
        //
        this.usid = usid;
        this.name = name;
        this.locale = locale;
        this.status = CastleState.Ready;
        this.buildTime = System.currentTimeMillis();
    }

    public static Castle newInstance(String usid, String name, Locale locale) {
        //
        return new Castle(usid, name, locale);
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
         this.usid = usid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(long buildTime) {
        this.buildTime = buildTime;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public CastleState getStatus() {
        return status;
    }

    public void setStatus(CastleState status) {
        this.status = status;
    }

    public Castellan getOwner() {
        return owner;
    }

    public void setOwner(Castellan owner) {
        this.owner = owner;
    }
}