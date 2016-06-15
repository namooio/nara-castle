/**
 * Created by hkkang on 2016-04-07.
 */

import router from 'app/lib/nara-react-router';
import { Constant } from 'app/common/castle-common';


let component = null;

( function () {
    //
    'use strict';


    // Index
    router.addRedirect(Constant.PAV_CTX_HASH, `${Constant.PAV_CTX_HASH}/castles`);

    // Castle
    router.addMapping(`${Constant.PAV_CTX_HASH}/castles`, [
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/list.jsx`,      component: { namespace: component, name: 'List' } }
    ]);

    router.addMapping(`${Constant.PAV_CTX_HASH}/castle/basic`, [
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/basic.jsx`,         component: { namespace: component, name: 'Basic' } },
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/detail-tab.jsx`,    component: { namespace: component, name: 'Tab' } }
    ]);

    // Castle contact
    router.addMapping(`${Constant.PAV_CTX_HASH}/castle/contact/name-book`, [
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/contact/name-book.jsx`, component: { namespace: component, name: 'NameBook' } },
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/detail-tab.jsx`,        component: { namespace: component, name: 'Tab' } }
    ]);
    router.addMapping(`${Constant.PAV_CTX_HASH}/castle/contact/phone-book`, [
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/contact/phone-book.jsx`,   component: { namespace: component, name: 'PhoneBook' } },
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/detail-tab.jsx`,           component: { namespace: component, name: 'Tab' } }
    ]);
    router.addMapping(`${Constant.PAV_CTX_HASH}/castle/contact/email-book`, [
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/contact/email-book.jsx`,   component: { namespace: component, name: 'EmailBook' } },
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/detail-tab.jsx`,           component: { namespace: component, name: 'Tab' } }
    ]);
    router.addMapping(`${Constant.PAV_CTX_HASH}/castle/contact/address-book`, [
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/contact/address-book.jsx`, component: { namespace: component, name: 'AddressBook' } },
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/detail-tab.jsx`,           component: { namespace: component, name: 'Tab' } }
    ]);

    // Castle history
    router.addMapping(`${Constant.PAV_CTX_HASH}/castle/history/account-book`, [
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/history/account-book.jsx`, component: { namespace: component, name: 'AccountBook' } },
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/detail-tab.jsx`,           component: { namespace: component, name: 'Tab' } }
    ]);
    router.addMapping(`${Constant.PAV_CTX_HASH}/castle/history/state-book`, [
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/history/state-book.jsx`,   component: { namespace: component, name: 'StateBook' } },
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/detail-tab.jsx`,           component: { namespace: component, name: 'Tab' } }
    ]);
    router.addMapping(`${Constant.PAV_CTX_HASH}/castle/history/metro-book`, [
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/history/metro-book.jsx`,   component: { namespace: component, name: 'MetroBook' } },
        { path: `${Constant.PAV_CTX_RSRC}/resources/js/component/detail-tab.jsx`,           component: { namespace: component, name: 'Tab' } }
    ]);

})();