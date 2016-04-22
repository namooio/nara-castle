/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.Detail = Components.Castle.Detail || {};

(function () {
    //
    // Import component module
    var mainComponent = Components.Main
        , castleConst = CastleConst
        , castleCommon = CastleCommon;

    // Define Content attributes name
    var contentProps = {
        buttons : {
            save: { KOR: '저장', USA: 'Save'}
            , modify: { KOR: '수정', USA: 'Modify' }
            , remove: { KOR: '삭제', USA: 'Remove'}
            , cancel: { KOR: '취소', USA: 'Cancel'}
        },
        tabs: {
            basic: { name: 'basic', KOR: '기본정보', USA: 'Basic information' }
            , name: { name: 'name', KOR: '이름', USA: 'Name' }
            , phone: { name: 'phone', KOR: '전화번호', USA: 'Phone number' }
            , email: { name: 'email', KOR: '이메일', USA: 'Email' }
            , address: { name: 'address', KOR: '주소', USA: 'Address' }
            , account: { name: 'account', KOR: '계정내역', USA: 'Account history' }
            , state: { name: 'state', KOR: '상태내역', USA: 'State history' }
            , metro: { name: 'metro', KOR: '메트로내역', USA: 'Metro history' }
        },
        basicInfo: {
            castle : {
                id: { name: 'id', KOR: '아이디', USA: 'Id' }
                , name: { name: 'name', KOR: '이름', USA: 'Name' }
                , locale: { name: 'locale', KOR: '지역', USA: 'Locale' }
                , state: { name: 'state', KOR: '상태', USA: 'State' }
                , buildTime: { name: 'buildTime', KOR: '생성일시', USA: 'Build time' }
            },
            castellan: {
                primaryEmail: { name: 'primaryEmail', KOR: '기본 이메일', USA: 'Primary email' }
                , primaryPhone: { name: 'primaryPhone', KOR: '기본 전화번호', USA: 'Primary phone number' }
                , photo: { name: 'photoId', KOR: '사진', USA: 'Photo' }
            }
        },
        name: {
            familyName: { name: 'familyName', KOR: '성', USA: 'Family name' }
            , firstName: { name: 'firstName', KOR: '이름', USA: 'First name' }
            , displayName: { name: 'displayName', KOR: '전체이름', USA: 'Display name' }
            , middleName: { name: 'middleName', KOR: '중간이름', USA: 'Middle name' }
            , langCode: { name: 'langCode', KOR: '언어', USA: 'Language' }
        },
        phone: {

        },
        email: {

        },
        address: {

        },
        account: {

        },
        state: {

        },
        metro: {

        },
    };


    var CastleDetailPage = React.createClass({
        propTypes : {
            contentType: React.PropTypes.string.isRequired
            , id: React.PropTypes.string
        },
        statics : {
            containerStyle: {width: '1100px'}
        },
        getDefaultProps: function () {
            return {
                contentType: 'basic'
            }
        },
        getInitialState: function () {
            return {
                castle: { basicInfo: {}, castellan: {}, nameBook: [] }
                , contentModifiable: false
            };
        },
        componentDidMount: function () {
            console.debug('Exectue detal.jsx CastleDetailPage componentDidMount');
            castleCommon.getJSON(castleConst.CTX + '/api/castle/' + this.props.id).done(function (castleResult) {
                console.debug('Exectue detal.jsx CastleDetailPage componentDidMount callback');
                //var castle = this.state.
                var castleState = this.state.castle;
                castleState.basicInfo = castleResult;
                this.setState({castle: castleState});
                console.dir(this.props);
                console.dir(this.state);
            }.bind(this));
        },
        componentWillReceiveProps: function () {
            var CONTENT_TYPES = contentProps.tabs
                , url
                , callback;

            console.debug('Exectue detal.jsx CastleDetailPage componentWillReceiveProps');
            console.dir(this.props);
            console.dir(this.state);

            switch (this.props.contentType) {
                case CONTENT_TYPES.basic.name:
                    console.debug('basic');
                    url = '/api/castle/' + this.props.id;
                    callback = function (castleResult) {
                        var castleState = this.state.castle;
                        castleState.basicInfo = castleResult;
                        this.setState({castle: castleState});
                    }.bind(this);
                    break;
                case CONTENT_TYPES.name.name:
                    console.debug('name');
                    url = '/api/castle/' + this.props.id + '/namebook';
                    callback = function (nameBookResult) {
                        var castleState = this.state.castle;
                        castleState.nameBook = nameBookResult;
                        this.setState( {castle: castleState}) ;
                        console.debug('Execute detail.jsx NameContent componentDidMount()');
                        console.dir(this.props);
                        console.dir(this.state);
                    };
                    break;
                case CONTENT_TYPES.phone.name:
                    break;
                case CONTENT_TYPES.email.name:
                    break;
                case CONTENT_TYPES.address.name:
                    break;
                case CONTENT_TYPES.account.name:
                    break;
                case CONTENT_TYPES.state.name:
                    break;
                case CONTENT_TYPES.metro.name:
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
                        changeModifiableMode={this.changeModifiableMode} changeViewMode={this.changeViewMode}
                    />
                </article>
            );
        }
    });

    var Tab = React.createClass({
        propTypes: {
            containerStyle: React.PropTypes.object.isRequired
            , contentType: React.PropTypes.string.isRequired
            , castle: React.PropTypes.object
            , modifiable: React.PropTypes.bool.isRequired

            , changeModifiableMode: React.PropTypes.func.isRequired
        },
        render: function () {
            var lang = mainComponent.lang
                , TAB_NAMES = contentProps.tabs
                , currentContentType = this.props.contentType
                , content;

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

                    />
                    break;
                case TAB_NAMES.email.name:
                    content = <EmailContent

                    />
                    break;
                case TAB_NAMES.address.name:
                    content = <AddressContent

                    />
                    break;
                case TAB_NAMES.account.name:
                    content = <AccountContent

                    />
                    break;
                case TAB_NAMES.state.name:
                    content = <StateContent

                    />
                    break;
                case TAB_NAMES.metro.name:
                    content = <MetroContent

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
            var BUTTON_NAMES = contentProps.buttons
                , lang = mainComponent.lang
                , buttonRender;

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
            var lang = mainComponent.lang
                , CASTLE_ATTRS = contentProps.basicInfo.castle
                , CASTELLAN_ATTRS = contentProps.basicInfo.castellan
                , BUTTON_NAMES = contentProps.buttons;

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
                                    <p className="form-control-static">{this.props.castellan[CASTELLAN_ATTRS.primaryEmail.name]}</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{CASTELLAN_ATTRS.primaryPhone[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">{this.props.castellan[CASTELLAN_ATTRS.primaryPhone.name]}</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{CASTELLAN_ATTRS.photo[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">{this.props.castellan[CASTELLAN_ATTRS.photo.name]}</p>
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

        },
        render: function () {
            var ATTRS = contentProps.name
                , lang = mainComponent.lang
                , existsName;

            //if (this.props.nameBook.)


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
                                { this.props.nameBook.map(function (name) {
                                    return (
                                        <tr key={name[ATTRS.familyName.name]}>
                                            <td>{name[ATTRS.familyName.name]}</td>
                                            <td>{name[ATTRS.firstName.name]}</td>
                                            <td>{name[ATTRS.displayName.name]}</td>
                                            <td>{name[ATTRS.middleName.name]}</td>
                                            <td>{name[ATTRS.langCode.name]}</td>
                                        </tr>
                                    );
                                })}
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

        },
        render: function () {
            return (
                <div className="tab-content">
                    <div className="tab-pane active">
                        <h2>This is a Phone content</h2>
                    </div>
                </div>
            );
        }
    });

    var EmailContent = React.createClass({
        propTypes: {

        },
        render: function () {
            return (
                <div className="tab-content">
                    <div className="tab-pane active">
                        <h2>This is an Email content</h2>
                    </div>
                </div>
            );
        }
    });

    var AddressContent = React.createClass({
        propTypes: {

        },
        render: function () {
            return (
                <div className="tab-content">
                    <div className="tab-pane active">
                        <h2>This is an Address content</h2>
                    </div>
                </div>
            );
        }
    });

    var AccountContent = React.createClass({
        propTypes: {

        },
        render: function () {
            return (
                <div className="tab-content">
                    <div className="tab-pane active">
                        <h2>This is an Account content</h2>
                    </div>
                </div>
            );
        }
    });

    var StateContent = React.createClass({
        propTypes: {

        },
        render: function () {
            return (
                <div className="tab-content">
                    <div className="tab-pane active">
                        <h2>This is a State content</h2>
                    </div>
                </div>
            );
        }
    });

    var MetroContent = React.createClass({
        propTypes: {

        },
        render: function () {
            return (
                <div className="tab-content">
                    <div className="tab-pane active">
                        <h2>This is a Metro content</h2>
                    </div>
                </div>
            );
        }
    });

    Components.Castle.Detail = CastleDetailPage;
})();
