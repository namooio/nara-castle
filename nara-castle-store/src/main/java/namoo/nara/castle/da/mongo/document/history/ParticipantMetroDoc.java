package namoo.nara.castle.da.mongo.document.history;

import namoo.nara.castle.domain.entity.history.ParticipantMetro;

/**
 * Created by kchuh@nextree.co.kr on 2016. 4. 7..
 */
public class ParticipantMetroDoc {
    //
    private String metroId;
    private String metroName;
    private long joinTime;
    private long withdrawalTime;
    private String remarks;

    public ParticipantMetroDoc() {
        //
    }

    public static ParticipantMetroDoc newInstance(ParticipantMetro participantMetro) {
        //
        ParticipantMetroDoc participantMetroDoc = new ParticipantMetroDoc();
        participantMetroDoc.setMetroId(participantMetro.getMetroId());
        participantMetroDoc.setMetroName(participantMetro.getMetroName());
        participantMetroDoc.setJoinTime(participantMetro.getJoinTime());
        participantMetroDoc.setWithdrawalTime(participantMetro.getWithdrawalTime());
        participantMetroDoc.setRemarks(participantMetro.getRemarks());
        return participantMetroDoc;
    }

    public ParticipantMetro toDomain() {
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
