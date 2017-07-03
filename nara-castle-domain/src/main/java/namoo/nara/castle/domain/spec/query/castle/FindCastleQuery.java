package namoo.nara.castle.domain.spec.query.castle;

import namoo.nara.share.domain.protocol.NaraCommand;

public class FindCastleQuery implements NaraCommand {
    //
    private String castleId;

    public FindCastleQuery(String castleId) {
        //
        this.castleId = castleId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("castleId:'").append(castleId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getCastleId() {
        return castleId;
    }
}
