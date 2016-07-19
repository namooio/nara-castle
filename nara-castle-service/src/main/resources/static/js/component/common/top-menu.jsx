/**
 * Created by hkkang on 2016-04-05.
 */

import React, { Component, PropTypes } from 'react';
import { Link } from 'react-router';
import { RoleBook } from 'nara-react';
import { Constant } from 'app/common/castle-common';


'use strict';

// Define attributes name
const CastleTopMenuModel = {
    //
    attrs: {
        castles: { KOR: '목록', USA: 'List'}
    }
};


/**
 * Castle 공통 상단 메뉴 컴포넌트
 */
class TopMenu extends Component {
    //
    constructor(props) {
        super(props);

        this.state = {
            roleDisplayable: false
        };

        this.changeLanguageClick = this.changeLanguageClick.bind(this);
    }
    // event
    changeLanguageClick(event) {
        let lang = event.target.dataset.lang;
        this.props.changeLanguage(lang);
    }
    render() {
        //
        let MENUS = CastleTopMenuModel.attrs,
            lang = this.props.getLanguage(),
            displayLang;

        if (lang === 'KOR') {
            displayLang = '한국어';
        }
        else if (lang === 'USA') {
            displayLang = 'English';
        }

        return (
            <nav className="navbar navbar-inverse navbar-static-top">
                <div className="container">
                    <div className="navbar-header">
                        <Link className="navbar-brand" to={`${Constant.PAV_CTX.hash}/`}>Castle</Link>
                    </div>
                    <div className="collapse navbar-collapse">
                        <ui className="nav navbar-nav">
                            <li><Link to={`${Constant.PAV_CTX.hash}/castles`}>{MENUS.castles[lang]}</Link></li>
                        </ui>
                        <ul className="nav navbar-nav navbar-right">
                            <RoleBook init={true}/>
                            <li className="dropdown">
                                <a href="javascript:" className="dropdown-toggle" data-toggle="dropdown"
                                   role="button" aria-expanded="false">
                                    Language ({displayLang}) <span className="caret"/>
                                </a>
                                <ul className="dropdown-menu" role="menu">
                                    <li><a href="javascript:" onClick={this.changeLanguageClick} data-lang="KOR">한국어</a></li>
                                    <li><a href="javascript:" onClick={this.changeLanguageClick} data-lang="USA">English</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        );
    }
}

TopMenu.propTypes = {
    changeLanguage: PropTypes.func.isRequired
};


export default TopMenu;