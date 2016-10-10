package namoo.nara.castle.adapter.rest;

import namoo.nara.share.restclient.HttpMethod;
import namoo.nara.share.restclient.NaraRestUrl;

public enum CastleRestUrl implements NaraRestUrl {
    //
    // Castle
    URL_CASTLE_BUILD                         ("castle-api/castle",                              HttpMethod.POST      ),
    URL_CASTLE_LOCALE_MODIFY                 ("castle-api/castles/{id}/locale",                 HttpMethod.PUT       ),
    URL_CASTLE_FIND                          ("castle-api/castles/{id}",                        HttpMethod.GET       ),
    URL_CASTLE_FIND_ALL                      ("castle-api/castles",                             HttpMethod.GET       ),

    // Castellan
    URL_CASTELLAN_CREATE                     ("castle-api/castellans/{id}",                     HttpMethod.POST      ),
    URL_CASTELLAN_FIND                       ("castle-api/castellans/{id}",                     HttpMethod.GET       ),
    URL_CASTELLAN_FIND_BY_ACCOUNT            ("castle-api/castellan",                           HttpMethod.GET       ),
    URL_CASTELLAN_REMOVE                     ("castle-api/castellans/{id}",                     HttpMethod.DELETE    ),
    URL_CASTELLAN_ACCOUNT_ADD                ("castle-api/castellans/{id}/account",             HttpMethod.POST      ),
    URL_CASTELLAN_ACCOUNTS_FIND              ("castle-api/castellans/{id}/accounts",            HttpMethod.GET       ),
    URL_CASTELLAN_ACCOUNT_REMOVE             ("castle-api/castellans/{id}/account",             HttpMethod.DELETE    ),
    URL_CASTELLAN_PASSWORD_FIND              ("castle-api/castellans/{id}/password",            HttpMethod.GET       ),
    URL_CASTELLAN_PASSWORD_MODIFY            ("castle-api/castellans/{id}/password",            HttpMethod.PUT       ),
    URL_CASTELLAN_EMAIL_ADD                  ("castle-api/castellans/{id}/email",               HttpMethod.POST      ),
    URL_CASTELLAN_EMAIL_VERIFY               ("castle-api/castellans/{id}/email-verification",  HttpMethod.PUT       ),
    URL_CASTELLAN_PRIMARY_EMAIL_SET          ("castle-api/castellans/{id}/primary-email",       HttpMethod.PUT       ),
    URL_CASTELLAN_EMAIL_REMOVE               ("castle-api/castellans/{id}/email",               HttpMethod.DELETE    ),
    URL_CASTELLAN_JOINED_METRO_ADD           ("castle-api/castellans/{id}/joined-metro",        HttpMethod.POST      ),
    URL_CASTELLAN_JOINED_METROS_FIND         ("castle-api/castellans/{id}/joined-metros",       HttpMethod.GET       ),
    URL_CASTELLAN_JOINED_METRO_REMOVE        ("castle-api/castellans/{id}/joined-metro",        HttpMethod.DELETE    ),
    ;

    private String restUrl;
    private HttpMethod method;


    CastleRestUrl(String restUrl, HttpMethod method) {
        //
        this.restUrl = restUrl;
        this.method = method;
    }


    @Override
    public String getUrl() {
        return this.restUrl;
    }

    @Override
    public HttpMethod getMethod() {
        return this.method;
    }
}
