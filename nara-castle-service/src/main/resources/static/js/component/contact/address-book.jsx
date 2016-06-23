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
const CastleAddressModel = {
    //
    attrs: {
        title:          { name: 'title',            KOR: '주소명',   USA: 'Title' },
        langCode:       { name: 'langCode',         KOR: '언어코드', USA: 'Language code' },
        style:          { name: 'style',            KOR: '유형',     USA: 'Style' },
        country:        { name: 'country',          KOR: '국가',     USA: 'Coutnry' },
        zipCode:        { name: 'zipCode',          KOR: '우편번호', USA: 'Zip code' },
        state:          { name: 'state',            KOR: '지역',     USA: 'State' },
        city:           { name: 'city',             KOR: '시',       USA: 'City' },
        addressPartOne: { name: 'addressPartOne',   KOR: '주소1',    USA: 'Address part1' },
        addressPartTwo: { name: 'addressPartTwo',   KOR: '주소2',    USA: 'Address part2' },
        phoneNumber:    { name: 'phoneNumber',      KOR: '전화번호', USA: 'Phone number' }
    },
    messages: {
        notRegisteredAddress:   { KOR: '등록 된 주소가 없습니다',  USA: 'Not registered the address' },
        completeSave:           { KOR: '주소가 저장되었습니다.',   USA: 'Save has been completed.' }
    }
};


// Define components
class AddressContent extends Component {
    //
    constructor(props) {
        //
        super(props);

        this.setAddressBook = this.setAddressBook.bind(this);
        this.requestFindAddressBook = this.requestFindAddressBook.bind(this);
        this.requestSaveAddressBook = this.requestSaveAddressBook.bind(this);
    }
    // overriding
    componentDidMount() {
        this.requestFindAddressBook(this.props.castleId);
    }
    // custom
    setAddressBook(addressBook) {
        let castle = this.props.castle;

        castle.contact.addressBook = addressBook;
        this.props.setCastle(castle);
    }
    // request
    requestFindAddressBook(castleId) {
        //
        NaraAjax
            .getJSON(AddressContent.url.FIND_ADDRESS_BOOK.replace('{id}', castleId))
            .done( function (addressBookResult) {
                this.setAddressBook(addressBookResult);
            }.bind(this));
    }
    requestSaveAddressBook(addressBook) {
        //
        NaraAjax
            .postJSON(AddressContent.url.ATTACH_PHONE_BOOK.replace('{id}', this.props.castleId), addressBook)
            .done( function () {
                const lang = MainComponent.lang;

                this.setAddressBook(addressBook);
                alert(CastleAddressModel.messages.completeSave[lang]);
                this.props.changeViewMode();
            }.bind(this));
    }
    render() {
        //
        return (
            this.props.modifiable ?
                <AddressModifiableContent
                    addressBook={this.props.castle.contact.addressBook}
                    changeViewMode={this.props.changeViewMode}
                    saveAddressBook={this.requestSaveAddressBook}
                />
                :
                <AddressViewContent
                    addressBook={this.props.castle.contact.addressBook}
                    changeModifiableMode={this.props.changeModifiableMode}
                />
        );
    }
}

AddressContent.propTypes = {
    //
    castleId: PropTypes.string,
    castle: PropTypes.shape({
        contact: PropTypes.shape({
            addressBook: PropTypes.shape({
                addresses: PropTypes.array
            })
        })
    }),
    modifiable: PropTypes.bool,

    changeModifiableMode: PropTypes.func,
    changeViewMode: PropTypes.func,
    setCastle: PropTypes.func
};

AddressContent.url = {
    //
    FIND_ADDRESS_BOOK: `${Constant.PAV_CTX.api}/castellans/{id}/contacts/address-book`,
    ATTACH_PHONE_BOOK: `${Constant.PAV_CTX.api}/castellans/{id}/contacts/address-book`
};


