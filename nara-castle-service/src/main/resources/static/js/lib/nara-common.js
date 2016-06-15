/**
 * Created by hkkang on 2016-04-28.
 */

window.naraContextNamespace = 'NaraCommon';

//window.__nara = {
//    namespace: naraNamespace,
//    [naraNamespace]: {}
//};
//window[naraNamespace] = window.__nara[naraNamespace];

let objectPublicNamespace = {};

// Object util
( function () {
    //
    'use strict';

    let naraNamespace = naraContextNamespace;
    //let publicNamespace = {};


    objectPublicNamespace.defineConstProperty = function (obj, name, value) {
        //
        Object.defineProperty(obj, name, {
            value: value,
            writable: false,
            configurable: false,
            enumerable: true
        });
    };

    objectPublicNamespace.defineConstProperties = function (obj, nameValues) {
        //
        Object.keys(nameValues).forEach(function (name) {
            objectPublicNamespace.defineConstProperty(obj, name, nameValues[name]);
        });
    };

    objectPublicNamespace.isEmpty = function (object) {
        //
        return (!object || Object.keys(object).length === 0);
    };

    objectPublicNamespace.deepCopy = function (source) {
        //
        if (!source || typeof source !== 'object') {
            console.warn(`Source is not array or object. > ${naraNamespace}.Object.deepCopy`);
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

    //__nara[__nara.namespace].Object = publicNamespace;
})();
export { objectPublicNamespace as Object };


let datePublicNamespace = {};

// Date util
( function () {
    //
    'use strict';

    //let publicNamespace = {};

    datePublicNamespace.parseToString = function (date) {
        //
        if (!date) {
            return null;
        }
        return new Date(date).toLocaleString();
    };

    //__nara[__nara.namespace].Date = publicNamespace;
})();
export { datePublicNamespace as Date };


import * as _jQuery from 'jquery';
//import { transform as _jsxTransform } from 'babel-core';
//let babel = require('babel-core');
//let _jsxTransform = babel.transform;

let ajaxPublicNamespace = {};

// Ajax util
( function () {
    //
    'use strict';

    let naraNamespace = naraContextNamespace;
    //let publicNamespace = {};

    // TODO: jQuery랑 babel을 사용하고 있으므로 해당 라이브러리(스크립트)가 로드 됐는지 확인 필요
    // Import external library
    //let _jQuery = jQuery,
    //    _jsxTransform = babel.transform;



    class UrlBuilder {
        //
        constructor() {
            this.urlAndParams = [];
        }
        addUrl(url) {
            this.urlAndParams.push({url: url});
        }
        addUrlAndParam(url, param) {
            this.urlAndParams.push({url: url, param: param});
        }
        build() {
            return this.urlAndParams;
        }
    }

    ajaxPublicNamespace.createUrlBuilder = function () {
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
    ajaxPublicNamespace.getJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string') {
            console.error(`Invalid url for ${naraNamespace} Ajax getJSON -> url: ${url}, param: ${param}`);
        }
        return commonRequestJson(url, 'GET', param).pipe(function (jsonResult) {
            return jsonResult;
        });
    };

    ajaxPublicNamespace.getJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error(`Invalid url for ${naraNamespace} Ajax getJSONs -> urlParams: ${urlParams}`);
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
    ajaxPublicNamespace.postJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string' || !param) {
            console.error(`Invalid arguments for ${naraNamespace} Ajax postJSON -> url: ${url}, param: ${param}`);
        }
        return commonRequestJson(url, 'POST', param);
    };

    ajaxPublicNamespace.postJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error(`Invalid url for ${naraNamespace} Ajax postJSONs -> urlParams: ${urlParams}`);
        }
        return commonRequestJsons(urlParams, callback, 'POST');
    };

    ajaxPublicNamespace.putJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string' || !param) {
            console.error(`Invalid arguments for ${naraNamespace} Ajax putJSON -> url: ${url}, param: ${param}`);
        }
        return commonRequestJson(url, 'PUT', param);
    };

    ajaxPublicNamespace.putJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error(`Invalid url for ${naraNamespace} Ajax putJSONs -> urlParams: ${urlParams}`);
        }
        return commonRequestJsons(urlParams, callback, 'PUT');
    };

    ajaxPublicNamespace.deleteJSON = function (url, param) {
        //
        if (!url || typeof url !== 'string') {
            console.error(`Invalid arguments for ${naraNamespace} Ajax deleteJSON -> url: ${url}, param: ${param}`);
        }
        return commonRequestJson(url, 'DELETE', param);
    };

    ajaxPublicNamespace.deleteJSONs = function (urlParams, callback) {
        //
        if (!urlParams || !Array.isArray(urlParams)) {
            console.error(`Invalid url for ${naraNamespace} Ajax deleteJSONs -> urlParams: ${urlParams}`);
        }
        return commonRequestJsons(urlParams, callback, 'DELETE');
    };

    let commonRequestJson = function (url, method, param) {
        //
        if (!url || typeof url !== 'string') {
            console.error(`Invalid arguments for ${naraNamespace} Ajax JSON -> url: ${url}, param: ${param}`);
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
            promises = [];

        for (let i = 0; i < urlParams.length; i++) {
            //
            let url = urlParams[i].url,
                param = urlParams[i].param,
                promise = commonRequestJson(url, method, param);

            promises.push(promise);
        }

        _jQuery.when.apply(_jQuery, promises).done(function () {
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
    ajaxPublicNamespace.getScript = function (url, param1, param2) {
        //
        if (!url || typeof url !== 'string') {
            console.error(`Invalid url for ${naraNamespace} Ajax getScript -> url: ${url}`);
        }
        ajaxPublicNamespace.getScripts([url], param1, param2);
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
    ajaxPublicNamespace.getScripts = function (urlArray, param1, param2) {
        //
        let callback,
            callbackCallable = false,
            settings = { async: true };

        if (!urlArray || !Array.isArray(urlArray) || urlArray.length === 0) {
            console.error(`Invalid url for ${naraNamespace} Ajax getScripts -> urlArray: ${urlArray}`);
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
                console.info(`Execute cached script -> ${url}`);
                //_jsxTransform.run(cached.script);
            }
            // Not exists
            else {
                let promise = _jQuery.ajax({
                    url: url,
                    method: 'GET',
                    cache: false,
                    async: settings.async,
                    dataType: 'text',
                    contentType: 'text/plain',
                    error: function (result) {
                        console.error(`Failed  get script at ${naraNamespace} Ajax -> url: ${this.url}, dataType: ${this.dataType}`);
                        console.log(result);
                    }
                });
                promises.push(promise);
            }
        }

        if (promises.length > 0) {
            _jQuery.when.apply(_jQuery, promises).then(function () {
                //
                let ajaxResults = Array.isArray(arguments[0]) ? arguments : [arguments],
                    ajaxRequest = Array.isArray(this) ? this : [this];

                for (let i = 0; i < ajaxResults.length; i++) {
                    let url = ajaxRequest[i].url,
                        ajaxResult = ajaxResults[i],
                        resultScript = ajaxResult[0];

                    console.info(`Execute script from server -> ${url}`);
                    scriptCache.add(url, resultScript);
                    //_jsxTransform.run(resultScript);
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

    ajaxPublicNamespace.getHtml = function (url, callback) {
        //
        return _jQuery.get(url, callback, 'html');
    };

    //__nara[naraNamespace].Ajax = publicNamespace;
})();
export { ajaxPublicNamespace as Ajax };


import { Object as NaraObject } from 'app/lib/nara-common';
console.dir(NaraObject);
let constantPublicNamespace = {};

// Nara common constant
( function () {
    //
    'use strict';

    //let publicNamespace = {};

    //let naraObject = __nara[__nara.namespace].Object;


    //naraObject.defineConstProperties(constantPublicNamespace, {
    objectPublicNamespace.defineConstProperties(constantPublicNamespace, {
        CTX: ','
    });


    //__nara[__nara.namespace].Const = publicNamespace;
})();
export { constantPublicNamespace as Constant };

export default {
    Object: objectPublicNamespace,
    Ajax: ajaxPublicNamespace,
    Date: datePublicNamespace,
    Constant: constantPublicNamespace
};