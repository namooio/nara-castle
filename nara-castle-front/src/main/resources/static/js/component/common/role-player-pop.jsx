/**
 * Created by hkkang on 2016-06-14.
 */
import React, { Component, PropTypes } from 'react';
import { Modal, Button } from 'react-bootstrap';
import { Ajax as NaraAjax, Object as NaraObject } from 'app/lib/nara-common';
import { Constant } from 'app/common/castle-common';


'use strict';

class RolePlayerPop extends Component {
    //
    constructor(props) {
        //
        super(props);

        this.state = {
            roles: [],
            players: [],
            playerRoleCheckList: []
        };

        this.roleCheckChange = this.roleCheckChange.bind(this);
    }
    // overriding
    componentDidMount() {
        this.requestRolePlayer();
    }
    // event
    roleCheckChange(playerIndex, roleIndex) {
        //
        let playerRoleCheckList = this.state.playerRoleCheckList;
        playerRoleCheckList[playerIndex][roleIndex] = !playerRoleCheckList[playerIndex][roleIndex];

        this.setState({ playerRoleCheckList: playerRoleCheckList});
    }
    // request
    requestRolePlayer() {

        let roles = [
            {
                name: 'Admin'
            },
            {
                name: 'User'
            }
        ];

        let players = [
            {
                name: 'kim'
            },
            {
                name: 'lee'
            },
            {
                name: 'park'
            }
        ];

        let roleCheckList = [];
        roleCheckList.length = roles.length;
        roleCheckList.fill(false);

        let playerRoleCheckList = [];

        for (let index in players) {
            playerRoleCheckList[index] = NaraObject.deepCopy(roleCheckList);
        }

        this.setState({ roles : roles, players: players, playerRoleCheckList: playerRoleCheckList});
    }
    requestSavePlayerRoles() {

    }
    render() {
        //
        let existsPlayerCheckList = this.state.playerRoleCheckList.length > 0;

        return (
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
                                this.state.players.map(function (player, playerIndex) {
                                    return (
                                        <tr key={playerIndex}>
                                            <td>{player.name}</td>
                                            {this.state.playerRoleCheckList[playerIndex].map(function (roleCheck, roleIndex) {
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
                    <Button onClick={this.props.onHide}>Close</Button>
                </Modal.Footer>
            </Modal>
        );
    }
}

RolePlayerPop.propTypes = {
    //
    displayable: PropTypes.bool.isRequired,
    onHide: PropTypes.func.isRequired
};
RolePlayerPop.url = {
    //
    FIND_ROLES: `${Constant.PAV_CTX_API}/api/castellans/{id}/contacts/email-book`,
    FIND_PLAYERS: '',
    SAVE_PLAYER_ROLE_LIST: ''
};


export default RolePlayerPop;