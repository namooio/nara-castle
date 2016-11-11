package namoo.nara.castle.protocol.sdo;

public class JoinedMetroAddSdo {
    //
    private String metroId;
    private String citizenId;

    public JoinedMetroAddSdo() {
        //
    }

    public JoinedMetroAddSdo(String metroId, String citizenId) {
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
