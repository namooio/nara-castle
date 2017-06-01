package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.ValueObject;

public class JoinedMetro implements ValueObject {

    private String metroId;
    private String civilianId;

    private Long joinedTime;

    public JoinedMetro() {

    }

    public JoinedMetro(String metroId, String civilianId) {
        this.metroId = metroId;
        this.civilianId = civilianId;
        this.joinedTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("metroId:'").append(metroId).append('\'');
        sb.append(", civilianId:'").append(civilianId).append('\'');
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

    public String getCivilianId() {
        return civilianId;
    }

    public void setCivilianId(String civilianId) {
        this.civilianId = civilianId;
    }

    public Long getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(Long joinedTime) {
        this.joinedTime = joinedTime;
    }

}
