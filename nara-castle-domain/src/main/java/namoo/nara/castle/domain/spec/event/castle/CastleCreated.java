package namoo.nara.castle.domain.spec.event.castle;

import namoo.nara.castle.domain.entity.Castle;
import namoo.nara.share.domain.event.NaraEvent;

public class CastleCreated implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private Castle castle;

    public CastleCreated(Castle castle) {
        //
        this.castle = castle;
    }

    public Castle getCastle() {
        return castle;
    }

}
