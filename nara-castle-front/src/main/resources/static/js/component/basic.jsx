/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.Basic = Components.Castle.Basic || {};

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
            notFoundCastle: { KOR: '해당 Id의 Castle 정보가 없습니다. -> Id: {id}', USA: 'Not found the Castle -> Id: {id}' },
            completeModify: { KOR: 'Basic이 수정 되었습니다.',    USA: 'Modify has been completed.' },
            confirmRemove:  { KOR: '삭제 시 Contact와 History 관련 모든 정보가 삭제되며 복구할 수 없습니다. 정말 삭제하시겠습니까?', USA: 'Are you really remove castle?' }
        }
    };

    var BasicContent = React.createClass({
        //
        statics: {
            FIND_CASTLE_URL: constant.CTX + '/api/castles/{id}',
            FIND_CASTELLAN_URL: constant.CTX + '/api/castellans/{id}',
            MODIFY_NAME_URL: constant.CTX + '/api/castles/{id}/name',
            MODIFY_LOCALE_URL: constant.CTX + '/api/castles/{id}/locale',
            SUSPEND_CASTLE_URL: constant.CTX + '/api/castles/{id}/suspend',
            REOPEN_CASTLE_URL: constant.CTX + '/api/castles/{id}/reopen'
        },
        propTypes: {
            castleId: React.PropTypes.string.isRequired,
            castle: React.PropTypes.shape({
                basic: React.PropTypes.shape({
                    castellan: React.PropTypes.object.isRequired
                }).isRequired
            }).isRequired,
            modifiable: React.PropTypes.bool.isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired,
            changeViewMode: React.PropTypes.func.isRequired,
            setCastle: React.PropTypes.func.isRequired
        },
        componentDidMount: function () {
            this.requestFindCastle(this.props.castleId);
        },
        setBasic: function (castleBasic, castellan) {
            //
            var castle = this.props.castle;

            castle.basic = castleBasic;
            castle.basic.castellan = castellan || castle.basic.castellan;

            this.props.setCastle(castle);
        },
        requestFindCastle: function (castleId) {
            //
            var urlBuilder = commonAjax.createUrlBuilder();

            urlBuilder.addUrl(BasicContent.FIND_CASTLE_URL.replace('{id}', castleId));
            urlBuilder.addUrl(BasicContent.FIND_CASTELLAN_URL.replace('{id}', castleId));

            commonAjax.getJSONs(urlBuilder.build(), function (castleResult, castellanResult) {
                //
                if (commonObject.isEmpty(castleResult)) {
                    var MESSAGES = castleBasicModel.messages,
                        lang = mainComponent.lang;

                    alert(MESSAGES.notFoundCastle[lang].replace('{id}', castleId));
                    return;
                }
                this.setBasic(castleResult, castellanResult);
            }.bind(this));
        },
        requestModifyBasic: function (castleBasic) {
            var urlBuilder = commonAjax.createUrlBuilder();

            urlBuilder.addUrlAndParam(BasicContent.MODIFY_NAME_URL.replace('{id}', this.props.castleId), castleBasic.name);
            urlBuilder.addUrlAndParam(BasicContent.MODIFY_LOCALE_URL.replace('{id}', this.props.castleId), castleBasic.locale);

            if (castleBasic.state === castleModel.enums.state.Open.name) {
                urlBuilder.addUrlAndParam(BasicContent.REOPEN_CASTLE_URL.replace('{id}', this.props.castleId), 'remaraks');
            }
            else if (castleBasic.state === castleModel.enums.state.Suspended.name) {
                urlBuilder.addUrlAndParam(BasicContent.SUSPEND_CASTLE_URL.replace('{id}', this.props.castleId), 'remaraks');
            }

            console.debug('저장');
            console.dir(castleBasic);

            commonAjax.putJSONs(urlBuilder.build(), function () {
                this.setBasic(castleBasic);

                var lang = mainComponent.lang;
                alert(castleBasicModel.messages.completeModify[lang]);
            }.bind(this));
        },
        render : function () {
            //
            return (
                this.props.modifiable ?
                    <BasicModifiableContent
                        basicInfo={this.props.castle.basic}
                        modifiable={this.props.modifiable}
                        changeViewMode={this.props.changeViewMode}
                        setBasic={this.setBasic}
                        modifyBasic={this.requestModifyBasic}
                    />
                    :
                    <BasicViewContent
                        basicInfo={this.props.castle.basic}
                        modifiable={this.props.modifiable}
                        changeModifiableMode={this.props.changeModifiableMode}
                    />
            );
        }
    });


    var BasicViewContent = React.createClass({
        //
        propTypes: {
            basicInfo: React.PropTypes.shape({
                castellan: React.PropTypes.object.isRequired
            }).isRequired,

            changeModifiableMode: React.PropTypes.func.isRequired
        },
        modifiableModeBtnClick: function () {
            this.props.changeModifiableMode();
        },
        removeBtnClick: function () {
            var lang = mainComponent.lang;
            if (confirm(castleBasicModel.messages.confirmRemove[lang])) {
                // TODO : Castle 삭제
            }
        },
        render: function () {
            //
            var ENUMS = castleModel.enums,
                BUTTON_NAMES = castleModel.buttons,
                ATTRS = castleBasicModel.attrs,
                lang = mainComponent.lang,
                propBasicInfo = this.props.basicInfo,
                existsCastle = !commonObject.isEmpty(propBasicInfo) && propBasicInfo[ATTRS.id.name];


            return (
                <form className="form-horizontal">
                    <div className="form-group"><p>&nbsp;</p></div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.id[lang]}</label>
                        <div className="col-lg-5">
                            <p className="form-control-static">{propBasicInfo[ATTRS.id.name]}</p>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.name[lang]}</label>
                        <div className="col-lg-5">
                            <p className="form-control-static">{propBasicInfo[ATTRS.name.name]}</p>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.locale[lang]}</label>
                        <div className="col-lg-5">
                            <p className="form-control-static">{existsCastle ? ENUMS.locale[propBasicInfo[ATTRS.locale.name]][lang] : null}</p>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.state[lang]}</label>
                        <div className="col-lg-5">
                            <p className="form-control-static">{existsCastle ? ENUMS.state[propBasicInfo[ATTRS.state.name]][lang] : null}</p>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.castellan.primaryEmail[lang]}</label>
                        <div className="col-lg-5">
                            <p className="form-control-static">{propBasicInfo.castellan[ATTRS.castellan.primaryEmail.name]}</p>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.castellan.primaryPhone[lang]}</label>
                        <div className="col-lg-5">
                            <p className="form-control-static">{propBasicInfo.castellan[ATTRS.castellan.primaryPhone.name]}</p>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.castellan.photo[lang]}</label>
                        <div className="col-lg-5">
                            <p className="form-control-static">{propBasicInfo.castellan[ATTRS.castellan.photo.name]}</p>
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.buildTime[lang]}</label>
                        <div className="col-lg-5">
                            <p className="form-control-static">{commonDate.parseToString(propBasicInfo[ATTRS.buildTime.name])}</p>
                        </div>
                    </div>

                    <div className="btn-toolbar pull-right">
                        <button type="button" className="btn-group btn btn-default" onClick={this.modifiableModeBtnClick}>{BUTTON_NAMES.modify[lang]}</button>
                        <button type="button" className="btn-group btn btn-danger" onClick={this.removeBtnClick}>{BUTTON_NAMES.remove[lang]}</button>
                    </div>
                </form>
            );
        }
    });

    var BasicModifiableContent = React.createClass({
        //
        propTypes: {
            basicInfo: React.PropTypes.shape({
                castellan: React.PropTypes.object.isRequired
            }).isRequired,

            changeViewMode: React.PropTypes.func.isRequired,
            setBasic: React.PropTypes.func.isRequired,
            modifyBasic: React.PropTypes.func.isRequired
        },
        getInitialState: function () {
            return {
                willModifyBasic: {
                    castellan: {}
                }
            };
        },
        componentDidMount: function () {
            var basicInfo = commonObject.deepCopy(this.props.basicInfo);
            this.setState({ willModifyBasic: basicInfo });
        },
        nameChange: function (event) {
            this.setWillModifyBasicState(castleBasicModel.attrs.name.name, event.target.value);
        },
        localeChange: function (event) {
            this.setWillModifyBasicState(castleBasicModel.attrs.locale.name, event.target.value);
        },
        stateChange: function (event) {
            this.setWillModifyBasicState(castleBasicModel.attrs.state.name, event.target.value);
        },
        saveBtnClick: function () {
            this.props.modifyBasic(this.state.willModifyBasic);
        },
        cancelModificationBtnClick: function () {
            this.props.changeViewMode();
        },
        setWillModifyBasicState: function (propertyName, value) {
            var basic = this.state.willModifyBasic;

            basic[propertyName] = value;
            this.setState({ willModifyBasic: basic });
        },
        render: function () {
            //
            var ENUMS = castleModel.enums,
                BUTTON_NAMES = castleModel.buttons,
                ATTRS = castleBasicModel.attrs,
                lang = mainComponent.lang,
                propBasicInfo = this.state.willModifyBasic;


            return (
                <div className="tab-content">
                    <div className="tab-pane active">
                        <form className="form-horizontal">
                            <div className="form-group"><p>&nbsp;</p></div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.id[lang]}</label>
                                <div className="col-lg-5">
                                    <p className="form-control-static">{propBasicInfo[ATTRS.id.name]}</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.name[lang]}</label>
                                <div className="col-lg-5">
                                    <input type="text" className="form-control" onChange={this.nameChange} value={propBasicInfo[ATTRS.name.name]}/>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.locale[lang]}</label>
                                <div className="col-lg-5">
                                    <select className="form-control" onChange={this.localeChange} value={ propBasicInfo[ATTRS.locale.name] }>
                                        <option>{ ATTRS.locale[lang] }</option>
                                        { Object.keys(ENUMS.locale).map( function (localeKey, index) {
                                            var LOCALE_ENUM = ENUMS.locale[localeKey];

                                            return (
                                                <option key={index} value={LOCALE_ENUM.name}>{LOCALE_ENUM[lang]}</option>
                                            );
                                        })}
                                    </select>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.state[lang]}</label>
                                <div className="col-lg-5">
                                    <select className="form-control" onChange={this.stateChange} value={propBasicInfo[ATTRS.state.name]}>
                                        <option>{ ATTRS.state[lang] }</option>
                                        { Object.keys(ENUMS.state).map( function (stateKey, index) {
                                            var STATE_ENUM = ENUMS.state[stateKey];

                                            return (
                                                <option key={index} value={STATE_ENUM.name}>{STATE_ENUM[lang]}</option>
                                            );
                                        })}
                                    </select>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.castellan.primaryEmail[lang]}</label>
                                <div className="col-lg-5">
                                    <p className="form-control-static">{propBasicInfo.castellan[ATTRS.castellan.primaryEmail.name]}</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.castellan.primaryPhone[lang]}</label>
                                <div className="col-lg-5">
                                    <p className="form-control-static">{propBasicInfo.castellan[ATTRS.castellan.primaryPhone.name]}</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.castellan.photo[lang]}</label>
                                <div className="col-lg-5">
                                    <input type="text" className="form-control" value={propBasicInfo.castellan[ATTRS.castellan.photo.name]}/>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTRS.buildTime[lang]}</label>
                                <div className="col-lg-5">
                                    <p className="form-control-static">{commonDate.parseToString(propBasicInfo[ATTRS.buildTime.name])}</p>
                                </div>
                            </div>

                            <div className="btn-toolbar pull-right">
                                <button type="button" className="btn-group btn btn-primary" onClick={this.saveBtnClick}>{BUTTON_NAMES.save[lang]}</button>
                                <button type="button" className="btn-group btn btn-default" onClick={this.cancelModificationBtnClick}>{BUTTON_NAMES.cancel[lang]}</button>
                            </div>
                        </form>
                    </div>
                </div>
            );
        }
    });


    Components.Castle.Basic = BasicContent;
})();