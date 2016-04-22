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

    // Define Content properties name
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
            id: { name: 'id', KOR: '아이디', USA: 'Id' }
            , name: { name: 'name', KOR: '이름', USA: 'Name' }
            , state: { name: 'state', KOR: '상태', USA: 'State' }
            , locale: { name: 'locale', KOR: '지역', USA: 'Locale' }
            , primaryEmail: { name: 'primaryEmail', KOR: '기본 이메일', USA: 'Primary email' }
            , primaryPhone: { KOR: '기본 전화번호', USA: 'Primary phone number' }
            , photo: { KOR: '사진', USA: 'Photo' }
            , buildTime: { KOR: '생성일시', USA: 'Build time'}
        },
        name: {

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
        statics : {
            containerStyle: {width: '1000px'}
        },
        getInitialState: function () {
            return {
                contentModifiable: false
            };
        },
        componentDidMount: function (obj) {
            console.debug('Execute detal.jsx CastleDetailPage componentDidMount()');

            castleCommon.getJSON(castleConst.CTX + '/api/castle/' + this.props.id).done(function (castleResult) {
                this.setState({castle: castleResult});
            }.bind(this));
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
                        contentType={this.props.tab}
                        modifiable={this.state.contentModifiable}
                        changeModifiableMode={this.changeModifiableMode} changeViewMode={this.changeViewMode}
                        tabType={this.props.tab}
                        castle={this.state.castle}
                    />
                </article>
            );
        }
    });

    var Tab = React.createClass({
        render: function () {
            var lang = mainComponent.MainPage.lang
                , TAB_NAMES = contentProps.tabs
                , currentContentType = this.props.tabType;

            return (
                <div className="container" style={this.props.containerStyle}>
                    <div className="panel panel-success">
                        <div className="panel-body">
                            <ul className="nav nav-tabs">
                                {$.map(TAB_NAMES, function (tabAttr) {
                                    return (
                                        <li key={tabAttr.name} className={(currentContentType === tabAttr.name) ? "active" : null}>
                                            <a href={"#/castle/detail?tab=" + tabAttr.name}>{tabAttr[lang]}</a>
                                        </li>
                                    );
                                })}
                            </ul>
                            <BasicInfoContent
                                modifiable={this.props.modifiable}
                                changeModifiableMode={this.props.changeModifiableMode}
                                changeViewMode={this.props.changeViewMode}
                                castle={this.props.castle}
                            />
                        </div>
                    </div>
                </div>
            );
        }
    });

    var BasicInfoContent = React.createClass({
        modifiableModeBtnClick: function () {
            this.props.changeModifiableMode();
        },
        cancelBtnClick: function () {
            this.props.changeViewMode();
        },
        render: function () {
            var lang = mainComponent.MainPage.lang
                , ATTR_NAMES = contentProps.basicInfo
                ,BUTTON_NAMES = contentProps.buttons;

            return (
                <div className="tab-content">
                    <div className="tab-pane active">
                        <form className="form-horizontal">
                            <div className="form-group"><p>&nbsp;</p></div>
                            {$.map(ATTR_NAMES, function (attr) {
                                return (
                                    <div key={attr[lang]} className="form-group">
                                        <label className="col-lg-3 col-lg-offset-1 control-label">{attr[lang]}</label>
                                        <div className="col-lg-7">
                                            <p className="form-control-static">{this.props.castle[attr]}</p>
                                        </div>
                                    </div>
                                );
                            }.bind(this))}
                            <div className="form-group"><p>&nbsp;</p></div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTR_NAMES.id[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">아이디</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTR_NAMES.name[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">아이디</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTR_NAMES.state[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">아이디</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTR_NAMES.locale[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">아이디</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTR_NAMES.primaryEmail[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">아이디</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTR_NAMES.primaryPhone[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">아이디</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTR_NAMES.photo[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">아이디</p>
                                </div>
                            </div>
                            <div className="form-group">
                                <label className="col-lg-3 col-lg-offset-1 control-label">{ATTR_NAMES.buildTime[lang]}</label>
                                <div className="col-lg-7">
                                    <p className="form-control-static">아이디</p>
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

    Components.Castle.Detail = CastleDetailPage;
})();
