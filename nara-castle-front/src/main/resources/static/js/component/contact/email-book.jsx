/**
 * Created by hkkang on 2016-04-12.
 */

import React, { Component, PropTypes} from 'react';
import { Ajax as NaraAjax, Object as NaraObject, Date as NaraDate } from 'app/lib/nara-common';
import { Const as Constant } from 'app/common/castle-common';
import CastleModel from 'app/common/castle-model';
import MainComponent from 'app/component/common/main.jsx';


'use strict';

// Define Content attributes name
const CastleEmailModel = {
    //
    attrs: {
        email:          { name: 'email',        KOR: '이메일',        USA: 'Email' },
        emailType:      { name: 'emailType',    KOR: '유형',          USA: 'Type' },
        verified:       { name: 'verified',     KOR: '유효확인 여부', USA: 'Verified' },
        verifiedTime:   { name: 'verifiedTime', KOR: '유효확인 일시', USA: 'Vefiried time' }
    },
    messages: {
        notRegisteredEmail: { KOR: '등록 된 Email이 없습니다',      USA: 'Not registered the email' },
        completeSave:       { KOR: 'EmailBook이 저장되었습니다.',   USA: 'Save has been completed.' }
    }
};


// Define components
class EmailContent extends Component {
    //
    constructor(props) {
        super(props);

        this.setEmailBook = this.setEmailBook.bind(this);
        this.requestFindEmailBook = this.requestFindEmailBook.bind(this);
        this.requestSaveEmailBook = this.requestSaveEmailBook.bind(this);
    }
    // overriding
    componentDidMount() {
        this.requestFindEmailBook(this.props.castleId);
    }
    // custom
    setEmailBook(emailBook) {
        let castle = this.props.castle;

        castle.contact.emailBook = emailBook;
        this.props.setCastle(castle);
    }
    // request
    requestFindEmailBook(castleId) {
        //
        NaraAjax
            .getJSON(EmailContent.url.FIND_EMAIL_BOOK.replace('{id}', castleId))
            .done( function (emailBookResult) {
                this.setEmailBook(emailBookResult);
            }.bind(this));
    }
    requestSaveEmailBook(emailBook) {
        //
        NaraAjax
            .postJSON(EmailContent.url.ATTACH_EMAIL_BOOK.replace('{id}', this.props.castleId), emailBook)
            .done( function () {
                const LANG = MainComponent.lang;

                this.setEmailBook(emailBook);
                alert(CastleEmailModel.messages.completeSave[LANG]);
                this.props.changeViewMode();
            }.bind(this));
    }
    render() {
        //
        return (
            this.props.modifiable ?
                <EmailModifiableContent
                    emailBook={this.props.castle.contact.emailBook}
                    changeViewMode={this.props.changeViewMode}
                    saveEmailBook={this.requestSaveEmailBook}
                />
                :
                <EmailViewContent
                    emailBook={this.props.castle.contact.emailBook}
                    changeModifiableMode={this.props.changeModifiableMode}
                />
        );
    }
}

EmailContent.url = {
    //
    FIND_EMAIL_BOOK: `${Constant.PAV_CTX_API}/api/castellans/{id}/contacts/email-book`,
    ATTACH_EMAIL_BOOK: `${Constant.PAV_CTX_API}/api/castellans/{id}/contacts/email-book`
};
EmailContent.propTypes = {
    //
    castleId: PropTypes.string.isRequired,
    castle: PropTypes.shape({
        contact: PropTypes.shape({
            emailBook: PropTypes.shape({
                emails: PropTypes.array.isRequired
            }).isRequired
        }).isRequired
    }).isRequired,
    modifiable: PropTypes.bool.isRequired,

    changeModifiableMode: PropTypes.func.isRequired,
    changeViewMode: PropTypes.func.isRequired,
    setCastle: PropTypes.func.isRequired
};


class EmailViewContent extends Component {
    //
    constructor(props) {
        super(props);

        this.modifiableModeBtnClick = this.modifiableModeBtnClick.bind(this);
    }
    // event
    modifiableModeBtnClick() {
        this.props.changeModifiableMode();
    }
    removeBtnClick() {

    }
    render() {
        //
        const ENUMS = CastleModel.enums,
            BUTTON_NAMES = CastleModel.buttons,
            ATTRS = CastleEmailModel.attrs,
            MESSAGES = CastleEmailModel.messages,
            LANG = MainComponent.lang;

        let propEmailBook = this.props.emailBook,
            existsEmailBook = propEmailBook.emails.length > 0;

        return (
            <article>
                <table className="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>{ATTRS.email[LANG]}</th>
                        <th>{ATTRS.emailType[LANG]}</th>
                        <th>{ATTRS.verified[LANG]}</th>
                        <th>{ATTRS.verifiedTime[LANG]}</th>
                    </tr>
                    </thead>
                    <tbody>
                    { existsEmailBook ?
                        propEmailBook.emails.map( function (email, index) {
                            return (
                                <tr key={index}>
                                    <td>{email[ATTRS.email.name]}</td>
                                    <td>{ENUMS.emailType[email[ATTRS.emailType.name]][LANG]}</td>
                                    <td>{ENUMS.verified[email[ATTRS.verified.name]][LANG]}</td>
                                    <td>{NaraDate.parseToString(email[ATTRS.verifiedTime.name])}</td>
                                </tr>
                            )
                        })
                        :
                        <tr>
                            <td colSpan="4">{MESSAGES.notRegisteredEmail[LANG]}</td>
                        </tr>
                    }
                    </tbody>
                </table>
                <div className="btn-toolbar pull-right">
                    <button type="button" className="btn-group btn btn-default"
                            onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.modify[LANG]}</button>
                    <button type="button" className="btn-group btn btn-danger"
                            onClick={this.removeBtnClick}>{BUTTON_NAMES.remove[LANG]}</button>
                </div>
            </article>
        );
    }
}

