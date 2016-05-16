/**
 * Created by hkkang on 2016-04-05.
 */
let CastleCommon = {};


// Castle app components namespace
let Components = {
    Castle : {},
    Common : {}
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
        CTX: '.'
    });


    publicNamespace.getCastleMainJDom = function () {
        return _jQuery('#castle-drama')[0];
    };


    CastleCommon = publicNamespace;
})();