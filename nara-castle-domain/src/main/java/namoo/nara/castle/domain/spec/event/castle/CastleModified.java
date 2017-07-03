package namoo.nara.castle.domain.spec.event.castle;

import namoo.nara.castle.domain.spec.command.castle.ModifyCastleCommand;
import namoo.nara.share.domain.NameValueList;
import namoo.nara.share.domain.event.NaraEvent;

public class CastleModified implements NaraEvent {
    //
    private static final long serialVersionUID = 1L;

    private String castleId;
    private NameValueList nameValues;

    public CastleModified(ModifyCastleCommand command) {
        //
        this.castleId = command.getCastleId();
        this.nameValues = command.getNameValues();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("castleId:'").append(castleId).append('\'');
        sb.append(", nameValues:").append(nameValues);
        sb.append('}');
        return sb.toString();
    }

    public String getCastleId() {
        return castleId;
    }

    public NameValueList getNameValues() {
        return nameValues;
    }
}
