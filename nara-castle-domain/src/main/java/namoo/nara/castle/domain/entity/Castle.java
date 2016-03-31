package namoo.nara.castle.domain.entity;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 1..
 */
public class Castle {

    /** Same with owner oid */
    private String oid;

    private String name;

    private long buildTime;

    private String localeCode;

    private CastleStatus status;

    private Castellan owner;

    public Castle() {
        this.buildTime = System.currentTimeMillis();
    }

    public Castle(String oid, CastleStatus status) {
        this();
        this.oid = oid;
        this.status = status;
    }

    public Castle(String oid) {
        this();
        this.oid = oid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
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

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public CastleStatus getStatus() {
        return status;
    }

    public void setStatus(CastleStatus status) {
        this.status = status;
    }

    public Castellan getOwner() {
        return owner;
    }

    public void setOwner(Castellan owner) {
        this.owner = owner;
    }
}
