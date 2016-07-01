/**
 * Created by hkkang on 2016-04-05.
 */

// Initialize
( function () {
    //
    'use strict';

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
        api: `${NaraUrl.getPavilionHashContextPath()}/api`,
        res: `${NaraUrl.getPavilionHashContextPath()}/resource`,
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
