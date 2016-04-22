/**
 * Created by hkkang on 2016-04-05.
 */
var CastleConst = CastleConst || {};

CastleConst.CTX = '';
CastleConst.TOP_MENU_DOM_ID = 'castle-top-menu';
CastleConst.CONTENTS_DOM_ID = 'castle-content';

var Components = {
    Castle : { name: 'Castle'}
    , Castellan : { name: 'Castellan'}
};

var CastleCommon = {};

(function () {
    //
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

    CastleCommon.getJSON = function (url, param2) {
        //
        if (!url) {
            alert('Invalid arguments -> url: ' + url + ', param2: ' + param2);
        }

        if (param2) {
            return $.getJSON(url, JSON.stringify(param2));
        }
        else {
            return $.getJSON(url);
        }
    }

    var scriptCache = {};

    /**
     * Get and execute jsx
     * @param url
     */
    CastleCommon.getJSX = function (url, callback) {
        //
        var cachedScript = scriptCache[url];

        if (cachedScript) {
            console.info('Execute cached script');
            JSXTransformer.exec(cachedScript);
            return;
        }

        $.ajax({
            url: url
            , method: 'GET'
            , cache: false
            , success : function (result) {
                console.info('Execute script from server');
                scriptCache[url] = result;
                JSXTransformer.exec(result);

                if (callback && typeof callback === 'function') {
                    callback(result);
                }
            }
            , error: function (result) {
                alert('Fail CastleCommon.getJSX');
                console.info(result);
            }
        });
    };


    CastleCommon.Date = {};
    CastleCommon.Date.parseToString = function (date) {
        if (!date) {
            return null;
        }
        return new Date(date).toLocaleString();
    };

})();
