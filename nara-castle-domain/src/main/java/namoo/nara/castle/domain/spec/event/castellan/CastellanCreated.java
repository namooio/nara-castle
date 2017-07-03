package namoo.nara.castle.domain.spec.event.castellan;

import namoo.nara.castle.domain.entity.Castellan;
import namoo.nara.share.domain.event.NaraEvent;

public class CastellanCreated implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private Castellan castellan;

    public CastellanCreated(Castellan castellan) {
        //
        this.castellan = castellan;
    }

    public Castellan getCastellan() {
        return castellan;
    }
}
