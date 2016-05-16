/**
 * Created by hkkang on 2016-04-28.
 */
let NaraCommon = {};

// Object util
( function () {
    //
    'use strict';

    let publicNamespace = {};


    publicNamespace.defineConstProperty = function (obj, name, value) {
        //
        Object.defineProperty(obj, name, {
            value: value,
            writable: false,
            configurable: false,
            enumerable: true
        });
    };

    publicNamespace.defineConstProperties = function (obj, nameValues) {
        //
        Object.keys(nameValues).forEach(function (name) {
            publicNamespace.defineConstProperty(obj, name, nameValues[name]);
        });
    };

    publicNamespace.isEmpty = function (object) {
        //
        return (!object || Object.keys(object).length === 0);
    };

    publicNamespace.deepCopy = function (source) {
        //
        if (!source || typeof source !== 'object') {
            console.warn("Source is not array or object. > NaraCommon.Object.deepCopy");
            return;
        }
        return deepCopy(source);
    };

    let deepCopy = function (source) {
        //
        let result;

        if (!source || !(typeof source === 'object' || Array.isArray(source))) {
            return source;
        }
        if (Array.isArray(source)) {
            result = [];
        }
        else {
            result = {};
        }

        for (let property in source) {
            if (source.hasOwnProperty(property)) {
                let sourcePropValue = source[property];
                result[property] = deepCopy(sourcePropValue);
            }
        }
        return result;
    };

    NaraCommon.Object = publicNamespace;
})();

// Date util
( function () {
    //
    'use strict';

    //let publicNamespace = {};
    let publicNamespace = {};

    publicNamespace.parseToString = function (date) {
        //
        if (!date) {
            return null;
        }
        return new Date(date).toLocaleString();
    };

    NaraCommon.Date = publicNamespace;
})();

// Ajax util
( function () {
    //
    'use strict';

    var publicNamespace = {};

    // TODO: jQuery랑 babel을 사용하고 있으므로 해당 라이브러리(스크립트)가 로드 됐는지 확인 필요
    // Import external library
    let _jQuery = jQuery,
        _jsxTransform = babel.transform;

    /*
     let UrlBuilder = function () {
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
     */

    class UrlBuilder {
        //
        constructor() {
            this.urlAndParams = [];
        }
        addUrl(url) {
            this.urlAndParams.push({url: url});
        }
        addUrlAndParam(url, parm) {
            this.urlAndParams.push({url: url, param: param});
        }
        build() {
            return this.urlAndParams;
        }
    }

    publicNamespace.createUrlBuilder = function () {
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
    publicNamespace.getJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string') {
            console.error('Invalid url for Ajax getJSON -> url: ' + url + ', param: ' + param);
        }
        return commonRequestJson(url, 'GET', param).pipe(function (jsonResult, status, jqXHR) {
            return jsonResult;
        });
    };

    publicNamespace.getJSONs = function (urlParams, callback) {
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
    publicNamespace.postJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string' || !param) {
            console.error('Invalid arguments for Ajax postJSON -> url: ' + url + ', param: ' + param);
        }
        return commonRequestJson(url, 'POST', param);
    };

    publicNamespace.postJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error('Invalid url for Ajax postJSONs -> urlParams: ' + urlParams);
        }
        return commonRequestJsons(urlParams, callback, 'POST');
    };

    publicNamespace.putJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string' || !param) {
            console.error('Invalid arguments for Ajax putJSON -> url: ' + url + ', param: ' + param);
        }
        return commonRequestJson(url, 'PUT', param);
    };

    publicNamespace.putJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error('Invalid url for Ajax putJSONs -> urlParams: ' + urlParams);
        }
        return commonRequestJsons(urlParams, callback, 'PUT');
    };

    publicNamespace.deleteJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string') {
            console.error('Invalid arguments for Ajax deleteJSON -> url: ' + url + ', param: ' + param);
        }
        return commonRequestJson(url, 'DELETE', param);
    };

    publicNamespace.deleteJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error('Invalid url for Ajax deleteJSONs -> urlParams: ' + urlParams);
        }
        return commonRequestJsons(urlParams, callback, 'DELETE');
    };

    let commonRequestJson = function (url, method, param) {
        //
        if (!url || typeof url !== 'string') {
            console.error('Invalid arguments for Ajax JSON -> url: ' + url + ', param: ' + param);
        }

        let jqAjaxReq = {
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

    let commonRequestJsons = function (urlParams, callback, method) {
        //
        let callbackCallable = callback && typeof callback === 'function',
            promisses = [];

        for (let i = 0; i < urlParams.length; i++) {
            //
            let url = urlParams[i].url,
                param = urlParams[i].param,
                promiss;

            promiss = commonRequestJson(url, method, param);
            promisses.push(promiss);
        }

        _jQuery.when.apply(_jQuery, promisses).done(function () {
            //
            let ajaxResults = Array.isArray(arguments[0]) ? arguments : [arguments],
                resultDatas = [];

            for (let i = 0; i < ajaxResults.length; i++) {
                let ajaxResult = ajaxResults[i],
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
    /*
     let scriptCache = {
     //
     caches : { },
     add: function (url, script) {
     this.caches[url.split('?')[0]] = script;
     },
     get: function (url) {
     return this.caches[url.split('?')[0]];
     }
     };
     */
    class ScriptCache {
        //
        constructor() {
            this.caches = {};
        }

        get(url) {
            return this.caches[url.split('?')[0]];
        }

        add(url, script) {
            this.caches[url.split('?')[0]] = script;
        }
    }

    let scriptCache = new ScriptCache();


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
    publicNamespace.getScript = function (url, param1, param2) {
        //
        if (!url || typeof url !== 'string') {
            alert('Invalid url for Ajax getScript -> url: ' + url);
        }
        publicNamespace.getScripts([url], param1, param2);
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
    publicNamespace.getScripts = function (urlArray, param1, param2) {
        //
        let settings,
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

        let promisses = [];

        for (let i = 0; i < urlArray.length; i++) {
            //
            let url = urlArray[i],
                cached = scriptCache.get(url);

            // Script exists in cache
            if (cached) {
                console.info('Execute cached script');
                _jsxTransform.run(cached.script);
            }
            // Not exists
            else {
                let promiss = _jQuery.ajax({
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
                let ajaxResults = Array.isArray(arguments[0]) ? arguments : [arguments],
                    ajaxRequest = Array.isArray(this) ? this : [this];

                for (let i = 0; i < ajaxResults.length; i++) {
                    let url = ajaxRequest[i].url,
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

    NaraCommon.Ajax = publicNamespace;
})();


// Nara commmon constant
( function () {
    //
    'use strict';

    let publicNamespace = {};

    let naraObject = NaraCommon.Object;


    naraObject.defineConstProperties(publicNamespace, {
        CTX: ','
    });


    NaraCommon.Const = publicNamespace;

})();