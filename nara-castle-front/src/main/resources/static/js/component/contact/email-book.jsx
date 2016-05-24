/**
 * Created by hkkang on 2016-04-12.
 */

castle.component.EmailBook = castle.component.EmailBook || {};


( function () {
    //
    'use strict';

    // Import component module
    let commonAjax = NaraCommon.Ajax,
        commonDate = NaraCommon.Date,
        constant = castle.common.Const,
        castleModel = castle.common.Model,
        mainComponent = castle.component.common.Main;


    // Define Content attributes name
    let castleEmailModel = {
        attrs: {
            email:          { name: 'email',        KOR: '이메일',        USA: 'Email' },
            emailType:      { name: 'emailType',    KOR: '유형',          USA: 'Type' },
            verified:       { name: 'verified',     KOR: '유효확인 여부', USA: 'Verified' },
            verifiedTime:   { name: 'verifiedTime', KOR: '유효확인 일시', USA: 'Vefiried time' }
        },
        messages: {
            notRegisteredEmail: { KOR: '등록 된 email이 없습니다', USA: 'Not registered the email' }
        }
    };

    // Define components
    let CastleDetailPage = React.createClass({
        //
        statics: {
            FIND_EMAIL_BOOK_URL: constant.CTX + '/api/castellans/{id}/contacts/email-book'
        },
        propTypes: {
            id: React.PropTypes.string
        },
        getInitialState() {
            return {
                emailBook: {emails: []},
                contentModifiable: false
            };
        },
        componentDidMount() {
            this.requestEmailBook(this.props);
        },
        changeModifiableMode() {
            this.setState({contentModifiable: true});
        },
        changeViewMode() {
            this.setState({contentModifiable: false});
        },
        requestEmailBook(props) {
            commonAjax
                .getJSON(CastleDetailPage.FIND_EMAIL_BOOK_URL.replace('{id}', props.id))
                .done(function (emailBookResult) {
                    this.setState({emailBook: emailBookResult});
                }.bind(this));
        },
        render() {
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

    let Tab = React.createClass({
        //
        propTypes: {
            castleId: React.PropTypes.string.isRequired,
            emailBook: React.PropTypes.object,
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
                                    <EmailContent emailBook={this.props.emailBook}/>
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


    let EmailContent = React.createClass({
        propTypes: {
            emailBook: React.PropTypes.shape({
                emails: React.PropTypes.array.isRequired
            }).isRequired
        },
        render() {
            let ENUMS = castleModel.enums,
                ATTRS = castleEmailModel.attrs,
                MESSAGES = castleEmailModel.messages,
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
                            propEmailBook.emails.map(function (email) {
                                return (
                                    <tr key={email[ATTRS.email.name]}>
                                        <td>{email[ATTRS.email.name]}</td>
                                        <td>{ENUMS.emailType[email[ATTRS.emailType.name]][lang]}</td>
                                        <td>{ENUMS.verified[email[ATTRS.verified.name]][lang]}</td>
                                        <td>{commonDate.parseToString(email[ATTRS.verifiedTime.name])}</td>
                                    </tr>
                                )
                            })
                            :
                            <tr>
                                <td colSpan="4">{MESSAGES.notRegisteredEmail[lang]}</td>
                            </tr>
                        }
                        </tbody>
                    </table>
                    <ButtonGroup modifiable={this.props.modifiable}/>
                </article>
            );
        }
    });


    castle.component.EmailBook = CastleDetailPage;
})();