class AddressViewContent extends Component {
    //
    constructor(props) {
        //
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
            ATTRS = CastleAddressModel.attrs,
            MESSAGES = CastleAddressModel.messages,
            LANG = MainComponent.lang;

        let existsAddressBook = this.props.addressBook.addresses.length > 0;

        return (
            <article>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>{ATTRS.title[LANG]}</th>
                            <th>{ATTRS.langCode[LANG]}</th>
                            <th>{ATTRS.style[LANG]}</th>
                            <th>{ATTRS.country[LANG]}</th>
                            <th>{ATTRS.zipCode[LANG]}</th>
                            <th>{ATTRS.phoneNumber[LANG]}</th>
                            <th>{ATTRS.state[LANG]}</th>
                            <th>{ATTRS.city[LANG]}</th>
                        </tr>
                        <tr>
                            <th colSpan="4">{ATTRS.addressPartOne[LANG]}</th>
                            <th colSpan="4">{ATTRS.addressPartTwo[LANG]}</th>
                        </tr>
                    </thead>
                    { existsAddressBook ?
                        this.props.addressBook.addresses.map( function (address, index) {
                            //
                            let className = index % 2 === 0 ? 'active' : '';

                            return (
                                <tbody key={index}>
                                    <tr className={className}>
                                        <td>{address[ATTRS.title.name]}</td>
                                        <td>{ENUMS.language[address[ATTRS.langCode.name]][LANG]}</td>
                                        <td>{ENUMS.addressStyle[address[ATTRS.style.name]][LANG]}</td>
                                        <td>{address[ATTRS.country.name]}</td>
                                        <td>{address[ATTRS.zipCode.name]}</td>
                                        <td>{address[ATTRS.phoneNumber.name]}</td>
                                        <td>{address[ATTRS.state.name]}</td>
                                        <td>{address[ATTRS.city.name]}</td>
                                    </tr>
                                    <tr className={className}>
                                        <td colSpan="4">{address[ATTRS.addressPartOne.name]}</td>
                                        <td colSpan="4">{address[ATTRS.addressPartTwo.name]}</td>
                                    </tr>
                                </tbody>
                            )
                        })
                        :
                        <tbody>
                            <tr>
                                <td colSpan="8">{MESSAGES.notRegisteredAddress[LANG]}</td>
                            </tr>
                        </tbody>
                    }
                </table>
                <div className="btn-toolbar pull-right">
                    <button type="button" className="btn-group btn btn-default" onClick={this.modifiableModeBtnClick}>
                        {BUTTON_NAMES.modify[LANG]}
                    </button>
                    <button type="button" className="btn-group btn btn-danger" onClick={this.removeBtnClick}>
                        {BUTTON_NAMES.remove[LANG]}
                    </button>
                </div>
            </article>
        );
    }
}

AddressViewContent.propTypes = {
    //
    addressBook: PropTypes.shape({
        addresses: PropTypes.array.isRequired
    }).isRequired,
    changeModifiableMode: PropTypes.func.isRequired
};


