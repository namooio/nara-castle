/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.NameBook = Components.Castle.NameBook || {};

( function () {
    //
    'use strict';

    // Import component module
    let commonAjax = NaraCommon.Ajax,
        constant = CastleCommon.Const,
        mainComponent = Components.Common.Main,
        castleModel = Components.Castle.Model;


    // Define Content attributes name
    let castleNameModel = {
        attrs: {
            familyName: {name: 'familyName', KOR: '성', USA: 'Family name'},
            firstName: {name: 'firstName', KOR: '이름', USA: 'First name'},
            displayName: {name: 'displayName', KOR: '전체이름', USA: 'Display name'},
            middleName: {name: 'middleName', KOR: '중간이름', USA: 'Middle name'},
            langCode: {name: 'langCode', KOR: '언어', USA: 'Language'}
        },
        messages: {
            notRegisteredName: {KOR: '등록된 Name이 없습니다', USA: 'Not registered the name'},
            completeSave: {KOR: 'NameBook이 저장되었습니다.', USA: 'Save has been completed.'}
        }
    };

    // Define components
    let NameContent = React.createClass({
        //
        statics: {
            FIND_NAME_BOOK_URL: constant.CTX + '/api/castellans/{id}/contacts/name-book',
            ATTACH_NAME_BOOK_URL: constant.CTX + '/api/castellans/{id}/contacts/name-book'
        },
        propTypes: {
            castleId: React.PropTypes.string.isRequired,
            castle: React.PropTypes.shape({
                contact: React.PropTypes.shape({
                    nameBook: React.PropTypes.shape({
                        names: React.PropTypes.array.isRequired
                    }).isRequired
                }).isRequired
            }).isRequired,
            modifiable: React.PropTypes.bool.isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired,
            changeViewMode: React.PropTypes.func.isRequired,
            setCastle: React.PropTypes.func.isRequired
        },
        componentDidMount() {
            this.requestFindNameBook(this.props);
        },
        setNameBook(nameBook) {
            let castle = this.props.castle;

            castle.contact.nameBook = nameBook;
            this.props.setCastle(castle);
        },
        requestFindNameBook(props) {
            //
            commonAjax
                .getJSON(NameContent.FIND_NAME_BOOK_URL.replace('{id}', props.castleId))
                .done(function (nameBookResult) {
                    this.setNameBook(nameBookResult);
                }.bind(this));
        },
        requestAttachNameBook(nameBook) {
            //
            commonAjax
                .postJSON(NameContent.ATTACH_NAME_BOOK_URL.replace('{id}', this.props.castleId), nameBook)
                .done(function () {
                    this.setNameBook(nameBook);

                    let lang = mainComponent.lang;
                    alert(castleNameModel.messages.completeSave[lang]);
                }.bind(this));
        },
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
    });


    let NameViewContent = React.createClass({
        propTypes: {
            nameBook: React.PropTypes.shape({
                names: React.PropTypes.array.isRequired
            }).isRequired,
            changeModifiableMode: React.PropTypes.func.isRequired
        },
        modifiableModeBtnClick() {
            this.props.changeModifiableMode();
        },
        removeBtnClick() {
            // TODO: NameBook 삭제 기능
        },
        render() {
            //
            let ENUMS = castleModel.enums,
                BUTTON_NAMES = castleModel.buttons,
                ATTRS = castleNameModel.attrs,
                MESSAGES = castleNameModel.messages,
                lang = mainComponent.lang,
                existsNameBook = (this.props.nameBook && this.props.nameBook.names.length > 0) ? true : false;

            return (
                <article>
                    <table className="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th className="col-md-2">{ATTRS.familyName[lang]}</th>
                            <th className="col-md-2">{ATTRS.firstName[lang]}</th>
                            <th className="col-md-3">{ATTRS.displayName[lang]}</th>
                            <th className="col-md-3">{ATTRS.middleName[lang]}</th>
                            <th className="col-md-2">{ATTRS.langCode[lang]}</th>
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
                                        <td>{ENUMS.language[name[ATTRS.langCode.name]][lang]}</td>
                                    </tr>
                                )
                            })
                            :
                            <tr>
                                <td colSpan="5">{MESSAGES.notRegisteredName[lang]}</td>
                            </tr>
                        }
                        </tbody>
                    </table>
                    <div className="btn-toolbar pull-right">
                        <button type="button" className="btn-group btn btn-default"
                                onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.modify[lang]}</button>
                        <button type="button" className="btn-group btn btn-danger"
                                onClick={this.removeBtnClick}>{BUTTON_NAMES.remove[lang]}</button>
                    </div>
                </article>
            );
        }
    });

    let NameModifiableContent = React.createClass({
        propTypes: {
            nameBook: React.PropTypes.shape({
                names: React.PropTypes.array.isRequired
            }).isRequired,
            changeViewMode: React.PropTypes.func.isRequired,
            saveNameBook: React.PropTypes.func.isRequired
        },
        getInitialState() {
            return {
                willSaveNames: [],
                inputProgressNames: [],
                rowsModifiable: []
            };
        },
        componentDidMount() {
            let names = NaraCommon.Object.deepCopy(this.props.nameBook.names),
                rowsModifiable = [];

            rowsModifiable.length = names.length;
            rowsModifiable.fill(false);

            this.setState({
                willSaveNames: names,
                inputProgressNames: NaraCommon.Object.deepCopy(names),
                rowsModifiable: rowsModifiable
            });
        },
        addNameBtnClick() {
            let names = this.state.inputProgressNames,
                rowsModifiable = this.state.rowsModifiable;

            names.push({});
            rowsModifiable.push(true);

            this.setState({inputProgressNames: names, rowsModifiable: rowsModifiable});
        },
        modifyNameBtnClick(index) {
            this.setRowModifiableState(index, true);
        },
        removeNameBtnClick(index) {
            let progressNames = this.state.inputProgressNames,
                saveNames = this.state.willSaveNames,
                rowsModifiable = this.state.rowsModifiable;

            progressNames.splice(index, 1);
            saveNames.splice(index, 1);
            rowsModifiable.splice(index, 1);

            this.setState({
                inputProgressNames: progressNames,
                willSaveNames: saveNames,
                rowsModifiable: rowsModifiable
            });
        },
        completeNameBtnClick(index) {
            let names = NaraCommon.Object.deepCopy(this.state.willSaveNames);
            names[index] = NaraCommon.Object.deepCopy(this.state.inputProgressNames[index]);

            this.setState({willSaveNames: names});
            this.setRowModifiableState(index, false);
        },
        cancelNameBtnClick(index) {
            let names = NaraCommon.Object.deepCopy(this.state.inputProgressNames);
            names[index] = NaraCommon.Object.deepCopy(this.state.willSaveNames[index]);

            if (NaraCommon.Object.isEmpty(names[index])) {
                names.splice(index, 1);

                let rowsModifiable = this.state.rowsModifiable;
                rowsModifiable.splice(index, 1);

                this.setState({rowsModifiable: rowsModifiable});
            }
            else {
                this.setRowModifiableState(index, false);
            }
            this.setState({inputProgressNames: names});
        },
        familyNameChange(index, event) {
            this.setProgressNameState(index, castleNameModel.attrs.familyName.name, event.target.value);
        },
        firstNameChange(index, event) {
            this.setProgressNameState(index, castleNameModel.attrs.firstName.name, event.target.value);
        },
        displayNameChange(index, event) {
            this.setProgressNameState(index, castleNameModel.attrs.displayName.name, event.target.value);
        },
        middleNameChange(index, event) {
            this.setProgressNameState(index, castleNameModel.attrs.middleName.name, event.target.value);
        },
        langCodeChange(index, event) {
            this.setProgressNameState(index, castleNameModel.attrs.langCode.name, event.target.value);
        },
        saveNameBookBtnClick() {
            this.props.saveNameBook({names: this.state.willSaveNames});
        },
        cancelModificationBtnClick() {
            this.props.changeViewMode();
        },
        setRowModifiableState(index, modifiable) {
            //
            let rowsModifiable = this.state.rowsModifiable;
            rowsModifiable[index] = modifiable;

            this.setState({rowsModifiable: rowsModifiable});
        },
        setProgressNameState(index, propertyName, value) {
            //
            let names = this.state.inputProgressNames;
            names[index][propertyName] = value;

            this.setState({inputProgressNames: names});
        },
        render() {
            //
            let BUTTON_NAMES = castleModel.buttons,
                ENUMS = castleModel.enums,
                ATTRS = castleNameModel.attrs,
                MESSAGES = castleNameModel.messages,
                lang = mainComponent.lang,
                existsNameBook = this.state.willSaveNames.length > 0;


            return (
                <article>
                    <table className="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th className="col-md-2">{ATTRS.familyName[lang]}</th>
                            <th className="col-md-2">{ATTRS.firstName[lang]}</th>
                            <th className="col-md-3">{ATTRS.displayName[lang]}</th>
                            <th className="col-md-2">{ATTRS.middleName[lang]}</th>
                            <th className="col-md-1">{ATTRS.langCode[lang]}</th>
                            <th className="col-md-2"></th>
                        </tr>
                        </thead>
                        <tbody>
                        { existsNameBook ?
                            this.state.inputProgressNames.map(function (name, index) {

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
                                                    <option>{ATTRS.langCode[lang]}</option>
                                                    {Object.keys(ENUMS.language).map(function (languageKey, index) {
                                                        let LANG_ENUM = ENUMS.language[languageKey];

                                                        return (
                                                            <option key={index}
                                                                    value={LANG_ENUM.name}>{LANG_ENUM[lang]}</option>
                                                        );
                                                    })}
                                                </select>
                                            </td>
                                            <td>
                                                <div className="btn-group btn-group-sm text-center">
                                                    <button type="button" className="btn btn-sm btn-primary"
                                                            onClick={this.completeNameBtnClick.bind(this, index)}>{BUTTON_NAMES.complete[lang]}</button>
                                                    <button type="button" className="btn btn-sm btn-default"
                                                            onClick={this.cancelNameBtnClick.bind(this, index)}>{BUTTON_NAMES.cancel[lang]}</button>
                                                </div>
                                            </td>
                                        </tr>
                                        :
                                        <tr key={index}>
                                            <td><p>{name[ATTRS.familyName.name]}</p></td>
                                            <td>{name[ATTRS.firstName.name]}</td>
                                            <td>{name[ATTRS.displayName.name]}</td>
                                            <td>{name[ATTRS.middleName.name]}</td>
                                            <td>{ENUMS.language[name[ATTRS.langCode.name]][lang]}</td>
                                            <td>
                                                <div className="btn-group btn-group-sm text-center">
                                                    <button type="button" className="btn btn-sm btn-default"
                                                            onClick={this.modifyNameBtnClick.bind(this, index)}>{BUTTON_NAMES.modify[lang]}</button>
                                                    <button type="button" className="btn btn-sm btn-danger"
                                                            onClick={this.removeNameBtnClick.bind(this, index)}>{BUTTON_NAMES.remove[lang]}</button>
                                                </div>
                                            </td>
                                        </tr>
                                );
                            }.bind(this))
                            :
                            <tr>
                                <td colSpan="5">{MESSAGES.notRegisteredName[lang]}</td>
                            </tr>
                        }
                        </tbody>
                    </table>
                    <div className="btn-block btn-toolbar">
                        <div className="pull-right">
                            <button type="button" className="btn btn-default btn-sm"
                                    onClick={this.addNameBtnClick}>{BUTTON_NAMES.add[lang]}</button>
                        </div>
                    </div>
                    <div className="row"><p></p></div>
                    <div className="btn-toolbar pull-right">
                        <button type="button" className="btn btn-primary"
                                onClick={this.saveNameBookBtnClick}>{BUTTON_NAMES.save[lang]}</button>
                        <button type="button" className="btn btn-default"
                                onClick={this.cancelModificationBtnClick}>{BUTTON_NAMES.cancel[lang]}</button>
                    </div>
                </article>
            );
        }
    });


    Components.Castle.NameBook = NameContent;
})();