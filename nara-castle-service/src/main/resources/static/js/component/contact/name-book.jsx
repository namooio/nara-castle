/**
 * Created by hkkang on 2016-04-12.
 */

import React, { Component, PropTypes} from 'react';
import { Ajax as NaraAjax, Object as NaraObject } from 'app/lib/nara-common';
import { Constant } from 'app/common/castle-common';
import CastleModel from 'app/common/castle-model';
import MainComponent from 'app/component/common/main.jsx';


'use strict';

// Define Content attributes name
const CastleNameModel = {
    //
    attrs: {
        familyName:     { name: 'familyName',   KOR: '성',       USA: 'Family name' },
        firstName:      { name: 'firstName',    KOR: '이름',     USA: 'First name' },
        displayName:    { name: 'displayName',  KOR: '전체이름', USA: 'Display name' },
        middleName:     { name: 'middleName',   KOR: '중간이름', USA: 'Middle name' },
        langCode:       { name: 'langCode',     KOR: '언어',      USA: 'Language' }
    },
    messages: {
        notRegisteredName:  { KOR: '등록된 Name이 없습니다',        USA: 'Not registered the name' },
        completeSave:       { KOR: 'NameBook이 저장되었습니다.',    USA: 'Save has been completed.' }
    }
};


// Define components
class NameContent extends Component {
    //
    constructor(props) {
        super(props);

        this.setNameBook = this.setNameBook.bind(this);
        this.requestFindNameBook = this.requestFindNameBook.bind(this);
        this.requestAttachNameBook = this.requestAttachNameBook.bind(this);
    }
    // overriding
    componentDidMount() {
        this.requestFindNameBook(this.props);
    }
    // custom
    setNameBook(nameBook) {
        let castle = this.props.castle;

        castle.contact.nameBook = nameBook;
        this.props.setCastle(castle);
    }
    // request
    requestFindNameBook(props) {
        //
        NaraAjax
            .getJSON(NameContent.url.FIND_NAME_BOOK.replace('{id}', props.castleId))
            .done(function (nameBookResult) {
                this.setNameBook(nameBookResult);
            }.bind(this));
    }
    requestAttachNameBook(nameBook) {
        //
        NaraAjax
            .postJSON(NameContent.url.ATTACH_NAME_BOOK.replace('{id}', this.props.castleId), nameBook)
            .done(function () {
                this.setNameBook(nameBook);

                let lang = MainComponent.lang;
                alert(CastleNameModel.messages.completeSave[lang]);
                this.props.changeViewMode();
            }.bind(this));
    }
    render () {
        //
        return (
            this.props.modifiable ?
                <NameModifiableContent
                    nameBook={this.props.castle.contact.nameBook}
                    changeViewMode={this.props.changeViewMode}
                    saveNameBook={this.requestAttachNameBook}
                />
                :
                <NameViewContent
                    nameBook={this.props.castle.contact.nameBook}
                    changeModifiableMode={this.props.changeModifiableMode}
                />
        );
    }
}

NameContent.url = {
    //
    FIND_NAME_BOOK: `${Constant.PAV_CTX_API}/front/castellans/{id}/contacts/name-book`,
    ATTACH_NAME_BOOK: `${Constant.PAV_CTX_API}/front/castellans/{id}/contacts/name-book`
};
NameContent.propTypes = {
    //
    castleId: PropTypes.string.isRequired,
    castle: PropTypes.shape({
        contact: PropTypes.shape({
            nameBook: PropTypes.shape({
                names: PropTypes.array.isRequired
            }).isRequired
        }).isRequired
    }).isRequired,
    modifiable: PropTypes.bool.isRequired,

    changeModifiableMode: PropTypes.func.isRequired,
    changeViewMode: PropTypes.func.isRequired,
    setCastle: PropTypes.func.isRequired
};


