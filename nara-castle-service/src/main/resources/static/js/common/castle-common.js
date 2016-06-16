/**
 * Created by hkkang on 2016-04-05.
 */

import { Object as NaraObject } from 'app/lib/nara-common';

let constPublicNamespace = {};
let domPublicNamespace = {};

( function () {
    //
    'use strict';

    //let publicNamespace = {};

    // Castle app constant
    constPublicNamespace = {};

    let hashs = window.location.hash.split('/'),
        standAlone = (hashs.length < 2 || hashs[1] !== 'drama');


    NaraObject.defineConstProperties(constPublicNamespace, {
        CTX: '.',
        PAV_CTX_API: standAlone ? '' : '/drama/castle',
        PAV_CTX_RSRC: standAlone ? '' : '/drama/castle',
        PAV_CTX_HASH: standAlone ? '' : '/drama/castle'
    });

    domPublicNamespace = {};

    domPublicNamespace.getCastleMainDom = function () {
        return document.getElementById('castle-drama');
    };


    //window.castle.common = publicNamespace;
})();

export default { Constant: constPublicNamespace, Dom: domPublicNamespace };
export { constPublicNamespace as Constant, domPublicNamespace as Dom };