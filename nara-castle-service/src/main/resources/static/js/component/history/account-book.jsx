/**
 * Created by hkkang on 2016-04-12.
 */

import React, { Component, PropTypes} from 'react';
import { Ajax as NaraAjax, Date as NaraDate } from 'nara';
import { Constant } from 'app/common/castle-common';
import MainComponent from 'app/component/common/main.jsx';


'use strict';

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
class AccountContent extends Component {
    //
    constructor(props) {
        //
        super(props);

        this.setAccountBook = this.setAccountBook.bind(this);
        this.requestFindAccountBook = this.requestFindAccountBook.bind(this);
    }
    // overriding
    componentDidMount() {
        this.requestFindAccountBook(this.props.castleId);
    }
    // custom
    setAccountBook(accountBook) {
        let castle = this.props.castle;

        castle.history.accountBook = accountBook;
        this.props.setCastle(castle);
    }
    // request
    requestFindAccountBook(castleId) {
        //
        NaraAjax
            .getJSON(AccountContent.url.FIND_ACCOUNT_BOOK.replace('{id}', castleId))
            .done( function (resultAccountBook) {
                this.setAccountBook(resultAccountBook);
            }.bind(this));
    }
    render() {
        //
        return (
            <AccountViewContent
                accountBook={this.props.castle.history.accountBook}
            />
        );
    }
}

AccountContent.propTypes = {
    //
    castleId: PropTypes.string,
    castle: PropTypes.shape({
        history: PropTypes.shape({
            accountBook: PropTypes.shape({
                accounts: PropTypes.array
            })
        })
    })
};

AccountContent.url = {
    //
    FIND_ACCOUNT_BOOK: `${Constant.PAV_CTX.api}/castles/{id}/histories/account-book`
};


class AccountViewContent extends Component {
    //
    constructor(props) {
        super(props);
    }
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
}

AccountViewContent.propTypes = {
    //
    accountBook: PropTypes.shape({
        accounts: PropTypes.array.isRequired
    }).isRequired
};

export default AccountContent;

