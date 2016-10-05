package namoo.nara.castle.domain.context;

public class CastleContext {

    private static CastleIdBuilder castleIdBuilder;

    public static CastleIdBuilder getCastleIdBuilder() {
        //
        if (castleIdBuilder == null) {
            castleIdBuilder = new CastleIdBuilder();
        }

        return castleIdBuilder;
    }

}
