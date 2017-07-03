package namoo.nara.castle.domain.spec.command.castle;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.share.domain.protocol.NaraCommand;

public class BuildCastleCommand implements NaraCommand {
    //
    private Castle castle;

    public BuildCastleCommand(Castle castle) {
        //
        this.castle = castle;
    }

    public Castle getCastle() {
        return castle;
    }
}
