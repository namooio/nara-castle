package nara.castle.domain.castlequery.query;

import nara.share.domain.protocol.NaraQuery;

public class FindIdentityPlateQuery implements NaraQuery {
    //
    private String castellanId;

    public FindIdentityPlateQuery(String castellanId) {
        //
        this.castellanId = castellanId;
    }

    public String getCastellanId() {
        return castellanId;
    }
}
