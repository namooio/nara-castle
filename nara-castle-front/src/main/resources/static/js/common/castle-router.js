/**
 * Created by hkkang on 2016-04-07.
 */
CastleCommon.Router = CastleCommon.Router || { };


( function () {
    //
    'use strict';

    // Import module
    var router = NaraReactRouter,
        constant = CastleCommon.Const;


    // Index
    router.addRedirect('', '#/castles');

    // Castle
    router.addMapping('#/castles', [
        { path: constant.CTX + '/resources/js/common/castle-model.js', component: { namespace: Components.Castle,  name: 'Model' } },
        { path: constant.CTX + '/resources/js/component/list.jsx',        component: { namespace: Components.Castle,  name: 'List' } }
    ]);

    router.addMapping('#/castle/basic', [
        { path: constant.CTX + '/resources/js/common/castle-model.js', component: { namespace: Components.Castle,  name: 'Model' } },
        { path: constant.CTX + '/resources/js/component/basic.jsx',       component: { namespace: Components.Castle,  name: 'Basic' } }
    ]);

    // Castle contact
    router.addMapping('#/castle/contact/name-book', [
        { path: constant.CTX + '/resources/js/common/castle-model.js',         component: { namespace: Components.Castle,  name: 'Model' } },
        { path: constant.CTX + '/resources/js/component/contact/name-book.jsx',   component: { namespace: Components.Castle,  name: 'NameBook' } }
    ]);
    router.addMapping('#/castle/contact/phone-book', [
        { path: constant.CTX + '/resources/js/common/castle-model.js',         component: { namespace: Components.Castle,  name: 'Model' } },
        { path: constant.CTX + '/resources/js/component/contact/phone-book.jsx',  component: { namespace: Components.Castle,  name: 'PhoneBook' } }
    ]);
    router.addMapping('#/castle/contact/email-book', [
        { path: constant.CTX + '/resources/js/common/castle-model.js',         component: { namespace: Components.Castle,  name: 'Model' } },
        { path: constant.CTX + '/resources/js/component/contact/email-book.jsx',  component: { namespace: Components.Castle,  name: 'EmailBook' } }
    ]);
    router.addMapping('#/castle/contact/address-book', [
        { path: constant.CTX + '/resources/js/common/castle-model.js',             component: { namespace: Components.Castle,  name: 'Model' } },
        { path: constant.CTX + '/resources/js/component/contact/address-book.jsx',    component: { namespace: Components.Castle,  name: 'AddressBook' } }
    ]);

    // Castle history
    router.addMapping('#/castle/history/account-book', [
        { path: constant.CTX + '/resources/js/common/castle-model.js',             component: { namespace: Components.Castle,  name: 'Model' } },
        { path: constant.CTX + '/resources/js/component/history/account-book.jsx',    component: { namespace: Components.Castle,  name: 'AccountBook' } }
    ]);
    router.addMapping('#/castle/history/state-book', [
        { path: constant.CTX + '/resources/js/common/castle-model.js',         component: { namespace: Components.Castle,  name: 'Model' } },
        { path: constant.CTX + '/resources/js/component/history/state-book.jsx',  component: { namespace: Components.Castle,  name: 'StateBook' } }
    ]);
    router.addMapping('#/castle/history/metro-book', [
        { path: constant.CTX + '/resources/js/common/castle-model.js',         component: { namespace: Components.Castle,  name: 'Model' } },
        { path: constant.CTX + '/resources/js/component/history/metro-book.jsx',  component: { namespace: Components.Castle,  name: 'MetroBook' } }
    ]);

})();
