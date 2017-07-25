package nara.castle.domain.castlequery.query;

import nara.share.domain.protocol.NaraQuery;

public class EnrolledCheckQuery implements NaraQuery {
    //
    private String castellanId;
    private String metroId;

    public EnrolledCheckQuery() {
        //
    }

    public EnrolledCheckQuery(String castellanId, String metroId) {
        //
        this.castellanId = castellanId;
        this.metroId = metroId;
    }

    @Override
    public String toString() {
        return "EnrolledCheckQuery{" +
                "castellanId='" + castellanId + '\'' +
                ", metroId='" + metroId + '\'' +
                '}';
    }

    public String getCastellanId() {
        return castellanId;
    }

    public void setCastellanId(String castellanId) {
        this.castellanId = castellanId;
    }

    public String getMetroId() {
        return metroId;
    }

    public void setMetroId(String metroId) {
        this.metroId = metroId;
    }
}