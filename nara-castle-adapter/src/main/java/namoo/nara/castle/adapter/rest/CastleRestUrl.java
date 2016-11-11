package namoo.nara.castle.adapter.rest;

import namoo.nara.share.restclient.HttpMethod;
import namoo.nara.share.restclient.NaraRestUrl;

public enum CastleRestUrl implements NaraRestUrl {
    //
    // Castle
    URL_CASTLE_BUILD                         ("castle-api/castle",                                         HttpMethod.POST      ),
    URL_CASTLE_LOCALE_MODIFY                 ("castle-api/castles/{id}/locale",                            HttpMethod.PUT       ),
    URL_CASTLE_FIND                          ("castle-api/castles/{id}",                                   HttpMethod.GET       ),
    URL_CASTLE_FIND_BY_CONDITION             ("castle-api/castle",                                         HttpMethod.GET       ),
    URL_CASTLE_FIND_ALL                      ("castle-api/castles",                                        HttpMethod.GET       ),

    // Castellan
    URL_CASTELLAN_EMAIL_ADD                  ("castle-api/castellans/{id}/email",                          HttpMethod.POST      ),
    URL_CASTELLAN_EMAIL_REMOVE               ("castle-api/castellans/{id}/email",                          HttpMethod.DELETE    ),
    URL_CASTELLAN_JOINED_METRO_ADD           ("castle-api/castellans/{id}/joined-metro",                   HttpMethod.POST      ),
    URL_CASTELLAN_JOINED_METROS_FIND         ("castle-api/castellans/{id}/joined-metros",                  HttpMethod.GET       ),
    URL_CASTELLAN_JOINED_METRO_REMOVE        ("castle-api/castellans/{id}/joined-metros/{metroId}",        HttpMethod.DELETE    ),
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
