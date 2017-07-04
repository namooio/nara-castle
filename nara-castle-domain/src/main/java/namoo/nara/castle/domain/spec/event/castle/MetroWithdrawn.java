package namoo.nara.castle.domain.spec.event.castle;

import namoo.nara.share.domain.event.NaraEvent;

public class MetroWithdrawn implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private String metroId;
    private String civilianId;

    public MetroWithdrawn(String metroId, String civilianId) {
        //
        this.metroId = metroId;
        this.civilianId = civilianId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("metroId:'").append(metroId).append('\'');
        sb.append(", civilianId:'").append(civilianId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getMetroId() {
        return metroId;
    }

    public String getCivilianId() {
        return civilianId;
    }
}
