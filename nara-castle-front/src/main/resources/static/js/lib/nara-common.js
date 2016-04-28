/**
 * Created by hkkang on 2016-04-28.
 */
var NaraCommon = NaraCommon || { };


( function () {
    //
    'use strict';

    // TODO: jQuery랑 babel을 사용하고 있으므로 해당 라이브러리(스크립트)가 로드 됐는지 확인 필요
    // Import external library
    var _jQuery = jQuery,
        _jsxTransform = babel.transform;


    // Castle app Constant
    NaraCommon.Const = { };
    NaraCommon.Const.CTX = '.';


    // Ajax util
    NaraCommon.Ajax = { };

    /**
     * Post Json AJAX
     *
     * <p>postJSON(String url, Object paramData)</p>
     *
     * @param url url
     * @param param
     * @returns {*}
     */
    NaraCommon.Ajax.postJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string' || !param) {
            alert('Invalid arguments for Ajax postJSON -> url: ' + url + ', param: ' + param);
        }

        var jsonParamData = JSON.stringify(param);

        return _jQuery.ajax({
            url: url,
            method: 'POST',
            contentType: 'application/json',
            cache: false,
            data: jsonParamData
        });
    };

    /**
     * Get Json AJAX
     *
     * <p>postJSON(String url, Object paramData)</p>
     *
     * @param url url
     * @param param
     * @returns {*}
     */
    NaraCommon.Ajax.getJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string') {
            alert('Invalid url for Ajax getJSON -> url: ' + url + ', param: ' + param);
        }

        if (param) {
            return _jQuery.getJSON(url, JSON.stringify(param));
        }
        else {
            return _jQuery.getJSON(url);
        }
    };

    // Script cache object
    var scriptCache = {
        caches : { },
        add: function (url, script) {
            this.caches[url] = script;
        },
        get: function (url) {
            return this.caches[url];
        }
    };

    /**
     * Get and execute JavaScript of JSX
     * @param url
     * @param callback
     */
    NaraCommon.Ajax.getScript = function (url, callback) {
        //
        // Import extenral module
        var callbackCallable = false,
            cachedScript;

        if (!url || typeof url !== 'string') {
            alert('Invalid url for Ajax getScript -> url: ' + url);
        }
        if (callback && typeof callback === 'function') {
            callbackCallable = true;
        }

        cachedScript = scriptCache.get(url);

        // Script exists in cache
        if (cachedScript) {
            console.info('Execute cached script');
            _jsxTransform.run(cachedScript);

            if (callbackCallable) {
                callback(cachedScript);
            }
        }
        // Not exists
        else {
            _jQuery.ajax({
                url: url,
                method: 'GET',
                cache: false,
                success : function (result) {
                    console.info('Execute script from server');
                    scriptCache.add(url, result);
                    _jsxTransform.run(result);

                    if (callbackCallable) {
                        callback(result);
                    }
                },
                error: function (result) {
                    alert('Fail CastleCommon.getScript');
                    console.error(result);
                }
            });
        }
    };


    // Date util
    NaraCommon.Date = { };
    NaraCommon.Date.parseToString = function (date) {
        if (!date) {
            return null;
        }
        return new Date(date).toLocaleString();
    };


    // Object util
    NaraCommon.Object = { };
    NaraCommon.Object.isEmpty = function (object) {
        return (!object || Object.keys(object).length === 0);
    };

})();
