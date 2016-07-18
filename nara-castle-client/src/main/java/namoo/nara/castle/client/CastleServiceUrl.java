package namoo.nara.castle.client;

import namoo.nara.share.restclient.NaraServiceUrl;

import javax.ws.rs.HttpMethod;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public enum CastleServiceUrl implements NaraServiceUrl {
    //
    URL_REP_CASTLE_BUILD                        ("rep/castles/{id}",                                    HttpMethod.POST     ),
    URL_REP_CASTLE_FIND                         ("rep/castles/{id}",                                    HttpMethod.GET      ),
    URL_REP_CASTLE_METRO_ADD                    ("rep/castles/{id}/metro/{metroId}",                    HttpMethod.POST     ),


    URL_STAGE_FIND_PLAYERS                      ("api/stage/players",                                 HttpMethod.GET      ),
    URL_STAGE_SAVE_ROLE_BOOK                    ("api/stage/rolebooks",                               HttpMethod.POST     ),
    URL_STAGE_FIND_ROLE_BOOK                    ("api/stage/rolebooks/castingId/{castingId}",     HttpMethod.GET      ),
    URL_STAGE_FIND_ALL_ROLES                    ("api/stage/roles",                                   HttpMethod.GET      ),
    URL_STAGE_FIND_ROLES_BY_PLYAER              ("api/stage/roles/castingId/{castingId}/plyaerId/{playerId}",     HttpMethod.GET),

    URL_ALL_CASTLES_FIND                        ("api/castles",                                       HttpMethod.GET      ),
    URL_CASTLE_FIND                             ("api/castles/{id}",                                  HttpMethod.GET      ),
    URL_CASTLE_SUSPEND                          ("api/castles/{id}/suspend",                          HttpMethod.PUT      ),
    URL_CASTLE_REOPEN                           ("api/castles/{id}/reopen",                           HttpMethod.PUT      ),
    URL_CASTLE_NAME_MODIFY                      ("api/castles/{id}/name",                             HttpMethod.PUT      ),
    URL_CASTLE_LOCALE_MODIFY                    ("api/castles/{id}/locale",                           HttpMethod.PUT      ),

    URL_CASTLE_HISTORY_ACCOUNTBOOK_ATTACH       ("api/castles/{id}/histories/accountbook",            HttpMethod.POST     ),
    URL_CASTLE_HISTORY_ACCOUNTBOOK_DETACH       ("api/castles/{id}/histories/accountbook",            HttpMethod.DELETE   ),
    URL_CASTLE_HISTORY_ACCOUNTBOOK_FIND         ("api/castles/{id}/histories/accountbook",            HttpMethod.GET      ),
    URL_CASTLE_HISTORY_STATEBOOK_ATTACH         ("api/castles/{id}/histories/statebook",              HttpMethod.POST     ),
    URL_CASTLE_HISTORY_STATEBOOK_DETACH         ("api/castles/{id}/histories/statebook",              HttpMethod.DELETE   ),
    URL_CASTLE_HISTORY_STATEBOOK_FIND           ("api/castles/{id}/histories/statebook",              HttpMethod.GET      ),
    URL_CASTLE_HISTORY_METROBOOK_ATTACH         ("api/castles/{id}/histories/metrobook",              HttpMethod.POST     ),
    URL_CASTLE_HISTORY_METROBOOK_DETACH         ("api/castles/{id}/histories/metrobook",              HttpMethod.DELETE   ),
    URL_CASTLE_HISTORY_METROBOOK_FIND           ("api/castles/{id}/histories/metrobook",              HttpMethod.GET      ),

    URL_CASTELLAN_FIND                          ("api/castellans/{id}",                               HttpMethod.GET      ),
    URL_CASTELLAN_DISPLAYNAME_MODIFY            ("api/castellans/{id}/displayname",                   HttpMethod.PUT      ),
    URL_CASTELLAN_PHOTO_MODIFY                  ("api/castellans/{id}/photo",                         HttpMethod.PUT      ),
    URL_CASTELLAN_PRIMARYEMAIL_MODIFY           ("api/castellans/{id}/primaryemail",                  HttpMethod.PUT      ),
    URL_CASTELLAN_PRIMARYPHONE_MODIFY           ("api/castellans/{id}/primaryphone",                  HttpMethod.PUT      ),

    URL_CASTELLAN_CONTACT_NAMEBOOK_ATTACH       ("api/castellans/{id}/contacts/namebook",             HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_NAMEBOOK_DETACH       ("api/castellans/{id}/contacts/namebook",             HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_NAMEBOOK_FIND         ("api/castellans/{id}/contacts/namebook",             HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_EMAILBOOK_ATTACH      ("api/castellans/{id}/contacts/emailbook",            HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_EMAILBOOK_DETACH      ("api/castellans/{id}/contacts/emailbook",            HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_EMAILBOOK_FIND        ("api/castellans/{id}/contacts/emailbook",            HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_PHONEBOOK_ATTACH      ("api/castellans/{id}/contacts/phonebook",            HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_PHONEBOOK_DETACH      ("api/castellans/{id}/contacts/phonebook",            HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_PHONEBOOK_FIND        ("api/castellans/{id}/contacts/phonebook",            HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_ADDRESSBOOK_ATTACH    ("api/castellans/{id}/contacts/addressbook",          HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_ADDRESSBOOK_DETACH    ("api/castellans/{id}/contacts/addressbook",          HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_ADDRESSBOOK_FIND      ("api/castellans/{id}/contacts/addressbook",          HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_ADDRESS_ADD           ("api/castellans/{id}/contacts/addresses",            HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_ADDRESS_REMOVE        ("api/castellans/{id}/contacts/addresses/{title}",    HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_ADDRESS_MODIFY        ("api/castellans/{id}/contacts/addresses",            HttpMethod.PUT      ),

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
