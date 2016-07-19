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
	exports.RoleBook = exports.Modal = undefined;

	var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol ? "symbol" : typeof obj; };

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _reactBootstrap = __webpack_require__(5);

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
	        _this.setParams = _this.setParams.bind(_this);

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
	                    this.setParams(contentOrParams);
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
	                    this.setParams(contentOrParams);
	                } else {
	                    console.error('Invalid confirm arguments');
	                }

	            this.setState({ show: true, type: NaraModal.type.CONFIRM });
	        }
	    }, {
	        key: '_openModal',
	        value: function _openModal(paramsObject) {
	            //
	            this.setParams(paramsObject);
	            this.setState({ show: true, type: NaraModal.type.CUSTOM });
	        }
	    }, {
	        key: 'setParams',
	        value: function setParams(paramsObject) {
	            var title = paramsObject.title;
	            var content = paramsObject.content;
	            var handleOk = paramsObject.handleOk;
	            var handleCancel = paramsObject.handleCancel;
	            var options = paramsObject.options;


	            this.setState({
	                content: content,
	                title: typeof title === 'string' ? title : NaraModal.initialState.title,
	                handleOk: typeof handleOk === 'function' ? handleOk : NaraModal.initialState.handleOk,
	                handleCancel: typeof handleCancel === 'function' ? handleCancel : NaraModal.initialState.handleCancel
	            });
	            if (options) {
	                this.setState({
	                    options: {
	                        okUsable: typeof options.okUsable === 'boolean' ? options.okUsable : NaraModal.initialState.options.okUsable
	                    }
	                });
	            }
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
	                                        'h4',
	                                        { className: 'modal-title' },
	                                        this.state.title
	                                    )
	                                ),
	                                _react2.default.createElement(
	                                    'div',
	                                    { className: 'modal-body' },
	                                    this.state.content
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
	                                            { type: 'button', className: 'btn btn-success', 'data-dismiss': 'modal', onClick: this.handleOk },
	                                            'Ok'
	                                        )
	                                    ) : null,
	                                    this.state.type === NaraModal.type.CUSTOM ? _react2.default.createElement(
	                                        'div',
	                                        null,
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-default', 'data-dismiss': 'modal', onClick: this.handleCancel },
	                                            'Cancel'
	                                        ),
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-success', 'data-dismiss': 'modal', onClick: this.handleOk },
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
	/*
	<button type="button" className="close" data-dismiss="modal">×</button>
	*/


	NaraModal.initialState = {
	    show: false, // boolean
	    type: null, // enum
	    title: 'Notice', // string
	    content: null, // string or html
	    handleOk: null, // function
	    handleCancel: null, // function
	    options: {
	        okUsable: true // boolean
	    }
	};

	NaraModal.type = {
	    ALERT: 'ALERT',
	    CONFIRM: 'CONFIRM',
	    CUSTOM: 'CUSTOM'
	};

	exports.Modal = NaraModal;

	// RoleBook

	'use strict';

	// Define component

	var RoleBook = function (_Component2) {
	    _inherits(RoleBook, _Component2);

	    _createClass(RoleBook, null, [{
	        key: 'setContextPath',

	        //
	        value: function setContextPath(contextPath) {
	            //
	            RoleBook.contextPath = contextPath;
	            RoleBook.setUrl();
	        }
	    }, {
	        key: 'setUrl',
	        value: function setUrl() {
	            //
	            RoleBook.url = {
	                //
	                FIND_ROLES_OF_PLAYER: RoleBook.contextPath + '/stage/rolebook/players/{playerId}/roles?castingId={castingId}',
	                FIND_PLAYERS: RoleBook.contextPath + '/stage/players?pavilionId={pavilionId}&castingId={castingId}'
	            };

	            RolePlayerMappingPop.url = {
	                FIND_ROLES: RoleBook.contextPath + '/stage/roles',
	                SAVE_ROLE_BOOK: RoleBook.contextPath + '/stage/rolebook'
	            };
	        }
	    }, {
	        key: 'getRoles',
	        value: function getRoles() {
	            //
	            return RoleBook.rolesOfPlayer;
	        }
	    }, {
	        key: 'getRoleNames',
	        value: function getRoleNames() {
	            //
	            var roleNames = [];

	            if (RoleBook.rolesOfPlayer) {
	                RoleBook.rolesOfPlayer.forEach(function (role) {
	                    roleNames.push(role.name);
	                });
	            }
	            return roleNames;
	        }
	    }, {
	        key: 'hasRole',
	        value: function hasRole(roleName) {
	            //
	            var result = false;

	            if (RoleBook.rolesOfPlayer) {
	                result = RoleBook.rolesOfPlayer.some(function (role) {
	                    return roleName === role.name;
	                });
	            }
	            return result;
	        }
	    }]);

	    function RoleBook(props) {
	        _classCallCheck(this, RoleBook);

	        var _this2 = _possibleConstructorReturn(this, Object.getPrototypeOf(RoleBook).call(this, props));
	        //


	        _this2.state = {
	            pavilionId: null,
	            castingId: null,
	            playerId: null,
	            players: [],
	            roles: [],
	            roleState: {
	                unconfigured: false,
	                admin: false,
	                user: false,
	                modifiable: false
	            },
	            popupState: {
	                rolePlayerMapping: false,
	                alertUnconfigured: false,
	                alertRoles: false
	            }
	        };

	        _this2.roleCheckClick = _this2.roleCheckClick.bind(_this2);
	        _this2.rolePlayerMappingPopOnHide = _this2.rolePlayerMappingPopOnHide.bind(_this2);
	        _this2.rolesBtnOnClick = _this2.rolesBtnOnClick.bind(_this2);
	        _this2.modifyRoleBookBtnOnClick = _this2.modifyRoleBookBtnOnClick.bind(_this2);
	        _this2.isUnconfiguredAndAdmin = _this2.isUnconfiguredAndAdmin.bind(_this2);
	        _this2.isUnconfiguredAndUser = _this2.isUnconfiguredAndUser.bind(_this2);
	        _this2.isModifiableAndAdmin = _this2.isModifiableAndAdmin.bind(_this2);
	        _this2.requestFindRolesOfPlayer = _this2.requestFindRolesOfPlayer.bind(_this2);
	        _this2.requestFindPlayers = _this2.requestFindPlayers.bind(_this2);
	        return _this2;
	    }
	    // overriding


	    _createClass(RoleBook, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            //
	            var pavilionId = document.getElementsByName('pavilionId')[0].content,
	                castingId = document.getElementsByName('castingId')[0].content,
	                playerId = document.getElementsByName('playerId')[0].content;

	            this.setState({ pavilionId: pavilionId, castingId: castingId, playerId: playerId });

	            if (!RoleBook.contextPath) {
	                RoleBook.setContextPath(_naraCommon.Url.getPavilionHashContextPath());
	            }

	            if (this.props.init === true) {
	                this.requestFindRolesOfPlayer(pavilionId, castingId, playerId);
	            }
	        }
	    }, {
	        key: 'componentWillUpdate',
	        value: function componentWillUpdate(nextProps, nextState) {
	            var _this3 = this;

	            if (nextState.init === true || nextProps.init === true) {
	                if (nextState.roleState.unconfigured && nextState.roleState.user) {
	                    NaraModal.alert({
	                        title: 'Sorry!',
	                        content: '해당 드라마의 역할 설정이 되지 않아 이용할 수 없습니다.',
	                        options: { okUsable: false }
	                    });
	                } else if (nextState.roleState.unconfigured && nextState.roleState.admin) {
	                    console.log('Configuration popup');
	                } else if (nextState.popupState.alertRoles) {
	                    (function () {
	                        var rolesElement = '';
	                        _this3.state.roles.forEach(function (role, index) {
	                            rolesElement.concat(role.name + ' : ' + role.description + '}');
	                        });

	                        NaraModal.alert({
	                            title: 'Roles',
	                            content: rolesElement,
	                            handleOk: _this3.rolesBtnOnClick
	                        });
	                    })();
	                }
	            }
	        }
	        // event

	    }, {
	        key: 'roleCheckClick',
	        value: function roleCheckClick() {
	            this.setState({ init: true });
	            this.requestFindRolesOfPlayer(this.state.pavilionId, this.state.castingId, this.state.playerId);
	        }
	    }, {
	        key: 'rolePlayerMappingPopOnHide',
	        value: function rolePlayerMappingPopOnHide() {
	            var popupState = this.state.popupState;
	            popupState.rolePlayerMapping = false;
	            this.setState({ popupState: popupState });
	        }
	    }, {
	        key: 'rolesBtnOnClick',
	        value: function rolesBtnOnClick() {
	            var popupState = this.state.popupState;
	            popupState.alertRoles = false;
	            this.setState({ popupState: popupState });
	        }
	    }, {
	        key: 'modifyRoleBookBtnOnClick',
	        value: function modifyRoleBookBtnOnClick() {
	            var roleState = this.state.roleState;
	            roleState.unconfigured = true;

	            this.setState({ roleState: roleState });
	        }
	        // custom

	    }, {
	        key: 'isUnconfiguredAndAdmin',
	        value: function isUnconfiguredAndAdmin() {
	            return this.state.roleState.unconfigured && this.state.roleState.admin;
	        }
	    }, {
	        key: 'isUnconfiguredAndUser',
	        value: function isUnconfiguredAndUser() {
	            return this.state.roleState.unconfigured && this.state.roleState.user;
	        }
	    }, {
	        key: 'isModifiableAndAdmin',
	        value: function isModifiableAndAdmin() {
	            return this.state.roleState.modifiable && this.state.roleState.admin;
	        }
	        // request

	    }, {
	        key: 'requestFindRolesOfPlayer',
	        value: function requestFindRolesOfPlayer(pavilionId, castingId, playerId) {
	            //
	            _naraCommon.Ajax.getJSON(RoleBook.url.FIND_ROLES_OF_PLAYER.replace('{castingId}', castingId).replace('{playerId}', playerId)).done(function (roles) {
	                if (roles && roles.length > 0) {
	                    var popupState = this.state.popupState,
	                        roleState = this.state.roleState;

	                    popupState.alertRoles = true;
	                    roleState.modifiable = true;

	                    this.setState({ roles: roles, popupState: popupState });
	                    RoleBook.rolesOfPlayer = roles;

	                    if (this.props.onInit) {
	                        this.props.onInit();
	                    }
	                } else {
	                    var _roleState = this.state.roleState;
	                    _roleState.unconfigured = true;

	                    this.setState({ roleState: _roleState });
	                }
	                this.requestFindPlayers(pavilionId, castingId, playerId);
	            }.bind(this));
	        }
	    }, {
	        key: 'requestFindPlayers',
	        value: function requestFindPlayers(pavilionId, castingId, playerId) {
	            //
	            _naraCommon.Ajax.getJSON(RoleBook.url.FIND_PLAYERS.replace('{pavilionId}', pavilionId).replace('{castingId}', castingId)).done(function (players) {
	                //
	                var administrant = players.some(function (player) {
	                    return player.id === playerId && player.leader === true;
	                });

	                if (administrant) {
	                    var roleState = this.state.roleState,
	                        popupState = this.state.popupState;

	                    roleState.admin = true;
	                    popupState.rolePlayerMapping = true;
	                    this.setState({ roleState: roleState, popupState: popupState, players: players });
	                } else {
	                    var _roleState2 = this.state.roleState,
	                        _popupState = this.state.popupState;

	                    _roleState2.user = true;
	                    _popupState.alertUnconfigured = true;
	                    this.setState({ roleState: _roleState2, popupState: _popupState });
	                }
	            }.bind(this));
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //

	            return _react2.default.createElement(
	                'li',
	                null,
	                this.props.init !== true ? _react2.default.createElement(
	                    'a',
	                    { href: 'javascript:', onClick: this.roleCheckClick },
	                    'RoleCheck'
	                ) : null,
	                this.isModifiableAndAdmin() === true ? _react2.default.createElement(
	                    'a',
	                    { href: 'javascript:', onClick: this.modifyRoleBookBtnOnClick },
	                    'Modify role book'
	                ) : null,
	                this.isUnconfiguredAndAdmin() === true ? _react2.default.createElement(RolePlayerMappingPop, {
	                    castingId: this.state.castingId,
	                    players: this.state.players,
	                    displayable: this.state.popupState.rolePlayerMapping,
	                    onHide: this.rolePlayerMappingPopOnHide,
	                    onSaveSuccess: this.props.onSaveSuccess
	                }) : null
	            );
	        }
	    }]);

	    return RoleBook;
	}(_react.Component);

	/*
	{ this.isUnconfiguredAndUser() === true ?
	    <Modal
	        show={this.state.popupState.alertUnconfigured}>
	        <Modal.Header>
	            <Modal.Title id="modal-title">Sorry!</Modal.Title>
	        </Modal.Header>
	        <Modal.Body>
	            <h4>해당 드라마의 역할 설정이 되지 않아 이용할 수 없습니다.</h4>
	        </Modal.Body>
	    </Modal> : null
	}
	*/
	/*
	<div className="modal-container">
	    <Modal show={this.state.popupState.alertRoles} container={this} aria-labelledby="contained-modal-title">
	        <Modal.Header>
	            <Modal.Title id="modal-roles-of-player-title">Roles</Modal.Title>
	        </Modal.Header>
	        <Modal.Body>
	            { this.state.roles.map( function (role, index) {
	                return (<h4 key={index}>{role.name} : {role.description}</h4>);
	            })}
	        </Modal.Body>
	        <Modal.Footer>
	            <Button onClick={this.rolesBtnOnClick}>Close</Button>
	        </Modal.Footer>
	    </Modal>
	</div>
	*/

	RoleBook.contextPath = null;
	RoleBook.rolesOfPlayer = RoleBook.rolesOfPlayer || [];

	RoleBook.propTypes = {
	    init: _react.PropTypes.bool,
	    onInit: _react.PropTypes.func,
	    onSaveSuccess: _react.PropTypes.func
	};
	RoleBook.defaultProps = {};

	var RolePlayerMappingPop = function (_Component3) {
	    _inherits(RolePlayerMappingPop, _Component3);

	    //

	    function RolePlayerMappingPop(props) {
	        _classCallCheck(this, RolePlayerMappingPop);

	        var _this4 = _possibleConstructorReturn(this, Object.getPrototypeOf(RolePlayerMappingPop).call(this, props));
	        //


	        _this4.state = {
	            roles: [],
	            rolePlayers: [],
	            successPopup: false
	        };

	        _this4.roleCheckChange = _this4.roleCheckChange.bind(_this4);
	        _this4.saveRoleBookBtnOnClick = _this4.saveRoleBookBtnOnClick.bind(_this4);
	        _this4.successPopCloseBtnOnClick = _this4.successPopCloseBtnOnClick.bind(_this4);
	        _this4.requestRolePlayer = _this4.requestRolePlayer.bind(_this4);
	        _this4.requestSaveRoleBook = _this4.requestSaveRoleBook.bind(_this4);
	        return _this4;
	    }
	    // overriding


	    _createClass(RolePlayerMappingPop, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            this.requestRolePlayer();
	        }
	    }, {
	        key: 'componentWillUpdate',
	        value: function componentWillUpdate(nextProps, nextState) {

	            if (nextState.rolePlayers.length > 0) {

	                var content = _react2.default.createElement(
	                    'table',
	                    { className: 'table table-stri1ed table-hover' },
	                    _react2.default.createElement(
	                        'thead',
	                        null,
	                        _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-3' },
	                                'Player'
	                            ),
	                            nextState.roles.map(function (role, index) {
	                                return _react2.default.createElement(
	                                    'th',
	                                    { key: index },
	                                    role.name
	                                );
	                            })
	                        )
	                    ),
	                    _react2.default.createElement(
	                        'tbody',
	                        null,
	                        this.props.players.map(function (player, playerIndex) {
	                            return _react2.default.createElement(
	                                'tr',
	                                { key: playerIndex },
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    player.name
	                                ),
	                                nextState.rolePlayers[playerIndex].map(function (roleCheck, roleIndex) {
	                                    return _react2.default.createElement(
	                                        'td',
	                                        { key: roleIndex },
	                                        _react2.default.createElement('input', { type: 'checkbox', checked: roleCheck, onChange: this.roleCheckChange.bind(this, playerIndex, roleIndex) })
	                                    );
	                                }.bind(this))
	                            );
	                        }.bind(this))
	                    )
	                );

	                NaraModal.openModal({
	                    title: 'Role player configuration',
	                    content: content,
	                    handleOk: this.saveRoleBookBtnOnClick
	                });
	            }
	        }
	        // event

	    }, {
	        key: 'roleCheckChange',
	        value: function roleCheckChange(playerIndex, roleIndex) {
	            //
	            var rolePlayers = this.state.rolePlayers;
	            rolePlayers[playerIndex][roleIndex] = !rolePlayers[playerIndex][roleIndex];

	            this.setState({ rolePlayers: rolePlayers });
	        }
	    }, {
	        key: 'saveRoleBookBtnOnClick',
	        value: function saveRoleBookBtnOnClick() {
	            //
	            var roleBook = {
	                castingId: this.props.castingId,
	                rolePlayers: []
	            };

	            this.state.rolePlayers.forEach(function (roleCheckList, index) {
	                //
	                var player = this.props.players[index];

	                var rolePlayer = {
	                    playerId: player.id,
	                    name: player.name,
	                    roles: []
	                };

	                roleCheckList.forEach(function (roleCheck, index) {
	                    //
	                    if (roleCheck === false) {
	                        return;
	                    }
	                    var role = this.state.roles[index];
	                    rolePlayer.roles.push(role);
	                }.bind(this));

	                roleBook.rolePlayers.push(rolePlayer);
	            }.bind(this));

	            this.requestSaveRoleBook(roleBook);
	        }
	    }, {
	        key: 'successPopCloseBtnOnClick',
	        value: function successPopCloseBtnOnClick() {
	            this.setState({ successPopup: false });
	        }
	        // request

	    }, {
	        key: 'requestRolePlayer',
	        value: function requestRolePlayer() {
	            //
	            _naraCommon.Ajax.getJSON(RolePlayerMappingPop.url.FIND_ROLES).done(function (roles) {
	                //
	                var roleCheckList = [];
	                roleCheckList.length = roles.length;
	                roleCheckList.fill(false);

	                var rolePlayers = [];

	                for (var index in this.props.players) {
	                    rolePlayers[index] = _naraCommon.Object.deepCopy(roleCheckList);
	                }

	                this.setState({ roles: roles, rolePlayers: rolePlayers });
	            }.bind(this));
	        }
	    }, {
	        key: 'requestSaveRoleBook',
	        value: function requestSaveRoleBook(roleBook) {
	            //
	            _naraCommon.Ajax.postJSON(RolePlayerMappingPop.url.SAVE_ROLE_BOOK, roleBook).done(function () {
	                NaraModal.alert('RoleBook 저장이 완료 되었습니다.');
	                //this.props.onHide();
	                //this.setState({ successPopup: true});
	                //
	                //if (this.props.onSaveSuccess) {
	                //    this.props.onSaveSuccess();
	                //}
	            }.bind(this));
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var existsPlayerCheckList = this.state.rolePlayers.length > 0;
	            return null;
	        }
	    }]);

	    return RolePlayerMappingPop;
	}(_react.Component);

	/*
	return (
	    <section>
	        <Modal
	            bsSize="large"
	            aria-labelledby="contained-modal-title-lg"
	            show={this.props.displayable} >
	            <Modal.Header>
	                <Modal.Title id="contained-modal-title-lg">Role player</Modal.Title>
	            </Modal.Header>
	            <Modal.Body>
	                { existsPlayerCheckList ?
	                    <table className="table table-stri1ed table-hover">
	                        <thead>
	                        <tr>
	                            <th className="col-md-3">Player</th>
	                            {
	                                this.state.roles.map(function (role, index) {
	                                    return (<th key={index}>{role.name}</th>);
	                                })
	                            }
	                        </tr>
	                        </thead>
	                        <tbody>
	                        {
	                            this.props.players.map(function (player, playerIndex) {
	                                return (
	                                    <tr key={playerIndex}>
	                                        <td>{player.name}</td>
	                                        {this.state.rolePlayers[playerIndex].map(function (roleCheck, roleIndex) {
	                                            return (
	                                                <td key={roleIndex}><input type="checkbox" checked={roleCheck} onChange={this.roleCheckChange.bind(this, playerIndex, roleIndex)}/></td>
	                                            );
	                                        }.bind(this))}
	                                    </tr>
	                                );
	                            }.bind(this))
	                        }
	                        </tbody>
	                    </table>
	                    :
	                    <h4>역할 목록이 없습니다.</h4>
	                }
	            </Modal.Body>
	            <Modal.Footer>
	                { existsPlayerCheckList ?
	                    <Button onClick={this.saveRoleBookBtnOnClick}>Save</Button>
	                    : null
	                }
	            </Modal.Footer>
	        </Modal>
	        <Modal show={this.state.successPopup}>
	            <Modal.Header>
	                <Modal.Title id="modal-success-title">Success</Modal.Title>
	            </Modal.Header>
	            <Modal.Body>
	                RoleBook 저장이 완료 되었습니다.
	            </Modal.Body>
	            <Modal.Footer>
	                <Button onClick={this.successPopCloseBtnOnClick}>Close</Button>
	            </Modal.Footer>
	        </Modal>
	    </section>
	);
	*/

	RolePlayerMappingPop.propTypes = {
	    //
	    castingId: _react.PropTypes.string.isRequired,
	    players: _react.PropTypes.array.isRequired,
	    displayable: _react.PropTypes.bool.isRequired,
	    onHide: _react.PropTypes.func.isRequired
	};

	exports.RoleBook = RoleBook;
	exports.default = {
	    RoleBook: RoleBook,
	    Modal: _reactBootstrap.Modal
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