package namoo.nara.castle.domain.event.global;

import namoo.nara.share.event.type.Event;
import namoo.nara.share.event.type.NaraEvent;

public class CastellanFailEvent extends NaraEvent {
    //
    private String castleId;
    private String workerName;
    private Event sourceEvent;          // if exist

    public CastellanFailEvent(String castleId, String workerName) {
        //
        super(CastellanFailEvent.class.getName());
        this.castleId = castleId;
        this.workerName = workerName;
    }

    public String getCastleId() {
        return castleId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public Event getSourceEvent() {
        return sourceEvent;
    }

    public void setSourceEvent(Event sourceEvent) {
        this.sourceEvent = sourceEvent;
    }
}