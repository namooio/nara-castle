/**
 * Created by hkkang on 2016-04-12.
 */

import React, { Component, PropTypes } from 'react';
import { Ajax as NaraAjax, Object as NaraObject, Date as NaraDate } from 'app/lib/nara-common';
import { Constant } from 'app/common/castle-common';
import CastleModel from 'app/common/castle-model';
import MainComponent from 'app/component/common/main.jsx';


'use strict';

// Define Content attributes name
const CastleBasicModel = {
    //
    attrs: {
        id:         { name: 'id',                                     KOR: '아이디',      USA: 'Id' },
        name:       { name: 'name',         bookName: 'nameBook',   KOR: '이름',        USA: 'Name' },
        locale:     { name: 'locale',                                KOR: '지역',        USA: 'Locale' },
        state:      { name: 'state',                                 KOR: '상태',        USA: 'State' },
        buildTime:  { name: 'buildTime',                            KOR: '생성일시',    USA: 'Build time' },
        castellan: {
            primaryEmail:   { name: 'primaryEmail', bookName: 'emailBook', KOR: '기본 이메일',   USA: 'Primary email' },
            primaryPhone:   { name: 'primaryPhone', bookName: 'phoneBook', KOR: '기본 전화번호', USA: 'Primary phone number' },
            photo:          { name: 'photoId',      KOR: '사진',          USA: 'Photo' }
        }
    },
    messages: {
        notFoundCastle: { KOR: '해당 Id의 Castle 정보가 없습니다. -> Id: {id}', USA: 'Not found the Castle -> Id: {id}' },
        completeModify: { KOR: 'Basic이 수정 되었습니다.', USA: 'Modify has been completed.' },
        confirmRemove: {
            KOR: '삭제 시 Contact와 History 관련 모든 정보가 삭제되며 복구할 수 없습니다. 정말 삭제하시겠습니까?',
            USA: 'Are you really remove castle?'
        }
    }
};


