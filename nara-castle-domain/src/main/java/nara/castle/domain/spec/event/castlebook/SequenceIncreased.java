package nara.castle.domain.spec.event.castlebook;

import nara.castle.domain.entity.CastleBook;
import nara.share.domain.event.NaraEvent;

public class SequenceIncreased implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private CastleBook castleBook;

    public SequenceIncreased(CastleBook castleBook) {
        //
        this.castleBook = castleBook;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("castleBook:").append(castleBook);
        sb.append('}');
        return sb.toString();
    }

    public CastleBook getCastleBook() {
        return castleBook;
    }
}
