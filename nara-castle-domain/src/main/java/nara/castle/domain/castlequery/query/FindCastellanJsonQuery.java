package nara.castle.domain.castlequery.query;

import nara.share.domain.protocol.NaraQuery;

public class FindCastellanJsonQuery implements NaraQuery {
    //
    private String castellanId;

    public FindCastellanJsonQuery() {
        //
    }

    public FindCastellanJsonQuery(String castellanId) {
        //
        this.castellanId = castellanId;
    }

    @Override
    public String toString() {
        return "FindCastellanJsonQuery{" +
                "castellanId='" + castellanId + '\'' +
                '}';
    }

    public String getCastellanId() {
        return castellanId;
    }

    public void setCastellanId(String castellanId) {
        this.castellanId = castellanId;
    }
}