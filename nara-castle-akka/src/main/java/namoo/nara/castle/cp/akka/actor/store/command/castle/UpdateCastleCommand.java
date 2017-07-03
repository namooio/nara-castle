package namoo.nara.castle.cp.akka.actor.store.command.castle;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.share.domain.protocol.NaraCommand;

public class UpdateCastleCommand implements NaraCommand {
    //
    private Castle castle;

    public UpdateCastleCommand(Castle castle) {
        //
        this.castle = castle;
    }

    public Castle getCastle() {
        return castle;
    }
}
