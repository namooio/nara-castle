package namoo.nara.castle.domain.context;

public class CastleContext {
    //
    private static CastleIdBuilder castleIdBuilder = new CastleIdBuilder();

    public static CastleIdBuilder getCastleIdBuilder() {
        return castleIdBuilder;
    }
}