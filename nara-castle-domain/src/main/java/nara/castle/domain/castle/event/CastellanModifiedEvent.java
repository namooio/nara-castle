package nara.castle.domain.castle.event;

import nara.share.domain.NameValueList;
import nara.share.domain.event.NaraEvent;

public class CastellanModifiedEvent implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private String castellanId;
    private NameValueList nameValues;

    public CastellanModifiedEvent(String castellanId, NameValueList nameValues) {
        //
        this.castellanId = castellanId;
        this.nameValues = nameValues;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("castellanId:'").append(castellanId).append('\'');
        sb.append(", nameValues:").append(nameValues);
        sb.append('}');
        return sb.toString();
    }

    public String getCastellanId() {
        return castellanId;
    }

    public NameValueList getNameValues() {
        return nameValues;
    }
}
