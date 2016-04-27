/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.Tab = Components.Castle.Tab || {};

(function () {
    //
    // Import component module
    'use strict';

    var castleModel = Components.Castle.Model,
        mainComponent = Components.Main;


    // Define components
    var CastleDetailPage = React.createClass({
        //
        propTypes : {
            contentType: React.PropTypes.string.isRequired,
            id: React.PropTypes.string
        },
        getDefaultProps: function () {
            return {
                contentType: 'basic'
            };
        },
        /*
        getInitialState: function () {
            return {
                castle: {
                    basicInfo: { castellan: {} },
                    nameBook: { names: [] },
                    phoneBook: { phones: [] },
                    emailBook: { emails: [] },
                    addressBook: { addresses: [] },
                    accountBook: { accounts: [] },
                    stateBook: { states: [] },
                    metroBook: { metros: [] }
                },
                contentModifiable: false
            };
        },
        componentDidMount: function () {
            console.debug('Exectue detal.jsx CastleDetailPage componentDidMount');
            castleCommon.getJSON(castleConst.CTX + '/api/castle/' + this.props.id).done( function (castleResult) {
                console.debug('Exectue detal.jsx CastleDetailPage componentDidMount callback');

                var castleState = this.state.castle;

                castleState.basicInfo = castleResult;
                this.setState({castle: castleState});
                console.dir(this.props);
                console.dir(this.state);
            }.bind(this));
        },
        componentWillReceiveProps: function (props) {
            var CONTENT_TYPES = contentProps.tabs,
                url,
                callback;

            console.debug('Exectue detal.jsx CastleDetailPage componentWillReceiveProps -> ');

            switch (props.contentType) {
                case CONTENT_TYPES.basic.name:
                    console.debug('basic');
                    url = '/api/castle/' + props.id;
                    callback = function (castleResult) {
                        var castleState = this.state.castle;
                        castleState.basicInfo = castleResult;
                        this.setState({castle: castleState});
                    }.bind(this);
                    break;
                case CONTENT_TYPES.name.name:
                    console.debug('name');
                    url = '/api/castle/' + props.id + '/name-book';
                    callback = function (nameBookResult) {
                        var castleState = this.state.castle;
                        castleState.nameBook = nameBookResult;
                        this.setState( {castle: castleState} );
                        console.debug('Execute detail.jsx NameContent componentDidMount()');
                        console.dir(this.props);
                        console.dir(this.state);
                    };
                    break;
                case CONTENT_TYPES.phone.name:
                    url = '/api/castle/' + props.id + '/phone-book';
                    callback = function (phoneBookdResult) {
                        var castleState = this.state.castle;
                        castleState.phoneBook = phoneBookdResult;
                        this.setState( {castle: castleState} );
                    };
                    break;
                case CONTENT_TYPES.email.name:
                    url = '/api/castle/' + props.id + '/email-book';
                    callback = function (emailBookResult) {
                        var castleState = this.state.castle;
                        castleState.emailBook = emailBookResult;
                        this.setState( { castle: castleState } );
                    };
                    break;
                case CONTENT_TYPES.address.name:
                    url = '/api/castle/' + props.id + '/address-book';
                    callback = function (addressBookResult) {
                        var castleState = this.state.castle;
                        castleState.addressBook = addressBookResult;
                        this.setState( { castle: castleState });
                    };
                    break;
                case CONTENT_TYPES.account.name:
                    url = '/api/castle/' + props.id + '/account-book';
                    callback = function (accountBookResult) {
                        var castleState = this.state.castle;
                        castleState.accountBook = accountBookResult;
                        this.setState( { castle: castleState });
                    };
                    break;
                case CONTENT_TYPES.state.name:
                    url = '/api/castle/' + props.id + '/state-book';
                    callback = function (stateBookResult) {
                        var castleState = this.state.castle;
                        castleState.stateBook = stateBookResult;
                        this.setState( { castle: castleState });
                    };
                    break;
                case CONTENT_TYPES.metro.name:
                    url = '/api/castle/' + props.id + '/metro-book';
                    callback = function (metroBookResult) {
                        var castleState = this.state.castle;
                        castleState.metroBook = metroBookResult;
                        this.setState( { castle: castleState });
                    };
                    break;
            }
            castleCommon.getJSON(castleConst.CTX + url).done(callback.bind(this));
        },
        */
        changeModifiableMode: function () {
            this.setState({contentModifiable: true});
        },
        changeViewMode: function () {
            this.setState({contentModifiable: false});
        },
        render: function () {
            return (
                <article>
                    <Tab
                        contentType={this.props.contentType}
                        castle={this.state.castle}
                        modifiable={this.state.contentModifiable}
                        changeModifiableMode={this.changeModifiableMode}
                        changeViewMode={this.changeViewMode}
                    />
                </article>
            );
        }
    });

    var Tab = React.createClass({
        //
        propTypes: {
            contentType: React.PropTypes.string.isRequired,
            castle: React.PropTypes.object,
            modifiable: React.PropTypes.bool.isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired
        },
        render: function () {
            var lang = mainComponent.lang,
                TAB_NAMES = castleModel.tabs,
                currentContentType = this.props.contentType,
                content;

            switch (currentContentType) {
                case TAB_NAMES.basic.name:
                    content = <BasicInfoContent
                        basicInfo={this.props.castle.basicInfo}
                        castellan={this.props.castle.castellan}
                        modifiable={this.props.modifiable}
                        changeModifiableMode={this.props.changeModifiableMode}
                        changeViewMode={this.props.changeViewMode}
                    />
                    break;
                case TAB_NAMES.name.name:
                    content = <NameContent
                        nameBook={this.props.castle.nameBook}
                    />
                    break;
                case TAB_NAMES.phone.name:
                    content = <PhoneContent
                        phoneBook={this.props.castle.phoneBook}
                    />
                    break;
                case TAB_NAMES.email.name:
                    content = <EmailContent
                        emailBook={this.props.castle.emailBook}
                    />
                    break;
                case TAB_NAMES.address.name:
                    content = <AddressContent
                        addressBook={this.props.castle.addressBook}
                    />
                    break;
                case TAB_NAMES.account.name:
                    content = <AccountContent
                        accountBook={this.props.castle.accountBook}
                    />
                    break;
                case TAB_NAMES.state.name:
                    content = <StateContent
                        stateBook={this.props.castle.stateBook}
                    />
                    break;
                case TAB_NAMES.metro.name:
                    content = <MetroContent
                        metroBook={this.props.castle.metroBook}
                    />
                    break;
            }

            return (
                <div className="container" >
                    <div className="panel panel-success">
                        <div className="panel-body">
                            <ul className="nav nav-tabs">
                                <li key={TAB_NAMES.basic.name} className={(currentContentType === TAB_NAMES.basic.name) ? "active" : null}>
                                    <a href={"#/castle/basic?contentType=" + TAB_NAMES.basic.name + '&id=' + this.props.castle.basicInfo.id}>{TAB_NAMES.basic[lang]}</a>
                                </li>
                                <li key={TAB_NAMES.name.name} className={(currentContentType === TAB_NAMES.name.name) ? "active" : null}>
                                    <a href={"#/castle/contact/name-book?contentType=" + TAB_NAMES.name.name + '&id=' + this.props.castle.basicInfo.id}>{TAB_NAMES.name[lang]}</a>
                                </li>
                                <li key={TAB_NAMES.phone.name} className={(currentContentType === TAB_NAMES.phone.name) ? "active" : null}>
                                    <a href={"#/castle/contact/phone-book?contentType=" + TAB_NAMES.phone.name + '&id=' + this.props.castle.basicInfo.id}>{TAB_NAMES.phone[lang]}</a>
                                </li>
                                <li key={TAB_NAMES.email.name} className={(currentContentType === TAB_NAMES.email.name) ? "active" : null}>
                                    <a href={"#/castle/contact/email-book?contentType=" + TAB_NAMES.email.name + '&id=' + this.props.castle.basicInfo.id}>{TAB_NAMES.email[lang]}</a>
                                </li>
                                <li key={TAB_NAMES.address.name} className={(currentContentType === TAB_NAMES.address.name) ? "active" : null}>
                                    <a href={"#/castle/contact/address-book?contentType=" + TAB_NAMES.address.name + '&id=' + this.props.castle.basicInfo.id}>{TAB_NAMES.address[lang]}</a>
                                </li>
                                <li key={TAB_NAMES.account.name} className={(currentContentType === TAB_NAMES.account.name) ? "active" : null}>
                                    <a href={"#/castle/history/account-book?contentType=" + TAB_NAMES.account.name + '&id=' + this.props.castle.basicInfo.id}>{TAB_NAMES.account[lang]}</a>
                                </li>
                                <li key={TAB_NAMES.state.name} className={(currentContentType === TAB_NAMES.state.name) ? "active" : null}>
                                    <a href={"#/castle/history/state-book?contentType=" + TAB_NAMES.state.name + '&id=' + this.props.castle.basicInfo.id}>{TAB_NAMES.state[lang]}</a>
                                </li>
                                <li key={TAB_NAMES.metro.name} className={(currentContentType === TAB_NAMES.metro.name) ? "active" : null}>
                                    <a href={"#/castle/history/metro-book?contentType=" + TAB_NAMES.metro.name + '&id=' + this.props.castle.basicInfo.id}>{TAB_NAMES.metro[lang]}</a>
                                </li>
                            </ul>
                            <div className="tab-content">
                                <div className="tab-pane active">
                                    {content}
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