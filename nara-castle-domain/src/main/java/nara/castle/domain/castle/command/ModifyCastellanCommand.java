package nara.castle.domain.castle.command;

import nara.share.domain.NameValueList;
import nara.share.domain.protocol.NaraCommand;

public class ModifyCastellanCommand implements NaraCommand {
    //
    private NameValueList nameValues;

    public ModifyCastellanCommand() {
        //
    }

    public ModifyCastellanCommand(NameValueList nameValues) {
        //
        this.nameValues = nameValues;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("nameValues:").append(nameValues);
        sb.append('}');
        return sb.toString();
    }

    public NameValueList getNameValues() {
        return nameValues;
    }

    public void setNameValues(NameValueList nameValues) {
        this.nameValues = nameValues;
    }
}
