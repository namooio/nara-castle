package namoo.nara.castle.adapter.dto.history;

import namoo.nara.castle.domain.entity.history.ParticipantMetro;

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

    public static ParticipantMetroDto newInstance(ParticipantMetro participantMetro) {
        //
        ParticipantMetroDto participantMetroDto = new ParticipantMetroDto();
        participantMetroDto.setMetroId(participantMetro.getMetroId());
        participantMetroDto.setMetroName(participantMetro.getMetroName());
        participantMetroDto.setJoinTime(participantMetro.getJoinTime());
        participantMetroDto.setWithdrawalTime(participantMetro.getWithdrawalTime());
        participantMetroDto.setRemarks(participantMetro.getRemarks());
        return participantMetroDto;
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
