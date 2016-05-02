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
            this.caches[url.split('?')[0]] = script;
        },
        get: function (url) {
            return this.caches[url.split('?')[0]];
        }
    };

    /**
     * Get and execute JavaScript or JSX
     * <p>
     *     function (string url, function callback [optional])
     *     function (string url, object settings [optional], function callback [optional]
     * </p>
     *
     * @param url
     * @param param1 Optional, this param is callback or settings
     * @param param2 Optional calllback when param1 exists
     */
    NaraCommon.Ajax.getScript = function (url, param1, param2) {
        //
        if (!url || typeof url !== 'string') {
            alert('Invalid url for Ajax getScript -> url: ' + url);
        }

        NaraCommon.Ajax.getScripts([url], param1, param2);
    };

    /**
     * Get and execute JavaScript or JSX list
     * <p>
     *     function (array<string> url, function callback [optional])
     *     function (array<string> url, object settings [optional], function callback [optional]
     * </p>
     *
     * @param url
     * @param param1 Optional, this param is callback or settings
     * @param param2 Optional calllback when param1 exists
     */
    NaraCommon.Ajax.getScripts = function (urlArray, param1, param2) {
        //
        var settings,
            callback,
            callbackCallable = false;

        if (!urlArray || !Array.isArray(urlArray) || urlArray.length === 0) {
            alert('Invalid url for Ajax getScripts -> urlArray: ' + urlArray);
        }

        if (param1 && typeof param1 === 'function') {
            settings = {
                async: true
            };
            callback = param1;
        }
        else if (param1 && typeof param1 === 'object') {
            settings = param1;
            if (param2 && typeof param2 === 'function') {
                callback = param2;
            }
        }

        if (callback && typeof callback === 'function') {
            callbackCallable = true;
        }

        var promisses = [];

        for (var i = 0; i < urlArray.length; i ++) {
            //
            var url = urlArray[i],
                cached = scriptCache.get(url);

            // Script exists in cache
            if (cached) {
                console.info('Execute cached script');
                _jsxTransform.run(cached.script);
            }
            // Not exists
            else {
                var promiss = _jQuery.ajax({
                    url: url,
                    method: 'GET',
                    cache: false,
                    async: settings.async,
                    error: function (result) {
                        alert('Fail CastleCommon.getScript');
                        console.error(result);
                    }
                });
                promisses.push(promiss);
            }
        }

        if (promisses.length > 0) {
            $.when.apply($, promisses).done(function () {
                //
                var ajaxResults = Array.isArray(arguments[0]) ? arguments : [arguments],
                    ajaxRequest = Array.isArray(this) ? this : [this];

                for (var i = 0; i < ajaxResults.length; i++) {
                    var url = ajaxRequest[i].url,
                        ajaxResult = ajaxResults[i],
                        resultScript = ajaxResult[0];

                    console.info('Execute script from server');
                    scriptCache.add(url, resultScript);
                    _jsxTransform.run(resultScript);
                }

                if (callbackCallable) {
                    callback();
                }
            });
        }
        else {
            if (callbackCallable) {
                callback();
            }
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
