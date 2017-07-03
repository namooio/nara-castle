package namoo.nara.castle.cp.akka.actor.store.command.castlebook;

import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.share.domain.protocol.NaraCommand;

public class UpdateCastleBookCommand implements NaraCommand {
    //
    private CastleBook castleBook;

    public UpdateCastleBookCommand(CastleBook castleBook) {
        //
        this.castleBook = castleBook;
    }

    public CastleBook getCastleBook() {
        return castleBook;
    }
}
