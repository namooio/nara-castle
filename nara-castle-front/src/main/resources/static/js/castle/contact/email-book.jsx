/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.EmailBook = Components.Castle.EmailBook || {};

(function () {
    //
    'use strict';

    // Import component module
    var castleCommon = CastleCommon,
        castleConst = CastleConst,
        mainComponent = Components.Main,
        castleModel = Components.Castle.Model;

    // Define Content attributes name

    // Define components
    var CastleDetailPage = React.createClass({
        //
        propTypes : {
            id: React.PropTypes.string
        },
        getInitialState: function () {
            return {
                emailBook: { emails: [] },
                contentModifiable: false
            };
        },
        componentDidMount: function () {
            this.requestEmailBook(this.props);
        },
        componentWillReceiveProps: function (props) {
            this.requestEmailBook(props);
        },
        changeModifiableMode: function () {
            this.setState({contentModifiable: true});
        },
        changeViewMode: function () {
            this.setState({contentModifiable: false});
        },
        requestEmailBook: function (props) {
            castleCommon.getJSON(castleConst.CTX + '/api/castle/' + props.id + '/email-book').done( function (emailBookResult) {
                this.setState({ emailBook: emailBookResult });
            }.bind(this));
        },
        render: function () {
            return (
                <Tab
                    castleId={this.props.id}
                    emailBook={this.state.emailBook}
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
            castleId: React.PropTypes.string,
            emailBook: React.PropTypes.object,
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
                                <li className="active">
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
                                    <EmailContent emailBook={this.props.emailBook} />
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



    var EmailContent = React.createClass({
        propTypes: {
            emailBook: React.PropTypes.object.isRequired
        },
        render: function () {
            var ATTRS = castleModel.email,
                lang = mainComponent.lang,
                propEmailBook = this.props.emailBook,
                existsEmailBook = (propEmailBook && propEmailBook.emails && propEmailBook.emails.length > 0) ? true : false;

            return (
                <article>
                    <table className="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>{ATTRS.email[lang]}</th>
                                <th>{ATTRS.emailType[lang]}</th>
                                <th>{ATTRS.verified[lang]}</th>
                                <th>{ATTRS.verifiedTime[lang]}</th>
                            </tr>
                        </thead>
                        <tbody>
                        { existsEmailBook ?
                            propEmailBook.emails.map( function (email) {
                                return (
                                    <tr key={email[ATTRS.email.name]}>
                                        <td>{email[ATTRS.email.name]}</td>
                                        <td>{email[ATTRS.emailType.name]}</td>
                                        <td>{(email[ATTRS.verified.name]).toString()}</td>
                                        <td>{castleCommon.Date.parseToString(email[ATTRS.verifiedTime.name])}</td>
                                    </tr>
                                )
                            })
                            :
                            <tr><td colSpan="4">등록 된 이메일이 없습니다.</td></tr>
                        }
                        </tbody>
                    </table>
                    <ButtonGroup modifiable={this.props.modifiable}/>
                </article>
            );
        }
    });


    Components.Castle.EmailBook = CastleDetailPage;
})();