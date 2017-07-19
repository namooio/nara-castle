package nara.castle.domain.castle.event;

import nara.castle.domain.castle.command.ModifyCastellanCommand;
import nara.share.domain.NameValueList;
import nara.share.domain.event.NaraEvent;

public class CastellanModified implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private String castellanId;
    private NameValueList nameValues;

    public CastellanModified(ModifyCastellanCommand command) {
        //
        this.castellanId = command.getCastellanId();
        this.nameValues = command.getNameValues();
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
