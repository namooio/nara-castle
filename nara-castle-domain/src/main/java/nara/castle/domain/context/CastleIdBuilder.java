package nara.castle.domain.context;

import nara.castle.domain.entity.CastleBook;
import nara.share.domain.util.numeral36.Numeral36;

public final class CastleIdBuilder {
    //
    private static Numeral36 numeral36 = Numeral36.getInstance();

    private CastleIdBuilder() {
        //
    }

    public static String requestCastleBookId() {
        //
        return CastleBook.class.getSimpleName();
    }

    public static String requestCastleId(long castleSequence) {
        //
        return String.format("%s", numeral36.getStr36(castleSequence));
    }
}