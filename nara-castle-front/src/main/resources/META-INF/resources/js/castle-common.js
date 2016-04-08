/**
 * Created by hkkang on 2016-04-05.
 */
var CONST = CONST || {};

CONST.CTX = '';

var CastleComponent = {};
var CastellanComponent = {};

var CastleCommon = {};

(function () {
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
