/**
 * Created by hkkang on 2016-04-07.
 */

var CastleRouter = CastleRouter || {};


(function () {
    //
    'use strict';

    // Import component module
    var castleConst = CastleConst,
        naraReactRouter = NaraReactRouter;

    // Castle
    naraReactRouter.addMapping('#/castles', [
        { path: castleConst.CTX + '/resources/js/castle/castle-model.js', component: { namespace: Components.Castle,  name: 'Model' } },
        { path: castleConst.CTX + '/resources/js/castle/list.jsx',        component: { namespace: Components.Castle,  name: 'List' } }
    ]);

    naraReactRouter.addMapping('#/castle/basic', [
        { path: castleConst.CTX + '/resources/js/castle/castle-model.js', component: { namespace: Components.Castle,  name: 'Model' } },
        { path: castleConst.CTX + '/resources/js/castle/basic.jsx',       component: { namespace: Components.Castle,  name: 'Basic' } }
    ]);

    // Castle contact
    naraReactRouter.addMapping('#/castle/contact/name-book', [
        { path: castleConst.CTX + '/resources/js/castle/castle-model.js',         component: { namespace: Components.Castle,  name: 'Model' } },
        { path: castleConst.CTX + '/resources/js/castle/contact/name-book.jsx',   component: { namespace: Components.Castle,  name: 'NameBook' } }
    ]);
    naraReactRouter.addMapping('#/castle/contact/phone-book', [
        { path: castleConst.CTX + '/resources/js/castle/castle-model.js',         component: { namespace: Components.Castle,  name: 'Model' } },
        { path: castleConst.CTX + '/resources/js/castle/contact/phone-book.jsx',  component: { namespace: Components.Castle,  name: 'PhoneBook' } }
    ]);
    naraReactRouter.addMapping('#/castle/contact/email-book', [
        { path: castleConst.CTX + '/resources/js/castle/castle-model.js',         component: { namespace: Components.Castle,  name: 'Model' } },
        { path: castleConst.CTX + './resources/js/castle/contact/email-book.jsx',  component: { namespace: Components.Castle,  name: 'EmailBook' } }
    ]);
    naraReactRouter.addMapping('#/castle/contact/address-book', [
        { path: castleConst.CTX + '/resources/js/castle/castle-model.js',             component: { namespace: Components.Castle,  name: 'Model' } },
        { path: castleConst.CTX + '/resources/js/castle/contact/address-book.jsx',    component: { namespace: Components.Castle,  name: 'AddressBook' } }
    ]);

    // Castle history
    naraReactRouter.addMapping('#/castle/history/account-book', [
        { path: castleConst.CTX + '/resources/js/castle/castle-model.js',             component: { namespace: Components.Castle,  name: 'Model' } },
        { path: castleConst.CTX + '/resources/js/castle/history/account-book.jsx',    component: { namespace: Components.Castle,  name: 'AccountBook' } }
    ]);
    naraReactRouter.addMapping('#/castle/history/state-book', [
        { path: castleConst.CTX + './resources/js/castle/castle-model.js',         component: { namespace: Components.Castle,  name: 'Model' } },
        { path: castleConst.CTX + './resources/js/castle/history/state-book.jsx',  component: { namespace: Components.Castle,  name: 'StateBook' } }
    ]);
    naraReactRouter.addMapping('#/castle/history/metro-book', [
        { path: castleConst.CTX + './resources/js/castle/castle-model.js',         component: { namespace: Components.Castle,  name: 'Model' } },
        { path: castleConst.CTX + './resources/js/castle/history/metro-book.jsx',  component: { namespace: Components.Castle,  name: 'MetroBook' } }
    ]);

})();
