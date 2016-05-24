/**
 * Created by hkkang on 2016-04-05.
 */

// Castle app components namespace
window.castle = {
    component: {
        common: {}
    },
    common: {}
};


( function () {
    //
    'use strict';

    let publicNamespace = {};

    // Import external library and module
    let _jQuery = $,
        commonObject = NaraCommon.Object;


    // Castle app constant
    publicNamespace.Const = {};

    commonObject.defineConstProperties(publicNamespace.Const, {
        CTX: '.',
        PAV_CTX_API: '/drama/castle',
        PAV_CTX_RSRC: '/drama/castle',
        PAV_CTX_HASH: '#/drama/castle'

    });


    publicNamespace.getCastleMainJDom = function () {
        return _jQuery('#castle-drama')[0];
    };


    window.castle.common = publicNamespace;
})();