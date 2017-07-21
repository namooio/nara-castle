package nara.castle.domain.castle.event;

import nara.share.domain.event.NaraEvent;

public class CastleDemolished implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private String castellanId;

    public CastleDemolished(String castellanId) {
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
