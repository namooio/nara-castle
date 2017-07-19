package nara.castle.domain.castle.event;

import nara.castle.domain.castle.entity.Castle;
import nara.share.domain.event.NaraEvent;

public class CastleBuilt implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private Castle castle;

    public CastleBuilt(Castle castle) {
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

    public Castle getCastle() {
        return castle;
    }

}

