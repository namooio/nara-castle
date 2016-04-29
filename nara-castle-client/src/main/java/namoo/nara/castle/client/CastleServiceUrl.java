package namoo.nara.castle.client;

import namoo.nara.share.restclient.NaraServiceUrl;

import javax.ws.rs.HttpMethod;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public enum CastleServiceUrl implements NaraServiceUrl {
    //
    URL_ALL_CASTLES_FIND                      ("castles",                                      HttpMethod.GET     ),
    URL_CASTLE_BUILD                          ("castles/{id}",                                 HttpMethod.POST     ),
    URL_CASTLE_FIND                           ("castles/{id}",                                 HttpMethod.GET      ),
    URL_CASTLE_SUSPEND                        ("castles/{id}/suspend",                         HttpMethod.PUT      ),
    URL_CASTLE_REOPEN                         ("castles/{id}/reopen",                          HttpMethod.PUT      ),
    URL_CASTLE_NAME_MODIFY                    ("castles/{id}/name",                            HttpMethod.PUT      ),
    URL_CASTLE_LOCALE_MODIFY                  ("castles/{id}/locale",                          HttpMethod.PUT      ),

    URL_CASTLE_HISTORY_ACCOUNTBOOK_ATTACH     ("castles/{id}/histories/accountbook",           HttpMethod.POST     ),
    URL_CASTLE_HISTORY_ACCOUNTBOOK_DETACH     ("castles/{id}/histories/accountbook",           HttpMethod.DELETE   ),
    URL_CASTLE_HISTORY_ACCOUNTBOOK_FIND       ("castles/{id}/histories/accountbook",           HttpMethod.GET      ),
    URL_CASTLE_HISTORY_STATEBOOK_ATTACH       ("castles/{id}/histories/statebook",             HttpMethod.POST     ),
    URL_CASTLE_HISTORY_STATEBOOK_DETACH       ("castles/{id}/histories/statebook",             HttpMethod.DELETE   ),
    URL_CASTLE_HISTORY_STATEBOOK_FIND         ("castles/{id}/histories/statebook",             HttpMethod.GET      ),
    URL_CASTLE_HISTORY_METROBOOK_ATTACH       ("castles/{id}/histories/metrobook",             HttpMethod.POST     ),
    URL_CASTLE_HISTORY_METROBOOK_DETACH       ("castles/{id}/histories/metrobook",             HttpMethod.DELETE   ),
    URL_CASTLE_HISTORY_METROBOOK_FIND         ("castles/{id}/histories/metrobook",             HttpMethod.GET      ),

    URL_CASTELLAN_FIND                        ("castellans/{id}",                              HttpMethod.GET      ),
    URL_CASTELLAN_DISPLAYNAME_MODIFY          ("castellans/{id}/displayname",                  HttpMethod.PUT      ),
    URL_CASTELLAN_PHOTO_MODIFY                ("castellans/{id}/photo",                        HttpMethod.PUT      ),
    URL_CASTELLAN_PRIMARYEMAIL_MODIFY         ("castellans/{id}/primaryemail",                 HttpMethod.PUT      ),
    URL_CASTELLAN_PRIMARYPHONE_MODIFY         ("castellans/{id}/primaryphone",                 HttpMethod.PUT      ),

    URL_CASTELLAN_CONTACT_NAMEBOOK_ATTACH     ("castellans/{id}/contacts/namebook",            HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_NAMEBOOK_DETACH     ("castellans/{id}/contacts/namebook",            HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_NAMEBOOK_FIND       ("castellans/{id}/contacts/namebook",            HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_EMAILBOOK_ATTACH    ("castellans/{id}/contacts/emailbook",           HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_EMAILBOOK_DETACH    ("castellans/{id}/contacts/emailbook",           HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_EMAILBOOK_FIND      ("castellans/{id}/contacts/emailbook",           HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_PHONEBOOK_ATTACH    ("castellans/{id}/contacts/phonebook",           HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_PHONEBOOK_DETACH    ("castellans/{id}/contacts/phonebook",           HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_PHONEBOOK_FIND      ("castellans/{id}/contacts/phonebook",           HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_ADDRESSBOOK_ATTACH  ("castellans/{id}/contacts/addressbook",         HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_ADDRESSBOOK_DETACH  ("castellans/{id}/contacts/addressbook",         HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_ADDRESSBOOK_FIND    ("castellans/{id}/contacts/addressbook",         HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_ADDRESS_ADD         ("castellans/{id}/contacts/addresses",           HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_ADDRESS_REMOVE      ("castellans/{id}/contacts/addresses/{title}",   HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_ADDRESS_MODIFY      ("castellans/{id}/contacts/addresses",           HttpMethod.PUT      ),

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
