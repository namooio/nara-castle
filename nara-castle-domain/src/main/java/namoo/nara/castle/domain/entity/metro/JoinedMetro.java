package namoo.nara.castle.domain.entity.metro;

import namoo.nara.share.domain.ValueObject;

import java.time.ZonedDateTime;

public class JoinedMetro implements ValueObject {
    //
    private String metroId;
    private String citizenId;

    private ZonedDateTime joinedTime;

    public JoinedMetro() {

    }

    public String getMetroId() {
        return metroId;
    }

    public void setMetroId(String metroId) {
        this.metroId = metroId;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public ZonedDateTime getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(ZonedDateTime joinedTime) {
        this.joinedTime = joinedTime;
    }

    @Override
    public String toString() {
        return "JoinedMetro{" +
                "metroId='" + metroId + '\'' +
                ", citizenId='" + citizenId + '\'' +
                ", joinedTime=" + joinedTime +
                '}';
    }
}
