package namoo.nara.castle.adapter.rest;

import namoo.nara.share.restclient.HttpMethod;
import namoo.nara.share.restclient.NaraRestUrl;

public enum CastleRestUrl implements NaraRestUrl {
    //
    // Castle
    URL_CASTLE_BUILD                         ("castle-api/castles",                                        HttpMethod.POST      ),
    URL_CASTLE_FIND                          ("castle-api/castles/{castleId}",                             HttpMethod.GET       ),
    URL_CASTLE_FIND_BY_NATION_AND_EMAIL      ("castle-api/nations/{nationId}/castles/emails/{email}",      HttpMethod.GET       ),
    URL_CASTLE_FIND_ALL                      ("castle-api/nations/{nationId}/castles",                     HttpMethod.GET       ),
    URL_CASTLE_MODIFY                        ("castle-api/castles/{castleId}",                             HttpMethod.PUT       ),
    URL_CASTLE_REMOVE                        ("castle-api/castles/{castleId}",                             HttpMethod.DELETE    ),

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
