/**
 * Created by hkkang on 2016-04-05.
 */

import { Object as NaraObject, Url as NaraUrl } from 'nara';


let constantPublicContext = {};
let domPublicContext = {};

( function () {
    //
    'use strict';

    // Castle app constant
    let PAV_CTX = {};

    NaraObject.defineConstProperties(PAV_CTX, {
        root: NaraUrl.getPavilionContextPath(),
        api: `${NaraUrl.getPavilionContextPath()}/api`,
        res: `${NaraUrl.getPavilionContextPath()}/resource`,
        hash: NaraUrl.getPavilionContextPath()
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



import { Dom as NaraDom } from 'nara';
import NaraRoleBook from 'app/lib/nara-role-book.jsx';
import { Constant } from 'app/common/castle-common';

// Initialize
( function () {
    //
    'use strict';

    // Security token
    NaraDom.addTokenAtAjaxSendEvent();

    // RoleBook set context path
    NaraRoleBook.setContextPath(Constant.PAV_CTX.root);

})();