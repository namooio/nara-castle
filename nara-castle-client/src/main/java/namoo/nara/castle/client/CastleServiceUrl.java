package namoo.nara.castle.client;

import namoo.nara.share.restclient.NaraServiceUrl;

import javax.ws.rs.HttpMethod;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public enum CastleServiceUrl implements NaraServiceUrl {
    //
    URL_CASTLE_BUILD                 ("castle/{id}",                       HttpMethod.POST     ),
    URL_CASTLE_FIND                  ("castle/{id}",                       HttpMethod.GET      ),
    URL_CASTLE_SUSPEND               ("castle/{id}/suspend",               HttpMethod.PUT      ),
    URL_CASTLE_REOPEN                ("castle/{id}/reopen",                HttpMethod.PUT      ),
    URL_CASTLE_NAME_MODIFY           ("castle/{id}/name",                  HttpMethod.PUT      ),
    URL_CASTLE_LOCALE_MODIFY         ("castle/{id}/locale",                HttpMethod.PUT      ),

    URL_CASTELLAN_DISPLAYNAME_MODIFY ("castellan/{id}/displayname",        HttpMethod.PUT      ),
    URL_CASTELLAN_PHOTO_MODIFY       ("castellan/{id}/photo",              HttpMethod.PUT      ),
    URL_CASTELLAN_PRIMARYEMAIL_MODIFY("castellan/{id}/primaryemail",       HttpMethod.PUT      ),
    URL_CASTELLAN_PRIMARYPHONE_MODIFY("castellan/{id}/primaryphone",       HttpMethod.PUT      ),
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
