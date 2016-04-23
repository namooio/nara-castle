package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.util.Identifiable;

import java.util.Locale;

/**
 * Information repository for an individual </br>
 * Castle.usid is specified in Bureau service.
 */
public class Castle implements Identifiable {
    //
    private String usid;
    private String name;                // Castellan's display name
    private Locale locale;
    private OpenState state;
    private long buildTime;

    private Castellan owner;
    private InfoBundleBox infoBundleBox;

    public Castle() {
        //
    }

    protected Castle(String usid, String name, Locale locale) {
        //
        this.usid = usid;
        this.name = name;
        this.locale = locale;
        this.state = OpenState.Ready;
        this.buildTime = System.currentTimeMillis();
        this.infoBundleBox = new InfoBundleBox(usid);
    }

    public static Castle newInstance(String usid, String name, String email, Locale locale) {
        //
        Castle castle = new Castle(usid, name, locale);
        Castellan castellan = new Castellan(usid, name, email);
        castle.setOwner(castellan);

        return castle;
    }

    @Override
    public String getId() {
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

    public OpenState getState() {
        return state;
    }

    public void setState(OpenState state) {
        this.state = state;
    }

    public Castellan getOwner() {
        return owner;
    }

    public void setOwner(Castellan owner) {
        this.owner = owner;
    }


    public InfoBundleBox getInfoBundleBox() {
        return infoBundleBox;
    }

    public void setInfoBundleBox(InfoBundleBox infoBundleBox) {
        this.infoBundleBox = infoBundleBox;
    }
}