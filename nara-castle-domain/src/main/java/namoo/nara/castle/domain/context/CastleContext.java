package namoo.nara.castle.domain.context;

import namoo.nara.castle.domain.proxy.CastleProxyLycler;
import namoo.nara.castle.domain.service.CastleServiceLycler;

public class CastleContext {

    private static CastleIdBuilder castleIdBuilder;
    private static CastleServiceLycler serviceLycler;
    private static CastleProxyLycler proxyLycler;

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

    public static CastleServiceLycler getServiceLycler() {
        return serviceLycler;
    }

    public static void setServiceLycler(CastleServiceLycler serviceLycler) {
        CastleContext.serviceLycler = serviceLycler;
    }

    public static CastleProxyLycler getProxyLycler() {
        return proxyLycler;
    }

    public static void setProxyLycler(CastleProxyLycler proxyLycler) {
        CastleContext.proxyLycler = proxyLycler;
    }
}
