/**
 * Created by hkkang on 2016-04-05.
 */

import React, { Component } from 'react';
import { Modal, Button } from 'react-bootstrap';
import { Ajax as NaraAjax } from 'app/lib/nara-common';
import { Constant } from 'app/common/castle-common';
import RolePlayerMappingPop from 'app/component/common/role-player-pop.jsx';


'use strict';

// Define component
class RoleBook extends Component {
    //
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
                user: false
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
        this.isUnconfiguredAndAdmin = this.isUnconfiguredAndAdmin.bind(this);
        this.isUnconfiguredAndUser = this.isUnconfiguredAndUser.bind(this);
        this.requestRolesOfPlayer = this.requestRolesOfPlayer.bind(this);
        this.requestPlayers = this.requestPlayers.bind(this);
    }
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
    // custom
    isUnconfiguredAndAdmin() {
        return this.state.roleState.unconfigured && this.state.roleState.admin;
    }
    isUnconfiguredAndUser() {
        return this.state.roleState.unconfigured && this.state.roleState.user;
    }
    // request
    requestRolesOfPlayer(castingId, playerId) {
        //
        NaraAjax
            .getJSON(RoleBook.url.FIND_ROLES_OF_PLAYER.replace('{castingId}', castingId).replace('{playerId}', playerId))
            .done( function (roles) {
                if (roles) {
                    let popupState = this.state.popupState;
                    popupState.alertRoles = true;

                    this.setState({ roles: roles, popupState: popupState });
                    RoleBook.rolesOfPlayer = roles;
                    window.RoleBook = {
                        rolesOfPlayer: roles
                    };
                }
                else {
                    let roleState = this.state.roleState;
                    roleState.unconfigured = true;

                    this.setState({ roleState: roleState });
                    this.requestPlayers(castingId, playerId);
                }
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
            <section>
                <Button onClick={this.roleCheckClick} >RoleCheck</Button>
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
            </section>
        );
    }
}

RoleBook.url = {
    //
    FIND_ROLES_OF_PLAYER: `${Constant.PAV_CTX_API}/stage/roles/castingId/{castingId}/playerId/{playerId}`,
    FIND_PLAYERS: `${Constant.PAV_CTX_API}/stage/players`
};


export default RoleBook;