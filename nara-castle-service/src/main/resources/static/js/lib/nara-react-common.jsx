/**
 * Created by hkkang on 2016-07-09.
 */

import React, { Component, PropTypes } from 'react';
import { Modal, Button } from 'react-bootstrap';


// Modal
'use strict';

let modalPublicContext = {
    alert: null,
    confirm: null,
    openModal: null
};

// Define component
class NaraModal extends Component {
    //
    static alert(contentOrParams, handleOk) {
        //
        if (typeof modalPublicContext.alert === 'function') {
            modalPublicContext.alert(contentOrParams, handleOk);
        }
    }
    static confirm(contentOrParams, handleOk, handleCancel) {
        //
        if (typeof modalPublicContext.confirm === 'function') {
            modalPublicContext.confirm(contentOrParams, handleOk, handleCancel);
        }
    }
    static openModal(paramsObject) {
        //
        if (typeof modalPublicContext.openModal === 'function') {
            modalPublicContext.openModal(paramsObject);
        }
    }
    constructor(props) {
        //
        super(props);

        this.state = NaraModal.initialState;

        this.handleOk = this.handleOk.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
        this._alert = this._alert.bind(this);
        this._confirm = this._confirm.bind(this);
        this._openModal = this._openModal.bind(this);

        modalPublicContext.alert = this._alert;
        modalPublicContext.confirm = this._confirm;
        modalPublicContext.openModal = this._openModal;
    }
    // event
    handleOk() {
        //
        let handleOk = this.state.handleOk;

        if (typeof handleOk === 'function') {
            handleOk();
        }
        this.setState(NaraModal.initialState);
    }
    handleCancel() {
        //
        let handleCancel = this.state.handleCancel;

        if (typeof handleCancel === 'function') {
            handleCancel();
        }
        this.setState(NaraModal.initialState);
    }
    // private custom
    _alert(contentOrParams, handleOk) {
        //
        // Content and handleOk
        if (contentOrParams && typeof contentOrParams === 'string') {
            this.setState({
                content: contentOrParams,
                handleOk: typeof handleOk === 'function' ? handleOk : NaraModal.initialState.handleOk
            });
        }
        // Parameters object
        else if (contentOrParams && typeof contentOrParams === 'object') {
            this._openModal(contentOrParams);
        }
        else {
            console.error('Invalid alert arguments');
        }

        this.setState({ show: true, type: NaraModal.type.ALERT });
    }
    _confirm(contentOrParams, handleOk, handleCancel) {
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
        else if (contentOrParams && typeof contentOrParams === 'object') {
            this._openModal(contentOrParams);
        }
        else {
            console.error('Invalid confirm arguments')
        }

        this.setState({ show: true, type: NaraModal.type.CONFIRM });
    }
    _openModal(paramsObject) {
        //
        let { title, content, handleOk, handleCancel } = paramsObject;
        this.setState({
            content,
            title: typeof title === 'string' ? title : NaraModal.initialState.title,
            handleOk: typeof handleOk === 'function' ? handleOk : NaraModal.initialState.handleOk,
            handleCancel: typeof handleCancel === 'function' ? handleCancel : NaraModal.initialState.handleCancel
        });
    }
    render() {
        //
        if (this.state.show !== true) {
            return (null);
        }
        return (
            <div>
                <div className="modal-container">
                    <div className="modal fade in" id="myModal" role="dialog" style={{display: 'block'}}>
                        <div className="modal-dialog">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <button type="button" className="close" data-dismiss="modal">×</button>
                                    <h4 className="modal-title">{this.state.title}</h4>
                                </div>
                                <div className="modal-body">
                                    <p>{this.state.content}</p>
                                </div>
                                <div className="modal-footer">
                                    { this.state.type === NaraModal.type.ALERT && this.state.options.okUsable === true ?
                                        <button type="button" className="btn btn-default" data-dismiss="modal" onClick={this.handleOk}>Ok</button>
                                        : null
                                    }
                                    { this.state.type === NaraModal.type.CONFIRM ?
                                        <div>
                                            <button type="button" className="btn btn-default" data-dismiss="modal" onClick={this.handleCancel}>Cancel</button>
                                            <button type="button" className="btn btn-info" data-dismiss="modal" onClick={this.handleOk}>Ok</button>
                                        </div>
                                        : null
                                    }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="modal-backdrop fade in"></div>
            </div>
        );
    }
}

