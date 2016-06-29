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

	var _castleCommon = __webpack_require__(1);

	var CastleCommon = _interopRequireWildcard(_castleCommon);

	var _castleModel = __webpack_require__(8);

	var _castleModel2 = _interopRequireDefault(_castleModel);

	var _main = __webpack_require__(9);

	var _main2 = _interopRequireDefault(_main);

	var _appReactRouter = __webpack_require__(13);

	var CastleReactRouter = _interopRequireWildcard(_appReactRouter);

	var _error = __webpack_require__(15);

	var _error2 = _interopRequireDefault(_error);

	var _topMenu = __webpack_require__(10);

	var _topMenu2 = _interopRequireDefault(_topMenu);

	var _list = __webpack_require__(16);

	var _list2 = _interopRequireDefault(_list);

	var _detailTab = __webpack_require__(17);

	var _detailTab2 = _interopRequireDefault(_detailTab);

	var _basic = __webpack_require__(18);

	var _basic2 = _interopRequireDefault(_basic);

	var _nameBook = __webpack_require__(19);

	var _nameBook2 = _interopRequireDefault(_nameBook);

	var _phoneBook = __webpack_require__(20);

	var _phoneBook2 = _interopRequireDefault(_phoneBook);

	var _emailBook = __webpack_require__(21);

	var _emailBook2 = _interopRequireDefault(_emailBook);

	var _addressBook = __webpack_require__(22);

	var _addressBook2 = _interopRequireDefault(_addressBook);

	var _accountBook = __webpack_require__(23);

	var _accountBook2 = _interopRequireDefault(_accountBook);

	var _metroBook = __webpack_require__(25);

	var _metroBook2 = _interopRequireDefault(_metroBook);

	var _stateBook = __webpack_require__(24);

	var _stateBook2 = _interopRequireDefault(_stateBook);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } else { var newObj = {}; if (obj != null) { for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) newObj[key] = obj[key]; } } newObj.default = obj; return newObj; } }

/***/ },
/* 1 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	exports.Dom = exports.Constant = undefined;

	var _nara = __webpack_require__(2);

	var _naraRoleBook = __webpack_require__(3);

	var _naraRoleBook2 = _interopRequireDefault(_naraRoleBook);

	var _castleCommon = __webpack_require__(1);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	var constantPublicContext = {}; /**
	                                 * Created by hkkang on 2016-04-05.
	                                 */

	var domPublicContext = {};

	(function () {
	    //
	    'use strict';

	    // Castle app constant

	    var PAV_CTX = {};

	    _nara.Object.defineConstProperties(PAV_CTX, {
	        root: _nara.Url.getPavilionContextPath(),
	        api: _nara.Url.getPavilionContextPath() + '/api',
	        res: _nara.Url.getPavilionContextPath() + '/resource',
	        hash: _nara.Url.getPavilionContextPath()
	    });
	    _nara.Object.defineConstProperties(constantPublicContext, {
	        PAV_CTX: PAV_CTX
	    });

	    // Dom
	    domPublicContext.getCastleMainDom = function () {
	        //
	        return document.getElementById('castle-drama');
	    };
	})();

	exports.Constant = constantPublicContext;
	exports.Dom = domPublicContext;
	exports.default = {
	    Constant: constantPublicContext,
	    Dom: domPublicContext
	};


	// Initialize
	(function () {
	    //
	    'use strict';

	    // Security token

	    _nara.Dom.addTokenAtAjaxSendEvent();

	    // RoleBook set context path
	    _naraRoleBook2.default.setContextPath(_castleCommon.Constant.PAV_CTX.root);
	})();

/***/ },
/* 2 */
/***/ function(module, exports) {

	module.exports = naraLib.Nara;

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

	var _naraCommon = __webpack_require__(6);

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
	        _this.requestRolesOfPlayer = _this.requestRolesOfPlayer.bind(_this);
	        _this.requestPlayers = _this.requestPlayers.bind(_this);
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
	            //this.requestRolesOfPlayer(castingId, playerId);
	        }
	        // event

	    }, {
	        key: 'roleCheckClick',
	        value: function roleCheckClick() {
	            this.requestRolesOfPlayer(this.state.pavilionId, this.state.castingId, this.state.playerId);
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
	        key: 'requestRolesOfPlayer',
	        value: function requestRolesOfPlayer(pavilionId, castingId, playerId) {
	            //
	            _naraCommon.Ajax.getJSON(RoleBook.url.FIND_ROLES_OF_PLAYER.replace('{castingId}', castingId).replace('{playerId}', playerId)).done(function (roles) {
	                if (roles) {
	                    var popupState = this.state.popupState,
	                        roleState = this.state.roleState;

	                    popupState.alertRoles = true;
	                    roleState.modifiable = true;

	                    this.setState({ roles: roles, popupState: popupState });
	                    RoleBook.rolesOfPlayer = roles;
	                } else {
	                    var _roleState = this.state.roleState;
	                    _roleState.unconfigured = true;

	                    this.setState({ roleState: _roleState });
	                }
	                this.requestPlayers(pavilionId, castingId, playerId);
	            }.bind(this));
	        }
	    }, {
	        key: 'requestPlayers',
	        value: function requestPlayers(pavilionId, castingId, playerId) {
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
	                    onHide: this.rolePlayerMappingPopOnHide
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

/***/ },
/* 6 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	exports.Dom = exports.Url = exports.Ajax = exports.Date = exports.Object = undefined;

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol ? "symbol" : typeof obj; };

	var _jquery = __webpack_require__(7);

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
	                    new Function(cached.script)();
	                } catch (e) {
	                    console.error(e);
	                    if (window.babel) {
	                        babel.transform.run(cached.script);
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
/* 7 */
/***/ function(module, exports) {

	module.exports = externalLib.jQuery;

/***/ },
/* 8 */
/***/ function(module, exports) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
	/**
	 * Created by hkkang on 2016-04-12.
	 */

	var CastleModel = {};

	(function () {
	    //
	    'use strict';

	    CastleModel = {
	        //
	        buttons: {
	            search: { KOR: '검색', USA: 'Search' },
	            save: { KOR: '저장', USA: 'Save' },
	            modify: { KOR: '수정', USA: 'Modify' },
	            remove: { KOR: '삭제', USA: 'Remove' },
	            cancel: { KOR: '취소', USA: 'Cancel' },
	            complete: { KOR: '입력완료', USA: 'Complete' },
	            add: { KOR: '추가', USA: 'Add' }
	        },
	        enums: {
	            state: {
	                Ready: { name: 'Ready', KOR: '준비', USA: 'Ready' },
	                Open: { name: 'Open', KOR: '사용', USA: 'Open' },
	                Suspended: { name: 'Suspended', KOR: '중단', USA: 'Suspended' },
	                Closed: { name: 'Closed', KOR: '닫힘', USA: 'Closed' }
	            },
	            modifiableState: {
	                Open: { name: 'Open', KOR: '사용', USA: 'Open' },
	                Suspended: { name: 'Suspended', KOR: '중단', USA: 'Suspended' }
	            },
	            locale: {
	                ko: { name: 'ko', KOR: '대한민국', USA: 'Republic of Korea' },
	                ko_KR: { name: 'ko_KR', KOR: '대한민국', USA: 'Republic of Korea' },
	                us: { name: 'us', KOR: '미국', USA: 'United States of America' },
	                en_US: { name: 'en_US', KOR: '미국', USA: 'United States of America' }
	            },
	            language: {
	                ko: { name: 'ko', KOR: '한국어', USA: 'Korean' },
	                kor: { name: 'kor', KOR: '한국어', USA: 'Korean' },
	                en: { name: 'en', KOR: '영어', USA: 'English' },
	                eng: { name: 'eng', KOR: '영어', USA: 'English' }
	            },
	            emailType: {
	                Business: { name: 'Business', KOR: '업무용', USA: 'Business' },
	                Private: { name: 'Private', KOR: '개인용', USA: 'Private' }
	            },
	            verified: {
	                true: { KOR: '확인완료', USA: 'Verified' },
	                false: { KOR: '미확인', USA: 'Unverified' }
	            },
	            addressStyle: {
	                Korean: { KOR: '대한민국', USA: 'Korean' },
	                US: { KOR: '미국', USA: 'USA' }
	            }
	        }
	    };
	})();

	exports.default = CastleModel;

/***/ },
/* 9 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _topMenu = __webpack_require__(10);

	var _topMenu2 = _interopRequireDefault(_topMenu);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-05.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	// Define component

	var MainComponent = function (_Component) {
	    _inherits(MainComponent, _Component);

	    //

	    function MainComponent(props) {
	        _classCallCheck(this, MainComponent);

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(MainComponent).call(this, props));
	        //


	        _this.state = {
	            lang: 'KOR'
	        };

	        _this.getLanguage = _this.getLanguage.bind(_this);
	        _this.changeLanguage = _this.changeLanguage.bind(_this);
	        _this.initLanguage = _this.initLanguage.bind(_this);
	        return _this;
	    }

	    _createClass(MainComponent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            //
	            this.initLanguage();
	        }
	        // custom

	    }, {
	        key: 'getLanguage',
	        value: function getLanguage() {
	            return this.state.lang;
	        }
	    }, {
	        key: 'changeLanguage',
	        value: function changeLanguage(lang) {
	            this.setState({ lang: lang });
	            MainComponent.lang = lang;
	        }
	    }, {
	        key: 'initLanguage',
	        value: function initLanguage() {
	            //
	            var lang = void 0;

	            if (navigator.language) lang = navigator.language;else if (navigator.browserLanguage) lang = navigator.browserLanguage;else if (navigator.systemLanguage) lang = navigator.systemLanguage;else if (navigator.userLanguage) lang = navigator.userLanguage;

	            if (lang === 'ko') {
	                lang = 'KOR';
	            } else if (lang === 'en') {
	                lang = 'USA';
	            }

	            this.changeLanguage(lang);
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            return _react2.default.createElement(
	                'div',
	                null,
	                _react2.default.createElement(
	                    'header',
	                    null,
	                    _react2.default.createElement(_topMenu2.default, { changeLanguage: this.changeLanguage, getLanguage: this.getLanguage })
	                ),
	                _react2.default.createElement(
	                    'section',
	                    null,
	                    this.props.children
	                )
	            );
	        }
	    }]);

	    return MainComponent;
	}(_react.Component);

	MainComponent.lang = 'KOR';

	exports.default = MainComponent;

/***/ },
/* 10 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _reactRouter = __webpack_require__(11);

	var _naraRoleBook = __webpack_require__(12);

	var _naraRoleBook2 = _interopRequireDefault(_naraRoleBook);

	var _castleCommon = __webpack_require__(1);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-05.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	// Define attributes name
	var CastleTopMenuModel = {
	    //
	    attrs: {
	        castles: { KOR: '목록', USA: 'List' }
	    }
	};

	/**
	 * Castle 공통 상단 메뉴 컴포넌트
	 */

	var TopMenu = function (_Component) {
	    _inherits(TopMenu, _Component);

	    //

	    function TopMenu(props) {
	        _classCallCheck(this, TopMenu);

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(TopMenu).call(this, props));

	        _this.state = {
	            roleDisplayable: false
	        };

	        _this.changeLanguageClick = _this.changeLanguageClick.bind(_this);
	        return _this;
	    }
	    // event


	    _createClass(TopMenu, [{
	        key: 'changeLanguageClick',
	        value: function changeLanguageClick(event) {
	            var lang = $(event.target).data('lang');
	            this.props.changeLanguage(lang);
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var MENUS = CastleTopMenuModel.attrs,
	                lang = this.props.getLanguage(),
	                displayLang = void 0;

	            if (lang === 'KOR') {
	                displayLang = '한국어';
	            } else if (lang === 'USA') {
	                displayLang = 'English';
	            }

	            return _react2.default.createElement(
	                'nav',
	                { className: 'navbar navbar-inverse navbar-static-top' },
	                _react2.default.createElement(
	                    'div',
	                    { className: 'container' },
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'navbar-header' },
	                        _react2.default.createElement(
	                            _reactRouter.Link,
	                            { className: 'navbar-brand', to: _castleCommon.Constant.PAV_CTX.hash + '/' },
	                            'Castle'
	                        )
	                    ),
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'collapse navbar-collapse' },
	                        _react2.default.createElement(
	                            'ui',
	                            { className: 'nav navbar-nav' },
	                            _react2.default.createElement(
	                                'li',
	                                null,
	                                _react2.default.createElement(
	                                    _reactRouter.Link,
	                                    { to: _castleCommon.Constant.PAV_CTX.hash + '/castles' },
	                                    MENUS.castles[lang]
	                                )
	                            )
	                        ),
	                        _react2.default.createElement(
	                            'ul',
	                            { className: 'nav navbar-nav navbar-right' },
	                            _react2.default.createElement(_naraRoleBook2.default, null),
	                            _react2.default.createElement(
	                                'li',
	                                { className: 'dropdown' },
	                                _react2.default.createElement(
	                                    'a',
	                                    { href: 'javascript:', className: 'dropdown-toggle', 'data-toggle': 'dropdown',
	                                        role: 'button', 'aria-expanded': 'false' },
	                                    'Language (',
	                                    displayLang,
	                                    ') ',
	                                    _react2.default.createElement('span', { className: 'caret' })
	                                ),
	                                _react2.default.createElement(
	                                    'ul',
	                                    { className: 'dropdown-menu', role: 'menu' },
	                                    _react2.default.createElement(
	                                        'li',
	                                        null,
	                                        _react2.default.createElement(
	                                            'a',
	                                            { href: 'javascript:', onClick: this.changeLanguageClick,
	                                                'data-lang': 'KOR' },
	                                            '한국어'
	                                        )
	                                    ),
	                                    _react2.default.createElement(
	                                        'li',
	                                        null,
	                                        _react2.default.createElement(
	                                            'a',
	                                            { href: 'javascript:', onClick: this.changeLanguageClick, 'data-lang': 'USA' },
	                                            'English'
	                                        )
	                                    )
	                                )
	                            )
	                        )
	                    )
	                )
	            );
	        }
	    }]);

	    return TopMenu;
	}(_react.Component);

	TopMenu.propTypes = {
	    changeLanguage: _react.PropTypes.func.isRequired
	};

	exports.default = TopMenu;

/***/ },
/* 11 */
/***/ function(module, exports) {

	module.exports = externalLib.ReactRouter;

/***/ },
/* 12 */
/***/ function(module, exports) {

	module.exports = naraLib.NaraRoleBook;

/***/ },
/* 13 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _reactDom = __webpack_require__(14);

	var _reactDom2 = _interopRequireDefault(_reactDom);

	var _reactRouter = __webpack_require__(11);

	var _castleCommon = __webpack_require__(1);

	var _main = __webpack_require__(9);

	var _main2 = _interopRequireDefault(_main);

	var _error = __webpack_require__(15);

	var _error2 = _interopRequireDefault(_error);

	var _list = __webpack_require__(16);

	var _list2 = _interopRequireDefault(_list);

	var _detailTab = __webpack_require__(17);

	var _detailTab2 = _interopRequireDefault(_detailTab);

	var _basic = __webpack_require__(18);

	var _basic2 = _interopRequireDefault(_basic);

	var _nameBook = __webpack_require__(19);

	var _nameBook2 = _interopRequireDefault(_nameBook);

	var _phoneBook = __webpack_require__(20);

	var _phoneBook2 = _interopRequireDefault(_phoneBook);

	var _emailBook = __webpack_require__(21);

	var _emailBook2 = _interopRequireDefault(_emailBook);

	var _addressBook = __webpack_require__(22);

	var _addressBook2 = _interopRequireDefault(_addressBook);

	var _accountBook = __webpack_require__(23);

	var _accountBook2 = _interopRequireDefault(_accountBook);

	var _stateBook = __webpack_require__(24);

	var _stateBook2 = _interopRequireDefault(_stateBook);

	var _metroBook = __webpack_require__(25);

	var _metroBook2 = _interopRequireDefault(_metroBook);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	// Routes
	/**
	 * Created by hkkang on 2016-04-05.
	 */

	'use strict';

	var CTX = _castleCommon.Constant.PAV_CTX.hash,
	    appRootPath = CTX ? CTX : '/';

	_reactDom2.default.render(_react2.default.createElement(
	    _reactRouter.Router,
	    { history: _reactRouter.hashHistory },
	    _react2.default.createElement(
	        _reactRouter.Route,
	        { path: '' + appRootPath, component: _main2.default },
	        _react2.default.createElement(_reactRouter.IndexRedirect, { to: 'castles' }),
	        _react2.default.createElement(_reactRouter.Route, { path: 'castles', component: _list2.default }),
	        _react2.default.createElement(
	            _reactRouter.Route,
	            { path: 'castle/:castleId', component: _detailTab2.default },
	            _react2.default.createElement(_reactRouter.Route, { path: 'basic', component: _basic2.default }),
	            _react2.default.createElement(_reactRouter.Route, { path: 'name-book', component: _nameBook2.default }),
	            _react2.default.createElement(_reactRouter.Route, { path: 'phone-book', component: _phoneBook2.default }),
	            _react2.default.createElement(_reactRouter.Route, { path: 'email-book', component: _emailBook2.default }),
	            _react2.default.createElement(_reactRouter.Route, { path: 'address-book', component: _addressBook2.default }),
	            _react2.default.createElement(_reactRouter.Route, { path: 'account-book', component: _accountBook2.default }),
	            _react2.default.createElement(_reactRouter.Route, { path: 'state-book', component: _stateBook2.default }),
	            _react2.default.createElement(_reactRouter.Route, { path: 'metro-book', component: _metroBook2.default })
	        ),
	        _react2.default.createElement(_reactRouter.Route, { path: '*', component: _error2.default })
	    )
	), _castleCommon.Dom.getCastleMainDom());

