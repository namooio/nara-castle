/**
 * Created by hkkang on 2016-04-05.
 */
var CastleCommon = CastleCommon || { };


// Castle app components namespace
var Components = {
    Castle : { },
    Common : { }
};

( function () {
    //
    'use strict';

    var namespace = {};

    // Import external library
    var _jQuery = $;

    // Castle app constant
    namespace.Const = {
        CTX: '.',
        TOP_MENU_DOM_ID: 'castle-top-menu',
        CONTENTS_DOM_ID: 'castle-content'
    };

    namespace.getTopMenuJDom = function () {
        return _jQuery('#' + CastleCommon.Const.TOP_MENU_DOM_ID)[0];
    };

    namespace.getContentsJDom = function () {
        return _jQuery('#' + CastleCommon.Const.CONTENTS_DOM_ID)[0];
    };

    CastleCommon = namespace;

})();
