package namoo.nara.castle.domain.event.local;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.share.event.NaraEvent;

public class CastellanCreatedEvent implements NaraEvent {
    //
    private Castellan castellan;

    public CastellanCreatedEvent() {
        //
    }

    public CastellanCreatedEvent(Castellan castellan) {
        //
        this.castellan = castellan;
    }

    public Castellan getCastellan() {
        return castellan;
    }

    public void setCastellan(Castellan castellan) {
        this.castellan = castellan;
    }
}
