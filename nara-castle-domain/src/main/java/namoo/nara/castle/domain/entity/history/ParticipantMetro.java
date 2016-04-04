package namoo.nara.castle.domain.entity.history;

public class ParticipantMetro {
    //
    private String metroId;
    private String metroName;
    private long joinTime;
    private long withdrawalTime;
    private String remarks;

    public ParticipantMetro() {
        //
    }

    protected ParticipantMetro(String metroId, String metroName, long joinTime) {
        //
        this.metroId = metroId;
        this.metroName = metroName;
        this.joinTime = joinTime;
        this.withdrawalTime = 0L;
    }

    public static ParticipantMetro newInstance(String metroId, String metroName, long joinTime) {
        //
        return new ParticipantMetro(metroId, metroName, joinTime);
    }

    public String getMetroId() {
        return metroId;
    }

    public void setMetroId(String metroId) {
        this.metroId = metroId;
    }

    public String getMetroName() {
        return metroName;
    }

    public void setMetroName(String metroName) {
        this.metroName = metroName;
    }

    public long getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(long joinTime) {
        this.joinTime = joinTime;
    }

    public long getWithdrawalTime() {
        return withdrawalTime;
    }

    public void setWithdrawalTime(long withdrawalTime) {
        this.withdrawalTime = withdrawalTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}