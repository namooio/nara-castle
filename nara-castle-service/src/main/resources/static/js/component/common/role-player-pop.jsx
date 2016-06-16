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
            .getJSON(RolePlayerPop.url.FIND_ROLES)
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
            .postJSON(RolePlayerPop.url.SAVE_ROLE_BOOK, roleBook)
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
                        <Button onClick={this.saveRoleBookBtnOnClick}>Save</Button>
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

RolePlayerPop.propTypes = {
    //
    castingId: PropTypes.string.isRequired,
    players: PropTypes.array.isRequired,
    displayable: PropTypes.bool.isRequired,
    onHide: PropTypes.func.isRequired
};
RolePlayerPop.url = {
    //
    FIND_ROLES: `${Constant.PAV_CTX_API}/stage/roles`,
    SAVE_ROLE_BOOK: `${Constant.PAV_CTX_API}/stage/rolebooks`
};


export default RolePlayerPop;