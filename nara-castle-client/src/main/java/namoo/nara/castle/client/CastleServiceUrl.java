package namoo.nara.castle.client;

import namoo.nara.share.restclient.NaraServiceUrl;

import javax.ws.rs.HttpMethod;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public enum CastleServiceUrl implements NaraServiceUrl {
    //
    URL_CASTLE_BUILD                          ("castle/{id}",                               HttpMethod.POST     ),
    URL_CASTLE_FIND                           ("castle/{id}",                               HttpMethod.GET      ),
    URL_CASTLE_SUSPEND                        ("castle/{id}/suspend",                       HttpMethod.PUT      ),
    URL_CASTLE_REOPEN                         ("castle/{id}/reopen",                        HttpMethod.PUT      ),
    URL_CASTLE_NAME_MODIFY                    ("castle/{id}/name",                          HttpMethod.PUT      ),
    URL_CASTLE_LOCALE_MODIFY                  ("castle/{id}/locale",                        HttpMethod.PUT      ),

    URL_CASTLE_HISTORY_ACCOUNTBOOK_ATTACH     ("castle/{id}/history/accountbook",           HttpMethod.POST     ),
    URL_CASTLE_HISTORY_ACCOUNTBOOK_DETACH     ("castle/{id}/history/accountbook",           HttpMethod.DELETE   ),
    URL_CASTLE_HISTORY_ACCOUNTBOOK_FIND       ("castle/{id}/history/accountbook",           HttpMethod.GET      ),
    URL_CASTLE_HISTORY_STATEBOOK_ATTACH       ("castle/{id}/history/statebook",             HttpMethod.POST     ),
    URL_CASTLE_HISTORY_STATEBOOK_DETACH       ("castle/{id}/history/statebook",             HttpMethod.DELETE   ),
    URL_CASTLE_HISTORY_STATEBOOK_FIND         ("castle/{id}/history/statebook",             HttpMethod.GET      ),
    URL_CASTLE_HISTORY_METROBOOK_ATTACH       ("castle/{id}/history/metrobook",             HttpMethod.POST     ),
    URL_CASTLE_HISTORY_METROBOOK_DETACH       ("castle/{id}/history/metrobook",             HttpMethod.DELETE   ),
    URL_CASTLE_HISTORY_METROBOOK_FIND         ("castle/{id}/history/metrobook",             HttpMethod.GET      ),

    URL_CASTELLAN_DISPLAYNAME_MODIFY          ("castellan/{id}/displayname",                HttpMethod.PUT      ),
    URL_CASTELLAN_PHOTO_MODIFY                ("castellan/{id}/photo",                      HttpMethod.PUT      ),
    URL_CASTELLAN_PRIMARYEMAIL_MODIFY         ("castellan/{id}/primaryemail",               HttpMethod.PUT      ),
    URL_CASTELLAN_PRIMARYPHONE_MODIFY         ("castellan/{id}/primaryphone",               HttpMethod.PUT      ),

    URL_CASTELLAN_CONTACT_NAMEBOOK_ATTACH     ("castellan/{id}/contact/namebook",           HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_NAMEBOOK_DETACH     ("castellan/{id}/contact/namebook",           HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_NAMEBOOK_FIND       ("castellan/{id}/contact/namebook",           HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_EMAILBOOK_ATTACH    ("castellan/{id}/contact/emailbook",          HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_EMAILBOOK_DETACH    ("castellan/{id}/contact/emailbook",          HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_EMAILBOOK_FIND      ("castellan/{id}/contact/emailbook",          HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_PHONEBOOK_ATTACH    ("castellan/{id}/contact/phonebook",          HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_PHONEBOOK_DETACH    ("castellan/{id}/contact/phonebook",          HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_PHONEBOOK_FIND      ("castellan/{id}/contact/phonebook",          HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_ADDRESSBOOK_ATTACH  ("castellan/{id}/contact/addressbook",        HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_ADDRESSBOOK_DETACH  ("castellan/{id}/contact/addressbook",        HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_ADDRESSBOOK_FIND    ("castellan/{id}/contact/addressbook",        HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_ADDRESS_ADD         ("castellan/{id}/contact/address",            HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_ADDRESS_REMOVE      ("castellan/{id}/contact/address/{title}",    HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_ADDRESS_MODIFY      ("castellan/{id}/contact/address",            HttpMethod.PUT      ),

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
