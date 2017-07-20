package nara.castle.domain.castle.command;

import nara.share.domain.NameValueList;
import nara.share.domain.protocol.NaraCommand;

public class ModifyCastellanCommand implements NaraCommand {
    //
    private String castellanId;
    private NameValueList nameValues;

    public ModifyCastellanCommand() {
        //
    }

    public ModifyCastellanCommand(String castellanId, NameValueList nameValues) {
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
