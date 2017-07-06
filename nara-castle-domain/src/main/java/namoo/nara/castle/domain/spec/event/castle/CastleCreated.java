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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("castle:").append(castle);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String getName() {
        //
        return getClass().getName();
    }

    public Castle getCastle() {
        return castle;
    }

}
