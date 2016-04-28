/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.Basic = Components.Castle.Basic || { };

( function () {
    //
    'use strict';


    // Import component module
    var commonAjax = NaraCommon.Ajax,
        commonObject = NaraCommon.Object,
        commonDate = NaraCommon.Date,
        constant = CastleCommon.Const,
        mainComponent = Components.Main,
        castleModel = Components.Castle.Model;


    // Define Content attributes name
    var castleBasicModel = {
        attrs: {
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
        messages: {
            notFoundCastle: { KOR: '해당 Id의 Castle 정보가 없습니다. -> Id: {id}', USA: 'Not found the Castle -> Id: {id}'}
        }

    };

    // Define components
    var CastleDetailPage = React.createClass({
        //
        statics : {
            FIND_CASTLE_URL: constant.CTX + '/api/castle/{id}'
        },
        propTypes : {
            id: React.PropTypes.string
        },
        getInitialState: function () {
            return {
                basicInfo: { castellan: {} },
                contentModifiable: false
            };
        },
        componentDidMount: function () {
            //
            this.requestCastle(this.props);
        },
        changeModifiableMode: function () {
            this.setState({contentModifiable: true});
        },
        changeViewMode: function () {
            this.setState({contentModifiable: false});
        },
        requestCastle: function (props) {
            commonAjax
                .getJSON(CastleDetailPage.FIND_CASTLE_URL.replace('{id}', props.id))
                .done( function (castleResult) {
                    if (commonObject.isEmpty(castleResult)) {
                        var MESSAGES = castleBasicModel.messages,
                            lang = mainComponent.lang;

                        alert(MESSAGES.notFoundCastle[lang].replace('{id}', props.id));
                        return;
                    }
                    this.setState({ basicInfo: castleResult });
                }.bind(this));
        },
        render: function () {
            return (
                <Tab
                    castleId={this.props.id}
                    basicInfo={this.state.basicInfo}
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
            basicInfo: React.PropTypes.object,
            modifiable: React.PropTypes.bool.isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired
        },
        render: function () {
            var TAB_NAMES = castleModel.tabs,
                lang = mainComponent.lang;

            return (
                <div className="container">
                    <div className="panel panel-success">
                        <div className="panel-body">
                            <ul className="nav nav-tabs">
                                <li className="active">
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
                                    <BasicInfoContent
                                        basicInfo={this.props.basicInfo}
                                        modifiable={this.props.modifiable}
                                        changeModifiableMode={this.props.changeModifiableMode}
                                        changeViewMode={this.props.changeViewMode}
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

    var BasicInfoContent = React.createClass({
        propTypes: {
            basicInfo: React.PropTypes.shape({
                castellan: React.PropTypes.object.isRequired
            }).isRequired,
            modifiable: React.PropTypes.bool.isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired
        },
        modifiableModeBtnClick: function () {
            this.props.changeModifiableMode();
        },
        render: function () {
            var ENUMS = castleModel.enum,
                BUTTON_NAMES = castleModel.buttons,
                CASTLE_ATTRS = castleBasicModel.attrs,
                lang = mainComponent.lang,
                propBasicInfo = this.props.basicInfo,
                existsCastle = true;

            if (commonObject.isEmpty(propBasicInfo) || commonObject.isEmpty(propBasicInfo.castellan)) {
                existsCastle = false;
            }

            return (
                <form className="form-horizontal">
                    <div className="form-group"><p>&nbsp;</p></div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.id[lang]}</label>
                        <div className="col-lg-7">
                            <p className="form-control-static">{propBasicInfo[CASTLE_ATTRS.id.name]}</p>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.name[lang]}</label>
                        <div className="col-lg-7">
                            <p className="form-control-static">{propBasicInfo[CASTLE_ATTRS.name.name]}</p>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.locale[lang]}</label>
                        <div className="col-lg-7">
                            <p className="form-control-static">{existsCastle ? ENUMS.locale[propBasicInfo[CASTLE_ATTRS.locale.name]][lang] : null}</p>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.state[lang]}</label>
                        <div className="col-lg-7">
                            <p className="form-control-static">{propBasicInfo[CASTLE_ATTRS.state.name]}</p>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.castellan.primaryEmail[lang]}</label>
                        <div className="col-lg-7">
                            <p className="form-control-static">{propBasicInfo.castellan[CASTLE_ATTRS.castellan.primaryEmail.name]}</p>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.castellan.primaryPhone[lang]}</label>
                        <div className="col-lg-7">
                            <p className="form-control-static">{propBasicInfo.castellan[CASTLE_ATTRS.castellan.primaryPhone.name]}</p>
                        </div>
                    </div>
                    { this.props.modifiable ?
                        <div className="form-group">
                            <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.castellan.photo[lang]}</label>
                            <div className="col-lg-7">
                                <input type="text" className="form-control" value={propBasicInfo.castellan[CASTLE_ATTRS.castellan.photo.name]}/>
                            </div>
                        </div>
                        :
                        <div className="form-group">
                            <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.castellan.photo[lang]}</label>
                            <div className="col-lg-7">
                                <p className="form-control-static">{propBasicInfo.castellan[CASTLE_ATTRS.castellan.photo.name]}</p>
                            </div>
                        </div>
                    }
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.buildTime[lang]}</label>
                        <div className="col-lg-7">
                            <p className="form-control-static">{commonDate.parseToString(propBasicInfo[CASTLE_ATTRS.buildTime.name])}</p>
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
            );
        }
    });

    var BasicInfoModifiableContent = React.createClass({
        propTypes: {
            basicInfo: React.PropTypes.object
            , castellan: React.PropTypes.object

            , changeViewMode: React.PropTypes.func.isRequired
        },
        cancelBtnClick: function () {
            this.props.changeViewMode();
        },
        render: function () {
            var lang = mainComponent.lang,
                CASTLE_ATTRS = contentProps.basicInfo,
                CASTELLAN_ATTRS = contentProps.basicInfo.castellan,
                ENUMS = contentProps.enum,
                BUTTON_NAMES = contentProps.buttons,
                propBasicInfo = this.props.basicInfo;

            if (commonObject.isEmpty(propBasicInfo) || commonObject.isEmpty(propBasicInfo.castellan)) {
                return (<p>Castle 정보가 없습니다.</p>);
            }

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
                                    <p className="form-control-static">{ENUMS.locale[this.props.basicInfo[CASTLE_ATTRS.locale.name]][lang]}</p>
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
                            { this.props.modifiable ?
                                <div className="form-group">
                                    <label className="col-lg-3 col-lg-offset-1 control-label">{CASTELLAN_ATTRS.photo[lang]}</label>
                                    <div className="col-lg-7">
                                        <input type="text" className="form-control" value={this.props.basicInfo.castellan[CASTELLAN_ATTRS.photo.name]}/>
                                    </div>
                                </div>
                                :
                                <div className="form-group">
                                    <label className="col-lg-3 col-lg-offset-1 control-label">{CASTELLAN_ATTRS.photo[lang]}</label>
                                    <div className="col-lg-7">
                                        <p className="form-control-static">{this.props.basicInfo.castellan[CASTELLAN_ATTRS.photo.name]}</p>
                                    </div>
                                </div>
                            }
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{CASTLE_ATTRS.buildTime[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">{commonDate.parseToString(this.props.basicInfo[CASTLE_ATTRS.buildTime.name])}</p>
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


    Components.Castle.Basic = CastleDetailPage;
})();