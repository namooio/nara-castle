package namoo.nara.castle.domain.context;

import namoo.nara.share.domain.util.numeral36.Numeral36;
import namoo.nara.share.domain.util.numeral36.Numeral36Util;

public class CastleIdBuilder {
    //
    private Numeral36 numeral36;

    public CastleIdBuilder() {
        //
        this.numeral36 = Numeral36.getInstance();
    }

    public String makeCastleId(long castleSequence) {
        //
        return String.format("%s", Numeral36Util.getStr36(castleSequence));
    }
}