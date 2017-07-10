package namoo.nara.castle.domain.spec.command.castellan;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.share.domain.protocol.NaraCommand;

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
