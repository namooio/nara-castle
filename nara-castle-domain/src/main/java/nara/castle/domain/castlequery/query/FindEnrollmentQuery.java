package nara.castle.domain.castlequery.query;

import nara.share.domain.protocol.NaraQuery;

public class FindEnrollmentQuery implements NaraQuery {
    //
    private String castellanId;

    public FindEnrollmentQuery(String castellanId) {
        //
        this.castellanId = castellanId;
    }

    public String getCastellanId() {
        return castellanId;
    }
}