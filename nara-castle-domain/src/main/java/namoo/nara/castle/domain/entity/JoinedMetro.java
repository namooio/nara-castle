package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.ValueObject;

public class JoinedMetro implements ValueObject {
    //
    private String metroId;
    private String citizenId;

    private Long joinedTime;

    public JoinedMetro() {
        joinedTime = System.currentTimeMillis();
    }

    public JoinedMetro(String metroId, String citizenId) {
        this();
        this.metroId = metroId;
        this.citizenId = citizenId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("metroId:'").append(metroId).append('\'');
        sb.append(", citizenId:'").append(citizenId).append('\'');
        sb.append(", joinedTime:").append(joinedTime);
        sb.append('}');
        return sb.toString();
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

    public Long getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(Long joinedTime) {
        this.joinedTime = joinedTime;
    }

}
