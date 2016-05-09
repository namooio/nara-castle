package namoo.nara.castle.front.dto.history;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 12..
 */
public class ParticipantMetroDto {
    //
    private String metroId;
    private String metroName;
    private long joinTime;
    private long withdrawalTime;
    private String remarks;

    public ParticipantMetroDto() {
        //
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
