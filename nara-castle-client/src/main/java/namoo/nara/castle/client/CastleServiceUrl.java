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


    URL_STAGE_FIND_PLAYERS                      ("front/stage/players",                                 HttpMethod.GET      ),
    URL_STAGE_SAVE_ROLE_BOOK                    ("front/stage/rolebooks",                               HttpMethod.POST     ),
    URL_STAGE_FIND_ROLE_BOOK                    ("front/stage/rolebooks/castingId/{castingId}",     HttpMethod.GET      ),
    URL_STAGE_FIND_ALL_ROLES                    ("front/stage/roles",                                   HttpMethod.GET      ),
    URL_STAGE_FIND_ROLES_BY_PLYAER              ("front/stage/roles/castingId/{castingId}/plyaerId/{playerId}",     HttpMethod.GET),

    URL_ALL_CASTLES_FIND                        ("front/castles",                                       HttpMethod.GET      ),
    URL_CASTLE_FIND                             ("front/castles/{id}",                                  HttpMethod.GET      ),
    URL_CASTLE_SUSPEND                          ("front/castles/{id}/suspend",                          HttpMethod.PUT      ),
    URL_CASTLE_REOPEN                           ("front/castles/{id}/reopen",                           HttpMethod.PUT      ),
    URL_CASTLE_NAME_MODIFY                      ("front/castles/{id}/name",                             HttpMethod.PUT      ),
    URL_CASTLE_LOCALE_MODIFY                    ("front/castles/{id}/locale",                           HttpMethod.PUT      ),

    URL_CASTLE_HISTORY_ACCOUNTBOOK_ATTACH       ("front/castles/{id}/histories/accountbook",            HttpMethod.POST     ),
    URL_CASTLE_HISTORY_ACCOUNTBOOK_DETACH       ("front/castles/{id}/histories/accountbook",            HttpMethod.DELETE   ),
    URL_CASTLE_HISTORY_ACCOUNTBOOK_FIND         ("front/castles/{id}/histories/accountbook",            HttpMethod.GET      ),
    URL_CASTLE_HISTORY_STATEBOOK_ATTACH         ("front/castles/{id}/histories/statebook",              HttpMethod.POST     ),
    URL_CASTLE_HISTORY_STATEBOOK_DETACH         ("front/castles/{id}/histories/statebook",              HttpMethod.DELETE   ),
    URL_CASTLE_HISTORY_STATEBOOK_FIND           ("front/castles/{id}/histories/statebook",              HttpMethod.GET      ),
    URL_CASTLE_HISTORY_METROBOOK_ATTACH         ("front/castles/{id}/histories/metrobook",              HttpMethod.POST     ),
    URL_CASTLE_HISTORY_METROBOOK_DETACH         ("front/castles/{id}/histories/metrobook",              HttpMethod.DELETE   ),
    URL_CASTLE_HISTORY_METROBOOK_FIND           ("front/castles/{id}/histories/metrobook",              HttpMethod.GET      ),

    URL_CASTELLAN_FIND                          ("front/castellans/{id}",                               HttpMethod.GET      ),
    URL_CASTELLAN_DISPLAYNAME_MODIFY            ("front/castellans/{id}/displayname",                   HttpMethod.PUT      ),
    URL_CASTELLAN_PHOTO_MODIFY                  ("front/castellans/{id}/photo",                         HttpMethod.PUT      ),
    URL_CASTELLAN_PRIMARYEMAIL_MODIFY           ("front/castellans/{id}/primaryemail",                  HttpMethod.PUT      ),
    URL_CASTELLAN_PRIMARYPHONE_MODIFY           ("front/castellans/{id}/primaryphone",                  HttpMethod.PUT      ),

    URL_CASTELLAN_CONTACT_NAMEBOOK_ATTACH       ("front/castellans/{id}/contacts/namebook",             HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_NAMEBOOK_DETACH       ("front/castellans/{id}/contacts/namebook",             HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_NAMEBOOK_FIND         ("front/castellans/{id}/contacts/namebook",             HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_EMAILBOOK_ATTACH      ("front/castellans/{id}/contacts/emailbook",            HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_EMAILBOOK_DETACH      ("front/castellans/{id}/contacts/emailbook",            HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_EMAILBOOK_FIND        ("front/castellans/{id}/contacts/emailbook",            HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_PHONEBOOK_ATTACH      ("front/castellans/{id}/contacts/phonebook",            HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_PHONEBOOK_DETACH      ("front/castellans/{id}/contacts/phonebook",            HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_PHONEBOOK_FIND        ("front/castellans/{id}/contacts/phonebook",            HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_ADDRESSBOOK_ATTACH    ("front/castellans/{id}/contacts/addressbook",          HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_ADDRESSBOOK_DETACH    ("front/castellans/{id}/contacts/addressbook",          HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_ADDRESSBOOK_FIND      ("front/castellans/{id}/contacts/addressbook",          HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_ADDRESS_ADD           ("front/castellans/{id}/contacts/addresses",            HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_ADDRESS_REMOVE        ("front/castellans/{id}/contacts/addresses/{title}",    HttpMethod.DELETE   ),
    URL_CASTELLAN_CONTACT_ADDRESS_MODIFY        ("front/castellans/{id}/contacts/addresses",            HttpMethod.PUT      ),

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
