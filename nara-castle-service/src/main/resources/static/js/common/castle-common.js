/**
 * Created by hkkang on 2016-04-05.
 */

import jQuery from 'jquery';
import { Object as NaraObject } from 'app/lib/nara-common';

let constPublicContext = {};
let domPublicContext = {};


// Initialize
( function () {
    //
    'use strict';

    //let _jQuery = pavilion.lib.jQuery,
    let token = jQuery('meta[name=_csrf]').attr('content'),
        header = jQuery('meta[name=_csrf_header]').attr('content');

    if (token && header) {
        jQuery(document).ajaxSend( function(event, xhr) {
            xhr.setRequestHeader(header, token);
        });
    }
})();

( function () {
    //
    'use strict';

    //let publicNamespace = {};

    // Castle app constant
    constPublicContext = {};

    let hashs = window.location.hash.split('/'),
        standAlone = (hashs.length < 2 || hashs[1] !== 'drama');


    NaraObject.defineConstProperties(constPublicContext, {
        CTX: '.',
        PAV_CTX_API: standAlone ? '' : '/drama/castle',
        PAV_CTX_RSRC: standAlone ? '' : '/drama/castle',
        PAV_CTX_HASH: standAlone ? '' : '/drama/castle'
    });

    domPublicContext = {};

    domPublicContext.getCastleMainDom = function () {
        return document.getElementById('castle-drama');
    };


    //window.castle.common = publicNamespace;
})();

export default { Constant: constPublicContext, Dom: domPublicContext };
export { constPublicContext as Constant, domPublicContext as Dom };