package namoo.nara.castle.domain.event;

import namoo.nara.share.event.type.Event;
import namoo.nara.share.event.type.NaraEvent;

public class CastellanFail extends NaraEvent {
    //
    private String castleId;
    private String workerName;
    private Event sourceEvent;          // if exist

    public CastellanFail(String castleId, String workerName) {
        //
        super(CastellanFail.class.getName());
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