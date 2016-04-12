/**
 * Created by hkkang on 2016-04-05.
 */
var CastleConst = CastleConst || {};

CastleConst.CTX = '';
CastleConst.TOP_MENU_DOM_ID = 'castle-top-menu';
CastleConst.CONTENTS_DOM_ID = 'castle-contents';

var CastleComponent = {};
var CastellanComponent = {};

var CastleCommon = {};

(function () {

    CastleCommon.getTopMenuJDom = function () {
        return $('#' + CastleConst.TOP_MENU_DOM_ID)[0];
    };

    CastleCommon.getContentsJDom = function () {
        return $('#' + CastleConst.CONTENTS_DOM_ID)[0];
    };

    //
    /**
     * POST Json AJAX
     * <p>postJSON(String url, Object paramData)</p>
     * @param param1 url
     * @param param2
     * @returns {*}
     */
    CastleCommon.postJSON = function (param1, param2) {
        //
        if (!param1 || !param2) {
            alert('Invalid arguments -> param1: ' + param1 + ', param2: ' + param2);
        }

        var jsonParamData = JSON.stringify(param2);

        return $.ajax({
            url: param1
            , method: 'POST'
            , contentType: 'application/json'
            , cache: false
            , data: jsonParamData
        });
    };

    /**
     * Ajax getJSX
     * @param url
     */
    CastleCommon.getJSX = function (url) {
        //
        $.ajax({
            url: url
            , method: 'GET'
            , cache: false
            , success : function (result) {
                JSXTransformer.exec(result);
            }
            , error: function (result) {
                alert('Fail CastleCommon.getJSX');
                console.dir(result);
            }
        });
    };


})();