class AddressModifiableContent extends Component {
    //
    constructor(props) {
        //
        super(props);

        this.state = {
            willModifyAddresses: [],
            inputProgressAddresses : [],
            rowsModifiable: []
        };

        this.titleChange = this.titleChange.bind(this);
        this.langCodeChange = this.langCodeChange.bind(this);
        this.styleChange = this.styleChange.bind(this);
        this.countryChange = this.countryChange.bind(this);
        this.zipCodeChange = this.zipCodeChange.bind(this);
        this.phoneNumberChange = this.phoneNumberChange.bind(this);
        this.stateChange = this.stateChange.bind(this);
        this.cityChange = this.cityChange.bind(this);
        this.addressPartOneChange = this.addressPartOneChange.bind(this);
        this.addressPartTwoChange = this.addressPartTwoChange.bind(this);
        this.completeAddressBtnClick = this.completeAddressBtnClick.bind(this);
        this.cancelAddressBtnClick = this.cancelAddressBtnClick.bind(this);
        this.modifyAddressBtnClick = this.modifyAddressBtnClick.bind(this);
        this.removeAddressBtnClick = this.removeAddressBtnClick.bind(this);
        this.addAddressBtnClick = this.addAddressBtnClick.bind(this);
        this.saveAddressBookBtnClick = this.saveAddressBookBtnClick.bind(this);
        this.cancelModificationBtnClick = this.cancelModificationBtnClick.bind(this);
        this.setWillModifyAddress = this.setWillModifyAddress.bind(this);
        this.setProgressAddressState = this.setProgressAddressState.bind(this);
        this.setRowModifiableState = this.setRowModifiableState.bind(this);
    }
    // overriding
    componentDidMount() {
        let addresses = NaraObject.deepCopy(this.props.addressBook.addresses),
            rowsModifiable = [];

        rowsModifiable.length = addresses.length;
        rowsModifiable.fill(false);

        this.setState({
            willModifyAddresses: addresses,
            inputProgressAddresses: NaraObject.deepCopy(addresses),
            rowsModifiable: rowsModifiable
        });
    }
    // event
    titleChange(index, event) {
        this.setProgressAddressState(index, CastleAddressModel.attrs.title.name, event.target.value);
    }
    langCodeChange(index, event) {
        this.setProgressAddressState(index, CastleAddressModel.attrs.langCode.name, event.target.value);
    }
    styleChange(index, event) {
        this.setProgressAddressState(index, CastleAddressModel.attrs.style.name, event.target.value);
    }
    countryChange(index, event) {
        this.setProgressAddressState(index, CastleAddressModel.attrs.country.name, event.target.value);
    }
    zipCodeChange(index, event) {
        this.setProgressAddressState(index, CastleAddressModel.attrs.zipCode.name, event.target.value);
    }
    phoneNumberChange(index, event) {
        this.setProgressAddressState(index, CastleAddressModel.attrs.phoneNumber.name, event.target.value);
    }
    stateChange(index, event) {
        this.setProgressAddressState(index, CastleAddressModel.attrs.state.name, event.target.value);
    }
    cityChange(index, event) {
        this.setProgressAddressState(index, CastleAddressModel.attrs.city.name, event.target.value);
    }
    addressPartOneChange(index, event) {
        this.setProgressAddressState(index, CastleAddressModel.attrs.addressPartOne.name, event.target.value);
    }
    addressPartTwoChange(index, event) {
        this.setProgressAddressState(index, CastleAddressModel.attrs.addressPartTwo.name, event.target.value);
    }
    completeAddressBtnClick(index) {
        this.setWillModifyAddress(index, this.state.inputProgressAddresses[index]);
        this.setRowModifiableState(index, false);
    }
    cancelAddressBtnClick(index) {
        let addresses = NaraObject.deepCopy(this.state.inputProgressAddresses);
        addresses[index] = NaraObject.deepCopy(this.state.willModifyAddresses[index]);

        if (NaraObject.isEmpty(addresses[index])) {
            addresses.splice(index, 1);

            let rowsModifiable = this.state.rowsModifiable;
            rowsModifiable.splice(index, 1);

            this.setState({ rowsModifiable: rowsModifiable });
        }
        else {
            this.setRowModifiableState(index, false);
        }
        this.setState({ inputProgressAddresses: addresses });
    }
    modifyAddressBtnClick(index) {
        this.setRowModifiableState(index, true);
    }
    removeAddressBtnClick(index) {
        let progressAddresses = this.state.inputProgressAddresses,
            willModifyAddresses = this.state.willModifyAddresses,
            rowsModifiable = this.state.rowsModifiable;

        progressAddresses.splice(index, 1);
        willModifyAddresses.splice(index, 1);
        rowsModifiable.splice(index, 1);

        this.setState({
            inputProgressAddresses: progressAddresses,
            willModifyAddresses: willModifyAddresses,
            rowsModifiable: rowsModifiable
        });
    }
    addAddressBtnClick() {
        let addresses = this.state.inputProgressAddresses,
            rowsModifiable = this.state.rowsModifiable;

        addresses.push({});
        rowsModifiable.push(true);

        this.setState({ inputProgressAddresses: addresses, rowsModifiable: rowsModifiable });
    }
    saveAddressBookBtnClick() {
        this.props.saveAddressBook({ addresses: this.state.willModifyAddresses });
    }
    cancelModificationBtnClick() {
        this.props.changeViewMode();
    }
    // custom
    setWillModifyAddress(index, address) {
        //
        let copiedAddress = NaraObject.deepCopy(address);
        let addresses = this.state.willModifyAddresses;

        addresses[index] = copiedAddress;
        this.setState({ willModifyAddresses: addresses });
    }
    setProgressAddressState(index, propertyName, value) {
        //
        let addresses = this.state.inputProgressAddresses;

        addresses[index][propertyName] = value;
        this.setState({ inputProgressAddresses: addresses });
    }
    setRowModifiableState(index, modifiable) {
        //
        let rowsModifiable = this.state.rowsModifiable;

        rowsModifiable[index] = modifiable;
        this.setState({ rowsModifiable: rowsModifiable });
    }
    render() {
        //
        const ATTRS = CastleAddressModel.attrs,
            BUTTON_NAMES = CastleModel.buttons,
            MESSAGES = CastleAddressModel.messages,
            LANG = MainComponent.lang;

        let existsAddressBook = this.props.addressBook.addresses.length > 0;

        return (
            <article>
                <table className="table table-hover">
                    <thead>
                        <tr>
                            <th>{ATTRS.title[LANG]}</th>
                            <th>{ATTRS.langCode[LANG]}</th>
                            <th>{ATTRS.style[LANG]}</th>
                            <th>{ATTRS.country[LANG]}</th>
                            <th>{ATTRS.zipCode[LANG]}</th>
                            <th>{ATTRS.phoneNumber[LANG]}</th>
                            <th>{ATTRS.state[LANG]}</th>
                            <th>{ATTRS.city[LANG]}</th>
                            <th className="col-md-2" rowSpan="2">&nbsp;</th>
                        </tr>
                        <tr>
                            <th colSpan="4">{ATTRS.addressPartOne[LANG]}</th>
                            <th colSpan="4">{ATTRS.addressPartTwo[LANG]}</th>
                        </tr>
                    </thead>
                    { existsAddressBook ?
                        this.state.inputProgressAddresses.map( function (address, index) {
                            //
                            let className = index % 2 === 0 ? 'active' : '';

                            return (
                                this.state.rowsModifiable[index] === true ?
                                    <tbody key={index}>
                                        <tr className={className}>
                                            <td>
                                                <input type="text" className="form-control"
                                                       onChange={this.titleChange.bind(this, index)}
                                                       value={address[ATTRS.title.name]}
                                                />
                                            </td>
                                            <td>
                                                <input type="text" className="form-control"
                                                       onChange={this.langCodeChange.bind(this, index)}
                                                       value={address[ATTRS.langCode.name]}
                                                />
                                            </td>
                                            <td>
                                                <input type="text" className="form-control"
                                                       onChange={this.styleChange.bind(this, index)}
                                                       value={address[ATTRS.style.name]}
                                                />
                                            </td>
                                            <td>
                                                <input type="text" className="form-control"
                                                       onChange={this.countryChange.bind(this, index)}
                                                       value={address[ATTRS.country.name]}
                                                />
                                            </td>
                                            <td>
                                                <input type="text" className="form-control"
                                                       onChange={this.zipCodeChange.bind(this, index)}
                                                       value={address[ATTRS.zipCode.name]}
                                                />
                                            </td>
                                            <td>
                                                <input type="text" className="form-control"
                                                       onChange={this.phoneNumberChange.bind(this, index)}
                                                       value={address[ATTRS.phoneNumber.name]}
                                                />
                                            </td>
                                            <td>
                                                <input type="text" className="form-control"
                                                       onChange={this.stateChange.bind(this, index)}
                                                       value={address[ATTRS.state.name]}
                                                />
                                            </td>
                                            <td>
                                                <input type="text" className="form-control"
                                                       onChange={this.cityChange.bind(this, index)}
                                                       value={address[ATTRS.city.name]}
                                                />
                                            </td>
                                            <td rowSpan="2">
                                                <div className="btn-group btn-group-sm text-center">
                                                    <button type="button" className="btn btn-sm btn-primary"
                                                            onClick={this.completeAddressBtnClick.bind(this, index)}>
                                                        {BUTTON_NAMES.complete[LANG]}
                                                    </button>
                                                    <button type="button" className="btn btn-sm btn-default"
                                                            onClick={this.cancelAddressBtnClick.bind(this, index)}>
                                                        {BUTTON_NAMES.cancel[LANG]}
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr className={className}>
                                            <td colSpan="4">
                                                <input type="text" className="form-control"
                                                       onChange={this.addressPartOneChange.bind(this, index)}
                                                       value={address[ATTRS.addressPartOne.name]}
                                                />
                                            </td>
                                            <td colSpan="4">
                                                <input type="text" className="form-control"
                                                       onChange={this.addressPartTwoChange.bind(this, index)}
                                                       value={address[ATTRS.addressPartTwo.name]}
                                                />
                                            </td>
                                        </tr>
                                    </tbody>
                                    :
                                    <tbody key={index}>
                                        <tr className={className}>
                                            <td>{address[ATTRS.title.name]}</td>
                                            <td>{address[ATTRS.langCode.name]}</td>
                                            <td>{address[ATTRS.style.name]}</td>
                                            <td>{address[ATTRS.country.name]}</td>
                                            <td>{address[ATTRS.zipCode.name]}</td>
                                            <td>{address[ATTRS.phoneNumber.name]}</td>
                                            <td>{address[ATTRS.state.name]}</td>
                                            <td>{address[ATTRS.city.name]}</td>
                                            <td rowSpan="2">
                                                <div className="btn-group btn-group-sm text-center">
                                                    <button type="button" className="btn btn-sm btn-default"
                                                            onClick={this.modifyAddressBtnClick.bind(this, index)}>
                                                        {BUTTON_NAMES.modify[LANG]}
                                                    </button>
                                                    <button type="button" className="btn btn-sm btn-danger"
                                                            onClick={this.removeAddressBtnClick.bind(this, index)}>
                                                        {BUTTON_NAMES.remove[LANG]}
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr className={className}>
                                            <td colSpan="4">{address[ATTRS.addressPartOne.name]}</td>
                                            <td colSpan="4">{address[ATTRS.addressPartTwo.name]}</td>
                                        </tr>
                                    </tbody>
                            )
                        }.bind(this))
                        :
                        <tbody>
                            <tr>
                                <td colSpan="9">{MESSAGES.notRegisteredAddress[LANG]}</td>
                            </tr>
                        </tbody>
                    }
                </table>
                <div className="btn-block btn-toolbar">
                    <div className="pull-right">
                        <button type="button" className="btn btn-default btn-sm"
                                onClick={this.addAddressBtnClick}>
                            {BUTTON_NAMES.add[LANG]}
                        </button>
                    </div>
                </div>
                <div className="btn-toolbar pull-right">
                    <button type="button" className="btn-group btn btn-primary"
                            onClick={this.saveAddressBookBtnClick}>
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

AddressModifiableContent.propTypes = {
    //
    addressBook: PropTypes.shape({
        addresses: PropTypes.array.isRequired
    }).isRequired,
    changeViewMode: PropTypes.func.isRequired,
    saveAddressBook: PropTypes.func.isRequired
};


export default AddressContent;