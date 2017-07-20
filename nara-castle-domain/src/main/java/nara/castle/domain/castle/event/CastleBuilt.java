package nara.castle.domain.castle.event;

import nara.castle.domain.castle.entity.Castellan;
import nara.share.domain.event.NaraEvent;

public class CastleBuilt implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private Castellan castellan;

    public CastleBuilt(Castellan castellan) {
        //
        this.castellan = castellan;
    }

    public Castellan getCastellan() {
        return castellan;
    }

}