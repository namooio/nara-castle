/**
 * Created by hkkang on 2016-04-05.
 */

import React, { Component, PropTypes } from 'react';
import { Modal, Button } from 'react-bootstrap';
import { Ajax as NaraAjax, Object as NaraObject } from 'app/lib/nara-common';


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
            FIND_PLAYERS: `${RoleBook.contextPath}/stage/players`
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
        this.requestRolesOfPlayer = this.requestRolesOfPlayer.bind(this);
        this.requestPlayers = this.requestPlayers.bind(this);
    }
    // overriding
    componentDidMount() {
        //
        let castingId = document.getElementsByName('castingId')[0].content,
            playerId = document.getElementsByName('playerId')[0].content;

        this.setState({ castingId: castingId, playerId: playerId });
        //this.requestRolesOfPlayer(castingId, playerId);
    }
    // event
    roleCheckClick() {
        this.requestRolesOfPlayer(this.state.castingId, this.state.playerId);
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
    requestRolesOfPlayer(castingId, playerId) {
        //
        NaraAjax
            .getJSON(RoleBook.url.FIND_ROLES_OF_PLAYER.replace('{castingId}', castingId).replace('{playerId}', playerId))
            .done( function (roles) {
                if (roles) {
                    let popupState = this.state.popupState,
                        roleState = this.state.roleState;

                    popupState.alertRoles = true;
                    roleState.modifiable = true;

                    this.setState({ roles: roles, popupState: popupState });
                    RoleBook.rolesOfPlayer = roles;
                }
                else {
                    let roleState = this.state.roleState;
                    roleState.unconfigured = true;

                    this.setState({ roleState: roleState });
                }
                this.requestPlayers(castingId, playerId);
            }.bind(this));
    }
    requestPlayers(castingId, playerId) {
        //
        NaraAjax
            .getJSON(`${RoleBook.url.FIND_PLAYERS}?castingId=${castingId}`)
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
        return (
            <li>
                <a href="javascript:" onClick={this.roleCheckClick} >RoleCheck</a>
                { this.isModifiableAndAdmin() === true ?
                    <a href="javascript:" onClick={this.modifyRoleBookBtnOnClick}>Modify role book</a> : null
                }
                { this.isUnconfiguredAndAdmin() === true ?
                    <RolePlayerMappingPop
                        castingId={this.state.castingId}
                        players={this.state.players}
                        displayable={this.state.popupState.rolePlayerMapping}
                        onHide={this.rolePlayerMappingPopOnHide}
                    /> : null
                }
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
                <Modal show={this.state.popupState.alertRoles}>
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
            </li>
        );
    }
}

RoleBook.contextPath = null;
RoleBook.rolesOfPlayer = RoleBook.rolesOfPlayer || [];



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


export default RoleBook;