/***/ },
/* 14 */
/***/ function(module, exports) {

	module.exports = externalLib.ReactDOM;

/***/ },
/* 15 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _main = __webpack_require__(9);

	var _main2 = _interopRequireDefault(_main);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-12.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	var ErrorModel = {
	    //
	    messages: {
	        notFoundPage: {
	            title: { KOR: '404', USA: '404' },
	            message: { KOR: '요청 된 URL 주소가 잘못 되었습니다.', USA: 'Invalid the requested URL' }
	        }
	    },
	    buttons: {
	        back: { KOR: '뒤로가기', USA: 'Previous page' }
	    }
	};

	var ErrorPage = function (_Component) {
	    _inherits(ErrorPage, _Component);

	    //

	    function ErrorPage(props) {
	        _classCallCheck(this, ErrorPage);

	        return _possibleConstructorReturn(this, Object.getPrototypeOf(ErrorPage).call(this, props));
	    }
	    // event


	    _createClass(ErrorPage, [{
	        key: 'backBtnClick',
	        value: function backBtnClick() {
	            window.history.back();
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            var MESSAGE = ErrorModel.messages,
	                BUTTONS = ErrorModel.buttons,
	                lang = _main2.default.lang;

	            return _react2.default.createElement(
	                'article',
	                null,
	                _react2.default.createElement(
	                    'div',
	                    { className: 'jumbotron' },
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'container' },
	                        _react2.default.createElement(
	                            'h1',
	                            null,
	                            MESSAGE.notFoundPage.title[lang]
	                        ),
	                        _react2.default.createElement(
	                            'p',
	                            null,
	                            MESSAGE.notFoundPage.message[lang]
	                        ),
	                        _react2.default.createElement(
	                            'p',
	                            null,
	                            _react2.default.createElement(
	                                'a',
	                                { href: 'javascript:', className: 'btn btn-primary btn-lg',
	                                    onClick: this.backBtnClick },
	                                BUTTONS.back[lang]
	                            )
	                        )
	                    )
	                )
	            );
	        }
	    }]);

	    return ErrorPage;
	}(_react.Component);

	exports.default = ErrorPage;

/***/ },
/* 16 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _reactRouter = __webpack_require__(11);

	var _nara = __webpack_require__(2);

	var _castleCommon = __webpack_require__(1);

	var _castleModel = __webpack_require__(8);

	var _castleModel2 = _interopRequireDefault(_castleModel);

	var _main = __webpack_require__(9);

	var _main2 = _interopRequireDefault(_main);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-12.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	// Define content attributes
	var castleListModel = {
	    //
	    finder: {
	        title: { KOR: 'Castle 검색', USA: 'Search Castle' },
	        criteria: {
	            name: { KOR: '이름', USA: 'Name' }
	        }
	    },
	    list: {
	        header: {
	            id: { KOR: '아이디', USA: 'Id' },
	            name: { KOR: '이름', USA: 'Name' },
	            locale: { KOR: '지역', USA: 'Locale' },
	            primaryEmail: { KOR: '이메일', USA: 'Email' },
	            primaryPhone: { KOR: '전화번호', USA: 'Phone number' },
	            state: { KOR: '상태', USA: 'State' },
	            buildTime: { KOR: '생성일시', USA: 'Build time' },
	            detail: { KOR: '상세정보', USA: 'Detail info' }
	        }
	    },
	    messages: {
	        notExistsMessage: { KOR: 'Castle이 존재하지 않습니다.', USA: 'Not exists castle' }
	    }
	};

	// Define components

	var CastleListPage = function (_Component) {
	    _inherits(CastleListPage, _Component);

	    //

	    function CastleListPage(props) {
	        _classCallCheck(this, CastleListPage);

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(CastleListPage).call(this, props));

	        _this.state = {
	            castleCriteria: {},
	            castles: []
	        };
	        return _this;
	    }

	    _createClass(CastleListPage, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            this.requestFindCastles();
	        }
	        // custom

	    }, {
	        key: 'changeCriteriaInput',
	        value: function changeCriteriaInput() {}
	        // request

	    }, {
	        key: 'requestFindCastles',
	        value: function requestFindCastles(castleCriteria) {
	            //
	            _nara.Ajax.getJSON(CastleListPage.url.FIND_CASTLES).done(function (castlesResult) {
	                var notExistsMessage = castleListModel.messages.notExistsMessage,
	                    lang = _main2.default.lang;

	                if (!castlesResult || castlesResult.length === 0) {
	                    alert(notExistsMessage[lang]);
	                    return;
	                }
	                this.setState({ castles: castlesResult });
	            }.bind(this));
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            return _react2.default.createElement(
	                'article',
	                null,
	                _react2.default.createElement(Finder, {
	                    criteria: this.state.castleCriteria,
	                    change: this.changeCriteriaInput,
	                    find: this.requestFindCastles
	                }),
	                _react2.default.createElement(CastleList, { castles: this.state.castles })
	            );
	        }
	    }]);

	    return CastleListPage;
	}(_react.Component);

	CastleListPage.url = {
	    //
	    FIND_CASTLES: _castleCommon.Constant.PAV_CTX.api + '/castles'
	};

	/**
	 * Castle 검색 컴포넌트
	 */

	var Finder = function (_Component2) {
	    _inherits(Finder, _Component2);

	    //

	    function Finder(props) {
	        _classCallCheck(this, Finder);

	        var _this2 = _possibleConstructorReturn(this, Object.getPrototypeOf(Finder).call(this, props));

	        _this2.findBtnClick = _this2.findBtnClick.bind(_this2);
	        _this2.inputChange = _this2.inputChange.bind(_this2);
	        return _this2;
	    }
	    // event


	    _createClass(Finder, [{
	        key: 'findBtnClick',
	        value: function findBtnClick() {
	            this.props.find(this.props.criteria);
	        }
	    }, {
	        key: 'inputChange',
	        value: function inputChange(event) {
	            this.props.change({
	                name: event.target.value
	            });
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var ATTRS = castleListModel.finder,
	                BUTTON_NAMES = _castleModel2.default.buttons,
	                LANG = _main2.default.lang;

	            return _react2.default.createElement(
	                'div',
	                { className: 'container' },
	                _react2.default.createElement(
	                    'div',
	                    { className: 'panel panel-success' },
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'panel-heading' },
	                        _react2.default.createElement(
	                            'h4',
	                            { className: 'panel-title' },
	                            ATTRS.title[LANG]
	                        )
	                    ),
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'panel-body' },
	                        _react2.default.createElement(
	                            'div',
	                            { className: 'form-horizontal' },
	                            _react2.default.createElement(
	                                'div',
	                                { className: 'form-group' },
	                                _react2.default.createElement(
	                                    'label',
	                                    {
	                                        className: 'col-md-1 col-md-offset-6 control-label' },
	                                    ATTRS.criteria.name[LANG]
	                                ),
	                                _react2.default.createElement(
	                                    'div',
	                                    { className: 'col-md-3' },
	                                    _react2.default.createElement('input', { className: 'form-control', type: 'text', value: this.props.criteria.name,
	                                        onChange: this.inputChange, placeholder: 'Castle name' })
	                                ),
	                                _react2.default.createElement(
	                                    'div',
	                                    { className: 'col-md-2' },
	                                    _react2.default.createElement(
	                                        'div',
	                                        { className: 'btn-group' },
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-default', id: 'inBtn',
	                                                onClick: this.findBtnClick },
	                                            BUTTON_NAMES.search[LANG]
	                                        )
	                                    )
	                                )
	                            )
	                        )
	                    )
	                )
	            );
	        }
	    }]);

	    return Finder;
	}(_react.Component);

	Finder.propTypes = {
	    //
	    criteria: _react.PropTypes.object,

	    change: _react.PropTypes.func.isRequired,
	    find: _react.PropTypes.func.isRequired
	};

	/**
	 * Castle list 컴포넌트
	 */

	var CastleList = function (_Component3) {
	    _inherits(CastleList, _Component3);

	    //

	    function CastleList(props) {
	        _classCallCheck(this, CastleList);

	        var _this3 = _possibleConstructorReturn(this, Object.getPrototypeOf(CastleList).call(this, props));

	        _this3.renderList = _this3.renderList.bind(_this3);
	        return _this3;
	    }
	    //


	    _createClass(CastleList, [{
	        key: 'renderList',
	        value: function renderList() {
	            //
	            var existsCastle = this.props.castles.length > 0,
	                result = void 0;

	            if (existsCastle === true) {
	                result = this.props.castles.map(function (castle, index) {});
	            } else {}
	            return result;
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var ENUMS = _castleModel2.default.enums,
	                ATTRS = castleListModel.list,
	                MESSAGES = castleListModel.messages,
	                LANG = _main2.default.lang;

	            var existsCastle = this.props.castles.length > 0;

	            return _react2.default.createElement(
	                'div',
	                { className: 'container' },
	                _react2.default.createElement(
	                    'div',
	                    { className: 'panel panel-success' },
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'panel-body' },
	                        _react2.default.createElement(
	                            'table',
	                            { className: 'table table-striped table-hover' },
	                            _react2.default.createElement(
	                                'thead',
	                                null,
	                                _react2.default.createElement(
	                                    'tr',
	                                    null,
	                                    _react2.default.createElement(
	                                        'th',
	                                        null,
	                                        ATTRS.header.id[LANG]
	                                    ),
	                                    _react2.default.createElement(
	                                        'th',
	                                        null,
	                                        ATTRS.header.name[LANG]
	                                    ),
	                                    _react2.default.createElement(
	                                        'th',
	                                        null,
	                                        ATTRS.header.locale[LANG]
	                                    ),
	                                    _react2.default.createElement(
	                                        'th',
	                                        null,
	                                        ATTRS.header.primaryEmail[LANG]
	                                    ),
	                                    _react2.default.createElement(
	                                        'th',
	                                        null,
	                                        ATTRS.header.primaryPhone[LANG]
	                                    ),
	                                    _react2.default.createElement(
	                                        'th',
	                                        null,
	                                        ATTRS.header.state[LANG]
	                                    ),
	                                    _react2.default.createElement(
	                                        'th',
	                                        null,
	                                        ATTRS.header.buildTime[LANG]
	                                    ),
	                                    _react2.default.createElement(
	                                        'th',
	                                        null,
	                                        ATTRS.header.detail[LANG]
	                                    )
	                                )
	                            ),
	                            _react2.default.createElement(
	                                'tbody',
	                                null,
	                                existsCastle === true ? this.props.castles.map(function (castle, index) {
	                                    var existsOwner = castle.owner ? true : false;

	                                    return _react2.default.createElement(
	                                        'tr',
	                                        { key: index },
	                                        _react2.default.createElement(
	                                            'td',
	                                            null,
	                                            castle.id
	                                        ),
	                                        _react2.default.createElement(
	                                            'td',
	                                            null,
	                                            castle.name
	                                        ),
	                                        _react2.default.createElement(
	                                            'td',
	                                            null,
	                                            ENUMS.locale[castle.locale][LANG]
	                                        ),
	                                        _react2.default.createElement(
	                                            'td',
	                                            null,
	                                            existsOwner ? castle.owner.primaryEmail : null
	                                        ),
	                                        _react2.default.createElement(
	                                            'td',
	                                            null,
	                                            existsOwner ? castle.owner.primaryPhone : null
	                                        ),
	                                        _react2.default.createElement(
	                                            'td',
	                                            null,
	                                            ENUMS.state[castle.state][LANG]
	                                        ),
	                                        _react2.default.createElement(
	                                            'td',
	                                            null,
	                                            _nara.Date.parseToString(castle.buildTime)
	                                        ),
	                                        _react2.default.createElement(
	                                            'td',
	                                            null,
	                                            _react2.default.createElement(
	                                                _reactRouter.Link,
	                                                { to: _castleCommon.Constant.PAV_CTX.hash + '/castle/' + castle.id + '/basic' },
	                                                _react2.default.createElement('span', { className: 'glyphicon glyphicon-book' })
	                                            )
	                                        )
	                                    );
	                                }) : _react2.default.createElement(
	                                    'tr',
	                                    null,
	                                    _react2.default.createElement(
	                                        'td',
	                                        { colSpan: '8' },
	                                        MESSAGES.notExistsMessage[LANG]
	                                    )
	                                )
	                            )
	                        )
	                    )
	                )
	            );
	        }
	    }]);

	    return CastleList;
	}(_react.Component);

	CastleList.propTypes = {
	    castles: _react.PropTypes.array.isRequired
	};

	exports.default = CastleListPage;

