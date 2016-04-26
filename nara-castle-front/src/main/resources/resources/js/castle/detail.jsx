/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.Detail = Components.Castle.Detail || {};

(function () {
    //
    // Import component module
    'use strict';

    var mainComponent = Components.Main,
        castleConst = CastleConst,
        castleCommon = CastleCommon;

    // Define Content attributes name
    var contentProps = {
        buttons : {
            save:     { KOR: '저장', USA: 'Save'},
            modify:   { KOR: '수정', USA: 'Modify' },
            remove:   { KOR: '삭제', USA: 'Remove'},
            cancel:   { KOR: '취소', USA: 'Cancel'}
        },
        tabs: {
            basic:      { name: 'basic',    KOR: '기본정보',   USA: 'Basic information' },
            name:       { name: 'name',     KOR: '이름',       USA: 'Name' },
            phone:      { name: 'phone',    KOR: '전화번호',   USA: 'Phone number' },
            email:      { name: 'email',    KOR: '이메일',     USA: 'Email' },
            address:    { name: 'address',  KOR: '주소',       USA: 'Address' },
            account:    { name: 'account',  KOR: '계정내역',   USA: 'Account history' },
            state:      { name: 'state',    KOR: '상태내역',   USA: 'State history' },
            metro:      { name: 'metro',    KOR: '메트로내역', USA: 'Metro history' }
        },
        basicInfo: {
            id:         { name: 'id',         KOR: '아이디',   USA: 'Id' },
            name:       { name: 'name',       KOR: '이름',     USA: 'Name' },
            locale:     { name: 'locale',     KOR: '지역',     USA: 'Locale' },
            state:      { name: 'state',      KOR: '상태',     USA: 'State' },
            buildTime:  { name: 'buildTime',  KOR: '생성일시', USA: 'Build time' },
            castellan:  {
                primaryEmail:   { name: 'primaryEmail', KOR: '기본 이메일',   USA: 'Primary email' },
                primaryPhone:   { name: 'primaryPhone', KOR: '기본 전화번호', USA: 'Primary phone number' },
                photo:          { name: 'photoId',      KOR: '사진',          USA: 'Photo' }
            }
        },
        name: {
            familyName:     { name: 'familyName',   KOR: '성',       USA: 'Family name' },
            firstName:      { name: 'firstName',    KOR: '이름',     USA: 'First name' },
            displayName:    { name: 'displayName',  KOR: '전체이름', USA: 'Display name' },
            middleName:     { name: 'middleName',   KOR: '중간이름', USA: 'Middle name' },
            langCode:       { name: 'langCode',     KOR: '언어',     USA: 'Language' }
        },
        phone: {
            phoneNumber:    { name: 'phoneNumber',  KOR: '전체 번호', USA: 'Phone number' },
            countryCode:    { name: 'countryCode',  KOR: '국가코드',  USA: 'Country code' },
            areaCode:       { name: 'areaCode',     KOR: '지역코드',  USA: 'Area code' },
            number:         { name: 'number',       KOR: '번호',      USA: 'Number' }
        },
        email: {
            email:          { name: 'email',        KOR: '이메일',        USA: 'Email' },
            emailType:      { name: 'emailType',    KOR: '유형',          USA: 'type' },
            verified:       { name: 'verified',     KOR: '유효확인 여부', USA: 'Verified' },
            verifiedTime:   { name: 'verifiedTime', KOR: '유효확인 일시', USA: 'Vefiried time' }
        },
        address: {
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
        account: {
            loginUserId:    { name: 'loginUserId',      KOR: '로그인Id',    USA: 'Login user id' },
            channel:        { name: 'channel',          KOR: '접속방법',    USA: 'Channel' },
            createTime:     { name: 'createTime',       KOR: '생성일시',    USA: 'Create time' },
            deleteTime:     { name: 'deleteTime',       KOR: '삭제일시',    USA: 'Delete time' }
        },
        state: {
            currentState:   { name: 'currentState', KOR: '현재상태',    USA: 'Current state' },
            targetState:    { name: 'targetState',  KOR: '다음상태',    USA: 'Target state' },
            remarks:        { name: 'remarks',      KOR: '설명',        USA: 'Remarks' },
            modifiedTime:   { name: 'modifiedTime', KOR: '수정일시',    USA: 'Modified time' }
        },
        metro: {
            metroId:        { name: 'metroId',          KOR: '메트로Id',    USA: 'Metro id' },
            metroName:      { name: 'metroName',        KOR: '메트로명',    USA: 'Metro name' },
            joinTime:       { name: 'joinTime',         KOR: '가입일시',    USA: 'Join time' },
            withdrawalTime: { name: 'withdrawalTime',   KOR: '탈퇴일시',    USA: 'Withdrawal time' },
            remarks:        { name: 'remarks',          KOR: '설명',        USA: 'Remarks' }
        }
    };

    // Define components
    var CastleDetailPage = React.createClass({
        //
        propTypes : {
            contentType: React.PropTypes.string.isRequired,
            id: React.PropTypes.string
        },
        statics : {
            containerStyle: {width: '1100px'}
        },
        getDefaultProps: function () {
            return {
                contentType: 'basic'
            };
        },
        getInitialState: function () {
            return {
                castle: {
                    basicInfo: { castellan: {} },
                    nameBook: { names: [] },
                    phoneBook: { phones: [] },
                    emailBook: { emails: [] },
                    addressBook: { addresses: [] },
                    accountBook: { accounts: [] },
                    stateBook: { states: [] },
                    metroBook: { metros: [] }
                },
                contentModifiable: false
            };
        },
        componentDidMount: function () {
            console.debug('Exectue detal.jsx CastleDetailPage componentDidMount');
            castleCommon.getJSON(castleConst.CTX + '/api/castle/' + this.props.id).done( function (castleResult) {
                console.debug('Exectue detal.jsx CastleDetailPage componentDidMount callback');

                var castleState = this.state.castle;

                castleState.basicInfo = castleResult;
                this.setState({castle: castleState});
                console.dir(this.props);
                console.dir(this.state);
            }.bind(this));
        },
        componentWillReceiveProps: function (props) {
            var CONTENT_TYPES = contentProps.tabs,
                url,
                callback;

            console.debug('Exectue detal.jsx CastleDetailPage componentWillReceiveProps -> ');

            switch (props.contentType) {
                case CONTENT_TYPES.basic.name:
                    console.debug('basic');
                    url = '/api/castle/' + props.id;
                    callback = function (castleResult) {
                        var castleState = this.state.castle;
                        castleState.basicInfo = castleResult;
                        this.setState({castle: castleState});
                    }.bind(this);
                    break;
                case CONTENT_TYPES.name.name:
                    console.debug('name');
                    url = '/api/castle/' + props.id + '/name-book';
                    callback = function (nameBookResult) {
                        var castleState = this.state.castle;
                        castleState.nameBook = nameBookResult;
                        this.setState( {castle: castleState} );
                        console.debug('Execute detail.jsx NameContent componentDidMount()');
                        console.dir(this.props);
                        console.dir(this.state);
                    };
                    break;
                case CONTENT_TYPES.phone.name:
                    url = '/api/castle/' + props.id + '/phone-book';
                    callback = function (phoneBookdResult) {
                        var castleState = this.state.castle;
                        castleState.phoneBook = phoneBookdResult;
                        this.setState( {castle: castleState} );
                    };
                    break;
                case CONTENT_TYPES.email.name:
                    url = '/api/castle/' + props.id + '/email-book';
                    callback = function (emailBookResult) {
                        var castleState = this.state.castle;
                        castleState.emailBook = emailBookResult;
                        this.setState( { castle: castleState } );
                    };
                    break;
                case CONTENT_TYPES.address.name:
                    url = '/api/castle/' + props.id + '/address-book';
                    callback = function (addressBookResult) {
                        var castleState = this.state.castle;
                        castleState.addressBook = addressBookResult;
                        this.setState( { castle: castleState });
                    };
                    break;
                case CONTENT_TYPES.account.name:
                    url = '/api/castle/' + props.id + '/account-book';
                    callback = function (accountBookResult) {
                        var castleState = this.state.castle;
                        castleState.accountBook = accountBookResult;
                        this.setState( { castle: castleState });
                    };
                    break;
                case CONTENT_TYPES.state.name:
                    url = '/api/castle/' + props.id + '/state-book';
                    callback = function (stateBookResult) {
                        var castleState = this.state.castle;
                        castleState.stateBook = stateBookResult;
                        this.setState( { castle: castleState });
                    };
                    break;
                case CONTENT_TYPES.metro.name:
                    url = '/api/castle/' + props.id + '/metro-book';
                    callback = function (metroBookResult) {
                        var castleState = this.state.castle;
                        castleState.metroBook = metroBookResult;
                        this.setState( { castle: castleState });
                    };
                    break;
            }
            castleCommon.getJSON(castleConst.CTX + url).done(callback.bind(this));
        },
        changeModifiableMode: function () {
            this.setState({contentModifiable: true});
        },
        changeViewMode: function () {
            this.setState({contentModifiable: false});
        },
        render: function () {
            return (
                <article>
                    <Tab
                        containerStyle={CastleDetailPage.containerStyle}
                        contentType={this.props.contentType}
                        castle={this.state.castle}
                        modifiable={this.state.contentModifiable}
                        changeModifiableMode={this.changeModifiableMode}
                        changeViewMode={this.changeViewMode}
                    />
                </article>
            );
        }
    });

    var Tab = React.createClass({
        //
        propTypes: {
            containerStyle: React.PropTypes.object.isRequired,
            contentType: React.PropTypes.string.isRequired,
            castle: React.PropTypes.object,
            modifiable: React.PropTypes.bool.isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired
        },
        render: function () {
            var lang = mainComponent.lang,
                TAB_NAMES = contentProps.tabs,
                currentContentType = this.props.contentType,
                content;

            switch (currentContentType) {
                case TAB_NAMES.basic.name:
                    content = <BasicInfoContent
                        basicInfo={this.props.castle.basicInfo}
                        castellan={this.props.castle.castellan}
                        modifiable={this.props.modifiable}
                        changeModifiableMode={this.props.changeModifiableMode}
                        changeViewMode={this.props.changeViewMode}
                    />
                    break;
                case TAB_NAMES.name.name:
                    content = <NameContent
                        nameBook={this.props.castle.nameBook}
                    />
                    break;
                case TAB_NAMES.phone.name:
                    content = <PhoneContent
                        phoneBook={this.props.castle.phoneBook}
                    />
                    break;
                case TAB_NAMES.email.name:
                    content = <EmailContent
                        emailBook={this.props.castle.emailBook}
                    />
                    break;
                case TAB_NAMES.address.name:
                    content = <AddressContent
                        addressBook={this.props.castle.addressBook}
                    />
                    break;
                case TAB_NAMES.account.name:
                    content = <AccountContent
                        accountBook={this.props.castle.accountBook}
                    />
                    break;
                case TAB_NAMES.state.name:
                    content = <StateContent
                        stateBook={this.props.castle.stateBook}
                    />
                    break;
                case TAB_NAMES.metro.name:
                    content = <MetroContent
                        metroBook={this.props.castle.metroBook}
                    />
                    break;
            }

            return (
                <div className="container" style={this.props.containerStyle}>
                    <div className="panel panel-success">
                        <div className="panel-body">
                            <ul className="nav nav-tabs">
                                { $.map(TAB_NAMES, function (tabAttr) {
                                    return (
                                        <li key={tabAttr.name} className={(currentContentType === tabAttr.name) ? "active" : null}>
                                            <a href={"#/castle/detail?contentType=" + tabAttr.name + '&id=' + this.props.castle.basicInfo.id}>{tabAttr[lang]}</a>
                                        </li>
                                    );
                                }.bind(this))}
                            </ul>
                            {content}
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
            var BUTTON_NAMES = contentProps.buttons,
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

    var BasicInfoContent = React.createClass({
        propTypes: {
            basicInfo: React.PropTypes.object
            , castellan: React.PropTypes.object
            , modifiable: React.PropTypes.bool.isRequired

            , changeModifiableMode: React.PropTypes.func.isRequired
            , changeViewMode: React.PropTypes.func.isRequired
        },
        modifiableModeBtnClick: function () {
            this.props.changeModifiableMode();
        },
        cancelBtnClick: function () {
            this.props.changeViewMode();
        },
        render: function () {
            var lang = mainComponent.lang,
                CASTLE_ATTRS = contentProps.basicInfo,
                CASTELLAN_ATTRS = contentProps.basicInfo.castellan,
                BUTTON_NAMES = contentProps.buttons;

            return (
                <div className="tab-content">
                    <div className="tab-pane active">
                        <form className="form-horizontal">
                            <div className="form-group"><p>&nbsp;</p></div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.id[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">{this.props.basicInfo[CASTLE_ATTRS.id.name]}</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.name[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">{this.props.basicInfo[CASTLE_ATTRS.name.name]}</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.locale[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">{this.props.basicInfo[CASTLE_ATTRS.locale.name]}</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.state[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">{this.props.basicInfo[CASTLE_ATTRS.state.name]}</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{CASTELLAN_ATTRS.primaryEmail[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">{this.props.basicInfo.castellan[CASTELLAN_ATTRS.primaryEmail.name]}</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{CASTELLAN_ATTRS.primaryPhone[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">{this.props.basicInfo.castellan[CASTELLAN_ATTRS.primaryPhone.name]}</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{CASTELLAN_ATTRS.photo[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">{this.props.basicInfo.castellan[CASTELLAN_ATTRS.photo.name]}</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.buildTime[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">{castleCommon.Date.parseToString(this.props.basicInfo[CASTLE_ATTRS.buildTime.name])}</p>
                                </div>
                            </div>

                            { this.props.modifiable ?
                                <div className="btn-toolbar pull-right">
                                    <button type="button" className="btn-group btn btn-primary" onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.save[lang]}</button>
                                    <button type="button" className="btn-group btn btn-default" onClick={this.cancelBtnClick}>{BUTTON_NAMES.cancel[lang]}</button>
                                </div>
                                :
                                <div className="btn-toolbar pull-right">
                                    <button type="button" className="btn-group btn btn-default" onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.modify[lang]}</button>
                                    <button type="button" className="btn-group btn btn-danger" onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.remove[lang]}</button>
                                </div>
                            }
                        </form>
                    </div>
                </div>
            );
        }
    });

    var NameContent = React.createClass({
        propTypes: {
            nameBook: React.PropTypes.object.isRequired
        },
        render: function () {
            var ATTRS = contentProps.name,
                lang = mainComponent.lang,
                existsNameBook = (this.props.nameBook && this.props.nameBook.names.length > 0) ? true : false;

            return (
                <div className="tab-content">
                    <div className="tab-pane active">
                        <table className="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>{ATTRS.familyName[lang]}</th>
                                    <th>{ATTRS.firstName[lang]}</th>
                                    <th>{ATTRS.displayName[lang]}</th>
                                    <th>{ATTRS.middleName[lang]}</th>
                                    <th>{ATTRS.langCode[lang]}</th>
                                </tr>
                            </thead>
                            <tbody>
                                { existsNameBook ?
                                    this.props.nameBook.names.map( function (name) {
                                        return (
                                            <tr key={name[ATTRS.familyName.name]}>
                                                <td>{name[ATTRS.familyName.name]}</td>
                                                <td>{name[ATTRS.firstName.name]}</td>
                                                <td>{name[ATTRS.displayName.name]}</td>
                                                <td>{name[ATTRS.middleName.name]}</td>
                                                <td>{name[ATTRS.langCode.name]}</td>
                                            </tr>
                                        )
                                    })
                                    :
                                    <tr><td colSpan="5">등록 된 Name이 없습니다.</td></tr>
                                }
                            </tbody>
                        </table>
                        <ButtonGroup modifiable={this.props.modifiable}/>
                    </div>
                </div>
            );
        }
    });

    var PhoneContent = React.createClass({
        propTypes: {
            phoneBook: React.PropTypes.object.isRequired
        },
        render: function () {
            var ATTRS = contentProps.phone,
                lang = mainComponent.lang,
                propPhoneBook = this.props.phoneBook,
                existsPhoneBook = (propPhoneBook && propPhoneBook.phones && propPhoneBook.phones.length > 0) ? true : false;

            return (
                <div className="tab-content">
                    <div className="tab-pane active">
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
                                <tr><td colSpan="4">등록 된 전화번호가 없습니다.</td></tr>
                            }
                            </tbody>
                        </table>
                        <ButtonGroup modifiable={this.props.modifiable}/>
                    </div>
                </div>
            );
        }
    });

    var EmailContent = React.createClass({
        propTypes: {
            emailBook: React.PropTypes.object.isRequired
        },
        render: function () {
            var ATTRS = contentProps.email,
                lang = mainComponent.lang,
                propEmailBook = this.props.emailBook,
                existsEmailBook = (propEmailBook && propEmailBook.emails && propEmailBook.emails.length > 0) ? true : false;

            return (
                <div className="tab-content">
                    <div className="tab-pane active">
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
                                propEmailBook.emails.map( function (email) {
                                    return (
                                        <tr key={email[ATTRS.email.name]}>
                                            <td>{email[ATTRS.email.name]}</td>
                                            <td>{email[ATTRS.emailType.name]}</td>
                                            <td>{(email[ATTRS.verified.name]).toString()}</td>
                                            <td>{castleCommon.Date.parseToString(email[ATTRS.verifiedTime.name])}</td>
                                        </tr>
                                    )
                                })
                                :
                                <tr><td colSpan="4">등록 된 이메일이 없습니다.</td></tr>
                            }
                            </tbody>
                        </table>
                        <ButtonGroup modifiable={this.props.modifiable}/>
                    </div>
                </div>
            );
        }
    });

    var AddressContent = React.createClass({
        propTypes: {
            addressBook: React.PropTypes.object.isRequired
        },
        render: function () {
            var ATTRS = contentProps.address,
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
                }
                multipleRowAddresses.push(arrayFirstNode);

                var arraySecondNode = {
                    type: 'detailAddress',
                    odd: odd,
                    [ATTRS.addressPartOne.name] : address[ATTRS.addressPartOne.name],
                    [ATTRS.addressPartTwo.name] : address[ATTRS.addressPartTwo.name]
                }
                multipleRowAddresses.push(arraySecondNode);

                odd = !odd;
            });


            return (
                <div className="tab-content">
                    <div className="tab-pane active">
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

                                    var className = address.odd ? 'active' : '';
                                    console.log('odd: ' + address.odd);

                                    if (address.type === 'basicAddress') {
                                        return (
                                            <tr key={index} className={className}>
                                                <td>{address[ATTRS.title.name]}</td>
                                                <td>{address[ATTRS.langCode.name]}</td>
                                                <td>{address[ATTRS.style.name]}</td>
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
                                <tr><td colSpan="9">등록 된 주소가 없습니다.</td></tr>
                            }
                            </tbody>
                        </table>
                        <ButtonGroup modifiable={this.props.modifiable}/>
                    </div>
                </div>
            );
        }
    });

    var AccountContent = React.createClass({
        propTypes: {
            accountBook: React.PropTypes.object.isRequired
        },
        render: function () {
            var ATTRS = contentProps.account,
                lang = mainComponent.lang,
                propAccountBook = this.props.accountBook,
                existsAccountBook = (propAccountBook && propAccountBook.accounts && propAccountBook.accounts.length > 0) ? true : false;

            return (
                <div className="tab-content">
                    <div className="tab-pane active">
                        <table className="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>{ATTRS.loginUserId[lang]}</th>
                                    <th>{ATTRS.channel[lang]}</th>
                                    <th>{ATTRS.createTime[lang]}</th>
                                    <th>{ATTRS.deleteTime[lang]}</th>
                                </tr>
                            </thead>
                            <tbody>
                            { existsAccountBook ?
                                propAccountBook.accounts.map( function (account, index) {
                                    return (
                                        <tr key={index}>
                                            <td>{account[ATTRS.loginUserId.name]}</td>
                                            <td>{account[ATTRS.channel.name]}</td>
                                            <td>{castleCommon.Date.parseToString(account[ATTRS.createTime.name])}</td>
                                            <td>{castleCommon.Date.parseToString(account[ATTRS.deleteTime.name])}</td>
                                        </tr>
                                    )
                                })
                                :
                                <tr><td colSpan="4">계정 이력이 없습니다.</td></tr>
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            );
        }
    });

    var StateContent = React.createClass({
        propTypes: {
            stateBook: React.PropTypes.object.isRequired
        },
        render: function () {
            var ATTRS = contentProps.state,
                lang = mainComponent.lang,
                propStateBook = this.props.stateBook,
                existsStateBook = (propStateBook && propStateBook.states && propStateBook.states.length > 0) ? true : false;

            return (
                <div className="tab-content">
                    <div className="tab-pane active">
                        <table className="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>{ATTRS.currentState[lang]}</th>
                                    <th>{ATTRS.targetState[lang]}</th>
                                    <th>{ATTRS.remarks[lang]}</th>
                                    <th>{ATTRS.modifiedTime[lang]}</th>
                                </tr>
                            </thead>
                            <tbody>
                            { existsStateBook ?
                                propStateBook.states.map( function (state, index) {
                                    return (
                                        <tr key={index}>
                                            <td>{state[ATTRS.currentState.name]}</td>
                                            <td>{state[ATTRS.targetState.name]}</td>
                                            <td>{state[ATTRS.remarks.name]}</td>
                                            <td>{castleCommon.Date.parseToString(state[ATTRS.modifiedTime.name])}</td>
                                        </tr>
                                    )
                                })
                                :
                                <tr><td colSpan="4">상태 이력이 없습니다.</td></tr>
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            );
        }
    });

    var MetroContent = React.createClass({
        propTypes: {
            metroBook: React.PropTypes.object.isRequired
        },
        render: function () {
            var ATTRS = contentProps.metro,
                lang = mainComponent.lang,
                propMetroBook = this.props.metroBook,
                existsMetroBook = (propMetroBook && propMetroBook.metros && propMetroBook.metros.length > 0) ? true : false;

            return (
                <div className="tab-content">
                    <div className="tab-pane active">
                        <table className="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>{ATTRS.metroId[lang]}</th>
                                    <th>{ATTRS.metroName[lang]}</th>
                                    <th>{ATTRS.joinTime[lang]}</th>
                                    <th>{ATTRS.withdrawalTime[lang]}</th>
                                    <th>{ATTRS.remarks[lang]}</th>
                                </tr>
                            </thead>
                            <tbody>
                            { existsMetroBook ?
                                propMetroBook.metros.map( function (metro) {
                                    return (
                                        <tr key={metro[ATTRS.metroId.name]}>
                                            <td>{metro[ATTRS.metroId.name]}</td>
                                            <td>{metro[ATTRS.metroName.name]}</td>
                                            <td>{castleCommon.Date.parseToString(metro[ATTRS.joinTime.name])}</td>
                                            <td>{castleCommon.Date.parseToString(metro[ATTRS.withdrawalTime.name])}</td>
                                            <td>{metro[ATTRS.remarks.name]}</td>
                                        </tr>
                                    )
                                })
                                :
                                <tr><td colSpan="5">메트로 가입 이력이 없습니다.</td></tr>
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            );
        }
    });

    Components.Castle.Detail = CastleDetailPage;
})();