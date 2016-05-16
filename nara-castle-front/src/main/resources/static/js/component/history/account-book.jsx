/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.AccountBook = Components.Castle.AccountBook || {};

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
    let castleAccountModel = {
        attrs: {
            loginUserId:    { name: 'loginUserId',  KOR: '로그인Id', USA: 'Login user id' },
            channel:        { name: 'channel',      KOR: '접속방법', USA: 'Channel' },
            createTime:     { name: 'createTime',   KOR: '생성일시', USA: 'Create time' },
            deleteTime:     { name: 'deleteTime',   KOR: '삭제일시', USA: 'Delete time' }
        },
        messages: {
            notExistsAccount: { KOR: 'Account 이력이 없습니다.', USA: 'Not exists acount history' }
        }
    };

    // Define components
    let CastleDetailPage = React.createClass({
        //
        statics: {
            FIND_ACCOUNT_BOOK_URL: constant.CTX + '/api/castles/{id}/histories/account-book'
        },
        propTypes: {
            id: React.PropTypes.string
        },
        getInitialState() {
            return {
                accountBook: {accounts: []},
                contentModifiable: false
            };
        },
        componentDidMount() {
            this.requestAccountBook(this.props);
        },
        changeModifiableMode() {
            this.setState({contentModifiable: true});
        },
        changeViewMode() {
            this.setState({contentModifiable: false});
        },
        requestAccountBook(props) {
            commonAjax
                .getJSON(CastleDetailPage.FIND_ACCOUNT_BOOK_URL.replace('{id}', props.id))
                .done(function (accountBookResult) {
                    this.setState({accountBook: accountBookResult});
                }.bind(this));
        },
        render() {
            return (
                <Tab
                    castleId={this.props.id}
                    accountBook={this.state.accountBook}
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
            castleId: React.PropTypes.string.isRequired,
            accountBook: React.PropTypes.object,
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
                                <li className="active">
                                    <a href={"#/castle/history/account-book?contentType=" + TAB_NAMES.account.name + '&id=' + this.props.castleId}>{TAB_NAMES.account[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/history/state-book?contentType=" + TAB_NAMES.state.name + '&id=' + this.props.castleId}>{TAB_NAMES.state[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/history/metro-book?contentType=" + TAB_NAMES.metro.name + '&id=' + this.props.castleId}>{TAB_NAMES.metro[lang]}</a>
                                </li>
                            </ul>
                            <div className="tab-content">
                                <div className="tab-pane active">
                                    <AccountContent accountBook={this.props.accountBook}/>
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
        render () {

        }
    });

    let ButtonGroup = React.createClass({
        render () {
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


    let AccountContent = React.createClass({
        propTypes: {
            accountBook: React.PropTypes.shape({
                accounts: React.PropTypes.array.isRequired
            }).isRequired
        },
        render() {
            let ATTRS = castleAccountModel.attrs,
                MESSAGES = castleAccountModel.messages,
                lang = mainComponent.lang,
                propAccountBook = this.props.accountBook,
                existsAccountBook = (propAccountBook && propAccountBook.accounts && propAccountBook.accounts.length > 0) ? true : false;

            return (
                <table className="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>{ATTRS.loginUserId[lang]}</th>
                        <th>{ATTRS.channel[lang]}</th>
                        <th>{ATTRS.createTime[lang]}</th>
                        <th>{ATTRS.deleteTime[lang]}</th>
                    </tr>
                    </thead>
                    <tbody>
                    { existsAccountBook ?
                        propAccountBook.accounts.map(function (account, index) {
                            return (
                                <tr key={index}>
                                    <td>{account[ATTRS.loginUserId.name]}</td>
                                    <td>{account[ATTRS.channel.name]}</td>
                                    <td>{commonDate.parseToString(account[ATTRS.createTime.name])}</td>
                                    <td>{commonDate.parseToString(account[ATTRS.deleteTime.name])}</td>
                                </tr>
                            )
                        })
                        :
                        <tr>
                            <td colSpan="4">{MESSAGES.notExistsAccount[lang]}</td>
                        </tr>
                    }
                    </tbody>
                </table>
            );
        }
    });


    Components.Castle.AccountBook = CastleDetailPage;
})();