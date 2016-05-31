/**
 * Created by hkkang on 2016-04-12.
 */

castle.component.List = castle.component.List || { };


( function () {
    //
    'use strict';

    // Import component module
    const NaraAjax = NaraCommon.Ajax,
        NaraDate = NaraCommon.Date,
        Constant = castle.common.Const,
        CastleModel = castle.common.Model,
        MainComponent = castle.component.common.Main;


    // Define content attributes
    const castleListModel = {
        //
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
        //
        statics: {
            FIND_CASTLES_URL: Constant.PAV_CTX_API + '/api/castles'
        },
        getInitialState() {
            return {
                castleCriteria: {},
                castles: []
            };
        },
        componentDidMount() {
            this.requestFindCastles();
        },
        // custom
        changeCriteriaInput() {

        },
        // request
        requestFindCastles(castleCriteria) {
            //
            NaraAjax
                .getJSON(CastleListPage.FIND_CASTLES_URL)
                .done(function (castlesResult) {
                    let notExistsMessage = castleListModel.messages.notExistsMessage,
                        lang = MainComponent.lang;

                    if (!castlesResult || castlesResult.length === 0) {
                        alert(notExistsMessage[lang]);
                        return;
                    }
                    this.setState({castles: castlesResult});
                }.bind(this));
        },
        render() {
            //
            return (
                <article>
                    <Finder
                        criteria={this.state.castleCriteria}
                        change={this.changeCriteriaInput}
                        find={this.requestFindCastles}
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
        // event
        findBtnClick() {
            this.props.find(this.props.criteria);
        },
        inputChange(event) {
            this.props.change({
                name: event.target.value
            });
        },
        render() {
            //
            const ATTRS = castleListModel.finder,
                BUTTON_NAMES = CastleModel.buttons,
                LANG = MainComponent.lang;


            return (
                <div className="container">
                    <div className="panel panel-success">
                        <div className="panel-heading">
                            <h4 className="panel-title">{ATTRS.title[LANG]}</h4>
                        </div>
                        <div className="panel-body">
                            <div className="form-horizontal">
                                <div className="form-group">
                                    <label
                                        className="col-md-1 col-md-offset-6 control-label">{ATTRS.criteria.name[LANG]}</label>
                                    <div className="col-md-3">
                                        <input className="form-control" type="text" value={this.props.criteria.name}
                                               onChange={this.inputChange} placeholder="Castle name"/>
                                    </div>
                                    <div className="col-md-2">
                                        <div className="btn-group">
                                            <button type="button" className="btn btn-default" id="inBtn"
                                                    onClick={this.findBtnClick}>{BUTTON_NAMES.search[LANG]}</button>
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
        render() {
            //
            const ENUMS = CastleModel.enums,
                ATTRS = castleListModel.list,
                MESSAGES = castleListModel.messages,
                LANG = MainComponent.lang;

            let existsCastle = this.props.castles && this.props.castles.length > 0;

            return (
                <div className="container">
                    <div className="panel panel-success">
                        <div className="panel-body">
                            <table className="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th>{ATTRS.header.id[LANG]}</th>
                                    <th>{ATTRS.header.name[LANG]}</th>
                                    <th>{ATTRS.header.locale[LANG]}</th>
                                    <th>{ATTRS.header.primaryEmail[LANG]}</th>
                                    <th>{ATTRS.header.primaryPhone[LANG]}</th>
                                    <th>{ATTRS.header.state[LANG]}</th>
                                    <th>{ATTRS.header.buildTime[LANG]}</th>
                                    <th>{ATTRS.header.detail[LANG]}</th>
                                </tr>
                                </thead>
                                <tbody>
                                { existsCastle === true ?
                                    this.props.castles.map( function (castle, index) {
                                        let existsOwner = castle.owner ? true : false;

                                        return (
                                            <tr key={index}>
                                                <td>{castle.id}</td>
                                                <td>{castle.name}</td>
                                                <td>{ENUMS.locale[castle.locale][LANG]}</td>
                                                <td>{existsOwner ? castle.owner.primaryEmail : null}</td>
                                                <td>{existsOwner ? castle.owner.primaryPhone : null}</td>
                                                <td>{ENUMS.state[castle.state][LANG]}</td>
                                                <td>{NaraDate.parseToString(castle.buildTime)}</td>
                                                <td>
                                                    <a href={Constant.PAV_CTX_HASH + "/castle/basic?&id=" + castle.id}>
                                                        <span className="glyphicon glyphicon-book"/>
                                                    </a>
                                                </td>
                                            </tr>
                                        )
                                    })
                                    : <tr>
                                    <td colSpan="8">{MESSAGES.notExistsMessage[LANG]}</td>
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


    castle.component.List = CastleListPage;
})();