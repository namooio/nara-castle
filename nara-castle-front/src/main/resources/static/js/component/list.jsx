/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.List = Components.Castle.List || { };

( function () {
    //
    'use strict';

    // Import component module
    let commonAjax = NaraCommon.Ajax,
        commonDate = NaraCommon.Date,
        constant = CastleCommon.Const,
        mainComponent = Components.Common.Main,
        castleModel = Components.Castle.Model;

    // Define content attributes
    let castleListModel = {
        finder: {
            title: { KOR: 'Castle 검색', USA: 'Search Castle' },
            criteria: {
                name: { KOR: '이름', USA: 'Name' }
            }
        },
        list: {
            header: {
                id:             { KOR: '아이디',    USA: 'Id' },
                name:           { KOR: '이름',      USA: 'Name' },
                locale:         { KOR: '지역',      USA: 'Locale' },
                primaryEmail:   { KOR: '이메일',    USA: 'Email' },
                primaryPhone:   { KOR: '전화번호',  USA: 'Phone number' },
                state:          { KOR: '상태',      USA: 'State' },
                buildTime:      { KOR: '생성일시',  USA: 'Build time' },
                detail:         { KOR: '상세정보',  USA: 'Detail info' }
            }
        },
        messages: {
            notExistsMessage: { KOR: 'Castle이 존재하지 않습니다.', USA: 'Not exists castle' }
        }
    };

    // Define components
    let CastleListPage = React.createClass({
        statics: {
            FIND_CASTLES_URL: constant.CTX + '/api/castles'
        },
        getInitialState() {
            return {
                castleCriteria: {},
                castles: []
            };
        },
        componentDidMount() {
            this.findCastles();
        },
        changeCriteriaInput() {

        },
        findCastles(castleCriteria) {
            //
            commonAjax
                .getJSON(CastleListPage.FIND_CASTLES_URL)
                .done(function (castlesResult) {
                    let notExistsMessage = castleListModel.messages.notExistsMessage,
                        lang = mainComponent.lang;

                    if (!castlesResult || castlesResult.length === 0) {
                        alert(notExistsMessage[lang]);
                        return;
                    }
                    this.setState({castles: castlesResult});
                }.bind(this));
        },
        render() {
            return (
                <article>
                    <Finder
                        criteria={this.state.castleCriteria}
                        change={this.changeCriteriaInput}
                        find={this.findCastles}
                    />
                    <CastleList castles={this.state.castles}/>
                </article>
            );
        }
    });

    /**
     * Castle 검색 컴포넌트
     */
    let Finder = React.createClass({
        //
        propTypes: {
            criteria: React.PropTypes.object,

            change: React.PropTypes.func.isRequired,
            find: React.PropTypes.func.isRequired
        },
        //findBtnClick: function () {
        findBtnClick() {
            this.props.find(this.props.criteria);
        },
        //inputChange: function (event) {
        inputChange(event) {
            this.props.change({
                name: event.target.value
            });
        },
        //render: function () {
        render() {
            let ATTRS = castleListModel.finder,
                BUTTON_NAMES = castleModel.buttons,
                lang = mainComponent.lang;


            return (
                <div className="container">
                    <div className="panel panel-success">
                        <div className="panel-heading">
                            <h4 className="panel-title">{ATTRS.title[lang]}</h4>
                        </div>
                        <div className="panel-body">
                            <div className="form-horizontal">
                                <div className="form-group">
                                    <label
                                        className="col-md-1 col-md-offset-6 control-label">{ATTRS.criteria.name[lang]}</label>
                                    <div className="col-md-3">
                                        <input className="form-control" type="text" value={this.props.criteria.name}
                                               onChange={this.inputChange} placeholder="Castle name"/>
                                    </div>
                                    <div className="col-md-2">
                                        <div className="btn-group">
                                            <button type="button" className="btn btn-default" id="inBtn"
                                                    onClick={this.findBtnClick}>{BUTTON_NAMES.search[lang]}</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )
        }
    });

    /**
     * Castle list 컴포넌트
     */
    let CastleList = React.createClass({
        //
        propTypes: {
            castles: React.PropTypes.array.isRequired
        },
        //render: function () {
        render() {
            //
            let ENUMS = castleModel.enums,
                ATTRS = castleListModel.list,
                MESSAGES = castleListModel.messages,
                lang = mainComponent.lang,
                existsCastle = this.props.castles && this.props.castles.length > 0;

            return (
                <div className="container">
                    <div className="panel panel-success">
                        <div className="panel-body">
                            <table className="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th>{ATTRS.header.id[lang]}</th>
                                    <th>{ATTRS.header.name[lang]}</th>
                                    <th>{ATTRS.header.locale[lang]}</th>
                                    <th>{ATTRS.header.primaryEmail[lang]}</th>
                                    <th>{ATTRS.header.primaryPhone[lang]}</th>
                                    <th>{ATTRS.header.state[lang]}</th>
                                    <th>{ATTRS.header.buildTime[lang]}</th>
                                    <th>{ATTRS.header.detail[lang]}</th>
                                </tr>
                                </thead>
                                <tbody>
                                { existsCastle === true ?
                                    this.props.castles.map(function (castle) {
                                        return (
                                            <tr key={castle.id}>
                                                <td>{castle.id}</td>
                                                <td>{castle.name}</td>
                                                <td>{ENUMS.locale[castle.locale][lang]}</td>
                                                <td>{castle.castellan ? castle.castellan.primaryEmail : null}</td>
                                                <td>{castle.castellan ? castle.castellan.primaryPhone : null}</td>
                                                <td>{ENUMS.state[castle.state][lang]}</td>
                                                <td>{commonDate.parseToString(castle.buildTime)}</td>
                                                <td><a href={"#/castle/basic?&id=" + castle.id}><span
                                                    className="glyphicon glyphicon-book"/></a></td>
                                            </tr>
                                        )
                                    })
                                    : <tr>
                                    <td colSpan="6">{MESSAGES.notExistsMessage[lang]}</td>
                                </tr>
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            );
        }
    });


    Components.Castle.List = CastleListPage;
})();