/***/ },
/* 17 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _reactRouter = __webpack_require__(11);

	var _castleCommon = __webpack_require__(1);

	var _main = __webpack_require__(9);

	var _main2 = _interopRequireDefault(_main);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-12.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	// Define Content attributes name
	var castleTabModel = {
	    //
	    attrs: {
	        'basic': { name: 'basic', url: 'basic', KOR: '기본정보', USA: 'Basic information' },
	        'nameBook': { name: 'nameBook', url: 'name-book', KOR: '이름', USA: 'Name' },
	        'phoneBook': { name: 'phoneBook', url: 'phone-book', KOR: '전화번호', USA: 'Phone number' },
	        'emailBook': { name: 'emailBook', url: 'email-book', KOR: '이메일', USA: 'Email' },
	        'addressBook': { name: 'addressBook', url: 'address-book', KOR: '주소', USA: 'Address' },
	        'accountBook': { name: 'accountBook', url: 'account-book', KOR: '계정이력', USA: 'Account history' },
	        'stateBook': { name: 'stateBook', url: 'state-book', KOR: '상태이력', USA: 'State history' },
	        'metroBook': { name: 'metroBook', url: 'metro-book', KOR: '메트로이력', USA: 'Metro history' }
	    }
	};

	// Define components

	var CastleDetailPage = function (_Component) {
	    _inherits(CastleDetailPage, _Component);

	    //

	    function CastleDetailPage(props) {
	        _classCallCheck(this, CastleDetailPage);

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(CastleDetailPage).call(this, props));

	        _this.defaultProps = {
	            contentType: 'basic'
	        };

	        _this.state = {
	            castle: {
	                contact: {
	                    nameBook: { names: [] },
	                    phoneBook: { phones: [] },
	                    emailBook: { emails: [] },
	                    addressBook: { addresses: [] }
	                },
	                history: {
	                    accountBook: { accounts: [] },
	                    stateBook: { states: [] },
	                    metroBook: { metros: [] }
	                },
	                basic: { castellan: {} }
	            },
	            contentModifiable: false
	        };

	        _this.changeModifiableMode = _this.changeModifiableMode.bind(_this);
	        _this.changeViewMode = _this.changeViewMode.bind(_this);
	        _this.setCastle = _this.setCastle.bind(_this);
	        return _this;
	    }

	    _createClass(CastleDetailPage, [{
	        key: 'componentWillReceiveProps',
	        value: function componentWillReceiveProps() {
	            this.setState({ contentModifiable: false });
	        }
	        // custom

	    }, {
	        key: 'changeModifiableMode',
	        value: function changeModifiableMode() {
	            this.setState({ contentModifiable: true });
	        }
	    }, {
	        key: 'changeViewMode',
	        value: function changeViewMode() {
	            this.setState({ contentModifiable: false });
	        }
	    }, {
	        key: 'setCastle',
	        value: function setCastle(castle) {
	            this.setState({ castle: castle });
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var contentType = this.props.location.query.contentType || 'basic';

	            return _react2.default.createElement(Tab, {
	                castleId: this.props.params.castleId,
	                contentType: contentType,
	                contentComponent: _react2.default.cloneElement(this.props.children, {
	                    castleId: this.props.params.castleId,
	                    castle: this.state.castle,
	                    modifiable: this.state.contentModifiable,
	                    changeModifiableMode: this.changeModifiableMode,
	                    changeViewMode: this.changeViewMode,
	                    setCastle: this.setCastle
	                })
	            });
	        }
	    }]);

	    return CastleDetailPage;
	}(_react.Component);

	CastleDetailPage.propTypes = {
	    contentType: _react.PropTypes.string
	};

	var Tab = function (_Component2) {
	    _inherits(Tab, _Component2);

	    //

	    function Tab(props) {
	        _classCallCheck(this, Tab);

	        return _possibleConstructorReturn(this, Object.getPrototypeOf(Tab).call(this, props));
	    }

	    _createClass(Tab, [{
	        key: 'render',
	        value: function render() {
	            //
	            var TAB_NAMES = castleTabModel.attrs,
	                LANG = _main2.default.lang;

	            var contentType = this.props.contentType,
	                baseLinkUrl = _castleCommon.Constant.PAV_CTX.hash + '/castle/' + this.props.castleId;

	            return _react2.default.createElement(
	                'div',
	                { className: 'container' },
	                _react2.default.createElement(
	                    'div',
	                    { className: 'panel panel-success' },
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'panel-body' },
	                        _react2.default.createElement(
	                            'ul',
	                            { className: 'nav nav-tabs' },
	                            _react2.default.createElement(
	                                'li',
	                                { className: contentType === TAB_NAMES.basic.name ? "active" : null },
	                                _react2.default.createElement(
	                                    _reactRouter.Link,
	                                    { to: baseLinkUrl + '/' + TAB_NAMES.basic.url + '?contentType=' + TAB_NAMES.basic.name },
	                                    TAB_NAMES.basic[LANG]
	                                )
	                            ),
	                            _react2.default.createElement(
	                                'li',
	                                { className: contentType === TAB_NAMES.nameBook.name ? "active" : null },
	                                _react2.default.createElement(
	                                    _reactRouter.Link,
	                                    { to: baseLinkUrl + '/' + TAB_NAMES.nameBook.url + '?contentType=' + TAB_NAMES.nameBook.name },
	                                    TAB_NAMES.nameBook[LANG]
	                                )
	                            ),
	                            _react2.default.createElement(
	                                'li',
	                                { className: contentType === TAB_NAMES.phoneBook.name ? "active" : null },
	                                _react2.default.createElement(
	                                    _reactRouter.Link,
	                                    { to: baseLinkUrl + '/' + TAB_NAMES.phoneBook.url + '?contentType=' + TAB_NAMES.phoneBook.name },
	                                    TAB_NAMES.phoneBook[LANG]
	                                )
	                            ),
	                            _react2.default.createElement(
	                                'li',
	                                { className: contentType === TAB_NAMES.emailBook.name ? "active" : null },
	                                _react2.default.createElement(
	                                    _reactRouter.Link,
	                                    { to: baseLinkUrl + '/' + TAB_NAMES.emailBook.url + '?contentType=' + TAB_NAMES.emailBook.name },
	                                    TAB_NAMES.emailBook[LANG]
	                                )
	                            ),
	                            _react2.default.createElement(
	                                'li',
	                                { className: contentType === TAB_NAMES.addressBook.name ? "active" : null },
	                                _react2.default.createElement(
	                                    _reactRouter.Link,
	                                    { to: baseLinkUrl + '/' + TAB_NAMES.addressBook.url + '?contentType=' + TAB_NAMES.addressBook.name },
	                                    TAB_NAMES.addressBook[LANG]
	                                )
	                            ),
	                            _react2.default.createElement(
	                                'li',
	                                { className: contentType === TAB_NAMES.accountBook.name ? "active" : null },
	                                _react2.default.createElement(
	                                    _reactRouter.Link,
	                                    { to: baseLinkUrl + '/' + TAB_NAMES.accountBook.url + '?contentType=' + TAB_NAMES.accountBook.name },
	                                    TAB_NAMES.accountBook[LANG]
	                                )
	                            ),
	                            _react2.default.createElement(
	                                'li',
	                                { className: contentType === TAB_NAMES.stateBook.name ? "active" : null },
	                                _react2.default.createElement(
	                                    _reactRouter.Link,
	                                    { to: baseLinkUrl + '/' + TAB_NAMES.stateBook.url + '?contentType=' + TAB_NAMES.stateBook.name },
	                                    TAB_NAMES.stateBook[LANG]
	                                )
	                            ),
	                            _react2.default.createElement(
	                                'li',
	                                { className: contentType === TAB_NAMES.metroBook.name ? "active" : null },
	                                _react2.default.createElement(
	                                    _reactRouter.Link,
	                                    { to: baseLinkUrl + '/' + TAB_NAMES.metroBook.url + '?contentType=' + TAB_NAMES.metroBook.name },
	                                    TAB_NAMES.metroBook[LANG]
	                                )
	                            )
	                        ),
	                        _react2.default.createElement(
	                            'div',
	                            { className: 'tab-content' },
	                            _react2.default.createElement(
	                                'div',
	                                { className: 'tab-pane active' },
	                                this.props.contentComponent
	                            )
	                        )
	                    )
	                )
	            );
	        }
	    }]);

	    return Tab;
	}(_react.Component);

	Tab.propTypes = {
	    //
	    castleId: _react.PropTypes.string.isRequired,
	    contentType: _react.PropTypes.string.isRequired,
	    contentComponent: _react.PropTypes.object.isRequired
	};

	exports.default = CastleDetailPage;

/***/ },
/* 18 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _nara = __webpack_require__(2);

	var _castleCommon = __webpack_require__(1);

	var _castleModel = __webpack_require__(8);

	var _castleModel2 = _interopRequireDefault(_castleModel);

	var _main = __webpack_require__(9);

	var _main2 = _interopRequireDefault(_main);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-12.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	// Define Content attributes name
	var CastleBasicModel = {
	    //
	    attrs: {
	        id: { name: 'id', KOR: '아이디', USA: 'Id' },
	        name: { name: 'name', bookName: 'nameBook', KOR: '이름', USA: 'Name' },
	        locale: { name: 'locale', KOR: '지역', USA: 'Locale' },
	        state: { name: 'state', KOR: '상태', USA: 'State' },
	        buildTime: { name: 'buildTime', KOR: '생성일시', USA: 'Build time' },
	        castellan: {
	            primaryEmail: { name: 'primaryEmail', bookName: 'emailBook', KOR: '기본 이메일', USA: 'Primary email' },
	            primaryPhone: { name: 'primaryPhone', bookName: 'phoneBook', KOR: '기본 전화번호', USA: 'Primary phone number' },
	            photo: { name: 'photoId', KOR: '사진', USA: 'Photo' }
	        }
	    },
	    messages: {
	        notFoundCastle: { KOR: '해당 Id의 Castle 정보가 없습니다. -> Id: {id}', USA: 'Not found the Castle -> Id: {id}' },
	        completeModify: { KOR: 'Basic이 수정 되었습니다.', USA: 'Modify has been completed.' },
	        confirmRemove: {
	            KOR: '삭제 시 Contact와 History 관련 모든 정보가 삭제되며 복구할 수 없습니다. 정말 삭제하시겠습니까?',
	            USA: 'Are you really remove castle?'
	        }
	    }
	};

	var BasicContent = function (_Component) {
	    _inherits(BasicContent, _Component);

	    //

	    function BasicContent(props) {
	        _classCallCheck(this, BasicContent);

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(BasicContent).call(this, props));

	        _this.setBasic = _this.setBasic.bind(_this);
	        _this.setContactBook = _this.setContactBook.bind(_this);
	        _this.requestFindCastle = _this.requestFindCastle.bind(_this);
	        _this.requestFindNameBook = _this.requestFindNameBook.bind(_this);
	        _this.requestFindEmailBook = _this.requestFindEmailBook.bind(_this);
	        _this.requestFindPhoneBook = _this.requestFindPhoneBook.bind(_this);
	        _this.requestModifyBasic = _this.requestModifyBasic.bind(_this);
	        return _this;
	    }
	    // overriding


	    _createClass(BasicContent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            this.requestFindCastle(this.props.castleId);
	            this.requestFindNameBook(this.props.castleId);
	            this.requestFindEmailBook(this.props.castleId);
	            this.requestFindPhoneBook(this.props.castleId);
	        }
	        // custom

	    }, {
	        key: 'setBasic',
	        value: function setBasic(castleBasic, castellan) {
	            //
	            var castle = this.props.castle;

	            castle.basic = castleBasic;
	            castle.basic.castellan = castellan || castle.basic.castellan;

	            this.props.setCastle(castle);
	        }
	    }, {
	        key: 'setContactBook',
	        value: function setContactBook(propertyName, book) {
	            var castle = this.props.castle;

	            castle.contact[propertyName] = book;
	            this.props.setCastle(castle);
	        }
	        // request

	    }, {
	        key: 'requestFindCastle',
	        value: function requestFindCastle(castleId) {
	            //
	            var urlBuilder = _nara.Ajax.createUrlBuilder();

	            urlBuilder.addUrl(BasicContent.url.FIND_CASTLE.replace('{id}', castleId));
	            urlBuilder.addUrl(BasicContent.url.FIND_CASTELLAN.replace('{id}', castleId));

	            _nara.Ajax.getJSONs(urlBuilder.build(), function (castleResult, castellanResult) {
	                //
	                if (_nara.Object.isEmpty(castleResult)) {
	                    var MESSAGES = CastleBasicModel.messages,
	                        lang = _main2.default.lang;

	                    alert(MESSAGES.notFoundCastle[lang].replace('{id}', castleId));
	                    return;
	                }
	                this.setBasic(castleResult, castellanResult);
	            }.bind(this));
	        }
	    }, {
	        key: 'requestFindNameBook',
	        value: function requestFindNameBook(castleId) {
	            //
	            _nara.Ajax.getJSON(BasicContent.url.FIND_NAME_BOOK.replace('{id}', castleId)).done(function (nameBook) {
	                this.setContactBook(CastleBasicModel.attrs.name.bookName, nameBook);
	            }.bind(this));
	        }
	    }, {
	        key: 'requestFindEmailBook',
	        value: function requestFindEmailBook(castleId) {
	            //
	            _nara.Ajax.getJSON(BasicContent.url.FIND_EMAIL_BOOK.replace('{id}', castleId)).done(function (emailBook) {
	                this.setContactBook(CastleBasicModel.attrs.castellan.primaryEmail.bookName, emailBook);
	            }.bind(this));
	        }
	    }, {
	        key: 'requestFindPhoneBook',
	        value: function requestFindPhoneBook(castleId) {
	            //
	            _nara.Ajax.getJSON(BasicContent.url.FIND_PHONE_BOOK.replace('{id}', castleId)).done(function (phoneBook) {
	                this.setContactBook(CastleBasicModel.attrs.castellan.primaryPhone.bookName, phoneBook);
	            }.bind(this));
	        }
	    }, {
	        key: 'requestModifyBasic',
	        value: function requestModifyBasic(castleBasic) {
	            //
	            var urlBuilder = _nara.Ajax.createUrlBuilder(),
	                castleId = castleBasic.id;

	            urlBuilder.addUrlAndParam(BasicContent.url.MODIFY_NAME.replace('{id}', castleId), castleBasic.name);
	            urlBuilder.addUrlAndParam(BasicContent.url.MODIFY_LOCALE.replace('{id}', castleId), castleBasic.locale);
	            urlBuilder.addUrlAndParam(BasicContent.url.MODIFY_PRIMARY_EMAIL.replace('{id}', castleId), castleBasic.castellan.primaryEmail);
	            urlBuilder.addUrlAndParam(BasicContent.url.MODIFY_PRIMARY_PHONE.replace('{id}', castleId), castleBasic.castellan.primaryPhone);

	            if (castleBasic.state === _castleModel2.default.enums.state.Open.name) {
	                urlBuilder.addUrlAndParam(BasicContent.url.REOPEN_CASTLE.replace('{id}', castleId), 'remaraks');
	            } else if (castleBasic.state === _castleModel2.default.enums.state.Suspended.name) {
	                urlBuilder.addUrlAndParam(BasicContent.url.SUSPEND_CASTLE.replace('{id}', castleId), 'remaraks');
	            }

	            _nara.Ajax.putJSONs(urlBuilder.build(), function () {
	                this.setBasic(castleBasic);

	                var lang = _main2.default.lang;
	                alert(CastleBasicModel.messages.completeModify[lang]);
	            }.bind(this));
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            return this.props.modifiable ? _react2.default.createElement(BasicModifiableContent, {
	                basicInfo: this.props.castle.basic,
	                nameBook: this.props.castle.contact.nameBook,
	                emailBook: this.props.castle.contact.emailBook,
	                phoneBook: this.props.castle.contact.phoneBook,
	                modifiable: this.props.modifiable,
	                changeViewMode: this.props.changeViewMode,
	                setBasic: this.setBasic,
	                modifyBasic: this.requestModifyBasic
	            }) : _react2.default.createElement(BasicViewContent, {
	                basicInfo: this.props.castle.basic,
	                modifiable: this.props.modifiable,
	                changeModifiableMode: this.props.changeModifiableMode
	            });
	        }
	    }]);

	    return BasicContent;
	}(_react.Component);

	BasicContent.propTypes = {
	    //
	    castleId: _react.PropTypes.string,
	    castle: _react.PropTypes.shape({
	        basic: _react.PropTypes.shape({
	            castellan: _react.PropTypes.object
	        }),
	        contact: _react.PropTypes.shape({
	            emailBook: _react.PropTypes.shape({
	                emails: _react.PropTypes.array
	            }).isRequired,
	            phoneBook: _react.PropTypes.shape({
	                phones: _react.PropTypes.array
	            })
	        })
	    }),
	    modifiable: _react.PropTypes.bool,

	    changeModifiableMode: _react.PropTypes.func,
	    changeViewMode: _react.PropTypes.func,
	    setCastle: _react.PropTypes.func
	};

	BasicContent.url = {
	    //
	    FIND_CASTLE: _castleCommon.Constant.PAV_CTX.api + '/castles/{id}',
	    FIND_CASTELLAN: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}',
	    MODIFY_NAME: _castleCommon.Constant.PAV_CTX.api + '/castles/{id}/name',
	    MODIFY_LOCALE: _castleCommon.Constant.PAV_CTX.api + '/castles/{id}/locale',
	    SUSPEND_CASTLE: _castleCommon.Constant.PAV_CTX.api + '/castles/{id}/suspend',
	    REOPEN_CASTLE: _castleCommon.Constant.PAV_CTX.api + '/castles/{id}/reopen',
	    MODIFY_PRIMARY_EMAIL: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}/primary-email',
	    MODIFY_PRIMARY_PHONE: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}/primary-phone',
	    FIND_NAME_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}/contacts/name-book',
	    FIND_EMAIL_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}/contacts/email-book',
	    FIND_PHONE_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}/contacts/phone-book'
	};

	var BasicViewContent = function (_Component2) {
	    _inherits(BasicViewContent, _Component2);

	    //

	    function BasicViewContent(props) {
	        _classCallCheck(this, BasicViewContent);

	        var _this2 = _possibleConstructorReturn(this, Object.getPrototypeOf(BasicViewContent).call(this, props));

	        _this2.modifiableModeBtnClick = _this2.modifiableModeBtnClick.bind(_this2);
	        _this2.removeBtnClick = _this2.removeBtnClick.bind(_this2);
	        return _this2;
	    }
	    // event


	    _createClass(BasicViewContent, [{
	        key: 'modifiableModeBtnClick',
	        value: function modifiableModeBtnClick() {
	            this.props.changeModifiableMode();
	        }
	    }, {
	        key: 'removeBtnClick',
	        value: function removeBtnClick() {
	            var lang = _main2.default.lang;
	            if (confirm(CastleBasicModel.messages.confirmRemove[lang])) {
	                // TODO : Castle 삭제
	            }
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var ENUMS = _castleModel2.default.enums,
	                BUTTON_NAMES = _castleModel2.default.buttons,
	                ATTRS = CastleBasicModel.attrs,
	                LANG = _main2.default.lang;

	            var propBasicInfo = this.props.basicInfo,
	                existsCastle = !_nara.Object.isEmpty(propBasicInfo) && propBasicInfo[ATTRS.id.name];

	            return _react2.default.createElement(
	                'form',
	                { className: 'form-horizontal' },
	                _react2.default.createElement(
	                    'div',
	                    { className: 'form-group' },
	                    _react2.default.createElement(
	                        'p',
	                        null,
	                        ' '
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'form-group' },
	                    _react2.default.createElement(
	                        'label',
	                        { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                        ATTRS.id[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'col-lg-5' },
	                        _react2.default.createElement(
	                            'p',
	                            { className: 'form-control-static' },
	                            propBasicInfo[ATTRS.id.name]
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'form-group' },
	                    _react2.default.createElement(
	                        'label',
	                        { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                        ATTRS.name[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'col-lg-5' },
	                        _react2.default.createElement(
	                            'p',
	                            { className: 'form-control-static' },
	                            propBasicInfo[ATTRS.name.name]
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'form-group' },
	                    _react2.default.createElement(
	                        'label',
	                        { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                        ATTRS.locale[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'col-lg-5' },
	                        _react2.default.createElement(
	                            'p',
	                            { className: 'form-control-static' },
	                            existsCastle ? ENUMS.locale[propBasicInfo[ATTRS.locale.name]][LANG] : null
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'form-group' },
	                    _react2.default.createElement(
	                        'label',
	                        { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                        ATTRS.state[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'col-lg-5' },
	                        _react2.default.createElement(
	                            'p',
	                            { className: 'form-control-static' },
	                            existsCastle ? ENUMS.state[propBasicInfo[ATTRS.state.name]][LANG] : null
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'form-group' },
	                    _react2.default.createElement(
	                        'label',
	                        {
	                            className: 'col-lg-3 col-lg-offset-1 control-label' },
	                        ATTRS.castellan.primaryEmail[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'col-lg-5' },
	                        _react2.default.createElement(
	                            'p',
	                            { className: 'form-control-static' },
	                            propBasicInfo.castellan[ATTRS.castellan.primaryEmail.name]
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'form-group' },
	                    _react2.default.createElement(
	                        'label',
	                        {
	                            className: 'col-lg-3 col-lg-offset-1 control-label' },
	                        ATTRS.castellan.primaryPhone[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'col-lg-5' },
	                        _react2.default.createElement(
	                            'p',
	                            { className: 'form-control-static' },
	                            propBasicInfo.castellan[ATTRS.castellan.primaryPhone.name]
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'form-group' },
	                    _react2.default.createElement(
	                        'label',
	                        { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                        ATTRS.castellan.photo[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'col-lg-5' },
	                        _react2.default.createElement(
	                            'p',
	                            { className: 'form-control-static' },
	                            propBasicInfo.castellan[ATTRS.castellan.photo.name]
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'form-group' },
	                    _react2.default.createElement(
	                        'label',
	                        { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                        ATTRS.buildTime[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'col-lg-5' },
	                        _react2.default.createElement(
	                            'p',
	                            { className: 'form-control-static' },
	                            _nara.Date.parseToString(propBasicInfo[ATTRS.buildTime.name])
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'btn-toolbar pull-right' },
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-default',
	                            onClick: this.modifiableModeBtnClick },
	                        BUTTON_NAMES.modify[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-danger',
	                            onClick: this.removeBtnClick },
	                        BUTTON_NAMES.remove[LANG]
	                    )
	                )
	            );
	        }
	    }]);

	    return BasicViewContent;
	}(_react.Component);

	BasicViewContent.propTypes = {
	    //
	    basicInfo: _react.PropTypes.shape({
	        castellan: _react.PropTypes.object.isRequired
	    }).isRequired,
	    changeModifiableMode: _react.PropTypes.func.isRequired
	};

	var BasicModifiableContent = function (_Component3) {
	    _inherits(BasicModifiableContent, _Component3);

	    //

	    function BasicModifiableContent(props) {
	        _classCallCheck(this, BasicModifiableContent);

	        var _this3 = _possibleConstructorReturn(this, Object.getPrototypeOf(BasicModifiableContent).call(this, props));

	        _this3.state = {
	            willModifyBasic: {
	                castellan: {}
	            }
	        };

	        _this3.nameChange = _this3.nameChange.bind(_this3);
	        _this3.localeChange = _this3.localeChange.bind(_this3);
	        _this3.stateChange = _this3.stateChange.bind(_this3);
	        _this3.primaryEmailChange = _this3.primaryEmailChange.bind(_this3);
	        _this3.primaryPhoneNumberChange = _this3.primaryPhoneNumberChange.bind(_this3);
	        _this3.saveBtnClick = _this3.saveBtnClick.bind(_this3);
	        _this3.cancelModificationBtnClick = _this3.cancelModificationBtnClick.bind(_this3);
	        _this3.setWillModifyBasicState = _this3.setWillModifyBasicState.bind(_this3);
	        _this3.setWillModifyCastellanState = _this3.setWillModifyCastellanState.bind(_this3);
	        return _this3;
	    }
	    // overriding


	    _createClass(BasicModifiableContent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            var basicInfo = _nara.Object.deepCopy(this.props.basicInfo);
	            this.setState({ willModifyBasic: basicInfo });
	        }
	        // event

	    }, {
	        key: 'nameChange',
	        value: function nameChange(event) {
	            this.setWillModifyBasicState(CastleBasicModel.attrs.name.name, event.target.value);
	        }
	    }, {
	        key: 'localeChange',
	        value: function localeChange(event) {
	            this.setWillModifyBasicState(CastleBasicModel.attrs.locale.name, event.target.value);
	        }
	    }, {
	        key: 'stateChange',
	        value: function stateChange(event) {
	            this.setWillModifyBasicState(CastleBasicModel.attrs.state.name, event.target.value);
	        }
	    }, {
	        key: 'primaryEmailChange',
	        value: function primaryEmailChange(event) {
	            this.setWillModifyCastellanState(CastleBasicModel.attrs.castellan.primaryEmail.name, event.target.value);
	        }
	    }, {
	        key: 'primaryPhoneNumberChange',
	        value: function primaryPhoneNumberChange(event) {
	            this.setWillModifyCastellanState(CastleBasicModel.attrs.castellan.primaryPhone.name, event.target.value);
	        }
	    }, {
	        key: 'saveBtnClick',
	        value: function saveBtnClick() {
	            this.props.modifyBasic(this.state.willModifyBasic);
	        }
	    }, {
	        key: 'cancelModificationBtnClick',
	        value: function cancelModificationBtnClick() {
	            this.props.changeViewMode();
	        }
	        // custom

	    }, {
	        key: 'setWillModifyBasicState',
	        value: function setWillModifyBasicState(propertyName, value) {
	            //
	            var basic = this.state.willModifyBasic;

	            basic[propertyName] = value;
	            this.setState({ willModifyBasic: basic });
	        }
	    }, {
	        key: 'setWillModifyCastellanState',
	        value: function setWillModifyCastellanState(propertyName, value) {
	            //
	            var basic = this.state.willModifyBasic;

	            basic.castellan[propertyName] = value;
	            this.setState({ willModifyBasic: basic });
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var ENUMS = _castleModel2.default.enums,
	                BUTTON_NAMES = _castleModel2.default.buttons,
	                ATTRS = CastleBasicModel.attrs,
	                LANG = _main2.default.lang;

	            var propBasicInfo = this.state.willModifyBasic;

	            return _react2.default.createElement(
	                'div',
	                { className: 'tab-content' },
	                _react2.default.createElement(
	                    'div',
	                    { className: 'tab-pane active' },
	                    _react2.default.createElement(
	                        'form',
	                        { className: 'form-horizontal' },
	                        _react2.default.createElement(
	                            'div',
	                            { className: 'form-group' },
	                            _react2.default.createElement(
	                                'p',
	                                null,
	                                ' '
	                            )
	                        ),
	                        _react2.default.createElement(
	                            'div',
	                            { className: 'form-group' },
	                            _react2.default.createElement(
	                                'label',
	                                { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                                ATTRS.id[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'div',
	                                { className: 'col-lg-5' },
	                                _react2.default.createElement(
	                                    'p',
	                                    { className: 'form-control-static' },
	                                    propBasicInfo[ATTRS.id.name]
	                                )
	                            )
	                        ),
	                        _react2.default.createElement(
	                            'div',
	                            { className: 'form-group' },
	                            _react2.default.createElement(
	                                'label',
	                                { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                                ATTRS.name[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'div',
	                                { className: 'col-lg-5' },
	                                _react2.default.createElement(
	                                    'select',
	                                    { className: 'form-control', onChange: this.nameChange,
	                                        value: propBasicInfo[ATTRS.name.name] },
	                                    _react2.default.createElement(
	                                        'option',
	                                        { value: '' },
	                                        ATTRS.name[LANG]
	                                    ),
	                                    this.props.nameBook.names.map(function (name, index) {

	                                        return _react2.default.createElement(
	                                            'option',
	                                            { key: index, value: name.displayName },
	                                            name.displayName
	                                        );
	                                    })
	                                ),
	                                _react2.default.createElement('input', { type: 'text', className: 'form-control', onChange: this.nameChange,
	                                    value: propBasicInfo[ATTRS.name.name] })
	                            )
	                        ),
	                        _react2.default.createElement(
	                            'div',
	                            { className: 'form-group' },
	                            _react2.default.createElement(
	                                'label',
	                                { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                                ATTRS.locale[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'div',
	                                { className: 'col-lg-5' },
	                                _react2.default.createElement(
	                                    'select',
	                                    { className: 'form-control', onChange: this.localeChange,
	                                        value: propBasicInfo[ATTRS.locale.name] },
	                                    _react2.default.createElement(
	                                        'option',
	                                        { value: '' },
	                                        ATTRS.locale[LANG]
	                                    ),
	                                    Object.keys(ENUMS.locale).map(function (localeKey, index) {
	                                        var LOCALE_ENUM = ENUMS.locale[localeKey];
	                                        return _react2.default.createElement(
	                                            'option',
	                                            { key: index, value: LOCALE_ENUM.name },
	                                            LOCALE_ENUM[LANG]
	                                        );
	                                    })
	                                )
	                            )
	                        ),
	                        _react2.default.createElement(
	                            'div',
	                            { className: 'form-group' },
	                            _react2.default.createElement(
	                                'label',
	                                { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                                ATTRS.state[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'div',
	                                { className: 'col-lg-5' },
	                                _react2.default.createElement(
	                                    'select',
	                                    { className: 'form-control', onChange: this.stateChange,
	                                        value: propBasicInfo[ATTRS.state.name] },
	                                    _react2.default.createElement(
	                                        'option',
	                                        { value: '' },
	                                        ATTRS.state[LANG]
	                                    ),
	                                    Object.keys(ENUMS.modifiableState).map(function (stateKey, index) {
	                                        var STATE_ENUM = ENUMS.modifiableState[stateKey];

	                                        return _react2.default.createElement(
	                                            'option',
	                                            { key: index, value: STATE_ENUM.name },
	                                            STATE_ENUM[LANG]
	                                        );
	                                    })
	                                )
	                            )
	                        ),
	                        _react2.default.createElement(
	                            'div',
	                            { className: 'form-group' },
	                            _react2.default.createElement(
	                                'label',
	                                { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                                ATTRS.castellan.primaryEmail[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'div',
	                                { className: 'col-lg-5' },
	                                _react2.default.createElement(
	                                    'select',
	                                    { className: 'form-control', onChange: this.primaryEmailChange,
	                                        value: propBasicInfo.castellan[ATTRS.castellan.primaryEmail.name] },
	                                    _react2.default.createElement(
	                                        'option',
	                                        { value: '' },
	                                        ATTRS.castellan.primaryEmail[LANG]
	                                    ),
	                                    this.props.emailBook.emails.map(function (email, index) {

	                                        return _react2.default.createElement(
	                                            'option',
	                                            { key: index, value: email.email },
	                                            email.email
	                                        );
	                                    })
	                                )
	                            )
	                        ),
	                        _react2.default.createElement(
	                            'div',
	                            { className: 'form-group' },
	                            _react2.default.createElement(
	                                'label',
	                                { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                                ATTRS.castellan.primaryPhone[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'div',
	                                { className: 'col-lg-5' },
	                                _react2.default.createElement(
	                                    'select',
	                                    { className: 'form-control', onChange: this.primaryPhoneNumberChange,
	                                        value: propBasicInfo.castellan[ATTRS.castellan.primaryPhone.name] },
	                                    _react2.default.createElement(
	                                        'option',
	                                        { value: '' },
	                                        ATTRS.castellan.primaryPhone[LANG]
	                                    ),
	                                    this.props.phoneBook.phones.map(function (phone, index) {

	                                        return _react2.default.createElement(
	                                            'option',
	                                            { key: index, value: phone.phoneNumber },
	                                            phone.phoneNumber
	                                        );
	                                    })
	                                )
	                            )
	                        ),
	                        _react2.default.createElement(
	                            'div',
	                            { className: 'form-group' },
	                            _react2.default.createElement(
	                                'label',
	                                { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                                ATTRS.castellan.photo[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'div',
	                                { className: 'col-lg-5' },
	                                _react2.default.createElement(
	                                    'p',
	                                    { className: 'form-control-static' },
	                                    propBasicInfo.castellan[ATTRS.castellan.photo.name]
	                                )
	                            )
	                        ),
	                        _react2.default.createElement(
	                            'div',
	                            { className: 'form-group' },
	                            _react2.default.createElement(
	                                'label',
	                                { className: 'col-lg-3 col-lg-offset-1 control-label' },
	                                ATTRS.buildTime[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'div',
	                                { className: 'col-lg-5' },
	                                _react2.default.createElement(
	                                    'p',
	                                    { className: 'form-control-static' },
	                                    _nara.Date.parseToString(propBasicInfo[ATTRS.buildTime.name])
	                                )
	                            )
	                        ),
	                        _react2.default.createElement(
	                            'div',
	                            { className: 'btn-toolbar pull-right' },
	                            _react2.default.createElement(
	                                'button',
	                                { type: 'button', className: 'btn-group btn btn-primary', onClick: this.saveBtnClick },
	                                BUTTON_NAMES.save[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'button',
	                                { type: 'button', className: 'btn-group btn btn-default', onClick: this.cancelModificationBtnClick },
	                                BUTTON_NAMES.cancel[LANG]
	                            )
	                        )
	                    )
	                )
	            );
	        }
	    }]);

	    return BasicModifiableContent;
	}(_react.Component);

	BasicModifiableContent.propTypes = {
	    //
	    basicInfo: _react.PropTypes.shape({
	        castellan: _react.PropTypes.object.isRequired
	    }).isRequired,
	    nameBook: _react.PropTypes.shape({
	        names: _react.PropTypes.array.isRequired
	    }).isRequired,
	    emailBook: _react.PropTypes.shape({
	        emails: _react.PropTypes.array.isRequired
	    }).isRequired,
	    phoneBook: _react.PropTypes.shape({
	        phones: _react.PropTypes.array.isRequired
	    }).isRequired,

	    changeViewMode: _react.PropTypes.func.isRequired,
	    setBasic: _react.PropTypes.func.isRequired,
	    modifyBasic: _react.PropTypes.func.isRequired
	};

	exports.default = BasicContent;

/***/ },
/* 19 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _nara = __webpack_require__(2);

	var _castleCommon = __webpack_require__(1);

	var _castleModel = __webpack_require__(8);

	var _castleModel2 = _interopRequireDefault(_castleModel);

	var _main = __webpack_require__(9);

	var _main2 = _interopRequireDefault(_main);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-12.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	// Define Content attributes name
	var CastleNameModel = {
	    //
	    attrs: {
	        familyName: { name: 'familyName', KOR: '성', USA: 'Family name' },
	        firstName: { name: 'firstName', KOR: '이름', USA: 'First name' },
	        displayName: { name: 'displayName', KOR: '전체이름', USA: 'Display name' },
	        middleName: { name: 'middleName', KOR: '중간이름', USA: 'Middle name' },
	        langCode: { name: 'langCode', KOR: '언어', USA: 'Language' }
	    },
	    messages: {
	        notRegisteredName: { KOR: '등록된 Name이 없습니다', USA: 'Not registered the name' },
	        completeSave: { KOR: 'NameBook이 저장되었습니다.', USA: 'Save has been completed.' }
	    }
	};

	// Define components

	var NameContent = function (_Component) {
	    _inherits(NameContent, _Component);

	    //

	    function NameContent(props) {
	        _classCallCheck(this, NameContent);

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(NameContent).call(this, props));

	        _this.setNameBook = _this.setNameBook.bind(_this);
	        _this.requestFindNameBook = _this.requestFindNameBook.bind(_this);
	        _this.requestAttachNameBook = _this.requestAttachNameBook.bind(_this);
	        return _this;
	    }
	    // overriding


	    _createClass(NameContent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            this.requestFindNameBook(this.props);
	        }
	        // custom

	    }, {
	        key: 'setNameBook',
	        value: function setNameBook(nameBook) {
	            var castle = this.props.castle;

	            castle.contact.nameBook = nameBook;
	            this.props.setCastle(castle);
	        }
	        // request

	    }, {
	        key: 'requestFindNameBook',
	        value: function requestFindNameBook(props) {
	            //
	            _nara.Ajax.getJSON(NameContent.url.FIND_NAME_BOOK.replace('{id}', props.castleId)).done(function (nameBookResult) {
	                this.setNameBook(nameBookResult);
	            }.bind(this));
	        }
	    }, {
	        key: 'requestAttachNameBook',
	        value: function requestAttachNameBook(nameBook) {
	            //
	            _nara.Ajax.postJSON(NameContent.url.ATTACH_NAME_BOOK.replace('{id}', this.props.castleId), nameBook).done(function () {
	                this.setNameBook(nameBook);

	                var lang = _main2.default.lang;
	                alert(CastleNameModel.messages.completeSave[lang]);
	                this.props.changeViewMode();
	            }.bind(this));
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            return this.props.modifiable ? _react2.default.createElement(NameModifiableContent, {
	                nameBook: this.props.castle.contact.nameBook,
	                changeViewMode: this.props.changeViewMode,
	                saveNameBook: this.requestAttachNameBook
	            }) : _react2.default.createElement(NameViewContent, {
	                nameBook: this.props.castle.contact.nameBook,
	                changeModifiableMode: this.props.changeModifiableMode
	            });
	        }
	    }]);

	    return NameContent;
	}(_react.Component);

	NameContent.url = {
	    //
	    FIND_NAME_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}/contacts/name-book',
	    ATTACH_NAME_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}/contacts/name-book'
	};
	NameContent.propTypes = {
	    //
	    castleId: _react.PropTypes.string,
	    castle: _react.PropTypes.shape({
	        contact: _react.PropTypes.shape({
	            nameBook: _react.PropTypes.shape({
	                names: _react.PropTypes.array
	            })
	        })
	    }),
	    modifiable: _react.PropTypes.bool,

	    changeModifiableMode: _react.PropTypes.func,
	    changeViewMode: _react.PropTypes.func,
	    setCastle: _react.PropTypes.func
	};

	var NameViewContent = function (_Component2) {
	    _inherits(NameViewContent, _Component2);

	    //

	    function NameViewContent(props) {
	        _classCallCheck(this, NameViewContent);

	        var _this2 = _possibleConstructorReturn(this, Object.getPrototypeOf(NameViewContent).call(this, props));

	        _this2.modifiableModeBtnClick = _this2.modifiableModeBtnClick.bind(_this2);
	        _this2.removeBtnClick = _this2.removeBtnClick.bind(_this2);
	        return _this2;
	    }
	    // event


	    _createClass(NameViewContent, [{
	        key: 'modifiableModeBtnClick',
	        value: function modifiableModeBtnClick() {
	            this.props.changeModifiableMode();
	        }
	    }, {
	        key: 'removeBtnClick',
	        value: function removeBtnClick() {
	            // TODO: NameBook 삭제 기능
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var ENUMS = _castleModel2.default.enums,
	                BUTTON_NAMES = _castleModel2.default.buttons,
	                ATTRS = CastleNameModel.attrs,
	                MESSAGES = CastleNameModel.messages,
	                LANG = _main2.default.lang;

	            var existsNameBook = this.props.nameBook && this.props.nameBook.names.length > 0 ? true : false;

	            return _react2.default.createElement(
	                'article',
	                null,
	                _react2.default.createElement(
	                    'table',
	                    { className: 'table table-striped table-hover' },
	                    _react2.default.createElement(
	                        'thead',
	                        null,
	                        _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-2' },
	                                ATTRS.familyName[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-2' },
	                                ATTRS.firstName[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-3' },
	                                ATTRS.displayName[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-3' },
	                                ATTRS.middleName[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-2' },
	                                ATTRS.langCode[LANG]
	                            )
	                        )
	                    ),
	                    _react2.default.createElement(
	                        'tbody',
	                        null,
	                        existsNameBook ? this.props.nameBook.names.map(function (name, index) {
	                            return _react2.default.createElement(
	                                'tr',
	                                { key: index },
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    name[ATTRS.familyName.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    name[ATTRS.firstName.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    name[ATTRS.displayName.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    name[ATTRS.middleName.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    ENUMS.language[name[ATTRS.langCode.name]][LANG]
	                                )
	                            );
	                        }) : _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'td',
	                                { colSpan: '5' },
	                                MESSAGES.notRegisteredName[LANG]
	                            )
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'btn-toolbar pull-right' },
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-default',
	                            onClick: this.modifiableModeBtnClick },
	                        BUTTON_NAMES.modify[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-danger',
	                            onClick: this.removeBtnClick },
	                        BUTTON_NAMES.remove[LANG]
	                    )
	                )
	            );
	        }
	    }]);

	    return NameViewContent;
	}(_react.Component);

	NameViewContent.propTypes = {
	    //
	    nameBook: _react.PropTypes.shape({
	        names: _react.PropTypes.array.isRequired
	    }).isRequired,
	    changeModifiableMode: _react.PropTypes.func.isRequired
	};

	var NameModifiableContent = function (_Component3) {
	    _inherits(NameModifiableContent, _Component3);

	    //

	    function NameModifiableContent(props) {
	        _classCallCheck(this, NameModifiableContent);

	        var _this3 = _possibleConstructorReturn(this, Object.getPrototypeOf(NameModifiableContent).call(this, props));

	        _this3.state = {
	            willSaveNames: [],
	            inputProgressNames: [],
	            rowsModifiable: []
	        };

	        _this3.familyNameChange = _this3.familyNameChange.bind(_this3);
	        _this3.firstNameChange = _this3.firstNameChange.bind(_this3);
	        _this3.displayNameChange = _this3.displayNameChange.bind(_this3);
	        _this3.middleNameChange = _this3.middleNameChange.bind(_this3);
	        _this3.langCodeChange = _this3.langCodeChange.bind(_this3);
	        _this3.addNameBtnClick = _this3.addNameBtnClick.bind(_this3);
	        _this3.modifyNameBtnClick = _this3.modifyNameBtnClick.bind(_this3);
	        _this3.removeNameBtnClick = _this3.removeNameBtnClick.bind(_this3);
	        _this3.completeNameBtnClick = _this3.completeNameBtnClick.bind(_this3);

	        _this3.cancelNameBtnClick = _this3.cancelNameBtnClick.bind(_this3);
	        _this3.saveNameBookBtnClick = _this3.saveNameBookBtnClick.bind(_this3);
	        _this3.cancelModificationBtnClick = _this3.cancelModificationBtnClick.bind(_this3);
	        _this3.setRowModifiableState = _this3.setRowModifiableState.bind(_this3);
	        _this3.setProgressNameState = _this3.setProgressNameState.bind(_this3);
	        return _this3;
	    }
	    // overriding


	    _createClass(NameModifiableContent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            var names = _nara.Object.deepCopy(this.props.nameBook.names),
	                rowsModifiable = [];

	            rowsModifiable.length = names.length;
	            rowsModifiable.fill(false);

	            this.setState({
	                willSaveNames: names,
	                inputProgressNames: _nara.Object.deepCopy(names),
	                rowsModifiable: rowsModifiable
	            });
	        }
	        // event

	    }, {
	        key: 'familyNameChange',
	        value: function familyNameChange(index, event) {
	            this.setProgressNameState(index, CastleNameModel.attrs.familyName.name, event.target.value);
	        }
	    }, {
	        key: 'firstNameChange',
	        value: function firstNameChange(index, event) {
	            this.setProgressNameState(index, CastleNameModel.attrs.firstName.name, event.target.value);
	        }
	    }, {
	        key: 'displayNameChange',
	        value: function displayNameChange(index, event) {
	            this.setProgressNameState(index, CastleNameModel.attrs.displayName.name, event.target.value);
	        }
	    }, {
	        key: 'middleNameChange',
	        value: function middleNameChange(index, event) {
	            this.setProgressNameState(index, CastleNameModel.attrs.middleName.name, event.target.value);
	        }
	    }, {
	        key: 'langCodeChange',
	        value: function langCodeChange(index, event) {
	            this.setProgressNameState(index, CastleNameModel.attrs.langCode.name, event.target.value);
	        }
	    }, {
	        key: 'addNameBtnClick',
	        value: function addNameBtnClick() {
	            var names = this.state.inputProgressNames,
	                rowsModifiable = this.state.rowsModifiable;

	            names.push({});
	            rowsModifiable.push(true);

	            this.setState({ inputProgressNames: names, rowsModifiable: rowsModifiable });
	        }
	    }, {
	        key: 'modifyNameBtnClick',
	        value: function modifyNameBtnClick(index) {
	            this.setRowModifiableState(index, true);
	        }
	    }, {
	        key: 'removeNameBtnClick',
	        value: function removeNameBtnClick(index) {
	            var progressNames = this.state.inputProgressNames,
	                willSaveNames = this.state.willSaveNames,
	                rowsModifiable = this.state.rowsModifiable;

	            progressNames.splice(index, 1);
	            willSaveNames.splice(index, 1);
	            rowsModifiable.splice(index, 1);

	            this.setState({
	                inputProgressNames: progressNames,
	                willSaveNames: willSaveNames,
	                rowsModifiable: rowsModifiable
	            });
	        }
	    }, {
	        key: 'completeNameBtnClick',
	        value: function completeNameBtnClick(index) {
	            var names = _nara.Object.deepCopy(this.state.willSaveNames);
	            names[index] = _nara.Object.deepCopy(this.state.inputProgressNames[index]);

	            this.setState({ willSaveNames: names });
	            this.setRowModifiableState(index, false);
	        }
	    }, {
	        key: 'cancelNameBtnClick',
	        value: function cancelNameBtnClick(index) {
	            var names = _nara.Object.deepCopy(this.state.inputProgressNames);
	            names[index] = _nara.Object.deepCopy(this.state.willSaveNames[index]);

	            if (_nara.Object.isEmpty(names[index])) {
	                names.splice(index, 1);

	                var rowsModifiable = this.state.rowsModifiable;
	                rowsModifiable.splice(index, 1);

	                this.setState({ rowsModifiable: rowsModifiable });
	            } else {
	                this.setRowModifiableState(index, false);
	            }
	            this.setState({ inputProgressNames: names });
	        }
	    }, {
	        key: 'saveNameBookBtnClick',
	        value: function saveNameBookBtnClick() {
	            this.props.saveNameBook({ names: this.state.willSaveNames });
	        }
	    }, {
	        key: 'cancelModificationBtnClick',
	        value: function cancelModificationBtnClick() {
	            this.props.changeViewMode();
	        }
	        // custom

	    }, {
	        key: 'setRowModifiableState',
	        value: function setRowModifiableState(index, modifiable) {
	            //
	            var rowsModifiable = this.state.rowsModifiable;

	            rowsModifiable[index] = modifiable;
	            this.setState({ rowsModifiable: rowsModifiable });
	        }
	    }, {
	        key: 'setProgressNameState',
	        value: function setProgressNameState(index, propertyName, value) {
	            //
	            var names = this.state.inputProgressNames;

	            names[index][propertyName] = value;
	            this.setState({ inputProgressNames: names });
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var BUTTON_NAMES = _castleModel2.default.buttons,
	                ENUMS = _castleModel2.default.enums,
	                ATTRS = CastleNameModel.attrs,
	                MESSAGES = CastleNameModel.messages,
	                LANG = _main2.default.lang;

	            var existsNameBook = this.state.willSaveNames.length > 0;

	            return _react2.default.createElement(
	                'article',
	                null,
	                _react2.default.createElement(
	                    'table',
	                    { className: 'table table-striped table-hover' },
	                    _react2.default.createElement(
	                        'thead',
	                        null,
	                        _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-2' },
	                                ATTRS.familyName[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-2' },
	                                ATTRS.firstName[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-3' },
	                                ATTRS.displayName[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-2' },
	                                ATTRS.middleName[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-1' },
	                                ATTRS.langCode[LANG]
	                            ),
	                            _react2.default.createElement('th', { className: 'col-md-2' })
	                        )
	                    ),
	                    _react2.default.createElement(
	                        'tbody',
	                        null,
	                        existsNameBook ? this.state.inputProgressNames.map(function (name, index) {
	                            return this.state.rowsModifiable[index] === true ? _react2.default.createElement(
	                                'tr',
	                                { key: index },
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.familyNameChange.bind(this, index),
	                                        value: name[ATTRS.familyName.name] })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.firstNameChange.bind(this, index),
	                                        value: name[ATTRS.firstName.name] })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.displayNameChange.bind(this, index),
	                                        value: name[ATTRS.displayName.name] })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.middleNameChange.bind(this, index),
	                                        value: name[ATTRS.middleName.name] })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement(
	                                        'select',
	                                        { className: 'form-control',
	                                            onChange: this.langCodeChange.bind(this, index),
	                                            value: name[ATTRS.langCode.name] },
	                                        _react2.default.createElement(
	                                            'option',
	                                            null,
	                                            ATTRS.langCode[LANG]
	                                        ),
	                                        Object.keys(ENUMS.language).map(function (languageKey, index) {
	                                            var LANG_ENUM = ENUMS.language[languageKey];

	                                            return _react2.default.createElement(
	                                                'option',
	                                                { key: index, value: LANG_ENUM.name },
	                                                LANG_ENUM[LANG]
	                                            );
	                                        })
	                                    )
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement(
	                                        'div',
	                                        { className: 'btn-group btn-group-sm text-center' },
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-primary',
	                                                onClick: this.completeNameBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.complete[LANG]
	                                        ),
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-default',
	                                                onClick: this.cancelNameBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.cancel[LANG]
	                                        )
	                                    )
	                                )
	                            ) : _react2.default.createElement(
	                                'tr',
	                                { key: index },
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement(
	                                        'p',
	                                        null,
	                                        name[ATTRS.familyName.name]
	                                    )
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    name[ATTRS.firstName.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    name[ATTRS.displayName.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    name[ATTRS.middleName.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    ENUMS.language[name[ATTRS.langCode.name]][LANG]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement(
	                                        'div',
	                                        { className: 'btn-group btn-group-sm text-center' },
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-default',
	                                                onClick: this.modifyNameBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.modify[LANG]
	                                        ),
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-danger',
	                                                onClick: this.removeNameBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.remove[LANG]
	                                        )
	                                    )
	                                )
	                            );
	                        }.bind(this)) : _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'td',
	                                { colSpan: '5' },
	                                MESSAGES.notRegisteredName[LANG]
	                            )
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'btn-block btn-toolbar' },
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'pull-right' },
	                        _react2.default.createElement(
	                            'button',
	                            { type: 'button', className: 'btn btn-default btn-sm',
	                                onClick: this.addNameBtnClick },
	                            BUTTON_NAMES.add[LANG]
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'row' },
	                    _react2.default.createElement('p', null)
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'btn-toolbar pull-right' },
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn btn-primary',
	                            onClick: this.saveNameBookBtnClick },
	                        BUTTON_NAMES.save[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn btn-default',
	                            onClick: this.cancelModificationBtnClick },
	                        BUTTON_NAMES.cancel[LANG]
	                    )
	                )
	            );
	        }
	    }]);

	    return NameModifiableContent;
	}(_react.Component);

	NameModifiableContent.propTypes = {
	    //
	    nameBook: _react.PropTypes.shape({
	        names: _react.PropTypes.array.isRequired
	    }).isRequired,
	    changeViewMode: _react.PropTypes.func.isRequired,
	    saveNameBook: _react.PropTypes.func.isRequired
	};

	exports.default = NameContent;

/***/ },
/* 20 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _nara = __webpack_require__(2);

	var _castleCommon = __webpack_require__(1);

	var _castleModel = __webpack_require__(8);

	var _castleModel2 = _interopRequireDefault(_castleModel);

	var _main = __webpack_require__(9);

	var _main2 = _interopRequireDefault(_main);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-12.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	// Define Content attributes name
	var CastlePhoneModel = {
	    //
	    attrs: {
	        phoneNumber: { name: 'phoneNumber', KOR: '전체번호', USA: 'Phone number' },
	        countryCode: { name: 'countryCode', KOR: '국가코드', USA: 'Country code' },
	        areaCode: { name: 'areaCode', KOR: '지역코드', USA: 'Area code' },
	        number: { name: 'number', KOR: '번호', USA: 'Number' }
	    },
	    messages: {
	        notRegisteredPhone: { KOR: '등록 된 Phone이 없습니다', USA: 'Not registered the phone' },
	        completeSave: { KOR: 'PhoneBook이 저장되었습니다.', USA: 'Save has been completed.' }
	    }
	};

	// Define components

	var PhoneContent = function (_Component) {
	    _inherits(PhoneContent, _Component);

	    //

	    function PhoneContent(props) {
	        _classCallCheck(this, PhoneContent);

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(PhoneContent).call(this, props));

	        _this.setPhoneBook = _this.setPhoneBook.bind(_this);
	        _this.requestFindPhoneBook = _this.requestFindPhoneBook.bind(_this);
	        _this.requestSavePhoneBook = _this.requestSavePhoneBook.bind(_this);
	        return _this;
	    }
	    // overriding


	    _createClass(PhoneContent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            this.requestFindPhoneBook(this.props.castleId);
	        }
	        // custom

	    }, {
	        key: 'setPhoneBook',
	        value: function setPhoneBook(phoneBook) {
	            var castle = this.props.castle;

	            castle.contact.phoneBook = phoneBook;
	            this.props.setCastle(castle);
	        }
	        // request

	    }, {
	        key: 'requestFindPhoneBook',
	        value: function requestFindPhoneBook(castleId) {
	            //
	            _nara.Ajax.getJSON(PhoneContent.url.FIND_PHONE_BOOK.replace('{id}', castleId)).done(function (phoneBookResult) {
	                this.setPhoneBook(phoneBookResult);
	            }.bind(this));
	        }
	    }, {
	        key: 'requestSavePhoneBook',
	        value: function requestSavePhoneBook(phoneBook) {
	            //
	            _nara.Ajax.postJSON(PhoneContent.url.ATTACH_PHONE_BOOK.replace('{id}', this.props.castleId), phoneBook).done(function () {
	                var lang = _main2.default.lang;

	                this.setPhoneBook(phoneBook);
	                alert(CastlePhoneModel.messages.completeSave[lang]);
	                this.props.changeViewMode();
	            }.bind(this));
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            return this.props.modifiable ? _react2.default.createElement(PhoneModifiableContent, {
	                phoneBook: this.props.castle.contact.phoneBook,
	                changeViewMode: this.props.changeViewMode,
	                savePhoneBook: this.requestSavePhoneBook
	            }) : _react2.default.createElement(PhoneViewContent, {
	                phoneBook: this.props.castle.contact.phoneBook,
	                changeModifiableMode: this.props.changeModifiableMode
	            });
	        }
	    }]);

	    return PhoneContent;
	}(_react.Component);

	PhoneContent.propTypes = {
	    //
	    castleId: _react.PropTypes.string,
	    castle: _react.PropTypes.shape({
	        contact: _react.PropTypes.shape({
	            phoneBook: _react.PropTypes.shape({
	                phones: _react.PropTypes.array
	            })
	        })
	    }),
	    modifiable: _react.PropTypes.bool,

	    changeModifiableMode: _react.PropTypes.func,
	    changeViewMode: _react.PropTypes.func,
	    setCastle: _react.PropTypes.func
	};

	PhoneContent.url = {
	    //
	    FIND_PHONE_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}/contacts/phone-book',
	    ATTACH_PHONE_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}/contacts/phone-book'
	};

	var PhoneViewContent = function (_Component2) {
	    _inherits(PhoneViewContent, _Component2);

	    //

	    function PhoneViewContent(props) {
	        _classCallCheck(this, PhoneViewContent);

	        var _this2 = _possibleConstructorReturn(this, Object.getPrototypeOf(PhoneViewContent).call(this, props));

	        _this2.modifiableModeBtnClick = _this2.modifiableModeBtnClick.bind(_this2);
	        return _this2;
	    }
	    // event


	    _createClass(PhoneViewContent, [{
	        key: 'modifiableModeBtnClick',
	        value: function modifiableModeBtnClick() {
	            this.props.changeModifiableMode();
	        }
	    }, {
	        key: 'removeBtnClick',
	        value: function removeBtnClick() {}
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var BUTTON_NAMES = _castleModel2.default.buttons,
	                ATTRS = CastlePhoneModel.attrs,
	                MESSAGES = CastlePhoneModel.messages,
	                LANG = _main2.default.lang;

	            var propPhoneBook = this.props.phoneBook,
	                existsPhoneBook = propPhoneBook.phones.length > 0;

	            return _react2.default.createElement(
	                'article',
	                null,
	                _react2.default.createElement(
	                    'table',
	                    { className: 'table table-striped table-hover' },
	                    _react2.default.createElement(
	                        'thead',
	                        null,
	                        _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.phoneNumber[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.countryCode[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.areaCode[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.number[LANG]
	                            )
	                        )
	                    ),
	                    _react2.default.createElement(
	                        'tbody',
	                        null,
	                        existsPhoneBook ? propPhoneBook.phones.map(function (phone, index) {
	                            return _react2.default.createElement(
	                                'tr',
	                                { key: index },
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    phone[ATTRS.phoneNumber.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    phone[ATTRS.countryCode.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    phone[ATTRS.areaCode.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    phone[ATTRS.number.name]
	                                )
	                            );
	                        }) : _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'td',
	                                { colSpan: '4' },
	                                MESSAGES.notRegisteredPhone[LANG]
	                            )
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'btn-toolbar pull-right' },
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-default',
	                            onClick: this.modifiableModeBtnClick },
	                        BUTTON_NAMES.modify[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-danger',
	                            onClick: this.removeBtnClick },
	                        BUTTON_NAMES.remove[LANG]
	                    )
	                )
	            );
	        }
	    }]);

	    return PhoneViewContent;
	}(_react.Component);

	PhoneViewContent.propTypes = {
	    //
	    phoneBook: _react.PropTypes.shape({
	        phones: _react.PropTypes.array.isRequired
	    }).isRequired,
	    changeModifiableMode: _react.PropTypes.func.isRequired
	};

	var PhoneModifiableContent = function (_Component3) {
	    _inherits(PhoneModifiableContent, _Component3);

	    //

	    function PhoneModifiableContent(props) {
	        _classCallCheck(this, PhoneModifiableContent);

	        var _this3 = _possibleConstructorReturn(this, Object.getPrototypeOf(PhoneModifiableContent).call(this, props));

	        _this3.state = {
	            willModifyPhones: [],
	            inputProgressPhones: [],
	            rowsModifiable: []
	        };

	        _this3.countryCodeChange = _this3.countryCodeChange.bind(_this3);
	        _this3.areaCodeChange = _this3.areaCodeChange.bind(_this3);
	        _this3.numberChange = _this3.numberChange.bind(_this3);
	        _this3.completePhoneBtnClick = _this3.completePhoneBtnClick.bind(_this3);
	        _this3.cancelPhoneBtnClick = _this3.cancelPhoneBtnClick.bind(_this3);
	        _this3.modifyPhoneBtnClick = _this3.modifyPhoneBtnClick.bind(_this3);
	        _this3.removePhoneBtnClick = _this3.removePhoneBtnClick.bind(_this3);
	        _this3.addPhoneBtnClick = _this3.addPhoneBtnClick.bind(_this3);
	        _this3.savePhoneBookBtnClick = _this3.savePhoneBookBtnClick.bind(_this3);
	        _this3.cancelModificationBtnClick = _this3.cancelModificationBtnClick.bind(_this3);
	        _this3.setRowModifiableState = _this3.setRowModifiableState.bind(_this3);
	        _this3.setProgressPhoneState = _this3.setProgressPhoneState.bind(_this3);
	        return _this3;
	    }
	    // overriding


	    _createClass(PhoneModifiableContent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            var phones = _nara.Object.deepCopy(this.props.phoneBook.phones),
	                rowsModifiable = [];

	            rowsModifiable.length = phones.length;
	            rowsModifiable.fill(false);

	            this.setState({
	                willModifyPhones: phones,
	                inputProgressPhones: _nara.Object.deepCopy(phones),
	                rowsModifiable: rowsModifiable
	            });
	        }
	        // event

	    }, {
	        key: 'countryCodeChange',
	        value: function countryCodeChange(index, event) {
	            this.setProgressPhoneState(index, CastlePhoneModel.attrs.countryCode.name, event.target.value);
	        }
	    }, {
	        key: 'areaCodeChange',
	        value: function areaCodeChange(index, event) {
	            this.setProgressPhoneState(index, CastlePhoneModel.attrs.areaCode.name, event.target.value);
	        }
	    }, {
	        key: 'numberChange',
	        value: function numberChange(index, event) {
	            this.setProgressPhoneState(index, CastlePhoneModel.attrs.number.name, event.target.value);
	        }
	    }, {
	        key: 'completePhoneBtnClick',
	        value: function completePhoneBtnClick(index) {
	            var phones = _nara.Object.deepCopy(this.state.willModifyPhones),
	                completedPhone = this.state.inputProgressPhones[index];

	            completedPhone.phoneNumber = completedPhone.countryCode + '-' + completedPhone.areaCode + '-' + completedPhone.number;
	            phones[index] = _nara.Object.deepCopy(completedPhone);

	            this.setState({ willModifyPhones: phones });
	            this.setRowModifiableState(index, false);
	        }
	    }, {
	        key: 'cancelPhoneBtnClick',
	        value: function cancelPhoneBtnClick(index) {
	            var phones = _nara.Object.deepCopy(this.state.inputProgressPhones);
	            phones[index] = _nara.Object.deepCopy(this.state.willModifyPhones[index]);

	            if (_nara.Object.isEmpty(phones[index])) {
	                phones.splice(index, 1);

	                var rowsModifiable = this.state.rowsModifiable;
	                rowsModifiable.splice(index, 1);

	                this.setState({ rowsModifiable: rowsModifiable });
	            } else {
	                this.setRowModifiableState(index, false);
	            }
	            this.setState({ inputProgressPhones: phones });
	        }
	    }, {
	        key: 'modifyPhoneBtnClick',
	        value: function modifyPhoneBtnClick(index) {
	            this.setRowModifiableState(index, true);
	        }
	    }, {
	        key: 'removePhoneBtnClick',
	        value: function removePhoneBtnClick(index) {
	            var progressPhones = this.state.inputProgressPhones,
	                willModifyPhones = this.state.willModifyPhones,
	                rowsModifiable = this.state.rowsModifiable;

	            progressPhones.splice(index, 1);
	            willModifyPhones.splice(index, 1);
	            rowsModifiable.splice(index, 1);

	            this.setState({
	                inputProgressPhones: progressPhones,
	                willModifyPhones: willModifyPhones,
	                rowsModifiable: rowsModifiable
	            });
	        }
	    }, {
	        key: 'addPhoneBtnClick',
	        value: function addPhoneBtnClick() {
	            var phones = this.state.inputProgressPhones,
	                rowsModifiable = this.state.rowsModifiable;

	            phones.push({});
	            rowsModifiable.push(true);

	            this.setState({ inputProgressPhones: phones, rowsModifiable: rowsModifiable });
	        }
	    }, {
	        key: 'savePhoneBookBtnClick',
	        value: function savePhoneBookBtnClick() {
	            this.props.savePhoneBook({ phones: this.state.willModifyPhones });
	        }
	    }, {
	        key: 'cancelModificationBtnClick',
	        value: function cancelModificationBtnClick() {
	            this.props.changeViewMode();
	        }
	        // custom

	    }, {
	        key: 'setRowModifiableState',
	        value: function setRowModifiableState(index, modifiable) {
	            //
	            var rowsModifiable = this.state.rowsModifiable;

	            rowsModifiable[index] = modifiable;
	            this.setState({ rowsModifiable: rowsModifiable });
	        }
	    }, {
	        key: 'setProgressPhoneState',
	        value: function setProgressPhoneState(index, propertyName, value) {
	            //
	            var phones = this.state.inputProgressPhones;

	            phones[index][propertyName] = value;
	            this.setState({ inputProgressPhones: phones });
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var ATTRS = CastlePhoneModel.attrs,
	                BUTTON_NAMES = _castleModel2.default.buttons,
	                MESSAGES = CastlePhoneModel.messages,
	                LANG = _main2.default.lang;

	            var propPhoneBook = this.props.phoneBook,
	                existsPhoneBook = propPhoneBook.phones.length > 0;

	            return _react2.default.createElement(
	                'article',
	                null,
	                _react2.default.createElement(
	                    'table',
	                    { className: 'table table-striped table-hover' },
	                    _react2.default.createElement(
	                        'thead',
	                        null,
	                        _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-3' },
	                                ATTRS.phoneNumber[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-2' },
	                                ATTRS.countryCode[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-2' },
	                                ATTRS.areaCode[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-3' },
	                                ATTRS.number[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-2' },
	                                ' '
	                            )
	                        )
	                    ),
	                    _react2.default.createElement(
	                        'tbody',
	                        null,
	                        existsPhoneBook ? this.state.inputProgressPhones.map(function (phone, index) {
	                            return this.state.rowsModifiable[index] === true ? _react2.default.createElement(
	                                'tr',
	                                { key: index },
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control', readOnly: 'readOnly',
	                                        value: phone[ATTRS.phoneNumber.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.countryCodeChange.bind(this, index),
	                                        value: phone[ATTRS.countryCode.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.areaCodeChange.bind(this, index),
	                                        value: phone[ATTRS.areaCode.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.numberChange.bind(this, index),
	                                        value: phone[ATTRS.number.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement(
	                                        'div',
	                                        { className: 'btn-group btn-group-sm text-center' },
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-primary',
	                                                onClick: this.completePhoneBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.complete[LANG]
	                                        ),
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-default',
	                                                onClick: this.cancelPhoneBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.cancel[LANG]
	                                        )
	                                    )
	                                )
	                            ) : _react2.default.createElement(
	                                'tr',
	                                { key: index },
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    phone[ATTRS.phoneNumber.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    phone[ATTRS.countryCode.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    phone[ATTRS.areaCode.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    phone[ATTRS.number.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement(
	                                        'div',
	                                        { className: 'btn-group btn-group-sm text-center' },
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-default',
	                                                onClick: this.modifyPhoneBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.modify[LANG]
	                                        ),
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-danger',
	                                                onClick: this.removePhoneBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.remove[LANG]
	                                        )
	                                    )
	                                )
	                            );
	                        }.bind(this)) : _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'td',
	                                { colSpan: '5' },
	                                MESSAGES.notRegisteredPhone[LANG]
	                            )
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'btn-block btn-toolbar' },
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'pull-right' },
	                        _react2.default.createElement(
	                            'button',
	                            { type: 'button', className: 'btn btn-default btn-sm',
	                                onClick: this.addPhoneBtnClick },
	                            BUTTON_NAMES.add[LANG]
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'btn-toolbar pull-right' },
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-primary',
	                            onClick: this.savePhoneBookBtnClick },
	                        BUTTON_NAMES.save[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-default',
	                            onClick: this.cancelModificationBtnClick },
	                        BUTTON_NAMES.cancel[LANG]
	                    )
	                )
	            );
	        }
	    }]);

	    return PhoneModifiableContent;
	}(_react.Component);

	PhoneModifiableContent.propTypes = {
	    //
	    phoneBook: _react.PropTypes.shape({
	        phones: _react.PropTypes.array.isRequired
	    }).isRequired,
	    changeViewMode: _react.PropTypes.func.isRequired,
	    savePhoneBook: _react.PropTypes.func.isRequired
	};

	exports.default = PhoneContent;

/***/ },
/* 21 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _nara = __webpack_require__(2);

	var _castleCommon = __webpack_require__(1);

	var _castleModel = __webpack_require__(8);

	var _castleModel2 = _interopRequireDefault(_castleModel);

	var _main = __webpack_require__(9);

	var _main2 = _interopRequireDefault(_main);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-12.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	// Define Content attributes name
	var CastleEmailModel = {
	    //
	    attrs: {
	        email: { name: 'email', KOR: '이메일', USA: 'Email' },
	        emailType: { name: 'emailType', KOR: '유형', USA: 'Type' },
	        verified: { name: 'verified', KOR: '유효확인 여부', USA: 'Verified' },
	        verifiedTime: { name: 'verifiedTime', KOR: '유효확인 일시', USA: 'Vefiried time' }
	    },
	    messages: {
	        notRegisteredEmail: { KOR: '등록 된 Email이 없습니다', USA: 'Not registered the email' },
	        completeSave: { KOR: 'EmailBook이 저장되었습니다.', USA: 'Save has been completed.' }
	    }
	};

	// Define components

	var EmailContent = function (_Component) {
	    _inherits(EmailContent, _Component);

	    //

	    function EmailContent(props) {
	        _classCallCheck(this, EmailContent);

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(EmailContent).call(this, props));

	        _this.setEmailBook = _this.setEmailBook.bind(_this);
	        _this.requestFindEmailBook = _this.requestFindEmailBook.bind(_this);
	        _this.requestSaveEmailBook = _this.requestSaveEmailBook.bind(_this);
	        return _this;
	    }
	    // overriding


	    _createClass(EmailContent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            this.requestFindEmailBook(this.props.castleId);
	        }
	        // custom

	    }, {
	        key: 'setEmailBook',
	        value: function setEmailBook(emailBook) {
	            var castle = this.props.castle;

	            castle.contact.emailBook = emailBook;
	            this.props.setCastle(castle);
	        }
	        // request

	    }, {
	        key: 'requestFindEmailBook',
	        value: function requestFindEmailBook(castleId) {
	            //
	            _nara.Ajax.getJSON(EmailContent.url.FIND_EMAIL_BOOK.replace('{id}', castleId)).done(function (emailBookResult) {
	                this.setEmailBook(emailBookResult);
	            }.bind(this));
	        }
	    }, {
	        key: 'requestSaveEmailBook',
	        value: function requestSaveEmailBook(emailBook) {
	            //
	            _nara.Ajax.postJSON(EmailContent.url.ATTACH_EMAIL_BOOK.replace('{id}', this.props.castleId), emailBook).done(function () {
	                var LANG = _main2.default.lang;

	                this.setEmailBook(emailBook);
	                alert(CastleEmailModel.messages.completeSave[LANG]);
	                this.props.changeViewMode();
	            }.bind(this));
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            return this.props.modifiable ? _react2.default.createElement(EmailModifiableContent, {
	                emailBook: this.props.castle.contact.emailBook,
	                changeViewMode: this.props.changeViewMode,
	                saveEmailBook: this.requestSaveEmailBook
	            }) : _react2.default.createElement(EmailViewContent, {
	                emailBook: this.props.castle.contact.emailBook,
	                changeModifiableMode: this.props.changeModifiableMode
	            });
	        }
	    }]);

	    return EmailContent;
	}(_react.Component);

	EmailContent.propTypes = {
	    //
	    castleId: _react.PropTypes.string,
	    castle: _react.PropTypes.shape({
	        contact: _react.PropTypes.shape({
	            emailBook: _react.PropTypes.shape({
	                emails: _react.PropTypes.array
	            })
	        })
	    }),
	    modifiable: _react.PropTypes.bool,

	    changeModifiableMode: _react.PropTypes.func,
	    changeViewMode: _react.PropTypes.func,
	    setCastle: _react.PropTypes.func
	};

	EmailContent.url = {
	    //
	    FIND_EMAIL_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}/contacts/email-book',
	    ATTACH_EMAIL_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}/contacts/email-book'
	};

	var EmailViewContent = function (_Component2) {
	    _inherits(EmailViewContent, _Component2);

	    //

	    function EmailViewContent(props) {
	        _classCallCheck(this, EmailViewContent);

	        var _this2 = _possibleConstructorReturn(this, Object.getPrototypeOf(EmailViewContent).call(this, props));

	        _this2.modifiableModeBtnClick = _this2.modifiableModeBtnClick.bind(_this2);
	        return _this2;
	    }
	    // event


	    _createClass(EmailViewContent, [{
	        key: 'modifiableModeBtnClick',
	        value: function modifiableModeBtnClick() {
	            this.props.changeModifiableMode();
	        }
	    }, {
	        key: 'removeBtnClick',
	        value: function removeBtnClick() {}
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var ENUMS = _castleModel2.default.enums,
	                BUTTON_NAMES = _castleModel2.default.buttons,
	                ATTRS = CastleEmailModel.attrs,
	                MESSAGES = CastleEmailModel.messages,
	                LANG = _main2.default.lang;

	            var propEmailBook = this.props.emailBook,
	                existsEmailBook = propEmailBook.emails.length > 0;

	            return _react2.default.createElement(
	                'article',
	                null,
	                _react2.default.createElement(
	                    'table',
	                    { className: 'table table-striped table-hover' },
	                    _react2.default.createElement(
	                        'thead',
	                        null,
	                        _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.email[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.emailType[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.verified[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.verifiedTime[LANG]
	                            )
	                        )
	                    ),
	                    _react2.default.createElement(
	                        'tbody',
	                        null,
	                        existsEmailBook ? propEmailBook.emails.map(function (email, index) {
	                            return _react2.default.createElement(
	                                'tr',
	                                { key: index },
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    email[ATTRS.email.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    ENUMS.emailType[email[ATTRS.emailType.name]][LANG]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    ENUMS.verified[email[ATTRS.verified.name]][LANG]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _nara.Date.parseToString(email[ATTRS.verifiedTime.name])
	                                )
	                            );
	                        }) : _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'td',
	                                { colSpan: '4' },
	                                MESSAGES.notRegisteredEmail[LANG]
	                            )
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'btn-toolbar pull-right' },
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-default',
	                            onClick: this.modifiableModeBtnClick },
	                        BUTTON_NAMES.modify[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-danger',
	                            onClick: this.removeBtnClick },
	                        BUTTON_NAMES.remove[LANG]
	                    )
	                )
	            );
	        }
	    }]);

	    return EmailViewContent;
	}(_react.Component);

	EmailViewContent.propTypes = {
	    //
	    emailBook: _react.PropTypes.shape({
	        emails: _react.PropTypes.array.isRequired
	    }).isRequired,
	    changeModifiableMode: _react.PropTypes.func.isRequired
	};

	var EmailModifiableContent = function (_Component3) {
	    _inherits(EmailModifiableContent, _Component3);

	    //

	    function EmailModifiableContent(props) {
	        _classCallCheck(this, EmailModifiableContent);

	        var _this3 = _possibleConstructorReturn(this, Object.getPrototypeOf(EmailModifiableContent).call(this, props));

	        _this3.state = {
	            willModifyEmails: [],
	            inputProgressEmails: [],
	            rowsModifiable: []
	        };

	        _this3.emailChange = _this3.emailChange.bind(_this3);
	        _this3.emailTypeChange = _this3.emailTypeChange.bind(_this3);
	        _this3.completeEmailBtnClick = _this3.completeEmailBtnClick.bind(_this3);
	        _this3.cancelEmailBtnClick = _this3.cancelEmailBtnClick.bind(_this3);
	        _this3.modifyEmailBtnClick = _this3.modifyEmailBtnClick.bind(_this3);
	        _this3.removeEmailBtnClick = _this3.removeEmailBtnClick.bind(_this3);
	        _this3.addEmailBtnClick = _this3.addEmailBtnClick.bind(_this3);
	        _this3.saveEmailBookBtnClick = _this3.saveEmailBookBtnClick.bind(_this3);
	        _this3.cancelModificationBtnClick = _this3.cancelModificationBtnClick.bind(_this3);
	        _this3.setRowModifiableState = _this3.setRowModifiableState.bind(_this3);
	        _this3.setProgressEmailState = _this3.setProgressEmailState.bind(_this3);
	        return _this3;
	    }
	    // overriding


	    _createClass(EmailModifiableContent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            var emails = _nara.Object.deepCopy(this.props.emailBook.emails),
	                rowsModifiable = [];

	            rowsModifiable.length = emails.length;
	            rowsModifiable.fill(false);

	            this.setState({
	                willModifyEmails: emails,
	                inputProgressEmails: _nara.Object.deepCopy(emails),
	                rowsModifiable: rowsModifiable
	            });
	        }
	        // event

	    }, {
	        key: 'emailChange',
	        value: function emailChange(index, event) {
	            this.setProgressEmailState(index, CastleEmailModel.attrs.email.name, event.target.value);
	        }
	    }, {
	        key: 'emailTypeChange',
	        value: function emailTypeChange(index, event) {
	            this.setProgressEmailState(index, CastleEmailModel.attrs.emailType.name, event.target.value);
	        }
	    }, {
	        key: 'completeEmailBtnClick',
	        value: function completeEmailBtnClick(index) {
	            var emails = _nara.Object.deepCopy(this.state.willModifyEmails);
	            emails[index] = _nara.Object.deepCopy(this.state.inputProgressEmails[index]);

	            this.setState({ willModifyEmails: emails });
	            this.setRowModifiableState(index, false);
	        }
	    }, {
	        key: 'cancelEmailBtnClick',
	        value: function cancelEmailBtnClick(index) {
	            var emails = _nara.Object.deepCopy(this.state.inputProgressEmails);
	            emails[index] = _nara.Object.deepCopy(this.state.willModifyEmails[index]);

	            if (_nara.Object.isEmpty(emails[index])) {
	                emails.splice(index, 1);

	                var rowsModifiable = this.state.rowsModifiable;
	                rowsModifiable.splice(index, 1);

	                this.setState({ rowsModifiable: rowsModifiable });
	            } else {
	                this.setRowModifiableState(index, false);
	            }
	            this.setState({ inputProgressEmails: emails });
	        }
	    }, {
	        key: 'modifyEmailBtnClick',
	        value: function modifyEmailBtnClick(index) {
	            this.setRowModifiableState(index, true);
	        }
	    }, {
	        key: 'removeEmailBtnClick',
	        value: function removeEmailBtnClick(index) {
	            var progressEmails = this.state.inputProgressEmails,
	                willModifyEmails = this.state.willModifyEmails,
	                rowsModifiable = this.state.rowsModifiable;

	            progressEmails.splice(index, 1);
	            willModifyEmails.splice(index, 1);
	            rowsModifiable.splice(index, 1);

	            this.setState({
	                inputProgressEmails: progressEmails,
	                willModifyEmails: willModifyEmails,
	                rowsModifiable: rowsModifiable
	            });
	        }
	    }, {
	        key: 'addEmailBtnClick',
	        value: function addEmailBtnClick() {
	            var emails = this.state.inputProgressEmails,
	                rowsModifiable = this.state.rowsModifiable;

	            emails.push({
	                email: '',
	                emailType: 'Private',
	                verified: 'false',
	                verifiedTime: 0
	            });
	            rowsModifiable.push(true);

	            this.setState({ inputProgressEmails: emails, rowsModifiable: rowsModifiable });
	        }
	    }, {
	        key: 'saveEmailBookBtnClick',
	        value: function saveEmailBookBtnClick() {
	            this.props.saveEmailBook({ emails: this.state.willModifyEmails });
	        }
	    }, {
	        key: 'cancelModificationBtnClick',
	        value: function cancelModificationBtnClick() {
	            this.props.changeViewMode();
	        }
	        // custom

	    }, {
	        key: 'setRowModifiableState',
	        value: function setRowModifiableState(index, modifiable) {
	            //
	            var rowsModifiable = this.state.rowsModifiable;

	            rowsModifiable[index] = modifiable;
	            this.setState({ rowsModifiable: rowsModifiable });
	        }
	    }, {
	        key: 'setProgressEmailState',
	        value: function setProgressEmailState(index, propertyName, value) {
	            //
	            var emails = this.state.inputProgressEmails;

	            emails[index][propertyName] = value;
	            this.setState({ inputProgressEmails: emails });
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var ENUMS = _castleModel2.default.enums,
	                BUTTON_NAMES = _castleModel2.default.buttons,
	                ATTRS = CastleEmailModel.attrs,
	                MESSAGES = CastleEmailModel.messages,
	                LANG = _main2.default.lang;

	            var propEmailBook = this.props.emailBook,
	                existsEmailBook = propEmailBook.emails.length > 0;

	            return _react2.default.createElement(
	                'article',
	                null,
	                _react2.default.createElement(
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
	                                ATTRS.email[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-2' },
	                                ATTRS.emailType[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-2' },
	                                ATTRS.verified[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-3' },
	                                ATTRS.verifiedTime[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-2' },
	                                ' '
	                            )
	                        )
	                    ),
	                    _react2.default.createElement(
	                        'tbody',
	                        null,
	                        existsEmailBook ? this.state.inputProgressEmails.map(function (email, index) {
	                            return this.state.rowsModifiable[index] === true ? _react2.default.createElement(
	                                'tr',
	                                { key: index },
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.emailChange.bind(this, index),
	                                        value: email[ATTRS.email.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement(
	                                        'select',
	                                        { className: 'form-control', onChange: this.emailTypeChange.bind(this, index),
	                                            value: email[ATTRS.emailType.name] },
	                                        _react2.default.createElement(
	                                            'option',
	                                            { value: '' },
	                                            ATTRS.emailType[LANG]
	                                        ),
	                                        Object.keys(ENUMS.emailType).map(function (emailTypeKey, index) {
	                                            var EMAIL_TYPE_ENUM = ENUMS.emailType[emailTypeKey];
	                                            return _react2.default.createElement(
	                                                'option',
	                                                { key: index, value: EMAIL_TYPE_ENUM.name },
	                                                EMAIL_TYPE_ENUM[LANG]
	                                            );
	                                        })
	                                    )
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control', readOnly: 'readOnly',
	                                        value: ENUMS.verified[email[ATTRS.verified.name]][LANG]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control', readOnly: 'readOnly',
	                                        value: email[ATTRS.verifiedTime.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement(
	                                        'div',
	                                        { className: 'btn-group btn-group-sm text-center' },
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-primary',
	                                                onClick: this.completeEmailBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.complete[LANG]
	                                        ),
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-default',
	                                                onClick: this.cancelEmailBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.cancel[LANG]
	                                        )
	                                    )
	                                )
	                            ) : _react2.default.createElement(
	                                'tr',
	                                { key: index },
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    email[ATTRS.email.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    ENUMS.emailType[email[ATTRS.emailType.name]][LANG]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    ENUMS.verified[email[ATTRS.verified.name]][LANG]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _nara.Date.parseToString(email[ATTRS.verifiedTime.name])
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement(
	                                        'div',
	                                        { className: 'btn-group btn-group-sm text-center' },
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-default',
	                                                onClick: this.modifyEmailBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.modify[LANG]
	                                        ),
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-danger',
	                                                onClick: this.removeEmailBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.remove[LANG]
	                                        )
	                                    )
	                                )
	                            );
	                        }.bind(this)) : _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'td',
	                                { colSpan: '5' },
	                                MESSAGES.notRegisteredEmail[LANG]
	                            )
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'btn-block btn-toolbar' },
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'pull-right' },
	                        _react2.default.createElement(
	                            'button',
	                            { type: 'button', className: 'btn btn-default btn-sm',
	                                onClick: this.addEmailBtnClick },
	                            BUTTON_NAMES.add[LANG]
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'btn-toolbar pull-right' },
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-primary',
	                            onClick: this.saveEmailBookBtnClick },
	                        BUTTON_NAMES.save[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-default',
	                            onClick: this.cancelModificationBtnClick },
	                        BUTTON_NAMES.cancel[LANG]
	                    )
	                )
	            );
	        }
	    }]);

	    return EmailModifiableContent;
	}(_react.Component);

	EmailModifiableContent.propTypes = {
	    //
	    emailBook: _react.PropTypes.shape({
	        emails: _react.PropTypes.array.isRequired
	    }).isRequired,
	    changeViewMode: _react.PropTypes.func.isRequired,
	    saveEmailBook: _react.PropTypes.func.isRequired
	};

	exports.default = EmailContent;

/***/ },
/* 22 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _nara = __webpack_require__(2);

	var _castleCommon = __webpack_require__(1);

	var _castleModel = __webpack_require__(8);

	var _castleModel2 = _interopRequireDefault(_castleModel);

	var _main = __webpack_require__(9);

	var _main2 = _interopRequireDefault(_main);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-12.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	// Define Content attributes name
	var CastleAddressModel = {
	    //
	    attrs: {
	        title: { name: 'title', KOR: '주소명', USA: 'Title' },
	        langCode: { name: 'langCode', KOR: '언어코드', USA: 'Language code' },
	        style: { name: 'style', KOR: '유형', USA: 'Style' },
	        country: { name: 'country', KOR: '국가', USA: 'Coutnry' },
	        zipCode: { name: 'zipCode', KOR: '우편번호', USA: 'Zip code' },
	        state: { name: 'state', KOR: '지역', USA: 'State' },
	        city: { name: 'city', KOR: '시', USA: 'City' },
	        addressPartOne: { name: 'addressPartOne', KOR: '주소1', USA: 'Address part1' },
	        addressPartTwo: { name: 'addressPartTwo', KOR: '주소2', USA: 'Address part2' },
	        phoneNumber: { name: 'phoneNumber', KOR: '전화번호', USA: 'Phone number' }
	    },
	    messages: {
	        notRegisteredAddress: { KOR: '등록 된 주소가 없습니다', USA: 'Not registered the address' },
	        completeSave: { KOR: '주소가 저장되었습니다.', USA: 'Save has been completed.' }
	    }
	};

	// Define components

	var AddressContent = function (_Component) {
	    _inherits(AddressContent, _Component);

	    //

	    function AddressContent(props) {
	        _classCallCheck(this, AddressContent);

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(AddressContent).call(this, props));
	        //


	        _this.setAddressBook = _this.setAddressBook.bind(_this);
	        _this.requestFindAddressBook = _this.requestFindAddressBook.bind(_this);
	        _this.requestSaveAddressBook = _this.requestSaveAddressBook.bind(_this);
	        return _this;
	    }
	    // overriding


	    _createClass(AddressContent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            this.requestFindAddressBook(this.props.castleId);
	        }
	        // custom

	    }, {
	        key: 'setAddressBook',
	        value: function setAddressBook(addressBook) {
	            var castle = this.props.castle;

	            castle.contact.addressBook = addressBook;
	            this.props.setCastle(castle);
	        }
	        // request

	    }, {
	        key: 'requestFindAddressBook',
	        value: function requestFindAddressBook(castleId) {
	            //
	            _nara.Ajax.getJSON(AddressContent.url.FIND_ADDRESS_BOOK.replace('{id}', castleId)).done(function (addressBookResult) {
	                this.setAddressBook(addressBookResult);
	            }.bind(this));
	        }
	    }, {
	        key: 'requestSaveAddressBook',
	        value: function requestSaveAddressBook(addressBook) {
	            //
	            _nara.Ajax.postJSON(AddressContent.url.ATTACH_PHONE_BOOK.replace('{id}', this.props.castleId), addressBook).done(function () {
	                var lang = _main2.default.lang;

	                this.setAddressBook(addressBook);
	                alert(CastleAddressModel.messages.completeSave[lang]);
	                this.props.changeViewMode();
	            }.bind(this));
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            return this.props.modifiable ? _react2.default.createElement(AddressModifiableContent, {
	                addressBook: this.props.castle.contact.addressBook,
	                changeViewMode: this.props.changeViewMode,
	                saveAddressBook: this.requestSaveAddressBook
	            }) : _react2.default.createElement(AddressViewContent, {
	                addressBook: this.props.castle.contact.addressBook,
	                changeModifiableMode: this.props.changeModifiableMode
	            });
	        }
	    }]);

	    return AddressContent;
	}(_react.Component);

	AddressContent.propTypes = {
	    //
	    castleId: _react.PropTypes.string,
	    castle: _react.PropTypes.shape({
	        contact: _react.PropTypes.shape({
	            addressBook: _react.PropTypes.shape({
	                addresses: _react.PropTypes.array
	            })
	        })
	    }),
	    modifiable: _react.PropTypes.bool,

	    changeModifiableMode: _react.PropTypes.func,
	    changeViewMode: _react.PropTypes.func,
	    setCastle: _react.PropTypes.func
	};

	AddressContent.url = {
	    //
	    FIND_ADDRESS_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}/contacts/address-book',
	    ATTACH_PHONE_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castellans/{id}/contacts/address-book'
	};

	var AddressViewContent = function (_Component2) {
	    _inherits(AddressViewContent, _Component2);

	    //

	    function AddressViewContent(props) {
	        _classCallCheck(this, AddressViewContent);

	        var _this2 = _possibleConstructorReturn(this, Object.getPrototypeOf(AddressViewContent).call(this, props));
	        //


	        _this2.modifiableModeBtnClick = _this2.modifiableModeBtnClick.bind(_this2);
	        return _this2;
	    }
	    // event


	    _createClass(AddressViewContent, [{
	        key: 'modifiableModeBtnClick',
	        value: function modifiableModeBtnClick() {
	            this.props.changeModifiableMode();
	        }
	    }, {
	        key: 'removeBtnClick',
	        value: function removeBtnClick() {}
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var ENUMS = _castleModel2.default.enums,
	                BUTTON_NAMES = _castleModel2.default.buttons,
	                ATTRS = CastleAddressModel.attrs,
	                MESSAGES = CastleAddressModel.messages,
	                LANG = _main2.default.lang;

	            var existsAddressBook = this.props.addressBook.addresses.length > 0;

	            return _react2.default.createElement(
	                'article',
	                null,
	                _react2.default.createElement(
	                    'table',
	                    { className: 'table table-hover' },
	                    _react2.default.createElement(
	                        'thead',
	                        null,
	                        _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.title[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.langCode[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.style[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.country[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.zipCode[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.phoneNumber[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.state[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.city[LANG]
	                            )
	                        ),
	                        _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'th',
	                                { colSpan: '4' },
	                                ATTRS.addressPartOne[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { colSpan: '4' },
	                                ATTRS.addressPartTwo[LANG]
	                            )
	                        )
	                    ),
	                    existsAddressBook ? this.props.addressBook.addresses.map(function (address, index) {
	                        //
	                        var className = index % 2 === 0 ? 'active' : '';

	                        return _react2.default.createElement(
	                            'tbody',
	                            { key: index },
	                            _react2.default.createElement(
	                                'tr',
	                                { className: className },
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.title.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    ENUMS.language[address[ATTRS.langCode.name]][LANG]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    ENUMS.addressStyle[address[ATTRS.style.name]][LANG]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.country.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.zipCode.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.phoneNumber.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.state.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.city.name]
	                                )
	                            ),
	                            _react2.default.createElement(
	                                'tr',
	                                { className: className },
	                                _react2.default.createElement(
	                                    'td',
	                                    { colSpan: '4' },
	                                    address[ATTRS.addressPartOne.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    { colSpan: '4' },
	                                    address[ATTRS.addressPartTwo.name]
	                                )
	                            )
	                        );
	                    }) : _react2.default.createElement(
	                        'tbody',
	                        null,
	                        _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'td',
	                                { colSpan: '8' },
	                                MESSAGES.notRegisteredAddress[LANG]
	                            )
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'btn-toolbar pull-right' },
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-default', onClick: this.modifiableModeBtnClick },
	                        BUTTON_NAMES.modify[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-danger', onClick: this.removeBtnClick },
	                        BUTTON_NAMES.remove[LANG]
	                    )
	                )
	            );
	        }
	    }]);

	    return AddressViewContent;
	}(_react.Component);

	AddressViewContent.propTypes = {
	    //
	    addressBook: _react.PropTypes.shape({
	        addresses: _react.PropTypes.array.isRequired
	    }).isRequired,
	    changeModifiableMode: _react.PropTypes.func.isRequired
	};

	var AddressModifiableContent = function (_Component3) {
	    _inherits(AddressModifiableContent, _Component3);

	    //

	    function AddressModifiableContent(props) {
	        _classCallCheck(this, AddressModifiableContent);

	        var _this3 = _possibleConstructorReturn(this, Object.getPrototypeOf(AddressModifiableContent).call(this, props));
	        //


	        _this3.state = {
	            willModifyAddresses: [],
	            inputProgressAddresses: [],
	            rowsModifiable: []
	        };

	        _this3.titleChange = _this3.titleChange.bind(_this3);
	        _this3.langCodeChange = _this3.langCodeChange.bind(_this3);
	        _this3.styleChange = _this3.styleChange.bind(_this3);
	        _this3.countryChange = _this3.countryChange.bind(_this3);
	        _this3.zipCodeChange = _this3.zipCodeChange.bind(_this3);
	        _this3.phoneNumberChange = _this3.phoneNumberChange.bind(_this3);
	        _this3.stateChange = _this3.stateChange.bind(_this3);
	        _this3.cityChange = _this3.cityChange.bind(_this3);
	        _this3.addressPartOneChange = _this3.addressPartOneChange.bind(_this3);
	        _this3.addressPartTwoChange = _this3.addressPartTwoChange.bind(_this3);
	        _this3.completeAddressBtnClick = _this3.completeAddressBtnClick.bind(_this3);
	        _this3.cancelAddressBtnClick = _this3.cancelAddressBtnClick.bind(_this3);
	        _this3.modifyAddressBtnClick = _this3.modifyAddressBtnClick.bind(_this3);
	        _this3.removeAddressBtnClick = _this3.removeAddressBtnClick.bind(_this3);
	        _this3.addAddressBtnClick = _this3.addAddressBtnClick.bind(_this3);
	        _this3.saveAddressBookBtnClick = _this3.saveAddressBookBtnClick.bind(_this3);
	        _this3.cancelModificationBtnClick = _this3.cancelModificationBtnClick.bind(_this3);
	        _this3.setWillModifyAddress = _this3.setWillModifyAddress.bind(_this3);
	        _this3.setProgressAddressState = _this3.setProgressAddressState.bind(_this3);
	        _this3.setRowModifiableState = _this3.setRowModifiableState.bind(_this3);
	        return _this3;
	    }
	    // overriding


	    _createClass(AddressModifiableContent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            var addresses = _nara.Object.deepCopy(this.props.addressBook.addresses),
	                rowsModifiable = [];

	            rowsModifiable.length = addresses.length;
	            rowsModifiable.fill(false);

	            this.setState({
	                willModifyAddresses: addresses,
	                inputProgressAddresses: _nara.Object.deepCopy(addresses),
	                rowsModifiable: rowsModifiable
	            });
	        }
	        // event

	    }, {
	        key: 'titleChange',
	        value: function titleChange(index, event) {
	            this.setProgressAddressState(index, CastleAddressModel.attrs.title.name, event.target.value);
	        }
	    }, {
	        key: 'langCodeChange',
	        value: function langCodeChange(index, event) {
	            this.setProgressAddressState(index, CastleAddressModel.attrs.langCode.name, event.target.value);
	        }
	    }, {
	        key: 'styleChange',
	        value: function styleChange(index, event) {
	            this.setProgressAddressState(index, CastleAddressModel.attrs.style.name, event.target.value);
	        }
	    }, {
	        key: 'countryChange',
	        value: function countryChange(index, event) {
	            this.setProgressAddressState(index, CastleAddressModel.attrs.country.name, event.target.value);
	        }
	    }, {
	        key: 'zipCodeChange',
	        value: function zipCodeChange(index, event) {
	            this.setProgressAddressState(index, CastleAddressModel.attrs.zipCode.name, event.target.value);
	        }
	    }, {
	        key: 'phoneNumberChange',
	        value: function phoneNumberChange(index, event) {
	            this.setProgressAddressState(index, CastleAddressModel.attrs.phoneNumber.name, event.target.value);
	        }
	    }, {
	        key: 'stateChange',
	        value: function stateChange(index, event) {
	            this.setProgressAddressState(index, CastleAddressModel.attrs.state.name, event.target.value);
	        }
	    }, {
	        key: 'cityChange',
	        value: function cityChange(index, event) {
	            this.setProgressAddressState(index, CastleAddressModel.attrs.city.name, event.target.value);
	        }
	    }, {
	        key: 'addressPartOneChange',
	        value: function addressPartOneChange(index, event) {
	            this.setProgressAddressState(index, CastleAddressModel.attrs.addressPartOne.name, event.target.value);
	        }
	    }, {
	        key: 'addressPartTwoChange',
	        value: function addressPartTwoChange(index, event) {
	            this.setProgressAddressState(index, CastleAddressModel.attrs.addressPartTwo.name, event.target.value);
	        }
	    }, {
	        key: 'completeAddressBtnClick',
	        value: function completeAddressBtnClick(index) {
	            this.setWillModifyAddress(index, this.state.inputProgressAddresses[index]);
	            this.setRowModifiableState(index, false);
	        }
	    }, {
	        key: 'cancelAddressBtnClick',
	        value: function cancelAddressBtnClick(index) {
	            var addresses = _nara.Object.deepCopy(this.state.inputProgressAddresses);
	            addresses[index] = _nara.Object.deepCopy(this.state.willModifyAddresses[index]);

	            if (_nara.Object.isEmpty(addresses[index])) {
	                addresses.splice(index, 1);

	                var rowsModifiable = this.state.rowsModifiable;
	                rowsModifiable.splice(index, 1);

	                this.setState({ rowsModifiable: rowsModifiable });
	            } else {
	                this.setRowModifiableState(index, false);
	            }
	            this.setState({ inputProgressAddresses: addresses });
	        }
	    }, {
	        key: 'modifyAddressBtnClick',
	        value: function modifyAddressBtnClick(index) {
	            this.setRowModifiableState(index, true);
	        }
	    }, {
	        key: 'removeAddressBtnClick',
	        value: function removeAddressBtnClick(index) {
	            var progressAddresses = this.state.inputProgressAddresses,
	                willModifyAddresses = this.state.willModifyAddresses,
	                rowsModifiable = this.state.rowsModifiable;

	            progressAddresses.splice(index, 1);
	            willModifyAddresses.splice(index, 1);
	            rowsModifiable.splice(index, 1);

	            this.setState({
	                inputProgressAddresses: progressAddresses,
	                willModifyAddresses: willModifyAddresses,
	                rowsModifiable: rowsModifiable
	            });
	        }
	    }, {
	        key: 'addAddressBtnClick',
	        value: function addAddressBtnClick() {
	            var addresses = this.state.inputProgressAddresses,
	                rowsModifiable = this.state.rowsModifiable;

	            addresses.push({});
	            rowsModifiable.push(true);

	            this.setState({ inputProgressAddresses: addresses, rowsModifiable: rowsModifiable });
	        }
	    }, {
	        key: 'saveAddressBookBtnClick',
	        value: function saveAddressBookBtnClick() {
	            this.props.saveAddressBook({ addresses: this.state.willModifyAddresses });
	        }
	    }, {
	        key: 'cancelModificationBtnClick',
	        value: function cancelModificationBtnClick() {
	            this.props.changeViewMode();
	        }
	        // custom

	    }, {
	        key: 'setWillModifyAddress',
	        value: function setWillModifyAddress(index, address) {
	            //
	            var copiedAddress = _nara.Object.deepCopy(address);
	            var addresses = this.state.willModifyAddresses;

	            addresses[index] = copiedAddress;
	            this.setState({ willModifyAddresses: addresses });
	        }
	    }, {
	        key: 'setProgressAddressState',
	        value: function setProgressAddressState(index, propertyName, value) {
	            //
	            var addresses = this.state.inputProgressAddresses;

	            addresses[index][propertyName] = value;
	            this.setState({ inputProgressAddresses: addresses });
	        }
	    }, {
	        key: 'setRowModifiableState',
	        value: function setRowModifiableState(index, modifiable) {
	            //
	            var rowsModifiable = this.state.rowsModifiable;

	            rowsModifiable[index] = modifiable;
	            this.setState({ rowsModifiable: rowsModifiable });
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            var ATTRS = CastleAddressModel.attrs,
	                BUTTON_NAMES = _castleModel2.default.buttons,
	                MESSAGES = CastleAddressModel.messages,
	                LANG = _main2.default.lang;

	            var existsAddressBook = this.props.addressBook.addresses.length > 0;

	            return _react2.default.createElement(
	                'article',
	                null,
	                _react2.default.createElement(
	                    'table',
	                    { className: 'table table-hover' },
	                    _react2.default.createElement(
	                        'thead',
	                        null,
	                        _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.title[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.langCode[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.style[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.country[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.zipCode[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.phoneNumber[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.state[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                null,
	                                ATTRS.city[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { className: 'col-md-2', rowSpan: '2' },
	                                ' '
	                            )
	                        ),
	                        _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'th',
	                                { colSpan: '4' },
	                                ATTRS.addressPartOne[LANG]
	                            ),
	                            _react2.default.createElement(
	                                'th',
	                                { colSpan: '4' },
	                                ATTRS.addressPartTwo[LANG]
	                            )
	                        )
	                    ),
	                    existsAddressBook ? this.state.inputProgressAddresses.map(function (address, index) {
	                        //
	                        var className = index % 2 === 0 ? 'active' : '';

	                        return this.state.rowsModifiable[index] === true ? _react2.default.createElement(
	                            'tbody',
	                            { key: index },
	                            _react2.default.createElement(
	                                'tr',
	                                { className: className },
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.titleChange.bind(this, index),
	                                        value: address[ATTRS.title.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.langCodeChange.bind(this, index),
	                                        value: address[ATTRS.langCode.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.styleChange.bind(this, index),
	                                        value: address[ATTRS.style.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.countryChange.bind(this, index),
	                                        value: address[ATTRS.country.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.zipCodeChange.bind(this, index),
	                                        value: address[ATTRS.zipCode.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.phoneNumberChange.bind(this, index),
	                                        value: address[ATTRS.phoneNumber.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.stateChange.bind(this, index),
	                                        value: address[ATTRS.state.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.cityChange.bind(this, index),
	                                        value: address[ATTRS.city.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    { rowSpan: '2' },
	                                    _react2.default.createElement(
	                                        'div',
	                                        { className: 'btn-group btn-group-sm text-center' },
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-primary',
	                                                onClick: this.completeAddressBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.complete[LANG]
	                                        ),
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-default',
	                                                onClick: this.cancelAddressBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.cancel[LANG]
	                                        )
	                                    )
	                                )
	                            ),
	                            _react2.default.createElement(
	                                'tr',
	                                { className: className },
	                                _react2.default.createElement(
	                                    'td',
	                                    { colSpan: '4' },
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.addressPartOneChange.bind(this, index),
	                                        value: address[ATTRS.addressPartOne.name]
	                                    })
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    { colSpan: '4' },
	                                    _react2.default.createElement('input', { type: 'text', className: 'form-control',
	                                        onChange: this.addressPartTwoChange.bind(this, index),
	                                        value: address[ATTRS.addressPartTwo.name]
	                                    })
	                                )
	                            )
	                        ) : _react2.default.createElement(
	                            'tbody',
	                            { key: index },
	                            _react2.default.createElement(
	                                'tr',
	                                { className: className },
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.title.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.langCode.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.style.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.country.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.zipCode.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.phoneNumber.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.state.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    null,
	                                    address[ATTRS.city.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    { rowSpan: '2' },
	                                    _react2.default.createElement(
	                                        'div',
	                                        { className: 'btn-group btn-group-sm text-center' },
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-default',
	                                                onClick: this.modifyAddressBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.modify[LANG]
	                                        ),
	                                        _react2.default.createElement(
	                                            'button',
	                                            { type: 'button', className: 'btn btn-sm btn-danger',
	                                                onClick: this.removeAddressBtnClick.bind(this, index) },
	                                            BUTTON_NAMES.remove[LANG]
	                                        )
	                                    )
	                                )
	                            ),
	                            _react2.default.createElement(
	                                'tr',
	                                { className: className },
	                                _react2.default.createElement(
	                                    'td',
	                                    { colSpan: '4' },
	                                    address[ATTRS.addressPartOne.name]
	                                ),
	                                _react2.default.createElement(
	                                    'td',
	                                    { colSpan: '4' },
	                                    address[ATTRS.addressPartTwo.name]
	                                )
	                            )
	                        );
	                    }.bind(this)) : _react2.default.createElement(
	                        'tbody',
	                        null,
	                        _react2.default.createElement(
	                            'tr',
	                            null,
	                            _react2.default.createElement(
	                                'td',
	                                { colSpan: '9' },
	                                MESSAGES.notRegisteredAddress[LANG]
	                            )
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'btn-block btn-toolbar' },
	                    _react2.default.createElement(
	                        'div',
	                        { className: 'pull-right' },
	                        _react2.default.createElement(
	                            'button',
	                            { type: 'button', className: 'btn btn-default btn-sm',
	                                onClick: this.addAddressBtnClick },
	                            BUTTON_NAMES.add[LANG]
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'div',
	                    { className: 'btn-toolbar pull-right' },
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-primary',
	                            onClick: this.saveAddressBookBtnClick },
	                        BUTTON_NAMES.save[LANG]
	                    ),
	                    _react2.default.createElement(
	                        'button',
	                        { type: 'button', className: 'btn-group btn btn-default',
	                            onClick: this.cancelModificationBtnClick },
	                        BUTTON_NAMES.cancel[LANG]
	                    )
	                )
	            );
	        }
	    }]);

	    return AddressModifiableContent;
	}(_react.Component);

	AddressModifiableContent.propTypes = {
	    //
	    addressBook: _react.PropTypes.shape({
	        addresses: _react.PropTypes.array.isRequired
	    }).isRequired,
	    changeViewMode: _react.PropTypes.func.isRequired,
	    saveAddressBook: _react.PropTypes.func.isRequired
	};

	exports.default = AddressContent;

/***/ },
/* 23 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _nara = __webpack_require__(2);

	var _castleCommon = __webpack_require__(1);

	var _main = __webpack_require__(9);

	var _main2 = _interopRequireDefault(_main);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-12.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	// Define Content attributes name
	var CastleAccountModel = {
	    //
	    attrs: {
	        loginUserId: { name: 'loginUserId', KOR: '로그인Id', USA: 'Login user id' },
	        channel: { name: 'channel', KOR: '접속방법', USA: 'Channel' },
	        createTime: { name: 'createTime', KOR: '생성일시', USA: 'Create time' },
	        deleteTime: { name: 'deleteTime', KOR: '삭제일시', USA: 'Delete time' }
	    },
	    messages: {
	        notExistsAccount: { KOR: 'Account 이력이 없습니다.', USA: 'Not exists acount history' }
	    }
	};

	// Define components

	var AccountContent = function (_Component) {
	    _inherits(AccountContent, _Component);

	    //

	    function AccountContent(props) {
	        _classCallCheck(this, AccountContent);

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(AccountContent).call(this, props));
	        //


	        _this.setAccountBook = _this.setAccountBook.bind(_this);
	        _this.requestFindAccountBook = _this.requestFindAccountBook.bind(_this);
	        return _this;
	    }
	    // overriding


	    _createClass(AccountContent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            this.requestFindAccountBook(this.props.castleId);
	        }
	        // custom

	    }, {
	        key: 'setAccountBook',
	        value: function setAccountBook(accountBook) {
	            var castle = this.props.castle;

	            castle.history.accountBook = accountBook;
	            this.props.setCastle(castle);
	        }
	        // request

	    }, {
	        key: 'requestFindAccountBook',
	        value: function requestFindAccountBook(castleId) {
	            //
	            _nara.Ajax.getJSON(AccountContent.url.FIND_ACCOUNT_BOOK.replace('{id}', castleId)).done(function (resultAccountBook) {
	                this.setAccountBook(resultAccountBook);
	            }.bind(this));
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            return _react2.default.createElement(AccountViewContent, {
	                accountBook: this.props.castle.history.accountBook
	            });
	        }
	    }]);

	    return AccountContent;
	}(_react.Component);

	AccountContent.propTypes = {
	    //
	    castleId: _react.PropTypes.string,
	    castle: _react.PropTypes.shape({
	        history: _react.PropTypes.shape({
	            accountBook: _react.PropTypes.shape({
	                accounts: _react.PropTypes.array
	            })
	        })
	    })
	};

	AccountContent.url = {
	    //
	    FIND_ACCOUNT_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castles/{id}/histories/account-book'
	};

	var AccountViewContent = function (_Component2) {
	    _inherits(AccountViewContent, _Component2);

	    //

	    function AccountViewContent(props) {
	        _classCallCheck(this, AccountViewContent);

	        return _possibleConstructorReturn(this, Object.getPrototypeOf(AccountViewContent).call(this, props));
	    }

	    _createClass(AccountViewContent, [{
	        key: 'render',
	        value: function render() {
	            //
	            var ATTRS = CastleAccountModel.attrs,
	                MESSAGES = CastleAccountModel.messages,
	                LANG = _main2.default.lang;

	            var existsAccountBook = this.props.accountBook.accounts.length > 0;

	            return _react2.default.createElement(
	                'table',
	                { className: 'table table-striped table-hover' },
	                _react2.default.createElement(
	                    'thead',
	                    null,
	                    _react2.default.createElement(
	                        'tr',
	                        null,
	                        _react2.default.createElement(
	                            'th',
	                            null,
	                            ATTRS.loginUserId[LANG]
	                        ),
	                        _react2.default.createElement(
	                            'th',
	                            null,
	                            ATTRS.channel[LANG]
	                        ),
	                        _react2.default.createElement(
	                            'th',
	                            null,
	                            ATTRS.createTime[LANG]
	                        ),
	                        _react2.default.createElement(
	                            'th',
	                            null,
	                            ATTRS.deleteTime[LANG]
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'tbody',
	                    null,
	                    existsAccountBook ? this.props.accountBook.accounts.map(function (account, index) {
	                        return _react2.default.createElement(
	                            'tr',
	                            { key: index },
	                            _react2.default.createElement(
	                                'td',
	                                null,
	                                account[ATTRS.loginUserId.name]
	                            ),
	                            _react2.default.createElement(
	                                'td',
	                                null,
	                                account[ATTRS.channel.name]
	                            ),
	                            _react2.default.createElement(
	                                'td',
	                                null,
	                                _nara.Date.parseToString(account[ATTRS.createTime.name])
	                            ),
	                            _react2.default.createElement(
	                                'td',
	                                null,
	                                _nara.Date.parseToString(account[ATTRS.deleteTime.name])
	                            )
	                        );
	                    }) : _react2.default.createElement(
	                        'tr',
	                        null,
	                        _react2.default.createElement(
	                            'td',
	                            { colSpan: '4' },
	                            MESSAGES.notExistsAccount[LANG]
	                        )
	                    )
	                )
	            );
	        }
	    }]);

	    return AccountViewContent;
	}(_react.Component);

	AccountViewContent.propTypes = {
	    //
	    accountBook: _react.PropTypes.shape({
	        accounts: _react.PropTypes.array.isRequired
	    }).isRequired
	};

	exports.default = AccountContent;

/***/ },
/* 24 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _nara = __webpack_require__(2);

	var _castleCommon = __webpack_require__(1);

	var _castleModel = __webpack_require__(8);

	var _castleModel2 = _interopRequireDefault(_castleModel);

	var _main = __webpack_require__(9);

	var _main2 = _interopRequireDefault(_main);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-12.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	// Define Content attributes name
	var CastleStateModel = {
	    //
	    attrs: {
	        currentState: { name: 'currentState', KOR: '현재상태', USA: 'Current state' },
	        targetState: { name: 'targetState', KOR: '다음상태', USA: 'Target state' },
	        remarks: { name: 'remarks', KOR: '설명', USA: 'Remarks' },
	        modifiedTime: { name: 'modifiedTime', KOR: '수정일시', USA: 'Modified time' }
	    },
	    messages: {
	        notExistsState: { KOR: 'State 이력이 없습니다.', USA: 'Not exists state history' }
	    }
	};

	// Define components

	var StateContent = function (_Component) {
	    _inherits(StateContent, _Component);

	    //

	    function StateContent(props) {
	        _classCallCheck(this, StateContent);

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(StateContent).call(this, props));
	        //


	        _this.setStateBook = _this.setStateBook.bind(_this);
	        _this.requestFindStateBook = _this.requestFindStateBook.bind(_this);
	        return _this;
	    }
	    // overriding


	    _createClass(StateContent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            this.requestFindStateBook(this.props.castleId);
	        }
	        // custom

	    }, {
	        key: 'setStateBook',
	        value: function setStateBook(stateBook) {
	            var castle = this.props.castle;

	            castle.history.stateBook = stateBook;
	            this.props.setCastle(castle);
	        }
	        // request

	    }, {
	        key: 'requestFindStateBook',
	        value: function requestFindStateBook(castleId) {
	            //
	            _nara.Ajax.getJSON(StateContent.url.FIND_STATE_BOOK.replace('{id}', castleId)).done(function (stateBookResult) {
	                this.setStateBook(stateBookResult);
	            }.bind(this));
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            return _react2.default.createElement(StateViewContent, {
	                stateBook: this.props.castle.history.stateBook
	            });
	        }
	    }]);

	    return StateContent;
	}(_react.Component);

	StateContent.propTypes = {
	    //
	    castleId: _react.PropTypes.string,
	    castle: _react.PropTypes.shape({
	        history: _react.PropTypes.shape({
	            stateBook: _react.PropTypes.shape({
	                states: _react.PropTypes.array
	            })
	        })
	    })
	};

	StateContent.url = {
	    //
	    FIND_STATE_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castles/{id}/histories/state-book'
	};

	var StateViewContent = function (_Component2) {
	    _inherits(StateViewContent, _Component2);

	    //

	    function StateViewContent(props) {
	        _classCallCheck(this, StateViewContent);

	        //
	        return _possibleConstructorReturn(this, Object.getPrototypeOf(StateViewContent).call(this, props));
	    }

	    _createClass(StateViewContent, [{
	        key: 'render',
	        value: function render() {
	            var ENUMS = _castleModel2.default.enums,
	                ATTRS = CastleStateModel.attrs,
	                MESSAGES = CastleStateModel.messages,
	                LANG = _main2.default.lang;

	            var propStateBook = this.props.stateBook,
	                existsStateBook = propStateBook.states.length > 0;

	            return _react2.default.createElement(
	                'table',
	                { className: 'table table-striped table-hover' },
	                _react2.default.createElement(
	                    'thead',
	                    null,
	                    _react2.default.createElement(
	                        'tr',
	                        null,
	                        _react2.default.createElement(
	                            'th',
	                            null,
	                            ATTRS.currentState[LANG]
	                        ),
	                        _react2.default.createElement(
	                            'th',
	                            null,
	                            ATTRS.targetState[LANG]
	                        ),
	                        _react2.default.createElement(
	                            'th',
	                            null,
	                            ATTRS.remarks[LANG]
	                        ),
	                        _react2.default.createElement(
	                            'th',
	                            null,
	                            ATTRS.modifiedTime[LANG]
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'tbody',
	                    null,
	                    existsStateBook ? propStateBook.states.map(function (state, index) {
	                        return _react2.default.createElement(
	                            'tr',
	                            { key: index },
	                            _react2.default.createElement(
	                                'td',
	                                null,
	                                ENUMS.state[state[ATTRS.currentState.name]][LANG]
	                            ),
	                            _react2.default.createElement(
	                                'td',
	                                null,
	                                ENUMS.state[state[ATTRS.targetState.name]][LANG]
	                            ),
	                            _react2.default.createElement(
	                                'td',
	                                null,
	                                state[ATTRS.remarks.name]
	                            ),
	                            _react2.default.createElement(
	                                'td',
	                                null,
	                                _nara.Date.parseToString(state[ATTRS.modifiedTime.name])
	                            )
	                        );
	                    }) : _react2.default.createElement(
	                        'tr',
	                        null,
	                        _react2.default.createElement(
	                            'td',
	                            { colSpan: '4' },
	                            MESSAGES.notExistsState[LANG]
	                        )
	                    )
	                )
	            );
	        }
	    }]);

	    return StateViewContent;
	}(_react.Component);

	StateViewContent.propTypes = {
	    //
	    stateBook: _react.PropTypes.shape({
	        states: _react.PropTypes.array.isRequired
	    }).isRequired
	};

	exports.default = StateContent;

/***/ },
/* 25 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

	var _react = __webpack_require__(4);

	var _react2 = _interopRequireDefault(_react);

	var _nara = __webpack_require__(2);

	var _castleCommon = __webpack_require__(1);

	var _main = __webpack_require__(9);

	var _main2 = _interopRequireDefault(_main);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

	function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

	function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; } /**
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                * Created by hkkang on 2016-04-12.
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                */

	'use strict';

	// Define Content attributes name
	var CastleMetroModel = {
	    //
	    attrs: {
	        metroId: { name: 'metroId', KOR: '메트로Id', USA: 'Metro id' },
	        metroName: { name: 'metroName', KOR: '메트로명', USA: 'Metro name' },
	        joinTime: { name: 'joinTime', KOR: '가입일시', USA: 'Join time' },
	        withdrawalTime: { name: 'withdrawalTime', KOR: '탈퇴일시', USA: 'Withdrawal time' },
	        remarks: { name: 'remarks', KOR: '설명', USA: 'Remarks' }
	    },
	    messages: {
	        notExistsMetro: { KOR: 'Metro 이력이 없습니다', USA: 'Not exists metro history' }
	    }
	};

	// Define components

	var MetroContent = function (_Component) {
	    _inherits(MetroContent, _Component);

	    //

	    function MetroContent(props) {
	        _classCallCheck(this, MetroContent);

	        var _this = _possibleConstructorReturn(this, Object.getPrototypeOf(MetroContent).call(this, props));

	        _this.setMetroBook = _this.setMetroBook.bind(_this);
	        _this.requestFindMetroBook = _this.requestFindMetroBook.bind(_this);
	        return _this;
	    }
	    // overriding


	    _createClass(MetroContent, [{
	        key: 'componentDidMount',
	        value: function componentDidMount() {
	            this.requestFindMetroBook(this.props.castleId);
	        }
	        // custom

	    }, {
	        key: 'setMetroBook',
	        value: function setMetroBook(metroBook) {
	            var castle = this.props.castle;

	            castle.history.metroBook = metroBook;
	            this.props.setCastle(castle);
	        }
	        // request

	    }, {
	        key: 'requestFindMetroBook',
	        value: function requestFindMetroBook(castleId) {
	            _nara.Ajax.getJSON(MetroContent.url.FIND_METRO_BOOK.replace('{id}', castleId)).done(function (resultMetroBook) {
	                this.setMetroBook(resultMetroBook);
	            }.bind(this));
	        }
	    }, {
	        key: 'render',
	        value: function render() {
	            //
	            return _react2.default.createElement(MetroViewContent, {
	                metroBook: this.props.castle.history.metroBook
	            });
	        }
	    }]);

	    return MetroContent;
	}(_react.Component);

	MetroContent.propTypes = {
	    //
	    castleId: _react2.default.PropTypes.string,
	    castle: _react2.default.PropTypes.shape({
	        history: _react2.default.PropTypes.shape({
	            metroBook: _react2.default.PropTypes.shape({
	                metros: _react2.default.PropTypes.array
	            })
	        })
	    })
	};

	MetroContent.url = {
	    //
	    FIND_METRO_BOOK: _castleCommon.Constant.PAV_CTX.api + '/castles/{id}/histories/metro-book'
	};

	var MetroViewContent = function (_Component2) {
	    _inherits(MetroViewContent, _Component2);

	    //

	    function MetroViewContent(props) {
	        _classCallCheck(this, MetroViewContent);

	        return _possibleConstructorReturn(this, Object.getPrototypeOf(MetroViewContent).call(this, props));
	    }

	    _createClass(MetroViewContent, [{
	        key: 'render',
	        value: function render() {
	            //
	            var ATTRS = CastleMetroModel.attrs,
	                MESSAGES = CastleMetroModel.messages,
	                LANG = _main2.default.lang;

	            var existsMetroBook = this.props.metroBook.metros.length > 0;

	            return _react2.default.createElement(
	                'table',
	                { className: 'table table-striped table-hover' },
	                _react2.default.createElement(
	                    'thead',
	                    null,
	                    _react2.default.createElement(
	                        'tr',
	                        null,
	                        _react2.default.createElement(
	                            'th',
	                            null,
	                            ATTRS.metroId[LANG]
	                        ),
	                        _react2.default.createElement(
	                            'th',
	                            null,
	                            ATTRS.metroName[LANG]
	                        ),
	                        _react2.default.createElement(
	                            'th',
	                            null,
	                            ATTRS.joinTime[LANG]
	                        ),
	                        _react2.default.createElement(
	                            'th',
	                            null,
	                            ATTRS.withdrawalTime[LANG]
	                        ),
	                        _react2.default.createElement(
	                            'th',
	                            null,
	                            ATTRS.remarks[LANG]
	                        )
	                    )
	                ),
	                _react2.default.createElement(
	                    'tbody',
	                    null,
	                    existsMetroBook ? this.props.metroBook.metros.map(function (metro, index) {
	                        return _react2.default.createElement(
	                            'tr',
	                            { key: index },
	                            _react2.default.createElement(
	                                'td',
	                                null,
	                                metro[ATTRS.metroId.name]
	                            ),
	                            _react2.default.createElement(
	                                'td',
	                                null,
	                                metro[ATTRS.metroName.name]
	                            ),
	                            _react2.default.createElement(
	                                'td',
	                                null,
	                                _nara.Date.parseToString(metro[ATTRS.joinTime.name])
	                            ),
	                            _react2.default.createElement(
	                                'td',
	                                null,
	                                _nara.Date.parseToString(metro[ATTRS.withdrawalTime.name])
	                            ),
	                            _react2.default.createElement(
	                                'td',
	                                null,
	                                metro[ATTRS.remarks.name]
	                            )
	                        );
	                    }) : _react2.default.createElement(
	                        'tr',
	                        null,
	                        _react2.default.createElement(
	                            'td',
	                            { colSpan: '5' },
	                            MESSAGES.notExistsMetro[LANG]
	                        )
	                    )
	                )
	            );
	        }
	    }]);

	    return MetroViewContent;
	}(_react.Component);

	MetroViewContent.propTypes = {
	    //
	    metroBook: _react2.default.PropTypes.shape({
	        metros: _react2.default.PropTypes.array.isRequired
	    }).isRequired
	};

	exports.default = MetroContent;

/***/ }
/******/ ]);