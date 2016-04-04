package namoo.nara.castle.da.jpa.jpo;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.castle.domain.entity.CastleState;

import javax.persistence.*;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
@Entity(name = "NARA_CASTLE")
public class CastleJpo {

    /** Same with gateway login user id, castellan id */
    @Id
    private String id;

    @Column
    private String name;

    @Column(nullable = false)
    private long buildTime;

    @Column
    private String localeCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CastleState status;

    public CastleJpo() {
        this.buildTime = System.currentTimeMillis();
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

    public CastleState getStatus() {
        return status;
    }

    public void setStatus(CastleState status) {
        this.status = status;
    }

    public static CastleJpo create(Castle castle) {
        if (castle == null) return null;
        CastleJpo castleJpo = new CastleJpo();
        castleJpo.setId(castle.getOid());
        castleJpo.setName(castle.getName());
        castleJpo.setBuildTime(castle.getBuildTime());
        castleJpo.setLocaleCode(castle.getLocaleCode());
        castleJpo.setStatus(castle.getStatus());
        return castleJpo;
    }

    public Castle toDomain() {
        Castle castle = new Castle(this.id);
        castle.setName(this.name);
        castle.setBuildTime(this.buildTime);
        castle.setLocaleCode(this.localeCode);
        castle.setStatus(this.status);
        return castle;
    }
}
