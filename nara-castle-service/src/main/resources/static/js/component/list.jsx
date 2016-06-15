/**
 * Created by hkkang on 2016-04-12.
 */

import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';

import { Ajax as NaraAjax, Date as NaraDate } from 'app/lib/nara-common';
import { Constant } from 'app/common/castle-common';
import CastleModel from 'app/common/castle-model';
import MainComponent from 'app/component/common/main.jsx';


'use strict';

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
class CastleListPage extends Component {
    //
    constructor(props) {
        super(props);

        this.state = {
            castleCriteria: {},
            castles: []
        };
    }
    componentDidMount() {
        this.requestFindCastles();
    }
    // custom
    changeCriteriaInput() {

    }
    // request
    requestFindCastles(castleCriteria) {
        //
        NaraAjax
            .getJSON(CastleListPage.url.FIND_CASTLES)
            .done(function (castlesResult) {
                let notExistsMessage = castleListModel.messages.notExistsMessage,
                    lang = MainComponent.lang;

                if (!castlesResult || castlesResult.length === 0) {
                    alert(notExistsMessage[lang]);
                    return;
                }
                this.setState({ castles: castlesResult });
            }.bind(this));
    }
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
}

CastleListPage.url = {
    //
    FIND_CASTLES : `${Constant.PAV_CTX_API}/front/castles`
};


/**
 * Castle 검색 컴포넌트
 */
class Finder extends Component {
    //
    constructor(props) {
        super(props);

        this.findBtnClick = this.findBtnClick.bind(this);
        this.inputChange = this.inputChange.bind(this);
    }
    // event
    findBtnClick() {
        this.props.find(this.props.criteria);
    }
    inputChange(event) {
        this.props.change({
            name: event.target.value
        });
    }
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
}

Finder.propTypes = {
    //
    criteria: PropTypes.object,

    change: PropTypes.func.isRequired,
    find: PropTypes.func.isRequired
};

/**
 * Castle list 컴포넌트
 */
class CastleList extends Component {
    //
    constructor(props) {
        super(props);

        this.renderList = this.renderList.bind(this);
    }
    //
    renderList() {
        //
        let existsCastle = this.props.castles.length > 0,
            result;


        if (existsCastle === true) {
            result = this.props.castles.map( function (castle, index) {

            });
        }
        else {

        }
        return result;
    }
    render() {
        //
        const ENUMS = CastleModel.enums,
            ATTRS = castleListModel.list,
            MESSAGES = castleListModel.messages,
            LANG = MainComponent.lang;

        let existsCastle = this.props.castles.length > 0;

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
                                                <Link to={`${Constant.PAV_CTX_HASH}/castle/${castle.id}/basic`}><span className="glyphicon glyphicon-book"/></Link>
                                            </td>
                                        </tr>
                                    )
                                })
                                :
                                <tr>
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
}

CastleList.propTypes = {
    castles: PropTypes.array.isRequired
};

export default CastleListPage
