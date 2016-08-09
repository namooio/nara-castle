/**
 * Created by hkkang on 2016-04-05.
 */

import NaraRoleBook from 'nara-role-book';

// Initialize
( function () {
    //
    'use strict';

    // NaraRoleBook
    let roleBookConfig = {
        handler: {
            onLoad: function (roleNames) { console.log(`[Castle] role book on load -> ${roleNames}`); },
            onSaveSuccess: function () { console.log(`[Castle] role book on save success`); }
        }
    };
    NaraRoleBook.setRoleBookComponentConfig(roleBookConfig);
})();


import { Object as NaraObject, Url as NaraUrl } from 'nara';


// Castle app common
let constantPublicContext = {};
let domPublicContext = {};

( function () {
    //
    'use strict';

    // Constant
    let PAV_CTX = {};

    NaraObject.defineConstProperties(PAV_CTX, {
        root: NaraUrl.getPavilionHashContextPath(),
        api: `${NaraUrl.getPavilionHashContextPath()}/castle-api`,
        res: `${NaraUrl.getPavilionHashContextPath()}/castle-resource`,
        hash: NaraUrl.getPavilionHashContextPath()
    });
    NaraObject.defineConstProperties(constantPublicContext, {
        PAV_CTX: PAV_CTX
    });


    // Dom
    domPublicContext.getCastleMainDom = function () {
        //
        return document.getElementById('castle-drama');
    };


})();

export { constantPublicContext as Constant, domPublicContext as Dom };

export default {
    Constant: constantPublicContext,
    Dom: domPublicContext
};
