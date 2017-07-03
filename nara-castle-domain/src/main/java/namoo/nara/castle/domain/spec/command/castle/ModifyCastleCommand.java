package namoo.nara.castle.domain.spec.command.castle;

import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.domain.protocol.NaraCommand;

public class ModifyCastleCommand implements NaraCommand {
    //
    private String castleId;
    private NameValueList nameValues;

    public ModifyCastleCommand(String castleId, NameValueList nameValues) {
        //
        this.castleId = castleId;
        this.nameValues = nameValues;
    }

    public String getCastleId() {
        return castleId;
    }

    public NameValueList getNameValues() {
        return nameValues;
    }
}
