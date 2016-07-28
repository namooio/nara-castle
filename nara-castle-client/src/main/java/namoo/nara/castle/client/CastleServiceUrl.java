package namoo.nara.castle.client;

import namoo.nara.share.restclient.NaraServiceUrl;

import javax.ws.rs.HttpMethod;

/**
 * Created by kchuh@nextree.co.kr on 2016. 2. 11..
 */
public enum CastleServiceUrl implements NaraServiceUrl {
    //
    // Rep
    URL_REP_BUILD_CASTLE                        ("castle-rep/castles/{id}",                     HttpMethod.POST     ),
    URL_REP_FIND_CASTLE                         ("castle-rep/castles/{id}",                     HttpMethod.GET      ),
    URL_REP_ADD_METRO                           ("castle-rep/castles/{id}/metro/{metroId}",  HttpMethod.POST     ),

    URL_STAGE_FIND_PLAYERS                      ("stage/players",                                                 HttpMethod.GET      ),
    URL_STAGE_SAVE_ROLE_BOOK                    ("stage/rolebooks",                                               HttpMethod.POST     ),
    URL_STAGE_FIND_ROLE_BOOK                    ("stage/rolebooks/castingId/{castingId}",                      HttpMethod.GET      ),
    URL_STAGE_FIND_ALL_ROLES                    ("stage/roles",                                                    HttpMethod.GET      ),
    URL_STAGE_FIND_ROLES_BY_PLYAER              ("stage/roles/castingId/{castingId}/plyaerId/{playerId}",   HttpMethod.GET),

    // Castle
    URL_CASTLE_FIND_ALL                         ("castle-api/castles",                    HttpMethod.GET      ),
    URL_CASTLE_FIND                             ("castle-api/castles/{id}",              HttpMethod.GET      ),
    URL_CASTLE_SUSPEND                          ("castle-api/castles/{id}/suspend",     HttpMethod.PUT      ),
    URL_CASTLE_REOPEN                           ("castle-api/castles/{id}/reopen",      HttpMethod.PUT      ),
    URL_CASTLE_MODIFY_NAME                      ("castle-api/castles/{id}/name",        HttpMethod.PUT      ),
    URL_CASTLE_MODIFY_LOCALE                    ("castle-api/castles/{id}/locale",      HttpMethod.PUT      ),

    // Castle history
    URL_CASTLE_HISTORY_ATTACH_ACCOUNT_BOOK      ("castle-api/castles/{id}/histories/account-book",      HttpMethod.POST     ),
    URL_CASTLE_HISTORY_FIND_ACCOUNT_BOOK        ("castle-api/castles/{id}/histories/account-book",      HttpMethod.GET      ),
    URL_CASTLE_HISTORY_DETACH_ACCOUNT_BOOK      ("castle-api/castles/{id}/histories/account-book",      HttpMethod.DELETE   ),

    URL_CASTLE_HISTORY_ATTACH_STATE_BOOK        ("castle-api/castles/{id}/histories/state-book",        HttpMethod.POST     ),
    URL_CASTLE_HISTORY_FIND_STATE_BOOK          ("castle-api/castles/{id}/histories/state-book",        HttpMethod.GET      ),
    URL_CASTLE_HISTORY_DETACH_STATE_BOOK        ("castle-api/castles/{id}/histories/state-book",        HttpMethod.DELETE   ),

    URL_CASTLE_HISTORY_ATTACH_METRO_BOOK        ("castle-api/castles/{id}/histories/metro-book",        HttpMethod.POST     ),
    URL_CASTLE_HISTORY_FIND_METRO_BOOK          ("castle-api/castles/{id}/histories/metro-book",        HttpMethod.GET      ),
    URL_CASTLE_HISTORY_DETACH_METRO_BOOKH       ("castle-api/castles/{id}/histories/metro-book",        HttpMethod.DELETE   ),

    // Castellan
    URL_CASTELLAN_FIND                          ("castle-api/castellans/{id}",                        HttpMethod.GET      ),
    URL_CASTELLAN_MODIFY_DISPLAY_NAME           ("castle-api/castellans/{id}/display-name",         HttpMethod.PUT      ),
    URL_CASTELLAN_MODIFY_PHOTO                  ("castle-api/castellans/{id}/photo",                 HttpMethod.PUT      ),
    URL_CASTELLAN_MODIFY_PRIMARY_EMAIL          ("castle-api/castellans/{id}/primary-email",        HttpMethod.PUT      ),
    URL_CASTELLAN_MODIFY_PRIMARY_PHONE          ("castle-api/castellans/{id}/primary-phone",        HttpMethod.PUT      ),

    // Castellan contact
    URL_CASTELLAN_CONTACT_ATTACH_NAME_BOOK      ("castle-api/castellans/{id}/contacts/name-book",   HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_FIND_NAME_BOOK        ("castle-api/castellans/{id}/contacts/name-book",   HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_DETACH_NAME_BOOK      ("castle-api/castellans/{id}/contacts/name-book",   HttpMethod.DELETE   ),

    URL_CASTELLAN_CONTACT_ATTACH_EMAIL_BOOK     ("castle-api/castellans/{id}/contacts/email-book",  HttpMethod.POST     ),
    URL_CASTELLAN_CONTACT_FIND_EMAIL_BOOK       ("castle-api/castellans/{id}/contacts/email-book",  HttpMethod.GET      ),
    URL_CASTELLAN_CONTACT_DETACH_EMAIL_BOOK     ("castle-api/castellans/{id}/contacts/email-book",  HttpMethod.DELETE ),

    URL_CASTELLAN_CONTACT_ATTACH_PHONE_BOOK     ("castle-api/castellans/{id}/contacts/phone-book",  HttpMethod.POST   ),
    URL_CASTELLAN_CONTACT_FIND_PHONE_BOOK       ("castle-api/castellans/{id}/contacts/phone-book",  HttpMethod.GET    ),
    URL_CASTELLAN_CONTACT_DETACH_PHONE_BOOK     ("castle-api/castellans/{id}/contacts/phone-book",  HttpMethod.DELETE ),

    URL_CASTELLAN_CONTACT_ATTACH_ADDRESS_BOOK   ("castle-api/castellans/{id}/contacts/address-book",         HttpMethod.POST   ),
    URL_CASTELLAN_CONTACT_ADD_ADDRESS           ("castle-api/castellans/{id}/contacts/addresses",            HttpMethod.POST    ),
    URL_CASTELLAN_CONTACT_FIND_ADDRESS_BOOK     ("castle-api/castellans/{id}/contacts/address-book",         HttpMethod.GET    ),
    URL_CASTELLAN_CONTACT_MODIFY_ADDRESS        ("castle-api/castellans/{id}/contacts/addresses",            HttpMethod.PUT    ),
    URL_CASTELLAN_CONTACT_DETACH_ADDRESS_BOOK   ("castle-api/castellans/{id}/contacts/address-book",         HttpMethod.DELETE ),
    URL_CASTELLAN_CONTACT_REMOVE_ADDRESS        ("castle-api/castellans/{id}/contacts/addresses/{title}",   HttpMethod.DELETE ),

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