class BasicContent extends Component {
    //
    constructor(props) {
        super(props);

        this.setBasic = this.setBasic.bind(this);
        this.setContactBook = this.setContactBook.bind(this);
        this.requestFindCastle = this.requestFindCastle.bind(this);
        this.requestFindNameBook = this.requestFindNameBook.bind(this);
        this.requestFindEmailBook = this.requestFindEmailBook.bind(this);
        this.requestFindPhoneBook = this.requestFindPhoneBook.bind(this);
        this.requestModifyBasic = this.requestModifyBasic.bind(this);
    }
    // overriding
    componentDidMount() {
        this.requestFindCastle(this.props.castleId);
        this.requestFindNameBook(this.props.castleId);
        this.requestFindEmailBook(this.props.castleId);
        this.requestFindPhoneBook(this.props.castleId);
    }
    // custom
    setBasic(castleBasic, castellan) {
        //
        let castle = this.props.castle;

        castle.basic = castleBasic;
        castle.basic.castellan = castellan || castle.basic.castellan;

        this.props.setCastle(castle);
    }
    setContactBook(propertyName, book) {
        let castle = this.props.castle;

        castle.contact[propertyName] = book;
        this.props.setCastle(castle);
    }
    // request
    requestFindCastle(castleId) {
        //
        let urlBuilder = NaraAjax.createUrlBuilder();

        urlBuilder.addUrl(BasicContent.url.FIND_CASTLE.replace('{id}', castleId));
        urlBuilder.addUrl(BasicContent.url.FIND_CASTELLAN.replace('{id}', castleId));

        NaraAjax.getJSONs(urlBuilder.build(), function (castleResult, castellanResult) {
            //
            if (NaraObject.isEmpty(castleResult)) {
                let MESSAGES = CastleBasicModel.messages,
                    lang = MainComponent.lang;

                alert(MESSAGES.notFoundCastle[lang].replace('{id}', castleId));
                return;
            }
            this.setBasic(castleResult, castellanResult);
        }.bind(this));
    }
    requestFindNameBook(castleId) {
        //
        NaraAjax
            .getJSON(BasicContent.url.FIND_NAME_BOOK.replace('{id}', castleId))
            .done( function (nameBook) {
                this.setContactBook(CastleBasicModel.attrs.name.bookName, nameBook);
            }.bind(this));
    }
    requestFindEmailBook(castleId) {
        //
        NaraAjax
            .getJSON(BasicContent.url.FIND_EMAIL_BOOK.replace('{id}', castleId))
            .done( function (emailBook) {
                this.setContactBook(CastleBasicModel.attrs.castellan.primaryEmail.bookName, emailBook);
            }.bind(this));
    }
    requestFindPhoneBook(castleId) {
        //
        NaraAjax
            .getJSON(BasicContent.url.FIND_PHONE_BOOK.replace('{id}', castleId))
            .done( function (phoneBook) {
                this.setContactBook(CastleBasicModel.attrs.castellan.primaryPhone.bookName, phoneBook);
            }.bind(this));
    }
    requestModifyBasic(castleBasic) {
        //
        let urlBuilder = NaraAjax.createUrlBuilder(),
            castleId = castleBasic.id;

        urlBuilder.addUrlAndParam(BasicContent.url.MODIFY_NAME.replace('{id}', castleId), castleBasic.name);
        urlBuilder.addUrlAndParam(BasicContent.url.MODIFY_LOCALE.replace('{id}', castleId), castleBasic.locale);
        urlBuilder.addUrlAndParam(BasicContent.url.MODIFY_PRIMARY_EMAIL.replace('{id}', castleId), castleBasic.castellan.primaryEmail);
        urlBuilder.addUrlAndParam(BasicContent.url.MODIFY_PRIMARY_PHONE.replace('{id}', castleId), castleBasic.castellan.primaryPhone);

        if (castleBasic.state === CastleModel.enums.state.Open.name) {
            urlBuilder.addUrlAndParam(BasicContent.url.REOPEN_CASTLE.replace('{id}', castleId), 'remaraks');
        }
        else if (castleBasic.state === CastleModel.enums.state.Suspended.name) {
            urlBuilder.addUrlAndParam(BasicContent.url.SUSPEND_CASTLE.replace('{id}', castleId), 'remaraks');
        }

        NaraAjax.putJSONs(urlBuilder.build(), function () {
            this.setBasic(castleBasic);

            let lang = MainComponent.lang;
            alert(CastleBasicModel.messages.completeModify[lang]);
        }.bind(this));
    }
    render() {
        //
        return (
            this.props.modifiable ?
                <BasicModifiableContent
                    basicInfo={this.props.castle.basic}
                    nameBook={this.props.castle.contact.nameBook}
                    emailBook={this.props.castle.contact.emailBook}
                    phoneBook={this.props.castle.contact.phoneBook}
                    modifiable={this.props.modifiable}
                    changeViewMode={this.props.changeViewMode}
                    setBasic={this.setBasic}
                    modifyBasic={this.requestModifyBasic}
                />
                :
                <BasicViewContent
                    basicInfo={this.props.castle.basic}
                    modifiable={this.props.modifiable}
                    changeModifiableMode={this.props.changeModifiableMode}
                />
        );
    }
}

BasicContent.propTypes = {
    //
    castleId: PropTypes.string.isRequired,
    castle: PropTypes.shape({
        basic: PropTypes.shape({
            castellan: PropTypes.object.isRequired
        }).isRequired,
        contact: PropTypes.shape({
            emailBook: PropTypes.shape({
                emails: PropTypes.array.isRequired
            }).isRequired,
            phoneBook: PropTypes.shape({
                phones: PropTypes.array.isRequired
            }).isRequired
        }).isRequired
    }).isRequired,
    modifiable: PropTypes.bool.isRequired,

    changeModifiableMode: PropTypes.func.isRequired,
    changeViewMode: PropTypes.func.isRequired,
    setCastle: PropTypes.func.isRequired
};

