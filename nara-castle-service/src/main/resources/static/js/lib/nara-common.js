/**
 * Created by hkkang on 2016-04-28.
 */

let appContextName = 'NaraCommon';


let objectPublicContext = {};

// Object util
( function () {
    //
    'use strict';

    const contextName = appContextName;


    objectPublicContext.defineConstProperty = function (obj, name, value) {
        //
        Object.defineProperty(obj, name, {
            value: value,
            writable: false,
            configurable: false,
            enumerable: true
        });
    };

    objectPublicContext.defineConstProperties = function (obj, nameValues) {
        //
        Object.keys(nameValues).forEach(function (name) {
            objectPublicContext.defineConstProperty(obj, name, nameValues[name]);
        });
    };

    objectPublicContext.isEmpty = function (object) {
        //
        return (!object || Object.keys(object).length === 0);
    };

    objectPublicContext.deepCopy = function (source) {
        //
        if (!source || typeof source !== 'object') {
            console.warn(`[${contextName}] Source for copy is not array or object. -> Object.deepCopy`);
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

})();

export { objectPublicContext as Object };



let datePublicContext = {};

// Date util
( function () {
    //
    'use strict';

    datePublicContext.parseToString = function (date) {
        //
        if (!date) {
            return null;
        }
        return new Date(date).toLocaleString();
    };

})();

export { datePublicContext as Date };



import jQuery from 'jquery';
//import { transform as _jsxTransform } from 'babel-core';
//let _jsxTransform = babel.transform;

let ajaxPublicContext = {};

// Ajax util
( function () {
    //
    'use strict';

    const contextName = appContextName;

    // TODO: jQuery랑 babel을 사용하고 있으므로 해당 라이브러리(스크립트)가 로드 됐는지 확인 필요


    class UrlBuilder {
        //
        constructor() {
            this.urlAndParams = [];
        }
        addUrl(url) {
            this.urlAndParams.push({ url: url });
        }
        addUrlAndParam(url, param) {
            this.urlAndParams.push({ url: url, param: param });
        }
        build() {
            return this.urlAndParams;
        }
    }

    ajaxPublicContext.createUrlBuilder = function () {
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
    ajaxPublicContext.getJSON = function (url, param) {
        //
        console.debug(`[${contextName}] getJSON`);
        if (!url || typeof url !== 'string') {
            console.error(`[${contextName}] Invalid url for Ajax getJSON -> url: ${url}, param: ${param}`);
        }
        return commonRequestJson(url, 'GET', param).pipe(function (jsonResult, status, jqXHR) {
            return jsonResult;
        });
    };

    ajaxPublicContext.getJSONs = function (urlParams, callback) {
        //
        console.debug(`[${contextName}] getJSONs`);
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error(`[${contextName}] Invalid url for Ajax getJSONs -> urlParams: ${urlParams}`);
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
    ajaxPublicContext.postJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string' || !param) {
            console.error(`[${contextName}] Invalid arguments for Ajax postJSON -> url: ${url}, param: ${param}`);
        }
        return commonRequestJson(url, 'POST', param);
    };

    ajaxPublicContext.postJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error(`[${contextName}] Invalid url for Ajax postJSONs -> urlParams: ${urlParams}`);
        }
        return commonRequestJsons(urlParams, callback, 'POST');
    };

    ajaxPublicContext.putJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string' || !param) {
            console.error(`[${contextName}] Invalid arguments for Ajax putJSON -> url: ${url}, param: ${param}`);
        }
        return commonRequestJson(url, 'PUT', param);
    };

    ajaxPublicContext.putJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error(`[${contextName}] Invalid url for Ajax putJSONs -> urlParams: ${urlParams}`);
        }
        return commonRequestJsons(urlParams, callback, 'PUT');
    };

    ajaxPublicContext.deleteJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string') {
            console.error(`[${contextName}] Invalid arguments for Ajax deleteJSON -> url: ${url}, param: ${param}`);
        }
        return commonRequestJson(url, 'DELETE', param);
    };

    ajaxPublicContext.deleteJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error(`[${contextName}] Invalid url for Ajax deleteJSONs -> urlParams: ${urlParams}`);
        }
        return commonRequestJsons(urlParams, callback, 'DELETE');
    };

    let commonRequestJson = function (url, method, param) {
        //
        if (!url || typeof url !== 'string') {
            console.error(`[${contextName}] Invalid arguments for Ajax JSON -> url: ${url}, param: ${param}`);
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
        return jQuery.ajax(jqAjaxReq);
    };

    let commonRequestJsons = function (urlParams, callback, method) {
        //
        let callbackCallable = callback && typeof callback === 'function',
            promises = [];

        for (let i = 0; i < urlParams.length; i++) {
            //
            let url = urlParams[i].url,
                param = urlParams[i].param,
                promise = commonRequestJson(url, method, param);

            promises.push(promise);
        }

        jQuery.when.apply(jQuery, promises).done(function () {
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
            return promises;
        });
    };


    // Script cache object
    class ScriptCache {
        //
        constructor() {
            this.caches = {};
        }
        get(url) {
            var pureUrl = url.split('?')[0];
            return this.caches[pureUrl];
        }
        add(url, script) {
            var pureUrl = url.split('?')[0];
            this.caches[pureUrl] = script;
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
     * @param param2 Optional, this param is callback when exists param1
     */
    ajaxPublicContext.getScript = function (url, param1 = { async: false }, param2 = function () {}) {
        //
        if (!url || typeof url !== 'string') {
            console.error(`[${contextName}] Invalid url for Ajax getScript -> url: ${url}`);
        }
        ajaxPublicContext.getScripts([url], param1, param2);
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
     * @param param2 Optional callback when param1 exists
     */
    ajaxPublicContext.getScripts = function (urls, param1 = { async: false }, param2 = function () {}) {
        //
        console.debug(`[${contextName}] getScripts -> ${urls}`);

        let settings,
            callback;

        if (!urls || !Array.isArray(urls) || urls.length === 0) {
            console.error(`[${contextName}] Invalid url for Ajax getScripts -> urls: ${urls}`);
        }

        if (typeof param1 === 'object' && typeof param2 === 'function') {
            settings = param1;
            callback = param2;
        }
        else if (typeof param1 === 'function' && param2 === undefined) {
            settings = {
                async: false
            };
            callback = param1;
        }

        let scriptsDiv = document.getElementById('scripts');

        if (!scriptsDiv) {
            scriptsDiv = document.createElement('div');
            scriptsDiv.id = 'scripts';
            document.getElementsByTagName('head')[0].appendChild(scriptsDiv);
        }

        urls.forEach( function (url) {

            let scriptElement = document.createElement('script');
            scriptElement.src = url;
            scriptElement.async = settings.async;

            scriptsDiv.appendChild(scriptElement);
        });
        callback();
    };


    ajaxPublicContext.getScripts_deprecated = function (urlArray, param1, param2) {
        //
        console.debug(`[${contextName}] getScripts `);
        let callback,
            callbackCallable = false,
            settings = { async: true };

        if (!urlArray || !Array.isArray(urlArray) || urlArray.length === 0) {
            console.error(`[${contextName}] Invalid url for Ajax getScripts -> urlArray: ${urlArray}`);
        }

        if (param1 && typeof param1 === 'function') {
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

        let promises = [];

        for (let i = 0; i < urlArray.length; i++) {
            //
            let url = urlArray[i],
                cached = scriptCache.get(url);

            // Script exists in cache
            if (cached) {
                console.info(`[${contextName}] Execute cached script -> ${url}`);

                try {
                    new Function(cached)();
                } catch (e) {
                    console.error(e);
                    if (window.babel) {
                        babel.transform.run(cached);
                    }
                    else {
                        console.error('Babel 없음');
                    }
                }
            }
            // Not exists
            else {
                let promise = jQuery.ajax({
                    url: url,
                    method: 'GET',
                    cache: false,
                    async: settings.async,
                    dataType: 'text',
                    contentType: 'text/plain',
                    error: function (result) {
                        console.error(`[${contextName}] Failed  get script at Ajax -> url: ${this.url}, dataType: ${this.dataType}`);
                        console.log(result);
                    }
                });
                promises.push(promise);
            }
        }

        if (promises.length > 0) {
            jQuery.when.apply(jQuery, promises).then(function () {
                //
                let ajaxResults = Array.isArray(arguments[0]) ? arguments : [arguments],
                    ajaxRequest = Array.isArray(this) ? this : [this];

                for (let i = 0; i < ajaxResults.length; i++) {
                    let url = ajaxRequest[i].url,
                        ajaxResult = ajaxResults[i],
                        resultScript = ajaxResult[0];

                    console.info(`[${contextName}] Execute script from server -> ${url}`);
                    scriptCache.add(url, resultScript);

                    try {
                        new Function(resultScript)();
                    } catch (e) {
                        console.error(e);
                        if (window.babel) {
                            babel.transform.run(resultScript);
                        }
                        else {
                            console.error('Babel 없음');
                        }
                    }
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

    ajaxPublicContext.getHtml = function (url, callback) {
        //
        return jQuery.get(url, callback, 'html');
    };

    ajaxPublicContext.getScriptWithjQuery = function (url) {
        console.debug(`getScriptWithjQuery: ${url} -> Pav nara-common.js`);
        jQuery.getScript(url);
    }

})();

export { ajaxPublicContext as Ajax };



const NaraObject = objectPublicContext;

let urlPublicContext = {};

// Nara common url
( function () {
    //
    'use strict';

    urlPublicContext.getPavilionHashContextPath = function (appContextPath) {
        //
        let hashUrls = window.location.hash.split('/'),
            local = (hashUrls.length < 2 || hashUrls[1] !== 'dramas'),
            pavilionContextPath = null;

        if (local) {
            pavilionContextPath = '';
        }
        else {
            let dramaId = hashUrls[2],
                revision = hashUrls[4];

            if (revision.split('?').length > 1) {
                revision = revision.split('?')[0];
            }
            pavilionContextPath = `/dramas/${dramaId}/revisions/${revision}`;
        }
        return appContextPath ? `${pavilionContextPath}/${appContextPath}` : pavilionContextPath;
    };


    let pavilionContextPath = urlPublicContext.getPavilionHashContextPath(),
        PAV_CTX = {};

    NaraObject.defineConstProperties(PAV_CTX, {
        api: pavilionContextPath,
        res: pavilionContextPath,
        hash: pavilionContextPath
    });
    NaraObject.defineConstProperties(urlPublicContext, {
        PAV_CTX: PAV_CTX
    });

})();

export { urlPublicContext as Url };



let domPublicContext = {};

// Nara common dom
( function () {
    //
    const contextName = appContextName;


    domPublicContext.addTokenAtAjaxSendEvent = function (headerTokenName = jQuery('meta[name=_csrf_header]').attr('content'),
                                                         tokenValue = jQuery('meta[name=_csrf]').attr('content')) {
        //
        if (headerTokenName && tokenValue) {
            /*
             // Using jQuery
             jQuery(document).ajaxSend( function(event, xhr) {
             xhr.setRequestHeader(headerTokenName, tokenValue);
             });
             */
            let originalOpen = XMLHttpRequest.prototype.send;

            XMLHttpRequest.prototype.send = function(something) {
                //
                this.setRequestHeader(headerTokenName, tokenValue);
                originalOpen.apply(this, arguments);
            };
        }
        else {
            console.warn(`[${contextName}] Invalid token header name or value -> name: ${headerTokenName}, value: ${tokenValue}`);
        }
    };

    domPublicContext.getCSRF = function () {
        //
        let token = jQuery('meta[name=_csrf]').attr('content'),
            header = jQuery('meta[name=_csrf_header]').attr('content');

        return {
            [header]: token
        };
    };

})();

export { domPublicContext as Dom };


export default {
    Object: objectPublicContext,
    Ajax: ajaxPublicContext,
    Date: datePublicContext,
    Url: urlPublicContext,
    Dom: domPublicContext
};