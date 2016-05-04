/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.NameBook = Components.Castle.NameBook || { };

( function () {
    //
    'use strict';

    // Import component module
    var commonAjax = NaraCommon.Ajax,
        constant = CastleCommon.Const,
        mainComponent = Components.Main,
        castleModel = Components.Castle.Model;


    // Define Content attributes name
    var castleNameModel = {
        attrs: {
            familyName:     { name: 'familyName',   KOR: '성',       USA: 'Family name' },
            firstName:      { name: 'firstName',    KOR: '이름',     USA: 'First name' },
            displayName:    { name: 'displayName',  KOR: '전체이름', USA: 'Display name' },
            middleName:     { name: 'middleName',   KOR: '중간이름', USA: 'Middle name' },
            langCode:       { name: 'langCode',     KOR: '언어',     USA: 'Language' }
        },
        messages: {
            notRegisteredName:  { KOR: '등록된 Name이 없습니다',        USA: 'Not registered the name' },
            completeSave:       { KOR: 'NameBook이 저장되었습니다.',    USA: 'Save has been completed.' }
        }
    };

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
            //
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


    var NameViewContent = React.createClass({
        propTypes: {
            nameBook: React.PropTypes.shape({
                names: React.PropTypes.array.isRequired
            }).isRequired,
            changeModifiableMode: React.PropTypes.func.isRequired
        },
        modifiableModeBtnClick: function () {
            this.props.changeModifiableMode();
        },
        removeBtnClick: function () {

        },
        render: function () {
            var ENUMS = castleModel.enums,
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
                                this.props.nameBook.names.map( function (name, index) {
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
                                <tr><td colSpan="5">{MESSAGES.notRegisteredName[lang]}</td></tr>
                            }
                        </tbody>
                    </table>
                    <div className="btn-toolbar pull-right">
                        <button type="button" className="btn-group btn btn-default" onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.modify[lang]}</button>
                        <button type="button" className="btn-group btn btn-danger" onClick={this.removeBtnClick}>{BUTTON_NAMES.remove[lang]}</button>
                    </div>
                </article>
            );
        }
    });

    var NameModifiableContent = React.createClass({
        propTypes: {
            nameBook: React.PropTypes.shape({
                names: React.PropTypes.array.isRequired
            }).isRequired,
            changeViewMode: React.PropTypes.func.isRequired,
            saveNameBook: React.PropTypes.func.isRequired
        },
        getInitialState: function () {
            return {
                willSaveNames: [],
                inputProgressNames: [],
                rowsModifiable: []
            };
        },
        componentDidMount: function () {
            var names,
                rowsModifiable = [];

            names = NaraCommon.Object.deepCopy(this.props.nameBook.names);
            rowsModifiable.length = names.length;
            rowsModifiable.fill(false);

            this.setState({ willSaveNames: names, inputProgressNames: NaraCommon.Object.deepCopy(names), rowsModifiable: rowsModifiable });
        },
        addNameBtnClick: function () {
            var names = this.state.inputProgressNames,
                rowsModifiable = this.state.rowsModifiable;

            names.push({});
            rowsModifiable.push(true);

            this.setState({ inputProgressNames: names, rowsModifiable: rowsModifiable });
        },
        modifyNameBtnClick: function (index) {
            this.updateRowModifiableState(index, true);
        },
        removeNameBtnClick: function (index) {
            var progressNames = this.state.inputProgressNames,
                saveNames = this.state.willSaveNames,
                rowsModifiable = this.state.rowsModifiable;

            progressNames.splice(index, 1);
            saveNames.splice(index, 1);
            rowsModifiable.splice(index, 1);

            this.setState({ inputProgressNames: progressNames , willSaveNames: saveNames, rowsModifiable: rowsModifiable});
        },
        completeNameBtnClick: function (index) {
            var names = NaraCommon.Object.deepCopy(this.state.willSaveNames);
            names[index] = NaraCommon.Object.deepCopy(this.state.inputProgressNames[index]);

            this.setState({ willSaveNames: names });
            this.updateRowModifiableState(index, false);
        },
        cancelNameBtnClick: function (index) {
            var names = NaraCommon.Object.deepCopy(this.state.inputProgressNames);
            names[index] = NaraCommon.Object.deepCopy(this.state.willSaveNames[index]);

            if (NaraCommon.Object.isEmpty(names[index])) {
                names.splice(index, 1);

                var rowsModifiable = this.state.rowsModifiable;
                rowsModifiable.splice(index, 1);

                this.setState({ rowsModifiable: rowsModifiable });
            }
            else {
                this.updateRowModifiableState(index, false);
            }
            this.setState({ inputProgressNames: names });
        },
        familyNameChange: function (index, event) {
            this.updateProgressNameState(index, castleNameModel.attrs.familyName.name, event.target.value);
        },
        firstNameChange: function (index, event) {
            this.updateProgressNameState(index, castleNameModel.attrs.firstName.name, event.target.value);
        },
        displayNameChange: function (index, event) {
            this.updateProgressNameState(index, castleNameModel.attrs.displayName.name, event.target.value);
        },
        middleNameChange: function (index, event) {
            this.updateProgressNameState(index, castleNameModel.attrs.middleName.name, event.target.value);
        },
        langCodeChange: function (index, event) {
            console.log(event.target.value);
            this.updateProgressNameState(index, castleNameModel.attrs.langCode.name, event.target.value);
        },
        saveNameBookBtnClick: function () {
            this.props.saveNameBook(this.state.willSaveNames);
        },
        cancelModificationBtnClick: function () {
            this.props.changeViewMode();
        },
        updateRowModifiableState: function (index, modifiable) {
            var rowsModifiable = this.state.rowsModifiable;
            rowsModifiable[index] = modifiable;

            this.setState({ rowsModifiable: rowsModifiable });
        },
        updateProgressNameState: function (index, propertyName, value) {
            var names = this.state.inputProgressNames;
            names[index][propertyName] = value;

            this.setState({ inputProgressNames: names });
        },
        render: function () {
            var BUTTON_NAMES = castleModel.buttons,
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
                            this.state.inputProgressNames.map( function (name, index) {

                                return (
                                    this.state.rowsModifiable[index] === true ?
                                        <tr key={index}>
                                            <td><input type="text" className="form-control" onChange={this.familyNameChange.bind(this, index)} value={name[ATTRS.familyName.name]}/></td>
                                            <td><input type="text" className="form-control" onChange={this.firstNameChange.bind(this, index)} value={name[ATTRS.firstName.name]}/></td>
                                            <td><input type="text" className="form-control" onChange={this.displayNameChange.bind(this, index)} value={name[ATTRS.displayName.name]}/></td>
                                            <td><input type="text" className="form-control" onChange={this.middleNameChange.bind(this, index)} value={name[ATTRS.middleName.name]}/></td>
                                            <td>
                                                <select onChange={this.langCodeChange.bind(this, index)} value={name[ATTRS.langCode.name]}>
                                                    <option>{ATTRS.langCode[lang]}</option>
                                                    {Object.keys(ENUMS.language).map( function (language, index) {
                                                    return (
                                                        <option key={index} value={ENUMS.language[language].name}>{ENUMS.language[language][lang]}</option>
                                                    );
                                                })}
                                                </select>
                                            </td>
                                            <td>
                                                <div className="btn-group btn-group-sm text-center">
                                                    <button type="button" className="btn btn-sm btn-primary" onClick={this.completeNameBtnClick.bind(this, index)} >{BUTTON_NAMES.complete[lang]}</button>
                                                    <button type="button" className="btn btn-sm btn-default" onClick={this.cancelNameBtnClick.bind(this, index)} >{BUTTON_NAMES.cancel[lang]}</button>
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
                                                    <button type="button" className="btn btn-sm btn-default" onClick={this.modifyNameBtnClick.bind(this, index)} >{BUTTON_NAMES.modify[lang]}</button>
                                                    <button type="button" className="btn btn-sm btn-danger" onClick={this.removeNameBtnClick.bind(this, index)} >{BUTTON_NAMES.remove[lang]}</button>
                                                </div>
                                            </td>
                                        </tr>
                                );
                            }.bind(this))
                            :
                            <tr><td colSpan="5">{MESSAGES.notRegisteredName[lang]}</td></tr>
                        }
                        </tbody>
                    </table>
                    <div className="btn-block btn-toolbar">
                        <div className="pull-right">
                            <button type="button" className="btn btn-default btn-sm" onClick={this.addNameBtnClick}>{BUTTON_NAMES.add[lang]}</button>
                        </div>
                    </div>
                    <div className="row"><p></p></div>
                    <div className="btn-toolbar pull-right">
                        <button type="button" className="btn btn-primary" onClick={this.saveNameBookBtnClick}>{BUTTON_NAMES.save[lang]}</button>
                        <button type="button" className="btn btn-default" onClick={this.cancelModificationBtnClick}>{BUTTON_NAMES.cancel[lang]}</button>
                    </div>
                </article>
            );
        }
    });


    Components.Castle.NameBook = CastleDetailPage;
})();