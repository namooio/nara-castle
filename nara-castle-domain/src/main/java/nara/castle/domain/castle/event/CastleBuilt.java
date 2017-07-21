package nara.castle.domain.castle.event;

import nara.castle.domain.castle.entity.Castellan;
import nara.share.domain.event.NaraEvent;

public class CastleBuilt implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private Castellan initialState;

    public CastleBuilt(Castellan initialState) {
        //
        this.initialState = initialState;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("initialState:").append(initialState);
        sb.append('}');
        return sb.toString();
    }

    public Castellan getInitialState() {
        return initialState;
    }
}