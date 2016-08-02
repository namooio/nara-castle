window["naraLib"] =
/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	/**
	 * Created by hkkang on 2016-06-01.
	 */
	var Nara = __webpack_require__(1);
	var NaraReact = __webpack_require__(3);

	module.exports = {
	  Nara: Nara,
	  NaraReact: NaraReact
	};

/***/ },
/* 1 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	exports.Dom = exports.Url = exports.Ajax = exports.Date = exports.Object = undefined;

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol ? "symbol" : typeof obj; };

	var _jquery = __webpack_require__(2);

	var _jquery2 = _interopRequireDefault(_jquery);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	/**
	 * Created by hkkang on 2016-04-28.
	 */

	var appContextName = 'NaraCommon';

	var objectPublicContext = {};

	// Object util
	(function () {
	    //
	    'use strict';

	    var contextName = appContextName;

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
	        return !object || Object.keys(object).length === 0;
	    };

	    objectPublicContext.deepCopy = function (source) {
	        //
	        if (!source || (typeof source === 'undefined' ? 'undefined' : _typeof(source)) !== 'object') {
	            console.warn('[' + contextName + '] Source for copy is not array or object. -> Object.deepCopy');
	            return;
	        }
	        return deepCopy(source);
	    };

	    var deepCopy = function deepCopy(source) {
	        //
	        var result = void 0;

	        if (!source || !((typeof source === 'undefined' ? 'undefined' : _typeof(source)) === 'object' || Array.isArray(source))) {
	            return source;
	        }
	        if (Array.isArray(source)) {
	            result = [];
	        } else {
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
	})();

	exports.Object = objectPublicContext;


	var datePublicContext = {};

	// Date util
	(function () {
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

	exports.Date = datePublicContext;

	//import { transform as _jsxTransform } from 'babel-core';
	//let _jsxTransform = babel.transform;

	var ajaxPublicContext = {};

	// Ajax util
	(function () {
	    //
	    'use strict';

	    var contextName = appContextName;

	    // TODO: jQuery랑 babel을 사용하고 있으므로 해당 라이브러리(스크립트)가 로드 됐는지 확인 필요

	    var UrlBuilder = function () {
	        //

	        function UrlBuilder() {
	            _classCallCheck(this, UrlBuilder);

	            this.urlAndParams = [];
	        }

	        _createClass(UrlBuilder, [{
	            key: 'addUrl',
	            value: function addUrl(url) {
	                this.urlAndParams.push({ url: url });
	            }
	        }, {
	            key: 'addUrlAndParam',
	            value: function addUrlAndParam(url, param) {
	                this.urlAndParams.push({ url: url, param: param });
	            }
	        }, {
	            key: 'build',
	            value: function build() {
	                return this.urlAndParams;
	            }
	        }]);

	        return UrlBuilder;
	    }();

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
	        console.debug('[' + contextName + '] getJSON');
	        if (!url || typeof url !== 'string') {
	            console.error('[' + contextName + '] Invalid url for Ajax getJSON -> url: ' + url + ', param: ' + param);
	        }
	        return commonRequestJson(url, 'GET', param).pipe(function (jsonResult, status, jqXHR) {
	            return jsonResult;
	        });
	    };

	    ajaxPublicContext.getJSONs = function (urlParams, callback) {
	        //
	        console.debug('[' + contextName + '] getJSONs');
	        if (!urlParams || !Array.isArray(urlParams)) {
	            console.error('[' + contextName + '] Invalid url for Ajax getJSONs -> urlParams: ' + urlParams);
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
	            console.error('[' + contextName + '] Invalid arguments for Ajax postJSON -> url: ' + url + ', param: ' + param);
	        }
	        return commonRequestJson(url, 'POST', param);
	    };

	    ajaxPublicContext.postJSONs = function (urlParams, callback) {
	        //
	        if (!urlParams || !Array.isArray(urlParams)) {
	            console.error('[' + contextName + '] Invalid url for Ajax postJSONs -> urlParams: ' + urlParams);
	        }
	        return commonRequestJsons(urlParams, callback, 'POST');
	    };

	    ajaxPublicContext.putJSON = function (url, param) {
	        //
	        if (!url || typeof url !== 'string' || !param) {
	            console.error('[' + contextName + '] Invalid arguments for Ajax putJSON -> url: ' + url + ', param: ' + param);
	        }
	        return commonRequestJson(url, 'PUT', param);
	    };

	    ajaxPublicContext.putJSONs = function (urlParams, callback) {
	        //
	        if (!urlParams || !Array.isArray(urlParams)) {
	            console.error('[' + contextName + '] Invalid url for Ajax putJSONs -> urlParams: ' + urlParams);
	        }
	        return commonRequestJsons(urlParams, callback, 'PUT');
	    };

	    ajaxPublicContext.deleteJSON = function (url, param) {
	        //
	        if (!url || typeof url !== 'string') {
	            console.error('[' + contextName + '] Invalid arguments for Ajax deleteJSON -> url: ' + url + ', param: ' + param);
	        }
	        return commonRequestJson(url, 'DELETE', param);
	    };

	    ajaxPublicContext.deleteJSONs = function (urlParams, callback) {
	        //
	        if (!urlParams || !Array.isArray(urlParams)) {
	            console.error('[' + contextName + '] Invalid url for Ajax deleteJSONs -> urlParams: ' + urlParams);
	        }
	        return commonRequestJsons(urlParams, callback, 'DELETE');
	    };

	    var commonRequestJson = function commonRequestJson(url, method, param) {
	        //
	        if (!url || typeof url !== 'string') {
	            console.error('[' + contextName + '] Invalid arguments for Ajax JSON -> url: ' + url + ', param: ' + param);
	        }

	        var jqAjaxReq = {
	            url: url,
	            method: method,
	            contentType: 'application/json',
	            cache: false
	        };

	        if (param) {
	            jqAjaxReq.data = (typeof param === 'undefined' ? 'undefined' : _typeof(param)) === 'object' ? JSON.stringify(param) : param;
	        }
	        return _jquery2.default.ajax(jqAjaxReq);
	    };

	    var commonRequestJsons = function commonRequestJsons(urlParams, callback, method) {
	        //
	        var callbackCallable = callback && typeof callback === 'function',
	            promises = [];

	        for (var i = 0; i < urlParams.length; i++) {
	            //
	            var url = urlParams[i].url,
	                param = urlParams[i].param,
	                promise = commonRequestJson(url, method, param);

	            promises.push(promise);
	        }

	        _jquery2.default.when.apply(_jquery2.default, promises).done(function () {
	            //
	            var ajaxResults = Array.isArray(arguments[0]) ? arguments : [arguments],
	                resultDatas = [];

	            for (var _i = 0; _i < ajaxResults.length; _i++) {
	                var ajaxResult = ajaxResults[_i],
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

	    var ScriptCache = function () {
	        //

	        function ScriptCache() {
	            _classCallCheck(this, ScriptCache);

	            this.caches = {};
	        }

	        _createClass(ScriptCache, [{
	            key: 'get',
	            value: function get(url) {
	                var pureUrl = url.split('?')[0];
	                return this.caches[pureUrl];
	            }
	        }, {
	            key: 'add',
	            value: function add(url, script) {
	                var pureUrl = url.split('?')[0];
	                this.caches[pureUrl] = script;
	            }
	        }]);

	        return ScriptCache;
	    }();

	    var scriptCache = new ScriptCache();

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
	    ajaxPublicContext.getScript = function (url) {
	        var param1 = arguments.length <= 1 || arguments[1] === undefined ? { async: false } : arguments[1];
	        var param2 = arguments.length <= 2 || arguments[2] === undefined ? function () {} : arguments[2];

	        //
	        if (!url || typeof url !== 'string') {
	            console.error('[' + contextName + '] Invalid url for Ajax getScript -> url: ' + url);
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
	    ajaxPublicContext.getScripts = function (urls) {
	        var param1 = arguments.length <= 1 || arguments[1] === undefined ? { async: false } : arguments[1];
	        var param2 = arguments.length <= 2 || arguments[2] === undefined ? function () {} : arguments[2];

	        //
	        console.debug('[' + contextName + '] getScripts -> ' + urls);

	        var settings = void 0,
	            callback = void 0;

	        if (!urls || !Array.isArray(urls) || urls.length === 0) {
	            console.error('[' + contextName + '] Invalid url for Ajax getScripts -> urls: ' + urls);
	        }

	        if ((typeof param1 === 'undefined' ? 'undefined' : _typeof(param1)) === 'object' && typeof param2 === 'function') {
	            settings = param1;
	            callback = param2;
	        } else if (typeof param1 === 'function' && param2 === undefined) {
	            settings = {
	                async: false
	            };
	            callback = param1;
	        }

	        var scriptsDiv = document.getElementById('scripts');

	        if (!scriptsDiv) {
	            scriptsDiv = document.createElement('div');
	            scriptsDiv.id = 'scripts';
	            document.getElementsByTagName('head')[0].appendChild(scriptsDiv);
	        }

	        urls.forEach(function (url) {

	            var scriptElement = document.createElement('script');
	            scriptElement.src = url;
	            scriptElement.async = settings.async;

	            scriptsDiv.appendChild(scriptElement);
	        });
	        callback();
	    };

	    ajaxPublicContext.getScripts_deprecated = function (urlArray, param1, param2) {
	        //
	        console.debug('[' + contextName + '] getScripts ');
	        var callback = void 0,
	            callbackCallable = false,
	            settings = { async: true };

	        if (!urlArray || !Array.isArray(urlArray) || urlArray.length === 0) {
	            console.error('[' + contextName + '] Invalid url for Ajax getScripts -> urlArray: ' + urlArray);
	        }

	        if (param1 && typeof param1 === 'function') {
	            callback = param1;
	        } else if (param1 && (typeof param1 === 'undefined' ? 'undefined' : _typeof(param1)) === 'object') {
	            settings = param1;
	            if (param2 && typeof param2 === 'function') {
	                callback = param2;
	            }
	        }

	        if (callback && typeof callback === 'function') {
	            callbackCallable = true;
	        }

	        var promises = [];

	        for (var i = 0; i < urlArray.length; i++) {
	            //
	            var url = urlArray[i],
	                cached = scriptCache.get(url);

	            // Script exists in cache
	            if (cached) {
	                console.info('[' + contextName + '] Execute cached script -> ' + url);

	                try {
	                    new Function(cached)();
	                } catch (e) {
	                    console.error(e);
	                    if (window.babel) {
	                        babel.transform.run(cached);
	                    } else {
	                        console.error('Babel 없음');
	                    }
	                }
	            }
	            // Not exists
	            else {
	                    var promise = _jquery2.default.ajax({
	                        url: url,
	                        method: 'GET',
	                        cache: false,
	                        async: settings.async,
	                        dataType: 'text',
	                        contentType: 'text/plain',
	                        error: function error(result) {
	                            console.error('[' + contextName + '] Failed  get script at Ajax -> url: ' + this.url + ', dataType: ' + this.dataType);
	                            console.log(result);
	                        }
	                    });
	                    promises.push(promise);
	                }
	        }

	        if (promises.length > 0) {
	            _jquery2.default.when.apply(_jquery2.default, promises).then(function () {
	                //
	                var ajaxResults = Array.isArray(arguments[0]) ? arguments : [arguments],
	                    ajaxRequest = Array.isArray(this) ? this : [this];

	                for (var _i2 = 0; _i2 < ajaxResults.length; _i2++) {
	                    var _url = ajaxRequest[_i2].url,
	                        ajaxResult = ajaxResults[_i2],
	                        resultScript = ajaxResult[0];

	                    console.info('[' + contextName + '] Execute script from server -> ' + _url);
	                    scriptCache.add(_url, resultScript);

	                    try {
	                        new Function(resultScript)();
	                    } catch (e) {
	                        console.error(e);
	                        if (window.babel) {
	                            babel.transform.run(resultScript);
	                        } else {
	                            console.error('Babel 없음');
	                        }
	                    }
	                }

	                if (callbackCallable) {
	                    callback();
	                }
	            });
	        } else {
	            if (callbackCallable) {
	                callback();
	            }
	        }
	    };

	    ajaxPublicContext.getHtml = function (url, callback) {
	        //
	        return _jquery2.default.get(url, callback, 'html');
	    };

	    ajaxPublicContext.getScriptWithjQuery = function (url) {
	        console.debug('getScriptWithjQuery: ' + url + ' -> Pav nara-common.js');
	        _jquery2.default.getScript(url);
	    };
	})();

	exports.Ajax = ajaxPublicContext;


	var NaraObject = objectPublicContext;

	var urlPublicContext = {};

	// Nara common url
	(function () {
	    //
	    'use strict';

	    urlPublicContext.getPavilionHashContextPath = function (appContextPath) {
	        //
	        var hashUrls = window.location.hash.split('/'),
	            local = hashUrls.length < 2 || hashUrls[1] !== 'dramas',
	            pavilionContextPath = null;

	        if (local) {
	            pavilionContextPath = '';
	        } else {
	            var dramaId = hashUrls[2],
	                revision = hashUrls[4];

	            if (revision.split('?').length > 1) {
	                revision = revision.split('?')[0];
	            }
	            pavilionContextPath = '/dramas/' + dramaId + '/revisions/' + revision;
	        }
	        return appContextPath ? pavilionContextPath + '/' + appContextPath : pavilionContextPath;
	    };

	    var pavilionContextPath = urlPublicContext.getPavilionHashContextPath(),
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

	exports.Url = urlPublicContext;


	var domPublicContext = {};

	// Nara common dom
	(function () {
	    //
	    var contextName = appContextName;

	    domPublicContext.addTokenAtAjaxSendEvent = function () {
	        var headerTokenName = arguments.length <= 0 || arguments[0] === undefined ? (0, _jquery2.default)('meta[name=_csrf_header]').attr('content') : arguments[0];
	        var tokenValue = arguments.length <= 1 || arguments[1] === undefined ? (0, _jquery2.default)('meta[name=_csrf]').attr('content') : arguments[1];

	        //
	        if (headerTokenName && tokenValue) {
	            (function () {
	                /*
	                 // Using jQuery
	                 jQuery(document).ajaxSend( function(event, xhr) {
	                 xhr.setRequestHeader(headerTokenName, tokenValue);
	                 });
	                 */
	                var originalOpen = XMLHttpRequest.prototype.send;

	                XMLHttpRequest.prototype.send = function (something) {
	                    //
	                    this.setRequestHeader(headerTokenName, tokenValue);
	                    originalOpen.apply(this, arguments);
	                };
	            })();
	        } else {
	            console.warn('[' + contextName + '] Invalid token header name or value -> name: ' + headerTokenName + ', value: ' + tokenValue);
	        }
	    };

	    domPublicContext.getCSRF = function () {
	        //
	        var token = (0, _jquery2.default)('meta[name=_csrf]').attr('content'),
	            header = (0, _jquery2.default)('meta[name=_csrf_header]').attr('content');

	        return _defineProperty({}, header, token);
	    };
	})();

	exports.Dom = domPublicContext;
	exports.default = {
	    Object: objectPublicContext,
	    Ajax: ajaxPublicContext,
	    Date: datePublicContext,
	    Url: urlPublicContext,
	    Dom: domPublicContext
	};

/***/ },
/* 2 */
/***/ function(module, exports) {

	module.exports = externalLib.jQuery;

/***/ },
/* 3 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	exports.File = exports.Modal = undefined;

	var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol ? "symbol" : typeof obj; };

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _reactBootstrap = __webpack_require__(5);

	var _jquery = __webpack_require__(2);

	var _jquery2 = _interopRequireDefault(_jquery);

	var _naraCommon = __webpack_require__(1);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-07-09.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	// Modal
	'use strict';

	var modalPublicContext = {
	    alert: null,
	    confirm: null,
	    openModal: null
	};

	// Define component

	var NaraModal = function (_Component) {
	    _inherits(NaraModal, _Component);

	    _createClass(NaraModal, null, [{
	        key: 'alert',

	        //
	        value: function alert(contentOrParams, handleOk) {
	            //
	            if (typeof modalPublicContext.alert === 'function') {
	                modalPublicContext.alert(contentOrParams, handleOk);
	            }
	        }
	    }, {
	        key: 'confirm',
	        value: function confirm(contentOrParams, handleOk, handleCancel) {
	            //
	            if (typeof modalPublicContext.confirm === 'function') {
	                modalPublicContext.confirm(contentOrParams, handleOk, handleCancel);
	            }
	        }
	    }, {
	        key: 'openModal',
	        value: function openModal(paramsObject) {
	            //
	            if (typeof modalPublicContext.openModal === 'function') {
	                modalPublicContext.openModal(paramsObject);
	            }
	        }
	    }]);

	    function NaraModal(props) {
	        _classCallCheck(this, NaraModal);

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(NaraModal).call(this, props));
	        //


	        _this.state = NaraModal.initialState;

	        _this.handleOk = _this.handleOk.bind(_this);
	        _this.handleCancel = _this.handleCancel.bind(_this);
	        _this._alert = _this._alert.bind(_this);
	        _this._confirm = _this._confirm.bind(_this);
	        _this._openModal = _this._openModal.bind(_this);

	        modalPublicContext.alert = _this._alert;
	        modalPublicContext.confirm = _this._confirm;
	        modalPublicContext.openModal = _this._openModal;
	        return _this;
	    }
	    // event


	    _createClass(NaraModal, [{
	        key: 'handleOk',
	        value: function handleOk() {
	            //
	            var handleOk = this.state.handleOk;

	            if (typeof handleOk === 'function') {
	                handleOk();
	            }
	            this.setState(NaraModal.initialState);
	        }
	    }, {
	        key: 'handleCancel',
	        value: function handleCancel() {
	            //
	            var handleCancel = this.state.handleCancel;

	            if (typeof handleCancel === 'function') {
	                handleCancel();
	            }
	            this.setState(NaraModal.initialState);
	        }
	        // private custom

	    }, {
	        key: '_alert',
	        value: function _alert(contentOrParams, handleOk) {
	            //
	            // Content and handleOk
	            if (contentOrParams && typeof contentOrParams === 'string') {
	                this.setState({
	                    content: contentOrParams,
	                    handleOk: typeof handleOk === 'function' ? handleOk : NaraModal.initialState.handleOk
	                });
	            }
	            // Parameters object
	            else if (contentOrParams && (typeof contentOrParams === 'undefined' ? 'undefined' : _typeof(contentOrParams)) === 'object') {
	                    this._openModal(contentOrParams);
	                } else {
	                    console.error('Invalid alert arguments');
	                }

	            this.setState({ show: true, type: NaraModal.type.ALERT });
	        }
	    }, {
	        key: '_confirm',
	        value: function _confirm(contentOrParams, handleOk, handleCancel) {
	            //
	            // Content and handleOk
	            if (contentOrParams && typeof contentOrParams === 'string') {
	                this.setState({
	                    content: contentOrParams,
	                    handleOk: typeof handleOk === 'function' ? handleOk : NaraModal.initialState.handleOk,
	                    handleCancel: typeof handleCancel === 'function' ? handleCancel : NaraModal.initialState.handleCancel
	                });
	            }
	            // Parameters object
	            else if (contentOrParams && (typeof contentOrParams === 'undefined' ? 'undefined' : _typeof(contentOrParams)) === 'object') {
	                    this._openModal(contentOrParams);
	                } else {
	                    console.error('Invalid confirm arguments');
	                }

	            this.setState({ show: true, type: NaraModal.type.CONFIRM });
	        }
	    }, {
	        key: '_openModal',
	        value: function _openModal(paramsObject) {
	            //
	            var title = paramsObject.title;
	            var content = paramsObject.content;
	            var handleOk = paramsObject.handleOk;
	            var handleCancel = paramsObject.handleCancel;

	            this.setState({
	                content: content,
	                title: typeof title === 'string' ? title : NaraModal.initialState.title,
	                handleOk: typeof handleOk === 'function' ? handleOk : NaraModal.initialState.handleOk,
	                handleCancel: typeof handleCancel === 'function' ? handleCancel : NaraModal.initialState.handleCancel
	            });
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            if (this.state.show !== true) {
	                return null;
	            }
	            return _react2.default.createElement(
	                'div',
	                null,
	                _react2.default.createElement(
	                    'div',
	                    { className: 'modal-container' },
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'modal fade in', id: 'myModal', role: 'dialog', style: { display: 'block' } },
	                        _react2.default.createElement(
	                            'div',
	                            { className: 'modal-dialog' },
	                            _react2.default.createElement(
	                                'div',
	                                { className: 'modal-content' },
	                                _react2.default.createElement(
	                                    'div',
	                                    { className: 'modal-header' },
	                                    _react2.default.createElement(
	                                        'button',
	                                        { type: 'button', className: 'close', 'data-dismiss': 'modal' },
	                                        '×'
	                                    ),
	                                    _react2.default.createElement(
	                                        'h4',
	                                        { className: 'modal-title' },
	                                        this.state.title
	                                    )
	                                ),
	                                _react2.default.createElement(
	                                    'div',
	                                    { className: 'modal-body' },
	                                    _react2.default.createElement(
	                                        'p',
	                                        null,
	                                        this.state.content
	                                    )
	                                ),
	                                _react2.default.createElement(
	                                    'div',
	                                    { className: 'modal-footer' },
	                                    this.state.type === NaraModal.type.ALERT && this.state.options.okUsable === true ? _react2.default.createElement(
	                                        'button',
	                                        { type: 'button', className: 'btn btn-default', 'data-dismiss': 'modal', onClick: this.handleOk },
	                                        'Ok'
	                                    ) : null,
	                                    this.state.type === NaraModal.type.CONFIRM ? _react2.default.createElement(
	                                        'div',
	                                        null,
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-default', 'data-dismiss': 'modal', onClick: this.handleCancel },
	                                            'Cancel'
	                                        ),
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-info', 'data-dismiss': 'modal', onClick: this.handleOk },
	                                            'Ok'
	                                        )
	                                    ) : null
	                                )
	                            )
	                        )
	                    )
	                ),
	                _react2.default.createElement('div', { className: 'modal-backdrop fade in' })
	            );
	        }
	    }]);

	    return NaraModal;
	}(_react.Component);

	NaraModal.initialState = {
	    show: false, // boolean
	    type: null, // enum
	    title: 'Notice', // string
	    content: null, // string or html
	    handleOk: null, // function
	    handleCancel: null, // function
	    options: {
	        okUsable: true
	    }
	};

	NaraModal.type = {
	    ALERT: 'ALERT',
	    CONFIRM: 'CONFIRM',
	    CUSTOM: 'CUSTOM'
	};

	exports.Modal = NaraModal;

	// File

	var File = function (_Component2) {
	    _inherits(File, _Component2);

	    //

	    function File(props) {
	        _classCallCheck(this, File);

	        //
	        return _possibleConstructorReturn(this, Object.getPrototypeOf(File).call(this, props));
	    }

	    _createClass(File, [{
	        key: 'render',
	        value: function render() {
	            //
	            if (this.props.defaultProps === File.mode.DOWNLOAD) {
	                return _react2.default.createElement(FileDownloader, null);
	            } else {
	                return _react2.default.createElement(FileUploader, null);
	            }
	        }
	    }]);

	    return File;
	}(_react.Component);

	File.mode = {
	    DOWNLOAD: 'Download',
	    UPLOAD: 'Upload'
	};

	File.defaultProps = {
	    mode: File.mode.DOWNLOAD
	};

	// Downloader common static component

	var FileDownloader = function (_Component3) {
	    _inherits(FileDownloader, _Component3);

	    _createClass(FileDownloader, null, [{
	        key: 'requestDownload',

	        //
	        value: function requestDownload(fileId, model) {
	            //
	            _naraCommon.Ajax.getJSON(FileDownloader.url.DOWNLOAD_FILE.replace('{naraFileId}', fileId)).done(function (resultNaraFile) {
	                //
	                //model.setState({ naraFile: resultNaraFile, fileDataUrl:  `data:${resultNaraFile.type};base64,` });
	                model.setState({ naraFile: resultNaraFile });

	                console.debug('Download file result -> name: ' + resultNaraFile.name + ', type: ' + resultNaraFile.type + ', size: ' + resultNaraFile.size);
	                if (!resultNaraFile.type) {
	                    console.warn('[NaraFile] Invalid content type of file. -> ' + resultNaraFile.type);
	                }
	            });
	        }
	    }, {
	        key: 'getDownloaderInitailState',
	        value: function getDownloaderInitailState() {
	            //
	            return {
	                naraFile: null // { name, type, size, content }
	            };
	        }
	    }, {
	        key: 'getFileDataUrl',
	        value: function getFileDataUrl(contentType) {
	            //
	            return 'data:' + contentType + ';base64,';
	        }
	    }, {
	        key: 'isNotRenderable',
	        value: function isNotRenderable(model) {
	            //
	            var fileType = void 0;

	            if (!model.state.naraFile) {
	                return true;
	            }

	            fileType = model.state.naraFile.type;
	            if (!fileType || !fileType.includes('image')) {
	                console.warn('[NaraFile Downaloder] Invalid file content type. -> ' + fileType);
	            }
	            return false;
	        }
	    }]);

	    function FileDownloader(props) {
	        _classCallCheck(this, FileDownloader);

	        //
	        return _possibleConstructorReturn(this, Object.getPrototypeOf(FileDownloader).call(this, props));
	    }

	    _createClass(FileDownloader, [{
	        key: 'render',
	        value: function render() {
	            //
	            return null;
	        }
	    }]);

	    return FileDownloader;
	}(_react.Component);

	FileDownloader.url = {
	    //
	    DOWNLOAD_FILE: '/pavilion-api/files/{naraFileId}'
	};

	var ImageDownloader = function (_Component4) {
	    _inherits(ImageDownloader, _Component4);

	    //

	    function ImageDownloader(props) {
	        _classCallCheck(this, ImageDownloader);

	        var _this4 = _possibleConstructorReturn(this, Object.getPrototypeOf(ImageDownloader).call(this, props));
	        //


	        _this4.state = FileDownloader.getDownloaderInitailState();
	        return _this4;
	    }
	    // overriding


	    _createClass(ImageDownloader, [{
	        key: 'componentWillReceiveProps',
	        value: function componentWillReceiveProps(nextProps) {
	            //
	            if (nextProps.fileId) {
	                FileDownloader.requestDownload(nextProps.fileId, this);
	            }
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            if (FileDownloader.isNotRenderable(this)) {
	                return null;
	            }
	            return _react2.default.createElement('img', { src: FileDownloader.getFileDataUrl(this.state.naraFile.type) + this.state.naraFile.content,
	                className: this.props.className,
	                width: this.props.width,
	                height: this.props.height
	            });
	        }
	    }]);

	    return ImageDownloader;
	}(_react.Component);

	ImageDownloader.propTypes = {
	    //
	    fileId: _react.PropTypes.string.isRequired,
	    className: _react.PropTypes.string,
	    width: _react.PropTypes.node,
	    heigth: _react.PropTypes.node
	};
	ImageDownloader.defaultProps = {
	    //
	    fileId: null,
	    className: null,
	    width: null,
	    heigth: null
	};

	File.ImageLoader = ImageDownloader;

	var LinkDownloader = function (_Component5) {
	    _inherits(LinkDownloader, _Component5);

	    //

	    function LinkDownloader(props) {
	        _classCallCheck(this, LinkDownloader);

	        var _this5 = _possibleConstructorReturn(this, Object.getPrototypeOf(LinkDownloader).call(this, props));
	        //


	        _this5.state = FileDownloader.getDownloaderInitailState();
	        return _this5;
	    }
	    // overriding


	    _createClass(LinkDownloader, [{
	        key: 'componentWillReceiveProps',
	        value: function componentWillReceiveProps(nextProps) {
	            //
	            if (nextProps.fileId) {
	                FileDownloader.requestDownload(nextProps.fileId, this);
	            }
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            if (FileDownloader.isNotRenderable(this)) {
	                return null;
	            }

	            var linkName = this.props.linkName || (this.state.naraFile ? this.state.naraFile.name : 'File link');
	            return _react2.default.createElement(
	                'a',
	                { href: FileDownloader.getFileDataUrl(this.state.naraFile.type) + this.state.naraFile.content,
	                    className: this.props.className },
	                linkName
	            );
	        }
	    }]);

	    return LinkDownloader;
	}(_react.Component);

	LinkDownloader.propTypes = {
	    //
	    fileId: _react.PropTypes.string.isRequired,
	    className: _react.PropTypes.string,
	    linkName: _react.PropTypes.string
	};
	LinkDownloader.defaultProps = {
	    //
	    fileId: null,
	    className: null,
	    linkName: null
	};

	File.LinkLoader = LinkDownloader;

	var FileUploader = function (_Component6) {
	    _inherits(FileUploader, _Component6);

	    //

	    function FileUploader(props) {
	        _classCallCheck(this, FileUploader);

	        var _this6 = _possibleConstructorReturn(this, Object.getPrototypeOf(FileUploader).call(this, props));
	        //


	        _this6.state = {
	            fileForm: null, // { files[], dramaId, clubId }
	            processing: false
	        };

	        _this6.fileOnChange = _this6.fileOnChange.bind(_this6);
	        _this6.fileOnSubmit = _this6.fileOnSubmit.bind(_this6);
	        _this6.processUpload = _this6.processUpload.bind(_this6);
	        _this6.requestUpload = _this6.requestUpload.bind(_this6);
	        return _this6;
	    }
	    // overriding


	    _createClass(FileUploader, [{
	        key: 'componentWillReceiveProps',
	        value: function componentWillReceiveProps(nextProps) {
	            //
	            if (nextProps.startUpload === true) {
	                this.processUpload();
	            }
	        }
	        // event

	    }, {
	        key: 'fileOnChange',
	        value: function fileOnChange(event) {
	            //
	            /*
	             const _this = this;
	               [].forEach.call(event.target.files, function (file) {
	             let reader = new FileReader();
	               reader.onload = (fileEvent) => {
	             let files = _this.state.files;
	               files.push({
	             dataUrl: fileEvent.target.result,
	             name: file.name,
	             type: file.type
	             });
	             _this.setState({ files });
	             };
	             reader.readAsDataURL(file);
	             });
	             //*/

	            //*
	            var fileForm = new FormData();

	            [].forEach.call(event.target.files, function (file, index) {
	                fileForm.append('files[' + index + ']', file);
	            });

	            var dramaId = this.props.dramaId;

	            if (dramaId) {
	                fileForm.append('dramaId', dramaId);
	            } else {
	                console.error('[NaraFile] Invalid dramaId -> ' + dramaId);
	                return false;
	            }

	            if (localStorage.getItem('clubId')) {
	                fileForm.append('clubId', localStorage.getItem('clubId'));
	            } else {
	                fileForm.append('clubId', '02-00D');
	            }

	            this.setState({ files: fileForm });
	            //*
	        }
	    }, {
	        key: 'fileOnSubmit',
	        value: function fileOnSubmit(event) {
	            //
	            event.preventDefault();
	            this.processUpload();
	        }
	        // custom

	    }, {
	        key: 'processUpload',
	        value: function processUpload() {
	            //
	            if (this.props.onStartUpload) {
	                var uploadable = this.props.onStartUpload();
	                if (uploadable === false) {
	                    return;
	                }
	            }

	            this.setState({
	                processing: true
	            });

	            var successCallback = function successCallback() {};
	            if (typeof this.props.onSuccessUpload === 'function') {
	                successCallback = this.props.onSuccessUpload;
	            }
	            this.requestUpload(successCallback);
	        }
	        // request

	    }, {
	        key: 'requestUpload',
	        value: function requestUpload(successCallback) {
	            //
	            /*
	             NaraAjax
	             .postJSON('/files', this.state.files)
	             .done(function (result) {
	             console.log('Complete ajax -> ' +  result);
	             });
	             */
	            //jQuery.post('/files', this.state.files, function (result) {
	            //    console.log('Complete ajax -> ' +  result);
	            //});
	            _jquery2.default.ajax({
	                method: 'POST',
	                url: FileUploader.url.UPLOAD_FILE,
	                data: this.state.files,
	                processData: false,
	                contentType: false,
	                success: function (resultFileIds) {
	                    console.log('File ids: ' + resultFileIds);

	                    if (this.props.multiple === true) {
	                        successCallback(resultFileIds);
	                    } else {
	                        successCallback(resultFileIds[0]);
	                    }
	                }.bind(this)
	            });

	            /*
	             const _this = this;
	             let formData = new FormData();
	               this.state.files.forEach( function (file, index) {
	             formData.append(`files[${index}]`, JSON.stringify(file));
	             });
	               const promise = jQuery.ajax({
	             url: '/file-form',
	             type: "POST",
	             data: formData,
	             //enctype: 'multipart/form-data',
	             processData: false,
	             contentType: false,
	             dataType: 'json'
	             });
	               promise.done( function(data) {
	             _this.setState({
	             processing: false,
	             uploadedUri: data.uri
	             });
	             });
	             */
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            if (this.props.multiple === true) {
	                return _react2.default.createElement('input', { type: 'file', className: this.props.className, onChange: this.fileOnChange });
	            } else {
	                return _react2.default.createElement('input', { type: 'file', className: this.props.className, onChange: this.fileOnChange, multiple: 'multiple' });
	            }
	        }
	    }]);

	    return FileUploader;
	}(_react.Component);

	FileUploader.propTypes = {
	    //
	    dramaId: _react.PropTypes.string.isRequired,
	    startUpload: _react.PropTypes.bool.isRequired,
	    multiple: _react.PropTypes.bool,
	    className: _react.PropTypes.string,
	    onStartUpload: _react.PropTypes.func,
	    onSuccessUpload: _react.PropTypes.func.isRequired
	};

	FileUploader.defaultProps = {
	    //
	    dramaId: null,
	    startUpload: null,
	    multiple: false,
	    className: 'file'
	};

	FileUploader.url = {
	    UPLOAD_FILE: '/pavilion-api/files'
	};

	File.Uploader = FileUploader;

	exports.File = File;
	exports.default = {
	    Modal: _reactBootstrap.Modal,
	    File: File
	};

/***/ },
/* 4 */
/***/ function(module, exports) {

	module.exports = externalLib.React;

/***/ },
/* 5 */
/***/ function(module, exports) {

	module.exports = externalLib.ReactBootstrap;

/***/ }
/******/ ]);