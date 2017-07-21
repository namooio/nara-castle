package nara.castle.adapter.rest;

import nara.share.restclient.HttpMethod;
import nara.share.restclient.NaraRestUrl;

public enum CastleRestUrl implements NaraRestUrl {
    //
    URL_CASTLE_BUILD            ("castle-api/castles",                                 HttpMethod.POST),
    URL_CASTELLAN_MODIFY        ("castle-api/castellans/{castellanId}",                HttpMethod.PUT),
    URL_ENROLLMENT_ADD          ("castle-api/castellans/{castellanId}/enrollments",    HttpMethod.POST),
    URL_ENROLLMENT_WIDTHRAW     ("castle-api/castellans/{castellanId}/withdrawal",     HttpMethod.PUT),

    URL_CASTELLAN_FIND          ("castle-api/castellans/{castellanId}",                HttpMethod.GET),
    URL_CASTELLANS_FIND         ("castle-api/castellans",                              HttpMethod.GET),
    URL_ENROLLMENTS_FIND        ("castle-api/castellans/{castellanId}/enrollments",    HttpMethod.GET),
    URL_UNIT_PLATES_FIND        ("castle-api/castellans/unitplates",                   HttpMethod.GET),
    URL_ENROLLMENTS_CHECK       ("castle-api/castellans/{castellanId}/enrollments/metros/{metroId}/exists",    HttpMethod.GET),
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
