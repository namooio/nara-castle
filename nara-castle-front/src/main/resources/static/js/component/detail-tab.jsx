/**
 * Created by hkkang on 2016-04-12.
 */

import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';

import { Const as Constant } from 'app/common/castle-common';
import MainComponent from 'app/component/common/main.jsx';


'use strict';

// Define Content attributes name
const castleTabModel = {
    //
    attrs: {
        'basic':        { name: 'basic',        url: 'basic',            KOR: '기본정보',     USA: 'Basic information' },
        'nameBook':    { name: 'nameBook',     url: 'name-book',       KOR: '이름',         USA: 'Name' },
        'phoneBook':   { name: 'phoneBook',    url: 'phone-book',      KOR: '전화번호',     USA: 'Phone number' },
        'emailBook':   { name: 'emailBook',    url: 'email-book',      KOR: '이메일',       USA: 'Email' },
        'addressBook': { name: 'addressBook', url: 'address-book',    KOR: '주소',         USA: 'Address' },
        'accountBook': { name: 'accountBook', url: 'account-book',    KOR: '계정이력',     USA: 'Account history' },
        'stateBook':   { name: 'stateBook',    url: 'state-book',      KOR: '상태이력',     USA: 'State history' },
        'metroBook':   { name: 'metroBook',    url: 'metro-book',      KOR: '메트로이력',   USA: 'Metro history' }
    }
};


// Define components
class CastleDetailPage extends Component {
    //
    constructor(props) {
        super(props);

        this.defaultProps = {
            contentType: 'basic'
        };

        this.state = {
            castle: {
                contact: {
                    nameBook: { names: [] },
                    phoneBook: { phones: [] },
                    emailBook: { emails: [] },
                    addressBook: { addresses: [] }
                },
                history: {
                    accountBook: { accounts: [] },
                    stateBook: { states: [] },
                    metroBook: { metros: [] }
                },
                basic: { castellan: {} }
            },
            contentModifiable: false
        };

        this.changeModifiableMode = this.changeModifiableMode.bind(this);
        this.changeViewMode = this.changeViewMode.bind(this);
        this.setCastle = this.setCastle.bind(this);
    }
    componentWillReceiveProps() {
        this.setState({ contentModifiable: false })
    }
    // custom
    changeModifiableMode() {
        this.setState({ contentModifiable: true });
    }
    changeViewMode() {
        this.setState({ contentModifiable: false });
    }
    setCastle(castle) {
        this.setState({ castle: castle })
    }
    render() {
        //
        let contentType = this.props.location.query.contentType || 'basic';

        return (
            <Tab
                castleId={this.props.params.castleId}
                contentType={contentType}
                contentComponent={React.cloneElement(this.props.children, {
                    castleId: this.props.params.castleId,
                    castle: this.state.castle,
                    modifiable: this.state.contentModifiable,
                    changeModifiableMode: this.changeModifiableMode,
                    changeViewMode: this.changeViewMode,
                    setCastle: this.setCastle
                })}
            />
        );
    }
}

CastleDetailPage.propTypes = {
    contentType: PropTypes.string
};


class Tab extends Component {
    //
    constructor(props) {
        super(props);
    }
    render() {
        //
        const TAB_NAMES = castleTabModel.attrs,
            LANG = MainComponent.lang;

        let contentType = this.props.contentType,
            baseLinkUrl = `${Constant.PAV_CTX_HASH}/castle/${this.props.castleId}`;

        return (
            <div className="container">
                <div className="panel panel-success">
                    <div className="panel-body">
                        <ul className="nav nav-tabs">
                            <li className={ contentType === TAB_NAMES.basic.name ? "active" : null }>
                                <Link to={`${baseLinkUrl}/${TAB_NAMES.basic.url}?contentType=${TAB_NAMES.basic.name}`}>{TAB_NAMES.basic[LANG]}</Link>
                            </li>
                            <li className={ contentType === TAB_NAMES.nameBook.name ? "active" : null }>
                                <Link to={`${baseLinkUrl}/${TAB_NAMES.nameBook.url}?contentType=${TAB_NAMES.nameBook.name}`}>{TAB_NAMES.nameBook[LANG]}</Link>
                            </li>
                            <li className={ contentType === TAB_NAMES.phoneBook.name ? "active" : null }>
                                <Link to={`${baseLinkUrl}/${TAB_NAMES.phoneBook.url}?contentType=${TAB_NAMES.phoneBook.name}`}>{TAB_NAMES.phoneBook[LANG]}</Link>
                            </li>
                            <li className={ contentType === TAB_NAMES.emailBook.name ? "active" : null }>
                                <Link to={`${baseLinkUrl}/${TAB_NAMES.emailBook.url}?contentType=${TAB_NAMES.emailBook.name}`}>{TAB_NAMES.emailBook[LANG]}</Link>
                            </li>
                            <li className={ contentType === TAB_NAMES.addressBook.name ? "active" : null }>
                                <Link to={`${baseLinkUrl}/${TAB_NAMES.addressBook.url}?contentType=${TAB_NAMES.addressBook.name}`}>{TAB_NAMES.addressBook[LANG]}</Link>
                            </li>
                            <li className={ contentType === TAB_NAMES.accountBook.name ? "active" : null }>
                                <Link to={`${baseLinkUrl}/${TAB_NAMES.accountBook.url}?contentType=${TAB_NAMES.accountBook.name}`}>{TAB_NAMES.accountBook[LANG]}</Link>
                            </li>
                            <li className={ contentType === TAB_NAMES.stateBook.name ? "active" : null }>
                                <Link to={`${baseLinkUrl}/${TAB_NAMES.stateBook.url}?contentType=${TAB_NAMES.stateBook.name}`}>{TAB_NAMES.stateBook[LANG]}</Link>
                            </li>
                            <li className={ contentType === TAB_NAMES.metroBook.name ? "active" : null }>
                                <Link to={`${baseLinkUrl}/${TAB_NAMES.metroBook.url}?contentType=${TAB_NAMES.metroBook.name}`}>{TAB_NAMES.metroBook[LANG]}</Link>
                            </li>
                        </ul>
                        <div className="tab-content">
                            <div className="tab-pane active">
                                {this.props.contentComponent}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

Tab.propTypes = {
    //
    castleId: PropTypes.string.isRequired,
    contentType: PropTypes.string.isRequired,
    contentComponent: PropTypes.object.isRequired
};


export default CastleDetailPage;
