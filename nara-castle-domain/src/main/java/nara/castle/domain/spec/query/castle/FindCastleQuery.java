package nara.castle.domain.spec.query.castle;

import namoo.nara.share.domain.protocol.NaraQuery;

public class FindCastleQuery implements NaraQuery {
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
