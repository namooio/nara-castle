package namoo.nara.castle.domain.context;

import namoo.nara.share.domain.util.numeral36.Numeral36;

public class CastleIdBuilder {
    //
    private Numeral36 numeral36;

    public CastleIdBuilder() {
        this.numeral36 = Numeral36.getInstance();
    }

    public String makeCastleId(long castleSequence) {
        //
        return numeral36.getStr36(castleSequence);
    }
}
