/**
 * Created by hkkang on 2016-04-28.
 */
var NaraCommon = NaraCommon || {};


( function () {
    //
    'use strict';

    var publicNamespace = {};

    // TODO: jQuery랑 babel을 사용하고 있으므로 해당 라이브러리(스크립트)가 로드 됐는지 확인 필요
    // Import external library
    var _jQuery = jQuery,
        _jsxTransform = babel.transform;


    // Object util
    publicNamespace.Object = {};

    publicNamespace.Object.defineConstProperty = function (obj, name, value) {
        //
        Object.defineProperty(obj, name, {
            value: value,
            writable: false,
            configurable: false,
            enumerable: true
        });
    };

    publicNamespace.Object.defineConstProperties = function (obj, nameValues) {
        //
        Object.keys(nameValues).forEach( function (name) {
            publicNamespace.Object.defineConstProperty(obj, name, nameValues[name]);
        });
    };

    publicNamespace.Object.isEmpty = function (object) {
        //
        return (!object || Object.keys(object).length === 0);
    };

    publicNamespace.Object.deepCopy = function (source) {
        //
        if (!source || typeof source !== 'object') {
            console.warn("Source is not array or object. > NaraCommon.Object.deepCopy");
            return;
        }
        return deepCopy(source);
    };

    var deepCopy = function (source) {
        //
        var result;

        if (!source || !(typeof source === 'object' || Array.isArray(source))) {
            return source;
        }
        if (Array.isArray(source)) {
            result = [];
        }
        else {
            result = {};
        }

        for (var property in source) {
            if (source.hasOwnProperty(property)) {
                var sourcePropValue = source[property];
                result[property] = deepCopy(sourcePropValue);
            }
        }
        return result;
    };


    // Date util
    publicNamespace.Date = {};
    publicNamespace.Date.parseToString = function (date) {
        //
        if (!date) {
            return null;
        }
        return new Date(date).toLocaleString();
    };


    // Ajax util
    publicNamespace.Ajax = {};


    var UrlBuilder = function () {
        //
        this.urlAndParams = [];
    };
    UrlBuilder.prototype.addUrl = function (url) {
        this.urlAndParams.push({ url: url });
    };
    UrlBuilder.prototype.addUrlAndParam = function (url, param) {
        this.urlAndParams.push({ url: url, param: param });
    };
    UrlBuilder.prototype.build = function () {
        return this.urlAndParams;
    };

    publicNamespace.Ajax.createUrlBuilder = function () {
        //
        return new UrlBuilder();
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
    publicNamespace.Ajax.getJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string') {
            console.error('Invalid url for Ajax getJSON -> url: ' + url + ', param: ' + param);
        }
        return commonRequestJson(url, 'GET', param).pipe( function (jsonResult, status, jqXHR) {
            return jsonResult;
        });
    };

    publicNamespace.Ajax.getJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error('Invalid url for Ajax getJSONs -> urlParams: ' + urlParams);
        }
        return commonRequestJsons(urlParams, callback, 'GET');
    };

    /**
     * Post Json AJAX
     *
     * <p>postJSON(String url, Object paramData)</p>
     *
     * @param url url
     * @param param
     * @returns {*}
     */
    publicNamespace.Ajax.postJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string' || !param) {
            console.error('Invalid arguments for Ajax postJSON -> url: ' + url + ', param: ' + param);
        }
        return commonRequestJson(url, 'POST', param);
    };

    publicNamespace.Ajax.postJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error('Invalid url for Ajax postJSONs -> urlParams: ' + urlParams);
        }
        return commonRequestJsons(urlParams, callback, 'POST');
    };

    publicNamespace.Ajax.putJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string' || !param) {
            console.error('Invalid arguments for Ajax putJSON -> url: ' + url + ', param: ' + param);
        }
        return commonRequestJson(url, 'PUT', param);
    };

    publicNamespace.Ajax.putJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error('Invalid url for Ajax putJSONs -> urlParams: ' + urlParams);
        }
        return commonRequestJsons(urlParams, callback, 'PUT');
    };

    publicNamespace.Ajax.deleteJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string') {
            console.error('Invalid arguments for Ajax deleteJSON -> url: ' + url + ', param: ' + param);
        }
        return commonRequestJson(url, 'DELETE', param);
    };

    publicNamespace.Ajax.deleteJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error('Invalid url for Ajax deleteJSONs -> urlParams: ' + urlParams);
        }
        return commonRequestJsons(urlParams, callback, 'DELETE');
    };

    var commonRequestJson = function (url, method, param) {
        //
        if (!url || typeof url !== 'string') {
            console.error('Invalid arguments for Ajax JSON -> url: ' + url + ', param: ' + param);
        }

        var jqAjaxReq = {
            url: url,
            method: method,
            contentType: 'application/json',
            cache: false
        };

        if (param) {
            jqAjaxReq.data = typeof param === 'object' ? JSON.stringify(param) : param;
        }
        return _jQuery.ajax(jqAjaxReq);
    };

    var commonRequestJsons = function (urlParams, callback, method) {
        //
        var callbackCallable = callback && typeof callback === 'function',
            promisses = [];

        for (var i = 0; i < urlParams.length; i ++) {
            //
            var url = urlParams[i].url,
                param = urlParams[i].param,
                promiss;

            promiss = commonRequestJson(url, method, param);
            promisses.push(promiss);
        }

        _jQuery.when.apply(_jQuery, promisses).done( function () {
            //
            var ajaxResults = Array.isArray(arguments[0]) ? arguments : [arguments],
                resultDatas = [];

            for (var i = 0; i < ajaxResults.length; i++) {
                var ajaxResult = ajaxResults[i],
                    resultData = ajaxResult[0];
                resultDatas.push(resultData);
            }

            if (callbackCallable) {
                callback.apply(this, resultDatas);
            }
            return promisses;
        });
    };


    // Script cache object
    var scriptCache = {
        //
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
     * @param param2 Optional, this param is calllback when exists param1
     */
    publicNamespace.Ajax.getScript = function (url, param1, param2) {
        //
        if (!url || typeof url !== 'string') {
            alert('Invalid url for Ajax getScript -> url: ' + url);
        }
        publicNamespace.Ajax.getScripts([url], param1, param2);
    };

    /**
     * Get and execute JavaScript or JSX list
     * <p>
     *     function (array<string> url, function callback [optional])
     *     function (array<string> url, object settings [optional], function callback [optional]
     * </p>
     *
     * @param urlArray
     * @param param1 Optional, this param is callback or settings
     * @param param2 Optional calllback when param1 exists
     */
    publicNamespace.Ajax.getScripts = function (urlArray, param1, param2) {
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
            _jQuery.when.apply(_jQuery, promisses).done(function () {
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


    // Nara commmon constant
    publicNamespace.Const = {};

    publicNamespace.Object.defineConstProperties(publicNamespace.Const, {
        CTX: ','
    });


    NaraCommon = publicNamespace;

})();
