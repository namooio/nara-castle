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
        return "ModifyCastellanCommand{" +
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
