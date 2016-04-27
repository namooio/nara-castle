/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.Basic = Components.Castle.Basic || {};

(function () {
    //
    'use strict';

    // Import component module
    var castleCommon = CastleCommon,
        castleConst = CastleConst,
        mainComponent = Components.Main,
        castleModel = Components.Castle.Model;


    // Define Content attributes name

    // Define components
    var CastleDetailPage = React.createClass({
        //
        statics : {
            FIND_CASTLE_URL: castleConst.CTX + '/api/castle/'
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
        componentWillReceiveProps: function (props) {
            //
            this.requestCastle(props);
        },
        changeModifiableMode: function () {
            this.setState({contentModifiable: true});
        },
        changeViewMode: function () {
            this.setState({contentModifiable: false});
        },
        requestCastle: function (props) {
            castleCommon.getJSON(CastleDetailPage.FIND_CASTLE_URL + props.id).done( function (castleResult) {
                this.setState({ basicInfo: castleResult });
            }.bind(this));
        },
        render: function () {
            return (
                <Tab
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
                                    <a href={"#/castle/basic?contentType=" + TAB_NAMES.basic.name + '&id=' + this.props.basicInfo.id}>{TAB_NAMES.basic[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/contact/name-book?contentType=" + TAB_NAMES.name.name + '&id=' + this.props.basicInfo.id}>{TAB_NAMES.name[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/contact/phone-book?contentType=" + TAB_NAMES.phone.name + '&id=' + this.props.basicInfo.id}>{TAB_NAMES.phone[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/contact/email-book?contentType=" + TAB_NAMES.email.name + '&id=' + this.props.basicInfo.id}>{TAB_NAMES.email[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/contact/address-book?contentType=" + TAB_NAMES.address.name + '&id=' + this.props.basicInfo.id}>{TAB_NAMES.address[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/history/account-book?contentType=" + TAB_NAMES.account.name + '&id=' + this.props.basicInfo.id}>{TAB_NAMES.account[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/history/state-book?contentType=" + TAB_NAMES.state.name + '&id=' + this.props.basicInfo.id}>{TAB_NAMES.state[lang]}</a>
                                </li>
                                <li>
                                    <a href={"#/castle/history/metro-book?contentType=" + TAB_NAMES.metro.name + '&id=' + this.props.basicInfo.id}>{TAB_NAMES.metro[lang]}</a>
                                </li>
                            </ul>
                            <div className="tab-content">
                                <div className="tab-pane active">
                                    <BasicInfoContent
                                        basicInfo={this.props.basicInfo}
                                        castellan={this.props.basicInfo.castellan}
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
            basicInfo: React.PropTypes.object
            , castellan: React.PropTypes.object
            , modifiable: React.PropTypes.bool.isRequired

            , changeModifiableMode: React.PropTypes.func.isRequired
        },
        getDefaultProps: function () {
            return {
                
            };
        },
        modifiableModeBtnClick: function () {
            this.props.changeModifiableMode();
        },
        render: function () {
            var CASTLE_ATTRS = castleModel.basicInfo,
                CASTELLAN_ATTRS = castleModel.basicInfo.castellan,
                ENUMS = castleModel.enum,
                BUTTON_NAMES = castleModel.buttons,
                lang = mainComponent.lang,
                propBasicInfo = this.props.basicInfo;

            if (castleCommon.Object.isEmpty(propBasicInfo) || castleCommon.Object.isEmpty(propBasicInfo.castellan)) {
                return (<p>Castle 정보가 없습니다.</p>);
            }

            return (
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

            if (castleCommon.Object.isEmpty(propBasicInfo) || castleCommon.Object.isEmpty(propBasicInfo.castellan)) {
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


    Components.Castle.Basic = CastleDetailPage;
})();