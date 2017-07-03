package namoo.nara.castle.domain.context;

import namoo.nara.castle.domain.entity.CastleBook;
import namoo.nara.share.domain.util.numeral36.Numeral36;

public final class CastleIdBuilder {
    //
    private static Numeral36 numeral36 = Numeral36.getInstance();

    private CastleIdBuilder() {
        //
    }

    public static String makeCastleBookId() {
        //
        return CastleBook.class.getSimpleName();
    }

    public static String makeCastleId(long castleSequence) {
        //
        return String.format("%s", numeral36.getStr36(castleSequence));
    }
}