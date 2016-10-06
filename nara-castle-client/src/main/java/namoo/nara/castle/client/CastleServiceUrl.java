package namoo.nara.castle.client;

import namoo.nara.share.restclient.NaraServiceUrl;

import javax.ws.rs.HttpMethod;

public enum CastleServiceUrl implements NaraServiceUrl {
    //
    // Castle
    URL_CASTLE_BUILD                         ("castle-api/castle",                              HttpMethod.POST     ),
    URL_CASTLE_LOCALE_MODIFY                 ("castle-api/castles/{id}/locale",                 HttpMethod.PUT      ),
    URL_CASTLE_FIND                          ("castle-api/castles/{id}",                        HttpMethod.GET      ),
    URL_CASTLE_FIND_ALL                      ("castle-api/castles",                             HttpMethod.GET      ),

    // Castellan
    URL_CASTELLAN_CREATE                     ("castle-api/castellans/{id}",                     HttpMethod.POST     ),
    URL_CASTELLAN_FIND                       ("castle-api/castellans/{id}",                     HttpMethod.GET      ),
    URL_CASTELLAN_FIND_BY_ACCOUNT            ("castle-api/castellan",                           HttpMethod.GET      ),
    URL_CASTELLAN_MODIFY                     ("castle-api/castellans/{id}",                     HttpMethod.PUT      ),
    URL_CASTELLAN_PHOTO_MODIFY               ("castle-api/castellans/{id}/photo",               HttpMethod.PUT      ),
    URL_CASTELLAN_REMOVE                     ("castle-api/castellans/{id}",                     HttpMethod.DELETE   ),
    URL_CASTELLAN_ACCOUNT_ADD                ("castle-api/castellans/{id}/account",             HttpMethod.POST     ),
    URL_CASTELLAN_ACCOUNT_REMOVE             ("castle-api/castellans/{id}/account",             HttpMethod.DELETE   ),
    URL_CASTELLAN_PASSWORD_FIND              ("castle-api/castellans/{id}/password",            HttpMethod.GET      ),
    URL_CASTELLAN_PASSWORD_MODIFY            ("castle-api/castellans/{id}/password",            HttpMethod.PUT      ),
    URL_CASTELLAN_EMAIL_ADD                  ("castle-api/castellans/{id}/email",               HttpMethod.POST     ),
    URL_CASTELLAN_EMAIL_VERIFY               ("castle-api/castellans/{id}/email-verification",  HttpMethod.PUT      ),
    URL_CASTELLAN_PRIMARY_EMAIL_SET          ("castle-api/castellans/{id}/primary-email",       HttpMethod.PUT      ),
    URL_CASTELLAN_EMAIL_REMOVE               ("castle-api/castellans/{id}/email",               HttpMethod.DELETE   ),
    URL_CASTELLAN_JOINED_METRO_ADD           ("castle-api/castellans/{id}/joined-metro",        HttpMethod.POST     ),
    URL_CASTELLAN_JOINED_METRO_REMOVE        ("castle-api/castellans/{id}/joined-metro",        HttpMethod.DELETE   ),
    ;

    private String serviceUrl;
    private String method;


    CastleServiceUrl(String serviceUrl, String httpMethod) {
        //
        this.serviceUrl = serviceUrl;
        this.method = httpMethod;
    }


    @Override
    public String getUrl() {
        return this.serviceUrl;
    }

    @Override
    public String getMethod() {
        return this.method;
    }
}
