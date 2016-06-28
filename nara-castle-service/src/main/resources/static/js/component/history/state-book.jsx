/**
 * Created by hkkang on 2016-04-12.
 */

import React, { Component, PropTypes} from 'react';
import { Ajax as NaraAjax, Date as NaraDate } from 'nara';
import { Constant } from 'app/common/castle-common';
import CastleModel from 'app/common/castle-model';
import MainComponent from 'app/component/common/main.jsx';


'use strict';

// Define Content attributes name
const CastleStateModel = {
    //
    attrs: {
        currentState:   { name: 'currentState', KOR: '현재상태',    USA: 'Current state' },
        targetState:    { name: 'targetState',  KOR: '다음상태',    USA: 'Target state' },
        remarks:        { name: 'remarks',      KOR: '설명',        USA: 'Remarks' },
        modifiedTime:   { name: 'modifiedTime', KOR: '수정일시',    USA: 'Modified time' }
    },
    messages: {
        notExistsState: { KOR: 'State 이력이 없습니다.', USA: 'Not exists state history' }
    }
};


// Define components
class StateContent extends Component {
    //
    constructor(props) {
        //
        super(props);

        this.setStateBook = this.setStateBook.bind(this);
        this.requestFindStateBook = this.requestFindStateBook.bind(this);
    }
    // overriding
    componentDidMount() {
        this.requestFindStateBook(this.props.castleId);
    }
    // custom
    setStateBook(stateBook) {
        let castle = this.props.castle;

        castle.history.stateBook = stateBook;
        this.props.setCastle(castle);
    }
    // request
    requestFindStateBook(castleId) {
        //
        NaraAjax
            .getJSON(StateContent.url.FIND_STATE_BOOK.replace('{id}', castleId))
            .done( function (stateBookResult) {
                this.setStateBook(stateBookResult);
            }.bind(this));
    }
    render() {
        //
        return (
            <StateViewContent
                stateBook={this.props.castle.history.stateBook}
            />
        );
    }
}

StateContent.propTypes = {
    //
    castleId: PropTypes.string,
    castle: PropTypes.shape({
        history: PropTypes.shape({
            stateBook: PropTypes.shape({
                states: PropTypes.array
            })
        })
    })
};

StateContent.url = {
    //
    FIND_STATE_BOOK: `${Constant.PAV_CTX.api}/castles/{id}/histories/state-book`
};


class StateViewContent extends Component {
    //
    constructor(props) {
        //
        super(props);
    }
    render() {
        const ENUMS = CastleModel.enums,
            ATTRS = CastleStateModel.attrs,
            MESSAGES = CastleStateModel.messages,
            LANG = MainComponent.lang;

        let propStateBook = this.props.stateBook,
            existsStateBook = propStateBook.states.length > 0;

        return (
            <table className="table table-striped table-hover">
                <thead>
                <tr>
                    <th>{ATTRS.currentState[LANG]}</th>
                    <th>{ATTRS.targetState[LANG]}</th>
                    <th>{ATTRS.remarks[LANG]}</th>
                    <th>{ATTRS.modifiedTime[LANG]}</th>
                </tr>
                </thead>
                <tbody>
                { existsStateBook ?
                    propStateBook.states.map( function (state, index) {
                        return (
                            <tr key={index}>
                                <td>{ENUMS.state[state[ATTRS.currentState.name]][LANG]}</td>
                                <td>{ENUMS.state[state[ATTRS.targetState.name]][LANG]}</td>
                                <td>{state[ATTRS.remarks.name]}</td>
                                <td>{NaraDate.parseToString(state[ATTRS.modifiedTime.name])}</td>
                            </tr>
                        )
                    })
                    :
                    <tr>
                        <td colSpan="4">{MESSAGES.notExistsState[LANG]}</td>
                    </tr>
                }
                </tbody>
            </table>
        );
    }
}

StateViewContent.propTypes = {
    //
    stateBook: PropTypes.shape({
        states: PropTypes.array.isRequired
    }).isRequired
};


export default StateContent;



