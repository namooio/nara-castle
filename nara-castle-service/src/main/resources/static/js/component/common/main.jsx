/**
 * Created by hkkang on 2016-04-05.
 */

import React, { Component } from 'react';
import TopMenu from 'app/component/common/top-menu.jsx';
import RoleBook from 'app/component/common/role-book.jsx';


'use strict';

// Define component
class MainComponent extends Component {
    //
    constructor(props) {
        //
        super(props);

        this.state = {
            lang: 'KOR'
        };

        this.getLanguage = this.getLanguage.bind(this);
        this.changeLanguage = this.changeLanguage.bind(this);
        this.initLanguage = this.initLanguage.bind(this);
    }
    componentDidMount() {
        //
        this.initLanguage();
    }
    // custom
    getLanguage() {
        return this.state.lang;
    }
    changeLanguage(lang) {
        this.setState({lang: lang});
        MainComponent.lang = lang;
    }
    initLanguage() {
        //
        let lang;

        if (navigator.language) lang = navigator.language;
        else if (navigator.browserLanguage) lang = navigator.browserLanguage;
        else if (navigator.systemLanguage) lang = navigator.systemLanguage;
        else if (navigator.userLanguage) lang = navigator.userLanguage;

        if (lang === 'ko') {
            lang = 'KOR';
        }
        else if (lang === 'en') {
            lang = 'USA';
        }

        this.changeLanguage(lang);
    }
    render() {
        //
        return (
            <div >
                <header>
                    <TopMenu changeLanguage={this.changeLanguage} getLanguage={this.getLanguage}/>
                    <RoleBook />
                </header>

                <section>
                    {this.props.children}
                </section>
            </div>
        );
    }
}

MainComponent.lang = 'KOR';


export default MainComponent;