/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.MetroBook = Components.Castle.MetroBook || {};

( function () {
    //
    'use strict';

    // Import component module
    let commonAjax = NaraCommon.Ajax,
        commonDate = NaraCommon.Date,
        constant = CastleCommon.Const,
        mainComponent = Components.Common.Main,
        castleModel = Components.Castle.Model;


    // Define Content attributes name
    let castleMetroModel = {
        attrs: {
            metroId:        { name: 'metroId',          KOR: '메트로Id', USA: 'Metro id' },
            metroName:      { name: 'metroName',        KOR: '메트로명', USA: 'Metro name' },
            joinTime:       { name: 'joinTime',         KOR: '가입일시', USA: 'Join time' },
            withdrawalTime: { name: 'withdrawalTime',   KOR: '탈퇴일시', USA: 'Withdrawal time' },
            remarks:        { name: 'remarks',          KOR: '설명',     USA: 'Remarks' }
        },
        messages: {
            notExistsMetro: { KOR: 'Metro 이력이 없습니다', USA: 'Not exists metro history' }
        }
    };

    // Define components
    let CastleDetailPage = React.createClass({
        //
        statics: {
            FIND_METRO_BOOK: constant.CTX + '/api/castles/{id}/histories/metro-book'
        },
        propTypes: {
            id: React.PropTypes.string
        },
        getInitialState() {
            return {
                metroBook: {metros: []},
                contentModifiable: false
            };
        },
        componentDidMount() {
            this.requestMetroBook(this.props);
        },
        changeModifiableMode() {
            this.setState({contentModifiable: true});
        },
        changeViewMode() {
            this.setState({contentModifiable: false});
        },
        requestMetroBook(props) {
            commonAjax
                .getJSON(CastleDetailPage.FIND_METRO_BOOK.replace('{id}', props.id))
                .done(function (metroBookResult) {
                    this.setState({metroBook: metroBookResult});
                }.bind(this));
        },
        render() {
            return (
                <Tab
                    castleId={this.props.id}
                    metroBook={this.state.metroBook}
                    modifiable={this.state.contentModifiable}
                    changeModifiableMode={this.changeModifiableMode}
                    changeViewMode={this.changeViewMode}
                />
            );
        }
    });

    let Tab = React.createClass({
        //
        propTypes: {
            castleId: React.PropTypes.string,
            metroBook: React.PropTypes.object,
            modifiable: React.PropTypes.bool.isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired
        },
        render() {
            let TAB_NAMES = castleModel.tabs,
                lang = mainComponent.lang;

            return (
                <div className="container">
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
                                <li>
                                    <a href={"#/castle/history/state-book?contentType=" + TAB_NAMES.state.name + '&id=' + this.props.castleId}>{TAB_NAMES.state[lang]}</a>
                                </li>
                                <li className="active">
                                    <a href={"#/castle/history/metro-book?contentType=" + TAB_NAMES.metro.name + '&id=' + this.props.castleId}>{TAB_NAMES.metro[lang]}</a>
                                </li>
                            </ul>
                            <div className="tab-content">
                                <div className="tab-pane active">
                                    <MetroContent metroBook={this.props.metroBook}/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
    });

    let Content = React.createClass({
        propTypes: {},
        render() {

        }
    });

    let ButtonGroup = React.createClass({
        render() {
            let BUTTON_NAMES = castleModel.buttons,
                lang = mainComponent.lang,
                buttonRender;

            if (this.props.modifiable) {
                buttonRender = (
                    <div className="btn-toolbar pull-right">
                        <button type="button" className="btn-group btn btn-primary"
                                onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.save[lang]}</button>
                        <button type="button" className="btn-group btn btn-default"
                                onClick={this.cancelModificationBtnClick}>{BUTTON_NAMES.cancel[lang]}</button>
                    </div>
                );
            }
            else {
                buttonRender = (
                    <div className="btn-toolbar pull-right">
                        <button type="button" className="btn-group btn btn-default"
                                onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.modify[lang]}</button>
                        <button type="button" className="btn-group btn btn-danger"
                                onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.remove[lang]}</button>
                    </div>
                );
            }
            return buttonRender;
        }
    });

    let MetroContent = React.createClass({
        propTypes: {
            metroBook: React.PropTypes.shape({
                metros: React.PropTypes.array.isRequired
            }).isRequired
        },
        render() {
            let ATTRS = castleMetroModel.attrs,
                MESSAGES = castleMetroModel.messages,
                lang = mainComponent.lang,
                propMetroBook = this.props.metroBook,
                existsMetroBook = (propMetroBook && propMetroBook.metros && propMetroBook.metros.length > 0) ? true : false;

            return (
                <table className="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>{ATTRS.metroId[lang]}</th>
                        <th>{ATTRS.metroName[lang]}</th>
                        <th>{ATTRS.joinTime[lang]}</th>
                        <th>{ATTRS.withdrawalTime[lang]}</th>
                        <th>{ATTRS.remarks[lang]}</th>
                    </tr>
                    </thead>
                    <tbody>
                    { existsMetroBook ?
                        propMetroBook.metros.map(function (metro) {
                            return (
                                <tr key={metro[ATTRS.metroId.name]}>
                                    <td>{metro[ATTRS.metroId.name]}</td>
                                    <td>{metro[ATTRS.metroName.name]}</td>
                                    <td>{commonDate.parseToString(metro[ATTRS.joinTime.name])}</td>
                                    <td>{commonDate.parseToString(metro[ATTRS.withdrawalTime.name])}</td>
                                    <td>{metro[ATTRS.remarks.name]}</td>
                                </tr>
                            )
                        })
                        :
                        <tr>
                            <td colSpan="5">{MESSAGES.notExistsMetro[lang]}</td>
                        </tr>
                    }
                    </tbody>
                </table>
            );
        }
    });

    Components.Castle.MetroBook = CastleDetailPage;
})();