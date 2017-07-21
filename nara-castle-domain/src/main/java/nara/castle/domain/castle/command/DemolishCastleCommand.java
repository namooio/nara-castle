package nara.castle.domain.castle.command;

import nara.share.domain.protocol.NaraCommand;

public class DemolishCastleCommand implements NaraCommand {
    //
    private String castellanId;

    public DemolishCastleCommand() {
        //
    }

    public DemolishCastleCommand(String castellanId) {
        //
        this.castellanId = castellanId;
    }

    public String getCastellanId() {
        return castellanId;
    }

    public void setCastellanId(String castellanId) {
        this.castellanId = castellanId;
    }
}
