package namoo.nara.castle.domain.spec.sdo;

import namoo.nara.share.domain.Cdo;
import namoo.nara.share.domain.NameValueList;

public class CastleCdo extends Cdo {

    private String nationId;
    private String originMetroId;
    private String originCivilianId;

    public CastleCdo() {

    }

    public CastleCdo(String nationId, String originMetroId, String originCivilianId) {
        this.nationId = nationId;
        this.originMetroId = originMetroId;
        this.originCivilianId = originCivilianId;
    }

    public CastleCdo(String nationId, String originMetroId, String originCivilianId, NameValueList nameValues) {
        super(nameValues);
        this.nationId = nationId;
        this.originMetroId = originMetroId;
        this.originCivilianId = originCivilianId;
    }

    public String getNationId() {
        return nationId;
    }

    public void setNationId(String nationId) {
        this.nationId = nationId;
    }

    public String getOriginMetroId() {
        return originMetroId;
    }

    public void setOriginMetroId(String originMetroId) {
        this.originMetroId = originMetroId;
    }

    public String getOriginCivilianId() {
        return originCivilianId;
    }

    public void setOriginCivilianId(String originCivilianId) {
        this.originCivilianId = originCivilianId;
    }
}
