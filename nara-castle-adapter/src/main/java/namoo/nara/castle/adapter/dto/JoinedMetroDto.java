package namoo.nara.castle.adapter.dto;

import java.io.Serializable;

public class JoinedMetroDto implements Serializable {
    //
    private String metroId;
    private String citizenId;

    public JoinedMetroDto() {

    }

    public JoinedMetroDto(String metroId, String citizenId) {
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
}
