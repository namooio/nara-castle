package namoo.nara.castle.domain.spec.event.castellan;

import namoo.nara.share.domain.event.NaraEvent;

public class CastellanCreated implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private String castellanId;

    public CastellanCreated(String castellanId) {
        //
        this.castellanId = castellanId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("castellanId:'").append(castellanId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getCastellanId() {
        return castellanId;
    }
}
