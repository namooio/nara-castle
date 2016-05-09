/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.Tab = Components.Castle.Tab || { };

( function () {
    //
    'use strict';

    // Import component module
    var mainComponent = Components.Main,
        castleNamespace = Components.Castle;


    // Define Content attributes name
    var castleTabModel = {
        attrs: {
            basic:      { name: 'basic',    KOR: '기본정보',   USA: 'Basic information' },
            name:       { name: 'name',     KOR: '이름',       USA: 'Name' },
            phone:      { name: 'phone',    KOR: '전화번호',   USA: 'Phone number' },
            email:      { name: 'email',    KOR: '이메일',     USA: 'Email' },
            address:    { name: 'address',  KOR: '주소',       USA: 'Address' },
            account:    { name: 'account',  KOR: '계정이력',   USA: 'Account history' },
            state:      { name: 'state',    KOR: '상태이력',   USA: 'State history' },
            metro:      { name: 'metro',    KOR: '메트로이력', USA: 'Metro history' }
        }
    };


    // Define components
    var CastleDetailPage = React.createClass({
        //
        propTypes : {
            id: React.PropTypes.string.isRequired,
            contentType: React.PropTypes.string
        },
        getDefaultProps: function () {
            return {
                contentType: 'basic'
            };
        },
        getInitialState: function () {
            return {
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
        },
        componentWillReceiveProps: function () {
            this.setState({ contentModifiable: false })
        },
        changeModifiableMode: function () {
            this.setState({ contentModifiable: true });
        },
        changeViewMode: function () {
            this.setState({ contentModifiable: false });
        },
        setCastle: function (castle) {
            this.setState({ castle: castle })
        },
        render: function () {
            return (
                <Tab
                    contentType={this.props.contentType}
                    castleId={this.props.id}
                    castle={this.state.castle}
                    modifiable={this.state.contentModifiable}
                    changeModifiableMode={this.changeModifiableMode}
                    changeViewMode={this.changeViewMode}
                    setCastle={this.setCastle}
                />
            );
        }
    });

    var Tab = React.createClass({
        //
        propTypes: {
            contentType: React.PropTypes.string.isRequired,
            castleId: React.PropTypes.string.isRequired,
            castle: React.PropTypes.object,
            modifiable: React.PropTypes.bool.isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired,
            changeViewMode: React.PropTypes.func.isRequired,
            setCastle: React.PropTypes.func.isRequired
        },
        getContentByType: function () {
            //
            var TAB_NAMES = castleTabModel.attrs;

            switch (this.props.contentType) {
                case TAB_NAMES.basic.name:
                    return castleNamespace.Basic;
                case TAB_NAMES.name.name:
                    return castleNamespace.NameBook;
                case TAB_NAMES.phone.name:
                    return castleNamespace.PhoneBook;
                case TAB_NAMES.email.name:
                    return castleNamespace.EmailBook;
                case TAB_NAMES.address.name:
                    return castleNamespace.AddressBook;
                case TAB_NAMES.account.name:
                    return castleNamespace.AccountBook;
                case TAB_NAMES.state.name:
                    return castleNamespace.StateBook;
                case TAB_NAMES.metro.name:
                    return castleNamespace.MetroBook;
            }
        },
        render: function () {
            var TAB_NAMES = castleTabModel.attrs,
                lang = mainComponent.lang,
                contentComponent = this.getContentByType();


            return (
                <div className="container" >
                    <div className="panel panel-success">
                        <div className="panel-body">
                            <ul className="nav nav-tabs">
                                <li className={ this.props.contentType === TAB_NAMES.basic.name ? "active" : null }>
                                    <a href={"#/castle/basic?contentType=" + TAB_NAMES.basic.name + '&id=' + this.props.castleId}>{TAB_NAMES.basic[lang]}</a>
                                </li>
                                <li className={ this.props.contentType === TAB_NAMES.name.name ? "active" : null }>
                                    <a href={"#/castle/contact/name-book?contentType=" + TAB_NAMES.name.name + '&id=' + this.props.castleId}>{TAB_NAMES.name[lang]}</a>
                                </li>
                                <li className={ this.props.contentType === TAB_NAMES.phone.name ? "active" : null }>
                                    <a href={"#/castle/contact/phone-book?contentType=" + TAB_NAMES.phone.name + '&id=' + this.props.castleId}>{TAB_NAMES.phone[lang]}</a>
                                </li>
                                <li className={ this.props.contentType === TAB_NAMES.email.name ? "active" : null }>
                                    <a href={"#/castle/contact/email-book?contentType=" + TAB_NAMES.email.name + '&id=' + this.props.castleId}>{TAB_NAMES.email[lang]}</a>
                                </li>
                                <li className={ this.props.contentType === TAB_NAMES.address.name ? "active" : null }>
                                    <a href={"#/castle/contact/address-book?contentType=" + TAB_NAMES.address.name + '&id=' + this.props.castleId}>{TAB_NAMES.address[lang]}</a>
                                </li>
                                <li className={ this.props.contentType === TAB_NAMES.account.name ? "active" : null }>
                                    <a href={"#/castle/history/account-book?contentType=" + TAB_NAMES.account.name + '&id=' + this.props.castleId}>{TAB_NAMES.account[lang]}</a>
                                </li>
                                <li className={ this.props.contentType === TAB_NAMES.state.name ? "active" : null }>
                                    <a href={"#/castle/history/state-book?contentType=" + TAB_NAMES.state.name + '&id=' + this.props.castleId}>{TAB_NAMES.state[lang]}</a>
                                </li>
                                <li className={ this.props.contentType === TAB_NAMES.metro.name ? "active" : null }>
                                    <a href={"#/castle/history/metro-book?contentType=" + TAB_NAMES.metro.name + '&id=' + this.props.castleId}>{TAB_NAMES.metro[lang]}</a>
                                </li>
                            </ul>
                            <div className="tab-content">
                                <div className="tab-pane active">
                                    { React.createElement(contentComponent, {
                                        castleId: this.props.castleId,
                                        castle: this.props.castle,
                                        modifiable: this.props.modifiable,
                                        changeModifiableMode: this.props.changeModifiableMode,
                                        changeViewMode: this.props.changeViewMode,
                                        setCastle: this.props.setCastle
                                    }) }
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
    });


    Components.Castle.Tab = CastleDetailPage;
})();