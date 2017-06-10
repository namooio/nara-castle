package namoo.nara.castle.domain.entity;

import namoo.nara.share.domain.ValueObject;
import namoo.nara.share.util.json.JsonUtil;

public class JoinedMetro implements ValueObject {
    //
    private String nationId;
    private String metroId;
    private String civilianId;

    private Long joinedTime;

    public JoinedMetro() {
        //
    }

    public JoinedMetro(String nationId, String metroId, String civilianId) {
        //
        this.nationId = nationId;
        this.metroId = metroId;
        this.civilianId = civilianId;
        this.joinedTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("nationId:'").append(nationId).append('\'');
        sb.append(", metroId:'").append(metroId).append('\'');
        sb.append(", civilianId:'").append(civilianId).append('\'');
        sb.append(", joinedTime:").append(joinedTime);
        sb.append('}');
        return sb.toString();
    }

    public static JoinedMetro getSample() {
        //
        String nationId = "P";
        String metroId = "POP";
        String civilianId = "12@POP";

        JoinedMetro sample = new JoinedMetro(nationId, metroId, civilianId);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public JoinedMetro fromJson(String json) {
        //
        return JsonUtil.fromJson(json, JoinedMetro.class);
    }

    public String getNationId() {
        return nationId;
    }

    public void setNationId(String nationId) {
        this.nationId = nationId;
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

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}