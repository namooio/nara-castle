package nara.castle.domain.castle.event;

import nara.castle.domain.castle.entity.Castellan;
import nara.share.domain.event.NaraEvent;

public class CastellanCreated implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private Castellan castellan;

    public CastellanCreated(Castellan castellan) {
        //
        this.castellan = castellan;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("castellan:").append(castellan);
        sb.append('}');
        return sb.toString();
    }

    public Castellan getCastellan() {
        return castellan;
    }
}
