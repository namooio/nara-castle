package nara.castle.domain.castle.command;

import nara.share.domain.NameValueList;
import nara.share.domain.protocol.NaraCommand;

public class ModifyCastellanCommand implements NaraCommand {
    //
    private String castellanId;
    private NameValueList nameValues;

    public ModifyCastellanCommand(String castellanId, NameValueList nameValues) {
        //
        this.castellanId = castellanId;
        this.nameValues = nameValues;
    }

    public String getCastellanId() {
        return castellanId;
    }

    public NameValueList getNameValues() {
        return nameValues;
    }
}
