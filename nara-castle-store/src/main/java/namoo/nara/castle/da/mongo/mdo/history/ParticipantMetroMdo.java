package namoo.nara.castle.da.mongo.mdo.history;

import namoo.nara.castle.domain.entity.history.ParticipantMetro;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class ParticipantMetroMdo {
    //
    private String metroId;
    private String metroName;
    private long joinTime;
    private long withdrawalTime;
    private String remarks;

    public ParticipantMetroMdo() {
        //
    }

    public static ParticipantMetroMdo newInstance(ParticipantMetro participantMetro) {
        //
        ParticipantMetroMdo participantMetroMdo = new ParticipantMetroMdo();
        participantMetroMdo.setMetroId(participantMetro.getMetroId());
        participantMetroMdo.setMetroName(participantMetro.getMetroName());
        participantMetroMdo.setJoinTime(participantMetro.getJoinTime());
        participantMetroMdo.setWithdrawalTime(participantMetro.getWithdrawalTime());
        participantMetroMdo.setRemarks(participantMetro.getRemarks());
        return participantMetroMdo;
    }

    public ParticipantMetro getDomain() {
        //
        ParticipantMetro participantMetro = new ParticipantMetro();
        participantMetro.setMetroId(metroId);
        participantMetro.setMetroName(metroName);
        participantMetro.setJoinTime(joinTime);
        participantMetro.setWithdrawalTime(withdrawalTime);
        participantMetro.setRemarks(remarks);
        return participantMetro;
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
