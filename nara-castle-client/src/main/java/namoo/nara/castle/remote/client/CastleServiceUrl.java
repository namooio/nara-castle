package namoo.nara.castle.remote.client;

import namoo.nara.share.restclient.ServiceUrl;
import org.springframework.http.HttpMethod;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public enum CastleServiceUrl implements ServiceUrl {

    URL_CASTLE_OPEN                  ("castle/{castleId}/open",                         HttpMethod.PUT      ),
    URL_CASTLE_CLOSE                 ("castle/{castleId}/close",                        HttpMethod.PUT      ),

    URL_CASTELLAN_CREATE             ("castellan",                                      HttpMethod.POST     ),
    URL_CASTELLAN_GET                ("castellan/{castellanId}",                        HttpMethod.GET      ),
    URL_CASTELLAN_REMOVE             ("castellan/{castellanId}",                        HttpMethod.DELETE   ),
    URL_CASTELLAN_DISPLAYNAME_GET    ("castellan/{castellanId}/displayname",            HttpMethod.GET      ),
    URL_CASTELLAN_GET_BY_EMAIL       ("castellan/email/{email}",                        HttpMethod.GET      ),
    URL_CASTELLAN_EMAIL_ADD          ("castellan/{castellanId}/email/{email}",          HttpMethod.POST     ),
    URL_CASTELLAN_EMAIL_VERIFY       ("castellan/{castellanId}/email/{email}/verify",   HttpMethod.PUT      ),
    URL_CASTELLAN_EMAIL_PRIMARY      ("castellan/{castellanId}/email/{email}/primary",  HttpMethod.PUT      ),
    URL_CASTELLAN_EMAIL_REMOVE       ("castellan/{castellanId}/email/{email}",          HttpMethod.DELETE   ),

    ;

    private String serviceUrl;

    private HttpMethod method;

    CastleServiceUrl(String serviceUrl, HttpMethod httpMethod) {
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