NaraModal.initialState = {
    show: false,        // boolean
    type: null,         // enum
    title: 'Notice',   // string
    content: null,      // string or html
    handleOk: null,     // function
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

export { NaraModal as Modal };


// RoleBook
import { Ajax as NaraAjax, Object as NaraObject, Url as NaraUrl } from './nara-common';


'use strict';

// Define component
class RoleBook extends Component {
    //
    static setContextPath(contextPath) {
        //
        RoleBook.contextPath = contextPath;
        RoleBook.setUrl();
    }
    static setUrl() {
        //
        RoleBook.url = {
            //
            FIND_ROLES_OF_PLAYER: `${RoleBook.contextPath}/stage/rolebook/players/{playerId}/roles?castingId={castingId}`,
            FIND_PLAYERS: `${RoleBook.contextPath}/stage/players?pavilionId={pavilionId}&castingId={castingId}`
        };

        RolePlayerMappingPop.url = {
            FIND_ROLES: `${RoleBook.contextPath}/stage/roles`,
            SAVE_ROLE_BOOK: `${RoleBook.contextPath}/stage/rolebook`
        };
    }
    static getRoles() {
        //
        return RoleBook.rolesOfPlayer;
    }
    static getRoleNames() {
        //
        let roleNames = [];

        if (RoleBook.rolesOfPlayer) {
            RoleBook.rolesOfPlayer.forEach( function (role) {
                roleNames.push(role.name);
            });
        }
        return roleNames;
    }
    static hasRole(roleName) {
        //
        let result = false;

        if (RoleBook.rolesOfPlayer) {
            result = RoleBook.rolesOfPlayer.some( function (role) {
                return roleName === role.name;
            });
        }
        return result;
    }
    constructor(props) {
        //
        super(props);

        this.state = {
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

        this.roleCheckClick = this.roleCheckClick.bind(this);
        this.rolePlayerMappingPopOnHide = this.rolePlayerMappingPopOnHide.bind(this);
        this.rolesBtnOnClick = this.rolesBtnOnClick.bind(this);
        this.modifyRoleBookBtnOnClick = this.modifyRoleBookBtnOnClick.bind(this);
        this.isUnconfiguredAndAdmin = this.isUnconfiguredAndAdmin.bind(this);
        this.isUnconfiguredAndUser = this.isUnconfiguredAndUser.bind(this);
        this.isModifiableAndAdmin = this.isModifiableAndAdmin.bind(this);
        this.requestFindRolesOfPlayer = this.requestFindRolesOfPlayer.bind(this);
        this.requestFindPlayers = this.requestFindPlayers.bind(this);
    }
    // overriding
    componentDidMount() {
        //
        let pavilionId = document.getElementsByName('pavilionId')[0].content,
            castingId = document.getElementsByName('castingId')[0].content,
            playerId = document.getElementsByName('playerId')[0].content;

        this.setState({ pavilionId: pavilionId, castingId: castingId, playerId: playerId });

        if (!RoleBook.contextPath) {
            RoleBook.setContextPath(NaraUrl.getPavilionHashContextPath());
        }

        if (this.props.init === true) {
            this.requestFindRolesOfPlayer(pavilionId, castingId, playerId);
        }
    }
    // event
    roleCheckClick() {
        this.requestFindRolesOfPlayer(this.state.pavilionId, this.state.castingId, this.state.playerId);
    }
    rolePlayerMappingPopOnHide() {
        let popupState = this.state.popupState;
        popupState.rolePlayerMapping = false;
        this.setState({ popupState: popupState });
    }
    rolesBtnOnClick() {
        let popupState = this.state.popupState;
        popupState.alertRoles = false;
        this.setState({ popupState: popupState });
    }
    modifyRoleBookBtnOnClick() {
        let roleState = this.state.roleState;
        roleState.unconfigured = true;

        this.setState({ roleState: roleState });
    }
    // custom
    isUnconfiguredAndAdmin() {
        return this.state.roleState.unconfigured && this.state.roleState.admin;
    }
    isUnconfiguredAndUser() {
        return this.state.roleState.unconfigured && this.state.roleState.user;
    }
    isModifiableAndAdmin() {
        return this.state.roleState.modifiable && this.state.roleState.admin;
    }
    // request
    requestFindRolesOfPlayer(pavilionId, castingId, playerId) {
        //
        NaraAjax
            .getJSON(RoleBook.url.FIND_ROLES_OF_PLAYER.replace('{castingId}', castingId).replace('{playerId}', playerId))
            .done( function (roles, a1, a2) {
                if (roles && roles.length > 0) {
                    let popupState = this.state.popupState,
                        roleState = this.state.roleState;

                    popupState.alertRoles = true;
                    roleState.modifiable = true;

                    this.setState({ roles: roles, popupState: popupState });
                    RoleBook.rolesOfPlayer = roles;

                    if (this.props.onInit) {
                        this.props.onInit();
                    }
                }
                else {
                    let roleState = this.state.roleState;
                    roleState.unconfigured = true;

                    this.setState({ roleState: roleState });
                }
                this.requestFindPlayers(pavilionId, castingId, playerId);
            }.bind(this));
    }
    requestFindPlayers(pavilionId, castingId, playerId) {
        //
        NaraAjax
            .getJSON(RoleBook.url.FIND_PLAYERS.replace('{pavilionId}', pavilionId).replace('{castingId}', castingId))
            .done( function (players) {
                //
                let administrant =
                    players.some( function (player) {
                        return (player.id === playerId && player.leader === true);
                    });

                if (administrant) {
                    let roleState = this.state.roleState,
                        popupState = this.state.popupState;

                    roleState.admin = true;
                    popupState.rolePlayerMapping = true;
                    this.setState({ roleState: roleState, popupState: popupState, players: players });
                }
                else {
                    let roleState = this.state.roleState,
                        popupState = this.state.popupState;

                    roleState.user = true;
                    popupState.alertUnconfigured = true;
                    this.setState({ roleState: roleState, popupState: popupState });
                }
            }.bind(this));
    }
    render() {
        //
        if (this.isUnconfiguredAndUser() === true) {
            NaraModal.alert({
                title: 'Sorry!',
                content: '해당 드라마의 역할 설정이 되지 않아 이용할 수 없습니다.',
                okUsable: false
            });
        }
        else {
            let rolesElement = this.state.roles.map( function (role, index) {
                return `${role.name} : ${role.description}}`;
            });

            NaraModal.alert({
                title: 'Roles',
                content: rolesElement,
                handleOk: this.rolesBtnOnClick
            });
        }

        return (
            <li>
                { this.props.init !== true ?
                    <a href="javascript:" onClick={this.roleCheckClick} >RoleCheck</a>
                    : null
                }
                { this.isModifiableAndAdmin() === true ?
                    <a href="javascript:" onClick={this.modifyRoleBookBtnOnClick}>Modify role book</a> : null
                }
                { this.isUnconfiguredAndAdmin() === true ?
                    <RolePlayerMappingPop
                        castingId={this.state.castingId}
                        players={this.state.players}
                        displayable={this.state.popupState.rolePlayerMapping}
                        onHide={this.rolePlayerMappingPopOnHide}
                        onSaveSuccess={this.props.onSaveSuccess}
                    /> : null
                }
            </li>
        );
    }
}

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
    init: PropTypes.bool,
    onInit: PropTypes.func,
    onSaveSuccess: PropTypes.func
};
RoleBook.defaultProps = {};



class RolePlayerMappingPop extends Component {
    //
    constructor(props) {
        //
        super(props);

        this.state = {
            roles: [],
            rolePlayers: [],
            successPopup: false
        };

        this.roleCheckChange = this.roleCheckChange.bind(this);
        this.saveRoleBookBtnOnClick = this.saveRoleBookBtnOnClick.bind(this);
        this.successPopCloseBtnOnClick = this.successPopCloseBtnOnClick.bind(this);
        this.requestRolePlayer = this.requestRolePlayer.bind(this);
        this.requestSaveRoleBook = this.requestSaveRoleBook.bind(this);
    }
    // overriding
    componentDidMount() {
        this.requestRolePlayer();
    }
    // event
    roleCheckChange(playerIndex, roleIndex) {
        //
        let rolePlayers = this.state.rolePlayers;
        rolePlayers[playerIndex][roleIndex] = !rolePlayers[playerIndex][roleIndex];

        this.setState({ rolePlayers: rolePlayers});
    }
    saveRoleBookBtnOnClick() {
        //
        let roleBook = {
            castingId: this.props.castingId,
            rolePlayers: []
        };

        this.state.rolePlayers.forEach( function (roleCheckList, index) {
            //
            let player = this.props.players[index];

            let rolePlayer = {
                playerId: player.id,
                name: player.name,
                roles: []
            };

            roleCheckList.forEach( function (roleCheck, index) {
                //
                if (roleCheck === false) {
                    return;
                }
                let role = this.state.roles[index];
                rolePlayer.roles.push(role);
            }.bind(this));

            roleBook.rolePlayers.push(rolePlayer);
        }.bind(this));

        this.requestSaveRoleBook(roleBook);
    }
    successPopCloseBtnOnClick() {
        this.setState({ successPopup: false});
    }
    // request
    requestRolePlayer() {
        //
        NaraAjax
            .getJSON(RolePlayerMappingPop.url.FIND_ROLES)
            .done( function (roles) {
                //
                let roleCheckList = [];
                roleCheckList.length = roles.length;
                roleCheckList.fill(false);

                let rolePlayers = [];

                for (let index in this.props.players) {
                    rolePlayers[index] = NaraObject.deepCopy(roleCheckList);
                }

                this.setState({ roles : roles, rolePlayers: rolePlayers});
            }.bind(this));
    }
    requestSaveRoleBook(roleBook) {
        //
        NaraAjax
            .postJSON(RolePlayerMappingPop.url.SAVE_ROLE_BOOK, roleBook)
            .done( function () {
                this.props.onHide();
                this.setState({ successPopup: true});

                if (this.props.onSaveSuccess) {
                    this.props.onSaveSuccess();
                }
            }.bind(this));
    }
    render() {
        //
        let existsPlayerCheckList = this.state.rolePlayers.length > 0;

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
    }
}

RolePlayerMappingPop.propTypes = {
    //
    castingId: PropTypes.string.isRequired,
    players: PropTypes.array.isRequired,
    displayable: PropTypes.bool.isRequired,
    onHide: PropTypes.func.isRequired
};

export { RoleBook };


export default {
    RoleBook: RoleBook,
    Modal: Modal
};
