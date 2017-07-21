package nara.castle.domain.castle.command;

import nara.share.domain.protocol.NaraCommand;

public class WithdrawMetroCommand implements NaraCommand {
    //
    private String metroId;
    private String civilianId;

    public WithdrawMetroCommand() {
        //
    }

    public WithdrawMetroCommand(String metroId, String civilianId) {
        //
        this.metroId = metroId;
        this.civilianId = civilianId;
    }

    public String getMetroId() {
        return metroId;
    }

    public void setMetroId(String metroId) {
        this.metroId = metroId;
    }

    public String getCivilianId() {
        return civilianId;
    }

    public void setCivilianId(String civilianId) {
        this.civilianId = civilianId;
    }
}