/**
 * Created by hkkang on 2016-04-12.
 */

castle.component.PhoneBook = castle.component.PhoneBook || {};


( function () {
    //
    'use strict';

    // Import component module
    const NaraAjax = NaraCommon.Ajax,
        NaraObject = NaraCommon.Object,
        Constant = castle.common.Const,
        CastleModel = castle.common.Model,
        MainComponent = castle.component.common.Main;


    // Define Content attributes name
    const CastlePhoneModel = {
        //
        attrs: {
            phoneNumber:    { name: 'phoneNumber',  KOR: '전체번호', USA: 'Phone number' },
            countryCode:    { name: 'countryCode',  KOR: '국가코드', USA: 'Country code' },
            areaCode:       { name: 'areaCode',     KOR: '지역코드', USA: 'Area code' },
            number:         { name: 'number',       KOR: '번호',     USA: 'Number' }
        },
        messages: {
            notRegisteredPhone: { KOR: '등록 된 Phone이 없습니다',      USA: 'Not registered the phone' },
            completeSave:       { KOR: 'PhoneBook이 저장되었습니다.',   USA: 'Save has been completed.' }
        }
    };


    // Define components
    let PhoneContent = React.createClass({
        //
        statics: {
            FIND_PHONE_BOOK_URL: Constant.PAV_CTX_API + '/api/castellans/{id}/contacts/phone-book',
            ATTACH_PHONE_BOOK_URL: Constant.PAV_CTX_API + '/api/castellans/{id}/contacts/phone-book'
        },
        propTypes: {
            castleId: React.PropTypes.string.isRequired,
            castle: React.PropTypes.shape({
                contact: React.PropTypes.shape({
                    phoneBook: React.PropTypes.shape({
                        phones: React.PropTypes.array.isRequired
                    }).isRequired
                }).isRequired
            }).isRequired,
            modifiable: React.PropTypes.bool.isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired,
            changeViewMode: React.PropTypes.func.isRequired,
            setCastle: React.PropTypes.func.isRequired
        },
        // overriding
        componentDidMount() {
            this.requestFindPhoneBook(this.props.castleId);
        },
        // custom
        setPhoneBook(phoneBook) {
            let castle = this.props.castle;

            castle.contact.phoneBook = phoneBook;
            this.props.setCastle(castle);
        },
        // request
        requestFindPhoneBook(castleId) {
            //
            NaraAjax
                .getJSON(PhoneContent.FIND_PHONE_BOOK_URL.replace('{id}', castleId))
                .done( function (phoneBookResult) {
                    this.setPhoneBook(phoneBookResult);
                }.bind(this));
        },
        requestSavePhoneBook(phoneBook) {
            //
            NaraAjax
                .postJSON(PhoneContent.ATTACH_PHONE_BOOK_URL.replace('{id}', this.props.castleId), phoneBook)
                .done( function () {
                    let lang = MainComponent.lang;

                    this.setPhoneBook(phoneBook);
                    alert(CastlePhoneModel.messages.completeSave[lang]);
                    this.props.changeViewMode();
                }.bind(this));
        },
        render() {
            //
            return (
                this.props.modifiable ?
                    <PhoneModifiableContent
                        phoneBook={this.props.castle.contact.phoneBook}
                        changeViewMode={this.props.changeViewMode}
                        savePhoneBook={this.requestSavePhoneBook}
                    />
                    :
                    <PhoneViewContent
                        phoneBook={this.props.castle.contact.phoneBook}
                        changeModifiableMode={this.props.changeModifiableMode}
                    />
            );
        }
    });


    let PhoneViewContent = React.createClass({
        //
        propTypes: {
            phoneBook: React.PropTypes.shape({
                phones: React.PropTypes.array.isRequired
            }).isRequired,
            changeModifiableMode: React.PropTypes.func.isRequired
        },
        // event
        modifiableModeBtnClick() {
            this.props.changeModifiableMode();
        },
        removeBtnClick() {

        },
        render() {
            //
            const BUTTON_NAMES = CastleModel.buttons,
                ATTRS = CastlePhoneModel.attrs,
                MESSAGES = CastlePhoneModel.messages,
                LANG = MainComponent.lang;

            let propPhoneBook = this.props.phoneBook,
                existsPhoneBook = propPhoneBook.phones.length > 0;

            return (
                <article>
                    <table className="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>{ATTRS.phoneNumber[LANG]}</th>
                            <th>{ATTRS.countryCode[LANG]}</th>
                            <th>{ATTRS.areaCode[LANG]}</th>
                            <th>{ATTRS.number[LANG]}</th>
                        </tr>
                        </thead>
                        <tbody>
                        { existsPhoneBook ?
                            propPhoneBook.phones.map( function (phone, index) {
                                return (
                                    <tr key={index}>
                                        <td>{phone[ATTRS.phoneNumber.name]}</td>
                                        <td>{phone[ATTRS.countryCode.name]}</td>
                                        <td>{phone[ATTRS.areaCode.name]}</td>
                                        <td>{phone[ATTRS.number.name]}</td>
                                    </tr>
                                )
                            })
                            :
                            <tr>
                                <td colSpan="4">{MESSAGES.notRegisteredPhone[LANG]}</td>
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
    });


    let PhoneModifiableContent = React.createClass({
        //
        propTypes: {
            phoneBook: React.PropTypes.shape({
                phones: React.PropTypes.array.isRequired
            }).isRequired,
            changeViewMode: React.PropTypes.func.isRequired,
            savePhoneBook: React.PropTypes.func.isRequired
        },
        // overriding
        getInitialState() {
            return {
                willModifyPhones: [],
                inputProgressPhones : [],
                rowsModifiable: []
            };
        },
        componentDidMount() {
            let phones = NaraObject.deepCopy(this.props.phoneBook.phones),
                rowsModifiable = [];

            rowsModifiable.length = phones.length;
            rowsModifiable.fill(false);

            this.setState({
                willModifyPhones: phones,
                inputProgressPhones: NaraObject.deepCopy(phones),
                rowsModifiable: rowsModifiable
            });
        },
        // event
        countryCodeChange(index, event) {
            this.setProgressPhoneState(index, CastlePhoneModel.attrs.countryCode.name, event.target.value);
        },
        areaCodeChange(index, event) {
            this.setProgressPhoneState(index, CastlePhoneModel.attrs.areaCode.name, event.target.value);
        },
        numberChange(index, event) {
            this.setProgressPhoneState(index, CastlePhoneModel.attrs.number.name, event.target.value);
        },
        completePhoneBtnClick(index) {
            let phones = NaraObject.deepCopy(this.state.willModifyPhones),
                completedPhone = this.state.inputProgressPhones[index];

            completedPhone.phoneNumber = completedPhone.countryCode + '-' + completedPhone.areaCode + '-' + completedPhone.number;
            phones[index] = NaraObject.deepCopy(completedPhone);

            this.setState({ willModifyPhones: phones });
            this.setRowModifiableState(index, false);
        },
        cancelPhoneBtnClick(index) {
            let phones = NaraObject.deepCopy(this.state.inputProgressPhones);
            phones[index] = NaraObject.deepCopy(this.state.willModifyPhones[index]);

            if (NaraObject.isEmpty(phones[index])) {
                phones.splice(index, 1);

                let rowsModifiable = this.state.rowsModifiable;
                rowsModifiable.splice(index, 1);

                this.setState({ rowsModifiable: rowsModifiable });
            }
            else {
                this.setRowModifiableState(index, false);
            }
            this.setState({ inputProgressPhones: phones });
        },
        modifyPhoneBtnClick(index) {
            this.setRowModifiableState(index, true);
        },
        removePhoneBtnClick(index) {
            let progressPhones = this.state.inputProgressPhones,
                willModifyPhones = this.state.willModifyPhones,
                rowsModifiable = this.state.rowsModifiable;

            progressPhones.splice(index, 1);
            willModifyPhones.splice(index, 1);
            rowsModifiable.splice(index, 1);


            this.setState({
                inputProgressPhones: progressPhones,
                willModifyPhones: willModifyPhones,
                rowsModifiable: rowsModifiable
            });
        },
        addPhoneBtnClick() {
            let phones = this.state.inputProgressPhones,
                rowsModifiable = this.state.rowsModifiable;

            phones.push({});
            rowsModifiable.push(true);

            this.setState({ inputProgressPhones: phones, rowsModifiable: rowsModifiable });
        },
        savePhoneBookBtnClick() {
            this.props.savePhoneBook({ phones: this.state.willModifyPhones });
        },
        cancelModificationBtnClick() {
            this.props.changeViewMode();
        },
        // custom
        setRowModifiableState(index, modifiable) {
            //
            let rowsModifiable = this.state.rowsModifiable;

            rowsModifiable[index] = modifiable;
            this.setState({ rowsModifiable: rowsModifiable });
        },
        setProgressPhoneState(index, propertyName, value) {
            //
            let phones = this.state.inputProgressPhones;

            phones[index][propertyName] = value;
            this.setState({ inputProgressPhones: phones });
        },
        render() {
            //
            const ATTRS = CastlePhoneModel.attrs,
                BUTTON_NAMES = CastleModel.buttons,
                MESSAGES = CastlePhoneModel.messages,
                LANG = MainComponent.lang;

            let propPhoneBook = this.props.phoneBook,
                existsPhoneBook = propPhoneBook.phones.length > 0;

            return (
                <article>
                    <table className="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th className="col-md-3">{ATTRS.phoneNumber[LANG]}</th>
                            <th className="col-md-2">{ATTRS.countryCode[LANG]}</th>
                            <th className="col-md-2">{ATTRS.areaCode[LANG]}</th>
                            <th className="col-md-3">{ATTRS.number[LANG]}</th>
                            <th className="col-md-2">&nbsp;</th>
                        </tr>
                        </thead>
                        <tbody>
                        { existsPhoneBook ?
                            this.state.inputProgressPhones.map( function (phone, index) {
                                return (
                                    this.state.rowsModifiable[index] === true ?
                                        <tr key={index}>
                                            <td>
                                                <input type="text" className="form-control" readOnly="readOnly"
                                                       value={phone[ATTRS.phoneNumber.name]}
                                                />
                                            </td>
                                            <td>
                                                <input type="text" className="form-control"
                                                       onChange={this.countryCodeChange.bind(this, index)}
                                                       value={phone[ATTRS.countryCode.name]}
                                                />
                                            </td>
                                            <td>
                                                <input type="text" className="form-control"
                                                       onChange={this.areaCodeChange.bind(this, index)}
                                                       value={phone[ATTRS.areaCode.name]}
                                                />
                                            </td>
                                            <td>
                                                <input type="text" className="form-control"
                                                       onChange={this.numberChange.bind(this, index)}
                                                       value={phone[ATTRS.number.name]}
                                                />
                                            </td>
                                            <td>
                                                <div className="btn-group btn-group-sm text-center">
                                                    <button type="button" className="btn btn-sm btn-primary"
                                                            onClick={this.completePhoneBtnClick.bind(this, index)}>
                                                        {BUTTON_NAMES.complete[LANG]}
                                                    </button>
                                                    <button type="button" className="btn btn-sm btn-default"
                                                            onClick={this.cancelPhoneBtnClick.bind(this, index)}>
                                                        {BUTTON_NAMES.cancel[LANG]}
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                        :
                                        <tr key={index}>
                                            <td>{phone[ATTRS.phoneNumber.name]}</td>
                                            <td>{phone[ATTRS.countryCode.name]}</td>
                                            <td>{phone[ATTRS.areaCode.name]}</td>
                                            <td>{phone[ATTRS.number.name]}</td>
                                            <td>
                                                <div className="btn-group btn-group-sm text-center">
                                                    <button type="button" className="btn btn-sm btn-default"
                                                            onClick={this.modifyPhoneBtnClick.bind(this, index)}>
                                                        {BUTTON_NAMES.modify[LANG]}
                                                    </button>
                                                    <button type="button" className="btn btn-sm btn-danger"
                                                            onClick={this.removePhoneBtnClick.bind(this, index)}>
                                                        {BUTTON_NAMES.remove[LANG]}
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                )
                            }.bind(this))
                            :
                            <tr>
                                <td colSpan="5">{MESSAGES.notRegisteredPhone[LANG]}</td>
                            </tr>
                        }
                        </tbody>
                    </table>
                    <div className="btn-block btn-toolbar">
                        <div className="pull-right">
                            <button type="button" className="btn btn-default btn-sm"
                                    onClick={this.addPhoneBtnClick}>
                                {BUTTON_NAMES.add[LANG]}
                            </button>
                        </div>
                    </div>
                    <div className="btn-toolbar pull-right">
                        <button type="button" className="btn-group btn btn-primary"
                                onClick={this.savePhoneBookBtnClick}>
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
    });

    castle.component.PhoneBook = PhoneContent;
})();