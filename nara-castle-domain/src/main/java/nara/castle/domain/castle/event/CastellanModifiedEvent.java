package nara.castle.domain.castle.event;

import nara.share.domain.NameValueList;
import nara.share.domain.event.NaraEvent;

public class CastellanModifiedEvent implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private String castellanId;
    private NameValueList nameValues;

    public CastellanModifiedEvent() {
        //
    }

    public CastellanModifiedEvent(String castellanId, NameValueList nameValues) {
        //
        this.castellanId = castellanId;
        this.nameValues = nameValues;
    }

    @Override
    public String toString() {
        return "CastellanModifiedEvent{" +
                "castellanId='" + castellanId + '\'' +
                ", nameValues=" + nameValues +
                '}';
    }

    public String getCastellanId() {
        return castellanId;
    }

    public void setCastellanId(String castellanId) {
        this.castellanId = castellanId;
    }

    public NameValueList getNameValues() {
        return nameValues;
    }

    public void setNameValues(NameValueList nameValues) {
        this.nameValues = nameValues;
    }
}
