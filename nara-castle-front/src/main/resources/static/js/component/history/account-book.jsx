/**
 * Created by hkkang on 2016-04-12.
 */

castle.component.AccountBook = castle.component.AccountBook || {};


( function () {
    //
    'use strict';

    // Import component module
    const NaraAjax = NaraCommon.Ajax,
        NaraDate = NaraCommon.Date,
        Constant = castle.common.Const,
        MainComponent = castle.component.common.Main;


    // Define Content attributes name
    const CastleAccountModel = {
        //
        attrs: {
            loginUserId:    { name: 'loginUserId',  KOR: '로그인Id', USA: 'Login user id' },
            channel:        { name: 'channel',      KOR: '접속방법', USA: 'Channel' },
            createTime:     { name: 'createTime',   KOR: '생성일시', USA: 'Create time' },
            deleteTime:     { name: 'deleteTime',   KOR: '삭제일시', USA: 'Delete time' }
        },
        messages: {
            notExistsAccount:   { KOR: 'Account 이력이 없습니다.', USA: 'Not exists acount history' }
        }
    };

    // Define components
    let AccountContent = React.createClass({
        //
        statics: {
            FIND_ACCOUNT_BOOK_URL: Constant.PAV_CTX_API + '/api/castles/{id}/histories/account-book'
        },
        propTypes: {
            castleId: React.PropTypes.string.isRequired,
            castle: React.PropTypes.shape({
                history: React.PropTypes.shape({
                    accountBook: React.PropTypes.shape({
                        accounts: React.PropTypes.array.isRequired
                    }).isRequired
                }).isRequired
            }).isRequired
        },
        // overriding
        componentDidMount() {
            this.requestFindAccountBook(this.props.castleId);
        },
        // custom
        setAccountBook(accountBook) {
            let castle = this.props.castle;

            castle.history.accountBook = accountBook;
            this.props.setCastle(castle);
        },
        // request
        requestFindAccountBook(castleId) {
            //
            NaraAjax
                .getJSON(AccountContent.FIND_ACCOUNT_BOOK_URL.replace('{id}', castleId))
                .done( function (resultAccountBook) {
                    this.setAccountBook(resultAccountBook);
                }.bind(this));
        },
        render() {
            //
            return (
                <AccountViewContent
                    accountBook={this.props.castle.history.accountBook}
                />
            );
        }
    });


    let AccountViewContent = React.createClass({
        //
        propTypes: {
            accountBook: React.PropTypes.shape({
                accounts: React.PropTypes.array.isRequired
            }).isRequired
        },
        render() {
            //
            const ATTRS = CastleAccountModel.attrs,
                MESSAGES = CastleAccountModel.messages,
                LANG = MainComponent.lang;

            let existsAccountBook = this.props.accountBook.accounts.length > 0;

            return (
                <table className="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>{ATTRS.loginUserId[LANG]}</th>
                        <th>{ATTRS.channel[LANG]}</th>
                        <th>{ATTRS.createTime[LANG]}</th>
                        <th>{ATTRS.deleteTime[LANG]}</th>
                    </tr>
                    </thead>
                    <tbody>
                    { existsAccountBook ?
                        this.props.accountBook.accounts.map( function (account, index) {
                            return (
                                <tr key={index}>
                                    <td>{account[ATTRS.loginUserId.name]}</td>
                                    <td>{account[ATTRS.channel.name]}</td>
                                    <td>{NaraDate.parseToString(account[ATTRS.createTime.name])}</td>
                                    <td>{NaraDate.parseToString(account[ATTRS.deleteTime.name])}</td>
                                </tr>
                            )
                        })
                        :
                        <tr>
                            <td colSpan="4">{MESSAGES.notExistsAccount[LANG]}</td>
                        </tr>
                    }
                    </tbody>
                </table>
            );
        }
    });


    castle.component.AccountBook = AccountContent;
})();