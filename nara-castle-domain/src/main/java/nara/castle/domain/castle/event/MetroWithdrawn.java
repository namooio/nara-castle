package nara.castle.domain.castle.event;

import nara.share.domain.event.NaraEvent;

public class MetroWithdrawn implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private String castellanId;
    private String metroId;
    private String civilianId;

    public MetroWithdrawn(String castellanId, String metroId, String civilianId) {
        //
        this.castellanId = castellanId;
        this.metroId = metroId;
        this.civilianId = civilianId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MetroWithdrawn{");
        sb.append("castellanId='").append(castellanId).append('\'');
        sb.append(", metroId='").append(metroId).append('\'');
        sb.append(", civilianId='").append(civilianId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getCastellanId() {
        return castellanId;
    }

    public String getMetroId() {
        return metroId;
    }

    public String getCivilianId() {
        return civilianId;
    }
}