package nara.castle.adapter.rest;

import nara.share.restclient.HttpMethod;
import nara.share.restclient.NaraRestUrl;

public enum CastleRestUrl implements NaraRestUrl {
    //
    URL_CASTLE_BUILD      ("castle-api/castles",                                HttpMethod.POST),
    URL_CASTELLAN_MODIFY  ("castle-api/castellan/{castellanId}",                HttpMethod.POST),
    URL_ADD_ENROLLMENT    ("castle-api/castellan/{castellanId}/enrollments",    HttpMethod.POST),
    URL_CASTLE_FIND       ("castle-api/castles/{castleId}",                     HttpMethod.GET),
    URL_CASTLES_FIND      ("castle-api/castles",                                HttpMethod.GET),
    URL_CASTELLANS_FIND   ("castle-api/castellans",                             HttpMethod.GET),
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
