/**
 * Created by hkkang on 2016-04-07.
 */

castle.common.Router = castle.common.Router || {};


( function () {
    //
    'use strict';

    // Import module
    let router = NaraCommon.ReactRouter,
        constant = castle.common.Const,
        component = castle.component;


    // Index
    router.addRedirect(constant.PAV_CTX_HASH, constant.PAV_CTX_HASH + '/castles');

    // Castle
    router.addMapping(constant.PAV_CTX_HASH + '/castles', [
        { path: constant.PAV_CTX_RSRC + '/resources/js/component/list.jsx',      component: { namespace: component, name: 'List' } }
    ]);

    router.addMapping(constant.PAV_CTX_HASH + '/castle/basic', [
        { path: constant.PAV_CTX_RSRC + '/resources/js/component/basic.jsx',         component: { namespace: component, name: 'Basic' } },
        { path: constant.PAV_CTX_RSRC + '/resources/js/component/detail-tab.jsx',    component: { namespace: component, name: 'Tab' } }
    ]);

    // Castle contact
    router.addMapping(constant.PAV_CTX_HASH + '/castle/contact/name-book', [
        { path: constant.PAV_CTX_RSRC + '/resources/js/component/contact/name-book.jsx', component: { namespace: component, name: 'NameBook' } },
        { path: constant.PAV_CTX_RSRC + '/resources/js/component/detail-tab.jsx',        component: { namespace: component, name: 'Tab' } }
    ]);
    router.addMapping(constant.PAV_CTX_HASH + '/castle/contact/phone-book', [
        { path: constant.PAV_CTX_RSRC + '/resources/js/component/contact/phone-book.jsx',    component: { namespace: component, name: 'PhoneBook' } }
    ]);
    router.addMapping(constant.PAV_CTX_HASH + '/castle/contact/email-book', [
        { path: constant.PAV_CTX_RSRC + '/resources/js/component/contact/email-book.jsx',    component: { namespace: component, name: 'EmailBook' } }
    ]);
    router.addMapping(constant.PAV_CTX_HASH + '/castle/contact/address-book', [
        { path: constant.PAV_CTX_RSRC + '/resources/js/component/contact/address-book.jsx',  component: { namespace: component, name: 'AddressBook' } }
    ]);

    // Castle history
    router.addMapping(constant.PAV_CTX_HASH + '/castle/history/account-book', [
        { path: constant.PAV_CTX_RSRC + '/resources/js/component/history/account-book.jsx',  component: { namespace: component, name: 'AccountBook' } }
    ]);
    router.addMapping(constant.PAV_CTX_HASH + '/castle/history/state-book', [
        { path: constant.PAV_CTX_RSRC + '/resources/js/component/history/state-book.jsx',    component: { namespace: component, name: 'StateBook' } }
    ]);
    router.addMapping(constant.PAV_CTX_HASH + '/castle/history/metro-book', [
        { path: constant.PAV_CTX_RSRC + '/resources/js/component/history/metro-book.jsx',    component: { namespace: component, name: 'MetroBook' } }
    ]);

})();