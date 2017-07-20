package nara.castle.domain.castlequery.query;

import nara.share.domain.protocol.NaraQuery;

public class FindCastellanQuery implements NaraQuery {
    //
    private String castellanId;

    public FindCastellanQuery() {
        //
    }

    public FindCastellanQuery(String castellanId) {
        //
        this.castellanId = castellanId;
    }

    public String getCastellanId() {
        return castellanId;
    }

    public void setCastellanId(String castellanId) {
        this.castellanId = castellanId;
    }
}