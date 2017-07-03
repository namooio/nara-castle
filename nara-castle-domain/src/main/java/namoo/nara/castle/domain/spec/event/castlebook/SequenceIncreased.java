package namoo.nara.castle.domain.spec.event.castlebook;

import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.share.domain.event.NaraEvent;

public class SequenceIncreased implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private CastleBook castleBook;

    public SequenceIncreased(CastleBook castleBook) {
        //
        this.castleBook = castleBook;
    }

    public CastleBook getCastleBook() {
        return castleBook;
    }
}
