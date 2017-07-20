package nara.castle.domain.castle.command;

import nara.share.domain.protocol.NaraCommand;

public class WithdrawMetroCommand implements NaraCommand {
    //
    private String castellanId;
    private String metroId;
    private String civilianId;

    public WithdrawMetroCommand(String castellanId, String metroId, String civilianId) {
        //
        this.castellanId = castellanId;
        this.metroId = metroId;
        this.civilianId = civilianId;
    }

    public String getCastellanId() {
        return castellanId;
    }

    public String getMetroId() {
        return metroId;
    }

    public String getCivilianId() {
        return civilianId;
    }
}