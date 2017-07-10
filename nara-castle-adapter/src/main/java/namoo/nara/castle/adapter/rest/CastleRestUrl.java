package namoo.nara.castle.adapter.rest;

import namoo.nara.share.restclient.HttpMethod;
import namoo.nara.share.restclient.NaraRestUrl;

public enum CastleRestUrl implements NaraRestUrl {
    //
    URL_CASTLE_BUILD      ("castle-api/castles",                            HttpMethod.POST),
    URL_ENROLL_METRO      ("castle-api/castles/{castleId}/enrollments",     HttpMethod.POST),
    URL_CASTLES_FIND      ("castle-api/castles",                            HttpMethod.GET),
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
