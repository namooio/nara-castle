package nara.castle.domain.castle.command;

import nara.share.domain.protocol.NaraCommand;

public class WithdrawMetroCommand implements NaraCommand {
    //
    private String castellanId;

    private String metroId;
    private String citizenId;

    public WithdrawMetroCommand() {
        //
    }

    public WithdrawMetroCommand(String castellanId, String metroId, String citizenId) {
        //
        this.castellanId = castellanId;
        this.metroId = metroId;
        this.citizenId = citizenId;
    }

    @Override
    public String toString() {
        return "WithdrawMetroCommand{" +
                "castellanId='" + castellanId + '\'' +
                ", metroId='" + metroId + '\'' +
                ", citizenId='" + citizenId + '\'' +
                '}';
    }

    public String getCastellanId() {
        return castellanId;
    }

    public void setCastellanId(String castellanId) {
        this.castellanId = castellanId;
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