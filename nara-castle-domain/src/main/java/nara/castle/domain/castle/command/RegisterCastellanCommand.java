package nara.castle.domain.castle.command;

import nara.castle.domain.castle.entity.Castle;
import nara.share.domain.protocol.NaraCommand;

public class RegisterCastellanCommand implements NaraCommand {
    //
    private Castle castle;

    public RegisterCastellanCommand() {
        //
    }

    public RegisterCastellanCommand(Castle castle) {
        //
        this.castle = castle;
    }

    public Castle getCastle() {
        return castle;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }
}
