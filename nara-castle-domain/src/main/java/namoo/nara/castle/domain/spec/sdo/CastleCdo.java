package namoo.nara.castle.domain.spec.sdo;

import namoo.nara.share.domain.AbstractCdo;
import namoo.nara.share.domain.NameValueList;

public class CastleCdo extends AbstractCdo {

    private String nationId;

    public CastleCdo() {

    }

    public CastleCdo(String nationId) {
        this.nationId = nationId;
    }

    public CastleCdo(String nationId, NameValueList nameValues) {
        super(nameValues);
        this.nationId = nationId;
    }

    public String getNationId() {
        return nationId;
    }

    public void setNationId(String nationId) {
        this.nationId = nationId;
    }

}
