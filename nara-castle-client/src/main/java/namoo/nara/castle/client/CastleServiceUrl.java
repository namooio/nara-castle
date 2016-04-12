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
