package namoo.nara.castle.spec.sdo;

public class JoinedMetroCdo {
    //
    private String metroId;
    private String citizenId;

    public JoinedMetroCdo() {
        //
    }

    public JoinedMetroCdo(String metroId, String citizenId) {
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
