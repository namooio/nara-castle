package namoo.nara.castle.protocol.sdo;

import java.io.Serializable;

public class JoinedMetroSdo implements Serializable {
    //
    private String metroId;
    private String citizenId;

    private long joinedTime;

    public JoinedMetroSdo() {

    }

    public JoinedMetroSdo(String metroId, String citizenId) {
        //
        this.metroId = metroId;
        this.citizenId = citizenId;
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

    public long getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(long joinedTime) {
        this.joinedTime = joinedTime;
    }
}