BasicContent.url = {
    //
    FIND_CASTLE:            `${Constant.PAV_CTX_API}/api/castles/{id}`,
    FIND_CASTELLAN:         `${Constant.PAV_CTX_API}/api/castellans/{id}`,
    MODIFY_NAME:            `${Constant.PAV_CTX_API}/api/castles/{id}/name`,
    MODIFY_LOCALE:          `${Constant.PAV_CTX_API}/api/castles/{id}/locale`,
    SUSPEND_CASTLE:         `${Constant.PAV_CTX_API}/api/castles/{id}/suspend`,
    REOPEN_CASTLE:          `${Constant.PAV_CTX_API}/api/castles/{id}/reopen`,
    MODIFY_PRIMARY_EMAIL:   `${Constant.PAV_CTX_API}/api/castellans/{id}/primary-email`,
    MODIFY_PRIMARY_PHONE:   `${Constant.PAV_CTX_API}/api/castellans/{id}/primary-phone`,
    FIND_NAME_BOOK:         `${Constant.PAV_CTX_API}/api/castellans/{id}/contacts/name-book`,
    FIND_EMAIL_BOOK:        `${Constant.PAV_CTX_API}/api/castellans/{id}/contacts/email-book`,
    FIND_PHONE_BOOK:        `${Constant.PAV_CTX_API}/api/castellans/{id}/contacts/phone-book`
};


class BasicViewContent extends Component {
    //
    constructor(props) {
        super(props);

        this.modifiableModeBtnClick = this.modifiableModeBtnClick.bind(this);
        this.removeBtnClick = this.removeBtnClick.bind(this);
    }
    // event
    modifiableModeBtnClick() {
        this.props.changeModifiableMode();
    }
    removeBtnClick() {
        let lang = MainComponent.lang;
        if (confirm(CastleBasicModel.messages.confirmRemove[lang])) {
            // TODO : Castle 삭제
        }
    }
    render() {
        //
        const ENUMS = CastleModel.enums,
            BUTTON_NAMES = CastleModel.buttons,
            ATTRS = CastleBasicModel.attrs,
            LANG = MainComponent.lang;

        let propBasicInfo = this.props.basicInfo,
            existsCastle = !NaraObject.isEmpty(propBasicInfo) && propBasicInfo[ATTRS.id.name];


        return (
            <form className="form-horizontal">
                <div className="form-group"><p>&nbsp;</p></div>
                <div className="form-group">
                    <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.id[LANG]}</label>
                    <div className="col-lg-5">
                        <p className="form-control-static">{propBasicInfo[ATTRS.id.name]}</p>
                    </div>
                </div>
                <div className="form-group">
                    <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.name[LANG]}</label>
                    <div className="col-lg-5">
                        <p className="form-control-static">{propBasicInfo[ATTRS.name.name]}</p>
                    </div>
                </div>
                <div className="form-group">
                    <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.locale[LANG]}</label>
                    <div className="col-lg-5">
                        <p className="form-control-static">{existsCastle ? ENUMS.locale[propBasicInfo[ATTRS.locale.name]][LANG] : null}</p>
                    </div>
                </div>
                <div className="form-group">
                    <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.state[LANG]}</label>
                    <div className="col-lg-5">
                        <p className="form-control-static">{existsCastle ? ENUMS.state[propBasicInfo[ATTRS.state.name]][LANG] : null}</p>
                    </div>
                </div>
                <div className="form-group">
                    <label
                        className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.castellan.primaryEmail[LANG]}</label>
                    <div className="col-lg-5">
                        <p className="form-control-static">{propBasicInfo.castellan[ATTRS.castellan.primaryEmail.name]}</p>
                    </div>
                </div>
                <div className="form-group">
                    <label
                        className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.castellan.primaryPhone[LANG]}</label>
                    <div className="col-lg-5">
                        <p className="form-control-static">{propBasicInfo.castellan[ATTRS.castellan.primaryPhone.name]}</p>
                    </div>
                </div>
                <div className="form-group">
                    <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.castellan.photo[LANG]}</label>
                    <div className="col-lg-5">
                        <p className="form-control-static">{propBasicInfo.castellan[ATTRS.castellan.photo.name]}</p>
                    </div>
                </div>
                <div className="form-group">
                    <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.buildTime[LANG]}</label>
                    <div className="col-lg-5">
                        <p className="form-control-static">{NaraDate.parseToString(propBasicInfo[ATTRS.buildTime.name])}</p>
                    </div>
                </div>

                <div className="btn-toolbar pull-right">
                    <button type="button" className="btn-group btn btn-default"
                            onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.modify[LANG]}</button>
                    <button type="button" className="btn-group btn btn-danger"
                            onClick={this.removeBtnClick}>{BUTTON_NAMES.remove[LANG]}</button>
                </div>
            </form>
        );
    }
}

