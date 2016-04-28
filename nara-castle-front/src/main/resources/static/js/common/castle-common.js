/**
 * Created by hkkang on 2016-04-05.
 */
// Castle app components namespace
var Components = {
    Castle : { },
    Common : { }
};

var CastleCommon = CastleCommon || { };


( function () {
    //
    'use strict';

    // Castle app constant
    CastleCommon.Const = { };
    CastleCommon.Const.CTX = '.';
    CastleCommon.Const.TOP_MENU_DOM_ID = 'castle-top-menu';
    CastleCommon.Const.CONTENTS_DOM_ID = 'castle-content';



    CastleCommon.getTopMenuJDom = function () {
        return $('#' + CastleCommon.Const.TOP_MENU_DOM_ID)[0];
    };

    CastleCommon.getContentsJDom = function () {
        return $('#' + CastleCommon.Const.CONTENTS_DOM_ID)[0];
    };


})();
