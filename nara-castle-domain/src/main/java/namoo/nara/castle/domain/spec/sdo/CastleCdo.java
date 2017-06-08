package namoo.nara.castle.domain.spec.sdo;

import namoo.nara.share.domain.AbstractCdo;

public class CastleCdo extends AbstractCdo {
    //
    private String nationId;
    private String metroId;
    private String civilianId;
    private String email;

    public CastleCdo() {
        //
    }

    public CastleCdo(String nationId, String metroId, String civilianId, String email) {
        //
        this.nationId = nationId;
        this.metroId = metroId;
        this.civilianId = civilianId;
        this.email = email;
    }

    public String getNationId() {
        return nationId;
    }

    public void setNationId(String nationId) {
        this.nationId = nationId;
    }

    public String getMetroId() {
        return metroId;
    }

    public void setMetroId(String metroId) {
        this.metroId = metroId;
    }

    public String getCivilianId() {
        return civilianId;
    }

    public void setCivilianId(String civilianId) {
        this.civilianId = civilianId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
