/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.StateBook = Components.Castle.StateBook || { };

( function () {
    //
    'use strict';

    // Import component module
    var commonAjax = NaraCommon.Ajax,
        commonDate = NaraCommon.Date,
        constant = CastleCommon.Const,
        mainComponent = Components.Main,
        castleModel = Components.Castle.Model;


    // Define Content attributes name
    var castleStateModel = {
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
    var CastleDetailPage = React.createClass({
        //
        statics: {
            FIND_STATE_BOOK_URL: constant.CTX + '/api/castles/{id}/histories/state-book'
        },
        propTypes : {
            id: React.PropTypes.string
        },
        getInitialState: function () {
            return {
                stateBook: { states: [] },
                contentModifiable: false
            };
        },
        componentDidMount: function () {
            this.requestStateBook(this.props);
        },
        changeModifiableMode: function () {
            this.setState({contentModifiable: true});
        },
        changeViewMode: function () {
            this.setState({contentModifiable: false});
        },
        requestStateBook: function (props) {
            commonAjax
                .getJSON(CastleDetailPage.FIND_STATE_BOOK_URL.replace('{id}', props.id))
                .done( function (stateBookResult) {
                    this.setState({ stateBook: stateBookResult });
                }.bind(this));
        },
        render: function () {
            return (
                <Tab
                    castleId={this.props.id}
                    stateBook={this.state.stateBook}
                    modifiable={this.state.contentModifiable}
                    changeModifiableMode={this.changeModifiableMode}
                    changeViewMode={this.changeViewMode}
                />
            );
        }
    });

    var Tab = React.createClass({
        //
        propTypes: {
            castleId: React.PropTypes.string.isRequired,
            stateBook: React.PropTypes.object,
            modifiable: React.PropTypes.bool.isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired
        },
        render: function () {
            var TAB_NAMES = castleModel.tabs,
                lang = mainComponent.lang;

            return (
                <div className="container" >
                    <div className="panel panel-success">
                        <div className="panel-body">
                            <ul className="nav nav-tabs">
                                <li>
                                    <a href={"#/castle/basic?contentType=" + TAB_NAMES.basic.name + '&id=' + this.props.castleId}>{TAB_NAMES.basic[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/contact/name-book?contentType=" + TAB_NAMES.name.name + '&id=' + this.props.castleId}>{TAB_NAMES.name[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/contact/phone-book?contentType=" + TAB_NAMES.phone.name + '&id=' + this.props.castleId}>{TAB_NAMES.phone[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/contact/email-book?contentType=" + TAB_NAMES.email.name + '&id=' + this.props.castleId}>{TAB_NAMES.email[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/contact/address-book?contentType=" + TAB_NAMES.address.name + '&id=' + this.props.castleId}>{TAB_NAMES.address[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/history/account-book?contentType=" + TAB_NAMES.account.name + '&id=' + this.props.castleId}>{TAB_NAMES.account[lang]}</a>
                                </li>
                                <li className="active">
                                    <a href={"#/castle/history/state-book?contentType=" + TAB_NAMES.state.name + '&id=' + this.props.castleId}>{TAB_NAMES.state[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/history/metro-book?contentType=" + TAB_NAMES.metro.name + '&id=' + this.props.castleId}>{TAB_NAMES.metro[lang]}</a>
                                </li>
                            </ul>
                            <div className="tab-content">
                                <div className="tab-pane active">
                                    <StateContent
                                        stateBook={this.props.stateBook}
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
    });

    var Content = React.createClass({
        propTypes: {

        },
        render : function () {

        }
    });

    var ButtonGroup = React.createClass({
        render : function () {
            var BUTTON_NAMES = castleModel.buttons,
                lang = mainComponent.lang,
                buttonRender;

            if (this.props.modifiable) {
                buttonRender = (
                    <div className="btn-toolbar pull-right">
                        <button type="button" className="btn-group btn btn-primary" onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.save[lang]}</button>
                        <button type="button" className="btn-group btn btn-default" onClick={this.cancelBtnClick}>{BUTTON_NAMES.cancel[lang]}</button>
                    </div>
                );
            }
            else {
                buttonRender = (
                    <div className="btn-toolbar pull-right">
                        <button type="button" className="btn-group btn btn-default" onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.modify[lang]}</button>
                        <button type="button" className="btn-group btn btn-danger" onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.remove[lang]}</button>
                    </div>
                );
            }
            return buttonRender;
        }
    });


    var StateContent = React.createClass({
        propTypes: {
            stateBook: React.PropTypes.shape({
                states: React.PropTypes.array.isRequired
            }).isRequired
        },
        render: function () {
            var ENUMS = castleModel.enums,
                ATTRS = castleStateModel.attrs,
                MESSAGES = castleStateModel.messages,
                lang = mainComponent.lang,
                propStateBook = this.props.stateBook,
                existsStateBook = (propStateBook && propStateBook.states && propStateBook.states.length > 0) ? true : false;

            return (
                <table className="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>{ATTRS.currentState[lang]}</th>
                        <th>{ATTRS.targetState[lang]}</th>
                        <th>{ATTRS.remarks[lang]}</th>
                        <th>{ATTRS.modifiedTime[lang]}</th>
                    </tr>
                    </thead>
                    <tbody>
                    { existsStateBook ?
                        propStateBook.states.map( function (state, index) {
                            return (
                                <tr key={index}>
                                    <td>{ENUMS.state[state[ATTRS.currentState.name]][lang]}</td>
                                    <td>{ENUMS.state[state[ATTRS.targetState.name]][lang]}</td>
                                    <td>{state[ATTRS.remarks.name]}</td>
                                    <td>{commonDate.parseToString(state[ATTRS.modifiedTime.name])}</td>
                                </tr>
                            )
                        })
                        :
                        <tr><td colSpan="4">{MESSAGES.notExistsState[lang]}</td></tr>
                    }
                    </tbody>
                </table>
            );
        }
    });


    Components.Castle.StateBook = CastleDetailPage;
})();