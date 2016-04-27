/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.PhoneBook = Components.Castle.PhoneBook || {};

(function () {
    //
    'use strict';

    // Import component module
    var castleCommon = CastleCommon,
        castleConst = CastleConst,
        mainComponent = Components.Main,
        castleModel = Components.Castle.Model;

    // Define Content attributes name
    var castlePhoneModel = {
        attrs: {
            phoneNumber:    { name: 'phoneNumber',  KOR: '전체 번호', USA: 'Phone number' },
            countryCode:    { name: 'countryCode',  KOR: '국가코드',  USA: 'Country code' },
            areaCode:       { name: 'areaCode',     KOR: '지역코드',  USA: 'Area code' },
            number:         { name: 'number',       KOR: '번호',      USA: 'Number' }
        },
        messages : {
            notRegisteredPhone : { KOR: '등록 된 phone이 없습니다', USA: 'Not registered the phone' }
        }
    };

    // Define components
    var CastleDetailPage = React.createClass({
        //
        statics: {
            FIND_PHONE_BOOK_URL: castleConst.CTX + '/api/castle/{id}/phone-book'

        },
        propTypes : {
            id: React.PropTypes.string
        },
        getInitialState: function () {
            return {
                phoneBook: { phones: [] },
                contentModifiable: false
            };
        },
        componentDidMount: function () {
            this.requestPhoneBook(this.props);
        },
        changeModifiableMode: function () {
            this.setState({contentModifiable: true});
        },
        changeViewMode: function () {
            this.setState({contentModifiable: false});
        },
        requestPhoneBook: function (props) {
            castleCommon
                .getJSON(CastleDetailPage.FIND_PHONE_BOOK_URL.replace('{id}', props.id))
                .done( function (phoneBookResult) {
                    this.setState({ phoneBook: phoneBookResult });
                }.bind(this));
        },
        render: function () {
            return (
                <Tab
                    castleId={this.props.id}
                    phoneBook={this.state.phoneBook}
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
            castleId: React.PropTypes.string.isRequired,
            phoneBook: React.PropTypes.object,
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
                                <li className="active">
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
                                    <PhoneContent phoneBook={this.props.phoneBook} />
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


    var PhoneContent = React.createClass({
        propTypes: {
            phoneBook: React.PropTypes.shape({
                phones: React.PropTypes.array.isRequired
            }).isRequired
        },
        render: function () {
            var ATTRS = castlePhoneModel.attrs,
                MESSAGES = castlePhoneModel.messages,
                lang = mainComponent.lang,
                propPhoneBook = this.props.phoneBook,
                existsPhoneBook = (propPhoneBook && propPhoneBook.phones && propPhoneBook.phones.length > 0) ? true : false;

            return (
                <article>
                    <table className="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>{ATTRS.phoneNumber[lang]}</th>
                                <th>{ATTRS.countryCode[lang]}</th>
                                <th>{ATTRS.areaCode[lang]}</th>
                                <th>{ATTRS.number[lang]}</th>
                            </tr>
                        </thead>
                        <tbody>
                        { existsPhoneBook ?
                            propPhoneBook.phones.map( function (phone) {
                                return (
                                    <tr key={phone[ATTRS.phoneNumber.name]}>
                                        <td>{phone[ATTRS.phoneNumber.name]}</td>
                                        <td>{phone[ATTRS.countryCode.name]}</td>
                                        <td>{phone[ATTRS.areaCode.name]}</td>
                                        <td>{phone[ATTRS.number.name]}</td>
                                    </tr>
                                )
                            })
                            :
                            <tr><td colSpan="4">{MESSAGES.notRegisteredPhone[lang]}</td></tr>
                        }
                        </tbody>
                    </table>
                    <ButtonGroup modifiable={this.props.modifiable}/>
                </article>
            );
        }
    });


    Components.Castle.PhoneBook = CastleDetailPage;
})();