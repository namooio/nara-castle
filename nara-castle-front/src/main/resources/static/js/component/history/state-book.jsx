/**
 * Created by hkkang on 2016-04-12.
 */

castle.component.StateBook = castle.component.StateBook || {};


( function () {
    //
    'use strict';

    // Import component module
    const NaraAjax = NaraCommon.Ajax,
        NaraDate = NaraCommon.Date,
        Constant = castle.common.Const,
        CastleModel = castle.common.Model,
        MainComponent = castle.component.common.Main;


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
    let StateContent = React.createClass({
        //
        statics: {
            FIND_STATE_BOOK_URL: Constant.PAV_CTX_API + '/api/castles/{id}/histories/state-book'
        },
        propTypes: {
            castleId: React.PropTypes.string.isRequired,
            castle: React.PropTypes.shape({
                history: React.PropTypes.shape({
                    stateBook: React.PropTypes.shape({
                        states: React.PropTypes.array.isRequired
                    }).isRequired
                }).isRequired
            }).isRequired
        },
        // overriding
        componentDidMount() {
            this.requestFindStateBook(this.props.castleId);
        },
        // custom
        setStateBook(stateBook) {
            let castle = this.props.castle;

            castle.history.stateBook = stateBook;
            this.props.setCastle(castle);
        },
        // request
        requestFindStateBook(castleId) {
            //
            NaraAjax
                .getJSON(StateContent.FIND_STATE_BOOK_URL.replace('{id}', castleId))
                .done( function (stateBookResult) {
                    this.setStateBook(stateBookResult);
                }.bind(this));
        },
        render() {
            //
            return (
                <StateViewContent
                    stateBook={this.props.castle.history.stateBook}
                />
            );
        }
    });

    let StateViewContent = React.createClass({
        //
        propTypes: {
            stateBook: React.PropTypes.shape({
                states: React.PropTypes.array.isRequired
            }).isRequired
        },
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
    });


    castle.component.StateBook = StateContent;
})();