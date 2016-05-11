/**
 * Created by hkkang on 2016-04-05.
 */
var CastleCommon = CastleCommon || {};


// Castle app components namespace
var Components = {
    Castle : {},
    Common : {}
};

( function () {
    //
    'use strict';

    var publicNamespace = {};

    // Import external library and module
    var _jQuery = $,
        commonObject = NaraCommon.Object;


    // Castle app constant
    publicNamespace.Const = {};
    commonObject.defineConstProperties(publicNamespace.Const, {
        CTX: '.',
    });


    publicNamespace.getCastleMainJDom = function () {
        return _jQuery('#castle-drama')[0];
    };



    CastleCommon = publicNamespace;

})();
