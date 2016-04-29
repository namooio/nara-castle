/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.AddressBook = Components.Castle.AddressBook || {};

(function () {
    //
    'use strict';

    // Import component module
    var commonAjax = NaraCommon.Ajax,
        constant = CastleCommon.Const,
        mainComponent = Components.Main,
        castleModel = Components.Castle.Model;


    // Define Content attributes name
    var castleAddressModel = {
        attrs: {
            title:          { name: 'title',            KOR: '주소명',    USA: 'Title' },
            langCode:       { name: 'langCode',         KOR: '언어코드',  USA: 'Language code' },
            style:          { name: 'style',            KOR: '유형',      USA: 'Style' },
            country:        { name: 'country',          KOR: '국가',      USA: 'Coutnry' },
            zipCode:        { name: 'zipCode',          KOR: '우편번호',  USA: 'Zip code' },
            state:          { name: 'state',            KOR: '지역',      USA: 'State' },
            city:           { name: 'city',             KOR: '시',        USA: 'City' },
            addressPartOne: { name: 'addressPartOne',   KOR: '주소1',     USA: 'Address part1' },
            addressPartTwo: { name: 'addressPartTwo',   KOR: '주소2',     USA: 'Address part2' },
            phoneNumber:    { name: 'phoneNumber',      KOR: '전화번호',  USA: 'Phone number' }
        },
        messages: {
            notRegisteredAddress: { KOR: '등록 된 주소가 없습니다', USA: 'Not registered the address' }
        }
    };

    // Define components
    var CastleDetailPage = React.createClass({
        //
        statics: {
            FIND_ADDRESS_BOOK_URL: constant.CTX + '/api/castellans/{id}/contacts/address-book'
        },
        propTypes : {
            id: React.PropTypes.string
        },
        getInitialState: function () {
            return {
                addressBook: { addresses: [] },
                contentModifiable: false
            };
        },
        componentDidMount: function () {
            this.requestAddressBook(this.props);
        },
        changeModifiableMode: function () {
            this.setState({contentModifiable: true});
        },
        changeViewMode: function () {
            this.setState({contentModifiable: false});
        },
        requestAddressBook: function (props) {
            commonAjax
                .getJSON(CastleDetailPage.FIND_ADDRESS_BOOK_URL.replace('{id}', props.id))
                .done( function (addressBookResult) {
                    this.setState({ addressBook: addressBookResult });
                }.bind(this));
        },
        render: function () {
            return (
                <Tab
                    castleId={this.props.id}
                    addressBook={this.state.addressBook}
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
            addressBook: React.PropTypes.object,
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
                                <li>
                                    <a href={"#/castle/contact/email-book?contentType=" + TAB_NAMES.email.name + '&id=' + this.props.castleId}>{TAB_NAMES.email[lang]}</a>
                                </li>
                                <li className="active">
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
                                    <AddressContent addressBook={this.props.addressBook} />
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

    var AddressContent = React.createClass({
        propTypes: {
            addressBook: React.PropTypes.shape({
                addresses: React.PropTypes.array.isRequired
            }).isRequired
        },
        render: function () {
            var ENUMS = castleModel.enums,
                ATTRS = castleAddressModel.attrs,
                MESSAGES = castleAddressModel.messages,
                lang = mainComponent.lang,
                propAddressBook = this.props.addressBook,
                existsAddressBook = (propAddressBook && propAddressBook.addresses && propAddressBook.addresses.length > 0) ? true : false,
                multipleRowAddresses = [],
                odd = true;

            propAddressBook.addresses.map( function (address) {
                var arrayFirstNode = {
                    type: 'basicAddress',
                    odd: odd,
                    [ATTRS.title.name] : address.title,
                    [ATTRS.langCode.name] : address[ATTRS.langCode.name],
                    [ATTRS.style.name] : address[ATTRS.style.name],
                    [ATTRS.country.name] : address[ATTRS.country.name],
                    [ATTRS.zipCode.name] : address[ATTRS.zipCode.name],
                    [ATTRS.phoneNumber.name] : address[ATTRS.phoneNumber.name],
                    [ATTRS.state.name] : address[ATTRS.state.name],
                    [ATTRS.city.name] : address[ATTRS.city.name]
                };
                multipleRowAddresses.push(arrayFirstNode);

                var arraySecondNode = {
                    type: 'detailAddress',
                    odd: odd,
                    [ATTRS.addressPartOne.name] : address[ATTRS.addressPartOne.name],
                    [ATTRS.addressPartTwo.name] : address[ATTRS.addressPartTwo.name]
                };
                multipleRowAddresses.push(arraySecondNode);

                odd = !odd;
            });


            return (
                <article>
                    <table className="table">
                        <thead>
                        <tr>
                            <th>{ATTRS.title[lang]}</th>
                            <th>{ATTRS.langCode[lang]}</th>
                            <th>{ATTRS.style[lang]}</th>
                            <th>{ATTRS.country[lang]}</th>
                            <th>{ATTRS.zipCode[lang]}</th>
                            <th>{ATTRS.phoneNumber[lang]}</th>
                            <th>{ATTRS.state[lang]}</th>
                            <th>{ATTRS.city[lang]}</th>
                        </tr>
                        <tr>
                            <th colSpan="4">{ATTRS.addressPartOne[lang]}</th>
                            <th colSpan="4">{ATTRS.addressPartTwo[lang]}</th>
                        </tr>
                        </thead>
                        <tbody>
                        { existsAddressBook ?
                            multipleRowAddresses.map( function (address, index) {
                                //
                                var className = address.odd ? 'active' : '';

                                if (address.type === 'basicAddress') {
                                    return (
                                        <tr key={index} className={className}>
                                            <td>{address[ATTRS.title.name]}</td>
                                            <td>{ENUMS.language[address[ATTRS.langCode.name]][lang]}</td>
                                            <td>{ENUMS.addressStyle[address[ATTRS.style.name]][lang]}</td>
                                            <td>{address[ATTRS.country.name]}</td>
                                            <td>{address[ATTRS.zipCode.name]}</td>
                                            <td>{address[ATTRS.phoneNumber.name]}</td>
                                            <td>{address[ATTRS.state.name]}</td>
                                            <td>{address[ATTRS.city.name]}</td>
                                        </tr>
                                    )
                                }
                                else if (address.type === 'detailAddress') {
                                    return (
                                        <tr key={index} className={className}>
                                            <td colSpan="4">{address[ATTRS.addressPartOne.name]}</td>
                                            <td colSpan="4">{address[ATTRS.addressPartTwo.name]}</td>
                                        </tr>
                                    )
                                }
                            })
                            :
                            <tr><td colSpan="9">{MESSAGES.notRegisteredAddress[lang]}</td></tr>
                        }
                        </tbody>
                    </table>
                    <ButtonGroup modifiable={this.props.modifiable}/>
                </article>
            );
        }
    });


    Components.Castle.AddressBook = CastleDetailPage;
})();