BasicViewContent.propTypes = {
    //
    basicInfo: PropTypes.shape({
        castellan: PropTypes.object.isRequired
    }).isRequired,
    changeModifiableMode: PropTypes.func.isRequired
};


class BasicModifiableContent extends Component {
    //
    constructor(props) {
        super(props);

        this.state = {
            willModifyBasic: {
                castellan: {}
            }
        };

        this.nameChange = this.nameChange.bind(this);
        this.localeChange = this.localeChange.bind(this);
        this.stateChange = this.stateChange.bind(this);
        this.primaryEmailChange = this.primaryEmailChange.bind(this);
        this.primaryPhoneNumberChange = this.primaryPhoneNumberChange.bind(this);
        this.saveBtnClick = this.saveBtnClick.bind(this);
        this.cancelModificationBtnClick = this.cancelModificationBtnClick.bind(this);
        this.setWillModifyBasicState = this.setWillModifyBasicState.bind(this);
        this.setWillModifyCastellanState = this.setWillModifyCastellanState.bind(this);
    }
    // overriding
    componentDidMount() {
        let basicInfo = NaraObject.deepCopy(this.props.basicInfo);
        this.setState({willModifyBasic: basicInfo});
    }
    // event
    nameChange(event) {
        this.setWillModifyBasicState(CastleBasicModel.attrs.name.name, event.target.value);
    }
    localeChange(event) {
        this.setWillModifyBasicState(CastleBasicModel.attrs.locale.name, event.target.value);
    }
    stateChange(event) {
        this.setWillModifyBasicState(CastleBasicModel.attrs.state.name, event.target.value);
    }
    primaryEmailChange(event) {
        this.setWillModifyCastellanState(CastleBasicModel.attrs.castellan.primaryEmail.name, event.target.value);
    }
    primaryPhoneNumberChange(event) {
        this.setWillModifyCastellanState(CastleBasicModel.attrs.castellan.primaryPhone.name, event.target.value);
    }
    saveBtnClick() {
        this.props.modifyBasic(this.state.willModifyBasic);
    }
    cancelModificationBtnClick() {
        this.props.changeViewMode();
    }
    // custom
    setWillModifyBasicState(propertyName, value) {
        //
        let basic = this.state.willModifyBasic;

        basic[propertyName] = value;
        this.setState({willModifyBasic: basic});
    }
    setWillModifyCastellanState(propertyName, value) {
        //
        let basic = this.state.willModifyBasic;

        basic.castellan[propertyName] = value;
        this.setState({willModifyBasic: basic});
    }
    render() {
        //
        const ENUMS = CastleModel.enums,
            BUTTON_NAMES = CastleModel.buttons,
            ATTRS = CastleBasicModel.attrs,
            LANG = MainComponent.lang;

        let propBasicInfo = this.state.willModifyBasic;

        return (
            <div className="tab-content">
                <div className="tab-pane active">
                    <form className="form-horizontal">
                        <div className="form-group"><p>&nbsp;</p></div>
                        <div className="form-group">
                            <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.id[LANG]}</label>
                            <div className="col-lg-5">
                                <p className="form-control-static">{propBasicInfo[ATTRS.id.name]}</p>
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.name[LANG]}</label>
                            <div className="col-lg-5">
                                <select className="form-control" onChange={this.nameChange}
                                        value={propBasicInfo[ATTRS.name.name]}>
                                    <option value="">{ ATTRS.name[LANG] }</option>
                                    { this.props.nameBook.names.map( function (name, index) {

                                        return (
                                            <option key={index} value={name.displayName}>{name.displayName}</option>
                                        );
                                    })}
                                </select>
                                <input type="text" className="form-control" onChange={this.nameChange}
                                       value={propBasicInfo[ATTRS.name.name]}/>
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.locale[LANG]}</label>
                            <div className="col-lg-5">
                                <select className="form-control" onChange={this.localeChange}
                                        value={ propBasicInfo[ATTRS.locale.name] }>
                                    <option value="">{ ATTRS.locale[LANG] }</option>
                                    { Object.keys(ENUMS.locale).map( function (localeKey, index) {
                                        const LOCALE_ENUM = ENUMS.locale[localeKey];
                                        return (
                                            <option key={index} value={LOCALE_ENUM.name}>{LOCALE_ENUM[LANG]}</option>
                                        );
                                    })}
                                </select>
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="col-lg-3 col-lg-offset-1 control-label">
                                {ATTRS.state[LANG]}
                            </label>
                            <div className="col-lg-5">
                                <select className="form-control" onChange={this.stateChange}
                                        value={propBasicInfo[ATTRS.state.name]}>
                                    <option value="">{ ATTRS.state[LANG] }</option>
                                    { Object.keys(ENUMS.modifiableState).map( function (stateKey, index) {
                                        let STATE_ENUM = ENUMS.modifiableState[stateKey];

                                        return (
                                            <option key={index} value={STATE_ENUM.name}>{STATE_ENUM[LANG]}</option>
                                        );
                                    })}
                                </select>
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="col-lg-3 col-lg-offset-1 control-label">
                                {ATTRS.castellan.primaryEmail[LANG]}
                            </label>
                            <div className="col-lg-5">
                                <select className="form-control" onChange={this.primaryEmailChange}
                                        value={propBasicInfo.castellan[ATTRS.castellan.primaryEmail.name]}>
                                    <option value="">{ ATTRS.castellan.primaryEmail[LANG] }</option>
                                    { this.props.emailBook.emails.map( function (email, index) {

                                        return (
                                            <option key={index} value={email.email}>{email.email}</option>
                                        );
                                    })}
                                </select>
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="col-lg-3 col-lg-offset-1 control-label">
                                {ATTRS.castellan.primaryPhone[LANG]}
                            </label>
                            <div className="col-lg-5">
                                <select className="form-control" onChange={this.primaryPhoneNumberChange}
                                        value={propBasicInfo.castellan[ATTRS.castellan.primaryPhone.name]}>
                                    <option value="">{ ATTRS.castellan.primaryPhone[LANG] }</option>
                                    { this.props.phoneBook.phones.map( function (phone, index) {

                                        return (
                                            <option key={index} value={phone.phoneNumber}>{phone.phoneNumber}</option>
                                        );
                                    })}
                                </select>
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="col-lg-3 col-lg-offset-1 control-label">
                                {ATTRS.castellan.photo[LANG]}
                            </label>
                            <div className="col-lg-5">
                                <p className="form-control-static">{propBasicInfo.castellan[ATTRS.castellan.photo.name]}</p>
                            </div>
                        </div>
                        <div className="form-group">
                            <label className="col-lg-3 col-lg-offset-1 control-label">
                                {ATTRS.buildTime[LANG]}
                            </label>
                            <div className="col-lg-5">
                                <p className="form-control-static">{NaraDate.parseToString(propBasicInfo[ATTRS.buildTime.name])}</p>
                            </div>
                        </div>

                        <div className="btn-toolbar pull-right">
                            <button type="button" className="btn-group btn btn-primary" onClick={this.saveBtnClick}>
                                {BUTTON_NAMES.save[LANG]}
                            </button>
                            <button type="button" className="btn-group btn btn-default" onClick={this.cancelModificationBtnClick}>
                                {BUTTON_NAMES.cancel[LANG]}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

BasicModifiableContent.propTypes = {
    //
    basicInfo: PropTypes.shape({
        castellan: PropTypes.object.isRequired
    }).isRequired,
    nameBook: PropTypes.shape({
        names: PropTypes.array.isRequired
    }).isRequired,
    emailBook: PropTypes.shape({
        emails: PropTypes.array.isRequired
    }).isRequired,
    phoneBook: PropTypes.shape({
        phones: PropTypes.array.isRequired
    }).isRequired,

    changeViewMode: PropTypes.func.isRequired,
    setBasic: PropTypes.func.isRequired,
    modifyBasic: PropTypes.func.isRequired
};


export default BasicContent;