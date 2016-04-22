/**
 * Created by hkkang on 2016-04-12.
 */
Components.Castle.List = Components.Castle.List || {};

(function () {
    //
    // Import component module
    var castleConst = CastleConst
        , castleCommon = CastleCommon
        , mainComponent = Components.Main;

    // Define content attributes
    var contentProps = {
        finder: {
            title: { KOR: 'Castle 검색', USA: 'Search Castle'}
            , criteria: {
                name: { KOR: '이름', USA: 'Name' }
            },
            notExistsMessage: { KOR: 'Castle이 존재하지 않습니다.', USA: 'No such castle' }
        },
        list: {
            header: {
                id:             { KOR: '아이디',    USA: 'Id' }
                , name:         { KOR: '이름',      USA: 'Name' }
                , locale:       { KOR: '지역',      USA: 'Locale' }
                , primaryEmail: { KOR: '이메일',    USA: 'Email' }
                , primaryPhone: { KOR: '전화번호',  USA: 'Phone number' }
                , state:        { KOR: '상태',      USA: 'State' }
                , buildTime:    { KOR: '생성일시',  USA: 'Build time' }
                , detail:       { KOR: '상세정보',  USA: 'Detail info' }
            }
        },
        buttons : {
            search: { KOR: '검색', USA: 'Find' }
        }
    };

    // Define components
    var CastleListPage = React.createClass({
        statics : {
            containerStyle: {width: '1100px'}
        },
        getInitialState: function () {
            return {
                castleCriteria: {}
                , castles: []
            };
        },
        componentDidMount: function () {
            this.findCastles();
        },
        changeCriteriaInput : function () {

        },
        findCastles: function (castleCriteria) {
            //
            castleCommon.getJSON(castleConst.CTX + '/api/castle').done(function (castlesResult) {
                var notExistsMessage = contentProps.finder.notExistsMessage
                    , lang = mainComponent.lang;

                if (!castlesResult || castlesResult.length === 0) {
                    alert(notExistsMessage[lang]);
                    return;
                }
                this.setState({castles: castlesResult});
            }.bind(this));
        },
        render: function () {
            return (
                <article>
                    <Finder containerStyle={CastleListPage.containerStyle} criteria={this.state.castleCriteria} change={this.changeCriteriaInput} find={this.findCastles}/>
                    <CastleList containerStyle={CastleListPage.containerStyle} castles={this.state.castles}/>
                </article>
            );
        }
    });

    /**
     * Castle 검색 컴포넌트
     */
    var Finder = React.createClass({
        propTypes : {
            containerStyle : React.PropTypes.object.isRequired
            , criteria: React.PropTypes.object

            , change : React.PropTypes.func.isRequired
            , find : React.PropTypes.func.isRequired
        },
        findBtnClick: function () {
            this.props.find(this.props.criteria);
        },
        inputChange: function (event) {
            this.props.change({
                name: event.target.value
            });
        },
        render: function () {
            var lang = mainComponent.lang
                , ATTRS = contentProps.finder
                , BUTTON_NAMES = contentProps.buttons;


            return (
                <div className="container" style={this.props.containerStyle}>
                    <div className="panel panel-success">
                        <div className="panel-heading">
                            <h4 className="panel-title">{ATTRS.title[lang]}</h4>
                        </div>
                        <div className="panel-body">
                            <div className="form-horizontal">
                                <div className="form-group">
                                    <label className="col-md-1 col-md-offset-6 control-label">{ATTRS.criteria.name[lang]}</label>
                                    <div className="col-md-3">
                                        <input className="form-control" type="text" value={this.props.criteria.name} onChange={this.inputChange} placeholder="Castle name" />
                                    </div>
                                    <div className="col-md-2">
                                        <div className="btn-group">
                                            <button type="button" className="btn btn-default" id="inBtn" onClick={this.findBtnClick}>{BUTTON_NAMES.search[lang]}</button>
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
    var CastleList = React.createClass({
        propTypes : {
            containerStyle : React.PropTypes.object.isRequired,
            castles : React.PropTypes.array.isRequired
        },
        render: function () {
            //
            var ATTRS = contentProps.list
                , lang = mainComponent.lang
                , existsCastle;

            if (!this.props.castles || this.props.castles.length === 0) {
                existsCastle = false;
            }
            else {
                existsCastle = true;
            }

            return (
                <div className="container" style={this.props.containerStyle}>
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
                                            return  (
                                                <tr key={castle.id}>
                                                    <td>{castle.id}</td>
                                                    <td>{castle.name}</td>
                                                    <td>{castle.locale}</td>
                                                    <td>{castle.castellan.primaryEmail}</td>
                                                    <td>{castle.castellan.primaryPhone}</td>
                                                    <td>{castle.state}</td>
                                                    <td>{castleCommon.Date.parseToString(castle.buildTime)}</td>
                                                    <td><a href={"#/castle/detail?&id=" + castle.id}><span className="glyphicon glyphicon-book"/></a></td>
                                                </tr>
                                            )
                                        })
                                        : <tr><td colspan="6">검색된 Castle이 없습니다.</td></tr>
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

