package namoo.nara.castle.domain.spec.command.castellan;

import namoo.nara.share.domain.protocol.NaraCommand;

public class RegisterCastellanCommand implements NaraCommand {
    //
    private String castleId;

    public RegisterCastellanCommand(String castleId) {
        //
        this.castleId = castleId;
    }

    public String getCastleId() {
        return castleId;
    }
}
