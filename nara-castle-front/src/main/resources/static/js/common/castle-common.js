/**
 * Created by hkkang on 2016-04-05.
 */
var CastleConst = CastleConst || {};

CastleConst.CTX = '.';
CastleConst.TOP_MENU_DOM_ID = 'castle-top-menu';
CastleConst.CONTENTS_DOM_ID = 'castle-content';

var Components = {
    Castle : { name: 'Castle' },
};

var CastleCommon = {};

(function () {
    //
    'use strict';

    // Import extenral module
    //var jsxTransformer = JSXTransformer;
    var jsxTransform = babel.transform;

    CastleCommon.getTopMenuJDom = function () {
        return $('#' + CastleConst.TOP_MENU_DOM_ID)[0];
    };

    CastleCommon.getContentsJDom = function () {
        return $('#' + CastleConst.CONTENTS_DOM_ID)[0];
    };


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
            url: param1,
            method: 'POST',
            contentType: 'application/json',
            cache: false,
            data: jsonParamData
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
    };

    var scriptCache = {};

    /**
     * Get and execute jsx
     * @param url
     * @param callback
     */
    CastleCommon.getJSX = function (url, callback) {
        //
        var cachedScript = scriptCache[url];

        if (cachedScript) {
            console.info('Execute cached script');
            jsxTransform.run(cachedScript);

            if (callback && typeof callback === 'function') {
                callback(cachedScript);
            }

            return;
        }

        $.ajax({
            url: url,
            method: 'GET',
            cache: false,
            success : function (result) {
                console.info('Execute script from server');
                scriptCache[url] = result;
                jsxTransform.run(result);

                if (callback && typeof callback === 'function') {
                    callback(result);
                }
            },
            error: function (result) {
                alert('Fail CastleCommon.getJSX');
                console.error(result);
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

    CastleCommon.Object = {};
    CastleCommon.Object.isEmpty = function (object) {
        if (!object) {
            return true;
        }
        else if (Object.keys(object).length === 0) {
            return true;
        }
        return false;
    };

})();