EmailViewContent.propTypes = {
    //
    emailBook: PropTypes.shape({
        emails: PropTypes.array.isRequired
    }).isRequired,
    changeModifiableMode: PropTypes.func.isRequired
};


class EmailModifiableContent extends Component {
    //
    constructor(props) {
        super(props);

        this.state = {
            willModifyEmails: [],
            inputProgressEmails : [],
            rowsModifiable: []
        };

        this.emailChange = this.emailChange.bind(this);
        this.emailTypeChange = this.emailTypeChange.bind(this);
        this.completeEmailBtnClick = this.completeEmailBtnClick.bind(this);
        this.cancelEmailBtnClick = this.cancelEmailBtnClick.bind(this);
        this.modifyEmailBtnClick = this.modifyEmailBtnClick.bind(this);
        this.removeEmailBtnClick = this.removeEmailBtnClick.bind(this);
        this.addEmailBtnClick = this.addEmailBtnClick.bind(this);
        this.saveEmailBookBtnClick = this.saveEmailBookBtnClick.bind(this);
        this.cancelModificationBtnClick = this.cancelModificationBtnClick.bind(this);
        this.setRowModifiableState = this.setRowModifiableState.bind(this);
        this.setProgressEmailState = this.setProgressEmailState.bind(this);
    }
    // overriding
    componentDidMount() {
        let emails = NaraObject.deepCopy(this.props.emailBook.emails),
            rowsModifiable = [];

        rowsModifiable.length = emails.length;
        rowsModifiable.fill(false);

        this.setState({
            willModifyEmails: emails,
            inputProgressEmails: NaraObject.deepCopy(emails),
            rowsModifiable: rowsModifiable
        });
    }
    // event
    emailChange(index, event) {
        this.setProgressEmailState(index, CastleEmailModel.attrs.email.name, event.target.value);
    }
    emailTypeChange(index, event) {
        this.setProgressEmailState(index, CastleEmailModel.attrs.emailType.name, event.target.value);
    }
    completeEmailBtnClick(index) {
        let emails = NaraObject.deepCopy(this.state.willModifyEmails);
        emails[index] = NaraObject.deepCopy(this.state.inputProgressEmails[index]);

        this.setState({ willModifyEmails: emails });
        this.setRowModifiableState(index, false);
    }
    cancelEmailBtnClick(index) {
        let emails = NaraObject.deepCopy(this.state.inputProgressEmails);
        emails[index] = NaraObject.deepCopy(this.state.willModifyEmails[index]);

        if (NaraObject.isEmpty(emails[index])) {
            emails.splice(index, 1);

            let rowsModifiable = this.state.rowsModifiable;
            rowsModifiable.splice(index, 1);

            this.setState({ rowsModifiable: rowsModifiable });
        }
        else {
            this.setRowModifiableState(index, false);
        }
        this.setState({ inputProgressEmails: emails });
    }
    modifyEmailBtnClick(index) {
        this.setRowModifiableState(index, true);
    }
    removeEmailBtnClick(index) {
        let progressEmails = this.state.inputProgressEmails,
            willModifyEmails = this.state.willModifyEmails,
            rowsModifiable = this.state.rowsModifiable;

        progressEmails.splice(index, 1);
        willModifyEmails.splice(index, 1);
        rowsModifiable.splice(index, 1);


        this.setState({
            inputProgressEmails: progressEmails,
            willModifyEmails: willModifyEmails,
            rowsModifiable: rowsModifiable
        });
    }
    addEmailBtnClick() {
        let emails = this.state.inputProgressEmails,
            rowsModifiable = this.state.rowsModifiable;

        emails.push({
            email: '',
            emailType: 'Private',
            verified: 'false',
            verifiedTime: 0
        });
        rowsModifiable.push(true);

        this.setState({ inputProgressEmails: emails, rowsModifiable: rowsModifiable });
    }
    saveEmailBookBtnClick() {
        this.props.saveEmailBook({ emails: this.state.willModifyEmails });
    }
    cancelModificationBtnClick() {
        this.props.changeViewMode();
    }
    // custom
    setRowModifiableState(index, modifiable) {
        //
        let rowsModifiable = this.state.rowsModifiable;

        rowsModifiable[index] = modifiable;
        this.setState({ rowsModifiable: rowsModifiable });
    }
    setProgressEmailState(index, propertyName, value) {
        //
        let emails = this.state.inputProgressEmails;

        emails[index][propertyName] = value;
        this.setState({ inputProgressEmails: emails });
    }
    render() {
        //
        const ENUMS = CastleModel.enums,
            BUTTON_NAMES = CastleModel.buttons,
            ATTRS = CastleEmailModel.attrs,
            MESSAGES = CastleEmailModel.messages,
            LANG = MainComponent.lang;

        let propEmailBook = this.props.emailBook,
            existsEmailBook = propEmailBook.emails.length > 0;

        return (
            <article>
                <table className="table table-stri1ed table-hover">
                    <thead>
                    <tr>
                        <th className="col-md-3">{ATTRS.email[LANG]}</th>
                        <th className="col-md-2">{ATTRS.emailType[LANG]}</th>
                        <th className="col-md-2">{ATTRS.verified[LANG]}</th>
                        <th className="col-md-3">{ATTRS.verifiedTime[LANG]}</th>
                        <th className="col-md-2">&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    { existsEmailBook ?
                        this.state.inputProgressEmails.map( function (email, index) {
                            return (
                                this.state.rowsModifiable[index] === true ?
                                    <tr key={index}>
                                        <td>
                                            <input type="text" className="form-control"
                                                   onChange={this.emailChange.bind(this, index)}
                                                   value={email[ATTRS.email.name]}
                                            />
                                        </td>
                                        <td>
                                            <select className="form-control" onChange={this.emailTypeChange.bind(this, index)}
                                                    value={email[ATTRS.emailType.name]}>
                                                <option value="">{ATTRS.emailType[LANG]}</option>
                                                { Object.keys(ENUMS.emailType).map( function (emailTypeKey, index) {
                                                    const EMAIL_TYPE_ENUM = ENUMS.emailType[emailTypeKey];
                                                    return (
                                                        <option key={index } value={EMAIL_TYPE_ENUM.name}>{EMAIL_TYPE_ENUM[LANG]}</option>
                                                    );
                                                })}
                                            </select>
                                        </td>
                                        <td>
                                            <input type="text" className="form-control" readOnly="readOnly"
                                                   value={ENUMS.verified[email[ATTRS.verified.name]][LANG]}
                                            />
                                        </td>
                                        <td>
                                            <input type="text" className="form-control" readOnly="readOnly"
                                                   value={email[ATTRS.verifiedTime.name]}
                                            />
                                        </td>
                                        <td>
                                            <div className="btn-group btn-group-sm text-center">
                                                <button type="button" className="btn btn-sm btn-primary"
                                                        onClick={this.completeEmailBtnClick.bind(this, index)}>
                                                    {BUTTON_NAMES.complete[LANG]}
                                                </button>
                                                <button type="button" className="btn btn-sm btn-default"
                                                        onClick={this.cancelEmailBtnClick.bind(this, index)}>
                                                    {BUTTON_NAMES.cancel[LANG]}
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                    :
                                    <tr key={index}>
                                        <td>{email[ATTRS.email.name]}</td>
                                        <td>{ENUMS.emailType[email[ATTRS.emailType.name]][LANG]}</td>
                                        <td>{ENUMS.verified[email[ATTRS.verified.name]][LANG]}</td>
                                        <td>{NaraDate.parseToString(email[ATTRS.verifiedTime.name])}</td>
                                        <td>
                                            <div className="btn-group btn-group-sm text-center">
                                                <button type="button" className="btn btn-sm btn-default"
                                                        onClick={this.modifyEmailBtnClick.bind(this, index)}>
                                                    {BUTTON_NAMES.modify[LANG]}
                                                </button>
                                                <button type="button" className="btn btn-sm btn-danger"
                                                        onClick={this.removeEmailBtnClick.bind(this, index)}>
                                                    {BUTTON_NAMES.remove[LANG]}
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                            )
                        }.bind(this))
                        :
                        <tr>
                            <td colSpan="5">{MESSAGES.notRegisteredEmail[LANG]}</td>
                        </tr>
                    }
                    </tbody>
                </table>
                <div className="btn-block btn-toolbar">
                    <div className="pull-right">
                        <button type="button" className="btn btn-default btn-sm"
                                onClick={this.addEmailBtnClick}>
                            {BUTTON_NAMES.add[LANG]}
                        </button>
                    </div>
                </div>
                <div className="btn-toolbar pull-right">
                    <button type="button" className="btn-group btn btn-primary"
                            onClick={this.saveEmailBookBtnClick}>
                        {BUTTON_NAMES.save[LANG]}
                    </button>
                    <button type="button" className="btn-group btn btn-default"
                            onClick={this.cancelModificationBtnClick}>
                        {BUTTON_NAMES.cancel[LANG]}
                    </button>
                </div>
            </article>
        );
    }
}

EmailModifiableContent.propTypes = {
    //
    emailBook: PropTypes.shape({
        emails: PropTypes.array.isRequired
    }).isRequired,
    changeViewMode: PropTypes.func.isRequired,
    saveEmailBook: PropTypes.func.isRequired
};

export default EmailContent;