class NameViewContent extends Component {
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
        // TODO: NameBook 삭제 기능
    }
    render() {
        //
        const ENUMS = CastleModel.enums,
            BUTTON_NAMES = CastleModel.buttons,
            ATTRS = CastleNameModel.attrs,
            MESSAGES = CastleNameModel.messages,
            LANG = MainComponent.lang;

        let existsNameBook = (this.props.nameBook && this.props.nameBook.names.length > 0) ? true : false;

        return (
            <article>
                <table className="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th className="col-md-2">{ATTRS.familyName[LANG]}</th>
                        <th className="col-md-2">{ATTRS.firstName[LANG]}</th>
                        <th className="col-md-3">{ATTRS.displayName[LANG]}</th>
                        <th className="col-md-3">{ATTRS.middleName[LANG]}</th>
                        <th className="col-md-2">{ATTRS.langCode[LANG]}</th>
                    </tr>
                    </thead>
                    <tbody>
                    { existsNameBook ?
                        this.props.nameBook.names.map(function (name, index) {
                            return (
                                <tr key={index}>
                                    <td>{name[ATTRS.familyName.name]}</td>
                                    <td>{name[ATTRS.firstName.name]}</td>
                                    <td>{name[ATTRS.displayName.name]}</td>
                                    <td>{name[ATTRS.middleName.name]}</td>
                                    <td>{ENUMS.language[name[ATTRS.langCode.name]][LANG]}</td>
                                </tr>
                            )
                        })
                        :
                        <tr>
                            <td colSpan="5">{MESSAGES.notRegisteredName[LANG]}</td>
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

NameViewContent.propTypes = {
    //
    nameBook: PropTypes.shape({
        names: PropTypes.array.isRequired
    }).isRequired,
    changeModifiableMode: PropTypes.func.isRequired
};


class NameModifiableContent extends Component {
    //
    constructor(props) {
        super(props);

        this.state = {
            willSaveNames: [],
            inputProgressNames: [],
            rowsModifiable: []
        };

        this.familyNameChange = this.familyNameChange.bind(this);
        this.firstNameChange = this.firstNameChange.bind(this);
        this.displayNameChange = this.displayNameChange.bind(this);
        this.middleNameChange = this.middleNameChange.bind(this);
        this.langCodeChange = this.langCodeChange.bind(this);
        this.addNameBtnClick = this.addNameBtnClick.bind(this);
        this.modifyNameBtnClick = this.modifyNameBtnClick.bind(this);
        this.removeNameBtnClick = this.removeNameBtnClick.bind(this);
        this.completeNameBtnClick = this.completeNameBtnClick.bind(this);

        this.cancelNameBtnClick = this.cancelNameBtnClick.bind(this);
        this.saveNameBookBtnClick = this.saveNameBookBtnClick.bind(this);
        this.cancelModificationBtnClick = this.cancelModificationBtnClick.bind(this);
        this.setRowModifiableState = this.setRowModifiableState.bind(this);
        this.setProgressNameState = this.setProgressNameState.bind(this);
    }
    // overriding
    componentDidMount() {
        let names = NaraObject.deepCopy(this.props.nameBook.names),
            rowsModifiable = [];

        rowsModifiable.length = names.length;
        rowsModifiable.fill(false);

        this.setState({
            willSaveNames: names,
            inputProgressNames: NaraObject.deepCopy(names),
            rowsModifiable: rowsModifiable
        });
    }
    // event
    familyNameChange(index, event) {
        this.setProgressNameState(index, CastleNameModel.attrs.familyName.name, event.target.value);
    }
    firstNameChange(index, event) {
        this.setProgressNameState(index, CastleNameModel.attrs.firstName.name, event.target.value);
    }
    displayNameChange(index, event) {
        this.setProgressNameState(index, CastleNameModel.attrs.displayName.name, event.target.value);
    }
    middleNameChange(index, event) {
        this.setProgressNameState(index, CastleNameModel.attrs.middleName.name, event.target.value);
    }
    langCodeChange(index, event) {
        this.setProgressNameState(index, CastleNameModel.attrs.langCode.name, event.target.value);
    }
    addNameBtnClick() {
        let names = this.state.inputProgressNames,
            rowsModifiable = this.state.rowsModifiable;

        names.push({});
        rowsModifiable.push(true);

        this.setState({ inputProgressNames: names, rowsModifiable: rowsModifiable });
    }
    modifyNameBtnClick(index) {
        this.setRowModifiableState(index, true);
    }
    removeNameBtnClick(index) {
        let progressNames = this.state.inputProgressNames,
            willSaveNames = this.state.willSaveNames,
            rowsModifiable = this.state.rowsModifiable;

        progressNames.splice(index, 1);
        willSaveNames.splice(index, 1);
        rowsModifiable.splice(index, 1);

        this.setState({
            inputProgressNames: progressNames,
            willSaveNames: willSaveNames,
            rowsModifiable: rowsModifiable
        });
    }
    completeNameBtnClick(index) {
        let names = NaraObject.deepCopy(this.state.willSaveNames);
        names[index] = NaraObject.deepCopy(this.state.inputProgressNames[index]);

        this.setState({ willSaveNames: names });
        this.setRowModifiableState(index, false);
    }
    cancelNameBtnClick(index) {
        let names = NaraObject.deepCopy(this.state.inputProgressNames);
        names[index] = NaraObject.deepCopy(this.state.willSaveNames[index]);

        if (NaraObject.isEmpty(names[index])) {
            names.splice(index, 1);

            let rowsModifiable = this.state.rowsModifiable;
            rowsModifiable.splice(index, 1);

            this.setState({ rowsModifiable: rowsModifiable });
        }
        else {
            this.setRowModifiableState(index, false);
        }
        this.setState({ inputProgressNames: names });
    }
    saveNameBookBtnClick() {
        this.props.saveNameBook({ names: this.state.willSaveNames });
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
    setProgressNameState(index, propertyName, value) {
        //
        let names = this.state.inputProgressNames;

        names[index][propertyName] = value;
        this.setState({ inputProgressNames: names });
    }
    render() {
        //
        const BUTTON_NAMES = CastleModel.buttons,
            ENUMS = CastleModel.enums,
            ATTRS = CastleNameModel.attrs,
            MESSAGES = CastleNameModel.messages,
            LANG = MainComponent.lang;

        let existsNameBook = this.state.willSaveNames.length > 0;


        return (
            <article>
                <table className="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th className="col-md-2">{ATTRS.familyName[LANG]}</th>
                        <th className="col-md-2">{ATTRS.firstName[LANG]}</th>
                        <th className="col-md-3">{ATTRS.displayName[LANG]}</th>
                        <th className="col-md-2">{ATTRS.middleName[LANG]}</th>
                        <th className="col-md-1">{ATTRS.langCode[LANG]}</th>
                        <th className="col-md-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    { existsNameBook ?
                        this.state.inputProgressNames.map( function (name, index) {
                            return (
                                this.state.rowsModifiable[index] === true ?
                                    <tr key={index}>
                                        <td><input type="text" className="form-control"
                                                   onChange={this.familyNameChange.bind(this, index)}
                                                   value={name[ATTRS.familyName.name]}/></td>
                                        <td><input type="text" className="form-control"
                                                   onChange={this.firstNameChange.bind(this, index)}
                                                   value={name[ATTRS.firstName.name]}/></td>
                                        <td><input type="text" className="form-control"
                                                   onChange={this.displayNameChange.bind(this, index)}
                                                   value={name[ATTRS.displayName.name]}/></td>
                                        <td><input type="text" className="form-control"
                                                   onChange={this.middleNameChange.bind(this, index)}
                                                   value={name[ATTRS.middleName.name]}/></td>
                                        <td>
                                            <select className="form-control"
                                                    onChange={this.langCodeChange.bind(this, index)}
                                                    value={name[ATTRS.langCode.name]}>
                                                <option>{ATTRS.langCode[LANG]}</option>
                                                {Object.keys(ENUMS.language).map( function (languageKey, index) {
                                                    let LANG_ENUM = ENUMS.language[languageKey];

                                                    return (
                                                        <option key={index} value={LANG_ENUM.name}>{LANG_ENUM[LANG]}</option>
                                                    );
                                                })}
                                            </select>
                                        </td>
                                        <td>
                                            <div className="btn-group btn-group-sm text-center">
                                                <button type="button" className="btn btn-sm btn-primary"
                                                        onClick={this.completeNameBtnClick.bind(this, index)}>{BUTTON_NAMES.complete[LANG]}</button>
                                                <button type="button" className="btn btn-sm btn-default"
                                                        onClick={this.cancelNameBtnClick.bind(this, index)}>{BUTTON_NAMES.cancel[LANG]}</button>
                                            </div>
                                        </td>
                                    </tr>
                                    :
                                    <tr key={index}>
                                        <td><p>{name[ATTRS.familyName.name]}</p></td>
                                        <td>{name[ATTRS.firstName.name]}</td>
                                        <td>{name[ATTRS.displayName.name]}</td>
                                        <td>{name[ATTRS.middleName.name]}</td>
                                        <td>{ENUMS.language[name[ATTRS.langCode.name]][LANG]}</td>
                                        <td>
                                            <div className="btn-group btn-group-sm text-center">
                                                <button type="button" className="btn btn-sm btn-default"
                                                        onClick={this.modifyNameBtnClick.bind(this, index)}>{BUTTON_NAMES.modify[LANG]}</button>
                                                <button type="button" className="btn btn-sm btn-danger"
                                                        onClick={this.removeNameBtnClick.bind(this, index)}>{BUTTON_NAMES.remove[LANG]}</button>
                                            </div>
                                        </td>
                                    </tr>
                            );
                        }.bind(this))
                        :
                        <tr>
                            <td colSpan="5">{MESSAGES.notRegisteredName[LANG]}</td>
                        </tr>
                    }
                    </tbody>
                </table>
                <div className="btn-block btn-toolbar">
                    <div className="pull-right">
                        <button type="button" className="btn btn-default btn-sm"
                                onClick={this.addNameBtnClick}>{BUTTON_NAMES.add[LANG]}</button>
                    </div>
                </div>
                <div className="row"><p></p></div>
                <div className="btn-toolbar pull-right">
                    <button type="button" className="btn btn-primary"
                            onClick={this.saveNameBookBtnClick}>{BUTTON_NAMES.save[LANG]}</button>
                    <button type="button" className="btn btn-default"
                            onClick={this.cancelModificationBtnClick}>{BUTTON_NAMES.cancel[LANG]}</button>
                </div>
            </article>
        );
    }
}

NameModifiableContent.propTypes = {
    //
    nameBook: PropTypes.shape({
        names: PropTypes.array.isRequired
    }).isRequired,
    changeViewMode: PropTypes.func.isRequired,
    saveNameBook: PropTypes.func.isRequired
};


export default NameContent;