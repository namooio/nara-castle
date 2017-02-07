package namoo.nara.castle.domain.context;

public class CastleContext {

    private static CastleIdBuilder castleIdBuilder;
    private static CastleEmailValidator emailValidator;

    public static CastleIdBuilder getCastleIdBuilder() {
        //
        if (castleIdBuilder == null) {
            castleIdBuilder = new CastleIdBuilder();
        }

        return castleIdBuilder;
    }

    public static CastleEmailValidator getEmailValidator() {
        //
        if (emailValidator == null) {
            emailValidator = new CastleEmailValidator();
        }

        return emailValidator;
    }

}
