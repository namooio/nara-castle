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
	var NaraRoleBook = __webpack_require__(3);

	module.exports = {
	  Nara: Nara,
	  NaraRoleBook: NaraRoleBook
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
	    ajaxPublicContext.getScript = function (url, param1, param2) {
	        //
	        console.debug('[' + contextName + '] getScript ');
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
	    ajaxPublicContext.getScripts = function (urlArray, param1, param2) {
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
	})();

	exports.Ajax = ajaxPublicContext;


	var NaraObject = objectPublicContext;

	var urlPublicContext = {};

	// Nara common url
	(function () {
	    //
	    'use strict';

	    urlPublicContext.getPavilionContextPath = function (appContextPath) {
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

	    var pavilionContextPath = urlPublicContext.getPavilionContextPath(),
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

	    domPublicContext.getCSRF = function () {
	        //
	        var token = (0, _jquery2.default)('meta[name=_csrf]').attr('content'),
	            header = (0, _jquery2.default)('meta[name=_csrf_header]').attr('content');

	        return _defineProperty({}, header, token);
	    };
	    domPublicContext.addTokenAtAjaxSendEvent = function (headerTokenName, tokenValue) {
	        //
	        var header = (0, _jquery2.default)('meta[name=_csrf_header]').attr('content'),
	            token = (0, _jquery2.default)('meta[name=_csrf]').attr('content');

	        if (header && token) {
	            (0, _jquery2.default)(document).ajaxSend(function (event, xhr) {
	                xhr.setRequestHeader(header, token);
	            });
	        } else {
	            console.warn('[' + contextName + '] Invalid token header name or value -> name: ' + header + ', value: ' + token);
	        }
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

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _reactBootstrap = __webpack_require__(5);

	var _naraCommon = __webpack_require__(1);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-05.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	// Define component

	var RoleBook = function (_Component) {
	    _inherits(RoleBook, _Component);

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

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(RoleBook).call(this, props));
	        //


	        _this.state = {
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

	        _this.roleCheckClick = _this.roleCheckClick.bind(_this);
	        _this.rolePlayerMappingPopOnHide = _this.rolePlayerMappingPopOnHide.bind(_this);
	        _this.rolesBtnOnClick = _this.rolesBtnOnClick.bind(_this);
	        _this.modifyRoleBookBtnOnClick = _this.modifyRoleBookBtnOnClick.bind(_this);
	        _this.isUnconfiguredAndAdmin = _this.isUnconfiguredAndAdmin.bind(_this);
	        _this.isUnconfiguredAndUser = _this.isUnconfiguredAndUser.bind(_this);
	        _this.isModifiableAndAdmin = _this.isModifiableAndAdmin.bind(_this);
	        _this.requestFindRolesOfPlayer = _this.requestFindRolesOfPlayer.bind(_this);
	        _this.requestFindPlayers = _this.requestFindPlayers.bind(_this);
	        return _this;
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
	                RoleBook.contextPath = _naraCommon.Url.getPavilionContextPath();
	                RoleBook.setUrl();
	            }

	            if (this.props.init === true) {
	                this.requestFindRolesOfPlayer(pavilionId, castingId, playerId);
	            }
	        }
	        // event

	    }, {
	        key: 'roleCheckClick',
	        value: function roleCheckClick() {
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
	                this.props.init === true ? _react2.default.createElement(
	                    'a',
	                    { href: 'javascript:', onClick: this.roleCheckClick },
	                    'RoleCheck'
	                ) : null,
	                _react2.default.createElement(
	                    'a',
	                    { href: 'javascript:', onClick: this.roleCheckClick },
	                    'RoleCheck'
	                ),
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
	                }) : null,
	                this.isUnconfiguredAndUser() === true ? _react2.default.createElement(
	                    _reactBootstrap.Modal,
	                    {
	                        show: this.state.popupState.alertUnconfigured },
	                    _react2.default.createElement(
	                        _reactBootstrap.Modal.Header,
	                        null,
	                        _react2.default.createElement(
	                            _reactBootstrap.Modal.Title,
	                            { id: 'modal-title' },
	                            'Sorry!'
	                        )
	                    ),
	                    _react2.default.createElement(
	                        _reactBootstrap.Modal.Body,
	                        null,
	                        _react2.default.createElement(
	                            'h4',
	                            null,
	                            '해당 드라마의 역할 설정이 되지 않아 이용할 수 없습니다.'
	                        )
	                    )
	                ) : null,
	                _react2.default.createElement(
	                    _reactBootstrap.Modal,
	                    { show: this.state.popupState.alertRoles },
	                    _react2.default.createElement(
	                        _reactBootstrap.Modal.Header,
	                        null,
	                        _react2.default.createElement(
	                            _reactBootstrap.Modal.Title,
	                            { id: 'modal-roles-of-player-title' },
	                            'Roles'
	                        )
	                    ),
	                    _react2.default.createElement(
	                        _reactBootstrap.Modal.Body,
	                        null,
	                        this.state.roles.map(function (role, index) {
	                            return _react2.default.createElement(
	                                'h4',
	                                { key: index },
	                                role.name,
	                                ' : ',
	                                role.description
	                            );
	                        })
	                    ),
	                    _react2.default.createElement(
	                        _reactBootstrap.Modal.Footer,
	                        null,
	                        _react2.default.createElement(
	                            _reactBootstrap.Button,
	                            { onClick: this.rolesBtnOnClick },
	                            'Close'
	                        )
	                    )
	                )
	            );
	        }
	    }]);

	    return RoleBook;
	}(_react.Component);

	RoleBook.contextPath = null;
	RoleBook.rolesOfPlayer = RoleBook.rolesOfPlayer || [];

	RoleBook.propTypes = {
	    init: _react.PropTypes.bool,
	    onInit: _react.PropTypes.func,
	    onSaveSuccess: _react.PropTypes.func
	};
	RoleBook.defaultProps = {};

	var RolePlayerMappingPop = function (_Component2) {
	    _inherits(RolePlayerMappingPop, _Component2);

	    //

	    function RolePlayerMappingPop(props) {
	        _classCallCheck(this, RolePlayerMappingPop);

	        var _this2 = _possibleConstructorReturn(this, Object.getPrototypeOf(RolePlayerMappingPop).call(this, props));
	        //


	        _this2.state = {
	            roles: [],
	            rolePlayers: [],
	            successPopup: false
	        };

	        _this2.roleCheckChange = _this2.roleCheckChange.bind(_this2);
	        _this2.saveRoleBookBtnOnClick = _this2.saveRoleBookBtnOnClick.bind(_this2);
	        _this2.successPopCloseBtnOnClick = _this2.successPopCloseBtnOnClick.bind(_this2);
	        _this2.requestRolePlayer = _this2.requestRolePlayer.bind(_this2);
	        _this2.requestSaveRoleBook = _this2.requestSaveRoleBook.bind(_this2);
	        return _this2;
	    }
	    // overriding


	    _createClass(RolePlayerMappingPop, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            this.requestRolePlayer();
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
	                this.props.onHide();
	                this.setState({ successPopup: true });

	                if (this.props.onSaveSuccess) {
	                    this.props.onSaveSuccess();
	                }
	            }.bind(this));
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var existsPlayerCheckList = this.state.rolePlayers.length > 0;

	            return _react2.default.createElement(
	                'section',
	                null,
	                _react2.default.createElement(
	                    _reactBootstrap.Modal,
	                    {
	                        bsSize: 'large',
	                        'aria-labelledby': 'contained-modal-title-lg',
	                        show: this.props.displayable },
	                    _react2.default.createElement(
	                        _reactBootstrap.Modal.Header,
	                        null,
	                        _react2.default.createElement(
	                            _reactBootstrap.Modal.Title,
	                            { id: 'contained-modal-title-lg' },
	                            'Role player'
	                        )
	                    ),
	                    _react2.default.createElement(
	                        _reactBootstrap.Modal.Body,
	                        null,
	                        existsPlayerCheckList ? _react2.default.createElement(
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
	                                    this.state.roles.map(function (role, index) {
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
	                                        this.state.rolePlayers[playerIndex].map(function (roleCheck, roleIndex) {
	                                            return _react2.default.createElement(
	                                                'td',
	                                                { key: roleIndex },
	                                                _react2.default.createElement('input', { type: 'checkbox', checked: roleCheck, onChange: this.roleCheckChange.bind(this, playerIndex, roleIndex) })
	                                            );
	                                        }.bind(this))
	                                    );
	                                }.bind(this))
	                            )
	                        ) : _react2.default.createElement(
	                            'h4',
	                            null,
	                            '역할 목록이 없습니다.'
	                        )
	                    ),
	                    _react2.default.createElement(
	                        _reactBootstrap.Modal.Footer,
	                        null,
	                        existsPlayerCheckList ? _react2.default.createElement(
	                            _reactBootstrap.Button,
	                            { onClick: this.saveRoleBookBtnOnClick },
	                            'Save'
	                        ) : null
	                    )
	                ),
	                _react2.default.createElement(
	                    _reactBootstrap.Modal,
	                    { show: this.state.successPopup },
	                    _react2.default.createElement(
	                        _reactBootstrap.Modal.Header,
	                        null,
	                        _react2.default.createElement(
	                            _reactBootstrap.Modal.Title,
	                            { id: 'modal-success-title' },
	                            'Success'
	                        )
	                    ),
	                    _react2.default.createElement(
	                        _reactBootstrap.Modal.Body,
	                        null,
	                        'RoleBook 저장이 완료 되었습니다.'
	                    ),
	                    _react2.default.createElement(
	                        _reactBootstrap.Modal.Footer,
	                        null,
	                        _react2.default.createElement(
	                            _reactBootstrap.Button,
	                            { onClick: this.successPopCloseBtnOnClick },
	                            'Close'
	                        )
	                    )
	                )
	            );
	        }
	    }]);

	    return RolePlayerMappingPop;
	}(_react.Component);

	RolePlayerMappingPop.propTypes = {
	    //
	    castingId: _react.PropTypes.string.isRequired,
	    players: _react.PropTypes.array.isRequired,
	    displayable: _react.PropTypes.bool.isRequired,
	    onHide: _react.PropTypes.func.isRequired
	};

	exports.default = RoleBook;

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