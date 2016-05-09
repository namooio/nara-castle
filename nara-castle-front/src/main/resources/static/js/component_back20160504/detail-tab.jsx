/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.Tab = Components.Castle.Tab || { };

( function () {
    //
    'use strict';

    // Import component module
    var commonAjax = NaraCommon.Ajax,
        constant = CastleCommon.Const,
        mainComponent = Components.Main,
        castleModel = Components.Castle.Model;


    // Define Content attributes name

    
    // Define components
    var CastleDetailPage = React.createClass({
        //
        statics : {
            FIND_NAME_BOOK_URL: constant.CTX + '/api/castellans/{id}/contacts/name-book',
            ATTACH_NAME_BOOK_URL: constant.CTX + '/api/castellans/{id}/contacts/name-book'
        },
        propTypes : {
            id: React.PropTypes.string
        },
        getInitialState: function () {
            return {
                nameBook: { names: [] },
                contentModifiable: false
            };
        },
        componentDidMount: function () {
            this.requestFindNameBook(this.props);
        },
        changeModifiableMode: function () {
            this.setState({contentModifiable: true});
        },
        changeViewMode: function () {
            this.setState({contentModifiable: false});
        },
        requestFindNameBook: function (props) {
            commonAjax
                .getJSON(CastleDetailPage.FIND_NAME_BOOK_URL.replace('{id}', props.id))
                .done( function (nameBookResult) {
                    this.setState({ nameBook: nameBookResult });
                }.bind(this));
        },
        requestAttachNameBook: function (names) {
            var nameBook = {
                names: names
            };
            this.setState({ nameBook: nameBook });

            commonAjax
                .postJSON(CastleDetailPage.ATTACH_NAME_BOOK_URL.replace('{id}', this.props.id), nameBook)
                .done( function () {
                    var lang = mainComponent.lang;
                    alert(castleNameModel.messages.completeSave[lang]);
                });
        },
        //request
        render: function () {
            return (
                <Tab
                    castleId={this.props.id}
                    nameBook={this.state.nameBook}
                    modifiable={this.state.contentModifiable}
                    changeModifiableMode={this.changeModifiableMode}
                    changeViewMode={this.changeViewMode}
                    saveNameBook={this.requestAttachNameBook}
                />
            );
        }
    });

    var Tab = React.createClass({
        //
        propTypes: {
            castleId: React.PropTypes.string.isRequired,
            nameBook: React.PropTypes.object,
            modifiable: React.PropTypes.bool.isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired,
            changeViewMode: React.PropTypes.func.isRequired,
            saveNameBook: React.PropTypes.func.isRequired
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
                                <li className="active">
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
                                <li>
                                    <a href={"#/castle/history/metro-book?contentType=" + TAB_NAMES.metro.name + '&id=' + this.props.castleId}>{TAB_NAMES.metro[lang]}</a>
                                </li>
                            </ul>
                            <div className="tab-content">
                                <div className="tab-pane active">
                                    <Content
                                        nameBook={this.props.nameBook}
                                        modifiable={this.props.modifiable}
                                        changeModifiableMode={this.props.changeModifiableMode}
                                        changeViewMode={this.props.changeViewMode}
                                        saveNameBook={this.props.saveNameBook}
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
            nameBook: React.PropTypes.shape({
                names: React.PropTypes.array.isRequired
            }).isRequired,
            modifiable: React.PropTypes.bool.isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired,
            changeViewMode: React.PropTypes.func.isRequired,
            saveNameBook: React.PropTypes.func.isRequired
        },
        render : function () {

            return (
                this.props.modifiable ?
                    <NameModifiableContent
                        nameBook={this.props.nameBook}
                        changeViewMode={this.props.changeViewMode}
                        saveNameBook={this.props.saveNameBook}
                    />
                    :
                    <NameViewContent
                        nameBook={this.props.nameBook}
                        changeModifiableMode={this.props.changeModifiableMode}
                    />
            );
        }
    });




    Components.Castle.Tab = CastleDetailPage;
})();