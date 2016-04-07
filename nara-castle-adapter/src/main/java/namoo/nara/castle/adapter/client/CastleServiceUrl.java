package namoo.nara.castle.adapter.client;

import namoo.nara.share.restclient.ServiceUrl;
import org.springframework.http.HttpMethod;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public enum CastleServiceUrl implements ServiceUrl {
    //
    URL_CASTLE_BUILD                 ("castle/{id}",                       HttpMethod.POST     ),
    URL_CASTLE_FIND                  ("castle/{id}",                       HttpMethod.GET      ),
    URL_CASTLE_SUSPEND               ("castle/{id}/suspend",               HttpMethod.PUT      ),
    URL_CASTLE_REOPEN                ("castle/{id}/reopen",                HttpMethod.PUT      ),
    URL_CASTLE_NAME_MODIFY           ("castle/{id}/name",                  HttpMethod.PUT      ),
    URL_CASTLE_LOCALE_MODIFY         ("castle/{id}/locale",                HttpMethod.PUT      ),

    ;

    private String serviceUrl;
    private HttpMethod method;

    CastleServiceUrl(String serviceUrl, HttpMethod httpMethod) {
        //
        this.serviceUrl = serviceUrl;
        this.method = httpMethod;
    }


    @Override
    public String getUrl() {
        return this.serviceUrl;
    }

    @Override
    public HttpMethod getMethod() {
        return this.method;
    }
}
